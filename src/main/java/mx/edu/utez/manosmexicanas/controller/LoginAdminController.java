package mx.edu.utez.manosmexicanas.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.manosmexicanas.dao.UserDao;
import mx.edu.utez.manosmexicanas.model.Usuario;
import mx.edu.utez.manosmexicanas.utils.DbConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "LoginAdminController",value = "/login")
public class LoginAdminController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String password =  req.getParameter("pass");
        UserDao userDao = new UserDao();
        Usuario usuario = new Usuario();
        try(Connection conn = DbConnectionManager.getConnection()){
            var result = userDao.loginAdmin(correo,password);
            if (result){
                res.setStatus(200);
                req.getSession().setAttribute("mensaje","Bienvenido de vuelta Erick");
                res.sendRedirect("./dashboard/dashboard.jsp");
               ;
            }
            if (!result){
                res.setStatus(401);
                res.sendRedirect("loginAdmin.jsp");
            }
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
