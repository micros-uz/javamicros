
<!DOCTYPE html>
<%@ page session="false" %>
<%
java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("yyyy m h s M H S mm ssssss");
%>
<html lang="en">
    <head>
        <title><%=request.getServletContext().getServerInfo() %></title>
        <link href="favicon.ico" rel="icon" type="image/x-icon" />
        <link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
        <link href="tomcat.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div id="wrapper">
		<h1>Current Time: <%=sdf1.format(new java.util.Date())%></h1>
		<p>
		Hello Spring MVC
		</p>
            
            
     </body>

</html>
