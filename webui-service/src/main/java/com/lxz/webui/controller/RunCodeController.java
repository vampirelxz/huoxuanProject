package com.lxz.webui.controller;


import com.lxz.webui.consumer.api.feign.WeatherFeign;
import com.lxz.webui.entity.AlgorithmInfo;
import com.lxz.webui.entity.AlgorithmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

//    @Autowired
//    private ExecuteStringSourceService executeStringSourceService;

    @RequestMapping(path = {"/run"}, method = RequestMethod.POST)
    public String runCode(@RequestParam("source") String source,
                          @RequestParam("systemIn") String systemIn,@RequestParam("algorithmId") String algorithmId,@RequestParam("userId") String userId, Model model) {
        System.out.println(source);
        String runResult;
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("source", source);
        bodyMap.add("systemIn", systemIn);
        bodyMap.add("algorithmId", algorithmId);
        bodyMap.add("userId", userId);
        String token=utilController.t;
        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/forecast/run";
        try{
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            String body = exchange.getBody();
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
            System.out.println(algorithmInfos.get(i).getQuestion());
            algorithmInfos.get(i).setQuestion(algorithmInfos.get(i).getQuestion().replaceAll("\n","<br/>"));
        }
        model.addAttribute("questionList",algorithmInfos);
        return "algorithm::questionList";
    }

    @RequestMapping(path = {"/getAlgorithmUser"},method = RequestMethod.GET)
    public String getAlgorithmUser(@RequestParam("userId") Integer userId,@RequestParam("algorithmId") Integer algorithmId,Model model){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("userId", userId);
        bodyMap.add("algorithmId", algorithmId);
        String token=utilController.t;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);
        String url="http://localhost:2001/forecast/getAlgorithmUser";
        try{
            ResponseEntity<AlgorithmUser> exchange = restTemplate.exchange(url, HttpMethod.POST, request, AlgorithmUser.class);
            AlgorithmUser algorithmUser = exchange.getBody();
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
                // 处理html中换行的问题
//                algorithmUser.setContent(algorithmUser.getContent().replaceAll("\n", "<br/>"));
                model.addAttribute("algorithmUserInfo",algorithmUser);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "algorithm::algorithmUserInfo";
    }

    @RequestMapping(path = {"/getAlgorithmInfo"},method = RequestMethod.GET)
    public String getAlgorithmInfo(Integer id,Model model){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();
        String token=utilController.t;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);
        String url="http://localhost:2001/forecast/getAlgorithmInfo?id="+id;
        ResponseEntity<AlgorithmInfo> exchange = restTemplate.exchange(url, HttpMethod.GET, request, AlgorithmInfo.class);
        AlgorithmInfo algorithmInfo = exchange.getBody();
        // 处理html中换行的问题
        algorithmInfo.setContent1(algorithmInfo.getContent1().replaceAll("\n", "<br/>"));
        algorithmInfo.setThinking1(algorithmInfo.getThinking1().replaceAll("\n", "<br/>"));
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

