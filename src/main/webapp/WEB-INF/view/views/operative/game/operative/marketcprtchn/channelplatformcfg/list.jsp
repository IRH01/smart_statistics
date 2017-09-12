<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script type="text/javascript">
	$.noConflict();
    function urlSubmit(url) {
        document.form.action = url;
        document.form.submit();
    }
  
    function del(id) {
		layer.confirm("是否要刪除该渠道游戏配置？", {
				btn : [ "确认", "取消"] //可以无限个按钮
			}, function(index, layero) {
					$.post("<c:url value="/game/operative/chnplfcfg/delete.do"/>", {
						channelId : id
					}, function(data) {
						if (0 == data.result) {
							layer.alert("删除成功", {
								icon : 6
							});
							urlSubmit("<%=request.getContextPath()%>/game/operative/chnplfcfg/list.do");
						} else {
							layer.alert("删除失败", {
								icon : 5
							});
						}
					});
			});
	}
    
    function add(){
    	layer.open({
    		  type: 2,
    		  area: ['700px', '530px'],
    		  fix: false, //不固定
    		  maxmin: true,
    		  content: "<%=request.getContextPath()%>/game/operative/chnplfcfg/toAdd.do"
    		});
    }
    
    
    function clearSearch() {
    	jQuery("#channelName").attr("value","");
    	jQuery("#channelId").attr("value","");
    	jQuery("#platformName").attr("value","");
    	jQuery("#platformId").attr("value","");
		document.form.action = 'list.do';
		document.form.submit();
	}
    
</script>
<div class="sdk-content">
	<ul class="breadcrumb">
		<li>您当前的位置：</li>
		<tags:breadcrumb />
		<li>列表</li>
	</ul>
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 渠道游戏管理 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				<div class="panel-body">
					<form name="form" class="form-inline"
						action="<c:url value="/game/operative/chnplfcfg/list.do"/>" method="post">
						<fieldset>
							<div class="form-group form-group-sm right20">
								<label class="control-label" for="channelId">渠道ID：</label> <input
									class="form-control" type="text" id="channelId" name="channelId"
									value="${condition.channelId}">
							</div>
							<div class="form-group form-group-sm right20">
								<label class="control-label" for="channelName">渠道名称：</label> <input
									class="form-control" type="text" id="channelName" name="channelName"
									value="${condition.channelName}">
							</div>
							<div class="form-group form-group-sm right20">
								<label class="control-label" for="platformId">游戏ID：</label> <input
									class="form-control" type="text" id="platformId" name="platformId"
									value="${condition.platformId}">
							</div>
							<div class="form-group form-group-sm right20">
								<label class="control-label" for="platformName">游戏名称：</label> <input
									class="form-control" type="text" id="platformName" name="platformName"
									value="${condition.platformName}">
							</div>
							<div class="form-group right20">
								<button type="submit" class="btn btn-primary btn-sm">
									<i class="icon-search icon-white"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
								</button>&nbsp;&nbsp;
								<input type="button" class="btn btn-primary btn-sm" onclick="clearSearch()" value='&nbsp;重&nbsp;&nbsp;置&nbsp;'>
									<i class="icon-search icon-white"></i>
								</input>&nbsp;&nbsp;
								<button type="submit" class="btn btn-primary btn-sm"
									onclick="urlSubmit('toAdd.do')">
									<i class="icon-plus icon-white"></i>新增
								</button>
							</div>
						</fieldset>
					</form>
					<table class="table table-striped table-bordered top20">
						<thead>
							<tr>
								<th>渠道ID</th>
								<th>渠道名称</th>
								<th>游戏ID</th>
								<th>对应推广游戏</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${null != channelPlatformList}">
								<c:forEach var="item" items="${channelPlatformList}">
									<tr>
										<td class="center">${item.channelId}</td>
										<td class="center">${item.channelName}</td>
										<td class="center">${item.platformId}</td>
										<td class="center">${item.platformName}</td>									
										<td>
											<shiro:hasPermission name="admin_admin_delete">
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <button type="submit"
														class="btn btn-danger btn-xs"
														onclick="del('${item.channelId}')">
														<i class="icon-off icon-white"></i>刪除
													</button>
											</shiro:hasPermission>
											</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					<div style="text-align: right">
						<tags:page page1="${page}" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
