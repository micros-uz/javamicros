<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
<link rel="stylesheet"  href="<c:url value="/css/bootstrap-theme.min.css" />" />
<link href="<c:url value="/css/fleetfoot.css" />" rel="stylesheet" />

<title>Fleetfoot login</title>
</head>
<body>
    <div class="container">

        <h1>Example webapp</h1>

        <div class="row">
            
            <div class="col-md-6">
                <h2>Login</h2>
                <form method="POST" action="<c:url value="j_spring_security_check" />">

                    <c:if test="${not empty error}">
                        Your login attempt was not successful, try again.<br /> Caused :
                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                    </c:if>

                    <div class="form-group">
                        <label class="sr-only" for="user">Username</label> <input
                            type="text" class="form-control" id="user" name="j_username"
                            placeholder="Enter username">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="pwd">Password</label> <input
                            type="password" class="form-control" id="pwd" name="j_password"
                            placeholder="Password">
                    </div>

                    <button type="submit" class="btn btn-default">Sign in</button>

                </form>
            </div>
        </div>

    </div>

<script src='<c:url value="/js/jquery-1.10.2.min.js" />' type='text/javascript'></script>
<script src="<c:url value="/js/bootstrap.min.js" />" type="text/javascript"></script>


</body>
</html>