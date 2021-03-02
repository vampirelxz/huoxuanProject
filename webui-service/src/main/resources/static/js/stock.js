$.get("/getToken",{
"token":localStorage.getItem("token")
},function(date){

})

$('#user-name').html(localStorage.getItem('uname'));


function findInfo() {
    var code=$('#stock-code').val();
    $.get("/realtimeStock",{
        "code":code
    },function(date){
        $("#timeStock").html(date);
    })
}

$.get("/stock",{
},function(date){
    $("#baseStock").html(date);
})

$.get("/selfStock",{
    "createId":localStorage.getItem("uid")
},function(date){
    $("#selfStock").html(date);
})

function findInInfo(value) {
    $.get("/realtimeStock",{
        "code":value.innerText
    },function(date){
        $("#timeStock").html(date);
    })

}

function flush() {
    $.get("/selfStock",{
        "createId":localStorage.getItem("uid")
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
    $.ajax({
        type: "POST",
        data: {stockId: stockId ,createId: createId},
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:80/insertSelfStock" ,
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
            return window.location.href= "http://localhost/stock/stock.html";
        }
    })
})

function deleteStock() {
    $("#self-table tbody").on("click","tr",function() {
        var td = $(this).find("td");
        var code = td.eq(0).text();
        $.post("/deleteSelfStock",{"createId":localStorage.getItem("uid"),
            "stockId":code
        },function(){
            $("#self-table tr:last").remove();
        })
    });
}

