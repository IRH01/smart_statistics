function showTrendLineData(echarts,json,index){
	trendline = echarts.init(document
			.getElementById('trendline'));
	trendline.setOption({
		calculable :true,
		title : {
			x : '20',
			text : '',
			textStyle : {
				fontSize : '16',
				fontWeight : 'bold',
			}
		},
		tooltip : {
			trigger : 'axis'
		},
		color : [ '#2ec7c9', '#b6a2de', '#5ab1ef',
				'#ffb980', '#d87a80' ],
		legend : {
			show : true,
			orient : 'horizontal',
			data : [ {
				name : '新增玩家',
				textStyle : {
					fontSize : 12,
					color : '#666'
				},
				icon : 'roundRect'
			}, {
				name : '活跃玩家',
				textStyle : {
					fontSize : 12,
					color : '#666'
				},
				icon : 'roundRect'
			}]
		},
		grid : {
			borderWidth : 0,
			y : 50,
			y2 : 30,
			x : 50,
			x2 : 50
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : json.scales,
			splitLine : {
				show : true,
				lineStyle : {
					color : '#f5f5f5',
					width : 1,
					type : 'solid'
				}
			},
			axisTick : {
				show : true,
				lineStyle : {
					color : '#ccc',
					width : 1,
					type : 'solid'
				}
			},
			axisLine : {
				lineStyle : {
					color : '#ccc',
					width : 1,
					type : 'solid'
				}
			},
			axisLabel : {
				textStyle : {
					color : "#999"
				}
			}
		} ],
		yAxis : [ {
			type : 'value',
			axisLabel : {
	                formatter: getYAxisLabelFormart(index),
	                margin:5
	        },
			axisLine : {
				show : true
			},
			axisTick : {
				show : true
			},
			splitArea : {
				show : false
			},
			splitLine : {
				show : true,
				lineStyle : {
					color : '#f5f5f5',
					width : 1,
					type : 'solid'
				}
			}
		} ],

		series : [
				{
					name : '新增玩家',
					type : 'line',
					data : getRegData(json,index)
				},
				{
					name : '活跃玩家',
					type : 'line',
					data : getActData(json,index)
				}]
	});
}