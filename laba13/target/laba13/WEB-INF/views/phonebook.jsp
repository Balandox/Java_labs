<%@ page import="java.util.List" %>
<%@ page import="org.suai.laba13.model.Person" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/search/styles.css"/>

<html>
<head>
    <title>PhoneBook</title>
</head>
<body>



<div>

    <h2 style="text-align: center">List of Person:</h2>

<%--    <div style="text-align: center">
    <a href="/phonebook/all">All person</a>
    <a href="/phonebook/friends">Friends</a>
    <a href="/phonebook/family">Family</a>
    </div>--%>

    <div>
        <form action="/phonebook/search" method="GET">
            <input name="searchPerson" placeholder="Искать здесь..." type="search">
            <button type="submit">Поиск</button>
            <c:if test="${errorString != null}" var="errorString">
                <div style="color: red">No matches found!</div>
            </c:if>
        </form>
    </div>

    <c:forEach items="${personList}" var="person">
        <a href="/phonebook/${person.getId()}">${person}</a>
        <br>
    </c:forEach>

    <br/>
    <hr/>
    <a href="${pageContext.request.contextPath}/phonebook/new">Create new person</a>

</div>


</body>
</html>
