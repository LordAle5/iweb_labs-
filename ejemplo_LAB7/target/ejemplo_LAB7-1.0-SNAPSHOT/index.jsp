<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Redirigir automáticamente al Servlet principal
    response.sendRedirect(request.getContextPath() + "/home");
%>