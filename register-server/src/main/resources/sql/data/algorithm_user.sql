INSERT INTO huoxuan.algorithm_user
(id, user_id, algorithm_id, content, delete_flag, update_time, time_expend, space_expend)
VALUES(100001, 10001, 1001, 'public class Run {
   public static void main(String[] args) {

     int[] arr={1,2,3,4};
      int[] ints = productExceptSelf(arr);
        System.out.println(ints);
    }

    public static int[] productExceptSelf(int[] nums) {
        int len=nums.length;
        int res[]=new int[len];

        int left=1;
        for(int i=0;i<len;i++){
            if(i>0){
                left=left*nums[i-1];
            }
            res[i]=left;
        }
        int right=1;
        for(int i=len-1;i>=0;i--){
            if(i<len-1){
                right=right*nums[i+1];
            }
            res[i]*=right;
        }
        return res;
    }
}

', 0, '2021-04-13', '0ms', '.51M');
INSERT INTO huoxuan.algorithm_user
(id, user_id, algorithm_id, content, delete_flag, update_time, time_expend, space_expend)
VALUES(100002, 10001, 1002, 'public class Run {
    public static void main(String[] args) {
        int a=100;
        for(int i=0;i<a;i++){
            System.out.println("i");
}
    }
}', 0, '2021-04-13', '1ms', '.51M');
INSERT INTO huoxuan.algorithm_user
(id, user_id, algorithm_id, content, delete_flag, update_time, time_expend, space_expend)
VALUES(100003, 10001, 1003, 'public class Run {
    public static void main(String[] args) {

    }
}', 0, '2021-03-30', '0ms', '.00M');
INSERT INTO huoxuan.algorithm_user
(id, user_id, algorithm_id, content, delete_flag, update_time, time_expend, space_expend)
VALUES(100004, 10001, 1008, 'public class Run {
    public static void main(String[] args) {
        int a=10000;
        for(int i=0;i<a;i++){
            System.out.println("i");
}
    }
}', 0, '2021-04-01', '2ms', '1.15M');
INSERT INTO huoxuan.algorithm_user
(id, user_id, algorithm_id, content, delete_flag, update_time, time_expend, space_expend)
VALUES(100005, 10002, 1001, 'public class Run {
    public static void main(String[] args) {

    }
}', 0, '2021-04-02', '1ms', '.28M');
