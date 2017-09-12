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
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin-trend.css" />
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
				<input type="hidden" id="userId" value='${userId}'/>
				<input type="hidden" id="statMonth" value='${statMonth}'/>
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
										<span>&nbsp;&nbsp;&nbsp;&nbsp;总佣金(元)：
											<span>
												<c:choose>
													<c:when test="${not empty sum}"><fmt:formatNumber type="number" value="${sum.totalCommissions}" pattern="#0.00"/></c:when>
       												<c:otherwise>0</c:otherwise>
												</c:choose>
											</span>
										</span>
									</div>
									<div class="tablePanel">
										<table class="tableList1">
											<thead>
												<tr>
													<th style="display:none;">gameTypeId</th>
													<th>应用</th>
													<th>合格人数</th>
													<th>等级</th>
													<th>充值金额</th>
													<th>有效金额</th>
													<th>输赢金额</th>
													<th>礼物金额</th>
													<th>结算金额</th>
													<th>返佣比例</th>
													<th>佣金</th>
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
			bindEnterEventOfAllEle();
			$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
		});
		var bindEnterEventOfAllEle = function(){
			bindEnterEvent("realName",search);
		}
   		var pageSize = 10;
		var addTbRow = function(data,isTotalRow){
			if(null != data && undefined != data && "" != data){
				if(true != isTotalRow){
					var ele="<tr><td class='gtId' style='display:none;'>gameTypeId</td><td>gameTypeName</td><td><a onclick='showQlfMbInfo(this)' class='partner-link'>qualifiedMemberCount</a></td><td>commissionsLevel</td><td>totalRechargeAmount</td><td>totalBetAmount</td><td>totalLosingAmount</td><td>totalGiftAmount</td><td>settleAmount</td><td>commissionsRate</td><td>commissions</td></tr>";
					ele = ele.replace("gameTypeId",data.gameTypeId).replace("gameTypeName",data.gameTypeName).replace("qualifiedMemberCount",data.qualifiedMemberCount).replace("commissionsLevel",data.commissionsLevel).replace("totalRechargeAmount",data.totalRechargeAmount).replace("totalBetAmount",data.totalBetAmount).replace("totalLosingAmount",data.totalLosingAmount).replace("totalGiftAmount",data.totalGiftAmount).replace("settleAmount",data.settleAmount).replace("commissionsRate",data.commissionsRate).replace("commissions",data.commissions);
					$("#data").append(ele);
				}else{
					var ele="<tr><td>gameTypeName</td><td>qualifiedMemberCount</td><td>commissionsLevel</td><td>totalRechargeAmount</td><td>totalBetAmount</td><td>totalLosingAmount</td><td>totalGiftAmount</td><td>settleAmount</td><td>commissionsRate</td><td>commissions</td></tr>";
					ele = ele.replace("gameTypeId",data.gameTypeId).replace("gameTypeName",data.gameTypeName).replace("qualifiedMemberCount",data.qualifiedMemberCount).replace("commissionsLevel",data.commissionsLevel).replace("totalRechargeAmount",data.totalRechargeAmount).replace("totalBetAmount",data.totalBetAmount).replace("totalLosingAmount",data.totalLosingAmount).replace("totalGiftAmount",data.totalGiftAmount).replace("settleAmount",data.settleAmount).replace("commissionsRate",data.commissionsRate).replace("commissions",data.commissions);
					$("#data").append(ele);
				}
			}
		}
		
		var showQlfMbInfo = function(ele){
			var parentObj = $(ele).parent().parent();
			var gameTypeIdEle = $(parentObj).children(".gtId")[0];
			var gameTypeId = $(gameTypeIdEle).text();
			
			
			var userId =  $("#userId").val();
			var statMonth =  $("#statMonth").val();
			layer.open({
                type: 2,
                title: "合格会员信息",
                maxmin: true,
                resize:true,
                moveOut:true,
                scrollbar:true,
                shadeClose: true, //点击遮罩关闭层
                skin: 'layui-layer-rim', //加上边框
                area : ["900px" , "300px"],
                content: "${path}/partner/qlfmbreport/show.do?userId=" + userId + "&statMonth=" + statMonth 
                		+ "&gameTypeId=" + gameTypeId
            });
		}
		
		
		
		//显示统计列表
		var showData = function(pageNumber,pageSize){
			$("#data").empty();
			$.post("${path}/partner/mbcmsreport/list.do",{
				pageNumber:pageNumber,
				pageSize:pageSize,
				userId:$("#userId").val(),
				statMonth:$("#statMonth").val()
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
					var infoData = json.memberCmsSumMList;
					if(null == infoData || undefined == infoData || 0 >= infoData.length){
						$("#data").append("<tr><td colspan=\"10\">没有数据</td></tr>");
					}else{
						var qualifiedMemberCountSum = 0;
						var totalRechargeAmountSum = 0;
						var totalBetAmountSum = 0;
						var totalLosingAmountSum = 0;
						var totalGiftAmountSum = 0;
						var settleAmountSum = 0;
						var commissionsSum = 0;
						for(var i=0;i < infoData.length;i++){
							qualifiedMemberCountSum = qualifiedMemberCountSum + infoData[i].qualifiedMemberCount;
							totalRechargeAmountSum = accAdd(totalRechargeAmountSum,infoData[i].totalRechargeAmount);
							totalBetAmountSum = accAdd(totalBetAmountSum,infoData[i].totalBetAmount);
							totalLosingAmountSum = accAdd(totalLosingAmountSum,infoData[i].totalLosingAmount);
							totalGiftAmountSum = accAdd(totalGiftAmountSum,infoData[i].totalGiftAmount);
							settleAmountSum = accAdd(settleAmountSum,infoData[i].settleAmount);
							commissionsSum = accAdd(commissionsSum,infoData[i].commissions);
							var ele = {
									gameTypeId:infoData[i].gameTypeId,
									gameTypeName:infoData[i].gameTypeName,
									qualifiedMemberCount:infoData[i].qualifiedMemberCount,
									commissionsLevel:infoData[i].commissionsLevel,
									totalRechargeAmount:infoData[i].totalRechargeAmount.toFixed(2),
									totalBetAmount:infoData[i].totalBetAmount.toFixed(2),
									totalLosingAmount:infoData[i].totalLosingAmount.toFixed(2),
									totalGiftAmount:infoData[i].totalGiftAmount.toFixed(2),
									settleAmount:infoData[i].settleAmount.toFixed(2),
									commissionsRate:convertToPercentFormat(infoData[i].commissionsRate),
									commissions:infoData[i].commissions.toFixed(2)
							}
							addTbRow(ele);
						}
						var ele1 = {
								gameTypeName:"总计",
								qualifiedMemberCount:qualifiedMemberCountSum,
								commissionsLevel:"-",
								totalRechargeAmount:totalRechargeAmountSum.toFixed(2),
								totalBetAmount:totalBetAmountSum.toFixed(2),
								totalLosingAmount:totalLosingAmountSum.toFixed(2),
								totalGiftAmount:totalGiftAmountSum.toFixed(2),
								settleAmount:settleAmountSum.toFixed(2),
								commissionsRate:"-",
								commissions:commissionsSum.toFixed(2)
						}
						addTbRow(ele1,true);
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
