
//
//

//
// $.get("/stock",{
// },function(date){
//     $("#baseStock").html(date);
// })
//
$('.toastsDefaultSuccess').click(function() {
    $(document.getElementById('selfFund')).Toasts('create', {
        class: 'bg-success',
        title: '关注基金',
        subtitle: '成功',
        body: '添加成功！！！'
    })
});

$.get("/selfFund",{
    "createId":localStorage.getItem("uid")
},function(date){
    $("#selfFund").html(date);
})
//

//
function flush() {
    $.get("/selfFund",{
        "createId":localStorage.getItem("uid")
    },function(date){
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
    // $.post("/insertSelfStock",{"createId":localStorage.getItem("uid"),
    //     "stockId":code
    // },function(date){
    //     if(date == "false"){
    //         console.log("respon error");
    //         var errorNum = localStorage.getItem("errorNum");
    //         if(errorNum>111){
    //             return window.location.href= "http://localhost:80";
    //         }
    //         localStorage.setItem("errorNum",errorNum+1)
    //         return window.location.href= "http://localhost/stock/stock.html";
    //     }else {
    //         flush()
    //     }
    // })
    $('#addBottom').attr('disabled','disabled');
    setTimeout('myfunction()',2000);
    $.ajax({
        type: "POST",
        data: {fundId: fundId ,createId: createId},
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
        alert(code)
        $.post("/deleteSelfFund",{"createId":localStorage.getItem("uid"),
            "fundId":code
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

