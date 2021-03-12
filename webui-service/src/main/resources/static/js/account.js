//
$.get("/account",{
    "createId":localStorage.getItem("uid")
},function(date){
    $("#account").html(date);
})
// //
//
// //
function flush() {
    $.get("/account",{
        "createId":localStorage.getItem("uid")
    },function(date){
        $("#account").html(date);
    })
}
// //
$('#example1 tr').find('td:eq(0)').css("display:none")
$("#form_data").submit(function(){
    var createId = localStorage.getItem("uid")
    var type =$('#type option:selected').text()
    var inout=$('#inorout option:selected').text()
    var money=$('#money').val()
    if(inout == '支出'){
        money = -money;
    }
    var time =$('#account-time').val()
    var remark= $('#remark').val()

    $('.toastrDefaultSuccess').attr('disabled','disabled');
    setTimeout('myfunction()',2000);

    console.log(createId+','+time+','+type+','+remark+','+money)
    $.ajax({
        type: "POST",
        data: {type: type ,createId: createId ,money: money,time: time,remark: remark},
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:80/insertSelfAccount" ,
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
            localStorage.setItem("errorNum",errorNum+1);
            return window.location.href= "http://localhost/stock/account.html";
        }
    })
})
// //
function deleteAccount(r) {
    $("#example1 tbody").on("click","tr",function() {
        var td = $(this).find("td");
        var code = td.eq(0).attr('name')
        $.get("/deleteAccount",{"id":code},function(){
            var i=r.parentNode.parentNode.rowIndex;
            document.getElementById('example1').deleteRow(i);
        })
    });
}

$.get("/getMonthModel",{"createId":localStorage.getItem("uid")},function(date){
    $("#accordion").html(date);

})


//
// // #############
// $.get("/rankFund",function(date){
//     $("#rankFund").html(date);
// })
//
// function findInInfo(value) {
//     $.get("/detailFund",{
//         "code":value.innerText
//     },function(date){
//         $("#detailFund").html(date);
//         $("#detailFund").css('display','block');
//         $("#listFund").css('display','none');
//     })
//
// }
//
// $.get("/getToken",{
//     "token":localStorage.getItem("token")
// },function(date){
//
// })
//
// $('#user-name').html(localStorage.getItem('uname'));
//
// function findInfo() {
//     var code=$('#fund-code').val();
//     $.get("/listFund",{
//         "code":code
//     },function(date){
//         $("#listFund").html(date);
//         $("#detailFund").css('display','none');
//         $("#listFundw").css('display','block');
//         $("#listFund").css('display','block');
//     })
// }

//-------------
//- DONUT CHART -
//-------------
// Get context with jQuery - using jQuery's .get() method.
function getMonthTypePie() {
    var date1=null;
    $.ajax({
        data: {"createId": localStorage.getItem("uid")},
        type: "get",
        url: "/getMonthTypePie",
        async: false,
        dataType: 'json',
        success : function (res) {
            date1 =res;
        }
    })
    return date1;
}

function getMonthValuePie() {
    var date1=null;
    $.ajax({
        data: {"createId": localStorage.getItem("uid")},
        type: "get",
        url: "/getMonthValuePie",
        async: false,
        dataType: 'json',
        success : function (res) {
            date1 =res;
        }
    })
    return date1;
}

function getYearTypePie() {
    var date1=null;
    $.ajax({
        data: {"createId": localStorage.getItem("uid")},
        type: "get",
        url: "/getYearTypePie",
        async: false,
        dataType: 'json',
        success : function (res) {
            date1 =res;
        }
    })
    return date1;
}

function getYearValuePie() {
    var date1=null;
    $.ajax({
        data: {"createId": localStorage.getItem("uid")},
        type: "get",
        url: "/getYearValuePie",
        async: false,
        dataType: 'json',
        success : function (res) {
            date1 =res;
        }
    })
    return date1;
}

var donutChartCanvas = $('#month-chart-canvas').get(0).getContext('2d')
var donutData        = {
    labels: getMonthTypePie(),
    datasets: [
        {
            data: getMonthValuePie,
            backgroundColor : ['#f56954', '#00a65a', '#f39c12', '#00c0ef', '#3c8dbc', '#d2d6de'],
        }
    ]
}
var donutOptions     = {
    maintainAspectRatio : false,
    responsive : true,
}
//Create pie or douhnut chart
// You can switch between pie and douhnut using the method below.
new Chart(donutChartCanvas, {
    type: 'doughnut',
    data: donutData,
    options: donutOptions
})

//-------------
//- DONUT CHART -
//-------------
// Get context with jQuery - using jQuery's .get() method.
var donutChartCanvas = $('#year-chart-canvas').get(0).getContext('2d')
var donutData        = {
    labels: getYearTypePie(),
    datasets: [
        {
            data: getYearValuePie(),
            backgroundColor : ['#f56954', '#00a65a', '#f39c12', '#00c0ef', '#3c8dbc', '#d2d6de'],
        }
    ]
}
var donutOptions     = {
    maintainAspectRatio : false,
    responsive : true,
}
//Create pie or douhnut chart
// You can switch between pie and douhnut using the method below.
new Chart(donutChartCanvas, {
    type: 'doughnut',
    data: donutData,
    options: donutOptions
})



//---------------------
//- STACKED BAR CHART -
//---------------------
function thisMonth() {
    var date1=null;
    $.ajax({
        data: {"createId": localStorage.getItem("uid")},
        type: "get",
        url: "/getThisMonthData",
        async: false,
        dataType: 'json',
        success : function (res) {
            date1 =res;
        }
    })
    return date1;
}
function lastMonth() {
    var date1=null;
    $.ajax({
        data: {"createId": localStorage.getItem("uid")},
        type: "get",
        url: "/getLastMonthData",
        async: false,
        dataType: 'json',
        success : function (res) {
            date1 =res;
        }
    })
    return date1;
}
var stackedBarChartCanvas = $('#stackedBarChart-canvas').get(0).getContext('2d')
// var stackedBarChartData = $.extend(true, {}, barChartData)
var stackedBarChartData = {
    labels  : ['交通出行','投资理财','数码电器','日用百货','服饰装扮','生活服务','运动户外','餐饮美食'],
    datasets: [
        {
            label               : '本月支出',
            backgroundColor     : 'rgba(60,141,188,0.9)',
            borderColor         : 'rgba(60,141,188,0.8)',
            pointRadius          : false,
            pointColor          : '#3b8bba',
            pointStrokeColor    : 'rgba(60,141,188,1)',
            pointHighlightFill  : '#fff',
            pointHighlightStroke: 'rgba(60,141,188,1)',
            data                : thisMonth()
        },
        {
            label               : '上月支出',
            backgroundColor     : 'rgba(210, 214, 222, 1)',
            borderColor         : 'rgba(210, 214, 222, 1)',
            pointRadius         : false,
            pointColor          : 'rgba(210, 214, 222, 1)',
            pointStrokeColor    : '#c1c7d1',
            pointHighlightFill  : '#fff',
            pointHighlightStroke: 'rgba(220,220,220,1)',
            data                : lastMonth()
        },
    ]
}
var stackedBarChartOptions = {
    responsive              : true,
    maintainAspectRatio     : false,
    scales: {
        xAxes: [{
            stacked: true,
        }],
        yAxes: [{
            stacked: true
        }]
    }
}

new Chart(stackedBarChartCanvas, {
    type: 'bar',
    data: stackedBarChartData,
    options: stackedBarChartOptions
})

//-------------
//- LINE CHART -
//--------------
function thisWeek() {
    var date1=null;
    $.ajax({
        data: {"createId": localStorage.getItem("uid")},
        type: "get",
        url: "/getThisWeekData",
        async: false,
        dataType: 'json',
        success : function (res) {
            date1 =res;
        }
    })
    return date1;
}
function lastWeek() {
    var date1=null;
    $.ajax({
        data: {"createId": localStorage.getItem("uid")},
        type: "get",
        url: "/getLastWeekData",
        async: false,
        dataType: 'json',
        success : function (res) {
            date1 =res;
        }
    })
    return date1;
}

var lineChartCanvas = $('#lineChart-canvas').get(0).getContext('2d')
var lineChartOptions = {
    maintainAspectRatio : false,
    responsive : true,
    legend: {
        display: false
    },
    scales: {
        xAxes: [{
            gridLines : {
                display : false,
            }
        }],
        yAxes: [{
            gridLines : {
                display : false,
            }
        }]
    }
}
var lineChartData = {
    labels  : [ '周一', '周二', '周三', '周四', '周五', '周六','周天'],
    datasets: [
        {
            label               : '本周',
            backgroundColor     : 'rgba(60,141,188,0.9)',
            borderColor         : 'rgba(60,141,188,0.8)',
            pointRadius          : false,
            pointColor          : '#3b8bba',
            pointStrokeColor    : 'rgba(60,141,188,1)',
            pointHighlightFill  : '#fff',
            pointHighlightStroke: 'rgba(60,141,188,1)',
            data                : thisWeek()
        },
        {
            label               : '上周',
            backgroundColor     : 'rgba(210, 214, 222, 1)',
            borderColor         : 'rgba(210, 214, 222, 1)',
            pointRadius         : false,
            pointColor          : 'rgba(210, 214, 222, 1)',
            pointStrokeColor    : '#c1c7d1',
            pointHighlightFill  : '#fff',
            pointHighlightStroke: 'rgba(220,220,220,1)',
            data                : lastWeek()
        },
    ]
}
lineChartData.datasets[0].fill = false;
lineChartData.datasets[1].fill = false;
lineChartOptions.datasetFill = false

var lineChart = new Chart(lineChartCanvas, {
    type: 'line',
    data: lineChartData,
    options: lineChartOptions
})

////////////////////////////////////////
$(function () {
    /* jQueryKnob */

    $('.knob').knob({

        draw: function () {

            if (this.$.data('skin') == 'tron') {

                var a   = this.angle(this.cv)  // Angle
                    ,
                    sa  = this.startAngle          // Previous start angle
                    ,
                    sat = this.startAngle         // Start angle
                    ,
                    ea                            // Previous end angle
                    ,
                    eat = sat + a                 // End angle
                    ,
                    r   = true

                this.g.lineWidth = this.lineWidth

                this.o.cursor
                && (sat = eat - 0.3)
                && (eat = eat + 0.3)

                if (this.o.displayPrevious) {
                    ea = this.startAngle + this.angle(this.value)
                    this.o.cursor
                    && (sa = ea - 0.3)
                    && (ea = ea + 0.3)
                    this.g.beginPath()
                    this.g.strokeStyle = this.previousColor
                    this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false)
                    this.g.stroke()
                }

                this.g.beginPath()
                this.g.strokeStyle = r ? this.o.fgColor : this.fgColor
                this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false)
                this.g.stroke()

                this.g.lineWidth = 2
                this.g.beginPath()
                this.g.strokeStyle = this.o.fgColor
                this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false)
                this.g.stroke()

                return false
            }
        }
    })
    /* END JQUERY KNOB */

    $("#example1").DataTable({
        "responsive": true, "lengthChange": false, "autoWidth": false,
        "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
})
//Date range picker
$('#reservationdate').datetimepicker({
    format: 'L'
});

// function isType() {
//     var inout=$('#inorout option:selected').text()
//     if(inout == '收入'){
//         $('.b').css('display','none')
//         // $('#type').append("<option >账户存取</option><option>转账红包</option><option>投资理财</option><option>收入</option><option>退款</option><option>亲友代付</option><option>其他</option>");
//
//     }else{
//         // $('#type').append("<option>账户存取</option><option>转账红包</option><option>餐饮美食</option><option>服饰装扮</option><option>日用百货</option><option>家居家装</option><option>数码电器</option>" +
//         //     "<option>运动户外</option><option>美容美发</option><option>母婴亲子</option><option>宠物</option><option>交通出行</option><option>爱车养车</option><option>住房物业</option><option>酒店旅游</option>" +
//         //     "<option>文化休闲</option><option>教育培训</option><option>医疗健康</option><option>生活服务</option><option>公共服务</option><option>商业服务</option><option>美容美发</option><option>公益捐赠</option>" +
//         //     "<option>互助保障</option><option>投资理财</option><option>保险</option><option>信用借还</option><option>充值缴费</option><option>转账红包</option><option>账户存取</option><option>其他</option> ");
//         $('.a').css('display','none')
//     }
// }

$('.toastrDefaultSuccess').click(function() {
    toastr.success('添加成功！！')

});

// function lock(){
//     $('#addBottom').attr('disabled','false');
//     setTimeout('myfunction()',1000);
// }
function myfunction() {
    $('#addBottom').removeAttr('disabled')
}

//----------------------------------------------------------------------------------

var chartDom = document.getElementById('week-model');
var app = {};

var myChart = echarts.init(chartDom);
var option;

var piePatternSrc = 'data:image/jpeg;base64,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';
var bgPatternSrc = 'data:image/png;charset=utf-8;base64,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';

var piePatternImg = new Image();
piePatternImg.src = piePatternSrc;
var bgPatternImg = new Image();
bgPatternImg.src = bgPatternSrc;

function f() {
    var date1=null;
    $.ajax({
        data: {"createId": localStorage.getItem("uid")},
        type: "get",
        url: "/getWeekModel",
        async: false,
        dataType: 'json',
        success : function (res) {
            date1 =res;
        }
    })
    //
    // $.get("/getWeekModel", {"createId": localStorage.getItem("uid")}, function (date) {
    //     localStorage.setItem("date1", date);
    //     date1=date
    // })
     return date1;
}

var itemStyle = {
    normal: {
        opacity: 0.7,
        color: {
            image: piePatternImg,
            repeat: 'repeat'
        },
        borderWidth: 3,
        borderColor: '#235894'
    }
};
option = {
    backgroundColor: {
        image: bgPatternImg,
        repeat: 'repeat'
    },
    title: {

        textStyle: {
            color: '#235894'
        }
    },
    tooltip: {},
    series: [{
        name: '周支出',
        type: 'pie',
        selectedMode: 'single',
        selectedOffset: 30,
        clockwise: true,
        label: {
            fontSize: 18,
            color: '#235894'
        },
        labelLine: {
            lineStyle: {
                color: '#235894'
            }
        },
        data:f(),
        itemStyle: itemStyle
    }]
};

option && myChart.setOption(option);