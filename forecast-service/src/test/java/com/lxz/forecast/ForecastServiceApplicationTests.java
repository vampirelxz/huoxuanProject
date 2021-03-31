package com.lxz.forecast;

import com.lxz.forecast.service.ExecuteStringSourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ForecastServiceApplicationTests {



    @Autowired
    ExecuteStringSourceService executeStringSourceService;

    @Test
    void contextLoads() {
    }

    @Test
    void TestAlgothmInsert(){
        String s = executeStringSourceService.execute2("public class Run {\n" +
                "   public static void main(String[] args) {\n" +
                "\n" +
                "     int[] arr={1,2,3,4};\n" +
                "      int[] ints = productExceptSelf(arr);\n" +
                "        System.out.println(ints);\n" +
                "    }\n" +
                "\n" +
                "    public static int[] productExceptSelf(int[] nums) {\n" +
                "        int len=nums.length;\n" +
                "        int res[]=new int[len];\n" +
                "\n" +
                "        int left=1;\n" +
                "        for(int i=0;i<len;i++){\n" +
                "            if(i>0){\n" +
                "                left=left*nums[i-1];\n" +
                "            }\n" +
                "            res[i]=left;\n" +
                "        }\n" +
                "        int right=1;\n" +
                "        for(int i=len-1;i>=0;i--){\n" +
                "            if(i<len-1){\n" +
                "                right=right*nums[i+1];\n" +
                "            }\n" +
                "            res[i]*=right;\n" +
                "        }\n" +
                "        return res;\n" +
                "    }\n" +
                "}\n" +
                "\n", "", "10001", "1001");
        System.out.println(s);
    }

    @Test
    void TestIsExist(){
        executeStringSourceService.isExistAlgorithmUser(10001,1001);
    }

    @Test
    void TestListQuestion(){
//        AlgorithmInfo algorithmInfo = executeStringSourceService.getAlgorithmInfo("1001");
//        System.out.println(algorithmInfo);
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        listNode.next=listNode1;

    }



    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
