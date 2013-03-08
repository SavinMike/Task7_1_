<%@ page contentType="text/html; encoding=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h1>${Exception}</h1>
<form name="form1" method="post" action="simple">

   <input name="Rollno" type="text" id="RollNo" size="15" maxlength="256">
   <input type="submit" name="Submit" value=" Add ">
</form>
<h1>Messages:</h1>
<c:forEach items="${GuestList}" var="message">
<h2>${message}</h2>
</c:forEach>
</body>
</html>
