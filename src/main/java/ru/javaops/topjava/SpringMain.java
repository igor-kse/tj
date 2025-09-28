package ru.javaops.topjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaops.topjava.controller.meal.MealRestController;
import ru.javaops.topjava.controller.user.AdminRestController;
import ru.javaops.topjava.model.Role;
import ru.javaops.topjava.model.User;

public class SpringMain {

    private static final Logger log =  LoggerFactory.getLogger(SpringMain.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-app.xml");

        AdminRestController adminRestController = (AdminRestController) applicationContext.getBean("adminRestController");
        adminRestController.create(new User("John Doe", "jd@email.com", "qwerty123", true,  Role.ADMIN));

        MealRestController  mealRestController = applicationContext.getBean(MealRestController.class);
        mealRestController.getAll().forEach(meal -> log.info(meal.toString()));

        applicationContext.close();
    }
}
