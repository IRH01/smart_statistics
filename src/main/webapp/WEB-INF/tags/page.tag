<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page1" type="com.hhly.smartdata.util.page.Page "
              required="true" %>
<script src="../../lib/layer/layer.js"></script>
<script src="../../lib/tool.js"></script>

<%
    int current = page1.getPageNo();
    int begin = 1;
    int end = page1.getTotalPage();

    request.setAttribute("current", current);
    request.setAttribute("begin", begin);
    request.setAttribute("end", end);
    request.setAttribute("pList", page1.getPageNoDisp());

%>
<script type="text/javascript">
    var paras = '<%=page1.getParaJson()%>';
    var paraJson = eval('(' + paras + ')');

    //将提交参数转换为JSON
    var paraLists = '<%=page1.getParaListJson()%>';
    var paraListJson = eval('(' + paraLists + ')');

    function pageClick(pNo) {
        paraJson["pageNo"] = pNo;
        paraJson["pageSize"] = "<%=page1.getPageSize()%>";
        paraJson["totalRecord"] = "<%=page1.getTotalRecord()%>";

        var jsPost = function (action, values, valueLists) {
            var id = Math.random();
            document.write('<form id="post' + id + '" name="post' + id + '" action="' + action + '" method="post">');
            for (var key in values) {
                document.write('<input type="hidden" name="' + key + '" value="' + values[key] + '" />');
            }
            for (var key2 in valueLists) {
                for (var index in valueLists[key2]) {
                    document.write('<input type="hidden" name="' + key2 + '" value="' + valueLists[key2][index] + '" />');
                }
            }
            document.write('</form>');
            document.getElementById('post' + id).submit();
        }

        //发送POST
        jsPost("<%=page1.getSearchUrl()%>", paraJson, paraListJson);
    }

    function goToPage() {
        var toPage = document.getElementById('pageToGo').value;
        if (undefined == toPage || '' == toPage || 0 >= toPage) {
            layer.alert("跳转页号不能为空，且需大于0", {
                icon: 5
            });
            document.getElementById('pageToGo').focus();
            return;
        }
        pageClick(toPage);
    }
</script>
<div class="page-pull-right">
    <% if (current != 1 && end != 0) {%>

    <button class="btn btn-default btn-sm" onclick="pageClick(1)">首页</button>
    <button class="btn btn-default btn-sm"
            onclick="pageClick(${current-1})">前页
    </button>
    <%} else {%>
    <button class="btn btn-default btn-sm" disabled="disabled">首页</button>
    <button class="btn btn-default btn-sm" disabled="disabled">前页</button>
    <%} %>
    <c:forTokens items="${pList}" delims="|" var="pNo">
        <c:choose>
            <c:when test="${pNo == 0}">
                <label style="font-size: 10px; width: 20px; text-align: center;">•••</label>
            </c:when>
            <c:when test="${pNo != current}">
                <button class="btn btn-default btn-sm" onclick="pageClick(${pNo})">${pNo}</button>
            </c:when>
            <c:otherwise>
                <button class="btn btn-primary btn-sm" style="font-weight: bold;">${pNo}</button>
            </c:otherwise>
        </c:choose>
    </c:forTokens>
    <% if (current < end && end != 0) {%>
    <button class="btn btn-default btn-sm"
            onclick="pageClick(${current+1})">后页
    </button>
    <button class="btn btn-default btn-sm" onclick="pageClick(${end})">末页</button>
    <%} else {%>
    <button class="btn btn-default btn-sm" disabled="disabled">后页</button>
    <button class="btn btn-default btn-sm" disabled="disabled">末页</button>
    <%} %>
    <input type="text" id="pageToGo" style="width:30px;"
           onkeypress="return (/[\d]/.test(String.fromCharCode(event.charCode)));" onkeyup="value=numberCheck(value);">
    <button class="btn btn-default btn-sm" onclick="goToPage()">跳转</button>
</div>