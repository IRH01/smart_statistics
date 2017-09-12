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
		<li>分配角色</li>
	</ul>
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 分配角色 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				<div class="panel-body">
					<form id="_form" name="form" class="form-horizontal"
						action="<%=path%>/admin/admin/allocRole.do" method="post">
						<div class="control-group">
							<input type="hidden" name="userId" value="${userId}"> <label
								class="control-label">角色名</label>
							<div class="controls checkbox top10">
								<c:forEach items="${roleList}" var="role" varStatus="status">
									<label><input id="role" type="checkbox" onclick="checkMove()"
										name="role" value="${role.id}"
										<c:if test="${role.owned}">checked="checked" </c:if> />${role.name}</label>&nbsp;&nbsp;
                                </c:forEach>
							</div>
						</div>
						<shiro:hasPermission name="admin_admin_allocRole">
							<button type="submit" class="btn btn-primary top20">
								<i class="icon-search icon-white"></i>保存
							</button>
						</shiro:hasPermission>
						<button type="button" class="btn btn-primary top20" onclick="history.go(-1)" ><i class="icon-search icon-white"></i>返回</button>
					</form>
				</div>
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
				role="tabpanel" aria-labelledby="headingtne" aria-expanded="true">
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
	</div>
	<div id="menuContent" class="menuContent"
		style="display: none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top: 0; width: 160px;"></ul>
	</div>
</div>
<script type="text/javascript">
       
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
     function checkMove(){
     		var objs=document.getElementsByName("role");
     		var ids="";
    		if(objs.length){  			
                 for(var i=0;i<objs.length;i++){  
                 			 if(objs[i].checked){
                 			   value=objs[i].value;//
                 			   ids=value+","+ids;
                 			} 
                 			
                 }
               }
            if(ids!=""){
                      ids=ids.substring(0,  ids.lastIndexOf(","));
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

  var menuTree;
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

    function changeAction(url){
        document.form.action=url;
        document.form.submit();
    }

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
            onClick: onCompanyClick
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
        hideCompanyMenu();
    }

    function onShowCompanyTree(){
        var compamyinput =$("#permission") ;
        var companyinputOffset=$("#permission").offset();
        $("#menuContent").css({left:companyinputOffset.left + "px", top:companyinputOffset.top + compamyinput.outerHeight() + "px"}).slideDown("fast");
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
    	$("#_form").validate({
    		rules: {
                role: {
                    required: true
                }
            },
            messages: {
            	role: {
                    required: "请选择角色",
                }
            }
        });
    	
        $.ajax({
            url: "<%=path%>/sys/role/"+Math.random()+"/tree.do" ,
            dataType:"json" ,
            success: function(date){
                $.fn.zTree.init($("#treeDemo"), CompanySetting, date);
            }
        });
        checkMove();
    });

</script>
<!--/row-->