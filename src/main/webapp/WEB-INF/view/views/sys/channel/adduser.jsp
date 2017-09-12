<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="sdk-content">
	<ul class="breadcrumb">
		<li>您当前的位置：</li>
		<tags:breadcrumb />
		<li>渠道账号查询设置</li>
	</ul>
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 渠道账号查询设置 </a>
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
					<form class="form-horizontal" name="form" id="form"
						action="<%=path%>/sys/channel/save.do" method="post">
						<fieldset>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="id">渠道标识号:</label>
								<div class="col-sm-3">
									<span class="form-span">${channel.channel}</span> <input
										class="form-control" type="hidden" id="id" name="id"
										value="${channel.id}"> <input class="form-control"
										type="hidden" name="userId" value="${channel.userId}">
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="channelUser"><em
									class="red-star">*</em>查询账号:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" id="channelUser"
										name="channelUser" value="${channel.channelUser}">
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="channelUserPassword"><em
									class="red-star">*</em>查询密码:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text"
										id="channelUserPassword" name="channelUserPassword">
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="markContent">备注:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" id="markContent"
										name="markContent" value="${channel.markContent}">
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="userStatus">登录权限:</label>
								<div class="col-sm-3">
									<label class="radio-inline"> <input type="radio"
										name="userStatus" id="inlineRadio3" value="0"
										checked="checked"> 允许登录
									</label> <label class="radio-inline"> <input type="radio"
										id="userStatus" name="userStatus" value="1"> 禁止登录
									</label>
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="userStatus"></label>
								<div class="col-sm-3">
									<button type="submit" onclick="return checkusername();"
										class="btn btn-primary">
										<i class="icon-ok icon-white"></i>确定
									</button>
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

    function checkusername()
    {
        var username=document.getElementById("channelUser");
        if(username.value.length < 5||username.value.length > 16){
            alert("帐号长度不对,应为5到16个字符");
            username.focus();
            return false;
        }else{
            var strRegex =/^[A-Za-z0-9]+$/;
            var re=new RegExp(strRegex);
            if(!re.test(username.value))
            {
                alert("帐号“"+username.value+"”不规范，\n\n只能包括字母、数字，以字母开头");
                username.focus();
                return false;
            }
        }
        return true;
    }

    $().ready(function () {
        $("#form").validate({
            rules: {
                channelUser: {
                    required: true ,
                    remote:{
                        url:"<%=path%>/sys/user/"+Math.random()+"/valid.do" ,
                        data:{username : function(){
                            return $("#channelUser").val(); }
                        }}
                },
                channelUserPassword: {
                    required: true,
                    maxlength: 15
                },
                markContent: {
                    maxlength: 100
                }
            },
            messages: {
                channelUser: {
                    required: "请输入账号" ,
                    remote:"该用户名已经存在"
                },
                channelUserPassword: {
                    required: "请输入密码"
                }
            }
        });
    });
</script>