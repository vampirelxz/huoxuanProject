package com.lxz.webui.controller;


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

@Controller
public class RunCodeController {
    @Autowired
    UtilController utilController;
    @Autowired
    RestTemplate restTemplate;
//    @Autowired
//    private ExecuteStringSourceService executeStringSourceService;

    @RequestMapping(path = {"/run"}, method = RequestMethod.POST)
    public String runCode(@RequestParam("source") String source,
                          @RequestParam("systemIn") String systemIn, Model model) {
        System.out.println(source);
        String runResult;
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("source", source);
        bodyMap.add("systemIn", systemIn);
        String token=utilController.t;
        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:1002/run";
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
}
