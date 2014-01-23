<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css" /> 
        <link rel="stylesheet" href="<c:url value="/resources/css/jquery.treeview.css"/>" type="text/css" />
        <link rel="stylesheet" href="<c:url value="/resources/css/screen.css" />" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf8">
        <title><spring:message code="label.title" /> Вход</title>
    </head>
    <body>
        <h1><spring:message code="label.title" /></h1>
        <br/>

        <c:if test="${not empty param.error}">
            <font color="red"> <spring:message code="label.loginerror" />
                : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
            </c:if>
        
        <form method="POST" action="<c:url value="/j_spring_security_check" />">
            <table border="0">
                <tr>
                    <td align="right"><spring:message code="label.login" /></td>
                    <td><input type="text" name="j_username" /></td>
                </tr>
                <tr>
                    <td align="right"><spring:message code="label.password" /></td>
                    <td><input type="password" name="j_password" /></td>
                </tr>
                <tr>
                    <td align="right"><spring:message code="label.remember" /></td>
                    <td><input type="checkbox" name="_spring_security_remember_me" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="left">
                        <input type="submit" value="Войти" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right"><a href="<c:url value="/registrationForm"/>">РЕГИСТРАЦИЯ</a></td>
                </tr>
            </table>
        </form>
    </body>
</html>