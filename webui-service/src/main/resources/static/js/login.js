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
            url: "http://192.168.13.71:2001/user/auth" ,
            dataType: "JSON",
            success: function (data) {
                if(data.success){
                    if (!window.localStorage) {
                        alsert(" 当浏览器不支持 localStorage ...")
                    } else {
                        localStorage.setItem("token",data.token);
                        localStorage.refreshToken = data.refreshToken;
                        alert(localStorage.getItem('token'))
                        return window.location.href= data.message;
                    }

                    // alert(localStorage.refreshToken)

                }
                else {
                    $("#loginInfor").css({ "display": "block", "opacity": "1" });
                    $("#loginInfor").animate({ opacity: 0 }, 2000);
                    $("#loginInfor").html(data.message);
                    console.log("respon success, but the password is worry!");
                }
            },
            error: function () {
                console.log("respon error");
            }
        });

});
