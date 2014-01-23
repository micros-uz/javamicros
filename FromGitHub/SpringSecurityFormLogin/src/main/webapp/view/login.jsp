<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:choose>
  <c:when test="${pageContext.request.userPrincipal.authenticated}">
You already logged. Go to <a href="<c:url value='index.htm'/>">secure page</a> or <a href="<c:url value='j_spring_security_logout'/>">logout</a>
  </c:when>
  <c:otherwise>

<form method="POST" action="<c:url value='j_spring_security_check'/>">
	<h2>Login Form</h2>
	<p>
	<label>username</label>
	<input type="text" name="j_username" size="20"/></p>
	
	<p>
	<label>password:</label>
	<input type="password" name="j_password" size="20"/></p>
	
	<input type="submit" value="submit"/>
</form>

  </c:otherwise>
</c:choose>
