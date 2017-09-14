<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row-fluid sortable">
    <div class="box span12">
        <div class="box-header well" data-original-title>
            <h2>
                <i class="icon-user"></i> 个人信息
            </h2>
            <div class="box-icon">
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
            </div>
        </div>
        <div class="box-content">
            <c:if test="${null != msg}">
                <div class="alert alert-info">
                    <i class="icon-info-sign"></i>${msg}
                </div>
            </c:if>

            <form class="form-horizontal" action="profile.do" method="post">
                <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="id">编号</label>

                        <div class="controls">
                            <input class="input-xlarge focused" id="id" name="id" type="text"
                                   value="${userInfo.yunvaId}" readonly>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="name">名称</label>

                        <div class="controls">
                            <input class="input-xlarge focused" id="name" name="name"
                                   type="text" value="${userInfo.nickName}" readonly>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <!--/span-->

</div>