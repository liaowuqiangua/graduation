var socket;
var tradeWarn = [];
var timeScaleWarn = [];
var head = '<blockquote class="layui-elem-quote">';
var tail = '</blockquote>';
var currentItem = 'TradeWarning';
socket = new WebSocket("ws://localhost:8888/te");
socket.onmessage = function(event){
    var jsonStr = $.parseJSON(event.data);
    if(jsonStr.type === 'Warn'){
        msg = $.parseJSON(jsonStr.json);
        // if(msg.messageTarget === 'TimeScaleWarning'){
        //     let div=$(head+'[Level] '+msg.noticeLevel+msg.message+tail);
        //     $("#msg").prepend(div);
        // }
        // else if(msg.messageTarget === 'TradeWarning'){
        //     let div=$(head+'[Level] '+msg.noticeLevel+msg.message+tail);
        //     $("#msg").prepend(div);
        // }
        lodeData(msg);
        if(msg.messageTarget === currentItem){
            var parentdiv=$(head+'[Level] '+msg.noticeLevel+msg.message+tail);
            $("#msg").prepend(parentdiv);
        }
    }
}
// socket.onmessage = function(event){
//     var jsonStr = $.parseJSON(event.data);
//     if(jsonStr.type === 'Log'){
//         msg = $.parseJSON(jsonStr.json);
//         lodeData(msg);
//         if(msg.fields.app_id === currentItem){
//             var parentdiv=$(head+msg.message+tail);
//             $("#msg").prepend(parentdiv);
//         }
//     }
// }

function changeItem(item){
    init();
    if(item === 'TimeScaleWarning' && item !== currentItem){
        currentItem = item;
        showMsg(timeScaleWarn)
    }
    else if(item === 'TradeWarning' && item !== currentItem){
        currentItem = item;
        showMsg(tradeWarn)
    }
}

function showMsg(arr){
    if(arr.length>100){
        for(let i = arr.length - 100;i<arr.length;i++){
            let parentdiv=$(head+'[Level] '+arr[i].noticeLevel+arr[i].message+tail);
            $("#msg").prepend(parentdiv);
        }
        for(let i = 0;i<arr.length-100;i++){
            arr.shift();
        }
    }
    else{
        for(let i = 0;i<arr.length;i++){
            let parentdiv=$(head+'[Level] '+arr[i].noticeLevel+arr[i].message+tail);
            $("#msg").prepend(parentdiv);
        }
    }
}

function init(){
    $('#msg').html("");
}

function lodeData(msg){
    if(msg.messageTarget === 'TimeScaleWarning'){
        timeScaleWarn.push(msg);    
    }
    else if(msg.messageTarget === 'TradeWarning'){
        tradeWarn.push(msg);    
    }
}

socket.onopen = function(event){
    console.log("conneted");
}
socket.onclose = function(event){
    console.log("close");
}