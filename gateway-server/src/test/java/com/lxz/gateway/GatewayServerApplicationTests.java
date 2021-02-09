package com.lxz.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GatewayServerApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(generateParenthesis(3));
    }
    List<String> res = new ArrayList<String>();
    public List<String> generateParenthesis(int n) {

        dfs( n, n ,"");

        return res;
    }

    void dfs(int l,int r,String curStr){
        if(l == 0 && r == 0){
            res.add(curStr);
            return;
        }
        if(l>0){
            dfs(l-1,r,curStr+"(");
        }
        if(r>l){
            dfs(l,r-1,curStr+")");
        }
    }
}
