// var map = new AMap.Map("container", {
//     resizeEnable: true,
//     center: [116.397428, 39.90923],
//     zoom: 13
// });
// var transOptions = {
//     map: map,
//     city: localStorage.getItem("city"),
//     panel: 'panel',
//     policy: AMap.TransferPolicy.LEAST_TIME //乘车策略
// };
//
//
//
// //构造公交换乘类
// var transfer = new AMap.Transfer(transOptions);
//
// //根据起、终点名称查询公交换乘路线
// transfer.search([
//     {keyword: '长沙市政府'},
//     //第一个元素city缺省时取transOptions的city属性
//     {keyword: '火车南站'}
//     //第二个元素city缺省时取transOptions的cityd属性
// ], function(status, result) {
//     // result即是对应的公交路线数据信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_TransferResult
//     if (status === 'complete') {
//         log.success('绘制公交路线完成')
//     } else {
//         log.error('公交路线数据查询失败' + result)
//     }
// });

function refreshToken(){
    $.get("/refreshToken",{
        "refreshToken":localStorage.getItem("refreshToken")
    },function(data){
        localStorage.setItem("token",data);
    })
}


$('#user-name').html(localStorage.getItem('uname'));

if(localStorage.getItem("uid") == null) {
    window.location.href = "http://localhost/";
}

// map.plugin(["AMap.MarkerClusterer"],function() {
//     //输入提示
//     var auto = new AMap.Autocomplete({
//         input: "tipinput"
//     });
// })

// window.onLoad  = function(){
//     var auto = new AMap.Autocomplete({
//         input: "tipinput"
// })
// }


$(function(){
        $.ajax({  //这里是用jquery自带的ajax发送请求。
            url:'http://localhost:80/getCity', //这个是后台提供的借口
            dataType: 'text',
            type: 'get',
            async: false,
            success:function(date){    //这里的json就是从后台获取的借口。
                refreshToken()
                localStorage.setItem('city',date)
            },
            error: function () {
                console.log("respon error");
            }
        });

});