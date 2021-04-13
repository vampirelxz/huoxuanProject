$("#apply_link_form").submit(function(){
    var username = $("#email").val();
    var password = $("#pwd").val();
    console.log("username:" + username);
    console.log("password:" + password);
    var data = { 'userName': username, 'password': password };
 /*   $.post("http://192.168.13.71:2001/user/auth",{email: username,pwd: password},function (data) {
        alert(1)
        // if(data.success){
        //     alert(data.message);
        //    return window.location.href= data.message;
        // }
        // else {
        //     $("#loginInfor").css({ "display": "block", "opacity": "1" });
        //     $("#loginInfor").animate({ opacity: 0 }, 2000);
        //     $("#loginInfor").html(data.message);
        //     console.log("respon success, but the password is worry!");
        // }

    },'json');*/
        $.ajax({
            type: "POST",
            data: {email: username,pwd: password},
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            url: "http://localhost:2001/user/auth" ,
            dataType: "JSON",
            success: function (data) {
                if(data.success){
                    if (!window.localStorage) {
                        alert(" 当前浏览器不支持 localStorage ...")
                    } else {
                        localStorage.setItem("token",data.token);
                        localStorage.setItem("uname",data.user.name);
                        localStorage.setItem("uid",data.user.id);
                        localStorage.setItem("email",$("#email").val())
                        localStorage.setItem("errorNum","0");
                        if($("input[type='checkbox']").prop('checked')){
                            localStorage.setItem(username,password)
                        }else{
                            localStorage.removeItem(username)
                        }
                        localStorage.refreshToken = data.refreshToken;
                        getUid();
                        // alert(localStorage.getItem('token'))
                        // alert(localStorage.getItem('uname'))
                        // alert($.cookie('uid'))

                        return window.location.href= data.message;
                    }
                    // alert(localStorage.refreshToken)
                }
                else {
                    $("#loginInfor").css({ "display": "block", "opacity": "1" });
                    $("#loginInfor").animate({ opacity: 0 }, 2000);
                    $("#loginInfor").html(data.message).css({"color":"red"});
                    console.log("respon success, but the password is worry!");
                }
            },
            error: function () {
                console.log("respon error");
            }
        });

function getUid() {
    $.ajax({
        url: "getUid/" + localStorage.getItem('uid'), //提价的路径
        type: "get",       //提交方式
        dataType: "JSON",       //规定请求成功后返回的数据
    });
}
});

function havePwd() {
    // alert(localStorage.getItem($("#email").val()))
    if (localStorage.getItem($("#email").val())) {
        // alert(2)
        $("#remember").attr("checked","checked")
        // $("input[type='checkbox']").checkbox[0].value=true
        $("#pwd").val(localStorage.getItem($("#email").val()))
    }
}
