<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add telephone number</title>
</head>
<body>

<form action="/phonebook/${person.getId()}" method="POST">

    Add telephone: <input type = "text" name = "telephone"/>
    <br />
    <input type = "submit" value = "Submit" />

</form>

</body>
</html>
