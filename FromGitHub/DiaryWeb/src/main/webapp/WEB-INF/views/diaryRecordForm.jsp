<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css" /> 
        <link rel="stylesheet" href="<c:url value="/resources/css/jquery.treeview.css"/>" type="text/css" />
        <link rel="stylesheet" href="<c:url value="/resources/css/screen.css" />" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить/Редактировать запись</title>
    </head>
    <body>

        <h1>Добавить/Редактировать запись</h1>
        <c:url var="urlPostSaveOrUpdateRecord" value="/saveOrUpdateRecord" />
        <form:form method="POST" action="${urlPostSaveOrUpdateRecord}" modelAttribute="diaryRecordForm">
            <table> 
                <tr>
                    <td><form:label path="diaryCategory">Категория</form:label></td>
                    <td><form:input disabled="true" size="100" path="diaryCategory.name" /></td>
                </tr>
                <tr>
                    <td><form:label path="id">Номер</form:label></td>
                    <td><form:input disabled="true" size="5" path="id" /></td>
                </tr>
                <tr>
                    <td><form:label path="name">Название записи</form:label></td>
                    <td><form:input path="name" size="100" /></td>
                </tr>
                <tr>
                    <td><form:label path="text">Текст записи</form:label></td>
                    <td><form:textarea path="text" cols="76" rows="20" /></td>
                </tr>
                <tr>
                    <td><form:label path="created">Создан</form:label></td>
                    <td><form:input disabled="true" path="created" /></td>
                </tr>
                <tr>
                    <td><form:label path="updated">Обновлен</form:label></td>
                    <td><form:input disabled="true" path="updated" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Отправить"/></td>
                </tr>
            </table>
        </form:form>

    </body>
</html>
