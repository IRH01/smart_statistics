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
<meta charset="UTF-8">
<title>后台统计</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin-trend.css" />

<script src="<%=request.getContextPath()%>/js/laydate/laydate.js"></script>
<link href="<%=request.getContextPath()%>/js/myPagination/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/myPagination/js/msgbox/msgbox.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/myPagination/js/myPagination/page.css" />

<script src="<%=request.getContextPath()%>/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script src="<%=request.getContextPath()%>/js/tool.js"></script>
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
			<div class="panel-heading" role="tab" id="headingpvCnt">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapsepvCnt" aria-expanded="true"
						aria-controls="collapsepvCnt" class=""> 统计图 </a>
				</h4>
			</div>
			<input type="hidden" id="countryId" value="${countryId}">
			<div id="collapsepvCnt" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingpvCnt" aria-expanded="true">
					<div class="panel-body">
						<div class="admin-content partner-container">
								<div class="section-box">
									<div class="titleDiv" style="height:25px;">
										<div style="display: inline;width:500px;text-align: center;"></div>
									    <div class="select-fr" style="padding-right: 30px;float:left;">
									    	<table style="margin-left:5px;">
									    		<tr>
									    			<td class="tb1Td"><span class="span">渠道：</span></td>
									    			<td><input id="channel" class="input" placeholder="支持渠道号/渠道名称查询" style="width: 140px;"></td>
									    			<td class="tb1Td">
									    				<span class="span">日期：
														</span>
													</td>
													<td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="dateStart" readonly> </td>
													<td>
														<span class="span">&nbsp;-&nbsp;</span>
													</td>
													<td><input class="laydate-icon" id="dateEnd" style="height:25px;" placeholder="请选择日期" readonly></td>
													<td class="tb1Td"><span class="span" style="width:80px;">充值金额：</span></td>
									    			<td>&nbsp;&nbsp;</td>
									    			<td><span class="span"><input id="payAmountStart" style="width:80px;" class="input-amount" onKeypress="return numberInput(event);" onkeyup="value=numberCheck(value);" onblur="value=checkAmount(true);"></td>
									    			<td>-</td>
									    			<td><input id="payAmountEnd" style="width:80px;" onKeypress="return numberInput(event);" onkeyup="value=numberCheck(value);" class="input-amount" onblur="value=checkAmount(false);"></span></td>
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
																		<th>渠道编号</th>
																		<th>渠道名称</th>
																		<th>PV<img class="order dailyDataOrder" src="${path}/img/arrow_down.png" onclick="orderChange(this);" order=1 name="pv_cnt"/></th>
																		<th>UV<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="orderChange(this);" order=0 name="uv_cnt"/></th>
																		<th>IP<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="orderChange(this);" order=0 name="ip_cnt"/></th>
																		<th>注册用户数</th>
																		<th>渠道注册比重</th>
																		<th>付费用户数</th>
																		<th>渠道付费比重</th>
																		<th>首充总额(RMB)</th>
																		<th>充值总额(RMB)</th>
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
					</div>
				</div>
		</div>
		<script type="text/javascript">
			$(function(){
				//起始时间根据：上线时间变化
				setDateRangeConfig("dateStart","dateEnd",null,true,false,"2017-04-07",true);
				//初始化时间为今天
				$("#dateStart").val(getFormatDate(new Date()));
				$("#dateEnd").val(getFormatDate(new Date()));
				search();
				bindEnterEventOfAllEle();
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("channel",search);
				bindEnterEvent("payAmountStart",search);
				bindEnterEvent("payAmountEnd",search);
			}
			
			var checkAmount = function(isMin){
				value = isMin ? $("#payAmountStart").val() : $("#payAmountEnd").val();
				if("" != $("#payAmountEnd").val() && "" != $("#payAmountStart").val()){
					if($("#payAmountEnd").val() * 1 < $("#payAmountStart").val() * 1){
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
			
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td class='channel'>channelId</td><td>channelName</td><td>pvCnt</td><td>uvCnt</td><td>ipCnt</td><td><a onclick='showDetail(this)' class='partner-link' >regUCnt</a></td><td>regUCntRate</td><td>payUCnt</td><td>payUCntRate</td><td>payFirstAmount</td><td>payAmount</td></tr>";
					ele = ele.replace("channelId",data.channelId).replace("channelName",data.channelName).replace("pvCnt",data.pvCnt).replace("uvCnt",data.uvCnt).replace("ipCnt",data.ipCnt).replace("payUCnt",data.payUCnt).replace("regUCnt",data.regUCnt).replace("payFirstAmount",data.payFirstAmount).replace("payAmount",data.payAmount).replace("regUCntRate",data.regUCntRate).replace("payUCntRate",data.payUCntRate);
					$("#data").append(ele);
				}
			}
			
			var showDetail = function(ele){
				var parentObj = $(ele).parent().parent();
				var channelIdEle = $(parentObj).children(".channel")[0];
				var url = "${path}/game/operative/vipregchannel/showdetail.do?channel=" + $(channelIdEle).text();
				url += ("&countryId=" + $("#countryId").val());
				if(undefined != $("#dateStart").val() && null != $("#dateStart").val() && '' != $("#dateStart").val()){
					url += ("&createTimeStart=" + $("#dateStart").val());
				}else{
					//默认时间根据上线时间确定
					url += "&createTimeStart=2017-03-15";
				}
				if(undefined != $("#dateEnd").val() && null != $("#dateEnd").val() && '' != $("#dateEnd").val()){
					url += ("&createTimeEnd=" + $("#dateEnd").val());
				}
				layer.open({
	                type: 2,
	                title: "注册用户",
	                maxmin: true,
	                resize:true,
	                shadeClose: false, //点击遮罩关闭层
	                moveOut:true,
	                scrollbar:true,
	                area : ["1200px" , "650px"],
	                scrollbar:true,
	                content: url
	            });
			}
			
			
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				var orderInfo = getOrder("order");
				$.post("${path}/game/operative/vipregchannel/listsum.do",{
					channel:$("#channel").val(),
					dateStart:$("#dateStart").val(),
					dateEnd:$("#dateEnd").val(),
					payAmountStart:$("#payAmountStart").val(),
					payAmountEnd:$("#payAmountEnd").val(),
					countryId:$("#countryId").val(),
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
							$("#data").append("<tr><td colspan=\"11\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										channelId:infoData[i].channelId,
										channelName:infoData[i].channelName,
										pvCnt:infoData[i].pvCnt,
										uvCnt:infoData[i].uvCnt,
										ipCnt:infoData[i].ipCnt,
										payUCnt:infoData[i].payUCnt,
										regUCnt:infoData[i].regUCnt,
										payFirstAmount:infoData[i].payFirstAmount.toFixed(2),
										payAmount:infoData[i].payAmount.toFixed(2),
										regUCntRate:convertToPercentFormat(infoData[i].regUCntRate),
										payUCntRate:convertToPercentFormat(infoData[i].payUCntRate)
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#data").append("<tr><td colspan=\"11\">没有数据</td></tr>");
					}
				});
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
				$("#channel").val("");
				$("#dateStart").val("");
				$("#dateEnd").val("");
				$("#payAmountStart").val("");
				$("#payAmountEnd").val("");
				search();
			}
			
			//查询显示
			var layerIndex;
			var search = function(){
				layerIndex = layer.load(0, {shade:[0.1,'#fff']}); //0代表加载的风格，支持0-2
				pageSize = $("#pageSize").val() * 1;
				showData(1,pageSize);
			}
		</script>
</body>
</html>
