
function funcTest(){

    //每隔30秒执行一次timelyFun方法
    window.setTimeout("remove()",60000*60);

}
function remove(){
    localStorage.setItem("uid","0");
    localStorage.setItem("uname","请重新登录")
}
window.onload = funcTest;

