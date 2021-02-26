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