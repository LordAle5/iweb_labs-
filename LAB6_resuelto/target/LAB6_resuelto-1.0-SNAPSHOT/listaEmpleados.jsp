<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 29/05/2026
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.lab6_resuelto.beans.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Recibir la lista de empleados del Servlet
    ArrayList<Employee> lista =
            (ArrayList<Employee>) request.getAttribute("lista");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FitControl - Empleados</title>
    <!-- Bootstrap para que se vea bonito -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container mt-4">

    <h1 class="mb-3">Lista de Empleados</h1>

    <!-- Botón para crear nuevo empleado -->
    <!-- Envía action=formCrear al Servlet por GET -->
    <a class="btn btn-primary mb-3"
       href="<%= request.getContextPath() %>/home?action=formCrear">
        + Nuevo Empleado
    </a>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Teléfono</th>
            <th>Puesto</th>
            <th>Salario</th>
            <th>Editar</th>
            <th>Borrar</th>
        </tr>
        </thead>
        <tbody>
        <% for (Employee e : lista) { %>
        <tr>
            <td><%= e.getEmployeeId() %></td>
            <!-- CORRECCIÓN: Ahora se concatenan firstName y lastName al momento de mostrar en la tabla -->
            <td><%= e.getFirstName() + " " + e.getLastName() %></td>
            <td><%= e.getEmail() %></td>
            <td><%= e.getPhoneNumber() %></td>
            <td><%= e.getJobId() %></td>
            <td>S/. <%= e.getSalary() %></td>

            <!-- Botón Editar → envía action=formEditar&id=X -->
            <td>
                <!-- CORRECCIÓN: Se eliminó el salto de línea que estaba dentro del atributo href. -->
                <!-- Un salto de línea en un href genera caracteres inválidos (%0A) en la URL y causa un error 404. -->
                <a class="btn btn-warning btn-sm"
                   href="<%= request.getContextPath() %>/home?action=formEditar&id=<%= e.getEmployeeId() %>">
                    Editar
                </a>
            </td>

            <!-- Botón Borrar → envía action=borrar&id=X -->
            <td>
                <!-- CORRECCIÓN: Al igual que en Editar, se eliminó el salto de línea en el href para evitar el error 404. -->
                <a class="btn btn-danger btn-sm"
                   href="<%= request.getContextPath() %>/home?action=borrar&id=<%= e.getEmployeeId() %>"
                   onclick="return confirm('¿Seguro que deseas borrar este empleado?')">
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