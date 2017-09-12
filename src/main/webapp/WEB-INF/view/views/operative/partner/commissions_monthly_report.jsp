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
    minWidth:300px;
    minHeight:200px;
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
				<input type="hidden" id="userId" value='${userId}'/>
				<div class="titleDiv" style="height:25px;">
				    <div style="float:left;">
				    	<table style="margin-left:5px;">
				    		<tr>
				    			<td class="tb1Td">
				    			<span class="laydateBox"> &nbsp;&nbsp;月份：<input type="text" id="statMonth" class="laydate-icon" style="width:140px;" readonly/>
								</span></td>
				    			<td  style="line-height:1;">
									<button type="button" id="search" class="btn btn-primary btn-sm" onclick="search();"
										<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>查询
									</button>
								<td/>
								<td  style="line-height:1;">
									<button type="button" id="reset" class="btn btn-primary btn-sm" onclick="reset();"
										<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>重置
									</button>
								<td/>
				    		</tr>
				    	</table>
					</div>
				</div>
				<div class="whiteDiv tab-content">
						<ul id="sortable" class="ui-widget-content sortable">
							<li class="ui-state-default">
								<div style="padding-bottom:35px;" class="ui-widget-content resize">
									<div class="sortHandle div-sum">
										<span>总直属会员佣金(元)：
											<span id="gameRcgSummaryAmount">
												<c:choose>
													<c:when test="${not empty partnerCmsInfo}"><fmt:formatNumber type="number" value="${partnerCmsInfo.directMemberCommissions}" pattern="#0.00"/></c:when>
       												<c:otherwise>0</c:otherwise>
												</c:choose>
											</span>
										</span>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;总发展代理佣金(元)：
											<span id="lybRcgSummaryAmount">
												<c:choose>
													<c:when test="${not empty partnerCmsInfo}"><fmt:formatNumber type="number" value="${partnerCmsInfo.agentCommissions}" pattern="#0.00"/></c:when>
       												<c:otherwise>0</c:otherwise>
												</c:choose>
											</span>
										</span>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;总佣金(元)：
											<span id="remainLybSummaryAmount">
												<c:choose>
													<c:when test="${not empty partnerCmsInfo}"><fmt:formatNumber type="number" value="${partnerCmsInfo.totalCommissions}" pattern="#0.00"/></c:when>
       												<c:otherwise>0</c:otherwise>
												</c:choose>
											</span>
										</span>
									</div>
									<div class="tablePanel">
										<table class="tableList1">
											<thead>
												<tr>
													<th>月份</th>
													<th>代理编码</th>
													<th>代理姓名</th>
													<th>直属会员佣金</th>
													<th>发展代理佣金</th>
													<th>合计</th>
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
	<script type="text/javascript">
		
		var setMonthConfig = function(){
			var date=new Date();
			var year=date.getFullYear(); 
			var years = new Array();
			var startYear = 2016;
			for(var i = 0;year >= startYear;year--,i++){
				years[i] = year;
			}
			$("#statMonth").monthpicker({ 
				years: years, 
				topOffset: 6, 
				width:"140px",
				onMonthSelect: function(m, y) { 
					search();//查询
					console.log('Month: ' + m + ', year: ' + y); 
			 }}); 
		}
		$(function(){
			setMonthConfig();
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
					var ele="<tr><td class='month'>statMonth</td><td>partnerNo</td><td>realName</td><td><a onclick='showMbCms(this)' class='partner-link'>directMemberCommissions</a></td><td onclick='showAgentCms(this)' class='partner-link'>agentCommissions</td><td>totalCommissions</td></tr>";
					ele = ele.replace("statMonth",data.statMonth).replace("partnerNo",data.partnerNo).replace("realName",data.realName).replace("directMemberCommissions",data.directMemberCommissions).replace("agentCommissions",data.agentCommissions).replace("totalCommissions",data.totalCommissions);
					$("#data").append(ele);
				}else{
					var ele="<tr><td>statMonth</td><td style='color:#0066ff;'>partnerNo</td><td>realName</td><td>directMemberCommissions</td><td>agentCommissions</td><td>totalCommissions</td></tr>";
					ele = ele.replace("statMonth",data.statMonth).replace("partnerNo",data.partnerNo).replace("realName",data.realName).replace("directMemberCommissions",data.directMemberCommissions).replace("agentCommissions",data.agentCommissions).replace("totalCommissions",data.totalCommissions);
					$("#data").append(ele);
				}
			}
		}
		
		//显示直属会员佣金报表
		var showMbCms = function(ele){
			var parentObj = $(ele).parent().parent();
			var statMonthEle = $(parentObj).children(".month")[0];
			var statMonth = $(statMonthEle).text();
			var userId =  $("#userId").val();
			layer.open({
                type: 2,
                title: "直属会员佣金",
                maxmin: true,
                resize:true,
                shadeClose: false, //点击遮罩关闭层
                moveOut:true,
                scrollbar:true,
                area : ["1000px" , "500px"],
                content: "${path}/partner/mbcmsreport/show.do?userId=" + userId + "&statMonth=" + statMonth
            });
		}
		
		//显示直属会员佣金报表
		var showAgentCms = function(ele){
			var parentObj = $(ele).parent().parent();
			var statMonthEle = $(parentObj).children(".month")[0];
			var statMonth = $(statMonthEle).text();
			var userId =  $("#userId").val();
			layer.open({
                type: 2,
                title: "代理佣金",
                maxmin: true,
                resize:true,
                shadeClose: false, //点击遮罩关闭层
                moveOut:true,
                scrollbar:true,
                area : ["900px" , "400px"],
                content: "${path}/partner/agentcmsreport/show.do?userId=" + userId + "&statMonth=" + statMonth
            });
		}
		
		
		
		//显示统计列表
		var showData = function(pageNumber,pageSize){
			$("#data").empty();
			$.post("${path}/partner/cmsmreport/list.do",{
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
					var infoData = json.commissionsSumMList;
					if(null == infoData || undefined == infoData || 0 >= infoData.length){
						$("#data").append("<tr><td colspan=\"10\">没有数据</td></tr>");
					}else{
						var directMemberCommissionsSum = 0;
						var agentCommissionsSum = 0;
						var totalCommissionsSum = 0;
						for(var i=0;i < infoData.length;i++){
							directMemberCommissionsSum = accAdd(directMemberCommissionsSum,infoData[i].directMemberCommissions);
							agentCommissionsSum = accAdd(agentCommissionsSum,infoData[i].agentCommissions);
							totalCommissionsSum = accAdd(totalCommissionsSum,infoData[i].totalCommissions);
							var ele = {
									statMonth:infoData[i].statMonthYYYYMM,
									partnerNo:infoData[i].partnerNo,
									realName:infoData[i].realName,
									directMemberCommissions:infoData[i].directMemberCommissions.toFixed(2),
									agentCommissions:infoData[i].agentCommissions.toFixed(2),
									totalCommissions:infoData[i].totalCommissions.toFixed(2)
							}
							addTbRow(ele);
						}
						var ele1 = {
								statMonth:"",
								partnerNo:"",
								realName:"累计",
								directMemberCommissions:directMemberCommissionsSum.toFixed(2),
								agentCommissions:agentCommissionsSum.toFixed(2),
								totalCommissions:totalCommissionsSum.toFixed(2)
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
			$("#statMonth").attr("value","");
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
