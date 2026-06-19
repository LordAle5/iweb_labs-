<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 19/06/2026
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9_practicando.beans.Evento" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Evento> listaEventos =
            (ArrayList<Evento>) request.getAttribute("listaEventos");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Ticket</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container" style="max-width:500px">
    <h2>Crear Tipo de Ticket</h2>

    <% String error = (String) request.getAttribute("error");
        if (error != null) { %>
    <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <form action="<%= request.getContextPath() %>/ticketServlet"
          method="POST">

        <%-- Selector de evento --%>
        <div class="mb-3">
            <label class="form-label">Evento:</label>
            <select class="form-select" name="idEvento" required>
                <option value="">Seleccione un evento</option>
                <% for (Evento e : listaEventos) { %>
                <option value="<%= e.getIdEvento() %>">
                    <%= e.getTitulo() %> (<%= e.getFecha() %>)
                </option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Nombre del Ticket:</label>
            <input type="text" class="form-control" name="nombre" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Precio:</label>
            <input type="number" step="0.01" min="0" class="form-control"
                   name="precio" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Cupo Total:</label>
            <input type="number" min="0" class="form-control"
                   name="cupoTotal" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Cupo Disponible:</label>
            <input type="number" min="0" class="form-control"
                   name="cupoDisponible" required>
        </div>

        <button type="submit" class="btn btn-success">Guardar</button>
        <a href="<%= request.getContextPath() %>/ticketServlet"
           class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>