<%--
  Created by IntelliJ IDEA.
  User: ALEJANDRO
  Date: 19/06/2026
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-dark bg-dark px-3 mb-3">
<span class="navbar-brand">Sistema de Eventos</span>
<div>
    <a class="btn btn-outline-light btn-sm me-2"
       href="<%= request.getContextPath() %>/eventoServlet">
        Eventos
    </a>
    <a class="btn btn-outline-light btn-sm me-2"
       href="<%= request.getContextPath() %>/ticketServlet">
        Tickets
    </a>
    <a class="btn btn-outline-light btn-sm"
       href="<%= request.getContextPath() %>/reservaServlet">
        Reservas
    </a>
</div>
</nav>