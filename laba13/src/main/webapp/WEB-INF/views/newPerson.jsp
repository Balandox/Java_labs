<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.10.2022
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Person</title>
</head>
<body>

<form action="/phonebook" method="POST">

    First name: <input type = "text" name = "firstName"/>
    <br />

    Last name: <input type = "text" name = "lastName"/>
    <br />

    Add telephone: <input type = "text" name = "telephone"/>
    <br />
    <input type = "submit" value = "Submit" />

</form>

</body>
</html>
