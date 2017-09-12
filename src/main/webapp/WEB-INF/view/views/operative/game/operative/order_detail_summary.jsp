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
					<div class="panel-body" >
						<div class="admin-content"  style="overflow-x:auto;overflow-y:hidden;">
							<div class="section-box" style="min-width:1500px;">
								<div class="titleDiv" style="height: 65px;">
									<div style="display: inline;width:500px;text-align: center;"></div>
								    <div class="select-fr" style="float:left;">
								    	<table style="margin-left:5px;">
									    	<tr>
										    	<td class="tb1Td">单号：</td>
										    	<td><input id="orderNo" class="input" style="width:100px;"></td>
										    	<td class="tb1Td">用户：</td>
										    	<td><input id="user" class="input" placeholder="支持用户名/昵称查询"></td>
										    	<td class="tb1Td">渠道号：</td>
									    		<td><input id="channelId" class="input" style="width:100px;"></td>
										    	<td class="tb1Td">订单金额：</td>
										    	<td><input id="amountStart" class="input-amount" style="width:48px;" onKeypress="return numberInput(event);" onkeyup="value=numberCheck(value);" onblur="value=checkAmount(true);">-<input id="amountEnd" onKeypress="return numberInput(event);" onkeyup="value=numberCheck(value);" class="input-amount" style="width:48px;" onblur="value=checkAmount(false);"></td>
										    	<td class="tb1Td">
										    				<span class="span">平台：</span>
									    		</td>
								    			<td>
							    					<select id="platformTerminal"  class="select" onchange="setPlatformInfos();">
							    						<option value="-999">请选择平台</option>
				                                 		<c:forEach items="${platTerminalInfos}" var="platTerminalInfo">
				                                 			 <option value="${platTerminalInfo.id}">${platTerminalInfo.name}</option>
				                                 		</c:forEach>
				                                    </select>
				                                </td>
										    	<td class="tb1Td">游戏：</td>
										    	<td>
										    		<select id="platformId"  class="select" onchange="setGameServers();" style="width:100px;">
		                                        		<option value="-999">请选择游戏</option>
		                                            	<c:forEach items="${platforms}" var="platform">
		                                            		<option value="${platform.id}" title="${platform.name}">${platform.name}</option>
		                                            	</c:forEach>
		                                        	</select>
		                                        </td>
		                                        <td>服务器：</td>
		                                        <td>
		                                            <select id="serverId"  class="select" onchange="$('#serverId').attr('title',$('#serverId').find('option:selected').text());" style="width:100px;">
		                                                 <option value="-999">请选择服务器</option>
		                                            </select>
										    	</td>
										    	<td class="tb1Td">类型： </td>
										    	<td>
										    		<select id="orderType"  class="select" style="width:100px;">
		                                                 <option value="-999">请选择</option>
		                                                 <option value="1">充值</option>
		                                                 <option value="2">返点</option>
		                                            </select>
										    	</td>
										    	<td class="tb1Td"><span class="span">开发商：</span></td>
										    	<td><input id="developers" class="input" style="width:60px;"></td>
										    	<td style="line-height:1;">
													<button type="button" id="search" class="btn btn-primary btn-sm" onclick="search();"
														<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
													</button>
												</td>
												<td style="line-height:1;">
													<button type="button" id="reset" class="btn btn-primary btn-sm" onclick="reset();"
														<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;"></i>&nbsp;重&nbsp;&nbsp;置&nbsp;
													</button>
												</td>
									    	</tr>
									    	<tr>
									    		<td class="tb1Td">订单状态：</td>
										    	<td>
										    		<select id="payStatus"  class="select" style="width:100px;">
		                                                 <option value="-999">请选择</option>
		                                                 <option value="0">处理中</option>
		                                                 <option value="1">成功</option>
		                                                 <option value="2">失败</option>
		                                                 <option value="3">已关闭</option>
		                                            </select>
										    	</td>
									    		<td class="tb1Td">下单时间：</td>
									    		<td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="createTimeStart" readonly></td>
									    		<td>&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;</td>
									    		<td>
									    			<input class="laydate-icon" id="createTimeEnd" style="height:25px;width:100px;" placeholder="请选择日期" readonly>
									    		</td>
									    		<td class="tb1Td">充值方式：</td>
									    		<td>
									    			<select id="rechargeWay"  class="select" style="width:100px;">
		                                                 <option value="-999">请选择</option>
		                                                 <option value="1">微信支付</option>
		                                                 <option value="2">支付宝</option>
		                                                 <option value="3">乐盈币兑换</option>
		                                                 <option value="4">银联充值</option>
		                                                 <option value="5">优贝支付</option>
		                                                 <option value="6">CC电子钱包</option>
		                                                 <option value="7">CC点卡</option>
		                                                 <option value="9">mol电子钱包</option>
		                                                 <option value="10">mol点卡</option>
		                                                 <option value="11">IOS内购</option>
		                                                 <option value="13">支付宝.威富通</option>
		                                                 <option value="14">微信.威富通</option>
		                                                 <option value="16">快捷支付</option>
		                                                 <option value="17">网银支付</option>
		                                                 <option value="18">易游酷点卡支付</option>
		                                                 <option value="19">支付宝.聚合支付</option>
		                                                 <option value="20">微信.聚合支付</option>
		                                                 <option value="100">其他</option>
	                                            	</select>
									    		</td>
									    		<td class="tb1Td">充值时间：</td>
									    		<td>
									    			<input placeholder="请选择日期" class="laydate-icon" style="height:25px;width:100px;" id="payTimeStart" readonly>
									    		</td>
									    		<td>&nbsp;至&nbsp;</td>
									    		<td>
									    			 <input class="laydate-icon" id="payTimeEnd" style="height:25px;width:100px;" placeholder="请选择日期" readonly>
									    		</td>
									    		<td class="tb1Td"><span class="span">订单号：</span></td>
										    	<td><input id="paymentOrderNo" class="input" style="width:100px;"></td>
									    		<td class="tb1Td"><span class="span">充值单号：</span></td>
										    	<td><input id="tradeNo" class="input" style="width:100px;"></td>
										    	<td></td>
										    	<td></td>
												<td colspan="2" style="line-height:1;">
													<button type="button" id="export" class="btn btn-primary btn-sm" onclick="exportToExcel();"
														<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:124px"></i>导出为Excel
													</button>
												</td>
									    	</tr>
								    	</table>
									</div>
								</div>
								<div class="whiteDiv">
									<ul id="sortable" class="ui-widget-content sortable">
									<li class="ui-state-default">
										<div style="padding-bottom:35px;" class="ui-widget-content resize">
											<div class="sortHandle div-sum">
												<span>游戏充值订单金额(${currencyUnit})：
													<span id="gameRcgSummaryAmount">
														<c:choose>
															<c:when test="${not empty gameRcgSummary}"><fmt:formatNumber type="number" value="${gameRcgSummary}" pattern="#.00"/></c:when>
		       												<c:otherwise>0</c:otherwise>
														</c:choose>
													</span>
												</span>
												<span>&nbsp;&nbsp;&nbsp;&nbsp;乐盈币充值订单金额(${currencyUnit})：
													<span id="lybRcgSummaryAmount">
														<c:choose>
															<c:when test="${not empty lybRcgSummary}"><fmt:formatNumber type="number" value="${lybRcgSummary}" pattern="#.00"/></c:when>
		       												<c:otherwise>0</c:otherwise>
														</c:choose>
													</span>
												</span>
												<span>&nbsp;&nbsp;&nbsp;&nbsp;剩余乐盈币金额(${currencyUnit})：
													<span id="remainLybSummaryAmount">
														<c:choose>
															<c:when test="${not empty remainLybSummary}"><fmt:formatNumber type="number" value="${remainLybSummary}" pattern="#.00"/></c:when>
		       												<c:otherwise>0</c:otherwise>
														</c:choose>
													</span>
												</span>
											</div>
											<div class="tablePanel">
												<table class="tableList1">
													<thead>
														<tr>
															<th>单号</th>
															<th style='min-width:45px;'>状态</th>
															<th>用户名</th>
															<th>用户昵称</th>
															<th style='min-width:50px;'>渠道号</th>
															<th style='min-width:50px;'>类型</th>
															<th style='min-width:65px;'>充值方式</th>
															<th style='min-width:50px;'>开发商</th>
															<th style='min-width:50px;'>平台</th>
															<th style='min-width:50px;'>游戏</th>
															<th style='min-width: 60px;'>服务器</th>
															<th style='min-width:75px;'>订单金额（${currencyUnit}）<img class='order' id='amountOrder' src='${path}/img/arrow_disable.png' onclick='orderchange(this);' lang='0'></img></th>
															<th style='display:none;'>手续费</th>
															<th style='display:none;'>实收金额</th>
															<th style='min-width: 80px;'>下单时间<img id='createTimeOrder' class='order' src='${path}/img/arrow_disable.png' onclick='orderchange(this);' lang='0'></img></th>
															<th style='min-width: 80px;'>充值时间<img id='payTimeOrder' class='order' src='${path}/img/arrow_down.png' onclick='orderchange(this);' lang='1'></img></th>
															<th>订单号</th>
															<th>充值单号</th>
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
			$(function(){
				setPlatformInfos();
				setDateRangeConfig("createTimeStart","createTimeEnd",null,true);
				setDateRangeConfig("payTimeStart","payTimeEnd",null,true);
				search();
				bindEnterEventOfAllEle();
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({minHeight: 600,minWidth: 600});
			});
			
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("orderNo",search);
				bindEnterEvent("user",search);
				bindEnterEvent("amountStart",search);
				bindEnterEvent("amountEnd",search);
				bindEnterEvent("createTimeStart",search);
				bindEnterEvent("createTimeEnd");
				bindEnterEvent("payTimeStart",search);
				bindEnterEvent("payTimeEnd",search);
				bindEnterEvent("pageSize",search);
				bindEnterEvent("channelId",search);
				bindEnterEvent("developers",search);
				bindEnterEvent("paymentOrderNo",search);
				bindEnterEvent("tradeNo",search);
			}
			
			var checkAmount = function(isMin){
				value = isMin ? $("#amountStart").val() : $("#amountEnd").val();
				if("" != $("#amountEnd").val() && "" != $("#amountStart").val()){
					if($("#amountEnd").val() * 1 < $("#amountStart").val() * 1){
						if(isMin){
							layer.alert("起始值必须小于等于结束值", {
								icon : 5
							});
							value = null;
						}else{
							layer.alert("结束值必须大于等于起始值", {
								icon : 5
							});
							value = null;
						}
					}
				}
				return value;
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
			
			var resetOrder = function(){
				var eles = $(".order");
				for(var i = 0;i < eles.length;i++){
					eles[i].lang = 0;
					eles[i].src = "${path}/img/arrow_disable.png";
				}
			}
			
			var orderchange = function(value){
				var tempValue = value.lang;
				resetOrder();
				value.lang = tempValue;
				if(1 == value.lang){
					value.lang = 2;
					value.src = "${path}/img/arrow_up.png";
				}else if(2 == value.lang){
					value.lang = 0;
					value.src = "${path}/img/arrow_disable.png";
				}else if(0 == value.lang){
					value.lang = 1;
					value.src = "${path}/img/arrow_down.png";
				}
				search();
			}
		
			var addTbRow = function(data,isTotal){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td >orderNo</td><td>payStatus</td><td>userId</td><td>nickName</td><td>channelId</td><td>orderTypeName</td><td>rechargeWay</td><td>developers</td><td>platformTerminalName</td><td>platformName</td><td>serverName</td><td>amount</td><td style=\"display:none;\">fee</td><td style=\"display:none;\">receipt_amount</td><td>createTime</td><td>payTime</td><td>paymentOrderNo</td><td>tradeNo</td></tr>"; 
					ele = ele.replace("orderNo",data.orderNo).replace("payStatus",data.payStatus).replace("userId",data.userId).replace("nickName",data.nickName).replace("channelId",data.channelId).replace("orderTypeName",data.orderTypeName).replace("rechargeWay",data.rechargeWay).replace("developers",data.developers).replace("platformTerminalName",data.platformTerminalName).replace("platformName",data.platformName).replace("serverName",data.serverName).replace("amount",data.amount).replace("fee",data.fee).replace("receipt_amount",data.receipt_amount).replace("createTime",data.createTime).replace("payTime",data.payTime).replace("paymentOrderNo",data.paymentOrderNo).replace("tradeNo",data.tradeNo);
					$("#data").append(ele);
				}
			}
			
			//保留两位小数
			var getTwoFixedDouble = function(value){
				return (value*100).toFixed(2);
			}
			
			//设置类别选择数据
	    	var setPlatformInfos = function(){
	    		var optionstring = "<option value=\"-999\" >请选择游戏</option>";
		    	$.post("${path}/game/operative/orderdetailsum/platforminfos.do",{
		    		countryId:$("#countryId").val(),
		    		platformTerminal:$("#platformTerminal").val()
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json && ""!=json){
						for(var i=0;i < json.length;i++){
	                        optionstring += "<option value=\"" + json[i].id + "\"title=" + json[i].name + ">" + json[i].name + "</option>";  
						}
					}
					$("#platformId").html(optionstring);
				});
	    	}
			
			//设置类别选择数据
	    	var setGameServers = function(){
				$("#platformId").attr("title",$("#platformId").find("option:selected").text());
	    		var optionstring = "<option value=\"-999\" >请选择服务器</option>";
	    		if($("#platformId").val() == -999){
	    			$("#serverId").html(optionstring);
	    			return;
	    		}
		    	$.post("${path}/game/operative/orderdetailsum/gameservers.do",{
					platformId:$("#platformId").val()
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json && ""!=json){
						for(var i=0;i < json.length;i++){
	                        optionstring += "<option value=\"" + json[i].id + "\"title=" + json[i].fullName + "\">" + json[i].fullName + "</option>";  
						}
					}
					$("#serverId").html(optionstring);
				});
	    	}
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				$.post("${path}/game/operative/orderdetailsum/list.do",{
					pageNumber:pageNumber,
					pageSize:pageSize,
					orderNo:$("#orderNo").val(),
					user:$("#user").val(),
					amountStart:$("#amountStart").val(),
					amountEnd:$("#amountEnd").val(),
					countryId:$("#countryId").val(),
					platformId:$("#platformId").val() != -999 ? $("#platformId").val() : null,
					serverId:$("#serverId").val() != -999 ? $("#serverId").val() : null,
					payStatus:$("#payStatus").val() != -999 ? $("#payStatus").val() : null,
					createTimeStart:$("#createTimeStart").val(),
					createTimeEnd:$("#createTimeEnd").val(),
					rechargeWay:$("#rechargeWay").val() != -999 ? $("#rechargeWay").val() : null,
					payTimeStart:$("#payTimeStart").val(),
					payTimeEnd:$("#payTimeEnd").val(),
					channelId:$("#channelId").val(),
					developers:$("#developers").val(),
					paymentOrderNo:$("#paymentOrderNo").val(),
					tradeNo:$("#tradeNo").val(),
					orderType:$("#orderType").val() != -999 ? $("#orderType").val() : null,
					amountOrder:$("#amountOrder").attr("lang"),
					createTimeOrder:$("#createTimeOrder").attr("lang"),
					payTimeOrder:$("#payTimeOrder").attr("lang"),
					platformTerminal:$("#platformTerminal").val()
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
							$("#data").append("<tr><td colspan=\"16\">没有数据</td></tr>");
						}else{
							var totalAmount = 0;
							var totalFee = 0;
							var totalReceiptAmount = 0;
							for(var i=0;i < infoData.length;i++){
								totalAmount = accAdd(totalAmount,infoData[i].amount);
								totalFee = accAdd(totalFee,infoData[i].fee);
								totalReceiptAmount = accAdd(totalReceiptAmount,infoData[i].receiptAmount);
								var ele = {
										orderNo:infoData[i].orderNo,
										payStatus:infoData[i].payStatusName,
										userId:infoData[i].userId,
										nickName:infoData[i].nickName,
										channelId:infoData[i].channelId,
										rechargeWay:infoData[i].rechargeWayName,
										platformId:infoData[i].platformId,
										areaId:infoData[i].areaId,
										amount:infoData[i].amount.toFixed(2),
										fee:infoData[i].fee.toFixed(2),
										receiptAmount:infoData[i].receiptAmount.toFixed(2),
										createTime:infoData[i].createTime,
										payTime:infoData[i].payTime,
										tradeNo:infoData[i].tradeNo,
									    serverName:infoData[i].serverName,
									    developers:infoData[i].developers,
									    countryName:infoData[i].countryName,
									    platformName:infoData[i].platformName,
									    orderTypeName:infoData[i].orderTypeName,
									    paymentOrderNo:infoData[i].paymentOrderNo,
									    platformTerminalName:infoData[i].platformTerminalName
								}
								addTbRow(ele);
							}
							
							var ele1 = {
									orderNo:"",
									payStatus:"",
									userId:"",
									nickName:"",
									rechargeWay:"",
									platformId:"",
									amount:totalAmount.toFixed(2),
									fee:totalFee.toFixed(2),
									receiptAmount:totalReceiptAmount.toFixed(2),
									createTime:"",
									payTime:"",
									tradeNo:"",
									serverName:"累计",
									countryName:"",
									platformName:"",
									channelId:"",
									orderTypeName:"",
									paymentOrderNo:"",
									developers:"",
									platformTerminalName:""
							}
							addTbRow(ele1);
						}
					}else{
						$("#totalCount").html(0);
						$("#totalPage").html(0);
						$("#data").append("<tr><td colspan=\"16\">没有数据</td></tr>");
					}
				});
			}
			
			var exportToExcel = function(){
				var index = layer.load();
				$.ajax({
				    url: "${path}/game/operative/orderdetailsum/export.do",
				    type: "POST",
				    data: {
				    	orderNo:$("#orderNo").val(),
						user:$("#user").val(),
						amountStart:$("#amountStart").val(),
						amountEnd:$("#amountEnd").val(),
						countryId:$("#countryId").val(),
						platformId:$("#platformId").val() != -999 ? $("#platformId").val() : null,
						serverId:$("#serverId").val() != -999 ? $("#serverId").val() : null,
						payStatus:$("#payStatus").val() != -999 ? $("#payStatus").val() : null,
						createTimeStart:$("#createTimeStart").val(),
						createTimeEnd:$("#createTimeEnd").val(),
						rechargeWay:$("#rechargeWay").val() != -999 ? $("#rechargeWay").val() : null,
						payTimeStart:$("#payTimeStart").val(),
						payTimeEnd:$("#payTimeEnd").val(),
						channelId:$("#channelId").val(),
						developers:$("#developers").val(),
						paymentOrderNo:$("#paymentOrderNo").val(),
						tradeNo:$("#tradeNo").val(),
						orderType:$("#orderType").val() != -999 ? $("#orderType").val() : null,
						amountOrder:$("#amountOrder").attr("lang"),
						createTimeOrder:$("#createTimeOrder").attr("lang"),
						payTimeOrder:$("#payTimeOrder").attr("lang"),
						platformTerminal:$("#platformTerminal").val()
					},
				    success:function(response, status, request){
				    	layer.close(index);
				    	if(!response.canExport){
				    		layer.msg("当前查询条件数据量过大，请缩小查询条件再导出！", {icon: 5}); 
				    		return;
				    	}
				        window.location = "${path}/game/operative/orderdetailsum/download.do?uuid=" + response.uuid;
			    }});
			}
			
			//重置
			var reset = function(){
				$("#orderNo").val("");
				$("#user").val("");
				$("#amountStart").val("");
				$("#amountEnd").val("");
				$("#platformId").val("-999");
				$("#serverId").val("-999");
				$("#payStatus").val("-999");
				$("#createTimeStart").val("");
				$("#createTimeEnd").val("");
				$("#rechargeWay").val("-999");
				$("#payTimeStart").val("");
				$("#payTimeEnd").val("");
				$("#channelId").val("");
				$("#orderType").val("-999");
				$("#developers").val("");
				$("#paymentOrderNo").val("");
				$("#tradeNo").val("");
				$("#platformTerminal").val("-999");
				search();
			}

			//查询显示
			var search = function(){
				pageSize = $("#pageSize").val() * 1;
				showData(1,pageSize);
			}
		</script>
</body>
</html>
