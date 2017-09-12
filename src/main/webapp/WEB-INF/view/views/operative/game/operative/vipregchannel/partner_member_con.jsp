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
				<div class="whiteDiv tab-content">
					<div>
						<ul id="sortable" class="ui-widget-content sortable">
							<li class="ui-state-default">
								<div class="ui-widget-content resize">
									<div class="sortHandle div-sum">
									</div>
									<div class="tablePanel">
										<table class="tableList1">
											<thead>
												<tr>
													<th>代理推广编码</th>
													<th>代理账号</th>
													<th>代理姓名</th>
												</tr>
											</thead>
											<tbody id="data">
												<tr>
													<td>${partnerInfo.partnerNo}</td>
													<td>${partnerInfo.partnerId}</td>
													<td>${partnerInfo.partnerName}</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</li>
						</ul>
					</div>
			</div>
	</div>
</body>
</html>
