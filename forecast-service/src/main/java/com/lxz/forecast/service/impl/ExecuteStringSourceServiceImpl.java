package com.lxz.forecast.service.impl;


import com.lxz.forecast.compile.StringSourceCompiler;
import com.lxz.forecast.dao.AlgorithmMapper;
import com.lxz.forecast.entity.AlgorithmInfo;
import com.lxz.forecast.entity.AlgorithmUser;
import com.lxz.forecast.execute.JavaClassExecutor;
import com.lxz.forecast.service.ExecuteStringSourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ExecuteStringSourceServiceImpl implements ExecuteStringSourceService {
    @Resource
    AlgorithmMapper algorithmMapper;

    /** 客户端发来的程序的运行时间限制 */
    private static final int RUN_TIME_LIMITED = 15;

    /** N_THREAD = N_CPU + 1，因为是 CPU 密集型的操作 */
    private static final int N_THREAD = 5;

    /** 负责执行客户端代码的线程池，根据《Java 开发手册》不可用 Executor 创建，有 OOM 的可能 */
    private static final ExecutorService pool = new ThreadPoolExecutor(N_THREAD, N_THREAD,
            0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(N_THREAD));

    private static final String WAIT_WARNING = "服务器忙，请稍后提交";
    private static final String NO_OUTPUT = "Nothing.";

    @Override
    public String execute2(String source, String systemIn,String userId,String algorithmId) {
        // 编译结果收集器
        DiagnosticCollector<JavaFileObject> compileCollector = new DiagnosticCollector<>();

        // 编译源代码
        byte[] classBytes = StringSourceCompiler.compile(source, compileCollector);

        // 编译不通过，获取并返回编译错误信息
        if (classBytes == null) {
            // 获取编译错误信息
            List<Diagnostic<? extends JavaFileObject>> compileError = compileCollector.getDiagnostics();
            StringBuilder compileErrorRes = new StringBuilder();
            for (Diagnostic diagnostic : compileError) {
                compileErrorRes.append("Compilation error at ");
                compileErrorRes.append(diagnostic.getLineNumber());
                compileErrorRes.append(".");
                compileErrorRes.append(System.lineSeparator());
            }
            return compileErrorRes.toString();
        }

        long startTime = System.currentTimeMillis();
        //得到虚拟机运行、程序开始执行时jvm所占用的内存。
        Runtime runtime = Runtime.getRuntime();
        long l = runtime.totalMemory();
        long l1 = runtime.freeMemory();
        long startSpace=l-l1;
        System.out.println(startSpace);

        // 运行字节码的main方法
        Callable<String> runTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return JavaClassExecutor.execute(classBytes, systemIn);
            }
        };

        Future<String> res = null;
        try {
            res = pool.submit(runTask);
        } catch (RejectedExecutionException e) {
            return WAIT_WARNING;
        }

        // 获取运行结果，处理非客户端代码错误
        String runResult;
        try {
            runResult = res.get(RUN_TIME_LIMITED, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            runResult = "Program interrupted.";
        } catch (ExecutionException e) {
            runResult = e.getCause().getMessage();
        } catch (TimeoutException e) {
            runResult = "Time Limit Exceeded.";
        } finally {
            res.cancel(true);
        }

        long endTime = System.currentTimeMillis();
        String expendTime=String.valueOf(endTime-startTime);
        long t = runtime.totalMemory();
        long t1 = runtime.freeMemory();
        long endSpace=t-t1;
        System.out.println(endSpace);
        DecimalFormat df = new DecimalFormat("##.00");
        String expandSpace= String.valueOf(df.format((double)(endSpace-startSpace)/1024/1024));
        //添加或更新用户记录
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format.format(date);
        Integer existAlgorithmUser = isExistAlgorithmUser(Integer.valueOf(userId), Integer.valueOf(algorithmId));
        if(existAlgorithmUser==0){
            insertAlgorithmUser(Integer.valueOf(userId),Integer.valueOf(algorithmId),source,date1,expendTime+"ms",expandSpace+"M");
        }else{
            updateAlgorithmUser(existAlgorithmUser,source,date1,expendTime+"ms",expandSpace+"M");
        }

        return runResult != null ? runResult : NO_OUTPUT;
    }

    @Override
    public String execute(String source, String systemIn) {
        // 编译结果收集器
        DiagnosticCollector<JavaFileObject> compileCollector = new DiagnosticCollector<>();

        // 编译源代码
        byte[] classBytes = StringSourceCompiler.compile(source, compileCollector);

        // 编译不通过，获取并返回编译错误信息
        if (classBytes == null) {
            // 获取编译错误信息
            List<Diagnostic<? extends JavaFileObject>> compileError = compileCollector.getDiagnostics();
            StringBuilder compileErrorRes = new StringBuilder();
            for (Diagnostic diagnostic : compileError) {
                compileErrorRes.append("Compilation error at ");
                compileErrorRes.append(diagnostic.getLineNumber());
                compileErrorRes.append(".\n");
                compileErrorRes.append(System.lineSeparator());
                compileErrorRes.append("reason:");
                compileErrorRes.append(diagnostic.getCode());
            }
            return compileErrorRes.toString();
        }

        // 运行字节码的main方法
        Callable<String> runTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return JavaClassExecutor.execute(classBytes, systemIn);
            }
        };


        Future<String> res = null;
        try {
            res = pool.submit(runTask);
        } catch (RejectedExecutionException e) {
            return WAIT_WARNING;
        }

        // 获取运行结果，处理非客户端代码错误
        String runResult;
        try {
            runResult = res.get(RUN_TIME_LIMITED, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            runResult = "Program interrupted.";
        } catch (ExecutionException e) {
            runResult = e.getCause().getMessage();
        } catch (TimeoutException e) {
            runResult = "Time Limit Exceeded.";
        } finally {
            res.cancel(true);
        }
        return runResult != null ? runResult : NO_OUTPUT;
    }

    @Override
    public List<AlgorithmInfo> listAlgorithmInfo() {
        List<AlgorithmInfo> algorithmInfos = algorithmMapper.listAlgorithmInfo();
        return algorithmInfos;
    }

    @Override
    public AlgorithmInfo getAlgorithmInfo(String id) {
        AlgorithmInfo algorithmInfo = null;
        try {
             algorithmInfo = algorithmMapper.getAlgorithmInfo(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("不存在的id");
        }
        return algorithmInfo;
    }

    @Override
    public void insertAlgorithmUser(Integer userId, Integer algorithmId, String content, String updateTime, String timeExpend, String spaceExpend) {
        try{
            algorithmMapper.insertAlgorithmUser(userId,algorithmId,content,updateTime,timeExpend,spaceExpend);
        }catch (Exception e){
            System.out.println("添加失败");
            e.printStackTrace();
        }
    }

    @Override
    public void updateAlgorithmUser(Integer algorithmId, String content, String updateTime, String timeExpend, String spaceExpend) {
        try{
            algorithmMapper.updateAlgorithmUser(algorithmId,content,updateTime,timeExpend,spaceExpend);
        }catch (Exception e){
            System.out.println("更新失败");
            e.printStackTrace();
        }
    }

    @Override
    public Integer isExistAlgorithmUser(Integer userId, Integer algorithmId) {
        Integer algorithmUserId ;
        Integer existAlgorithmUser =null;
        try {
            existAlgorithmUser = Integer.valueOf(algorithmMapper.isExistAlgorithmUser(userId, algorithmId).getId());
        }catch (Exception e){
            System.out.println("用户未有该题目记录");
            e.printStackTrace();
        }
        if(existAlgorithmUser != null){
            algorithmUserId=existAlgorithmUser;
        }else{
            algorithmUserId=0;
        }
        return algorithmUserId;
    }

    @Override
    public AlgorithmUser getAlgorithmUser(Integer userId, Integer algorithmId) {
        Integer existAlgorithmUser = isExistAlgorithmUser(userId, algorithmId);
        AlgorithmUser algorithmUser=null;
        if(existAlgorithmUser != null){
            algorithmUser = algorithmMapper.getAlgorithmUser(existAlgorithmUser);
        }
        return algorithmUser;
    }
}
