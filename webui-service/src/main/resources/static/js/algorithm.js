function refreshToken(){
    $.get("/refreshToken",{
        "refreshToken":localStorage.getItem("refreshToken")
    },function(data){
        localStorage.setItem("token",data);
    })
}

function runContent() {
    var source=$("#myInput").val()
    var systemIn=$("#mySystemIn").text()
    var userId=localStorage.getItem("uid")

    if($("#nowQuestion").attr("val") != null){
        var algorithmId=$("#nowQuestion").attr("val")
    }else{
        algorithmId = null;
    }
    refreshToken()
    $.post("/run",{"source":source,"systemIn":systemIn,"userId":userId,"algorithmId":algorithmId,"token":localStorage.getItem("token")},
        function (date) {
        $("#showRunResult").html(date);
            $.get("/getAlgorithmUser",{"userId":localStorage.getItem("uid"),"algorithmId":algorithmId ,"token":localStorage.getItem("token")},function(date){
                $("#algorithmUserInfo").html(date);
                $("#timeExpend").text($("#myInput").attr("time-expend")),
                    $("#spaceExpend").text($("#myInput").attr("space-expend"))
            })
    })
}

var myInput = document.getElementById("myInput");
str = "    ";
if(myInput.addEventListener ) {
    myInput.addEventListener('keydown',this.keyHandler,false);
} else if(myInput.attachEvent ) {
    myInput.attachEvent('onkeydown',this.keyHandler); /* damn IE hack */
}
function keyHandler(e) {
    var TABKEY = 9;
    if(e.keyCode == TABKEY) {
        insertText(myInput,str);
        if(e.preventDefault) {
            e.preventDefault();
        }
    }
}
function insertText(obj,str) {
    if (document.selection) {
        var sel = document.selection.createRange();
        sel.text = str;
    } else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {
        var startPos = obj.selectionStart,
            endPos = obj.selectionEnd,
            cursorPos = startPos,
            tmpStr = obj.value;
        obj.value = tmpStr.substring(0, startPos) + str + tmpStr.substring(endPos, tmpStr.length);
        cursorPos += str.length;
        obj.selectionStart = obj.selectionEnd = cursorPos;
    } else {
        obj.value += str;
    }
}

$.get("/listAlgorithmInfo",{
},function(date){
    $("#questionList").html(date);
    refreshToken()
})

function loadHistory(obj) {
        var td =$(obj).find("td");
        var code = td.eq(1).text()
        var title = td.eq(2).text()
        $("#nowQuestion").text(title)
        $("#nowQuestion").attr("val",code)
        $.get("/getAlgorithmUser",{"userId":localStorage.getItem("uid"),"algorithmId":code,"token":localStorage.getItem("token")},function(date){
            $("#algorithmUserInfo").html(date);
            $("#timeExpend").text($("#myInput").attr("time-expend")),
            $("#spaceExpend").text($("#myInput").attr("space-expend"))
        })
}

function solution(obj) {
        var td =$(obj).parent().parent().find("td");
        var code = td.eq(1).text()
        $.get("/getAlgorithmInfo",{"id":code,"token":localStorage.getItem("token")},function(date){
            $("#solution").html(date);
            $("#out-questionList").css("display","none");
            $("#solution-more").css("display","block");
            refreshToken()
        })

}

function relist() {
    $("#out-questionList").css("display","block");
    $("#solution-more").css("display","none");
}
