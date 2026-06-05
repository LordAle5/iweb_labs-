<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ejemplo_lab7.beans.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  // Recibir las 3 listas para los 3 ComboBoxes
  ArrayList<Especie> listaEspecies =
          (ArrayList<Especie>) request.getAttribute("listaEspecies");
  ArrayList<Veterinario> listaVeterinarios =
          (ArrayList<Veterinario>) request.getAttribute("listaVeterinarios");
  ArrayList<Dueno> listaDuenos =
          (ArrayList<Dueno>) request.getAttribute("listaDuenos");
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Nueva Mascota</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet">
</head>
<body>
<div class="container mt-4" style="max-width:500px">

  <h2 class="mb-3">Nueva Mascota</h2>

  <!-- POST → el Servlet recibe en doPost() -->
  <form action="<%= request.getContextPath() %>/home"
        method="POST">

    <!-- Nombre -->
    <div class="mb-3">
      <label class="form-label"><b>Nombre:</b></label>
      <input type="text" class="form-control"
             name="nombre" required>
    </div>

    <!-- Edad -->
    <div class="mb-3">
      <label class="form-label"><b>Edad:</b></label>
      <input type="number" class="form-control"
             name="edad" required>
    </div>

    <!-- Peso -->
    <div class="mb-3">
      <label class="form-label"><b>Peso (kg):</b></label>
      <input type="number" step="0.01" class="form-control"
             name="peso" required>
    </div>

    <!-- ── ComboBox Especie ── -->
    <!-- value → el ID que va al Servlet -->
    <!-- texto → el nombre que ve el usuario -->
    <div class="mb-3">
      <label class="form-label"><b>Especie:</b></label>
      <select class="form-select" name="especieId" required>
        <option value="">Seleccione una especie</option>
        <% for (Especie e : listaEspecies) { %>
        <option value="<%= e.getIdEspecie() %>">
          <%= e.getNombre() %>
        </option>
        <% } %>
      </select>
    </div>

    <!-- ── ComboBox Veterinario ── -->
    <div class="mb-3">
      <label class="form-label"><b>Veterinario:</b></label>
      <select class="form-select" name="veterinarioId" required>
        <option value="">Seleccione un veterinario</option>
        <% for (Veterinario v : listaVeterinarios) { %>
        <option value="<%= v.getIdVeterinario() %>">
          <%= v.getNombre() %>
        </option>
        <% } %>
      </select>
    </div>

    <!-- ── ComboBox Dueño ── -->
    <div class="mb-3">
      <label class="form-label"><b>Dueño:</b></label>
      <select class="form-select" name="duenoId" required>
        <option value="">Seleccione un dueño</option>
        <% for (Dueno d : listaDuenos) { %>
        <option value="<%= d.getIdDueno() %>">
          <%= d.getNombre() %>
        </option>
        <% } %>
      </select>
    </div>

    <!-- Botones -->
    <button type="submit" class="btn btn-success">
      Guardar
    </button>
    <a href="<%= request.getContextPath() %>/home"
       class="btn btn-secondary">
      Cancelar
    </a>

  </form>
</div>
</body>
</html>