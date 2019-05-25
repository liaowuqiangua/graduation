var socket;
var tomcat = [];
var flink = [];
var hadoop = [];
var currentItem = 'tomcat';
var head = '<li class="layui-timeline-item"><i class="layui-icon layui-timeline-axis"></i><div class="layui-timeline-content layui-text"><div class="layui-timeline-title">';
var tail = '</div></div></li>';
socket = new WebSocket("ws://localhost:8888/te");
socket.onmessage = function(event){
    var jsonStr = $.parseJSON(event.data);
    if(jsonStr.type === 'Log'){
        msg = $.parseJSON(jsonStr.json);
        lodeData(msg)
        if(msg.fields.app_id === currentItem){
            var parentdiv=$(head+msg.message+tail);
            $("#msg").prepend(parentdiv);
        }
    }
}

function changeItem(item){
    init();
    if(item === 'tomcat'){
        currentItem = item;
        showMsg(tomcat)
    }
    else if(item === 'flink'){
        currentItem = item;
        showMsg(flink)
    }
    else if(item === 'hadoop'){
        currentItem = item;
        showMsg(hadoop)
    }
}

function init(){
    $('#msg').html("");
}

function showMsg(arr){
    if(arr.length>100){
        for(let i = arr.length - 100;i<arr.length;i++){
            let parentdiv=$(head+arr[i].message+tail);
            $("#msg").prepend(parentdiv);
        }
        for(let i = 0;i<arr.length-100;i++){
            arr.shift();
        }
    }
    else{
        for(let i = 0;i<arr.length;i++){
            let parentdiv=$(head+arr[i].message+tail);
            $("#msg").prepend(parentdiv);
        }
    }
}


function lodeData(item){
    if(item.fields.app_id === 'tomcat'){
        tomcat.push(item);    
    }
    else if(item.fields.app_id === 'flink'){
        flink.push(item);    
    }
    if(item.fields.app_id === 'hadoop'){
        hadoop.push(item);    
    }
}
socket.onopen = function(event){
    console.log("conneted");
}
socket.onclose = function(event){
    console.log("close");
}