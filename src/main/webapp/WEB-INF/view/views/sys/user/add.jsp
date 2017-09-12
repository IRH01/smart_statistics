<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2></h2>

			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i
					class="icon-chevron-up"></i></a> <a href="#"
					class="btn btn-close btn-round"><i class="icon-remove"></i></a>
			</div>
		</div>

		<div class="box-content">
			<h4>创建新用户：</h4>
			<hr />
			<form id="form" name="form" class="form-horizontal"
				action="<%=path%>/sys/user/save.do" method="post">
				<div class="control-group">
					<p class="clearfix">
						<label class="control-label" for="username" style="width: 80px">用户名：</label>
						<input name="username" type="text" id="username" />
					</p>
					<p class="clearfix" style="margin: 20px 0;">
						<label class="control-label" for="password" style="width: 80px">密码：</label>
						<input name="password" type="password" id="password" />
					</p>
					<p class="clearfix" style="margin: 20px 0;">
						<label class="control-label" for="password" style="width: 80px">确认密码：</label>
						<input name="confirmPassword" type="password" id="confirmPassword" />
					</p>
					<shiro:hasPermission name="sys_user_add">
						<button type="submit" class="btn btn-primary">保存</button>
					</shiro:hasPermission>
					<button type="button" class="btn btn-primary" onclick="history.go(-1)" >返回</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
    $().ready(function () {
        $("#form").validate({
            rules: {
                "username": {
                    required: true
                },
                "password": {
                    required: true
                },
                "confirmPassword": {
                    required: true,
                    equalTo: "#password"
                }
            },
            messages: {
                "username": {
                    required: "用户名不能为空!"
                },
                "password": {
                    required: "密码不能为空!"
                },
                "confirmPassword": {
                    required: "确认密码不能为空！",
                    equalTo: "确认密码必须与密码填写一致！"
                }
            },
            tooltip_options: {
                "username": {trigger: 'focus'},
                "password": {trigger: 'focus'}
            }
        });
    });

</script>
<!--/row-->