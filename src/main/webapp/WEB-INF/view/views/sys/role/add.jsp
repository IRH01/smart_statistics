<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/template/taglib.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script src="<%=request.getContextPath()%>/lib/layer/layer.js"></script>
<div class="sdk-content">
	<ul class="breadcrumb">
		<li>您当前的位置：</li>
		<tags:breadcrumb />
		<li>创建角色</li>
	</ul>
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 创建角色 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				<div class="panel-body">
					<form id="form" name="form" class="form-horizontal"
						action="<%=path%>/sys/role/save.do" method="post">
						<fieldset>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="name">角色名:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" id="name" name="name"
										value="${role.name}">
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="permission">权限:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" id="permission"
										name="permission" value="${role.permission}"
										onclick="onShowCompanyTree()" readonly> <input
										class="form-control" type="hidden" id="permissionIds"
										name="permissionIds" value="${role.permissionIds}">
									<div id="menuContent" class="menuContent"
										style="display: none; position: absolute;width:360px;border: 1px solid #ccc;left:15px;position: absolute; z-index: 10;">
										<ul id="treeDemo" class="ztree" style="margin-top: 0; width: 160px;width: 358px;background-color: white;"></ul>
									</div>
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2"></label>
								<div class="col-sm-3">
									<shiro:hasPermission name="sys_role_add">
										<button type="submit" class="btn btn-primary" onclick="return checkForm();">
											<i class="icon-ok icon-white"></i>保存
										</button>
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
        	name:{
                required: true,
                remote:"<c:url value="/sys/role/validateRoleName.do"/>",
                maxlength: 32
            },
            permission:{
                required: true
            }
        },
        messages: {
        	name: {
                remote: "角色名已经存在！"
            }
        }
    });
});

    var CompanySetting = {
        check: {
            enable: true ,
            chkStyle: "checkbox"
        },
        data: {
            simpleData: {
                enable: true
            }
        } ,
        callback: {
        	onCheck: onCompanyClick,
        }
    };

    function onCompanyClick() {
    	
        var zTree = $.fn.zTree.getZTreeObj("treeDemo") ;
        var n="" ,v="";
        var nodes = zTree.getCheckedNodes(true);
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l ; i++) {
            if(nodes[i].isParent==false){
                n += nodes[i].name + ",";
                v += nodes[i].id+",";
            }
        }
        if (n.length > 0 )  { n = n.substring(0, n.length-1) , v=v.substring(0, v.length-1) }
        var permission = $("#permission");
        var companyValue=$("#permissionIds");
        permission.attr("value", n);
        companyValue.attr("value", v);
        //hideCompanyMenu();
    }

    function onShowCompanyTree(){
        var compamyinput =$("#permission") ;
        var companyinputOffset=$("#permission").offset();
        $("#menuContent").css({top:compamyinput.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onCompanyBodyDown);
    }


    function hideCompanyMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onCompanyBodyDown);
    }


    function onCompanyBodyDown(event) {
        if (!(event.target.id == "permission"  || $(event.target).parents("#menuContent").length>0)) {
            hideCompanyMenu();
        }
    }

    $().ready(function(){
        $.ajax({
            url: "<%=path%>/sys/role/"+Math.random()+"/tree.do" ,
            dataType:"json" ,
            success: function(date){
                $.fn.zTree.init($("#treeDemo"), CompanySetting, date);
            }
        });
    });
    
    function checkForm(){
    	var permissionIds = $('#permissionIds').val();
    	var roleName = $('#name').val();
    	if(roleName==null||roleName.trim()==""){
    		layer.alert("角色名不能为空", {
								icon : 5
							});
			$('#name').focus();
    		return false;
    	}
    	if(permissionIds==null||permissionIds.trim()==""){
    		layer.alert("权限不能为空", {
								icon : 5
							});
			$('#permissionIds').focus();
    		return false;
    	} 
    	return true;
    }

</script>
<!--/row-->