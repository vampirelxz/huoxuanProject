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
    success: function (data) {
          // alert(data)

          return window.location.href= data;
        },
    error: function () {
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
var ROOT_PATH = 'https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples';

var chartDom = document.getElementById('gl1');
var myChart = echarts.init(chartDom);
var option;

option = {
  backgroundColor: '#000',
  globe: {
    baseTexture: ROOT_PATH + '/data-gl/asset/earth.jpg',
    heightTexture: ROOT_PATH + '/data-gl/asset/bathymetry_bw_composite_4k.jpg',

    displacementScale: 0.1,

    shading: 'lambert',

    environment: ROOT_PATH + '/data-gl/asset/starfield.jpg',

    light: {
      ambient: {
        intensity: 0.1
      },
      main: {
        intensity: 1.5
      }
    },

    layers: [{
      type: 'blend',
      blendTo: 'emission',
      texture: ROOT_PATH + '/data-gl/asset/night.jpg'
    }, {
      type: 'overlay',
      texture: ROOT_PATH + '/data-gl/asset/clouds.png',
      shading: 'lambert',
      distance: 5
    }]
  },
  series: []
}

option && myChart.setOption(option);

//------------------------------------------

var chartDom1 = document.getElementById('gl2');
var myChart1 = echarts.init(chartDom1);
var option1;


option1 = {
  backgroundColor: '#000',
  globe: {
    baseTexture: ROOT_PATH + "/data-gl/asset/world.topo.bathy.200401.jpg",
    heightTexture: ROOT_PATH + "/data-gl/asset/world.topo.bathy.200401.jpg",
    displacementScale: 0.04,
    shading: 'realistic',
    environment: ROOT_PATH + '/data-gl/asset/starfield.jpg',
    realisticMaterial: {
      roughness: 0.9
    },
    postEffect: {
      enable: true
    },
    light: {
      main: {
        intensity: 5,
        shadow: true
      },
      ambientCubemap: {
        texture: ROOT_PATH + '/data-gl/asset/pisa.hdr',
        diffuseIntensity: 0.2
      }
    }
  }
};


option1 && myChart1.setOption(option1);