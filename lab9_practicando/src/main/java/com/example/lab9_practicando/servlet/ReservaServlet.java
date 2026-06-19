package com.example.lab9_practicando.servlet;
import com.example.lab9_practicando.beans.TicketTipo;
import com.example.lab9_practicando.dao.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ReservaServlet", value = "/reservaServlet")
public class ReservaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action") == null
                ? "lista" : request.getParameter("action");

        ReservaDao reservaDao = new ReservaDao();
        UsuarioDao usuarioDao = new UsuarioDao();
        EventoDao eventoDao   = new EventoDao();
        TicketDao ticketDao   = new TicketDao();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                request.setAttribute("listaReservas",
                        reservaDao.listarReservasDto());
                view = request.getRequestDispatcher("listaReservas.jsp");
                view.forward(request, response);
                break;

            case "formCrear":
                // Enviar usuarios, eventos y tickets para los 3 selectores
                request.setAttribute("listaUsuarios",
                        usuarioDao.listarUsuarios());
                request.setAttribute("listaEventos",
                        eventoDao.listarEventosParaSelector());
                request.setAttribute("listaTickets",
                        ticketDao.listarTodos());
                view = request.getRequestDispatcher("formReserva.jsp");
                view.forward(request, response);
                break;

            case "borrar":
                int idItem = Integer.parseInt(request.getParameter("id"));
                // Obtener datos antes de borrar (para devolver el cupo)
                int[] datos = reservaDao.obtenerDatosParaCancelar(idItem);
                int idTicketTipo = datos[0];
                int cantidad     = datos[1];

                reservaDao.borrarReserva(idItem);
                // Devolver cupo al ticket
                ticketDao.aumentarCupo(idTicketTipo, cantidad);

                response.sendRedirect(
                        request.getContextPath() + "/reservaServlet");
                break;

            default:
                response.sendRedirect(
                        request.getContextPath() + "/reservaServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int idUsuario     = Integer.parseInt(request.getParameter("idUsuario"));
        int idTicketTipo  = Integer.parseInt(request.getParameter("idTicketTipo"));
        int cantidad      = Integer.parseInt(request.getParameter("cantidad"));

        TicketDao ticketDao = new TicketDao();
        TicketTipo ticket = ticketDao.obtenerPorId(idTicketTipo);

        // ── VALIDACIONES ──
        String error = null;

        if (ticket == null) {
            error = "El ticket seleccionado no existe.";
        } else if (ticket.getCupoDisponible() <= 0) {
            error = "No hay cupo disponible para este ticket.";
        } else if (cantidad > ticket.getCupoDisponible()) {
            error = "La cantidad solicitada supera el cupo disponible.";
        }

        // Validar fecha del evento >= fecha actual
        if (error == null) {
            EventoDao eventoDao = new EventoDao();
            // Buscamos el evento asociado al ticket para validar la fecha
            for (com.example.lab9_practicando.beans.Evento ev :
                    eventoDao.listarEventosParaSelector()) {
                if (ev.getIdEvento() == ticket.getIdEvento()) {
                    LocalDate fechaEvento = LocalDate.parse(ev.getFecha());
                    if (fechaEvento.isBefore(LocalDate.now())) {
                        error = "No se puede reservar para un evento ya pasado.";
                    }
                    break;
                }
            }
        }

        if (error != null) {
            UsuarioDao usuarioDao = new UsuarioDao();
            EventoDao eventoDao   = new EventoDao();
            request.setAttribute("error", error);
            request.setAttribute("listaUsuarios", usuarioDao.listarUsuarios());
            request.setAttribute("listaEventos",
                    eventoDao.listarEventosParaSelector());
            request.setAttribute("listaTickets", ticketDao.listarTodos());
            RequestDispatcher view =
                    request.getRequestDispatcher("formReserva.jsp");
            view.forward(request, response);
            return;
        }

        ReservaDao reservaDao = new ReservaDao();
        reservaDao.crearReserva(idUsuario, idTicketTipo, cantidad);

        // Disminuir cupo disponible
        ticketDao.disminuirCupo(idTicketTipo, cantidad);

        response.sendRedirect(request.getContextPath() + "/reservaServlet");
    }
}