<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css" /> 
        <link rel="stylesheet" href="<c:url value="/resources/css/jquery.treeview.css"/>" type="text/css" />
        <link rel="stylesheet" href="<c:url value="/resources/css/screen.css" />" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Персональный ежедневник</title>
    </head>
    <body>
        <h1>Регистрация</h1>
        <c:url var="urlReg" value="/registration" />
        <form:form method="POST" action="${urlReg}" modelAttribute="user">
            <table style=""> 
                <tr>
                    <td>Логин</td>
                    <td><form:input path="login" /></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><form:input path="password" type="password" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Зарегистрироватся"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
