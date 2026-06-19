package com.example.lab9_practicando.servlet;
import com.example.lab9_practicando.dao.EventoDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "EventoServlet", value = "/eventoServlet")
public class EventoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action") == null
                ? "lista" : request.getParameter("action");

        EventoDao dao = new EventoDao();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                request.setAttribute("listaEventos", dao.listarEventosDto());
                view = request.getRequestDispatcher("listaEventos.jsp");
                view.forward(request, response);
                break;

            case "formCrear":
                view = request.getRequestDispatcher("formEvento.jsp");
                view.forward(request, response);
                break;

            case "borrar":
                int idEvento = Integer.parseInt(request.getParameter("id"));
                dao.borrarEvento(idEvento);
                response.sendRedirect(request.getContextPath() + "/eventoServlet");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/eventoServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String titulo      = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fecha       = request.getParameter("fecha");
        String lugar       = request.getParameter("lugar");

        // ── VALIDACIÓN: fecha evento >= fecha actual ──
        LocalDate fechaEvento = LocalDate.parse(fecha);
        LocalDate hoy = LocalDate.now();

        if (fechaEvento.isBefore(hoy)) {
            request.setAttribute("error",
                    "La fecha del evento debe ser hoy o posterior.");
            RequestDispatcher view =
                    request.getRequestDispatcher("formEvento.jsp");
            view.forward(request, response);
            return;
        }

        EventoDao dao = new EventoDao();
        dao.crearEvento(titulo, descripcion, fecha, lugar);

        // Redirigir al listado
        response.sendRedirect(request.getContextPath() + "/eventoServlet");
    }
}