package com.lxz.forecast.controller;


import com.lxz.forecast.service.ExecuteStringSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunCodeController {

    @Autowired
    private ExecuteStringSourceService executeStringSourceService;


    @RequestMapping(path = {"/run"}, method = RequestMethod.POST)
    public String runCode(@RequestParam("source") String source,
                          @RequestParam("systemIn") String systemIn) {
        String runResult = executeStringSourceService.execute(source, systemIn);
        // 处理html中换行的问题
//        runResult = runResult.replaceAll(System.lineSeparator(), "<br/>");

        return runResult;
    }

    @RequestMapping(path = {"/run2"}, method = RequestMethod.POST)
    public String runCode2(@RequestParam("source") String source,
                          @RequestParam("systemIn") String systemIn,
                           @RequestParam("userId") String userId,
                           @RequestParam("algorithmId") String algorithmId) {
        String runResult = executeStringSourceService.execute2(source, systemIn, userId, algorithmId);
        // 处理html中换行的问题
//        runResult = runResult.replaceAll(System.lineSeparator(), "<br/>");

        return runResult;
    }
}
