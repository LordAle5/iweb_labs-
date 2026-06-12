<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 12/06/2026
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ejemplo_lab8.dto.CarritoDto" %>
<%@ page import="com.example.ejemplo_lab8.beans.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<CarritoDto> lista =
            (ArrayList<CarritoDto>) request.getAttribute("listaCarrito");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mi Carrito</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>

<%-- NAVBAR --%>
<nav class="navbar navbar-dark bg-dark px-3">
    <span class="navbar-brand">
        Tienda – <%= usuario != null ? usuario.getNombreCompleto() : "" %>
    </span>
    <div>
        <a class="btn btn-outline-light btn-sm me-2"
           href="<%= request.getContextPath() %>/productoServlet">
            Productos
        </a>
        <a class="btn btn-outline-light btn-sm me-2"
           href="<%= request.getContextPath() %>/carritoServlet">
            Carrito
        </a>
        <a class="btn btn-outline-danger btn-sm"
           href="<%= request.getContextPath() %>/logout">
            Cerrar sesión
        </a>
    </div>
</nav>

<div class="container mt-4">
    <h2>Mi Carrito</h2>

    <% if (lista.isEmpty()) { %>
    <div class="alert alert-info">
        Tu carrito está vacío.
        <a href="<%= request.getContextPath() %>/productoServlet">
            Ver productos
        </a>
    </div>
    <% } else { %>
    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID Item</th>
            <th>Producto</th>
            <th>Usuario</th>
            <th>Precio Unit.</th>
            <th>Cantidad</th>
            <th>Subtotal</th>
        </tr>
        </thead>
        <tbody>
        <%
            double total = 0;
            for (CarritoDto c : lista) {
                total += c.getSubtotal();
        %>
        <tr>
            <td><%= c.getIdItem() %></td>
            <td><%= c.getNombreProducto() %></td>
            <td><%= c.getNombreUsuario() %></td>
            <td>S/. <%= String.format("%.2f",
                    c.getPrecioUnit()) %></td>
            <td><%= c.getCantidad() %></td>
            <td>S/. <%= String.format("%.2f",
                    c.getSubtotal()) %></td>
        </tr>
        <% } %>
        <%-- Fila de total --%>
        <tr class="table-success fw-bold">
            <td colspan="5" class="text-end">TOTAL:</td>
            <td>S/. <%= String.format("%.2f", total) %></td>
        </tr>
        </tbody>
    </table>
    <% } %>
</div>
</body>
</html>
