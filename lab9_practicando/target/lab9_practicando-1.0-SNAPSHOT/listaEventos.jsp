<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 19/06/2026
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9_practicando.dto.EventoDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<EventoDto> lista =
            (ArrayList<EventoDto>) request.getAttribute("listaEventos");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eventos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container mt-4">
    <h2>Lista de Eventos</h2>

    <a class="btn btn-success mb-3"
       href="<%= request.getContextPath() %>/eventoServlet?action=formCrear">
        + Añadir Evento
    </a>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Descripción</th>
            <th>Fecha</th>
            <th>Lugar</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <% for (EventoDto e : lista) { %>
        <tr>
            <td><%= e.getIdEvento() %></td>
            <td><%= e.getTitulo() %></td>
            <td><%= e.getDescripcion() %></td>
            <td><%= e.getFecha() %></td>
            <td><%= e.getLugar() %></td>
            <td>
                <a class="btn btn-danger btn-sm"
                   href="<%= request.getContextPath() %>
                             /eventoServlet?action=borrar&id=<%= e.getIdEvento() %>"
                   onclick="return confirm('¿Borrar este evento?')">
                    Borrar
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
