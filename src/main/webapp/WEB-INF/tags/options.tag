<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="map" type="java.util.Map" required="true"%>
<%@ attribute name="select" type="java.lang.Object"%>

<c:forEach var="item" items="${map}">
	<option value="${item.key}"
		<c:if test="${item.key == select}" >selected="selected"</c:if>>${item.value}</option>
</c:forEach>