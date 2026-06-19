<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 19/06/2026
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9_practicando.beans.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Usuario> listaUsuarios =
            (ArrayList<Usuario>) request.getAttribute("listaUsuarios");
    ArrayList<Evento> listaEventos =
            (ArrayList<Evento>) request.getAttribute("listaEventos");
    ArrayList<TicketTipo> listaTickets =
            (ArrayList<TicketTipo>) request.getAttribute("listaTickets");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nueva Reserva</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container" style="max-width:500px">
    <h2>Añadir Reserva</h2>

    <% String error = (String) request.getAttribute("error");
        if (error != null) { %>
    <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <form action="<%= request.getContextPath() %>/reservaServlet"
          method="POST">

        <%-- Selector de Usuario --%>
        <div class="mb-3">
            <label class="form-label">Usuario:</label>
            <select class="form-select" name="idUsuario" required>
                <option value="">Seleccione un usuario</option>
                <% for (Usuario u : listaUsuarios) { %>
                <option value="<%= u.getIdUsuario() %>">
                    <%= u.getNombres() %> <%= u.getApellidos() %>
                    (<%= u.getEmail() %>)
                </option>
                <% } %>
            </select>
        </div>

        <%-- Selector de Evento (solo informativo, ayuda visual) --%>
        <div class="mb-3">
            <label class="form-label">Evento:</label>
            <select class="form-select" disabled>
                <% for (Evento e : listaEventos) { %>
                <option><%= e.getTitulo() %> (<%= e.getFecha() %>)</option>
                <% } %>
            </select>
            <small class="text-muted">
                Referencial — selecciona directamente el tipo de ticket abajo
            </small>
        </div>

        <%-- Selector de Tipo de Ticket (incluye el evento implícito) --%>
        <div class="mb-3">
            <label class="form-label">Tipo de Ticket:</label>
            <select class="form-select" name="idTicketTipo" required>
                <option value="">Seleccione un ticket</option>
                <% for (TicketTipo t : listaTickets) { %>
                <option value="<%= t.getIdTicketTipo() %>">
                    <%= t.getNombre() %> - S/. <%= t.getPrecio() %>
                    (Cupo: <%= t.getCupoDisponible() %>)
                </option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Cantidad:</label>
            <input type="number" min="1" class="form-control"
                   name="cantidad" required>
        </div>

        <button type="submit" class="btn btn-success">Guardar</button>
        <a href="<%= request.getContextPath() %>/reservaServlet"
           class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>