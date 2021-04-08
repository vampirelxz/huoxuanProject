var chartDom = document.getElementById('garbageRank');
var myChart = echarts.init(chartDom);
var option;

function refreshToken(){
    $.get("/refreshToken",{
        "refreshToken":localStorage.getItem("refreshToken")
    },function(data){
        localStorage.setItem("token",data);
    })
}

function getGarbageTitle() {
    var date1=null;
    $.ajax({
        type: "get",
        url: "/garbageRankTitil",
        async: false,
        dataType: 'json',
        success : function (res) {
            refreshToken()
            date1 =res;
        }
    })
    return date1;
}

function getGarbageValue() {
    var date1=null;
    $.ajax({
        type: "get",
        url: "/garbageRankValue",
        async: false,
        dataType: 'json',
        success : function (res) {
            date1 =res;
        }
    })
    return date1;
}


option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },

    grid: {
        left: '1%',
        right: '2%',
        bottom: '3%',
        top:'0%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data: getGarbageTitle(),
    },
    series: [
        {
            name: '搜索量',
            type: 'bar',
            data: getGarbageValue(),
        }
    ]
};

option && myChart.setOption(option);

//------------发送消息-------------

$("#form_data").submit(function(){
    var message=$('#message').val()
    console.log(message)
    $("#addMessage").replaceWith("<div class=\"direct-chat-msg right\" >\n" +
        "                                    <div class=\"direct-chat-infos clearfix\">\n" +
        "                                        <span class=\"direct-chat-name float-right\">"+userName+"</span>\n" +
        "                                    </div>\n" +
        "                                    <img class=\"direct-chat-img\" src=\"../dist/img/user1-128x128.jpg\" alt=\"Message User Image\">\n" +
        "                                    <!-- /.direct-chat-img -->\n" +
        "\n" +
        "                                    <!-- /.direct-chat-infos -->\n" +
        "                                    <div class=\"direct-chat-text\" style=\"width: auto;max-width: 60%;margin-right: 1%;float: right;\">\n" +
        "                                        "+message+"\n" +
        "                                    </div>\n" +
        "                                    <!-- /.direct-chat-text -->\n" +
        "                                </div>\n" +
        "                                <div id=\"addMessage\"></div>");
    var showContent = $(".direct-chat-messages");
    showContent[0].scrollTop = showContent[0].scrollHeight;

    $.ajax({
        type: "GET",
        data: {name: message},
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        url: "http://localhost:80/garbage" ,
        dataType: "html",
        success: function (date) {
            // alert(date)
            $("#addMessage").replaceWith(date);
            var showContent = $(".direct-chat-messages");
            showContent[0].scrollTop = showContent[0].scrollHeight;
            $("#message").val("")
        },
        error: function () {
            // console.log("respon error");
            // var errorNum = localStorage.getItem("errorNum");
            // if(errorNum>111){
            //     return window.location.href= "http://localhost:80";
            // }
            // localStorage.setItem("errorNum",errorNum+1);
            // return window.location.href= "http://localhost/stock/account.html";
        }
    })
})
var userName=localStorage.getItem('uname');
//-----------------------------------
var chartDom = document.getElementById('moreInfo');
var myChart = echarts.init(chartDom);
var option;

// Generate data
var category = [];
var dottedBase = +new Date();
var lineData = [];
var barData = [];
var t1=[]
var t2=[]

for (var i = 0; i < 20; i++) {
    var date = new Date(dottedBase += 3600 * 24 * 1000);
    category.push([
        date.getFullYear(),
        date.getMonth() + 1,
        date.getDate()
    ].join('-'));
    var b = Math.random() * 200;
    var d = Math.random() * 200;
    barData.push(b)
    lineData.push(d + b);
    t1.push(d)
    t2.push(d/2+b)
}


// option
option = {
    backgroundColor: '#0f375f',
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['line', 'bar'],
        textStyle: {
            color: '#ccc'
        }
    },
    xAxis: {
        data: category,
        axisLine: {
            lineStyle: {
                color: '#ccc'
            }
        }
    },
    yAxis: {
        splitLine: {show: false},
        axisLine: {
            lineStyle: {
                color: '#ccc'
            }
        }
    },
    series: [{
        name: '可回收垃圾',
        type: 'line',
        smooth: true,
        showAllSymbol: true,
        symbol: 'emptyCircle',
        symbolSize: 15,
        data: lineData
    }, {
        name: '有害垃圾',
        type: 'bar',
        barWidth: 10,
        itemStyle: {
            barBorderRadius: 5,
            color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [
                    {offset: 0, color: '#14c8d4'},
                    {offset: 1, color: '#43eec6'}
                ]
            )
        },
        data: barData
    }, {
        name: '干垃圾',
        type: 'bar',
        barGap: '-100%',
        barWidth: 10,
        itemStyle: {
            color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [
                    {offset: 0, color: 'rgba(20,200,212,0.5)'},
                    {offset: 0.2, color: 'rgba(20,200,212,0.2)'},
                    {offset: 1, color: 'rgba(20,200,212,0)'}
                ]
            )
        },
        z: -12,
        data: t1
    }, {
        name: '厨余垃圾',
        type: 'pictorialBar',
        symbol: 'rect',
        itemStyle: {
            color: '#0f375f'
        },
        symbolRepeat: true,
        symbolSize: [12, 4],
        symbolMargin: 1,
        z: -10,
        data: t2
    }]
};

option && myChart.setOption(option);
