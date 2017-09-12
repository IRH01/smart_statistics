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
				<input type="hidden" id="countryId" value="${countryId}">
				<input type="hidden" id="channel" value='${channel}'/>
				<input type="hidden" id="createTimeStart" value='${createTimeStart}'/>
				<input type="hidden" id="createTimeEnd" value='${createTimeEnd}'/>
				<div class="titleDiv" style="height:60px;">
					<div style="display: inline;width:500px;text-align: center;"></div>
				    <div class="select-fr" style="padding-right: 30px;float:left;">
				    	<table style="margin-left:5px;">
				    		<tr>
				    			<td class="tb1Td"><span class="span">注册来源：</span></td>
				    			<td><input id="platformName" class="input" style="width:165px;"></td>
								<td class="tb1Td"><span class="span" style="width:80px;">首充金额：</span></td>
				    			<td><span class="span"><input id="payFirstAmountStart" style="width:100px;" class="input-amount" onKeypress="return numberInput(event);" onkeyup="value=numberCheck(value);" onblur="value=checkAmount(true);"></td>
				    			<td>-</td>
				    			<td><input id="payFirstAmountEnd" style="width:100px;" onKeypress="return numberInput(event);" onkeyup="value=numberCheck(value);" class="input-amount" onblur="value=checkAmount(false);"></span></td>
								<td class="tb1Td">
					    				<span class="span">首充项目：</span>
					    		</td>
				    			<td>
			    					<select id="platformId"  class="select">
                                 		<option value="-999">请选择</option>
                                        <c:forEach items="${platformInfos}" var="platformInfo">
                                        	<option value="${platformInfo.id}" title="${platformInfo.name}">${platformInfo.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
								<td  style="line-height:1;">
									<button type="button" id="search" class="btn btn-primary btn-sm" onclick="search();"
										<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>查询
									</button>
								<td/>
							</tr>
							<tr>
								<td class="tb1Td"><span class="span">会员：</span></td>
				    			<td><input id="member" class="input" placeholder="支持昵称/账号/手机/邮箱查询" style="width:165px;"></td>
                                <td class="tb1Td">
				    				<span class="span">首充时间：
									</span>
								</td>
								<td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;width:100px;" id="payDateStart" readonly> </td>
								<td>
									<span class="span">&nbsp;-&nbsp;</span>
								</td>
								<td><input class="laydate-icon" id="payDateEnd" style="height:25px;width:100px;" placeholder="请选择日期" readonly></td>
								<td></td>
								<td></td>
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
													<th style="min-width:60px;">注册来源</th>
													<th>登录类型</th>
													<th>用户昵称</th>
													<th>账号</th>
													<th>手机</th>
													<th>邮箱</th>
													<th>注册时间</th>
													<th>首充金额(RMB)</th>
													<th>首充项目</th>
													<th>首充时间</th>
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
	<script type="text/javascript">
		
		$(function(){
			setDateRangeConfig("payDateStart","payDateEnd",null,true);
			search();
			bindEnterEventOfAllEle();
			$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
		});
		var bindEnterEventOfAllEle = function(){
			bindEnterEvent("member",search);
			bindEnterEvent("platformName",search);
			bindEnterEvent("payFirstAmountStart",search);
			bindEnterEvent("payFirstAmountEnd",search);
		}
   		var pageSize = 10;
		var addTbRow = function(data){
			if(null != data && undefined != data && "" != data){
				var ele="<tr><td>platformName</td><td>loginType</td><td>nickName</td><td class='user'>userId</td><td>phone</td><td>email</td><td>createTime</td><td>payFirstAmount</td><td>payPlatformName</td><td>payDate</td><td>payAmount</td></tr>";
				ele = ele.replace("platformName",data.platformName).replace("loginType",data.loginType).replace("nickName",data.nickName).replace("userId",data.userId).replace("phone",data.phone).replace("email",data.email).replace("createTime",data.createTime).replace("payFirstAmount",data.payFirstAmount).replace("payPlatformName",data.payPlatformName).replace("payDate",data.payDate).replace("payAmount",data.payAmount);
				$("#data").append(ele);
			}
		}
		
	
// 		var showPartnerInfo = function(ele){
// 			debugger
// 			var parentObj = $(ele).parent().parent();
// 			var userIdEle = $(parentObj).children(".user")[0];
// 			layer.open({
//                 type: 2,
//                 title: "代理信息",
//                 maxmin: true,
//                 resize:true,
//                 shadeClose: false, //点击遮罩关闭层
//                 moveOut:true,
//                 scrollbar:true,
//                 area : ["800px" , "150px"],
//                 scrollbar:true,
//                 content: "${path}/game/operative/vipregchannel/showpartnerinfo.do?memberId=" + $(userIdEle).text()
//             });
// 		}
		
		//显示统计列表
		var showData = function(pageNumber,pageSize){
			$("#data").empty();
			$.post("${path}/game/operative/vipregchannel/listdetail.do",{
				channel:$("#channel").val(),
				createTimeStart:$("#createTimeStart").val(),
				createTimeEnd:$("#createTimeEnd").val(),
				platformName:$("#platformName").val(),
				payDateStart:$("#payDateStart").val(),
				payDateEnd:$("#payDateEnd").val(),
				payFirstAmountStart:$("#payFirstAmountStart").val(),
				payFirstAmountEnd:$("#payFirstAmountEnd").val(),
				payPlatformId:$("#platformId").val()!=-999?$("#platformId").val():null,
				name:$("#member").val(),
				countryId:$("#countryId").val(),
// 				isVip:$("#isVip").val()!=-999?$("#isVip").val():null,
				pageNumber:pageNumber,
				pageSize:pageSize,
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
									userId:infoData[i].userId,
									platformName:infoData[i].platformName,
									loginType:convertLoginType(infoData[i].loginType),
									nickName:infoData[i].nickName,
									userId:infoData[i].userId,
									phone:infoData[i].phone,
									email:infoData[i].email,
									createTime:formatDate(infoData[i].createTime),
									payFirstAmount:infoData[i].payFirstAmount.toFixed(2),
									payPlatformName:infoData[i].payPlatformName,
									payDate:formatDate(infoData[i].payDate),
									payAmount:infoData[i].payAmount.toFixed(2),
									isVip:infoData[i].isVip
							}
							addTbRow(ele);
						}
					}
				}else{
					$("#data").append("<tr><td colspan=\"11\">没有数据</td></tr>");
				}
			});
		}
		
		var checkAmount = function(isMin){
			value = isMin ? $("#payFirstAmountStart").val() : $("#payFirstAmountEnd").val();
			if("" != $("#payFirstAmountEnd").val() && "" != $("#payFirstAmountStart").val()){
				if($("#payFirstAmountEnd").val() * 1 < $("#payFirstAmountStart").val() * 1){
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
		
		//登录类型显示转换
		var convertLoginType = function(value){
			if("0" == value){
				return "平台注册登录";
			}else if("1" == value){
				return "QQ注册登录";
			}else if("2" == value){
				return "新浪微博注册登录";
			}else if("3" == value){
				return "微信注册登录";
			}else if("4" == value){
				return "支付宝注册登录";
			}else if("5" == value){
				return "twitter注册登录";
			}else if("6" == value){
				return "Facebook注册登录";
			}else{
				return value;
			} 
		}
		
		var convertIsVip = function(value){
			if(0 == value){
				return "否";
			}else{
				return "是";
			}
		}
		
		//重置
		var reset = function(){
			$("#platformName").val("");
			$("#payDateStart").val("");
			$("#payDateEnd").val("");
			$("#payFirstAmountStart").val("");
			$("#payFirstAmountEnd").val("");
			$("#platformId").val(-999);
			$("#member").val("");
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
