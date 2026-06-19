<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 19/06/2026
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9_practicando.dto.TicketDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  ArrayList<TicketDto> lista =
          (ArrayList<TicketDto>) request.getAttribute("listaTickets");
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Tickets</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container mt-4">
  <h2>Lista de Tickets</h2>

  <a class="btn btn-success mb-3"
     href="<%= request.getContextPath() %>/ticketServlet?action=formCrear">
    + Crear Tipo de Ticket
  </a>

  <table class="table table-bordered table-hover">
    <thead class="table-dark">
    <tr>
      <th>Evento</th>
      <th>Descripción</th>
      <th>Fecha</th>
      <th>Lugar</th>
      <th>Ticket</th>
      <th>Precio</th>
      <th>Cupo Disp.</th>
      <th>Acción</th>
    </tr>
    </thead>
    <tbody>
    <% for (TicketDto t : lista) { %>
    <tr>
      <td><%= t.getTituloEvento() %></td>
      <td><%= t.getDescripcionEvento() %></td>
      <td><%= t.getFechaEvento() %></td>
      <td><%= t.getLugarEvento() %></td>
      <td><%= t.getNombreTicket() %></td>
      <td>S/. <%= String.format("%.2f", t.getPrecio()) %></td>
      <td><%= t.getCupoDisponible() %></td>
      <td>
        <a class="btn btn-danger btn-sm"
           href="<%= request.getContextPath() %>
                             /ticketServlet?action=borrar&id=<%= t.getIdTicketTipo() %>"
           onclick="return confirm('¿Borrar este ticket?')">
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