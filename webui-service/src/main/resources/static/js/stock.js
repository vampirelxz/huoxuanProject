function refreshToken(){
    $.get("/refreshToken",{
        "refreshToken":localStorage.getItem("refreshToken")
    },function(data){
        localStorage.setItem("token",data);
    })
}

$('.toastsDefaultSuccess').click(function() {
    $(document.getElementById('selfStock')).Toasts('create', {
        class: 'bg-success',
        title: '关注股票',
        subtitle: '成功',
        body: '添加成功！！！'
    })
});

$('#user-name').html(localStorage.getItem('uname'));


function findInfo() {
    var code=$('#stock-code').val();
    $.get("/realtimeStock",{
        "code":code
    },function(date){
        refreshToken()
        $("#timeStock").html(date);
    })
}

$.get("/stock",{
},function(date){
    $("#baseStock").html(date);
})
var token=localStorage.getItem("token")

$.get("/selfStock",{
    "createId":localStorage.getItem("uid"),"token":token
},function(date){
    refreshToken()
    $("#selfStock").html(date);
})

function findInInfo(value) {
    $.get("/realtimeStock",{
        "code":value.innerText
    },function(date){
        refreshToken()
        $("#timeStock").html(date);
    })

}

function flush() {
    $.get("/selfStock",{
        "createId":localStorage.getItem("uid"),"token":token
    },function(date){
        $("#selfStock").html(date);
    })
}

$("#form_data").submit(function(){
    var stockId = $("#stockId").val();
    var createId = localStorage.getItem("uid")
    if(stockId == null){
        return
    }

    $('.toastrDefaultError').attr('disabled','disabled');
    setTimeout('myfunction()',2000);
    $.ajax({
        type: "POST",
        data: {stockId: stockId ,createId: createId,token:token},
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:80/insertSelfStock" ,
        dataType: "text",
        success: function () {
            refreshToken()
            flush()
        },
        error: function () {
            console.log("respon error");
            var errorNum = localStorage.getItem("errorNum");
            if(errorNum>111){
                return window.location.href= "http://localhost:80";
            }
            localStorage.setItem("errorNum",errorNum+1)
            return window.location.href= "http://localhost/stock/stock.html";
        }
    })
})

function deleteStock(r) {
    $("#self-table tbody").on("click","tr",function() {
        var td = $(this).find("td");
        var code = td.eq(0).text();
        $.post("/deleteSelfStock",{"createId":localStorage.getItem("uid"),
            "stockId":code,"token":token
        },function(){
            refreshToken()
            var i=r.parentNode.parentNode.rowIndex;
            document.getElementById('self-table').deleteRow(i);
            // $("#self-table tr").remove();
        })
    });
}

$('.toastrDefaultError').click(function() {
    toastr.success('删除成功！！')
});

function myfunction() {
    $('#addBottom').removeAttr('disabled')
}
