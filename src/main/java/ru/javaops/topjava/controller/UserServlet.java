package ru.javaops.topjava.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UserServlet extends HttpServlet {

    protected static final String USERS_ACTION = "./jsp/users.jsp";

    private static final Logger log = LoggerFactory.getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Redirecting to {}", req.getRequestURI());
        resp.sendRedirect(USERS_ACTION); // NOSONAR will be replaced by controllers
    }
}
