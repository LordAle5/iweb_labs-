<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 29/05/2026
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.example.lab6_resuelto.beans.Employee" %>
<%@ page import="org.example.lab6_resuelto.beans.Job" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Intentar recibir el empleado (solo llega si es editar)
    Employee empleado = (Employee) request.getAttribute("empleado");
    ArrayList<Job> listaJobs =
            (ArrayList<Job>) request.getAttribute("listaJobs");

    // Determinar si es crear o editar
    boolean esEditar = (empleado != null);

    // Si es editar → action=editar, si es crear → action=crear
    String action = esEditar ? "editar" : "crear";
    String titulo = esEditar ? "Editar Empleado" : "Nuevo Empleado";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= titulo %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container mt-4">

    <h1 class="mb-3"><%= titulo %></h1>

    <!--
        action → va al Servlet por POST
        ?action=crear o ?action=editar va por GET en la URL
    -->
    <form action="<%= request.getContextPath() %>/home?action=<%= action %>"
          method="POST">

        <!-- Si es editar → enviamos el ID oculto -->
        <% if (esEditar) { %>
        <input type="hidden" name="employeeId"
               value="<%= empleado.getEmployeeId() %>">
        <% } %>

        <!-- Primera parte del nombre -->
        <div class="mb-3">
            <label>Nombre</label>
            <!-- CORRECCIÓN: Se reemplazó el uso de '.split(" ")' por el método directo getFirstName() -->
            <!-- Esto evita que la aplicación falle si el usuario tiene un nombre compuesto -->
            <input type="text" class="form-control" name="firstName"
                   value="<%= esEditar ? empleado.getFirstName() : "" %>"
                   required>
        </div>

        <!-- Apellido -->
        <div class="mb-3">
            <label>Apellido</label>
            <!-- CORRECCIÓN: Se reemplazó el uso de '.split(" ")' por el método directo getLastName() -->
            <input type="text" class="form-control" name="lastName"
                   value="<%= esEditar ? empleado.getLastName() : "" %>"
                   required>
        </div>

        <!-- Email -->
        <div class="mb-3">
            <label>Email</label>
            <input type="email" class="form-control" name="email"
                   value="<%= esEditar ? empleado.getEmail() : "" %>"
                   required>
        </div>

        <!-- Password -->
        <div class="mb-3">
            <label>Password</label>
            <input type="password" class="form-control" name="password"
                   value="<%= esEditar ? empleado.getPassword() : "" %>"
                   required>
        </div>

        <!-- Teléfono -->
        <div class="mb-3">
            <label>Teléfono</label>
            <input type="text" class="form-control" name="phoneNumber"
                   value="<%= esEditar ? empleado.getPhoneNumber() : "" %>">
        </div>

        <!-- Fecha de contratación -->
        <div class="mb-3">
            <label>Fecha de contratación</label>
            <input type="date" class="form-control" name="hireDate"
                   value="<%= esEditar ? empleado.getHireDate().substring(0,10) : "" %>"
                   required>
        </div>

        <!-- Puesto de trabajo (select con lista de jobs) -->
        <div class="mb-3">
            <label>Puesto de trabajo</label>
            <select class="form-select" name="jobId" required>
                <% for (Job job : listaJobs) { %>
                <option value="<%= job.getJobId() %>"
                        <%= esEditar && empleado.getJobId()
                                .equals(job.getJobId()) ? "selected" : "" %>>
                    <%= job.getJobTitle() %>
                </option>
                <% } %>
            </select>
        </div>

        <!-- Salario -->
        <div class="mb-3">
            <label>Salario</label>
            <input type="number" step="0.01" class="form-control"
                   name="salary"
                   value="<%= esEditar ? empleado.getSalary() : "" %>"
                   required>
        </div>

        <!-- Departamento -->
        <div class="mb-3">
            <label>ID Departamento</label>
            <input type="number" class="form-control" name="departmentId"
                   value="<%= esEditar ? empleado.getDepartmentId() : "" %>"
                   required>
        </div>

        <!-- Botones -->
        <button type="submit" class="btn btn-success">
            <%= esEditar ? "Actualizar" : "Guardar" %>
        </button>
        <a href="<%= request.getContextPath() %>/home"
           class="btn btn-secondary">
            Cancelar
        </a>

    </form>
</div>
</body>
</html>