package com.lxz.forecast.controller;


import com.lxz.forecast.entity.AlgorithmInfo;
import com.lxz.forecast.entity.AlgorithmUser;
import com.lxz.forecast.service.ExecuteStringSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RunCodeController {

    @Autowired
    private ExecuteStringSourceService executeStringSourceService;


//    @RequestMapping(path = {"/run"}, method = RequestMethod.POST)
//    public String runCode(@RequestParam("source") String source,
//                          @RequestParam("systemIn") String systemIn) {
//        String runResult = executeStringSourceService.execute(source, systemIn);
//        return runResult;
//    }
//
//    @RequestMapping(path = {"/run2"}, method = RequestMethod.POST)
//    public String runCode2(@RequestParam("source") String source,
//                          @RequestParam("systemIn") String systemIn,
//                           @RequestParam("userId") String userId,
//                           @RequestParam("algorithmId") String algorithmId) {
//        String runResult = executeStringSourceService.execute2(source, systemIn, userId, algorithmId);
//        return runResult;
//    }

    @RequestMapping(path = {"/run"}, method = RequestMethod.POST)
    public String runCode2(@RequestParam("source") String source,
                           @RequestParam("systemIn") String systemIn,
                           @RequestParam("userId") String userId,
                           @RequestParam("algorithmId") String algorithmId) {
        String runResult=null;
        if(userId == null || userId.equals(0) || algorithmId == null || algorithmId.isEmpty()){
            System.out.println("游客或未选中题目");
            runResult=executeStringSourceService.execute(source, systemIn);
        }else {
            System.out.println("存入数据库");
            runResult = executeStringSourceService.execute2(source, systemIn, userId, algorithmId);
        }
        return runResult;
    }

    @RequestMapping(path = {"/listAlgorithmInfo"},method = RequestMethod.GET)
    public List<AlgorithmInfo> listAlgorithmInfo(){
        List<AlgorithmInfo> algorithmInfos = executeStringSourceService.listAlgorithmInfo();
        return algorithmInfos;
    }

    @RequestMapping(path = {"/getAlgorithmInfo"},method = RequestMethod.GET)
    public AlgorithmInfo getAlgorithmInfo(@RequestParam("id") String id){
        AlgorithmInfo algorithmInfo = executeStringSourceService.getAlgorithmInfo(id);
        return algorithmInfo;
    }

    @RequestMapping(path = {"/getAlgorithmUser"},method = RequestMethod.POST)
    public AlgorithmUser getAlgorithmUser(@RequestParam("userId") Integer userId,@RequestParam("algorithmId") Integer algorithmId){
        AlgorithmUser algorithmUser = executeStringSourceService.getAlgorithmUser(userId, algorithmId);
        return algorithmUser;
    }
}
