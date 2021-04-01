// //
var token=localStorage.getItem("token")
$.get("/listNoteInfo",{
    "createId":localStorage.getItem("uid"),"token":token
},function(date){
    $("#notes").html(date);
})

function flush() {
    $.get("/listNoteInfo",{
        "createId":localStorage.getItem("uid"),"token":token
    },function(date){
        $("#notes").html(date);
    })
}
// // //
// $('#example1 tr').find('td:eq(0)').css("display:none")
$('#summernote').summernote()
$('.card-block').css('height','300px')
$("button[data-original-title*='Video']").css('display','none')
$('.toastrDefaultSuccess').click(function() {
    toastr.success('添加成功！！')
});
$('.toastrDefaultError').click(function() {
    toastr.success('删除成功！！')
});
$("#form_data").submit(function(){
    var createId = localStorage.getItem("uid")
    var id =$('#id').val()
    var title=$('#title').val()
    var content=$('.card-block').html()


    $('.toastrDefaultSuccess').attr('disabled','disabled');
    setTimeout('myfunction()',2000);

    $.ajax({
        type: "POST",
        data: {id: id ,createId: createId ,title: title,content: content,token: token},
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:80/saveOrUpdateNote" ,
        dataType: "text",
        success: function () {
            if(id == 0){
                $('#form_data')[0].reset();
                $('.card-block').html("")
            }
            flush();



        },
        error: function () {
            console.log("respon error");
            var errorNum = localStorage.getItem("errorNum");
            if(errorNum>111){
                return window.location.href= "http://localhost:80";
            }
            localStorage.setItem("errorNum",errorNum+1);
            // return window.location.href= "http://localhost/note.html";
        }
    })
})
// //

function myfunction() {
    $('.toastrDefaultSuccess').removeAttr('disabled')
}
// $.get("/getMonthModel",{"createId":localStorage.getItem("uid")},function(date){
//     $("#accordion").html(date);
//
// })


//
// function lock(){
//     $('#addBottom').attr('disabled','false');
//     setTimeout('myfunction()',1000);
// }


$('#user-name').html(localStorage.getItem('uname'));

function modify(data) {
    var id=data.name;
        $.get("/getContent",{"id":id,"token":token},function(date){
            $('#title').val(date.title);
            $('#id').val(id)
            $('.card-block').html(date.content)
        })

}

function deleteNote(r) {
    $("#notes tbody").on("click","tr",function() {
        var createId = localStorage.getItem("uid")
        var td = $(this).find("td");
        var id = td.eq(0).attr('id')

        $.get("/deleteNote",{"id":id,"createId":createId,"token":token},function(){
            flush()
        })
    });
}

function addNew() {
    $('#title').val("")
    $('#id').val(0)
    $('.card-block').html("")
}