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
		<li>编辑</li>
	</ul>
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 编辑</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				<div class="panel-body">
					<form id="form" name="form" class="form-horizontal"
						action="<c:url value="/ybfurlcfg/update.do"/> " method="post">
						<fieldset>
							<input type="hidden" name="tblId" id="tblId" value="${value.tblId}">
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="urlName"><em
									class="red-star">*</em>URL名称:</label>
								<div class="col-sm-3">
									<input class="form-control" name="urlName" type="text"
										maxlength="400" id="urlName" value="${value.urlName}" />
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="urlId"><em
									class="red-star">*</em>URL路径:</label>
								<div class="col-sm-3">
									<input class="form-control" name="urlId" type="text"
										maxlength="200" id="urlId" value="${value.urlId}" />
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="domainName"><em
									class="red-star">*</em>域名:</label>
								<div class="col-sm-3">
									<input class="form-control" name="domainName" type="text"
										maxlength="200" id="domainName" value="${value.domainName}" />
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2"></label>
								<div class="col-sm-3">
									<shiro:hasPermission name="admin_admin_edit">
										<button type="submit" class="btn btn-primary">保存</button>
									</shiro:hasPermission>
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
            	urlName: {
                	required: true,
                    maxlength: 400
                },
                urlId: {
                    required: true,
                    url:true,
                    remote:"<%=request.getContextPath()%>/ybfurlcfg/validateUrlId.do?tblId=" + $('#tblId').val(),
                    maxlength: 200
                },
                domainName: {
                	required: true,
                    maxlength: 200
                },
            },
            messages: {
            	urlId: {
            		url:"请输入正确的URL路径！",
                    remote: "该URL路径已存在！"
                }
            }
        });
    });
</script>
<!--/row-->