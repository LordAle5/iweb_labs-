<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.dto.DocenteDTO" %>
<%@ page import="com.example.dao.DocenteDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Obtenemos la lista de docentes directamente aqui
    // para llenar el menu desplegable (combobox)
    DocenteDAO docenteDAO = new DocenteDAO();
    ArrayList<DocenteDTO> listaDocentes = docenteDAO.listarDocentes();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Curso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5" style="max-width:500px">

    <div class="card p-4 shadow">
        <h2 class="text-center mb-4">Registro de Curso Nuevo</h2>

        <!-- El formulario envia los datos al CursoServlet mediante POST -->
        <form action="<%= request.getContextPath() %>/registrarCurso" method="POST">

            <div class="mb-3">
                <label class="form-label">Código:</label>
                <input type="text" class="form-control" name="codigo"
                       placeholder="Ej: TEL131" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Nombre:</label>
                <input type="text" class="form-control" name="nombre"
                       placeholder="Ej: Ingeniería Web" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Créditos:</label>
                <input type="number" class="form-control" name="creditos"
                       placeholder="Ej: 4" required>
            </div>

            <!-- Menu desplegable (combobox) con los docentes obtenidos de la BD -->
            <div class="mb-3">
                <label class="form-label">Docente:</label>
                <select class="form-select" name="idDocente" required>
                    <option value="">Seleccione un docente</option>
                    <% for (DocenteDTO d : listaDocentes) { %>
                        <option value="<%= d.getIdDocente() %>">
                            <%= d.getIdDocente() %> - <%= d.getNombre() %>
                        </option>
                    <% } %>
                </select>
            </div>

            <button type="submit" class="btn btn-dark w-100">Registrar Curso</button>

        </form>
    </div>

</div>
</body>
</html>
