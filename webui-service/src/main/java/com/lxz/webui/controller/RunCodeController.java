package com.lxz.webui.controller;


import com.lxz.webui.consumer.api.feign.GatewayFeign;
import com.lxz.webui.consumer.api.feign.WeatherFeign;
import com.lxz.webui.entity.AlgorithmInfo;
import com.lxz.webui.entity.AlgorithmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class RunCodeController {
    @Autowired
    UtilController utilController;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WeatherFeign weatherFeign;
    @Autowired
    GatewayFeign gatewayFeign;

    @RequestMapping(path = {"/run"}, method = RequestMethod.POST)
    public String runCode(@RequestParam("source") String source,
                          @RequestParam("systemIn") String systemIn, @RequestParam("algorithmId") String algorithmId, @RequestParam("userId") String userId, Model model, @RequestParam("token") String token) {
        System.out.println(source);
        String runResult;
        try{
            String body = gatewayFeign.runCode(source, systemIn, algorithmId, userId, token);
            // 处理html中换行的问题
            runResult = body.replaceAll(System.lineSeparator(), "<br/>");
        }catch (Exception e){
            e.printStackTrace();
            return "localhost:80/login.html";
        }
        System.out.println(runResult);
        model.addAttribute("runResult", runResult);
        return "/algorithm::show";
    }

    @RequestMapping(path = {"/listAlgorithmInfo"},method = RequestMethod.GET)
    public String listAlgorithmInfo(Model model){
        List<AlgorithmInfo> algorithmInfos = weatherFeign.listAlgorithmInfo();
        for(int i=0;i<algorithmInfos.size();i++){
            algorithmInfos.get(i).setQuestion(algorithmInfos.get(i).getQuestion().replaceAll("\n","<br/>"));
        }
        model.addAttribute("questionList",algorithmInfos);
        return "algorithm::questionList";
    }

    @RequestMapping(path = {"/getAlgorithmUser"},method = RequestMethod.GET)
    public String getAlgorithmUser(Model model, @RequestParam("userId") Integer userId,@RequestParam("algorithmId") Integer algorithmId,@RequestParam("token") String token){
        try{
            AlgorithmUser algorithmUser = gatewayFeign.getAlgorithmUser(userId,algorithmId,token);
            if(algorithmUser == null || algorithmUser.getContent()==null){
                AlgorithmUser algorithmUser1 = new AlgorithmUser();
                algorithmUser1.setContent("public class Run {\n" +
                        "    public static void main(String[] args) {\n" +
                        "\n" +
                        "    }\n" +
                        "}");
                algorithmUser1.setSpaceExpend("--M");
                algorithmUser1.setTimeExpend("--ms");
                model.addAttribute("algorithmUserInfo",algorithmUser1);
            }else{
                model.addAttribute("algorithmUserInfo",algorithmUser);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "algorithm::algorithmUserInfo";
    }

    @RequestMapping(path = {"/getAlgorithmInfo"},method = RequestMethod.GET)
    public String getAlgorithmInfo(Model model,@RequestParam("id")Integer id,@RequestParam("token") String token){
        AlgorithmInfo algorithmInfo = gatewayFeign.getAlgorithmInfo(id,token);
        // 处理html中换行的问题
        if(algorithmInfo.getContent1()!=null && algorithmInfo.getThinking1()!=null) {
            algorithmInfo.setContent1(algorithmInfo.getContent1().replaceAll("\n", "<br/>"));
            algorithmInfo.setThinking1(algorithmInfo.getThinking1().replaceAll("\n", "<br/>"));
        }
        if(algorithmInfo.getContent2()!=null && algorithmInfo.getThinking2()!=null) {
            algorithmInfo.setContent2(algorithmInfo.getContent2().replaceAll("\n", "<br/>"));
            algorithmInfo.setThinking2(algorithmInfo.getThinking2().replaceAll("\n", "<br/>"));
        }
        if(algorithmInfo.getContent3()!=null && algorithmInfo.getThinking3()!=null) {
            algorithmInfo.setContent3(algorithmInfo.getContent3().replaceAll("\n", "<br/>"));
            algorithmInfo.setThinking3(algorithmInfo.getThinking3().replaceAll("\n", "<br/>"));
        }
        model.addAttribute("algorithmInfo",algorithmInfo);
        return "algorithm::solution";
    }
}

