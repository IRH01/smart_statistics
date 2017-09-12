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
			<div class="panel-heading" role="tab" id="headingregistCount">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseregistCount" aria-expanded="true"
						aria-controls="collapseregistCount" class=""> 统计图 </a>
				</h4>
			</div>
			<div id="collapseregistCount" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingregistCount" aria-expanded="true">
					<div class="panel-body">
						<div class="admin-content partner-container">
								<div class="section-box">
									<div class="titleDiv" style="height:25px;">
										<div style="display: inline;width:500px;text-align: center;"></div>
									    <div class="select-fr" style="padding-right: 30px;float:left;">
									    	<table style="margin-left:5px;">
									    		<tr>
									    			<td class="tb1Td">
									    				<span class="span">日期：
														</span>
													</td>
													<td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="dateStart" readonly> </td>
													<td>
														<span class="span">&nbsp;-&nbsp;</span>
													</td>
													<td><input class="laydate-icon" id="dateEnd" style="height:25px;" placeholder="请选择日期" readonly></td>
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
																		<th style="display:none;">countryId</th>
																		<th>国家</th>
																		<th>新增注册数</th>
																		<th>付费用户数</th>
																		<th>充值次数</th>
																		<th>充值总额</th>
																	</tr>
																</thead>
																<tbody id="data">
																</tbody>
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
				setDateRangeConfig("dateStart","dateEnd",null,true,false);
				//初始化时间为今天
				$("#dateStart").val(getFormatDate(new Date()));
				$("#dateEnd").val(getFormatDate(new Date()));
				search();
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td class='cId' style='display:none;'>country</td><td>countryName</td><td>registCount</td><td>userCount</td><td>rechargeCount</td><td><a onclick='showDetail(this)' class='partner-link' >rechargeAmount</a></td></tr>";
					ele = ele.replace("country",data.country).replace("countryName",data.countryName).replace("registCount",data.registCount).replace("userCount",data.userCount).replace("rechargeCount",data.rechargeCount).replace("rechargeAmount",data.rechargeAmount);
					$("#data").append(ele);
				}
			}
			
			var showDetail = function(ele){
				var parentObj = $(ele).parent().parent();
				var countryIdEle = $(parentObj).children(".cId")[0];
				var url = "${path}/game/operative/countryrcg/showplatformrcg.do?country=" + $(countryIdEle).text();
				if(undefined != $("#dateStart").val() && null != $("#dateStart").val() && '' != $("#dateStart").val()){
					url += ("&dateStart=" + $("#dateStart").val());
				}
				if(undefined != $("#dateEnd").val() && null != $("#dateEnd").val() && '' != $("#dateEnd").val()){
					url += ("&dateEnd=" + $("#dateEnd").val());
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
			var showData = function(){
				$("#data").empty();
				$.post("${path}/game/operative/countryrcg/listcountryrcg.do",{
					dateStart:$("#dateStart").val(),
					dateEnd:$("#dateEnd").val()
				},function(data){
					layer.close(layerIndex);
					var infoData = JSON.parse(data);
					if(null != infoData && undefined != infoData && infoData.length > 0){
						for(var i=0;i < infoData.length;i++){
							var ele = {
									country:infoData[i].country,
									countryName:infoData[i].countryName,
									registCount:infoData[i].registCount,
									userCount:infoData[i].userCount,
									rechargeCount:infoData[i].rechargeCount,
									rechargeAmount:infoData[i].rechargeAmount.toFixed(2)  + "(" +  getMoneyUnit(infoData[i].country)  + ")" 
							}
							addTbRow(ele);
						}
					}else{
						$("#data").append("<tr><td colspan=\"5\">没有数据</td></tr>");
					}
				});
			}
			
			
			//重置
			var reset = function(){
				$("#dateStart").val("");
				$("#dateEnd").val("");
				search();
			}
			
			var layerIndex;
			//查询显示
			var search = function(){
				layerIndex = layer.load(0, {shade:[0.1,'#fff']}); //0代表加载的风格，支持0-2
				showData();
			}
		</script>
</body>
</html>
