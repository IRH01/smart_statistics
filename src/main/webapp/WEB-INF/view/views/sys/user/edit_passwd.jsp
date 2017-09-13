<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/template/taglib.jsp"%>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<div class="sdk-content">
	<ul class="breadcrumb">
		<li>您当前的位置：</li>
		<li>修改密码</li>
	</ul>
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 修改密码 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				<div class="panel-body">
					<form class="form-horizontal" name="form" id="form"
						action="<c:url value="/sys/user/modifyPasswd.do"/>" method="post">
						<input type="hidden" name="userId" id="userId"
							value="${session_user.userId}">
						<fieldset>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2">用户名:</label>
								<div class="col-sm-3">
									<span class="form-span">${session_user.username}</span>
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="password"><em
									class="red-star">*</em>原始密码:</label>
								<div class="col-sm-3">
									<input class="form-control" type="password" id="password"
										name="password">
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="newPassword"><em
									class="red-star">*</em>新密码:</label>
								<div class="col-sm-3">
									<input class="form-control" type="password" id="newPassword"
										name="newPassword">
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="confirmPassword"><em
									class="red-star">*</em>确认新密码:</label>
								<div class="col-sm-3">
									<input class="form-control" type="password"
										id="confirmPassword" name="confirmPassword">
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2"></label>
								<div class="col-sm-3">
									<button type="submit" class="btn btn-primary" onclick="return checkPassword();">保存</button>
									<button type="button" class="btn btn-primary" onclick="history.go(-1)" >返回</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
    $().ready(function () {
        $("#form").validate({
            rules: {
                password:{
                    required: true,
                    remote:{
                        url:"<c:url value="/sys/user/verifyPasswd.do"/>",
                        type:"post",
                        data:{
                            password: function(){
                                return $("#password").val()},
                            userId:$("#userId").val()
                        }
                    }
                },
                newPassword: {
                    required: true,
                    rangelength:[3,15]
                },
                confirmPassword: {
                    required: true,
                    rangelength:[3, 15],
                    equalTo:"#newPassword"
                }
            },
            messages: {
                password: {
                    required: "密码不能为空!",
                    remote:"密码输入错误！"
                },
                newPassword: {
                    required: "新密码不能为空!",
                    rangelength:"新密码必须在3-15个字符之间！"
                },
                confirmPassword: {
                    required: "确认密码不能为空！",
                    rangelength: "确认密码必须在3-15个字符之间！",
                    equalTo: "确认密码必须与新密码一致！"
                }
            }
        });
    });

    function checkPassword(){
    	var password = $("#password").val();
    	
    	var newPassword = $("#newPassword").val();
    	
    	if(password!=null&&password!=""&&password==newPassword){
    		layer.alert("新密码不能与旧密码一致，请重新输入", {
								icon : 5
							});
    		return false;
    	}
    	return true;
    }
    
</script>
<!--/row-->