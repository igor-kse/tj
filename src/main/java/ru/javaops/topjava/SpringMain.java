package ru.javaops.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaops.topjava.controller.user.AdminRestController;
import ru.javaops.topjava.model.Role;
import ru.javaops.topjava.model.User;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));

        AdminRestController adminRestController = (AdminRestController) applicationContext.getBean("adminRestController");
        adminRestController.create(new User("John Doe", "jd@email.com", "qwerty123", true,  Role.ADMIN));
        applicationContext.close();

    }
}
