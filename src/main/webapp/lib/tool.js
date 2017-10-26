var numberCheck = function(value){
	if (isNaN(value))
	{
		layer.alert("只能输入数值", {
			icon : 5
		});
		value = null;
	}
	return value;
}

var numberInput = function(event){
	if(undefined != event.code && ("Backspace".toLowerCase() == event.code.toLowerCase()||"Tab".toLowerCase() == event.code.toLowerCase())){
		return true;
	}
	return (/[\d.]/.test(String.fromCharCode(event.charCode)));
}

var intInput = function(event){
	if(undefined != event.code && ("Backspace".toLowerCase() == event.code.toLowerCase()||"Tab".toLowerCase() == event.code.toLowerCase())){
		return true;
	}
	return (/[\d]/.test(String.fromCharCode(event.charCode)));
}

/**
 ** 加法函数，用来得到精确的加法结果
 ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
 ** 调用：accAdd(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
 **/
var accAdd = function(arg1, arg2) {
    var r1, r2, m, c;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2));
    if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", "")) * cm;
        } else {
            arg1 = Number(arg1.toString().replace(".", "")) * cm;
            arg2 = Number(arg2.toString().replace(".", ""));
        }
    } else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m;
}

var bindEnterEvent = function(eleId,callBack){
	$("#" + eleId).bind("keypress",function(event){
         if(event.keyCode == "13")
         {
        	 if(undefined != callBack){
        		 callBack();
        	 }
         }
     });
}

var chartResize = function(chartEle){
	if(undefined != chartEle){
		chartEle.resize();
	}
}

//转化为百分数显示，且保留两位小数
var convertToPercentFormat = function(value){
	return (value*100).toFixed(2) + "%";
}

var getLocalhostPath = function(){
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPath = curWwwPath.substring(0, pos);
    return localhostPath;
}

//除法函数
function accDiv(arg1, arg2) {
    var t1 = 0, t2 = 0, r1, r2;
    try {
            t1 = arg1.toString().split(".")[1].length;
          }
	catch (e) {
	}
     try {
                 t2 = arg2.toString().split(".")[1].length;
     }
    catch (e) {
      }
      if (t1 = t2 == 0) {
    	return 0;
	  }
     with (Math) {
      r1 = Number(arg1.toString().replace(".", ""));
      r2 = Number(arg2.toString().replace(".", ""));
      return (r1 / r2) * pow(10, t2 - t1);
      }
 }


///////排序相关Start
//重置排序样式
var resetOrder = function(clsName){


    //获取主机地址，如： http://localhost:8083
	var eles = $("." + clsName);
	for(var i = 0;i < eles.length;i++){
		$(eles[i]).attr("order",0);
		$(eles[i]).attr("src",getLocalhostPath() + "/img/arrow_disable.png");
	}
}

//获取排序信息
var getOrder = function(clsName){
	var eles = $("." + clsName);
	var orderField = "";
	var order = 0
	for(var i = 0;i < eles.length;i++){
		if("1" == $(eles[i]).attr("order") || "2" == $(eles[i]).attr("order")){
			order = $(eles[i]).attr("order");
			orderField = $(eles[i]).attr("name");
			break;
		}
	}
	return {order:order,orderField:orderField};
}
///////排序相关End

/**
 * 根据国家获取单位
 */
var getMoneyUnit = function(country){
	if(0 == country){
		//人民币
		return "RMB"
	}else if(1 == country){
		//美元
		return "USD"
	}else if(2 == country){
		//泰国
		return "THB"
	}else if(3 == country){
		//越南
		return "VND"
	}else if(4 == country){
		//韩国
		return "KRW"
	}else if(5 == country){
		//印尼
		return "IDR"
	}else if(7 == country){
		//新加坡
		return "SGD"
	}else if(8 == country){
		//马来西亚
		return "MYR"
	}else{
		"未知"
	}
}
