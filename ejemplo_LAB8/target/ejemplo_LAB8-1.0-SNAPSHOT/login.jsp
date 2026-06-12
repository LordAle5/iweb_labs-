<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 12/06/2026
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Tienda</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5" style="max-width:400px">
    <h2 class="text-center mb-4">Iniciar Sesión</h2>

    <%-- Mostrar error si existe --%>
    <% String error = (String) request.getAttribute("error");
        if (error != null) { %>
    <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <%-- Formulario POST al LoginServlet --%>
    <form action="<%= request.getContextPath() %>/loginServlet"
          method="POST">
        <div class="mb-3">
            <label class="form-label">Correo:</label>
            <input type="email" class="form-control"
                   name="email" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Contraseña:</label>
            <input type="password" class="form-control"
                   name="password" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">
            Ingresar
        </button>
    </form>
</div>
</body>
</html>
