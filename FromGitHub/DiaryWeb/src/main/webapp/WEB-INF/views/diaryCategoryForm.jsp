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

        <h1>Добавить/Редактировать категорию</h1>

        <c:if test="${action eq \"add\"}" >
            <c:url var="urlActionPost" value="/addCategory" />
        </c:if>
        <c:if test="${action eq \"update\"}" >
            <c:url var="urlActionPost" value="/updateCategory" />
        </c:if>

        <form:form method="POST" action="${urlActionPost}" modelAttribute="diaryCategoryForm">
            <table> 
            <tr>
                <td>Родительская категория</td>
                <td>
                    <form:select path="parent.id" style="width: 300px">
                        <option value="null"></option>
                        ${categoriesTreeRenderedToSelectList}
                    </form:select>
                 </td>
            </tr>
            <tr>
                <td><form:label path="id">Номер</form:label></td>
                <td><form:input path="id" disabled="true" size="5" /></td>
            </tr>
            <tr>
                <td><form:label path="name" >Название категории</form:label></td>
                <td><form:input path="name" size="45" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Отправить"/></td>
            </tr>
        </table>
    </form:form>

</body>
</html>
