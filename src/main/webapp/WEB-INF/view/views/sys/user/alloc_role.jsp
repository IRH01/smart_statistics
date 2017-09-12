<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="sdk-content">
	<ul class="breadcrumb">
		<li>您当前的位置：</li>
		<li><a href="#">系统管理</a></li>
		<li><a href="#">全部用户管理</a></li>
		<li><a href="#">分配角色</a></li>
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
					<form name="form" class="form-horizontal"
						action="<%=path%>/sys/user/allocRole.do" method="post">
						<div class="control-group">
							<input type="hidden" name="id" value="${id}"> <label
								class="control-label">角色名</label>
							<div class="controls form-inline top20 ">
								<c:forEach items="${roleList}" var="role" varStatus="status">
									<input type="checkbox" name="role" value="${role.id}"
										<c:if test="${role.owned}">checked="checked" </c:if> />${role.name}&nbsp;&nbsp;
                                </c:forEach>
							</div>
						</div>
						<shiro:hasPermission name="sys_user_add">
							<button type="submit" class="btn btn-primary top20">
								<i class="icon-search icon-white"></i>保存
							</button>
						</shiro:hasPermission>
						<button type="button" class="btn btn-primary top20" onclick="history.go(-1)" ><i class="icon-search icon-white"></i>返回</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="menuContent" class="menuContent"
	style="display: none; position: absolute;">
	<ul id="treeDemo" class="ztree" style="margin-top: 0; width: 160px;"></ul>
</div>
<!--/span-->
</div>
<script type="text/javascript">
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

    $(document).ready(function(){
        $.ajax({
            url: "<%=path%>/sys/role/"+Math.random()+"/tree.do" ,
            dataType:"json" ,
            success: function(date){
                $.fn.zTree.init($("#treeDemo"), CompanySetting, date);
            }
        });
    });

</script>
<!--/row-->