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
<title>市场合作渠道数据</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin-trend.css" />
<script src="<%=request.getContextPath()%>/js/laydate/laydate.js"></script>
<link href="<%=request.getContextPath()%>/js/myPagination/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/myPagination/js/msgbox/msgbox.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/myPagination/js/myPagination/page.css" />

<script src="<%=request.getContextPath()%>/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/tool.js"></script>
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
			<div class="panel-heading" role="tab" id="headinggrade">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapsegrade" aria-expanded="true"
						aria-controls="collapsegrade" class=""> 统计图 </a>
				</h4>
			</div>
			<input type="hidden" id="countryId" value="${countryId}">
			<div id="collapsegrade" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headinggrade" aria-expanded="true">
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
													<td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="dateEnd" readonly> </td>
													<td class="tb1Td">等级：</td>
											    	<td>
											    		<select id="grade"  class="select" style="width:100px;">
			                                        		<option value="-999">全部等级</option>
			                                            	<c:forEach items="${grades}" var="grade">
			                                            		<option value="${grade}">${grade}</option>
			                                            	</c:forEach>
			                                        	</select>
			                                        </td>
			                                        <td class="tb1Td">平台：</td>
											    	<td>
											    		<select id="os"  class="select" style="width:100px;">
			                                        		<option value="-999">全部平台</option>
			                                            	<c:forEach items="${oss}" var="os">
			                                            		<option value="${os.osid}">${os.osname}</option>
			                                            	</c:forEach>
			                                        	</select>
			                                        </td>
			                                        
			                                        <td class="tb1Td"><span class="span">渠道：</span></td>
									    			<td><input id="channelId" class="input"></td>
									    			
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
										<div>
											<ul id="sortable" class="ui-widget-content sortable">
												<li class="ui-rege-default">
													<div style="padding-bottom:35px;" class="ui-widget-content resize">
														<div class="sortHandle div-sum" style="font-weight: bold;">
															渠道业务统计数据	
														</div>
														<div class="tablePanel">
															<table class="tableList1">
																<thead>
																	<tr>
																		<th>渠道</th>
																		<th>所属平台</th>
																		<th>注册账号</th>
																		<th>注册时间</th>
																		<th>等级</th>
																		<th>用户充值金额(RMB)</th>
																		<th>用户局数</th>
																	</tr>
																</thead>
																<tbody id="data">
																</tbody>
															</table>
														</div>
														<div style="font-size:14px;font-weight:bold;">注册总人数：<span id="userCount"></span>&nbsp;&nbsp;&nbsp;充值总金额(RMB)：<span id="rechargeAmountSum"></span>&nbsp;&nbsp;&nbsp;用户总局数：<span id="inningSum"></span></div>
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
				setDateRangeConfig("dateStart","dateEnd",null,true,false);
				search();
				bindEnterEventOfAllEle();
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("userId",search);
				bindEnterEvent("grade",search);
				bindEnterEvent("channelId",search);
				bindEnterEvent("os",search);
			}
			
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>channelId</td><td>osname</td><td>userId</td><td>regDate</td><td>grade</td><td>rechargeAmount</td><td>inning</td></tr>";
					ele = ele.replace("userId",data.userId).replace("regDate",data.regDate).replace("grade",data.grade).replace("rechargeAmount",data.rechargeAmount).replace("inning",data.inning).replace("channelId",data.channelId).replace("osname",data.osname);
					$("#data").append(ele);
				}
			}
			
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				$.post("${path}/game/operative/lmdz/channeldata/list.do",{
					dateStart:$("#dateStart").val(),
					dateEnd:$("#dateEnd").val(),
					os:$("#os").val() == -999 ? "" : $("#os").val(),
					grade:$("#grade").val() == -999 ? "" : $("#grade").val(),
					channelId:$("#channelId").val(),
					pageNumber:pageNumber,
					pageSize:pageSize
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
							$("#data").append("<tr><td colspan=\"5\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										channelId:infoData[i].channelId,
										osname:infoData[i].osname,
										userId:infoData[i].userId,
										regDate:formatDate(infoData[i].regDate),
										grade:infoData[i].grade,
										rechargeAmount:infoData[i].rechargeAmount.toFixed(2),
										inning:infoData[i].inning
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#data").append("<tr><td colspan=\"5\">没有数据</td></tr>");
					}
				});
			}
			
			var showSum = function(){
				$.post("${path}/game/operative/lmdz/channeldata/sum.do",{
					dateStart:$("#dateStart").val(),
					dateEnd:$("#dateEnd").val(),
					grade:$("#grade").val() == -999 ? "" : $("#grade").val(),
					pageSize:pageSize
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json && ""!=json){
						$("#userCount").text(json.userCount);
						$("#rechargeAmountSum").text(json.rechargeAmountSum.toFixed(2));
						$("#inningSum").text(json.inningSum);
					}
				});
			}
			
			

			//重置
			var reset = function(){
				$("#grade").val("-999");
				$("#os").val("-999");
				$("#channelId").val("");
				$("#dateStart").val("");
				$("#dateEnd").val("");
				search();
			}
			
			//查询显示
			var layerIndex;
			var search = function(){
				layerIndex = layer.load(0, {shade:[0.1,'#fff']}); //0代表加载的风格，支持0-2
				pageSize = $("#pageSize").val() * 1;
				showData(1,pageSize);
				showSum();
			}
			
			var exportToExcel = function(){
				var index = layer.load();
				$.ajax({
				    url: "${path}/game/operative/lmdz/channeldata/export.do",
				    type: "POST",
				    data: {
				    	dateStart:$("#dateStart").val(),
						dateEnd:$("#dateEnd").val(),
						os:$("#os").val() == -999 ? "" : $("#os").val(),
						grade:$("#grade").val() == -999 ? "" : $("#grade").val(),
						channelId:$("#channelId").val()
					},
				    success:function(response, status, request){
				    	layer.close(index);
				    	if(!response.canExport){
				    		layer.msg("当前查询条件数据量过大，请缩小查询条件再导出！", {icon: 5}); 
				    		return;
				    	}
				        window.location = "${path}/game/operative/lmdz/channeldata/download.do?uuid=" + response.uuid;
			    }});
			}
		</script>
</body>
</html>
