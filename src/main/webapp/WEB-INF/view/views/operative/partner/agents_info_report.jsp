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
				<input type="hidden" id="userId" value='${userId}'/>
				<div class="whiteDiv tab-content">
					<div>
						<ul id="sortable" class="ui-widget-content sortable">
							<li class="ui-state-default">
								<div style="padding-bottom:35px;" class="ui-widget-content resize">
									<div class="tablePanel">
										<table class="tableList1">
											<thead>
												<tr>
													<th>推广编码</th>
													<th>代理姓名</th>
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
			$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
		});
   		var pageSize = 10;
		var addTbRow = function(data){
			if(null != data && undefined != data && "" != data){
					var ele="<tr><td>partnerNo</td><td>realName</td><td>registerDate</td></tr>";
					ele = ele.replace("partnerNo",data.partnerNo).replace("realName",data.realName).replace("registerDate",data.registerDate);
					$("#data").append(ele);
			}
		}
		
		//显示统计列表
		var showData = function(pageNumber,pageSize){
			$("#data").empty();
			$.post("${path}/partner/agentsinforeport/list.do",{
				pageNumber:pageNumber,
				pageSize:pageSize,
				userId:$("#userId").val()
			},function(data){
				var json = JSON.parse(data);
				if(null != json && undefined != json){
					$("#totalCount").html(json.pager.totalRows);
					$("#totalPage").html(json.pager.totalPages);
					$("#page").myPagination({
				          currPage: pageNumber,
				          pageCount: json.pager.totalPages,
				          ajax:{on:false,  
			                    onClick:function(page){
			                    	showData(page,pageSize);
			                    }  
			        		}
				        });
					var infoData = json.pager.list;
					if(null == infoData || undefined == infoData || 0 >= infoData.length){
						$("#data").append("<tr><td colspan=\"3\">没有数据</td></tr>");
					}else{
						for(var i=0;i < infoData.length;i++){
							var ele = {
									partnerNo:infoData[i].partnerNo,
									realName:infoData[i].realName,
									registerDate:formatDate(infoData[i].registerDate)
							}
							addTbRow(ele);
						}
					}
				}else{
					$("#totalCount").html(0);
					$("#totalPage").html(0);
					$("#data").append("<tr><td colspan=\"3\">没有数据</td></tr>");
				}
			});
		}
		
		//查询显示
		var search = function(){
			pageSize = $("#pageSize").val() * 1;
			showData(1,pageSize);
		}
	</script>
</body>
</html>
