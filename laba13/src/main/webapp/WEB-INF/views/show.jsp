<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Person</title>
</head>
<body>
<p>First Name: ${person.getFirstName()}</p>
<p>Last Name: ${person.getLastName()}</p>
<p>Telephone numbers: </p>
<c:forEach items="${person.getPhoneList()}" var="personPhone">
    <p>${personPhone}</p>
</c:forEach>
<hr/>
<a href="/phonebook/${person.getId()}/addTelephone">Add new telephone number</a>

</body>
</html>
