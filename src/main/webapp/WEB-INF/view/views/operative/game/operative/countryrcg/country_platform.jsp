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
    backgound:white;
}
</style>
<meta charset="UTF-8">
<title>后台统计</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/admin-trend.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
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
				<input type="hidden" id="country" value='${country}'/>
				<input type="hidden" id="dateStart" value='${dateStart}'/>
				<input type="hidden" id="dateEnd" value='${dateEnd}'/>
				<div class="titleDiv" style="height:25px;">
					<div style="display: inline;width:500px;text-align: center;"></div>
				    <div class="select-fr" style="padding-right: 30px;float:left;">
				    	<table style="margin-left:5px;">
				    		<tr>
                                <td class="tb1Td">
					    				<span class="span">平台：</span>
					    		</td>
				    			<td>
			    					<select id="platformTerminal"  class="select">
                                 		<option value="-999">请选择</option>
                                 		<c:forEach items="${platTerminalInfos}" var="platTerminalInfo">
                                 			 <option value="${platTerminalInfo.id}">${platTerminalInfo.name}</option>
                                 		</c:forEach>
                                    </select>
                                </td>
				    			<td  style="line-height:1;">
									<button type="button" id="search" class="btn btn-primary btn-sm" onclick="search();"
										<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>查询
									</button>
								<td/>
								<td  style="line-height:1;">
									<button type="button" id="search" class="btn btn-primary btn-sm" onclick="reset();"
										<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>重置
									</button>
								<td/>
				    		</tr>
				    	</table>
					</div>
				</div>
				<div class="whiteDiv tab-content">
					<div>
						<ul id="sortable" class="ui-widget-content sortable">
							<li class="ui-state-default">
								<div style="padding-bottom:35px;" class="ui-widget-content resize">
									<div class="sortHandle div-sum">
									</div>
									<div class="tablePanel">
										<table class="tableList1">
											<thead>
												<tr>
													<th>平台</th>
													<th>游戏名称</th>
													<th>充值总额<img class="order dailyDataOrder" src="${path}/img/arrow_down.png" onclick="orderChange(this);" order=1 name="recharge_amount"/></th>
													<th>充值人数</th>
													<th>充值次数<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="orderChange(this);" order=0 name="recharge_count"/></th>
													<th>在线人数<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="orderChange(this);" order=0 name="online_count"/></th>
												</tr>
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
	</div>
	<script type="text/javascript">
		
		$(function(){
			search();
		});
   		var pageSize = 10;
		var addTbRow = function(data){
			if(null != data && undefined != data && "" != data){
				var ele="<tr><td>platformTerminal</td><td>platformName</td><td>rechargeAmount</td><td>userCount</td><td>rechargeCount</td><td>onlineCount</td></tr>";
				ele = ele.replace("platformTerminal",data.platformTerminal).replace("platformName",data.platformName).replace("rechargeAmount",data.rechargeAmount).replace("userCount",data.userCount).replace("rechargeCount",data.rechargeCount).replace("onlineCount",data.onlineCount);
				$("#data").append(ele);
			}
		}
		
		
		//显示统计列表
		var showData = function(pageNumber,pageSize){
			$("#data").empty();
			var orderInfo = getOrder("order");
			$.post("${path}/game/operative/countryrcg/listplatformrcg.do",{
				country:$("#country").val(),
				dateStart:$("#dateStart").val(),
				dateEnd:$("#dateEnd").val(),
				platformTerminal:$("#platformTerminal").val()!=-999?$("#platformTerminal").val():null,
				pageNumber:pageNumber,
				pageSize:pageSize,
				order:orderInfo.order,
				orderField:orderInfo.orderField
			},function(data){
				layer.close(layerIndex);
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
						$("#data").append("<tr><td colspan=\"12\">没有数据</td></tr>");
					}else{
						for(var i=0;i < infoData.length;i++){
							var ele = {
									platformTerminal:infoData[i].platformTerminalName,
									rechargeCount:infoData[i].rechargeCount,
									platformName:infoData[i].platformName,
									rechargeAmount:infoData[i].rechargeAmount.toFixed(2) + "(" + getMoneyUnit($("#country").val())  + ")" ,
									userCount:infoData[i].userCount,
									rechargeCount:infoData[i].rechargeCount,
									onlineCount:infoData[i].onlineCount
							}
							addTbRow(ele);
						}
					}
				}else{
					$("#data").append("<tr><td colspan=\"11\">没有数据</td></tr>");
				}
			});
		}
		
		var convertIsVip = function(value){
			if(0 == value){
				return "否";
			}else{
				return "是";
			}
		}
		
		var orderChange = function(value){
			var tempValue = $(value).attr("order");
			resetOrder("order");
			value.order = tempValue;
			if("1" == value.order){
				$(value).attr("order",2);
				value.src = "${path}/img/arrow_up.png";
			}else if("2" == value.order){
				$(value).attr("order",0);
				value.src = "${path}/img/arrow_disable.png";
			}else if("0" == value.order){
				$(value).attr("order",1);
				value.src = "${path}/img/arrow_down.png";
			}
			search();
		}
		
		//重置
		var reset = function(){
			$("#platformTerminal").val(-999),
			search();
		}
		
		
		var layerIndex;
		//查询显示
		var search = function(){
			layerIndex = layer.load(0, {shade:[0.1,'#fff']}); //0代表加载的风格，支持0-2
			pageSize = $("#pageSize").val() * 1;
			showData(1,pageSize);
		}
	</script>
</body>
</html>
