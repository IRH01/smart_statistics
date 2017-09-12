<%@ page language="java" pageEncoding="UTF-8"%>
<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true">
	<div class="panel panel-default">
		<div id="collapseOne" class="panel-collapse collapse in"
			role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
			<div class="panel-body">
				<div class="clm-box">
					<h4 class="clm-title">
						<img src="img/ico-clm.png" alt="" />选择您需要的栏目：
					</h4>
					<hr style="border-top: 1px solid #ddd" />
					<dl class="clm-ctn" id="clm">
						<dt>系统管理</dt>
						<dd>
							<a href="#">用户管理</a> <a href="#">角色管理</a> <a href="#">菜单管理</a> <a
								href="#">权限管理</a>
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

    $(function () {
        zTree_Menu = $.fn.zTree.getZTreeObj("menu");
        $("#clm").empty();
        $(zTree_Menu.getNodes()).each(function (i, item) {
        	
            if (item.children) {
                var content = "";
                content += "<dt>" + item.name + "</dt>";
                content += "<dd>";
                $(item.children).each(function (j, data) {
                	if(data.isParent){
                		$(data.children).each(function (j1, data1) {
                        	content += ("<a href='" + data1.url + "'>" + data1.name + "</a>");
                        });
                	}else{
                		content += ("<a href='" + data.url + "'>" + data.name + "</a>");
                	}
                });
                content += "</dd>";
                $("#clm").append(content);
            } else {
                if (item.url) {
                    var content = "";
                    content += "<dt>" + item.name + "</dt>";
                    content += "<dd>";
                        content += ("<a href='" + item.url + "'>" + item.name + "</a>");
                    content += "</dd>";
                    $("#clm").append(content);
                }
            }
        });
    });

</script>
