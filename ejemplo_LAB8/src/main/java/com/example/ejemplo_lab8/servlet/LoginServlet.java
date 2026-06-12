package com.example.ejemplo_lab8.servlet;


import com.example.ejemplo_lab8.beans.Usuario;
import com.example.ejemplo_lab8.dao.UsuarioDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {

    // GET → mostrar formulario login
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar si ya hay sesión activa
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");

        if (u != null && u.getIdUsuario() != 0) {
            // Ya está logueado → ir al home
            response.sendRedirect(
                    request.getContextPath() + "/productoServlet");
            return;
        }

        // No hay sesión → mostrar login
        RequestDispatcher view =
                request.getRequestDispatcher("login.jsp");
        view.forward(request, response);
    }

    // POST → validar credenciales
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email    = request.getParameter("email");
        String password = request.getParameter("password");

        UsuarioDao dao  = new UsuarioDao();
        Usuario usuario = dao.validarLogin(email, password);

        if (usuario != null) {
            // Login exitoso → guardar en sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setMaxInactiveInterval(30 * 60); // 30 minutos

            // Redirigir a productos
            response.sendRedirect(
                    request.getContextPath() + "/productoServlet");
        } else {
            // Login fallido → volver al login con error
            request.setAttribute("error",
                    "Correo o contraseña incorrectos.");
            RequestDispatcher view =
                    request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }
    }
}