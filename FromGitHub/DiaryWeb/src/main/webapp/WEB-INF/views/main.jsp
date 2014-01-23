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

        <script src="<c:url value="/resources/js/jquery.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jquery.cookie.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jquery.treeview.js"/>" type="text/javascript"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf8">
        <title><spring:message code="label.title" /></title>

        <script type="text/javascript">
            $(document).ready(function(){
                $("#navigation").treeview({
                    persist: "location",
                    collapsed: true,
                    animated: "fast",
                    unique: true
                });
            })
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div align="right">
                    Здравствуйте, <strong><c:out value="${user.login}" /></strong> | 
                    <a href="<c:url value="/logout" />"><spring:message code="label.logout" /></a>
                </div>
                <h2><spring:message code="label.title" /> ver. 1.0</h2>
            </div><!-- #header-->

            <div id="middle">

                <div id="container">

                    <div class="sidebar" id="sideLeft">
                        <h3>Категории <a href="<c:url value="/diaryCategory/showFormAdd/root" />" title="Добавить корневую категорию">[+]</a></h3>
                        <br />
                        <ul id='navigation'>
                            ${categoriesTreeRendered}
                        </ul>
                    </div>

                    <div id="content">
                        <c:choose>
                            <c:when test="${empty activeCategory.name}" >
                                <h3>Выберите или добавьте категорию</h3>
                            </c:when>
                            <c:otherwise>
                                <h3>Текущая категория: <i><u>${activeCategory.name}</u></i>
                                    <a href="<c:url value="/diaryCategory/showFormAdd/child"/>" title="Добавить вложенную категорию">[+]</a>
                                    <a href="<c:url value="/diaryCategory/showFormEdit"/>" title="Редактировать">[*]</a>
                                </h3>
                                <br />
                                <h3>Записи <a href="<c:url value="/diaryRecord/showFormAdd" />" title="Добавить">[+]</a></h3>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${!empty records}">
                            <br />

                            <table class="diaryRecords" width="800px">
                                <tr>
                                    <th>№</th>
                                    <th>Название записи</th>
                                    <th>Создан</th>
                                    <th>Обновлен</th>
                                    <th>Редактировать</th>
                                    <th>Удалить</th>
                                </tr>
                                <c:forEach items="${records}" var="rec">
                                    <tr>
                                        <td>${rec.id}</td>
                                        <td>${rec.name}</td>
                                        <td>${rec.created}</td>
                                        <td>${rec.updated}</td>
                                        <td><a href="<c:url value="/diaryRecord/showFormEdit/id/${rec.id}"/>">[*]</a></td>
                                        <td><a href="<c:url value="/diaryRecord/delete/id/${rec.id}"/>">[x]</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>

                    </div><!-- #content-->
                </div><!-- #container-->


            </div><!-- #middle-->
        </div><!-- #wrapper -->

        <div id="footer">
            <div align="center"><strong><spring:message code="label.footer" /></strong></div>
        </div><!-- #footer -->

    </body>
</html>