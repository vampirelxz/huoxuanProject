// $("#apply_link_form").submit(function(){
//     var username = $("#email").val();
//     var password = $("#pwd").val();
//     console.log("username:" + username);
//     console.log("password:" + password);
//     var data = { 'userName': username, 'password': password };
//         $.ajax({
//             type: "POST",
//             data: {email: username,pwd: password},
//             contentType : "application/x-www-form-urlencoded; charset=utf-8",
//             url: "http://localhost:2001/user/auth" ,
//             dataType: "JSON",
//             success: function (data) {
//                 if(data.success){
//                     if (!window.localStorage) {
//                         alert(" 当前浏览器不支持 localStorage ...")
//                     } else {
//                         localStorage.setItem("token",data.token);
//                         localStorage.setItem("uname",data.user.name);
//                         localStorage.setItem("uid",data.user.id);
//                         localStorage.setItem("errorNum","0");
//                         localStorage.refreshToken = data.refreshToken;
//                         getUid();
//                         // alert(localStorage.getItem('token'))
//                         // alert(localStorage.getItem('uname'))
//                         // alert($.cookie('uid'))
//
//                         return window.location.href= data.message;
//                     }
//
//                     // alert(localStorage.refreshToken)
//
//                 }
//                 else {
//                     $("#loginInfor").css({ "display": "block", "opacity": "1" });
//                     $("#loginInfor").animate({ opacity: 0 }, 2000);
//                     $("#loginInfor").html(data.message).css({"color":"red"});
//                     console.log("respon success, but the password is worry!");
//                 }
//             },
//             error: function () {
//                 console.log("respon error");
//             }
//         });
// function getUid() {
//     $.ajax({
//         url: "getUid/" + localStorage.getItem('uid'), //提价的路径
//         type: "get",       //提交方式
//         dataType: "JSON",       //规定请求成功后返回的数据
//
//     });
// }
// });

function haveEmail() {
    var email=$('#email').val()
    $.ajax({
        url: "/haveEmail" ,
        type: "get",
        dataType: "text",
        data: {"email":email},
        success(data){
            data = data.toString()
            if(data == "false"){
                $('#have-email').css("display","none")
            }else{
                $('#have-email').text("该邮箱未被注册")
                $('#have-email').css("display","block")
                $('#have-email').attr("iserror","true")
            }
        },
        error(){
            alert("非常抱歉，服务器出现了点错误，正在抢修中。")
        }
    });
}

function pwd_confirm(){
    var pwd=$("#password").val().toString()
    var repwd=$("#re-password").val().toString()
    // alert(pwd+","+repwd)
    if(pwd != repwd){
        $('#have-pwd').text("密码不一致")
        $('#have-pwd').css("display","block")
        $('#have-pwd').attr("iserror","true")
    }else{
        $('#have-pwd').css("display","none")
    }
}

function sendEmail(){
    var email=$('#email').val();
    if(email == null || email==""){
        console.log("error")
        $('#email').attr("placeholder","邮箱不能为空");
        $('#email').addClass('infoTextarea change')
        return;
    }
    $.ajax({
        url: "/sendUpdatePwdEmail" ,
        type: "get",
        dataType: "text",
        data: {"email":email},
        success(){
            var second = 60;
            var timer = null;
            timer = setInterval(function(){
                second -= 1;
                if(second >0){
                    $('#getCode').attr('disabled',true);
                    $('#getCode').text(second + "秒后获取验证码");
                }else{
                    clearInterval(timer);
                    $('#getCode').attr('disabled',false);
                    $('#getCode').text("发送短信验证码");
                }
            },1000);
        },
        error(){
            alert("不好意思，服务器出现了点错误，正在抢修中。")
        }
    });


}


$(function () {
    $.validator.setDefaults({
        submitHandler: function () {
            var email=$("#email").val()
            var verifyCode=$("#verification").val()
                $.ajax({
                    url: "/judgeVerifyCode" ,
                    type: "post",
                    dataType: "json",
                    data: {"email":email,"verifyCode":verifyCode},
                    success(data){
                        if(data.success.toString() == "true"){
                            localStorage.setItem("token",data.token)
                            // if(top.location !== self.location){
                            //     top.location.href = self.location.href;
                            // }
                            // window.location.href="http://localhost/"
                            $("#forgot-form").css("display","none")
                            $("#recover-form").css("display","block")
                            $(".login-box-msg").text("验证成功，请输入密码并确认")
                        }else{
                            $('#errorInfo').text(data.message)
                            $('#errorInfo').css("display","block")
                            $('#errorInfo').attr("iserror","true")
                        }
                    },
                    error(){
                        alert("不好意思，服务器出现了点错误，正在抢修中。")
                    }
                });

            }
    });
    $('#forgot-form').validate({
        rules: {
            email: {
                required: true,
                email: true,
            },
            verification: {
                required: true,
                minlength: 5
            },
        },
        messages: {
            email: {
                required: "请输入邮箱地址",
                email: "请输入有效的邮箱地址"
            },
            verification:{
                required: "请输入验证码",
                minlength: "请输入至少5位数以上的验证码"
            },

        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }

    });
});

$(function () {
    $.validator.setDefaults({
        submitHandler: function () {
            var email=$("#email").val()
            var pwd=$("#re-password").val()
            var css=$('#have-pwd').attr("iserror")
            if(css == "true"){
                alert("请输入相同的密码！！！")
                return;
            }else {
                $.ajax({
                    url: "/updatePwd",
                    type: "post",
                    dataType: "json",
                    data: {"email": email, "pwd": pwd, "token": localStorage.getItem("token")},
                    success(data) {
                        if (data.success.toString() == "true") {
                            alert("修改成功")
                            if (top.location !== self.location) {
                                top.location.href = self.location.href;
                            }
                            window.location.href = "http://localhost/"
                        } else {
                            $('#errorInfo').text(data.message)
                            $('#errorInfo').css("display", "block")
                            $('#errorInfo').attr("iserror", "true")
                        }
                    },
                    error() {
                        alert("不好意思，服务器出现了点错误，正在抢修中。")
                    }
                });
            }
            }

    });
    $('#recover-form').validate({
        rules: {
            password: {
                required: true,
                minlength: 5
            },
            repassword: {
                required: true,
                minlength: 5
            }
        },
        messages: {
            password: {
                required: "请输入密码",
                minlength: "请输入至少五位数以上的密码"
            },
            repassword: {
                required: "请再次输入密码",
                minlength: "请再次输入至少五位数以上的密码"
            },
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }

    });
});