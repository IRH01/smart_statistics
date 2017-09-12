<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>

<div class="sdk-content">
	<ul class="breadcrumb">
		<li>您当前的位置：</li>
		<tags:breadcrumb />
		<li>创建</li>
	</ul>
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 创建 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				<div class="panel-body">
					<form class="form-horizontal" name="form" id="form"
						action="<c:url value="/ybfurlcfg/insert.do"/>" method="post">
						<fieldset>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="urlName"><em
									class="red-star">*</em>URL名称:</label>
								<div class="col-sm-3">
									<input class="form-control" name="urlName" type="text"
										id="urlName" />
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="urlId"><em
									class="red-star">*</em>URL路径:</label>
								<div class="col-sm-3">
									<input class="form-control" name="urlId" type="text"
										id="urlId" />
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="domainName"><em
									class="red-star">*</em>域名:</label>
								<div class="col-sm-3">
									<input class="form-control" name="domainName" type="text"
										id="domainName" />
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2"></label>
								<div class="col-sm-3">
									<shiro:hasPermission name="admin_admin_add">
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
                    remote:"<c:url value="/ybfurlcfg/validateUrlId.do"/>",
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