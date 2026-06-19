<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 19/06/2026
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9_practicando.dto.ReservaDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<ReservaDto> lista =
            (ArrayList<ReservaDto>) request.getAttribute("listaReservas");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container mt-4">
    <h2>Lista de Reservas</h2>

    <a class="btn btn-success mb-3"
       href="<%= request.getContextPath() %>/reservaServlet?action=formCrear">
        + Añadir Reserva
    </a>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>Evento</th>
            <th>Fecha</th>
            <th>Usuario</th>
            <th>Email</th>
            <th>Ticket</th>
            <th>Cantidad</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <% for (ReservaDto r : lista) { %>
        <tr>
            <td><%= r.getTituloEvento() %></td>
            <td><%= r.getFechaEvento() %></td>
            <td><%= r.getNombres() %> <%= r.getApellidos() %></td>
            <td><%= r.getEmail() %></td>
            <td><%= r.getNombreTicket() %></td>
            <td><%= r.getCantidad() %></td>
            <td>
                <a class="btn btn-danger btn-sm"
                   href="<%= request.getContextPath() %>
                             /reservaServlet?action=borrar&id=<%= r.getIdItem() %>"
                   onclick="return confirm('¿Cancelar esta reserva?')">
                    Cancelar
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>