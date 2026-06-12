package com.example.ejemplo_lab8.servlet;

import com.example.ejemplo_lab8.beans.Usuario;
import com.example.ejemplo_lab8.dao.ProductoDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ProductoServlet", value = "/productoServlet")
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar sesión activa
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null || u.getIdUsuario() == 0) {
            response.sendRedirect(
                    request.getContextPath() + "/loginServlet");
            return;
        }

        String action = request.getParameter("action") == null
                ? "lista" : request.getParameter("action");

        ProductoDao dao = new ProductoDao();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                // Enviar DTO al JSP (no Bean)
                request.setAttribute("listaProductos",
                        dao.listarProductosDto());
                view = request.getRequestDispatcher("listaProductos.jsp");
                view.forward(request, response);
                break;

            case "formCrear":
                // Enviar categorías para el ComboBox
                request.setAttribute("listaCategorias",
                        dao.listarCategorias());
                view = request.getRequestDispatcher("formProducto.jsp");
                view.forward(request, response);
                break;

            default:
                response.sendRedirect(
                        request.getContextPath() + "/productoServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Verificar sesión
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");
        if (u == null || u.getIdUsuario() == 0) {
            response.sendRedirect(
                    request.getContextPath() + "/loginServlet");
            return;
        }

        int idCategoria = Integer.parseInt(
                request.getParameter("idCategoria"));
        String nombre   = request.getParameter("nombre");
        String desc     = request.getParameter("descripcion");
        double precio   = Double.parseDouble(
                request.getParameter("precio"));
        int stock       = Integer.parseInt(
                request.getParameter("stock"));

        ProductoDao dao = new ProductoDao();
        dao.crearProducto(idCategoria, nombre, desc, precio, stock);

        // Mensaje de éxito usando sesión (flash message)
        session.setAttribute("mensaje", "Producto creado correctamente.");

        response.sendRedirect(
                request.getContextPath() + "/productoServlet");
    }
}