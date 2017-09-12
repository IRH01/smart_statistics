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
				<input type="hidden" id="partnerId" value='${partnerId}'/>
				<input type="hidden" id="partnerName" value='${partnerName}'/>
				<input type="hidden" id="statMonth" value='${statMonth}'/>
				<input type="hidden" id="gameTypeId" value='${gameTypeId}'/>
				<div class="titleDiv" style="height:25px;">
				    <div class="select-fr" style="padding-right: 30px;float:left;">
				    	<table style="margin-left:5px;">
				    		<tr>
				    			<td class="tb1Td"><span class="span">会员：</span></td>
				    			<td><input id="user" class="input"></td>
				    			<td  style="line-height:1;">
									<button type="button" id="search" class="btn btn-primary btn-sm" onclick="search();"
										<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>查询
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
										<span>充值总额(元)：
											<span>
												<c:choose>
													<c:when test="${not empty sum}"><fmt:formatNumber type="number" value="${sum.totalRechargeAmount}" pattern="#0.00"/></c:when>
       												<c:otherwise>0</c:otherwise>
												</c:choose>
											</span>
										</span>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;有效总额(元)：
											<span>
												<c:choose>
													<c:when test="${not empty sum}"><fmt:formatNumber type="number" value="${sum.totalBetAmount}" pattern="#0.00"/></c:when>
       												<c:otherwise>0</c:otherwise>
												</c:choose>
											</span>
										</span>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;输赢总额(元)：
											<span>
												<c:choose>
													<c:when test="${not empty sum}"><fmt:formatNumber type="number" value="${sum.totalLosingAmount}" pattern="#0.00"/></c:when>
       												<c:otherwise>0</c:otherwise>
												</c:choose>
											</span>
										</span>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;礼物总额(元)：
											<span>
												<c:choose>
													<c:when test="${not empty sum}"><fmt:formatNumber type="number" value="${sum.totalGiftAmount}" pattern="#0.00"/></c:when>
       												<c:otherwise>0</c:otherwise>
												</c:choose>
											</span>
										</span>
									</div>
									<div class="tablePanel">
										<table class="tableList1">
											<thead>
												<tr>
													<th>应用</th>
													<th>代理姓名</th>
													<th>会员账号</th>
													<th>会员用户名</th>
													<th>手机</th>
													<th>邮箱</th>
													<th>充值金额</th>
													<th>有效金额</th>
													<th>输赢金额</th>
													<th>礼物金额</th>
													<th>注册时间</th>
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
			bindEnterEvent("user",search);
			$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
		});
   		var pageSize = 10;
		var addTbRow = function(data){
			if(null != data && undefined != data && "" != data){
					var ele="<tr><td>gameTypeName</td><td>partnerName</td><td>userId</td><td>nickName</td><td>phone</td><td>email</td><td>rechargeAmount</td><td>totalBetAmount</td><td>totalLosingAmount</td><td>totalGiftAmount</td><td>createTime</td></tr>";
					ele = ele.replace("gameTypeName",data.gameTypeName).replace("partnerName",data.partnerName).replace("userId",data.userId).replace("nickName",data.nickName).replace("phone",data.phone).replace("email",data.email).replace("rechargeAmount",data.rechargeAmount).replace("totalBetAmount",data.totalBetAmount).replace("totalLosingAmount",data.totalLosingAmount).replace("totalGiftAmount",data.totalGiftAmount).replace("createTime",data.createTime);
					$("#data").append(ele);
			}
		}
		
		//显示统计列表
		var showData = function(pageNumber,pageSize){
			$("#data").empty();
			$.post("${path}/partner/qlfmbreport/list.do",{
				pageNumber:pageNumber,
				pageSize:pageSize,
				partnerId:$("#partnerId").val(),
				statMonth:$("#statMonth").val(),
				gameTypeId:$("#gameTypeId").val(),
				user:$("#user").val()
			},function(data){
				var json = JSON.parse(data);
				if(null != json && undefined != json){
					$("#totalCount").html(json.page.total);
					$("#totalPage").html(json.page.pages);
					$("#page").myPagination({
				          currPage: pageNumber,
				          pageCount: json.page.pages,
				          ajax:{on:false,  
			                    onClick:function(page){
			                    	showData(page,pageSize);
			                    }  
			        		}
				        });
					var infoData = json.qualifiedMemberInfos;
					if(null == infoData || undefined == infoData || 0 >= infoData.length){
						$("#data").append("<tr><td colspan=\"11\">没有数据</td></tr>");
					}else{
						var rechargeAmountSum = 0;
						var totalBetAmountSum = 0;
						var totalLosingAmountSum = 0;
						var totalGiftAmountSum = 0;
						for(var i=0;i < infoData.length;i++){
							rechargeAmountSum = accAdd(rechargeAmountSum,infoData[i].rechargeAmount);
							totalBetAmountSum = accAdd(totalBetAmountSum,infoData[i].totalBetAmount);
							totalLosingAmountSum = accAdd(totalLosingAmountSum,infoData[i].totalLosingAmount);
							totalGiftAmountSum = accAdd(totalGiftAmountSum,infoData[i].totalGiftAmount);
							var ele = {
									gameTypeName:infoData[i].gameTypeName,
									partnerName:$("#partnerName").val(),
									userId:infoData[i].userId,
									nickName:infoData[i].nickName,
									phone:infoData[i].phone,
									email:infoData[i].email,
									rechargeAmount:infoData[i].rechargeAmount.toFixed(2),
									totalBetAmount:infoData[i].totalBetAmount.toFixed(2),
									totalLosingAmount:infoData[i].totalLosingAmount.toFixed(2),
									totalGiftAmount:infoData[i].totalGiftAmount.toFixed(2),
									createTime:infoData[i].createTime
							}
							addTbRow(ele);
						}
						var ele1 = {
								gameTypeName:"",
								partnerName:"",
								userId:"",
								nickName:"",
								phone:"",
								email:"总计",
								rechargeAmount:rechargeAmountSum.toFixed(2),
								totalBetAmount:totalBetAmountSum.toFixed(2),
								totalLosingAmount:totalLosingAmountSum.toFixed(2),
								totalGiftAmount:totalGiftAmountSum.toFixed(2),
								createTime:""
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
		

		//重置
		var reset = function(){
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
