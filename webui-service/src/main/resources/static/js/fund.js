function refreshToken(){
    $.get("/refreshToken",{
        "refreshToken":localStorage.getItem("refreshToken")
    },function(data){
        localStorage.setItem("token",data);
    })
}

$('.toastsDefaultSuccess').click(function() {
    $(document.getElementById('selfFund')).Toasts('create', {
        class: 'bg-success',
        title: '关注基金',
        subtitle: '成功',
        body: '添加成功！！！'
    })
});

var token=localStorage.getItem("token")

$.get("/selfFund",{
    "createId":localStorage.getItem("uid"),"token":token
},function(date){
    $("#selfFund").html(date);
})
//

//
function flush() {
    $.get("/selfFund",{
        "createId":localStorage.getItem("uid"),"token":token
    },function(date){
        refreshToken()
        $("#selfFund").html(date);
    })
}
//
$("#form_data").submit(function(){
    var fundId = $("#fundId").val();
    var createId = localStorage.getItem("uid")
    if(fundId == null){
        return
    }
    refreshToken()
    $('#addBottom').attr('disabled','disabled');
    setTimeout('myfunction()',2000);
    $.ajax({
        type: "POST",
        data: {fundId: fundId ,createId: createId,token: token},
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:80/insertSelfFund" ,
        dataType: "text",
        success: function () {
            flush()
        },
        error: function () {
            console.log("respon error");
            var errorNum = localStorage.getItem("errorNum");
            if(errorNum>111){
                return window.location.href= "http://localhost:80";
            }
            localStorage.setItem("errorNum",errorNum+1)
            return window.location.href= "http://localhost/stock/fund.html";
        }
    })
})
//
function deleteFund(r) {
    $("#self-table tbody").on("click","tr",function() {
        var td = $(this).find("td");
        var code = td.eq(1).text();
        refreshToken()
        $.post("/deleteSelfFund",{"createId":localStorage.getItem("uid"),
            "fundId":code,"token":token
        },function(){
            // $("#self-table tr:last").remove();
            var i=r.parentNode.parentNode.rowIndex;
            document.getElementById('self-table').deleteRow(i);
        })
    });
}

// #############
$.get("/rankFund",function(date){
    $("#rankFund").html(date);
})

function findInInfo(value) {
    $.get("/detailFund",{
        "code":value.innerText
    },function(date){
        refreshToken()
        $("#detailFund").html(date);
        $("#detailFund").css('display','block');
        $("#listFund").css('display','none');
    })

}

$.get("/getToken",{
    "token":localStorage.getItem("token")
},function(date){

})

$('#user-name').html(localStorage.getItem('uname'));

function findInfo() {
    var code=$('#fund-code').val();
    $.get("/listFund",{
        "code":code
    },function(date){
        refreshToken()
        $("#listFund").html(date);
        $("#detailFund").css('display','none');
        $("#listFundw").css('display','block');
        $("#listFund").css('display','block');
    })
}

$('.toastrDefaultError').click(function() {
    toastr.success('删除成功！！')
});

function myfunction() {
    $('#addBottom').removeAttr('disabled')
}

