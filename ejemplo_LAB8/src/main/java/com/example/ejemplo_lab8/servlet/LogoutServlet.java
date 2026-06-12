package com.example.ejemplo_lab8.servlet;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Cerrar sesión completamente
        HttpSession session = request.getSession();
        session.invalidate();

        // Redirigir al login
        response.sendRedirect(
                request.getContextPath() + "/loginServlet");
    }
}