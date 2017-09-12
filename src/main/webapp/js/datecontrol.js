var getFormatDate = function(date) {
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

var setDateConfig = function(dateId,callBack,isNotInit,isFloatRight){
	var dateStart;
	if(true !=  isNotInit){
		$("#" + dateId).val(getFormatDate(new Date()));
	}
	dateStart = {
		elem : "#" + dateId,
		format : "YYYY-MM-DD",
		min : '2000-01-01', //设定最小日期为当前日期
		max : laydate.now(), //最大日期
		istime : false,
		isclear:false,
		istoday : false,
		isFloatRight:isFloatRight,
		start: laydate.now(),
		choose : function(datas) {
			if(undefined != callBack){
				callBack();
			}
		}
	};
	laydate(dateStart);
}

var setMonthConfig = function(dateId,callBack,isNotInit,isFloatRight){
	var dateStart;
	if(true !=  isNotInit){
		$("#" + dateId).val(getFormatDate(new Date()));
	}
	dateStart = {
		elem : "#" + dateId,
		format : "YYYY-MM",
		min : '2000-01-01', //设定最小日期为当前日期
		max : laydate.now(), //最大日期
		istime : false,
		isday : false,
		isclear:false,
		istoday : false,
		isFloatRight:isFloatRight,
		start: laydate.now(),
		choose : function(datas) {
			if(undefined != callBack){
				callBack();
			}
		}
	};
	laydate(dateStart);
}

var setDateRangeConfig = function(dataStartId,dataEndId,callBack,isNotInit,isFloatRight,minDate,isToday,maxDate){
	var dateStart;
	var dateEnd;
	if(true !=  isNotInit){
		$("#" + dataStartId).val(getFormatDate(new Date()));
	}
	dateStart = {
		elem : "#" + dataStartId,
		format : "YYYY-MM-DD",
		min : undefined != minDate ? minDate : '2000-01-01', //设定最小日期为当前日期
		max : undefined != maxDate ? maxDate : laydate.now(),//最大日期
		istime : false,
		isclear:false,
		istoday : undefined != isToday ? isToday : false,
		start: laydate.now(),
		choose : function(datas) {
			dateEnd.min = datas; //开始日选好后，重置结束日的最小日期
			dateEnd.start = datas //将结束日的初始值设定为开始日
			if(undefined != callBack){
				callBack();
			}
		}
	};
	laydate(dateStart);
	if(true != isNotInit){
		$("#" + dataEndId).val(getFormatDate(new Date()));
	}
	dateEnd = {
		elem : "#" + dataEndId,
		format : "YYYY-MM-DD",
		min : undefined != minDate ? minDate : '2000-01-01', //设定最小日期为当前日期
		max : undefined != maxDate ? maxDate : laydate.now(),//最大日期
		istime : false,
		isclear:false,
		istoday : undefined != isToday ? isToday : false,
		isFloatRight:isFloatRight,
		start: laydate.now(),
		choose : function(datas) {
			dateStart.max = datas; //结束日选好后，重置开始日的最大日期
			if(undefined != callBack){
				callBack();
			}
		}
	};
	laydate(dateEnd);
	return {dateStart:dateStart,dateEnd:dateEnd};
}


var setDateTypeChange = function(dataTypeId,dateStartId,dateEndId,dateStart,dateEnd,callBack){
	if($("#" + dataTypeId).val()==1){
	//日期类型是今日
		$("#" + dateStartId).val(getFormatDate(new Date()));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}else if($("#" + dataTypeId).val()==2){
	//日期类型是昨日
		$("#" + dateStartId).val(getFormatDate(new Date(new Date().getTime() - 1 * 24 * 3600 * 1000)));
		$("#" + dateEndId).val(getFormatDate(new Date(new Date().getTime() - 1 * 24 * 3600 * 1000)));
		if(undefined != callBack){
			callBack();
		}
	}else if($("#" + dataTypeId).val()==3){
	//日期类型是近7天	
		$("#" + dateStartId).val(getFormatDate(new Date(new Date().getTime() - 6 * 24 * 3600 * 1000)));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}else if($("#" + dataTypeId).val()==4){
	//日期类型是近30天	
		$("#" + dateStartId).val(getFormatDate(new Date(new Date().getTime() - 29 * 24 * 3600 * 1000)));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}else if($("#" + dataTypeId).val()==5){
	//日期类型是近90天	
		$("#" + dateStartId).val(getFormatDate(new Date(new Date().getTime() - 89 * 24 * 3600 * 1000)));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}
	if(undefined != dateStart && undefined != dateEnd){
		dateStart.max = $("#" + dateEndId).val();
		dateEnd.min = $("#" + dateStartId).val();
	}
}

//获取当前月天数
var getCurMonthDayCnt = function(){
	var now = new Date();
	var date = new Date(now.getYear(),now.getMonth() + 1,0);
	return date.getDate();
}


var setDateType2Change = function(dataTypeId,dateStartId,dateEndId,dateStart,dateEnd,callBack){
	if($("#" + dataTypeId).val()==1){
	//日期类型是近7日
		$("#" + dateStartId).val(getFormatDate(new Date(new Date().getTime() - 6 * 24 * 3600 * 1000)));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}else if($("#" + dataTypeId).val()==2){
	//日期类型是近14天	
		$("#" + dateStartId).val(getFormatDate(new Date(new Date().getTime() - 13 * 24 * 3600 * 1000)));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}else if($("#" + dataTypeId).val()==3){
	//日期类型是近30天	
		$("#" + dateStartId).val(getFormatDate(new Date(new Date().getTime() - 29 * 24 * 3600 * 1000)));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}else if($("#" + dataTypeId).val()==4){
	//日期类型是近90天	
		$("#" + dateStartId).val(getFormatDate(new Date(new Date().getTime() - 89 * 24 * 3600 * 1000)));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}
	dateStart.max = $("#" + dateEndId).val();
	dateEnd.min = $("#" + dateStartId).val();
}

var setDateType3Change = function(dataTypeId,dateStartId,dateEndId,dateStart,dateEnd,callBack){
	if($("#" + dataTypeId).val()==1){
	//日期类型是今天
		$("#" + dateStartId).val(getFormatDate(new Date()));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}else if($("#" + dataTypeId).val()==2){
	//日期类型是近一周	
		$("#" + dateStartId).val(getFormatDate(new Date(new Date().getTime() - 6 * 24 * 3600 * 1000)));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}else if($("#" + dataTypeId).val()==3){
	//日期类型是近一月	
		$("#" + dateStartId).val(getFormatDate(new Date(new Date().getTime() - (getCurMonthDayCnt()-1) * 24 * 3600 * 1000)));
		$("#" + dateEndId).val(getFormatDate(new Date()));
		if(undefined != callBack){
			callBack();
		}
	}
	dateStart.max = $("#" + dateEndId).val();
	dateEnd.min = $("#" + dateStartId).val();
}

var formatSeconds = function(value) {
	var theTime = parseInt(value);// 秒
	var theTime1 = 0;// 分
	var theTime2 = 0;// 小时
	if(theTime > 60) {
		theTime1 = parseInt(theTime/60);
		theTime = parseInt(theTime%60);
	if(theTime1 > 60) {
		theTime2 = parseInt(theTime1/60);
		theTime1 = parseInt(theTime1%60);
	}
	}
	var result = "" + theTime > 9 ? parseInt(theTime) : ("0" + parseInt(theTime)) + "";
	
	if(theTime1 > 9) {
		result = "" + parseInt(theTime1) + ":" + result;
	}else{
		result = "0" + parseInt(theTime1) + ":" + result;
	}
	
	if(theTime2 > 9) {
		result = "" + parseInt(theTime2) + ":" + result;
	}else{
		result = "0" + parseInt(theTime2) + ":" + result;
	}
	return result;
} 

var formatSeconds1 = function(value) {
	var theTime = parseInt(value);// 秒
	var theTime1 = 0;// 分
	var theTime2 = 0;// 小时
	if(theTime > 60) {
	theTime1 = parseInt(theTime/60);
	theTime = parseInt(theTime%60);
	if(theTime1 > 60) {
	theTime2 = parseInt(theTime1/60);
	theTime1 = parseInt(theTime1%60);
	}
	}
	var result = "" + parseInt(theTime)+"s";
	if(theTime1 > 0) {
	result = "" + parseInt(theTime1)+"m"+result;
	}
	if(theTime2 > 0) {
	result = "" + parseInt(theTime2)+"h"+result;
	}
	return result;
} 


//日期转换
var formatDate = function(e){
    if(e ==null || e == undefined || null == e.time || undefined == e.time){
        return ''
    }
    var date = new Date(e.time);
    var year = date.getFullYear();
    var month = date.getMonth();
    var day = date.getDate();
    var hours= date.getHours();
    var min = date.getMinutes();
    var second = date.getSeconds();
    return addZero(year) + '-' + addZero(month+1)
        +'-'+addZero(day) + " "+addZero(hours)+":"+addZero(min)
        +":"+addZero(second);
}

//月份转换
var formatMonth = function(e){
    if(e ==null || e == undefined || null == e.time || undefined == e.time){
        return ''
    }
    var date = new Date(e.time);
    var year = date.getFullYear();
    var month = date.getMonth();
    return addZero(year) + '-' + addZero(month+1);
}

//月份转换
var formatMonth1 = function(date){
    if(date ==null || date == undefined){
        return ''
    }
    var year = date.getFullYear();
    var month = date.getMonth();
    return addZero(year) + '-' + addZero(month+1);
}

//日期转换
var formatDay = function(e){
    if(e ==null || e == undefined || null == e.time || undefined == e.time){
        return ''
    }
    var date = new Date(e.time);
    var year = date.getFullYear();
    var month = date.getMonth();
    var day = date.getDate();
    return addZero(year) + '-' + addZero(month+1)
        +'-'+addZero(day);
}

//获取某年某月的最后一天,以YYYY-MM-DD格式返回
//blnNowPreviousDay：true时，表示如果月份是当前月，是否限制为当前时间前一天
var getLastDay = function(year, month,blnNowPreviousDay){
	if(year + "-" + month == formatMonth1(new Date())){
		if(blnNowPreviousDay){
			return getNowPreviousDay();
		}
	}
    var dt = new Date(year, month - 1, '01');    
    dt.setDate(1);    
    dt.setMonth(dt.getMonth() + 1);    
    cdt = new Date(dt.getTime()-1000*60*60*24);
    return year + "-" + month + "-" + cdt.getDate();   
} 

//获取当前时间的前一天
var getNowPreviousDay = function(){
	var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth();
    var day = date.getDate() - 1;
    return addZero(year) + '-' + addZero(month+1)
        +'-'+addZero(day);
}

//补0操作
var addZero = function(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}





