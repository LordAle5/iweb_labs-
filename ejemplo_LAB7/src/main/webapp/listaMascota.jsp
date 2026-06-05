<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ejemplo_lab7.beans.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Recibir lista de mascotas del Servlet
    ArrayList<Mascota> lista =
            (ArrayList<Mascota>) request.getAttribute("lista");

    // Recibir lista de especies para el ComboBox de filtro
    ArrayList<Especie> listaEspecies =
            (ArrayList<Especie>) request.getAttribute("listaEspecies");

    // Especie actualmente seleccionada en el filtro
    Integer especieSeleccionada =
            (Integer) request.getAttribute("especieSeleccionada");
    if (especieSeleccionada == null) especieSeleccionada = 0;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Veterinaria - Mascotas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container mt-4">

    <h2 class="mb-3">Lista de Mascotas</h2>

    <!-- Botón Nueva Mascota → va a formCrear -->
    <a class="btn btn-success mb-3"
       href="<%= request.getContextPath() %>/home?action=formCrear">
        Nueva Mascota
    </a>

    <!-- ── ComboBox de filtro por especie (Pregunta 4) ── -->
    <!-- Cuando cambia el select → envía el formulario automáticamente -->
    <form method="GET"
          action="<%= request.getContextPath() %>/home"
          class="mb-3 d-flex align-items-center gap-2">
        <input type="hidden" name="action" value="filtrar">
        <label>Filtrar por especie:</label>
        <select name="idEspecie" class="form-select w-auto"
                onchange="this.form.submit()">
            <!-- Opción para ver todas -->
            <option value="0">Todas las especies</option>
            <% for (Especie esp : listaEspecies) { %>
            <option value="<%= esp.getIdEspecie() %>"
            <%-- Si es la especie seleccionada → marked selected --%>
                    <%= especieSeleccionada == esp.getIdEspecie()
                            ? "selected" : "" %>>
                <%= esp.getNombre() %>
            </option>
            <% } %>
        </select>
    </form>

    <!-- ── Tabla de mascotas ── -->
    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Edad</th>
            <th>Peso</th>
            <th>Especie</th>
            <th>Veterinario</th>
            <th>Dueño</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <% for (Mascota m : lista) { %>
        <tr>
            <td><%= m.getIdMascota() %></td>
            <td><%= m.getNombre() %></td>
            <td><%= m.getEdad() %></td>
            <td><%= m.getPeso() %></td>
            <%-- Bean dentro de Bean → getNombre() del objeto --%>
            <td><%= m.getEspecie().getNombre() %></td>
            <td><%= m.getVeterinario().getNombre() %></td>
            <td><%= m.getDueno().getNombre() %></td>
            <td>
                <!-- Botón borrar → action=borrar&id=X -->
                <a class="btn btn-danger btn-sm"
                   href="<%= request.getContextPath() %>/home?action=borrar&id=<%= m.getIdMascota() %>"
                   onclick="return confirm('¿Borrar esta mascota?')">
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