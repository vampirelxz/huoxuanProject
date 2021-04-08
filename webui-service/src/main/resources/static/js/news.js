$(function () {
    // $("#example1").DataTable({
    //     "responsive": true, "lengthChange": false, "autoWidth": false,
    //     "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    // }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');

    function newInfo2(){
        alert("这是第三种点击方式");
    }

    $('#example2').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
    $('#example3').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
    $('#example4').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
});

function refreshToken(){
    $.get("/refreshToken",{
        "refreshToken":localStorage.getItem("refreshToken")
    },function(data){
        localStorage.setItem("token",data);
    })
}

$(function(){
    $.ajax({  //这里是用jquery自带的ajax发送请求。
        url:'http://localhost:80/listNewsInfo', //这个是后台提供的借口
        dataType: 'text',
        type: 'get',
        async: false,
        success:function(date){    //这里的json就是从后台获取的借口。
            refreshToken()
            $("#toutiao-tab").html(date);
            $('#toutiaot').DataTable({
                "paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false,
                "responsive": true,
            });
        },
        error: function () {
            alert("服务未开启")
        }
    });
})

function newInfo(id){
    var type=id;
    $.ajax({  //这里是用jquery自带的ajax发送请求。
        url:'http://localhost:80/listNews?name='+type, //这个是后台提供的借口
        dataType: 'text',
        type: 'get',
        async: false,
        success:function(date){    //这里的json就是从后台获取的借口。
            refreshToken()
            $("#"+type+"-tab").html(date);
            $('#'+type+'t').DataTable({
                "paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false,
                "responsive": true,
            });
            $.attr("onclick",null)
        },
        error: function () {
            alert("error")
        }
    });
}

function newInfo2(id){
    var type=id;
    $.ajax({  //这里是用jquery自带的ajax发送请求。
        url:'http://localhost:80/listClassifyNews?name='+type, //这个是后台提供的借口
        dataType: 'text',
        type: 'get',
        async: false,
        success:function(date){    //这里的json就是从后台获取的借口。
            refreshToken()
            $("#"+type+"-tab").html(date);
            $('#'+type+'t').DataTable({
                "paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false,
                "responsive": true,
            });
            $.attr("onclick",null)
        },
        error: function () {
            alert("服务未开启")
        }
    });
}