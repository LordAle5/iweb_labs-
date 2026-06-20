<%@ page import="com.example.dto.CursoDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Recuperar el objeto DTO completo enviado por el Servlet
    CursoDTO curso = (CursoDTO) request.getAttribute("curso");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Curso Registrado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5" style="max-width:500px">

    <div class="card p-4 shadow">
        <h4 class="text-success mb-3">✅ CURSO REGISTRADO EXITOSAMENTE</h4>
        <hr>

        <table class="table table-borderless mb-0">
            <tr>
                <td class="text-muted">Código:</td>
                <td><b><%= curso.getCodigo() %></b></td>
            </tr>
            <tr>
                <td class="text-muted">Nombre:</td>
                <td><b><%= curso.getNombre() %></b></td>
            </tr>
            <tr>
                <td class="text-muted">Créditos:</td>
                <td><b><%= curso.getCreditos() %></b></td>
            </tr>
            <tr>
                <td class="text-muted">Docente Asignado:</td>
                <td>
                    <b>
                        <%= curso.getDocente().getNombre() %>
                        (<%= curso.getDocente().getEspecialidad() %>)
                    </b>
                </td>
            </tr>
        </table>

        <a href="<%= request.getContextPath() %>/index.jsp"
           class="btn btn-dark w-100 mt-3">
            Registrar otro curso
        </a>
    </div>

</div>
</body>
</html>
