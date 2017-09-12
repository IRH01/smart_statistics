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
						<div class="admin-content">
								<div class="section-box" style="min-width:1200px;">
									<div class="titleDiv" style="height:65px;">
										<div style="display: inline;width:500px;text-align: center;"></div>
									    <div class="select-fr" style="padding-right: 30px;float:left;">
									    	<table style="margin-left:5px;">
									    		<tr>
									    			<td class="tb1Td"><span class="span">渠道号：</span></td>
									    			<td><input id="channelId" class="input"></td>
									    			<td class="tb1Td"><span class="span">渠道名称：</span></td>
									    			<td><input id="channelName" class="input"></td>
									    			<td class="tb1Td"><span class="span">用户：</span></td>
									    			<td><input id="user" class="input" placeholder="支持用户名/昵称查询"></td>
									    			<td class="tb1Td"><span class="span">手机号：</span></td>
									    			<td><input id="phone" class="input"></td>
									    			<td class="tb1Td">
									    				<span class="span">注册时间：</span>
													</td>
													<td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="regTimeStart" readonly> </td>
													<td>
														<span class="span">&nbsp;至&nbsp;</span>
													</td>
													<td><input class="laydate-icon" id="regTimeEnd" style="height:25px;" placeholder="请选择日期" readonly></td>
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
													<td class="tb1Td">
									    				<span class="span">游戏：</span>
									    			</td>
									    			<td>
									    				<select id="platformId"  class="select" onchange="setGameServers();">
                                        					<option value="-999">请选择游戏</option>
			                                            	<c:forEach items="${platforms}" var="platform">
			                                            		<option value="${platform.id}" title="${platform.name}">${platform.name}</option>
			                                            	</c:forEach>
			                                        	</select>
	                                       				</span>
	                                       			</td>
	                                       			<td class="tb1Td">服务器：</td>
	                                       			<td>
			                                            <select id="serverId"  class="select" onchange="$('#serverId').attr('title',$('#serverId').find('option:selected').text());">
			                                                 <option value="-999">请选择服务器</option>
			                                            </select>
											    	</td>
											    	<td class="tb1Td"><span class="span">邮箱地址：</span></td>
									    			<td><input id="email" class="input"></td>
											    	<td class="tb1Td"><span class="span">充值金额：</span></td>
									    			<td><span class="span"><input id="amountStart" class="input" onKeypress="return numberInput(event);" onkeyup="value=numberCheck(value);" onblur="value=checkAmount(true);"></td>
									    			<td>&nbsp;-&nbsp;</td>
									    			<td><input id="amountEnd" onKeypress="return numberInput(event);" onkeyup="value=numberCheck(value);" class="input" onblur="value=checkAmount(false);"></span></td>
													<td colspan="2" style="line-height:1;">
														<button type="button" id="export" class="btn btn-primary btn-sm" onclick="exportToExcel();"
															<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:124px"></i>导出为Excel
														</button>
													</td>
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
															<span>游戏充值订单金额(${currencyUnit}):
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
																		<th style='min-width:50px;'>渠道号</th>
																		<th style='min-width:65px;'>渠道名称</th>
																		<th>用户名</th>
																		<th>用户昵称</th>
																		<th>平台</th>
																		<th style='min-width: 60px;'>游戏</th>
																		<th style='min-width: 60px;'>服务器</th>
																		<th>手机号</th>
																		<th>邮箱地址</th>
																		<th>注册时间<img id='regTimeOrder' class='order' src='${path}/img/arrow_disable.png' onclick='orderchange(this);' lang='0'></img></th>
																		<th>充值金额（${currencyUnit}）<img id='amountOrder' class='order' src='${path}/img/arrow_down.png' onclick='orderchange(this);' lang='1'></img></th>
																	</tr>
																</thead>
																<tbody id="data">
																</tbody>
															</table>
														</div>
														<div>
															<table class="tablePage">
																<tr>
																	<td><div class="divPage"><span class="spanPageSize">每页个数：</span><input id="pageSize" value="10" class="inputPageSize" onKeypress="return intInput(event);" onKeyup="value=pageSizeLimit(value);" onblur="value=pageSizeNotEmpty(value);"/></div></td>
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
				setPlatformInfos();
				search();
				bindEnterEventOfAllEle();
				setDateRangeConfig("regTimeStart","regTimeEnd",null,true);
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("user",search);
				bindEnterEvent("phone",search);
				bindEnterEvent("email",search);
				bindEnterEvent("amountStart",search);
				bindEnterEvent("amountEnd",search);
				bindEnterEvent("regTimeStart",search);
				bindEnterEvent("regTimeEnd",search);
				bindEnterEvent("pageSize",search);
				bindEnterEvent("channelId",search);
				bindEnterEvent("channelName",search);
			}
	   		var pageSize = 10;
			var addTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>channelId</td><td>channelName</td><td>userId</td><td>nickName</td><td>platformTerminalName</td><td>platformName</td><td>serverName</td><td>phone</td><td>email</td><td>regTime</td><td>totalAmount</td></tr>";
					ele = ele.replace("channelId",data.channelId).replace("channelName",data.channelName).replace("userId",data.userId).replace("nickName",data.nickName).replace("platformTerminalName",data.platformTerminalName).replace("platformName",data.platformName).replace("serverName",data.serverName).replace("phone",data.phone).replace("email",data.email).replace("regTime",data.regTime).replace("totalAmount",data.totalAmount);
					$("#data").append(ele);
				}
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
			
			//保留两位小数
			var getTwoFixedDouble = function(value){
				return (value*100).toFixed(2);
			}
			
			//设置类别选择数据
	    	var setPlatformInfos = function(){
	    		var optionstring = "<option value=\"-999\" >请选择游戏</option>";
		    	$.post("${path}/game/operative/userrechargesum/platforminfos.do",{
		    		countryId:$("#countryId").val(),
		    		platformTerminal:$("#platformTerminal").val()
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json && ""!=json){
						for(var i=0;i < json.length;i++){
	                        optionstring += "<option value=\"" + json[i].id + "\"title=" + json[i].name + "\">" + json[i].name + "</option>";  
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
		    	$.post("${path}/game/operative/userrechargesum/gameservers.do",{
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
				$.post("${path}/game/operative/userrechargesum/list.do",{
					pageNumber:pageNumber,
					pageSize:pageSize,
					user:$("#user").val(),
					phone:$("#phone").val(),
					email:$("#email").val(),
					amountStart:$("#amountStart").val(),
					amountEnd:$("#amountEnd").val(),
					countryId:$("#countryId").val(),
					platformId:$("#platformId").val()!=-999?$("#platformId").val():null,
					regTimeStart:$("#regTimeStart").val(),
				    regTimeEnd:$("#regTimeEnd").val(),
				    serverId:$("#serverId").val()!=-999?$("#serverId").val():null,
				    channelId:$("#channelId").val(),
					channelName:$("#channelName").val(),
				    regTimeOrder:$("#regTimeOrder").attr("lang"),
				    amountOrder:$("#amountOrder").attr("lang"),
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
							$("#data").append("<tr><td colspan=\"11\">没有数据</td></tr>");
						}else{
							var sumAmount = 0;
							for(var i=0;i < infoData.length;i++){
								sumAmount = accAdd(sumAmount,infoData[i].totalAmount);
								var ele = {
										channelId:infoData[i].channelId,
										channelName:infoData[i].channelName,
										userId:infoData[i].userId,
										nickName:infoData[i].nickName,
										phone:infoData[i].phone,
										email:infoData[i].email,
										regTime:infoData[i].regTime,
										totalAmount:infoData[i].totalAmount.toFixed(2),
										countryName:infoData[i].countryName,
										platformName:infoData[i].platformName,
										serverName:infoData[i].serverName,
										platformTerminalName:infoData[i].platformTerminalName
								}
								addTbRow(ele);
							}
							var ele1 = {
									userId:"",
									nickName:"",
									phone:"",
									email:"",
									regTime:"累计",
									totalAmount:sumAmount.toFixed(2),
									countryName:"",
									platformName:"",
									serverName:"",
									channelId:"",
									channelName:"",
									platformTerminalName:""
							}
							addTbRow(ele1);
						}
					}else{
						$("#totalCount").html(0);
						$("#totalPage").html(0);
						$("#data").append("<tr><td colspan=\"11\">没有数据</td></tr>");
					}
				});
			}
			
			var exportToExcel = function(){
				var index = layer.load();
				$.ajax({
				    url: "${path}/game/operative/userrechargesum/export.do",
				    type: "POST",
				    data: {
						user:$("#user").val(),
						phone:$("#phone").val(),
						email:$("#email").val(),
						amountStart:$("#amountStart").val(),
						amountEnd:$("#amountEnd").val(),
						countryId:$("#countryId").val(),
						platformId:$("#platformId").val()!=-999?$("#platformId").val():null,
						regTimeStart:$("#regTimeStart").val(),
					    regTimeEnd:$("#regTimeEnd").val(),
					    serverId:$("#serverId").val()!=-999?$("#serverId").val():null,
					    channelId:$("#channelId").val(),
					    channelName:$("#channelName").val(),
					    regTimeOrder:$("#regTimeOrder").attr("lang"),
					    amountOrder:$("#amountOrder").attr("lang"),
					    platformTerminal:$("#platformTerminal").val()!=-999?$("#platformTerminal").val():null,
					},
				    success:function(response, status, request){
				    	layer.close(index);
				    	if(!response.canExport){
				    		layer.msg("当前查询条件数据量过大，请缩小查询条件再导出！", {icon: 5}); 
				    		return;
				    	}
				        window.location = "${path}/game/operative/userrechargesum/download.do?uuid=" + response.uuid;
			    }});
			}

			//重置
			var reset = function(){
				$("#user").val("");
				$("#phone").val("");
				$("#email").val("");
				$("#amountStart").val("");
				$("#amountEnd").val("");
				$("#platformId").val("-999");
				$("#serverId").val("-999");
				$("#regTimeStart").val("");
				$("#regTimeEnd").val("");
				$("#channelId").val("");
				$("#channelName").val("");
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
