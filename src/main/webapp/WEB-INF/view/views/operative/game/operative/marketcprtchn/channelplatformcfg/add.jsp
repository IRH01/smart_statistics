<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<%
	request.setAttribute("path", request.getContextPath());
%>
<%
	String path = request.getContextPath();
%>
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
					<form class="form-horizontal" name="form" id="form">
						<fieldset>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="channelId"><em
									class="red-star">*</em>渠道ID:</label>
								<div class="col-sm-3">
									<input class="form-control" name="channelId" type="text"
										id="channelId" onblur="validateChannel();"/>
								</div>
								<span class="red-star" style="display:none;" id="channelIdTip"></span>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="urlId">渠道名称:</label>
								<div class="col-sm-3">
									<span id="channelName"/></span>
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="platforms"><em
									class="red-star">*</em>选择游戏:</label>
								<div class="col-sm-3">
									<select id="platformId"  class="select" style="height: 30px;width: 360px;border-radius: 4px;">
                                       	<c:forEach items="${platforms}" var="platform">
                                       		<option value="${platform.id}" title="${platform.name}">${platform.name}</option>
                                       	</c:forEach>
                                   	</select>
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2"></label>
								<div class="col-sm-3">
									<button type="button" class="btn btn-primary" onclick="add();" >保存</button>
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
   var channelIsOk = false;
   var add = function(){
	   validateChannel();
	   if(!channelIsOk){
		   return;
	   }
	   $.post(
		"${path}/game/operative/chnplfcfg/add.do",{
			channelId:$("#channelId").val(),
			platformId:$("#platformId").val()
		},
		function(data){
			//添加成功
			if(data.result == 0){
				window.location.href = "${path}/game/operative/chnplfcfg/list.do";
			}else{
				if(data.result == -1){
					layer.alert("添加失败,该渠道与游戏关联关系已存在，不能重复添加！", {
						icon : 5
					});
				}else{
					layer.alert("添加失败", {
						icon : 5
					});
				}
			}
		}
	   );
   }
   
   var validateChannel = function(){
	   $("#channelIdTip").hide();
	   if(undefined == $("#channelId").val() || "" == $("#channelId").val() || null == $("#channelId").val()){
			$("#channelIdTip").html("渠道号不能为空");
			$("#channelIdTip").show();
	   }
	   $.post(
		"${path}/game/operative/chnplfcfg/validateChannel.do",{
			channelId:$("#channelId").val()
		},
		function(data){
			//添加成功
			if(data.result == -1){//该渠道已被占用
				$("#channelName").val(data.value);
				channelIsOk = false;
				$("#channelIdTip").html("该渠道已设置推广游戏，不能再设置");
				$("#channelIdTip").show();
				return;
			}else if(data.result == -2){//渠道号不存在
				$("#channelIdTip").html("渠道号不存在");
				$("#channelIdTip").show();
				channelIsOk = false;
			}else if(data.result == 0){//渠道号找到了
				$("#channelName").html(data.value);
				channelIsOk = true;
			}else{
				$("#channelIdTip").html("系统异常");
				$("c#hannelIdTip").show();
				channelIsOk = false;
			}
		}
	   );
   }
</script>
<!--/row-->