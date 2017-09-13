<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/template/taglib.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<div class="sdk-content">
	<ul class="breadcrumb">
		<li>您当前的位置：</li>
		<tags:breadcrumb />
		<li>分配权限</li>
	</ul>
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 编辑角色 </a>
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
						action="<%=path%>/sys/role/save.do" method="post">
						<fieldset>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="name">角色名:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" id="name" name="name"
										value="${role.name}"> <input class="form-control"
										type="hidden" id="id" name="id" value="${role.id}">
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2" for="name">权限:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" id="permission"
										name="permission" value="${role.permission}"
										onclick="onShowCompanyTree()"  readonly> <input
										class="form-control" type="hidden" id="permissionIds"
										name="permissionIds" value="${role.permissionIds}">
									<div id="menuContent" class="menuContent"
										style="display: none; position: absolute; z-index: 10;width:360px;border: 1px solid #ccc;left:15px;">
										<ul id="treeDemo" class="ztree"
											style="margin-top: 0; width: 358px;background-color: white;"></ul>
									</div>
								</div>
							</div>

							<div class="form-group form-group-sm">
								<label class="control-label col-sm-2"></label>
								<div class="col-sm-3">
									<button type="submit" class="btn btn-primary" onclick="return checkForm();">
										<i class="icon-ok icon-white"></i>确定
									</button>
									<button type="button" class="btn btn-primary" onclick="history.go(-1)">
										<i class="icon-ok icon-white"></i>返回
									</button>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="headingTwo">
									<h4 class="panel-title" id="-collapsible-group-item-#1-">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseOne" aria-expanded="true"
											aria-controls="collapseOne" class=""> 菜单列表 </a>
									</h4>
								</div>
								<div id="collapseOne" class="panel-collapse collapse in"
									role="tabpanel" aria-labelledby="headingtne"
									aria-expanded="true">
									<div class="panel-body">

										<div class="col-md-4">

											<div class="col-md-10">
												<div class="main-menu">
													<ul id="menuTree" class="ztree">
													</ul>
												</div>
											</div>
										</div>
										<div class="col-md-8">
											<form id="form" class="form-horizontal"></form>
										</div>

									</div>
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
                remote:"<%=request.getContextPath()%>/sys/role/validateRoleName.do?id="+$('#id').val(),
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


    function changeAction(url){
        document.form.action=url;
        document.form.submit();
    }

    var CompanySetting = {
        check: {
            enable: true ,
            chkStyle: "checkbox" ,
            chkboxType: { "Y": "ps", "N": "s" }
        },
        data: {
            simpleData: {
                enable: true
            }
        } ,
        callback: {
        	onCheck: onCompanyClick
        }
    };

    function onCompanyClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo") ;
        var n="" ,v="";
        var nodes = zTree.getCheckedNodes(true);
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l ; i++) {
            if(nodes[i].isParent==false){
                n += nodes[i].name + ",";
                v+=nodes[i].id+",";
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

    $().ready(
       function(){
        $.ajax({
            url: "<%=path%>/sys/role/"+Math.random()+"/edittree.do"+"?roleId="+$("#id").attr("value"),
            dataType:"json" ,
            success: function(date){
                $.fn.zTree.init($("#treeDemo"), CompanySetting, date);
            }
        });
        showMenu();
    });


 function showMenuDetail(event, treeId, treeNode, clickFlag) {
        //清空全部数据
        $("#form").find("input[type!='button']").val("");
        var menuId = treeNode.id;
        //存在则查询展示 否则只展示菜单名称
        if (menuId) {
            $("#menuId").val(menuId);
            var url = "<%=path%>/sys/menu/menuDetail.do";
            $.post(url, {id: menuId}, function (json) {
                $("#form").find("input[name='name']").val(json.name);
                $("#form").find("input[name='permission']").val(json.permission);
                $("#form").find("input[name='url']").val(json.url);
            }, "json");
        }else{
           $("#form").find("input[name='name']").val(treeNode.name);
        };
    }
    
     function leafFilter(node){
                return !node.isParent&&!node.url;
            }
     function showMenu(){
     		var ids="";
     		var roleId=$("#id").attr("value");
     		ids=roleId+ids;
            if(ids!=""){
            		  $.post("<%=path%>/sys/menu/menuListByRole.do?",{'roleIds':ids},
               		  function (json) {
	                          		 $.fn.zTree.init($("#menuTree"), funcSetting,json);
	                         		 menuTree = $.fn.zTree.getZTreeObj("menuTree");	
				               	     $(menuTree.getNodesByFilter(leafFilter)).each(function(i,item){
				                   	 menuTree.removeNode(item);
				                });
               			 },
           			"json"); 
    		 }else{
    		   $.fn.zTree.init($("#menuTree"), funcSetting,null);
    		    menuTree = $.fn.zTree.getZTreeObj("menuTree");
    		 
    		 }
          
         }

	    var funcSetting = {
	        view: {
	            showLine: true,
	            selectedMulti: false
	        },
	        check: {
	            enable: false
	        },
	        edit: {
	            enable: true,
	            showRemoveBtn: false,
	            showRenameBtn: false
	        },
	        data: {
	            simpleData: {
	                enable: true
	            }
	        },
	        callback: {
	            onClick: showMenuDetail
	        }
	    };
	    
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