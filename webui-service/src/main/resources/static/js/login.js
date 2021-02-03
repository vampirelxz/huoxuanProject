$("#apply_link_form").submit(function(){
    var username = $("#email").val();
    var password = $("#pwd").val();
    console.log("username:" + username);
    console.log("password:" + password);
    var data = { 'userName': username, 'password': password };
    $.post("http://192.168.13.71:2001/user/login",{email: username,pwd: password},function (data) {
        if(data.success){
            return window.location.href= data.message;
        }
        else {
            $("#loginInfor").css({ "display": "block", "opacity": "1" });
            $("#loginInfor").animate({ opacity: 0 }, 2000);
            $("#loginInfor").html(data.message);
            console.log("respon success, but the password is worry!");
        }

    });
        // $.ajax({
        //     type: "POST",
        //     data: "{email: username,pwd: password}",
        //     contentType : "application/x-www-form-urlencoded; charset=utf-8",
        //     url: "http://192.168.13.71:2001/user/login" ,
        //     dataType: "JSON",
        //     success: function (data) {
        //         if(data.success){
        //
        //             return window.location.href= data.message;
        //         }
        //         else {
        //             $("#loginInfor").css({ "display": "block", "opacity": "1" });
        //             $("#loginInfor").animate({ opacity: 0 }, 2000);
        //             $("#loginInfor").html(data.message);
        //             console.log("respon success, but the password is worry!");
        //         }
        //     },
        //     error: function () {
        //         console.log("respon error");
        //     }
        // });

});
