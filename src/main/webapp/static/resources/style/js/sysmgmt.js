/**
 * Created by LIUYONG on 14-2-4.
 */
/**
 * 显示表单密码域
 */
function displayPW(obj){
    obj.setAttribute('type','text');
}

/**
 * 隐藏密码域
 */
function hiddenPW(obj){
    obj.setAttribute('type','password');
}

/**
 * ajax调用
 */
var xmlhttp;
function ajaxCallBack(name, value, url, cfunc) {
    if(window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = cfunc;
    xmlhttp.open("post",url, true);
    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlhttp.send(name + '=' + value);
}

/**
 * 关闭浏览器或其窗口时触发，删除验证码图片
 */
/*
window.onbeforeunload = function(e) {
    ajaxCallBack('','','/admin/removeValidateCode',null);
    e = e || window.event;
    if(e) {
        e.returnValue = "";
    }
}*/
$(document).ready(function(){
    $('#showWeather').mouseover(function(){
        $('#weatherBox').show();
    });
    /*
     * 跨域不能操作？？？
     * */
    /*$('#weatherBox').mouseout(function(){
     if($('#city-weaset',document.frames('ifm').document).is(":hidden")){
     alert("hehe");
     $('#weatherBox').hide();
     }
     });*/
    $('#weatherBox').mouseout(function(){
        $('#weatherBox').hide();
    });
    $('.moduleli').click(function(){
        $('.moduleli').css('background-color','transparent');
        $(this).css('background-color','#1871dd');
    });
    $('.moduleli').hover(
        function() {
            $(this).addClass('moduleSelect');
        },
        function() {
            $(this).removeClass('moduleSelect');
        }
    );
});