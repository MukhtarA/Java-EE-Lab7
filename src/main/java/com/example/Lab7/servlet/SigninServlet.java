package com.example.Lab7.servlet;

import com.example.Lab7.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "signin", value = "/signin")
public class SigninServlet extends HttpServlet {
    private UserService userService;

    public void init() { userService = new UserService(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signin").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String path = req.getContextPath() + "/signin";

        if(userService.checkUser(username, password)){
            HttpSession session = req.getSession();
            session.setAttribute("auth", username);
            path = req.getContextPath() + "/";
        }

        resp.sendRedirect(path);
    }
}
