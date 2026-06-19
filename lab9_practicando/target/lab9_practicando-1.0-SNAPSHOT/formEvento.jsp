<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 19/06/2026
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Evento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container" style="max-width:500px">
    <h2>Nuevo Evento</h2>

    <% String error = (String) request.getAttribute("error");
        if (error != null) { %>
    <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <form action="<%= request.getContextPath() %>/eventoServlet"
          method="POST">

        <div class="mb-3">
            <label class="form-label">Título:</label>
            <input type="text" class="form-control" name="titulo" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Descripción:</label>
            <input type="text" class="form-control" name="descripcion">
        </div>

        <div class="mb-3">
            <label class="form-label">Fecha:</label>
            <input type="date" class="form-control" name="fecha" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Lugar:</label>
            <input type="text" class="form-control" name="lugar" required>
        </div>

        <button type="submit" class="btn btn-success">Guardar</button>
        <a href="<%= request.getContextPath() %>/eventoServlet"
           class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>