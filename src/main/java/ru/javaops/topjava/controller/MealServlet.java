package ru.javaops.topjava.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaops.topjava.MealTempData;
import ru.javaops.topjava.controller.meal.MealRestController;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.util.SecurityUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {

    protected static final String MEALS_LIST_ACTION = "/meals";
    protected static final String MEALS_LIST_JSP = "./jsp/meals.jsp";
    protected static final String MEAL_FORM_JSP = "./jsp/mealForm.jsp";

    private static final String MEAL = "meal";
    private static final String NOW = "now";
    private static final String ID = "id";
    private static final String DATE_TIME = "dateTime";
    private static final String DESCRIPTION = "description";
    private static final String CALORIES = "calories";

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private static final ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-app.xml");

    private final transient MealRestController mealController;

    public MealServlet() {
        super();
        mealController = applicationContext.getBean(MealRestController.class);
        MealTempData.meals.forEach(meal -> mealController.save(new Meal(meal)));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add" -> {
                request.setAttribute(MEAL, null);
                request.setAttribute(NOW, LocalDateTime.now());
                log.info("Forwarding to new meal form");
                request.getRequestDispatcher(MEAL_FORM_JSP).forward(request, response); // NOSONAR will be covered by exception handler
            }
            case "edit" -> {
                int id = Integer.parseInt(request.getParameter("id")); // NOSONAR
                log.info("Getting meal by id: {}", id);
                Meal meal = mealController.get(id);
                request.setAttribute(MEAL, meal);
                request.setAttribute(NOW, LocalDateTime.now());

                log.info("Forwarding to edit meal form");
                request.getRequestDispatcher(MEAL_FORM_JSP).forward(request, response); // NOSONAR
            }
            case "delete" -> {
                int id = Integer.parseInt(request.getParameter(ID)); // NOSONAR
                mealController.delete(id);
                response.sendRedirect(request.getContextPath() + MEALS_LIST_ACTION);  // NOSONAR
            }
            case null, default -> handleGetAllMeals(request, response); // NOSONAR
        }
    }

    public void handleGetAllMeals(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Getting all meals");
        var meals = mealController.getAll();
        request.setAttribute("meals", meals);

        log.info("Redirecting to {}", request.getRequestURI());
        request.getRequestDispatcher(MEALS_LIST_JSP).forward(request, response); // NOSONAR
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8);

        String id =  request.getParameter(ID);
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter(DATE_TIME));
        String description = request.getParameter(DESCRIPTION);
        int calories = Integer.parseInt(request.getParameter(CALORIES)); // NOSONAR

        var meal = new Meal(null, dateTime, description, calories, SecurityUtil.authUserId());
        if (id == null || id.isBlank()) {
            log.info("Saving meal by description: {}, datetime: {}, calories: {}", description, dateTime, calories);
            mealController.save(meal);
        } else {
            log.info("Updating meal by id: {}, description: {}, datetime: {}, calories: {}", id, description, dateTime, calories);
            var longId = Long.parseLong(id); // NOSONAR
            meal.setId(longId);
            mealController.update(meal, longId);
        }
        response.sendRedirect(request.getContextPath() + MEALS_LIST_ACTION);  // NOSONAR
    }

    @Override
    public void destroy() {
        super.destroy();
        applicationContext.close();
    }
}
