<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<%
	request.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台统计</title>
<link type="text/css" rel="stylesheet"
	href="${path}/css/admin-trend.css" />

<script src="${path}/js/laydate/laydate.js"></script>
<link href="${path}/js/myPagination/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${path}/js/myPagination/js/myPagination/page.css" />

<script src="${path}/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<script src="${path}/js/layer/layer.js"></script>
<script src="${path}/js/tool.js"></script>
<script src="${path}/js/datecontrol.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
</head>
<body>
	<div class="sdk-content">
		<ul class="breadcrumb">
			<li>您当前的位置：</li>
			<tags:breadcrumb />
			<li>统计图</li>
		</ul>
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 统计图 </a>
				</h4>
			</div>
			<input type="hidden" id="countryId" value="${countryId}">
			<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
					<div class="panel-body">
						<div class="admin-content"  style="overflow-x:auto;overflow-y:hidden;">
							<div class="section-box">
								<div class="titleDiv" style="height: 65px;">
									<div style="display: inline;width:500px;text-align: center;"></div>
								    <div class="select-fr" style="padding-right: 30px;float:left;">
								    	<table style="margin-left:5px;">
									    	<tr>
									    		<td >
									    			<span class="span">查询时间：</span>
									    		</td>
									    		<td>
									    			<input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="dateStart" readonly>
									    		</td>
									    		<td>
									    			<span class="span">&nbsp;至&nbsp;</span>
									    		</td>
									    		<td>
									    			 <input class="laydate-icon" id="dateEnd" style="height:25px;" placeholder="请选择日期" readonly>
									    		</td>
									    		<td>
		                                            <select id="dateType" onchange="dateTypeChange();" style="width:60px;" class="select">
		                                                 <option value="1">近7日</option>
		                                                 <option value="2">近14日</option>
		                                                 <option value="3">近30日</option>
		                                                 <option value="4">近90日</option>
		                                                 <option value="0">自定义</option>
		                                            </select>
									    		</td>
									    		<td  style="line-height:1;">
													<button type="button" id="search" class="btn btn-primary btn-sm" onclick="search();"
														<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>查询
													</button>
												<td/>
									    	</tr>
								    	</table>
									</div>
								</div>
								<div class="whiteDiv">
									<ul id="sortable" class="ui-widget-content sortable">
									<li class="ui-state-default">
										<div style="padding-bottom:35px;" class="ui-widget-content resize">
											<div class="sortHandle">
											</div>
											<div class="tablePanel">
												<table class="tableListGame">
													<thead>
														<tr>
															<th>日期<img class="order dailyDataOrder" src="${path}/img/arrow_down.png" onclick="dailyOrderChange(this);" order=1 name="stat_date"/></th>
															<th style="min-width:45px;">注册用户数<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="dailyOrderChange(this);" order=0 name="reg_count"/></th>
															<th>付费用户<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="dailyOrderChange(this);" order=0 name="recharge_count"/></th>
															<th>付费率<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="dailyOrderChange(this);" order=0 name="recharge_count"/></th>
															<th style="min-width:50px;">充值金额(VND)<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="dailyOrderChange(this);" order=0 name="pay_amount"/></th>
															<th style="min-width:50px;">DAU<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="dailyOrderChange(this);" order=0 name="dau"/></th>
															<th style="min-width:65px;">WAU<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="dailyOrderChange(this);" order=0 name="wau"/></th>
															<th style="min-width:60px;">MAU<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="dailyOrderChange(this);" order=0 name="mau"/></th>
															<th style="min-width: 60px;">留存率<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="dailyOrderChange(this);" order=0 name="dau"/></th>
														</tr>
													</thead>
													<tbody id="data">
													</tbody>
												</table>
											</div>
											<div class="pagePanel">
												<table class="tablePage">
													<tr>
														<td><div class="divPage"><span class="spanPageSize">每页记录数：</span><input id="pageSize" value="10" class="inputPageSize" onKeypress="return intInput(event);" onKeyup="value=pageSizeLimit(value);" onblur="value=pageSizeNotEmpty(value);"/></div></td>
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
				</div>
		</div>
		<script type="text/javascript">
			var pageSize = 10;
			
			var dateChange = function(){
				$("#dateType").val(0);
				search();
			}
			var dateValue = setDateRangeConfig("dateStart","dateEnd",dateChange);
			var dateTypeChange = function(){
				setDateType2Change("dateType","dateStart","dateEnd",dateValue.dateStart,dateValue.dateEnd,search);
			}	
			
			$(function(){
				setDateType2Change("dateType","dateStart","dateEnd",dateValue.dateStart,dateValue.dateEnd,search);
				bindEnterEvent("pageSize",search);
				$( ".resize" ).resizable({minHeight: 100,minWidth: 600});
			});
			
			
			/************************公共方法相关Start*******************************/
			var resetOrder = function(clsName){
				var eles = $("." + clsName);
				for(var i = 0;i < eles.length;i++){
					$(eles[i]).attr("order",0);
					$(eles[i]).attr("src","${path}/img/arrow_disable.png");
				}
			}
			
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
			
			var pageSizeLimit = function(value){
				if("" != value && value * 1 > 200){
					layer.alert("每页个数最多只能为200", {
						icon : 5
					});
					value = 10;
				}
				return value;
			}
			var pageSizeNotEmpty = function(value){
				if("" == value){
					layer.alert("每页个数不能为空", {
						icon : 5
					});
					value = 10;
				}
				return value;
			}
			
			
			var expandAll = function(clsName){
				var eles = $("." + clsName);
				for(var i = 0;i < eles.length;i++){
					$(eles[i]).attr("expand",true);
					$(eles[i]).attr("src","${path}/img/expand_32.png");
				}
			}
			
			var setExpand = function(ele,clsName){
				var tempValue = $(ele).attr("expand");
				expandAll(clsName);
				if("true" === tempValue){
					$(ele).attr("expand",false);
					$(ele).attr("src","${path}/img/fold_32.png");
					return true;
				}else{
					$(ele).attr("expand",true);
					$(ele).attr("src","${path}/img/expand_32.png");
					return false;
				}
			}
			
			//创建表头
			var getColumnHead = function(order,columnName,fieldName,clsName,callBack){
				var column = "<th>columnName<img class='order clsName' src='${path}/img/arrow_disable.png' onclick='callBack(this);' order=0 name='fieldName'/></th>";
				if(1 == order){
					column = "<th>columnName<img class='order clsName' src='${path}/img/arrow_down.png' onclick='callBack(this);' order=1 name='fieldName'/></th>";
				}else if(2 == order){
					column = "<th>columnName<img class='order clsName' src='${path}/img/arrow_up.png' onclick='callBack(this);' order=2 name='fieldName'/></th>";
				}
				column = column.replace("clsName", clsName).replace("fieldName", fieldName).replace("callBack", callBack).replace("columnName", columnName);
				return column;
			}
			/************************公共方法相关End*******************************/
			
			
			/************************日数据相关Start*******************************/
			var search = function(){
				pageSize = $("#pageSize").val() * 1;
				showDailyData(1,pageSize);
			}
			
			var addDailyTbRow = function(data,isTotal){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td><img onclick='showGameData(this);' class='expand dailyDataExpand' expand=true src='${path}/img/expand_32.png'></img>statDate</td><td>regCount</td><td>rechargeCount</td><td>payPercent</td><td>payAmount</td><td>DAU</td><td>WAU</td><td>MAU</td><td>remainPercent</td></tr>";
					ele = ele.replace("statDate",data.statDate).replace("regCount",data.regCount).replace("rechargeCount",data.rechargeCount).replace("payPercent",data.payPercent).replace("payAmount",data.payAmount).replace("DAU",data.DAU).replace("WAU",data.WAU).replace("MAU",data.MAU).replace("remainPercent",data.remainPercent);
					$("#data").append(ele);
				}
			}
			
			var dailyOrderChange = function(value){
				var tempValue = $(value).attr("order");
				resetOrder("dailyDataOrder");
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
			
			//显示日数据
			var showDailyData = function(pageNumber,pageSize){
				var index = layer.load();
				$("#data").empty();
				var orderInfo = getOrder("dailyDataOrder");
				$.post("${path}/game/operative/channeldatastat/dailydatalist.do",{
					pageNumber:pageNumber,
					pageSize:pageSize,
					dateStart:$("#dateStart").val(),
					dateEnd:$("#dateEnd").val(),
					countryId:$("#countryId").val(),
					order:orderInfo.order,
					orderField:orderInfo.orderField
				},function(data){
					layer.close(index);
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						$("#totalCount").html(json.total);
						$("#totalPage").html(json.pages);
						$("#page").myPagination({
					          currPage: pageNumber,
					          pageCount: json.pages,
					          ajax:{on:false,  
				                    onClick:function(page){
				                    	showDailyData(page,pageSize);
				                    }  
				        		}
					        });
						var infoData = json.list;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							$("#data").append("<tr><td colspan=\"9\">没有数据</td></tr>");
							$("#totalCount").html(0);
							$("#totalPage").html(0);
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										statDate:infoData[i].statDate,
										regCount:infoData[i].regCount,
										rechargeCount:infoData[i].rechargeCount,
										payPercent:(infoData[i].payPercent * 100).toFixed(2) + "%",
										payAmount:infoData[i].payAmount,
										DAU:infoData[i].dau,
										WAU:infoData[i].wau,
										MAU:infoData[i].mau,
										remainPercent:(infoData[i].remainPercent * 100).toFixed(2) + "%"
								}
								addDailyTbRow(ele);
							}
						}
					}else{
						$("#totalCount").html(0);
						$("#totalPage").html(0);
						$("#data").append("<tr><td colspan=\"9\">没有数据</td></tr>");
					}
				});
			}
			/************************日数据相关End*******************************/
			
			/************************游戏数据相关Start*******************************/
			var getGameTbRow = function(data){
				var ele;
				if(null != data && undefined != data && "" != data){
					ele="<tr><td><img onclick='showChannelData(this);' class='expand gameDataExpand' expand=true src='${path}/img/expand_32.png'></img>gameId</td><td>gameName</td><td>regCount</td><td>rechargeCount</td><td>payPercent</td><td>payAmount</td><td>DAU</td><td>WAU</td><td>MAU</td><td>remainPercent</td></tr>";
					ele = ele.replace("gameId",data.gameId).replace("gameName",data.gameName).replace("regCount",data.regCount).replace("rechargeCount",data.rechargeCount).replace("payPercent",data.payPercent).replace("payAmount",data.payAmount).replace("DAU",data.DAU).replace("WAU",data.WAU).replace("MAU",data.MAU).replace("remainPercent",data.remainPercent);
				}
				return ele;
			}
			
			//游戏数据排序处理
			var gameOrderChange = function(value){
				var tempValue = $(value).attr("order");
				resetOrder("gameDataOrder");
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
				getGameData(gameQueryDate,"gameTbodyData");
			}
			
			//查询日期
			var gameQueryDate;
			//显示游戏数据
			var showGameData = function(dailyDataEle){
				$("#gameDataTb").remove();
				if(!setExpand(dailyDataEle,"dailyDataExpand")){
					return;
				}
				gameQueryDate = $(dailyDataEle).parent().text();
				var gameData = "<tr id='gameDataTb' style='border-bottom:none;'><td colspan='13' style='border-bottom:none;'>table</td></tr>";
				var columnHeader = getColumnHead(1,"游戏ID","game_id","gameDataOrder","gameOrderChange")
							     + getColumnHead(0,"游戏名称","game_name","gameDataOrder","gameOrderChange")
							     + getColumnHead(0,"注册用户数","reg_count","gameDataOrder","gameOrderChange")
							     + getColumnHead(0,"付费用户","recharge_count","gameDataOrder","gameOrderChange")
							     + getColumnHead(0,"付费率","recharge_count","gameDataOrder","gameOrderChange")
							     + getColumnHead(0,"充值金额(VND)","pay_amount","gameDataOrder","gameOrderChange")
							     + getColumnHead(0,"DAU","dau","gameDataOrder","gameOrderChange")
							     + getColumnHead(0,"WAU","wau","gameDataOrder","gameOrderChange")
							     + getColumnHead(0,"MAU","mau","gameDataOrder","gameOrderChange")
							     + getColumnHead(0,"留存率","dau","gameDataOrder","gameOrderChange");
				var tableData = "<table class='tableListGame' style='margin-left: 3%;width:97%;'><thead><tr>columnHeader</tr></<thead><tbody id='gameTbodyData'><tr><td colspan=\"10\">数据加载中...</td></tr></tbody></table>";
				tableData = tableData.replace("columnHeader", columnHeader);
				gameData = gameData.replace("table", tableData);
				$(dailyDataEle).parent().parent().after(gameData);
				getGameData(gameQueryDate,"gameTbodyData");
			}
			
			//获取游戏数据
			var getGameData = function(date,tbodyId){
				var index = layer.load();
				$("#" + tbodyId).empty();
				var orderInfo = getOrder("gameDataOrder");
				$.post("${path}/game/operative/channeldatastat/gamedatalist.do",{
					pageNumber:0,
					pageSize:0,
					date:date,
					countryId:$("#countryId").val(),
					order:orderInfo.order,
					orderField:orderInfo.orderField
				},function(data){
					layer.close(index);
					var tbodyData = "";
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						var infoData = json.list;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							tbodyData = "<tr><td colspan=\"10\">没有数据</td></tr>";
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										gameId:infoData[i].gameId,
										gameName:infoData[i].gameName,
										regCount:infoData[i].regCount,
										rechargeCount:infoData[i].rechargeCount,
										payPercent:(infoData[i].payPercent * 100).toFixed(2) + "%",
										payAmount:infoData[i].payAmount,
										DAU:infoData[i].dau,
										WAU:infoData[i].wau,
										MAU:infoData[i].mau,
										remainPercent:(infoData[i].remainPercent * 100).toFixed(2) + "%"
								}
								var tbRow = getGameTbRow(ele);
								tbodyData += tbRow;
							}
						}
					}else{
						tbodyData = "<tr><td colspan=\"10\">没有数据</td></tr>";
					}
					$("#" + tbodyId).append(tbodyData);
				});
			}
			/************************游戏数据相关End*******************************/
			
			
			/************************渠道数据相关Start*******************************/
			var getChannelTbRow = function(data){
				var ele;
				if(null != data && undefined != data && "" != data){
					ele="<tr><td>channelId</td><td>channelName</td><td>regCount</td><td>rechargeCount</td><td>payPercent</td><td>payAmount</td><td>DAU</td><td>WAU</td><td>MAU</td><td>remainPercent</td></tr>";
					ele = ele.replace("channelId",data.channelId).replace("channelName",data.channelName).replace("regCount",data.regCount).replace("rechargeCount",data.rechargeCount).replace("payPercent",data.payPercent).replace("payAmount",data.payAmount).replace("DAU",data.DAU).replace("WAU",data.WAU).replace("MAU",data.MAU).replace("remainPercent",data.remainPercent);
				}
				return ele;
			}
			
			//渠道数据排序
			var channelOrderChange = function(value){
				var tempValue = $(value).attr("order");
				resetOrder("channelDataOrder");
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
				getChannelData(gameQueryDate,channelQueryGameId,"channelTbodyData");
			}
			
			//查询游戏ID
			var channelQueryGameId;
			//显示渠道数据
			var showChannelData = function(gameDataEle){
				$("#channelDataTb").remove();
				if(!setExpand(gameDataEle,"gameDataExpand")){
					return;
				}
				channelQueryGameId = $(gameDataEle).parent().text();
				var channelData = "<tr id='channelDataTb' style='border-bottom:none;'><td colspan='13' style='border-bottom:none;'>table</td></tr>";
				var columnHeader = getColumnHead(2,"渠道ID","channel_id","channelDataOrder","channelOrderChange")
			     + getColumnHead(0,"渠道名称","channel_name","channelDataOrder","channelOrderChange")
			     + getColumnHead(0,"注册用户数","reg_count","channelDataOrder","channelOrderChange")
			     + getColumnHead(0,"付费用户","recharge_count","channelDataOrder","channelOrderChange")
			     + getColumnHead(0,"付费率","recharge_count","channelDataOrder","channelOrderChange")
			     + getColumnHead(0,"充值金额(VND)","pay_amount","channelDataOrder","channelOrderChange")
			     + getColumnHead(0,"DAU","dau","channelDataOrder","channelOrderChange")
			     + getColumnHead(0,"WAU","wau","channelDataOrder","channelOrderChange")
			     + getColumnHead(0,"MAU","mau","channelDataOrder","channelOrderChange")
			     + getColumnHead(0,"留存率","dau","channelDataOrder","channelOrderChange");
				var tableData = "<table class='tableListGame' style='margin-left: 3%;width:97%;'><thead><tr>columnHeader</tr></<thead><tbody id='channelTbodyData'><tr><td colspan=\"10\">数据加载中...</td></tr></tbody></table>";
				tableData = tableData.replace("columnHeader", columnHeader);
				channelData = channelData.replace("table", tableData);
				$(gameDataEle).parent().parent().after(channelData);
				getChannelData(gameQueryDate,channelQueryGameId,"channelTbodyData");
			}
			
			//获取渠道数据
			var getChannelData = function(date,gameId,tbodyId){
				var index = layer.load();
				$("#" + tbodyId).empty();
				var orderInfo = getOrder("channelDataOrder");
				$.post("${path}/game/operative/channeldatastat/channeldatalist.do",{
					pageNumber:0,
					pageSize:0,
					gameId:gameId,
					date:date,
					order:orderInfo.order,
					orderField:orderInfo.orderField
				},function(data){
					layer.close(index);
					var tbodyData = "";
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						var infoData = json.list;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							tbodyData = "<tr><td colspan=\"10\">没有数据</td></tr>";
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										channelId:"" == infoData[i].channelId ? "未知" : infoData[i].channelId,
										channelName:undefined != infoData[i].channelName ? infoData[i].channelName : "",
										regCount:infoData[i].regCount,
										rechargeCount:infoData[i].rechargeCount,
										payPercent:(infoData[i].payPercent * 100).toFixed(2) + "%",
										payAmount:infoData[i].payAmount,
										DAU:infoData[i].dau,
										WAU:infoData[i].wau,
										MAU:infoData[i].mau,
										remainPercent:(infoData[i].remainPercent * 100).toFixed(2) + "%"
								}
								var tbRow = getChannelTbRow(ele);
								tbodyData += tbRow;
							}
						}
					}else{
						tbodyData = "<tr><td colspan=\"10\">没有数据</td></tr>";
					}
					$("#" + tbodyId).append(tbodyData);
				});
			}
			/************************渠道数据相关End*******************************/
		</script>
</body>
</html>
