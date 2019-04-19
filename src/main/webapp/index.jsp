<%@page language="java" isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<c:forEach items="${sessionScope.users}" var="user">
    ${user.id}====${user.name}====${user.age}====
    <fmt:formatDate value="${user.bir}" pattern="yyyy-MM-dd HH:mm:ss"/><br/>
</c:forEach>

</html>