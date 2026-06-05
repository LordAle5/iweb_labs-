package com.example.ejemplo_lab7.servlet;

import com.example.ejemplo_lab7.dao.*;
import com.example.ejemplo_lab7.beans.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

// URL: http://localhost:8080/home
@WebServlet(name = "MascotaServlet", value = "/home")
public class MascotaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Leer el action de la URL, por defecto "lista"
        String action = request.getParameter("action") == null
                ? "lista" : request.getParameter("action");

        MascotaDao mascotaDao     = new MascotaDao();
        EspecieDao especieDao     = new EspecieDao();
        VeterinarioDao vetDao     = new VeterinarioDao();
        DuenoDao duenoDao         = new DuenoDao();
        RequestDispatcher view;

        switch (action) {

            // ── Listar todas las mascotas ──
            case "lista":
                ArrayList<Mascota> lista = mascotaDao.listarMascotas();
                request.setAttribute("lista", lista);
                // Enviar lista de especies para el ComboBox de filtro
                request.setAttribute("listaEspecies",
                        especieDao.listarEspecies());
                view = request.getRequestDispatcher("listaMascota.jsp");
                view.forward(request, response);
                break;

            // ── Filtrar mascotas por especie (Pregunta 4) ──
            case "filtrar":
                int idEspecie = Integer.parseInt(
                        request.getParameter("idEspecie"));
                ArrayList<Mascota> listaFiltrada;
                if (idEspecie == 0) {
                    listaFiltrada = mascotaDao.listarMascotas();
                } else {
                    listaFiltrada = mascotaDao.listarPorEspecie(idEspecie);
                }
                request.setAttribute("lista", listaFiltrada);
                request.setAttribute("listaEspecies",
                        especieDao.listarEspecies());
                // Guardamos la especie seleccionada para marcarla en el ComboBox
                request.setAttribute("especieSeleccionada", idEspecie);
                view = request.getRequestDispatcher("listaMascota.jsp");
                view.forward(request, response);
                break;

            // ── Mostrar formulario crear mascota ──
            case "formCrear":
                // Enviamos las 3 listas para los 3 ComboBoxes del formulario
                request.setAttribute("listaEspecies",
                        especieDao.listarEspecies());
                request.setAttribute("listaVeterinarios",
                        vetDao.listarVeterinarios());
                request.setAttribute("listaDuenos",
                        duenoDao.listarDuenos());
                view = request.getRequestDispatcher("formMascota.jsp");
                view.forward(request, response);
                break;

            // ── Borrar mascota ──
            case "borrar":
                int idBorrar = Integer.parseInt(
                        request.getParameter("id"));
                mascotaDao.borrarMascota(idBorrar);
                // Después de borrar → redirige a la lista
                response.sendRedirect(
                        request.getContextPath() + "/home");
                break;

            default:
                response.sendRedirect(
                        request.getContextPath() + "/home");
                break;
        }
    }

    // ── POST → guardar nueva mascota ──
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        MascotaDao mascotaDao = new MascotaDao();

        // Recibir datos del formulario
        String nombre     = request.getParameter("nombre");
        int edad          = Integer.parseInt(
                request.getParameter("edad"));
        double peso       = Double.parseDouble(
                request.getParameter("peso"));
        int especieId     = Integer.parseInt(
                request.getParameter("especieId"));
        int veterinarioId = Integer.parseInt(
                request.getParameter("veterinarioId"));
        int duenoId       = Integer.parseInt(
                request.getParameter("duenoId"));

        // Llamar al método crear del DAO
        mascotaDao.crearMascota(nombre, edad, peso,
                especieId, veterinarioId, duenoId);

        // Redirigir a la lista después de guardar
        response.sendRedirect(request.getContextPath() + "/home");
    }
}