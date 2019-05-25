var socket;
socket = new WebSocket("ws://localhost:8888/te");


var data;
var instrumentID='cu1910';
var chart;

var chartType='hcharts';

var category = [];
var barData1 = [];
var barData2 = [];


Highcharts.setOptions({
	global: {
		useUTC: false
	}
});
function activeLastPointToolip(chart) {
	var points = chart.series[0].points;
	chart.tooltip.refresh(points[points.length -1]);
}

var hchartsOption = {
	title: {
		text: '实时行情'
	},
	xAxis: {
		type: 'datetime',
		tickPixelInterval: 150
	},
	yAxis: {
		title: {
			text: null
		}
    },
    plotOptions: {
		line: {
			dataLabels: {
				enabled: true          
			},
			enableMouseTracking: true
		}
	},
	tooltip: {
		formatter: function () {
			return '<b>' + this.series.name + '</b><br/>' +
				Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
				Highcharts.numberFormat(this.y, 2);
		}
    },
    credits: {
        enabled: false
    },
	legend: {
		enabled: false
	},
	series: [{
		name: '实时行情',
		data: []
	}]
}; 

chart = Highcharts.chart('main',hchartsOption);

socket.onmessage = function(event){
    var jsonStr = $.parseJSON(event.data);
    if(jsonStr.type === 'MarketData'&&chartType === 'hcharts'){
        msg = $.parseJSON(jsonStr.json);
        if(msg.instrumentID === instrumentID){
            chart.series[0].addPoint([toInt(msg.updateTime),to2(msg.lastPrice)],true,true);
        }
    }
    else if(jsonStr.type === 'InitQuotes'){
		checkCharts('hcharts')
		msg = $.parseJSON(jsonStr.json);
		var lineData = msg.map(function (item){return [toInt(item.updateTime),to2(item.lastPrice)]});
		chart.update({series :[{data :lineData.reverse()}]},true);
	}
	else if(jsonStr.type === 'InitTrade'){
		checkCharts('echarts');
		echartsInit();
		msg = $.parseJSON(jsonStr.json);
		let sorted = groupBy(msg, function(item){
			return [item.tradeDate];
		});
		if(sorted.length!==0){
			title = sorted[0].instrumentID; 
		}
		category=sorted.map(function (item){
			return [item[0].tradeDate.substr(0,4),item[0].tradeDate.substr(4,2),item[0].tradeDate.substr(6,2)].join('-');
		});
		sorted.forEach(function (item){
			let buyCount = 0;
			let sellCount = 0;
			item.forEach(function (inner){
				if(inner.direction === '0'){
					buyCount++;
				}
				else{
					sellCount++;
				}
			});
			barData1.push(buyCount);
			barData2.push(sellCount);
		});
		chart.setOption({
			xAxis :{
				data :category
			},
			series: [{
				data: barData1
			},{
				data: barData2.map(function (item){
					return item*-1;
				})
			}]
		});
    }
    else if(jsonStr.type === 'Scatter'){
        checkCharts('scatter');
        msg = $.parseJSON(jsonStr.json);
        barData1=msg.map(function (item){
            return [item.tradeVolume,item.tradePrice];
        });
        chart.update({series :[{data :barData1}]},true);
    }
}
function checkCharts(newType){
	if(chartType !== newType){
        clean();
		redrawFunc(newType);
		chartType=newType;
	}
}
function echartsInit(){
	barData1 = [];
	barData2 = [];
	category = [];
}


function redrawFunc(type){
	if(type === 'hcharts'){
		chart = Highcharts.chart('main',hchartsOption);
	}
	else if(type === 'echarts'){
		chart = echarts.init(document.getElementById('main'));
        chart.setOption(echartsOption);
        window.onresize = function(){
            chart.resize();
        }
    }
    else if(type === 'scatter'){
        chart = Highcharts.chart('main',scatterOption);
	}
}

function clean(){
    if(chartType === 'hcharts'){
        chart.destroy();
    }
    else if(chartType === 'echarts' ){
        chart.dispose();
    }
    else if(chartType === 'scatter' ){
        chart.destroy();
    }
}

function to2(num){
    return Math.floor(num * 100) / 100;
}
function toInt(longTypeDate){  
    return parseInt(longTypeDate);
} 

function changeItem(type,json){
	let map = {};
	map.type=type;
	map.json=json;
	socket.send(JSON.stringify(map).toString());
}

function groupBy( array , f ) {
	let groups = {};
	array = Array.from(array)
    array.forEach( function( o ) {
        let group = JSON.stringify( f(o) );
        groups[group] = groups[group] || [];
        groups[group].push( o );
    });
    return Object.keys(groups).map( function( group ) {
        return groups[group];
    });
}


var echartsOption = {
	backgroundColor: '#161139',
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow',
            label: {
                show: true,
                backgroundColor: '#333'
            }
        }
    },
    legend: {
        data: ['多头','空头'],
        textStyle: {
            color: '#ccc'
        }
    },
    xAxis: {
         type: 'category',
        data: category,
        axisTick: {
            alignWithLabel: true
        },
       
        splitLine: {
                show: false,
               
            },
        axisLabel:{
                //fontWeight:10,
                //interval:2,
                fontsize:2,
                align:'center',
                 color:'rgba(255,255,255,0.3)'
            }
    },
     yAxis: [{
        type: 'value',
        splitLine: {
                show: true,
                lineStyle:{
                    color:'rgba(255,255,255,0.2)'
                }
            },
            axisLine:{
                show:false
            },
            axisLabel:{
               
                fontWeight:10,
                fontsize:5,
               color:'rgba(255,255,255,0.3)'
            }
            
    }],
    series: [{
        name: '多头',
        type: 'bar',
        stack: '总量',
        barWidth: 10,
        itemStyle: {
            normal: {
                barBorderRadius: 50,
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#BC34BC'},
                        {offset: 1, color: '#7F3594'}
                    ]
                )
            }
        },
        data: barData1
    },{
        name: '空头',
        type: 'bar',
        stack: '总量',
        barWidth: 10,
        itemStyle: {
            normal: {
                barBorderRadius: 50,
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#4740C8'},
                        {offset: 1, color: '#EF71FF'}
                    ]
                )
            }
        },
        data: barData2.map(function (item){
			return item*-1;
		})
    }]
};

var scatterOption = {
	chart: {
		type: 'scatter',
		zoomType: 'xy'
	},
	title: {
		text: ''
    },
    credits: {
        enabled: false
    },
	xAxis: {
		title: {
			enabled: true,
			text: '成交数量'
		},
		startOnTick: true,
		endOnTick: true,
		showLastLabel: true
	},
	yAxis: {
		title: {
			text: '成交金额'
		}
	},
	legend: {
		layout: 'vertical',
		align: 'left',
		verticalAlign: 'top',
		x: 100,
		y: 70,
		floating: true,
		backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
		borderWidth: 1
	},
	plotOptions: {
		scatter: {
			marker: {
				radius: 5,
				states: {
					hover: {
						enabled: true,
						lineColor: 'rgb(100,100,100)'
					}
				}
			},
			states: {
				hover: {
					marker: {
						enabled: false
					}
				}
			},
			tooltip: {
				headerFormat: '<b>{series.name}</b><br>',
				pointFormat: '{point.x} 手, {point.y} 元'
			}
		}
	},
	series: [{
		name: 'trade',
		color: 'rgb(0,255,255)',
		data: barData1
	}]
};