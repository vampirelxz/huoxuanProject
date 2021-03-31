/*
 * Author: Abdullah A Almsaeed
 * Date: 4 Jan 2014
 * Description:
 *      This is a demo file used only for the main dashboard (index.html)
 **/

/* global moment:false, Chart:false, Sparkline:false */
// $(document).ready(function () {
//   $.ajax({
//         url: "getUid/" + localStorage.getItem('uid'), //提价的路径
//         type: "get",       //提交方式
//         dataType: "JSON",       //规定请求成功后返回的数据
//
//     });
// })
//------------发送消息-------------

$("#form_data1").submit(function(){

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
    data: {question: message},
    contentType : "application/x-www-form-urlencoded; charset=utf-8",
    url: "http://localhost:80/getAiReply" ,
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
      $("#message").val("")
    }
  })
})
//________________________

$('.toastsDefaultSuccess').click(function() {
  $(document.getElementById('todo-list')).Toasts('create', {
    class: 'bg-success',
    title: '每日清单',
    subtitle: '成功',
    body: '每日清单添加成功！！！'
  })
});

$(function () {
  'use strict'

  if(localStorage.getItem("uid") == null) {
    top.location.href = self.location.href;
    window.location.href = "http://localhost/";
  }

  $('#datetimepicker2').datetimepicker({
    format: 'YYYY-MM-DD hh:mm',
    locale: moment.locale('zh-cn')
  })


  // Make the dashboard widgets sortable Using jquery UI
  $('.connectedSortable').sortable({
    placeholder: 'sort-highlight',
    connectWith: '.connectedSortable',
    handle: '.card-header, .nav-tabs',
    forcePlaceholderSize: true,
    zIndex: 999999
  })
  $('.connectedSortable .card-header').css('cursor', 'move')

  // jQuery UI sortable for the todo list
  $('.todo-list').sortable({
    placeholder: 'sort-highlight',
    handle: '.handle',
    forcePlaceholderSize: true,
    zIndex: 999999
  })

  // bootstrap WYSIHTML5 - text editor
  $('.textarea').summernote()

  $('.daterange').daterangepicker({
    ranges: {
      Today: [moment(), moment()],
      Yesterday: [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
      'Last 7 Days': [moment().subtract(6, 'days'), moment()],
      'Last 30 Days': [moment().subtract(29, 'days'), moment()],
      'This Month': [moment().startOf('month'), moment().endOf('month')],
      'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    },
    startDate: moment().subtract(29, 'days'),
    endDate: moment()
  }, function (start, end) {
    // eslint-disable-next-line no-alert
    alert('You chose: ' + start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
  })

  /* jQueryKnob */
  $('.knob').knob()

  // jvectormap data
  var visitorsData = {
    US: 398, // USA
    SA: 400, // Saudi Arabia
    CA: 1000, // Canada
    DE: 500, // Germany
    FR: 760, // France
    CN: 300, // China
    AU: 700, // Australia
    BR: 600, // Brazil
    IN: 800, // India
    GB: 320, // Great Britain
    RU: 3000 // Russia
  }


  // Sparkline charts
  var sparkline1 = new Sparkline($('#sparkline-1')[0], { width: 80, height: 50, lineColor: '#92c1dc', endColor: '#ebf4f9' })
  var sparkline2 = new Sparkline($('#sparkline-2')[0], { width: 80, height: 50, lineColor: '#92c1dc', endColor: '#ebf4f9' })
  var sparkline3 = new Sparkline($('#sparkline-3')[0], { width: 80, height: 50, lineColor: '#92c1dc', endColor: '#ebf4f9' })

  sparkline1.draw([1000, 1200, 920, 927, 931, 1027, 819, 930, 1021])
  sparkline2.draw([515, 519, 520, 522, 652, 810, 370, 627, 319, 630, 921])
  sparkline3.draw([15, 19, 20, 22, 33, 27, 31, 27, 19, 30, 21])

  // The Calender
  $('#calendar').datetimepicker({
    format: 'L',
    inline: true,

  })

  // SLIMSCROLL FOR CHAT WIDGET
  $('#chat-box').overlayScrollbars({
    height: '250px'
  })

  /* Chart.js Charts */
  // Sales chart
  var salesChartCanvas = document.getElementById('revenue-chart-canvas').getContext('2d')
  // $('#revenue-chart').get(0).getContext('2d');

  var salesChartData = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [
      {
        label: 'Digital Goods',
        backgroundColor: 'rgba(60,141,188,0.9)',
        borderColor: 'rgba(60,141,188,0.8)',
        pointRadius: false,
        pointColor: '#3b8bba',
        pointStrokeColor: 'rgba(60,141,188,1)',
        pointHighlightFill: '#fff',
        pointHighlightStroke: 'rgba(60,141,188,1)',
        data: [28, 48, 40, 19, 86, 27, 90]
      },
      {
        label: 'Electronics',
        backgroundColor: 'rgba(210, 214, 222, 1)',
        borderColor: 'rgba(210, 214, 222, 1)',
        pointRadius: false,
        pointColor: 'rgba(210, 214, 222, 1)',
        pointStrokeColor: '#c1c7d1',
        pointHighlightFill: '#fff',
        pointHighlightStroke: 'rgba(220,220,220,1)',
        data: [65, 59, 80, 81, 56, 55, 40]
      }
    ]
  }

  var salesChartOptions = {
    maintainAspectRatio: false,
    responsive: true,
    legend: {
      display: false
    },
    scales: {
      xAxes: [{
        gridLines: {
          display: false
        }
      }],
      yAxes: [{
        gridLines: {
          display: false
        }
      }]
    }
  }

  // This will get the first returned node in the jQuery collection.
  // eslint-disable-next-line no-unused-vars
  var salesChart = new Chart(salesChartCanvas, { // lgtm[js/unused-local-variable]
    type: 'line',
    data: salesChartData,
    options: salesChartOptions
  })

  // Donut Chart
  var pieChartCanvas = $('#sales-chart-canvas').get(0).getContext('2d')
  var pieData = {
    labels: [
      'Instore Sales',
      'Download Sales',
      'Mail-Order Sales'
    ],
    datasets: [
      {
        data: [30, 12, 20],
        backgroundColor: ['#f56954', '#00a65a', '#f39c12']
      }
    ]
  }
  var pieOptions = {
    legend: {
      display: false
    },
    maintainAspectRatio: false,
    responsive: true
  }
  // Create pie or douhnut chart
  // You can switch between pie and douhnut using the method below.
  // eslint-disable-next-line no-unused-vars
  var pieChart = new Chart(pieChartCanvas, { // lgtm[js/unused-local-variable]
    type: 'doughnut',
    data: pieData,
    options: pieOptions
  })

  // // Sales graph chart
  // var salesGraphChartCanvas = $('#line-chart').get(0).getContext('2d')
  // // $('#revenue-chart').get(0).getContext('2d');
  //
  // var salesGraphChartData = {
  //   labels: ['1/29 Q1', '1/30 Q2', '1/31 Q3'],
  //   datasets: [
  //     {
  //       label: 'Digital Goods',
  //       fill: false,
  //       borderWidth: 2,
  //       lineTension: 0,
  //       spanGaps: true,
  //       borderColor: '#efefef',
  //       pointRadius: 3,
  //       pointHoverRadius: 7,
  //       pointColor: '#efefef',
  //       pointBackgroundColor: '#efefef',
  //       data: [12,13,14]
  //     }
  //   ]
  // }
  //
  // var salesGraphChartOptions = {
  //   maintainAspectRatio: false,
  //   responsive: true,
  //   legend: {
  //     display: false
  //   },
  //   scales: {
  //     xAxes: [{
  //       ticks: {
  //         fontColor: '#efefef'
  //       },
  //       gridLines: {
  //         display: false,
  //         color: '#efefef',
  //         drawBorder: false
  //       }
  //     }],
  //     yAxes: [{
  //       ticks: {
  //         stepSize: 5000,
  //         fontColor: '#efefef'
  //       },
  //       gridLines: {
  //         display: true,
  //         color: '#efefef',
  //         drawBorder: false
  //       }
  //     }]
  //   }
  // }
  //
  // // This will get the first returned node in the jQuery collection.
  // // eslint-disable-next-line no-unused-vars
  // var salesGraphChart = new Chart(salesGraphChartCanvas, { // lgtm[js/unused-local-variable]
  //   type: 'line',
  //   data: salesGraphChartData,
  //   options: salesGraphChartOptions
  // })
})


/* Chart.js Charts */
// weather chart
var forecastChartCanvas = document.getElementById('weather-chart-canvas').getContext('2d')
// $('#revenue-chart').get(0).getContext('2d');


function dateInfo() {
    var arr=[];
  $.ajax({
    type: "get",
    url: "http://localhost:2001/forecast/forecast",
    async: false,
    dataType: 'json',
    beforeSend: function(head) {

      head.setRequestHeader("Authorization", token);

    },
    success : function (res) {

        var data =res;
        var len = eval(data).length;

        for (var i = 0; i < len; i++) {
          arr[i] = []; //js中二维数组必须进行重复的声明，否则会undefind
          arr[i][0] =  data[i].date  ;
        }

    }
  })
  arr.toString().replace(/\'/g, "")
  return arr;
}
function dayInfo() {
  var arr=[];
  $.ajax({
    type: "get",
    // headers:{"Authorization":localStorage.getItem('token')}, //请求头类型
    url: "http://localhost:2001/forecast/forecast",
    async: false,
    dataType: 'json',
    beforeSend: function(head) {

      head.setRequestHeader("Authorization", token);

    },

    success : function (res) {
      var data =res;
      var len = eval(data).length;

      for (var i = 0; i < len; i++) {
        arr[i] = []; //js中二维数组必须进行重复的声明，否则会undefind
        arr[i] =  data[i].daytemp.toString()  ;
      }

    }
  })

  return arr;
}
function nightInfo() {
  var arr=[];
  $.ajax({
    type: "get",
    url: "http://localhost:2001/forecast/forecast",
    async: false,
    dataType: 'json',
    headers:{"Authorization":token}, //请求头类型
    success : function (res) {

      var data =res;
      var len = eval(data).length;

      for (var i = 0; i < len; i++) {
        arr[i] = []; //js中二维数组必须进行重复的声明，否则会undefind
        arr[i] =  data[i].nighttemp.toString()  ;
      }

    }
  });
  return arr;
}
var token=localStorage.getItem('token')
// console.log(dateInfo())
// console.log(dayInfo())
// console.log(nightInfo())

var weatherChartData = {
  labels: dateInfo(),
  datasets: [
    {
      label: 'Digital Goods',
      backgroundColor: 'rgba(60,141,188,0.9)',
      borderColor: 'rgba(60,141,188,0.8)',
      pointRadius: false,
      pointColor: '#3b8bba',
      pointStrokeColor: 'rgba(60,141,188,1)',
      pointHighlightFill: '#fff',
      pointHighlightStroke: 'rgba(60,141,188,1)',
      data:nightInfo()
    },
    {
      label: 'Electronics',
      backgroundColor: 'rgba(210, 214, 222, 1)',
      borderColor: 'rgba(210, 214, 222, 1)',
      pointRadius: false,
      pointColor: 'rgba(210, 214, 222, 1)',
      pointStrokeColor: '#c1c7d1',
      pointHighlightFill: '#fff',
      pointHighlightStroke: 'rgba(220,220,220,1)',
      data: dayInfo()
    }
  ],

}

function check_form(){

}
function nothing(){
    console("等待")
}

var forecastChartOptions = {
  maintainAspectRatio: false,
  responsive: true,
  legend: {
    display: false
  },
  scales: {
    xAxes: [{
      gridLines: {
        display: false
      }
    }],
    yAxes: [{
      gridLines: {
        display: false
      }
    }]
  }
}

// This will get the first returned node in the jQuery collection.
// eslint-disable-next-line no-unused-vars
var salesChart = new Chart(forecastChartCanvas, { // lgtm[js/unused-local-variable]
  type: 'line',
  data: weatherChartData,
  options: forecastChartOptions
})

$('#user-name').html(localStorage.getItem('uname'));

document.getElementById("createId").value=localStorage.getItem("uid")
userName=localStorage.getItem("uname");


$("#form_data").submit(function(){
  var information = $("#information").val();
  var endTime = $("#endTime").val();
  var createId = $("#createId").val();
  $.ajax({
    type: "POST",
    data: {information: information ,endTime: endTime ,createId: createId},
    contentType : "application/x-www-form-urlencoded; charset=utf-8",
    url: "http://localhost:80/saveInfo" ,
    dataType: "JSON",
    success: function () {
          // alert(data)
          setTimeout('nothing()',1000);
          // return window.location.href= data;
        },
    error: function () {
      setTimeout('nothing()',2000);
      console.log("respon error");
    }
  })
})

  /* $.post("/saveInfo",{information: information ,endTime: endTime ,createId: createId},function (data) {
        alert(1)
        // if(data.success){
        //     alert(data.message);
        //    return window.location.href= data.message;
        // }
        // else {
        //     $("#loginInfor").css({ "display": "block", "opacity": "1" });
        //     $("#loginInfor").animate({ opacity: 0 }, 2000);
        //     $("#loginInfor").html(data.message);
        //     console.log("respon success, but the password is worry!");
        // }

    },'json');*/

$.get("/getToken",{
  "token":localStorage.getItem("token")
},function(date){

})

//-------------------------------------------------------------------------
// var ROOT_PATH = 'https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples';
//
// var chartDom = document.getElementById('gl1');
// var myChart = echarts.init(chartDom);
// var option;
//
// option = {
//   backgroundColor: '#000',
//   globe: {
//     baseTexture: ROOT_PATH + '/data-gl/asset/earth.jpg',
//     heightTexture: ROOT_PATH + '/data-gl/asset/bathymetry_bw_composite_4k.jpg',
//
//     displacementScale: 0.1,
//
//     shading: 'lambert',
//
//     environment: ROOT_PATH + '/data-gl/asset/starfield.jpg',
//
//     light: {
//       ambient: {
//         intensity: 0.1
//       },
//       main: {
//         intensity: 1.5
//       }
//     },
//
//     layers: [{
//       type: 'blend',
//       blendTo: 'emission',
//       texture: ROOT_PATH + '/data-gl/asset/night.jpg'
//     }, {
//       type: 'overlay',
//       texture: ROOT_PATH + '/data-gl/asset/clouds.png',
//       shading: 'lambert',
//       distance: 5
//     }]
//   },
//   series: []
// }
//
// option && myChart.setOption(option);
//
// //------------------------------------------
//
// var chartDom1 = document.getElementById('gl2');
// var myChart1 = echarts.init(chartDom1);
// var option1;
//
//
// option1 = {
//   backgroundColor: '#000',
//   globe: {
//     baseTexture: ROOT_PATH + "/data-gl/asset/world.topo.bathy.200401.jpg",
//     heightTexture: ROOT_PATH + "/data-gl/asset/world.topo.bathy.200401.jpg",
//     displacementScale: 0.04,
//     shading: 'realistic',
//     environment: ROOT_PATH + '/data-gl/asset/starfield.jpg',
//     realisticMaterial: {
//       roughness: 0.9
//     },
//     postEffect: {
//       enable: true
//     },
//     light: {
//       main: {
//         intensity: 5,
//         shadow: true
//       },
//       ambientCubemap: {
//         texture: ROOT_PATH + '/data-gl/asset/pisa.hdr',
//         diffuseIntensity: 0.2
//       }
//     }
//   }
// };
//
//
// option1 && myChart1.setOption(option1);
// import * as echarts from '/dist/js/echarts.min.js';
// var echarts=new import('/dist/js/echarts.min.js');
// var echarts = require('/dist/js/echarts.min.js');
var chartDom = document.getElementById('gl1');
var app = {};

var myChart = echarts.init(chartDom);
var option;

var data = [{
  name: '植物系',
  itemStyle: {
    color: '#da0d68'
  },
  children: [{
    name: '红茶',
    value: 1,
    itemStyle: {
      color: '#975e6d'
    }
  }, {
    name: '花茶',
    itemStyle: {
      color: '#e0719c'
    },
    children: [{
      name: '洋甘菊',
      value: 1,
      itemStyle: {
        color: '#f99e1c'
      }
    }, {
      name: '玫瑰',
      value: 1,
      itemStyle: {
        color: '#ef5a78'
      }
    }, {
      name: '茉莉花',
      value: 1,
      itemStyle: {
        color: '#f7f1bd'
      }
    }]
  }]
}, {
  name: '浆果',
  itemStyle: {
    color: '#da1d23'
  },
  children: [{
    name: '浆果',
    itemStyle: {
      color: '#dd4c51'
    },
    children: [{
      name: '黑莓',
      value: 1,
      itemStyle: {
        color: '#3e0317'
      }
    }, {
      name: '覆盆子',
      value: 1,
      itemStyle: {
        color: '#e62969'
      }
    }, {
      name: '蓝莓',
      value: 1,
      itemStyle: {
        color: '#6569b0'
      }
    }, {
      name: '草莓',
      value: 1,
      itemStyle: {
        color: '#ef2d36'
      }
    }]
  }, {
    name: '干果',
    itemStyle: {
      color: '#c94a44'
    },
    children: [{
      name: '葡萄干',
      value: 1,
      itemStyle: {
        color: '#b53b54'
      }
    }, {
      name: '西梅',
      value: 1,
      itemStyle: {
        color: '#a5446f'
      }
    }]
  }, {
    name: '其它果类',
    itemStyle: {
      color: '#dd4c51'
    },
    children: [{
      name: '椰汁类',
      value: 1,
      itemStyle: {
        color: '#f2684b'
      }
    }, {
      name: '樱桃',
      value: 1,
      itemStyle: {
        color: '#e73451'
      }
    }, {
      name: '石榴',
      value: 1,
      itemStyle: {
        color: '#e65656'
      }
    }, {
      name: '菠萝',
      value: 1,
      itemStyle: {
        color: '#f89a1c'
      }
    }, {
      name: '葡萄',
      value: 1,
      itemStyle: {
        color: '#aeb92c'
      }
    }, {
      name: '苹果',
      value: 1,
      itemStyle: {
        color: '#4eb849'
      }
    }, {
      name: '桃子',
      value: 1,
      itemStyle: {
        color: '#f68a5c'
      }
    }, {
      name: '梨子',
      value: 1,
      itemStyle: {
        color: '#baa635'
      }
    }]
  }, {
    name: '柑橘类',
    itemStyle: {
      color: '#f7a128'
    },
    children: [{
      name: '葡萄柚',
      value: 1,
      itemStyle: {
        color: '#f26355'
      }
    }, {
      name: '橙子',
      value: 1,
      itemStyle: {
        color: '#e2631e'
      }
    }, {
      name: '柠檬',
      value: 1,
      itemStyle: {
        color: '#fde404'
      }
    }, {
      name: '酸橙',
      value: 1,
      itemStyle: {
        color: '#7eb138'
      }
    }]
  }]
}, {
  name: '酸奶/\n发酵类',
  itemStyle: {
    color: '#ebb40f'
  },
  children: [{
    name: '酸臭味',
    itemStyle: {
      color: '#e1c315'
    },
    children: [{
      name: '酸性芳烃',
      value: 1,
      itemStyle: {
        color: '#9ea718'
      }
    }, {
      name: '醋酸',
      value: 1,
      itemStyle: {
        color: '#94a76f'
      }
    }, {
      name: '丁酸',
      value: 1,
      itemStyle: {
        color: '#d0b24f'
      }
    }, {
      name: '异戊酸',
      value: 1,
      itemStyle: {
        color: '#8eb646'
      }
    }, {
      name: '柠檬酸',
      value: 1,
      itemStyle: {
        color: '#faef07'
      }
    }, {
      name: '苹果酸',
      value: 1,
      itemStyle: {
        color: '#c1ba07'
      }
    }]
  }, {
    name: '酒精/\n弗雷门特',
    itemStyle: {
      color: '#b09733'
    },
    children: [{
      name: '葡萄酒',
      value: 1,
      itemStyle: {
        color: '#8f1c53'
      }
    }, {
      name: '威士忌',
      value: 1,
      itemStyle: {
        color: '#b34039'
      }
    }, {
      name: '弗雷门特',
      value: 1,
      itemStyle: {
        color: '#ba9232'
      }
    }, {
      name: '过熟酒',
      value: 1,
      itemStyle: {
        color: '#8b6439'
      }
    }]
  }]
}, {
  name: '蔬菜/\n植物类',
  itemStyle: {
    color: '#187a2f'
  },
  children: [{
    name: '橄榄油',
    value: 1,
    itemStyle: {
      color: '#a2b029'
    }
  }, {
    name: '生食',
    value: 1,
    itemStyle: {
      color: '#718933'
    }
  }, {
    name: '蔬菜/\n植物类',
    itemStyle: {
      color: '#3aa255'
    },
    children: [{
      name: '未成熟的',
      value: 1,
      itemStyle: {
        color: '#a2bb2b'
      }
    }, {
      name: '豆荚',
      value: 1,
      itemStyle: {
        color: '#62aa3c'
      }
    }, {
      name: '新鲜的',
      value: 1,
      itemStyle: {
        color: '#03a653'
      }
    }, {
      name: '深绿色',
      value: 1,
      itemStyle: {
        color: '#038549'
      }
    }, {
      name: '植物',
      value: 1,
      itemStyle: {
        color: '#28b44b'
      }
    }, {
      name: '干草',
      value: 1,
      itemStyle: {
        color: '#a3a830'
      }
    }, {
      name: '药草',
      value: 1,
      itemStyle: {
        color: '#7ac141'
      }
    }]
  }, {
    name: '豆腥味',
    value: 1,
    itemStyle: {
      color: '#5e9a80'
    }
  }]
}, {
  name: '其它',
  itemStyle: {
    color: '#0aa3b5'
  },
  children: [{
    name: '纸味/霉味',
    itemStyle: {
      color: '#9db2b7'
    },
    children: [{
      name: '陈腐的',
      value: 1,
      itemStyle: {
        color: '#8b8c90'
      }
    }, {
      name: '硬纸',
      value: 1,
      itemStyle: {
        color: '#beb276'
      }
    }, {
      name: '纸质',
      value: 1,
      itemStyle: {
        color: '#fefef4'
      }
    }, {
      name: '木本的',
      value: 1,
      itemStyle: {
        color: '#744e03'
      }
    }, {
      name: '发霉/潮湿',
      value: 1,
      itemStyle: {
        color: '#a3a36f'
      }
    }, {
      name: '发霉/尘土',
      value: 1,
      itemStyle: {
        color: '#c9b583'
      }
    }, {
      name: '发霉/泥土味',
      value: 1,
      itemStyle: {
        color: '#978847'
      }
    }, {
      name: '动物',
      value: 1,
      itemStyle: {
        color: '#9d977f'
      }
    }, {
      name: '肉汤',
      value: 1,
      itemStyle: {
        color: '#cc7b6a'
      }
    }, {
      name: '酚醛树脂',
      value: 1,
      itemStyle: {
        color: '#db646a'
      }
    }]
  }, {
    name: '化学味',
    itemStyle: {
      color: '#76c0cb'
    },
    children: [{
      name: '苦涩',
      value: 1,
      itemStyle: {
        color: '#80a89d'
      }
    }, {
      name: '咸的',
      value: 1,
      itemStyle: {
        color: '#def2fd'
      }
    }, {
      name: '药味',
      value: 1,
      itemStyle: {
        color: '#7a9bae'
      }
    }, {
      name: '石油',
      value: 1,
      itemStyle: {
        color: '#039fb8'
      }
    }, {
      name: '臭鼬',
      value: 1,
      itemStyle: {
        color: '#5e777b'
      }
    }, {
      name: '橡胶',
      value: 1,
      itemStyle: {
        color: '#120c0c'
      }
    }]
  }]
}, {
  name: '烘烤的',
  itemStyle: {
    color: '#c94930'
  },
  children: [{
    name: '烟草丝',
    value: 1,
    itemStyle: {
      color: '#caa465'
    }
  }, {
    name: '烟草味',
    value: 1,
    itemStyle: {
      color: '#dfbd7e'
    }
  }, {
    name: '烧焦味',
    itemStyle: {
      color: '#be8663'
    },
    children: [{
      name: '辛辣',
      value: 1,
      itemStyle: {
        color: '#b9a449'
      }
    }, {
      name: '灰烬',
      value: 1,
      itemStyle: {
        color: '#899893'
      }
    }, {
      name: '烟熏味',
      value: 1,
      itemStyle: {
        color: '#a1743b'
      }
    }, {
      name: '烘烤',
      value: 1,
      itemStyle: {
        color: '#894810'
      }
    }]
  }, {
    name: '谷类',
    itemStyle: {
      color: '#ddaf61'
    },
    children: [{
      name: '谷物',
      value: 1,
      itemStyle: {
        color: '#b7906f'
      }
    }, {
      name: '麦芽',
      value: 1,
      itemStyle: {
        color: '#eb9d5f'
      }
    }]
  }]
}, {
  name: '香料',
  itemStyle: {
    color: '#ad213e'
  },
  children: [{
    name: '刺激味',
    value: 1,
    itemStyle: {
      color: '#794752'
    }
  }, {
    name: '胡椒',
    value: 1,
    itemStyle: {
      color: '#cc3d41'
    }
  }, {
    name: '香料 ',
    itemStyle: {
      color: '#b14d57'
    },
    children: [{
      name: '茴香',
      value: 1,
      itemStyle: {
        color: '#c78936'
      }
    }, {
      name: '肉豆蔻',
      value: 1,
      itemStyle: {
        color: '#8c292c'
      }
    }, {
      name: '肉桂',
      value: 1,
      itemStyle: {
        color: '#e5762e'
      }
    }, {
      name: '丁香',
      value: 1,
      itemStyle: {
        color: '#a16c5a'
      }
    }]
  }]
}, {
  name: '坚果/\n可可',
  itemStyle: {
    color: '#a87b64'
  },
  children: [{
    name: '坚果',
    itemStyle: {
      color: '#c78869'
    },
    children: [ {
      name: '花生',
      value: 1,
      itemStyle: {
        color: '#d4ad12'
      }
    }, {
      name: '榛子',
      value: 1,
      itemStyle: {
        color: '#9d5433'
      }
    }, {
      name: '杏仁',
      value: 1,
      itemStyle: {
        color: '#c89f83'
      }
    }]
  }, {
    name: '可可',
    itemStyle: {
      color: '#bb764c'
    },
    children: [{
      name: '巧克力',
      value: 1,
      itemStyle: {
        color: '#692a19'
      }
    }, {
      name: '黑巧克力',
      value: 1,
      itemStyle: {
        color: '#470604'
      }
    }]
  }]
}, {
  name: '糖果',
  itemStyle: {
    color: '#e65832'
  },
  children: [{
    name: '红糖',
    itemStyle: {
      color: '#d45a59'
    },
    children: [{
      name: '糖蜜',
      value: 1,
      itemStyle: {
        color: '#310d0f'
      }
    }, {
      name: '枫糖浆',
      value: 1,
      itemStyle: {
        color: '#ae341f'
      }
    }, {
      name: '焦糖',
      value: 1,
      itemStyle: {
        color: '#d78823'
      }
    }, {
      name: '蜂蜜',
      value: 1,
      itemStyle: {
        color: '#da5c1f'
      }
    }]
  }, {
    name: '香草',
    value: 1,
    itemStyle: {
      color: '#f89a80'
    }
  }, {
    name: '香兰素',
    value: 1,
    itemStyle: {
      color: '#f37674'
    }
  }, {
    name: '糖果 ',
    value: 1,
    itemStyle: {
      color: '#e75b68'
    }
  }, {
    name: '芳香',
    value: 1,
    itemStyle: {
      color: '#d0545f'
    }
  }]
}];

option = {
  title: {
    textStyle: {
      fontSize: 14,
      align: 'center'
    },
    subtextStyle: {
      align: 'center'
    },
  },
  series: {
    type: 'sunburst',

    data: data,
    radius: [0, '95%'],
    sort: null,

    emphasis: {
      focus: 'ancestor'
    },

    levels: [{}, {
      r0: '15%',
      r: '35%',
      itemStyle: {
        borderWidth: 2
      },
      label: {
        rotate: 'tangential'
      }
    }, {
      r0: '35%',
      r: '70%',
      label: {
        align: 'right'
      }
    }, {
      r0: '70%',
      r: '72%',
      label: {
        position: 'outside',
        padding: 3,
        silent: false
      },
      itemStyle: {
        borderWidth: 3
      }
    }]
  }
};

option && myChart.setOption(option);