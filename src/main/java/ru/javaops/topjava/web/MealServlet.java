package ru.javaops.topjava.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javaops.topjava.util.MealsUtil;

import java.io.IOException;
import java.time.LocalTime;

public class MealServlet extends HttpServlet {

    protected static final String MEALS_ACTION = "./jsp/meals.jsp";

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var meals = MealsUtil.filteredByStreams(MealsUtil.meals, LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY);
        req.setAttribute("meals", meals);

        log.info("Redirecting to {}", req.getRequestURI());
        req.getRequestDispatcher(MEALS_ACTION).forward(req, resp); // NOSONAR will be replaced by controllers
    }
}
