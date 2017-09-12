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
    
    function update(id){
    	$.post("<c:url value="/ybfurlcfg/isExist.do"/>", {
			tblId : id
		}, function(data) {
			if (true == data) {
				urlSubmit("<%=request.getContextPath()%>/ybfurlcfg/toUpdate.do?tblId=" + id);
			} else {
				layer.confirm("该记录已不存在！", {
					btn : [ "确认"] //可以无限个按钮
				}, function(index, layero) {
					urlSubmit("<%=request.getContextPath()%>/ybfurlcfg/list.do");
				});
			}
		});
    }
    
    function del(id) {
		layer.confirm("是否要刪除该URL配置？", {
				btn : [ "确认", "取消"] //可以无限个按钮
			}, function(index, layero) {
					$.post("<c:url value="/ybfurlcfg/deleteById.do"/>", {
						tblId : id
					}, function(data) {
						if ("Y" == data) {
							layer.alert("删除成功", {
								icon : 6
							});
							urlSubmit("<%=request.getContextPath()%>/ybfurlcfg/list.do");
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
    		  content: "<%=request.getContextPath()%>/ybfurlcfg/toAdd.do"
    		});
    }
    
    
    function clearSearch() {
    	jQuery("#urlName").attr("value","");
    	jQuery("#urlId").attr("value","");
    	jQuery("#domainName").attr("value","");
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
						aria-controls="collapseOne" class=""> URL配置 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				<div class="panel-body">
					<form name="form" class="form-inline"
						action="<c:url value="/ybfurlcfg/list.do"/>" method="post">
						<fieldset>
							<div class="form-group form-group-sm right20">
								<label class="control-label" for="urlName">URL名称：</label> <input
									class="form-control" type="text" id="urlName" name="urlName"
									value="${condition.urlName}">
							</div>
							<div class="form-group form-group-sm right20">
								<label class="control-label" for="urlId">URL路径：</label> <input
									class="form-control" type="text" id="urlId" name="urlId"
									value="${condition.urlId}">
							</div>
							<div class="form-group form-group-sm right20">
								<label class="control-label" for="domainName">域名：</label> <input
									class="form-control" type="text" id="domainName" name="domainName"
									value="${condition.domainName}">
							</div>
							<div class="form-group right20">
								<shiro:hasPermission name="admin_admin_list">
									<c:if test="${not empty condition.urlName||not empty condition.urlId||not empty condition.domainName}"><input type="button" class="btn btn-primary btn-sm" onclick="clearSearch()" value='&nbsp;清&nbsp;&nbsp;空&nbsp;'>
										<i class="icon-search icon-white"></i>
									</input>&nbsp;&nbsp;</c:if>
									<button type="submit" class="btn btn-primary btn-sm">
										<i class="icon-search icon-white"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
									</button>&nbsp;&nbsp;
                                </shiro:hasPermission>
								<shiro:hasPermission name="admin_admin_add">
									<button type="submit" class="btn btn-primary btn-sm"
										onclick="urlSubmit('toAdd.do')">
										<i class="icon-plus icon-white"></i>新增
									</button>
								</shiro:hasPermission>
							</div>
						</fieldset>
					</form>
					<table class="table table-striped table-bordered top20">
						<thead>
							<tr>
								<th style="display:none;">tblId</th>
								<th>URL名称</th>
								<th>URL路径</th>
								<th>域名</th>
								<th>创建人</th>
								<th>创建时间</th>
								<th>修改人</th>
								<th>修改时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${null != dimYbfUrlCfgList}">
								<c:forEach var="item" items="${dimYbfUrlCfgList}">
									<tr>
										<td class="center" style="display:none;">${item.tblId}</td>
										<td class="center">${item.urlName}</td>
										<td class="center">${item.urlId}</td>
										<td class="center">${item.domainName}</td>
										<td class="center">${item.createdUser}</td>
										<td class="center"><fmt:formatDate
												value="${item.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td class="center">${item.updatedUser}</td>
										<td class="center"><fmt:formatDate
												value="${item.updatedDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><shiro:hasPermission name="admin_admin_edit">
												<button type="submit" class="btn btn-primary btn-xs"
													onclick="update('${item.tblId}')">
													<i class="icon-edit icon-white"></i>编辑
												</button>
											</shiro:hasPermission>
											<shiro:hasPermission name="admin_admin_delete">
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <button type="submit"
														class="btn btn-danger btn-xs"
														onclick="del('${item.tblId}')">
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
