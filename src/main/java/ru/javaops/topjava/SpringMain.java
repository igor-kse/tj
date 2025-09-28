package ru.javaops.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaops.topjava.model.Role;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.repository.UserRepository;
import ru.javaops.topjava.service.UserService;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        userRepository.findAll();

        UserService userService = applicationContext.getBean(UserService.class);
        userService.create(new User("John Doe", "jd@email.com", "qwerty123", true,  Role.ADMIN));
        applicationContext.close();

    }
}
