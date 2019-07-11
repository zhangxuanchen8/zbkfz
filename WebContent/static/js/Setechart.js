//排序
var sortBy = function (filed, rev, primer) {
    rev = (rev) ? -1 : 1;
    return function (a, b) {
        a = a[filed];
        b = b[filed];
        if (typeof (primer) != 'undefined') {
            a = primer(a);
            b = primer(b);
        }
        if (a < b) { return rev * -1; }
        if (a > b) { return rev * 1; }
        return 1;
    }
};

function setChart(id,text,subtext,legenddata,seriesdata){
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById(id));
	// 指定图表的配置项和数据
    var option = {
    		//标题
    		title:{
    			text:text,//标题
    			subtext:subtext,//副标题
    			left:'center'
    		},
    		//图例
    		legend: {
    			itemWidth:40,
		        itemHeight:20,
		        orient: 'vertical',
		        left: 'left',
		        data: legenddata
		    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{b}</br>{c} ({d}%)"//{a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
    	    },
    	    series : [
    	        {
    	            type: 'pie',
    	            name: name,
    	            radius : '54%',
    	            center: ['50%', '50%'],
    	            data:seriesdata,
    	            itemStyle: {
    	                emphasis: {
    	                    shadowBlur: 10,
    	                    shadowOffsetX: 0,
    	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
    	                }
    	            }
    	        }
    	    ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
function setzxChart(id,text,subtext,legenddata,seriesdata){
	var myChart = echarts.init(document.getElementById(id));
	option = {
		    title: {
		        text: text
		    },
		    tooltip: {},
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '16%',
		        containLabel: true
		    },
		    legend: {
		        data:['人数']
		    },
		    xAxis: {
		        data: legenddata
		    },
		    yAxis: {},
		    series: [{
		        name: '人数',
		        type: 'bar',
		        data: seriesdata
		    }]
		};
	 myChart.setOption(option);
}