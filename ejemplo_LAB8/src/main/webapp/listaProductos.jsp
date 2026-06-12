<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 12/06/2026
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ejemplo_lab8.dto.ProductoDto" %>
<%@ page import="com.example.ejemplo_lab8.beans.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<ProductoDto> lista =
            (ArrayList<ProductoDto>) request.getAttribute("listaProductos");

    // Obtener usuario de la sesión para el Navbar
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    // Flash message
    String mensaje = (String) session.getAttribute("mensaje");
    if (mensaje != null) session.removeAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>

<%-- ── NAVBAR ── --%>
<nav class="navbar navbar-dark bg-dark px-3">
    <span class="navbar-brand">
        Tienda –
        <%= usuario != null ? usuario.getNombreCompleto() : "" %>
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
    <h2>Lista de Productos</h2>

    <%-- Flash message --%>
    <% if (mensaje != null) { %>
    <div class="alert alert-success"><%= mensaje %></div>
    <% } %>

    <%-- Botón crear producto --%>
    <a class="btn btn-success mb-3"
       href="<%= request.getContextPath() %>/productoServlet?action=formCrear">
        + Nuevo Producto
    </a>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Categoría</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <% for (ProductoDto p : lista) { %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNombre() %></td>
            <td><%= p.getCategoriaNombre() %></td>
            <td>S/. <%= String.format("%.2f", p.getPrecio()) %></td>
            <td><%= p.getStock() %></td>
            <td>
                <%-- Botón añadir al carrito --%>
                <a class="btn btn-primary btn-sm"
                   href="<%= request.getContextPath() %>
                             /carritoServlet?action=agregar&idProducto=<%= p.getId() %>">
                    🛒 Añadir
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>