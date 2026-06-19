package com.example.lab9_practicando.servlet;

import com.example.lab9_practicando.beans.Evento;
import com.example.lab9_practicando.dao.EventoDao;
import com.example.lab9_practicando.dao.TicketDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TicketServlet", value = "/ticketServlet")
public class TicketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action") == null
                ? "lista" : request.getParameter("action");

        TicketDao ticketDao = new TicketDao();
        EventoDao eventoDao = new EventoDao();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                request.setAttribute("listaTickets",
                        ticketDao.listarTicketsDto());
                view = request.getRequestDispatcher("listaTickets.jsp");
                view.forward(request, response);
                break;

            case "formCrear":
                // Enviar eventos para el selector
                request.setAttribute("listaEventos",
                        eventoDao.listarEventosParaSelector());
                view = request.getRequestDispatcher("formTicket.jsp");
                view.forward(request, response);
                break;

            case "borrar":
                int idTicket = Integer.parseInt(request.getParameter("id"));
                ticketDao.borrarTicket(idTicket);
                response.sendRedirect(
                        request.getContextPath() + "/ticketServlet");
                break;

            default:
                response.sendRedirect(
                        request.getContextPath() + "/ticketServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int idEvento        = Integer.parseInt(request.getParameter("idEvento"));
        String nombre        = request.getParameter("nombre");
        double precio        = Double.parseDouble(request.getParameter("precio"));
        int cupoTotal        = Integer.parseInt(request.getParameter("cupoTotal"));
        int cupoDisponible   = Integer.parseInt(request.getParameter("cupoDisponible"));

        // ── VALIDACIONES ──
        String error = null;
        if (precio < 0) {
            error = "El precio debe ser mayor o igual a 0.";
        } else if (cupoTotal < 0) {
            error = "El cupo total debe ser mayor o igual a 0.";
        } else if (cupoDisponible < 0) {
            error = "El cupo disponible debe ser mayor o igual a 0.";
        } else if (cupoDisponible > cupoTotal) {
            error = "El cupo disponible no puede ser mayor al cupo total.";
        }

        if (error != null) {
            EventoDao eventoDao = new EventoDao();
            request.setAttribute("error", error);
            request.setAttribute("listaEventos",
                    eventoDao.listarEventosParaSelector());
            RequestDispatcher view =
                    request.getRequestDispatcher("formTicket.jsp");
            view.forward(request, response);
            return;
        }

        TicketDao dao = new TicketDao();
        dao.crearTicket(idEvento, nombre, precio, cupoTotal, cupoDisponible);

        // Redirigir al listado
        response.sendRedirect(request.getContextPath() + "/ticketServlet");
    }
}