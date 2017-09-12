<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<%
	request.setAttribute("path", request.getContextPath());
%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<style>
html,body{
    height: 100%;
    width: 100%;
    minWidth:300px;
    minHeight:200px;
    backgound:white;
}
</style>
<meta charset="UTF-8">
<title>后台统计</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/admin-trend.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
<script src="<%=request.getContextPath()%>/js/laydate/laydate.js"></script>
<link href="<%=request.getContextPath()%>/js/myPagination/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/myPagination/js/msgbox/msgbox.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/myPagination/js/myPagination/page.css" />
<link rel="stylesheet" type="text/css" href="${path}/js/monthpicker/skin/jquery.monthpicker.css" />

<script src="<%=request.getContextPath()%>/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script src="<%=request.getContextPath()%>/js/tool.js"></script>
<script src="${path}/js/datecontrol.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/jquery-ui.js"></script>
<script src="${path}/js/monthpicker/jquery.monthpicker.js"></script>


</head>
<body>
	<div class="admin-content partner-container" style="height:100%;">
				<input type="hidden" id="partnerUserId" value='${partnerUserId}'/>
				<input type="hidden" id="monthId" value='${monthId}'/>
				<div class="titleDiv" style="height:25px;">
				    <div style="float:left;">
				    	<table style="margin-left:5px;">
				    		<tr>
				    			<td class="tb1Td">起止日期：</td>
					    		<td>
					    			<input placeholder="请选择日期" class="laydate-icon" style="height:25px;width:100px;" id="dateStart" readonly>
					    		</td>
					    		<td>&nbsp;-&nbsp;</td>
					    		<td>
					    			 <input class="laydate-icon" id="dateEnd" style="height:25px;width:100px;" placeholder="请选择日期" readonly>
					    		</td>
					    		<td>游戏类型：</td>
                                <td>
                                    <select id="gameType"  class="select" style="min-width:100px;width:auto;">
                                    </select>
						    	</td>
						    	<td>对账类型：</td>
                                <td>
                                    <select id="rackBackModel"  class="select" style="width:100px;">
                                    </select>
						    	</td>
				    			<td  style="line-height:1;">
									<button type="button" id="search" class="btn btn-primary btn-sm" onclick="search();"
										<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>查询
									</button>
								<td/>
								<td  style="line-height:1;">
									<button type="button" id="reset" class="btn btn-primary btn-sm" onclick="reset();"
										<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>重置
									</button>
								<td/>
								<td  style="line-height:1;">
									<button type="button" id="reset" class="btn btn-primary btn-sm" onclick="exportToExcel();"
										<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>导出
									</button>
								<td/>
				    		</tr>
				    	</table>
					</div>
				</div>
				<div class="whiteDiv tab-content">
						<ul id="sortable" class="ui-widget-content sortable">
							<li class="ui-state-default">
								<div style="padding-bottom:35px;" class="ui-widget-content resize">
									<div class="sortHandle div-sum">
										<span>等级：
											<span id="commissionLevel">
											</span>
										</span>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;返佣比例：
											<span id="commissionPercent">
											</span>
										</span>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;活动金额：
											<span id="activityAmount">
											</span>
										</span>
									</div>
									<div class="tablePanel">
										<table class="tableList1">
											<thead id="head">
											</thead>
											<tbody id="data">
											</tbody>
										</table>
									</div>
									<div>
										<table class="tablePage">
											<tr>
												<td><div class="divPage">
													<span class="spanPageSize">每页个数：
														<select id="pageSize" onchange="search();">
															<option value="10">10</option>
															<option value="20">20</option>
															<option value="50">50</option>
															<option value="100">100</option>
															<option value="200">200</option>
														</select>
													</span>
												</td>
												<td><span class="spanPageSize">总记录数：</span><span id="totalCount" class="spanPageSize"></span></td>
												<td><span class="spanPageSize">总页数：</span><span id="totalPage" class="spanPageSize"></span></td>
												<td class="tablePageTd"><div id="page"></div></td>
											</tr>
										</table>
									</div>
								</div>
							</li>
						</ul>
			</div>
	</div>
	<script type="text/javascript">
		$(function(){
			setDateRangeConfig("dateStart","dateEnd",null,true,false,"${monthId}" + "-01",false,getLastDay("${monthId}".split("-")[0],"${monthId}".split("-")[1],false));
			//初始化时间为今天
			$("#dateStart").val("${monthId}" + "-01");
			$("#dateEnd").val(getLastDay("${monthId}".split("-")[0],"${monthId}".split("-")[1],false));
			
			getGameTypes();
			bindEnterEventOfAllEle();
			$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
		});
		
		var bindEnterEventOfAllEle = function(){
			bindEnterEvent("realName",search);
		}
		
		var getGameTypes = function(){
			var optionstring = "";
	    	$.post("${path}/partner/ptnmbbladetail/getGameTypes.do",{
	    		monthId:$("#monthId").val(),
	    		partnerUserId:$("#partnerUserId").val()
			},function(data){
				var json = JSON.parse(data);
				if(null != json && undefined != json && ""!=json){
					for(var i=0;i < json.length;i++){
                        optionstring += "<option value=\"" + json[i].id + "\"title=" + json[i].platformTypeName + "—" + json[i].gameTypeName + "\">" + json[i].platformTypeName + "—" + json[i].gameTypeName + "</option>";  
					}
				}
				$("#gameType").html(optionstring);
				getRackBackModels();
			});
		}
		
		var getRackBackModels = function(){
			var optionstring = "";
	    	$.post("${path}/partner/ptnmbbladetail/getPartnerMemberBlaDetails.do",{
	    		monthId:$("#monthId").val(),
	    		partnerUserId:$("#partnerUserId").val(),
	    		gameTypeId:$("#gameType").val()
			},function(data){
				var json = JSON.parse(data);
				if(null != json && undefined != json && ""!=json){
					for(var i=0;i < json.length;i++){
                        optionstring += "<option value=\"" + json[i].rackBackModel + "\"title=" + json[i].rackBackModel + "\">" + convertRackBackModel(json[i].rackBackModel) + "</option>";  
					}
				}
				$("#rackBackModel").html(optionstring);
				getCommissionInfo();
				pageSize = $("#pageSize").val() * 1;
				showData(1,pageSize);
			});
		}
		
		var convertRackBackModel = function(value){
			if(value == 1){
				return "输赢对账";
			}else if(value == 2){
				return "投注对账";
			}else if(value == 3){
				return "充值对账";
			}else if(value == 4){
				return "礼物对账";
			}else{
				return value;
			}
		}
		
		var getCommissionInfo = function(){
			$("#commissionLevel").html("");
			$("#commissionPercent").html("");
			$("#activityAmount").html("");
	    	$.post("${path}/partner/ptnmbbladetail/getCommissionInfo.do",{
	    		monthId:$("#monthId").val(),
	    		partnerUserId:$("#partnerUserId").val(),
	    		gameTypeId:$("#gameType").val(),
	    		rackBackModel:$("#rackBackModel").val()
			},function(data){
				var json = JSON.parse(data);
				$("#commissionLevel").html(json.commissionLevel);
				$("#commissionPercent").html(convertToPercentFormat(json.commissionPercent));
				$("#activityAmount").html(json.activityAmount.toFixed(2));
			});
		}
		
		var pageSize = 10;
   		
   		var addTableHead = function(arr){
   			$("#head").empty();
   			var eles = "<tr>";
   			for(var i = 0;i < arr.length;i++){
   				var ele = "<th>" + arr[i] + "</th>";
   				eles += ele;
   			}
   			eles += "<tr>";
   			$("#head").append(eles);
   		}
   		
   		var addTableValue = function(arr){
   			var eles = "<tr>";
   			for(var i = 0;i < arr.length;i++){
   				var ele = "<td>" + arr[i] + "</td>";
   				eles += ele;
   			}
   			eles += "<tr>";
   			$("#data").append(eles);
   		}
   		
   		var showTableHeads = function(){
   			var arr;
   			if(3 == $("#rackBackModel").val()){
   				//充值数据
   				arr = new Array("平台单号","充值方式","金额","充值时间","订单号","充值单号","渠道");
   			}else if(4 == $("#rackBackModel").val()){
   				//礼物数据
   				arr = new Array("单号","时间","主播名称","名称","数量","金额");
   			}else if(1 == $("#rackBackModel").val()){
   				//输赢数据
   				arr = new Array("时间","下注金额","下注订单号","货币类型","输赢");
   			}else if(2 == $("#rackBackModel").val()){
   				//投注数据
   				if($("#gameType").find("option:selected").text().indexOf("乐盈电竞") >= 0){
   					//乐盈电竞投注数据
   					arr = new Array("时间","下注金额","下注订单号","货币类型");
   				}else{
   					//撩妹德州投注数据
   					arr = new Array("时间","局号","房间号","游戏类型","有效投注","收益");
   				}
   			}
   			addTableHead(arr);
   		}
   		
   		var showTableValues = function(data){
   			$("#data").empty();
   			if(3 == $("#rackBackModel").val()){
   			//充值数据
   				for(var i = 0;i < data.length;i++){
   					var arr = new Array(data[i].orderNo,data[i].rechargeWayName,data[i].amount.toFixed(2),data[i].payTime,data[i].paymentOrderNo,data[i].tradeNo,data[i].channelId);
   					addTableValue(arr);
   				}
   			}else if(4 == $("#rackBackModel").val()){
   			//礼物数据
   				for(var i = 0;i < data.length;i++){
   					var arr = new Array(data[i].presentOrderNo,formatDate(data[i].presentTime),data[i].anchorName,data[i].presentName,data[i].presentCount,data[i].settleAmount.toFixed(2));
   					addTableValue(arr);
   				}
   			}else if(1 == $("#rackBackModel").val()){
   			//输赢数据
   				for(var i = 0;i < data.length;i++){
   					var arr = new Array(formatDate(data[i].settleTime),data[i].bettingAmount.toFixed(2),data[i].bettingOrderNo,data[i].currency,data[i].settleAmount.toFixed(2));
   					addTableValue(arr);
   				}
   			}else if(2 == $("#rackBackModel").val()){
   				//投注数据
   				if($("#gameType").find("option:selected").text().indexOf("乐盈电竞") >= 0){
   					//乐盈电竞投注数据
   					for(var i = 0;i < data.length;i++){
   	   					var arr = new Array(formatDate(data[i].settleTime),data[i].bettingAmount.toFixed(2),data[i].bettingOrderNo,data[i].currency);
   	   					addTableValue(arr);
   	   				}
   				}else{
   					//撩妹德州投注数据
   					for(var i = 0;i < data.length;i++){
   	   					var arr = new Array(formatDate(data[i].settleTime),data[i].bureauNo,data[i].roomNo,data[i].gameTypeName,(data[i].betAmount * 1).toFixed(2),data[i].incomeAmount.toFixed(2));
   	   					addTableValue(arr);
   	   				}
   				}
   			}
   		}
		
		//显示统计列表
		var showData = function(pageNumber,pageSize){
			showTableHeads();
			$("#data").empty();
			$.post("${path}/partner/ptnmbbladetail/list.do",{
				pageNumber:pageNumber,
				pageSize:pageSize,
				monthId:$("#monthId").val(),
	    		partnerUserId:$("#partnerUserId").val(),
	    		gameTypeId:$("#gameType").val(),
	    		rackBackModel:$("#rackBackModel").val(),
	    		dateStart:$("#dateStart").val(),
	    		dateEnd:$("#dateEnd").val(),
	    		isLydj:$("#gameType").find("option:selected").text().indexOf("乐盈电竞") >= 0 ? 1 : 0
			},function(data){
				var json = JSON.parse(data);
				if(null != json && undefined != json){
					$("#totalCount").html(json.total);
					$("#totalPage").html(json.pages);
					$("#page").myPagination({
				          currPage: pageNumber,
				          pageCount: json.pages,
				          ajax:{on:false,  
			                    onClick:function(page){
			                    	showData(page,pageSize);
			                    }  
			        		}
				        });
					var infoData = json.list;
					if(null == infoData || undefined == infoData || 0 >= infoData.length){
						$("#data").append("<tr><td colspan=\"10\">没有数据</td></tr>");
					}else{
						showTableValues(infoData);
					}
				}else{
					$("#totalCount").html(0);
					$("#totalPage").html(0);
					$("#data").append("<tr><td colspan=\"10\">没有数据</td></tr>");
				}
			});
		}
		

		//重置
		var reset = function(){
			//初始化时间为今天
			$("#dateStart").val("${monthId}" + "-01");
			$("#dateEnd").val(getLastDay("${monthId}".split("-")[0],"${monthId}".split("-")[1]));
			getGameTypes();
		}
		
		//查询显示
		var search = function(){
			getCommissionInfo();
			pageSize = $("#pageSize").val() * 1;
			showData(1,pageSize);
		}
		
		var exportToExcel = function(){
			var index = layer.load();
			$.ajax({
			    url: "${path}/partner/ptnmbbladetail/export.do",
			    type: "POST",
			    data: {
					monthId:$("#monthId").val(),
		    		partnerUserId:$("#partnerUserId").val(),
		    		gameTypeId:$("#gameType").val(),
		    		rackBackModel:$("#rackBackModel").val(),
		    		dateStart:$("#dateStart").val(),
		    		dateEnd:$("#dateEnd").val(),
		    		isLydj:$("#gameType").find("option:selected").text().indexOf("乐盈电竞") >= 0 ? 1 : 0
				},
			    success:function(response, status, request){
			    	layer.close(index);
			    	if(!response.canExport){
			    		layer.msg("当前查询条件数据量过大，请缩小查询条件再导出！", {icon: 5}); 
			    		return;
			    	}
			        window.location = "${path}/partner/ptnmbbladetail/download.do?uuid=" + response.uuid;
		    }});
		}
	</script>
</body>
</html>
