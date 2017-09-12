<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<%
    String path = request.getContextPath();
%>
<script src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">
     function changeAction(url){
          document.form.action=url;
          document.form.submit();
     }

     function confirmAction(url){
         layer.confirm("确定删除角色？", {
				btn : [ "确认", "取消"] //可以无限个按钮
			}, function(index, layero) {
				 document.form.action=url;
             	document.form.submit();	
			});
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
						aria-controls="collapseOne" class="">角色管理 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				<div class="panel-body">
					<c:if test="${null != msg}">
						<div class="alert alert-info">
							<i class="icon-info-sign"></i>${msg}
						</div>
					</c:if>
					<form name="form" class="form-inline"
						action="<%=path%>/sys/role/list.do" method="post">
						<fieldset>
							<div class="form-group form-group-sm right20">
								<label class="control-label" for="name">角色名：</label> <input
									class="form-control" type="text" id="name" name="name"
									value="${role.name}">
							</div>
							<div class="form-group form-group-sm right20">
								<label class="control-label" for="id">角色编号：</label> <input
									class="form-control" type="text"
									onkeyup="this.value=this.value.replace(/\D/g,'')"
									onafterpaste="this.value=this.value.replace(/\D/g,'')" id="id"
									name="id" value="${role.id}">
							</div>
							<div class="form-group form-group-sm">
								<shiro:hasPermission name="sys_role_list">
									<button type="submit" class="btn btn-primary btn-sm">
										<i class="icon-search icon-white"></i>查&nbsp;&nbsp;询
									</button>&nbsp;&nbsp;
                                </shiro:hasPermission>
								<shiro:hasPermission name="sys_role_add">
									<button type="submit" class="btn btn-primary btn-sm"
										onclick="changeAction('<%=path%>/sys/role/add.do')">
										<i class="icon-plus icon-white"></i>创建角色
									</button>
								</shiro:hasPermission>
							</div>
						</fieldset>
					</form>
					<table class="table table-striped table-bordered top20">
						<thead>
							<tr>
								<th>Id</th>
								<th>角色名</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${null != roleList}">
								<c:forEach var="role" items="${roleList}">
									<tr>
										<td class="center">${role.id}</td>
										<td class="center">${role.name}</td>
										<td class="center"><fmt:formatDate
												value="${role.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td><shiro:hasPermission name="sys_role_delete">
												<button type="submit" class="btn btn-primary btn-xs"
													onclick="confirmAction('<%=path%>/sys/role/${role.id}/delete.do')">
													<i class="icon-remove icon-white"></i>删除
												</button>
											</shiro:hasPermission> <shiro:hasPermission name="sys_role_update">
												<button type="submit" class="btn btn-primary btn-xs"
													onclick="changeAction('<%=path%>/sys/role/${role.id}/preupdate.do')">
													<i class="icon-edit icon-white"></i>分配权限
												</button>
											</shiro:hasPermission></td>
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
<!--/span-->