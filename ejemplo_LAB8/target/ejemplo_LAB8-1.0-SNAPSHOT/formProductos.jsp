<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 12/06/2026
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ejemplo_lab8.beans.Producto" %>
<%@ page import="com.example.ejemplo_lab8.beans.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  ArrayList<Producto> listaCategorias =
          (ArrayList<Producto>) request.getAttribute("listaCategorias");
  Usuario usuario = (Usuario) session.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Nuevo Producto</title>
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

<div class="container mt-4" style="max-width:500px">
  <h2>Nuevo Producto</h2>

  <form action="<%= request.getContextPath() %>/productoServlet"
        method="POST">

    <%-- ComboBox Categoría --%>
    <div class="mb-3">
      <label class="form-label">Categoría:</label>
      <select class="form-select" name="idCategoria" required>
        <option value="">Seleccione una categoría</option>
        <% for (Producto c : listaCategorias) { %>
        <option value="<%= c.getIdCategoria() %>">
          <%= c.getNombre() %>
        </option>
        <% } %>
      </select>
    </div>

    <div class="mb-3">
      <label class="form-label">Nombre:</label>
      <input type="text" class="form-control"
             name="nombre" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Descripción:</label>
      <input type="text" class="form-control"
             name="descripcion">
    </div>

    <div class="mb-3">
      <label class="form-label">Precio:</label>
      <input type="number" step="0.01" class="form-control"
             name="precio" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Stock:</label>
      <input type="number" class="form-control"
             name="stock" required>
    </div>

    <button type="submit" class="btn btn-success">Guardar</button>
    <a href="<%= request.getContextPath() %>/productoServlet"
       class="btn btn-secondary">Cancelar</a>
  </form>
</div>
</body>
</html>