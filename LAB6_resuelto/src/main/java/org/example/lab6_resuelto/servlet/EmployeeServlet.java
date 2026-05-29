package org.example.lab6_resuelto.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.lab6_resuelto.beans.Employee;
import org.example.lab6_resuelto.dao.DaoEmployee;
import org.example.lab6_resuelto.dao.DaoJob;

import java.io.IOException;
import java.util.ArrayList;

// La URL será: http://localhost:8080/home
@WebServlet(name = "EmployeeServlet", value = "/home")
public class EmployeeServlet extends HttpServlet {

    // ─────────────────────────────────────────
    // GET → listar, mostrar formulario, editar, borrar
    // ─────────────────────────────────────────
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Leer el parámetro "action" de la URL
        // Si no viene → por defecto es "lista"
        String action = request.getParameter("action") == null
                ? "lista"
                : request.getParameter("action");

        DaoEmployee daoEmployee = new DaoEmployee();
        DaoJob daoJob           = new DaoJob();
        RequestDispatcher view;

        switch (action) {

            // ── Listar todos los empleados ──
            case "lista":
                ArrayList<Employee> lista = daoEmployee.listarEmployees();
                request.setAttribute("lista", lista);
                view = request.getRequestDispatcher("listaEmpleados.jsp");
                view.forward(request, response);
                break;

            // ── Mostrar formulario para CREAR ──
            case "formCrear":
                // Enviamos la lista de jobs para el select del formulario
                request.setAttribute("listaJobs", daoJob.listarJobs());
                // CORRECCIÓN: Se cambió "formEmpleado.jsp" a "formEmpleados.jsp" (en plural)
                // para que coincida con el nombre real del archivo en la carpeta webapp y evitar el error 404.
                view = request.getRequestDispatcher("formEmpleados.jsp");
                view.forward(request, response);
                break;

            // ── Mostrar formulario para EDITAR  ──
            case "formEditar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Employee empleadoEditar = daoEmployee.obtenerEmployeePorId(idEditar);

                if (empleadoEditar == null) {
                    // Si no existe, vuelve a la lista
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    // Envía el empleado y la lista de jobs al formulario
                    request.setAttribute("empleado", empleadoEditar);
                    request.setAttribute("listaJobs", daoJob.listarJobs());
                    // CORRECCIÓN: Se cambió "formEmpleado.jsp" a "formEmpleados.jsp" (en plural)
                    // para que coincida con el nombre real del archivo en la carpeta webapp y evitar el error 404.
                    view = request.getRequestDispatcher("formEmpleados.jsp");
                    view.forward(request, response);
                }
                break;

            // ── Borrar empleado ──
            case "borrar":
                int idBorrar = Integer.parseInt(request.getParameter("id"));
                daoEmployee.borrarEmployee(idBorrar);
                // Después de borrar → redirige a la lista
                response.sendRedirect(request.getContextPath() + "/home");
                break;

            // ── Por defecto → lista ──
            default:
                response.sendRedirect(request.getContextPath() + "/home");
                break;
        }
    }

    // ─────────────────────────────────────────
    // POST → guardar (crear o editar)
    // ─────────────────────────────────────────
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Para que no haya problemas con tildes y ñ
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action") == null
                ? "lista"
                : request.getParameter("action");

        DaoEmployee daoEmployee = new DaoEmployee();

        switch (action) {

            // ── Guardar nuevo empleado ──
            case "crear":
                String firstName   = request.getParameter("firstName");
                String lastName    = request.getParameter("lastName");
                String email       = request.getParameter("email");
                String password    = request.getParameter("password");
                String phoneNumber = request.getParameter("phoneNumber");
                String hireDate    = request.getParameter("hireDate");
                String jobId       = request.getParameter("jobId");
                double salary      = Double.parseDouble(request.getParameter("salary"));
                int departmentId   = Integer.parseInt(request.getParameter("departmentId"));

                daoEmployee.crearEmployee(firstName, lastName, email,
                        password, phoneNumber, hireDate,
                        jobId, salary, departmentId);

                // Después de crear → redirige a la lista
                response.sendRedirect(request.getContextPath() + "/home");
                break;

            // ── Actualizar empleado existente ──
            case "editar":
                int employeeId      = Integer.parseInt(request.getParameter("employeeId"));
                String fName        = request.getParameter("firstName");
                String lName        = request.getParameter("lastName");
                String mail         = request.getParameter("email");
                String pass         = request.getParameter("password");
                String phone        = request.getParameter("phoneNumber");
                String hDate        = request.getParameter("hireDate");
                String jId          = request.getParameter("jobId");
                double sal          = Double.parseDouble(request.getParameter("salary"));
                int deptId          = Integer.parseInt(request.getParameter("departmentId"));

                daoEmployee.editarEmployee(employeeId, fName, lName, mail,
                        pass, phone, hDate, jId, sal, deptId);

                // Después de editar → redirige a la lista
                response.sendRedirect(request.getContextPath() + "/home");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/home");
                break;
        }
    }
}