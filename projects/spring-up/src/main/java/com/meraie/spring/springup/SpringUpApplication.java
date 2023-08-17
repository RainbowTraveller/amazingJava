package com.meraie.spring.springup;

import com.meraie.spring.springup.enterprise.example.web.WebController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringUpApplication {

    public static void main(String[] args) {
        // This is tight coupling when we create exclusively instance of various classes and use them
//        MarioGame marioGame = new MarioGame();
//        Game game = new SuperContraGame();

        // Loose coupling to some extent when we use interface
//        Game game = new PacmanGame();
//        GameRunner gameRunner = new GameRunner(game);

        ConfigurableApplicationContext context = SpringApplication.run(SpringUpApplication.class, args);
//        GameRunner gameRunner = context.getBean(GameRunner.class);
//        gameRunner.run();

        WebController controller = context.getBean(WebController.class);
        System.out.println(controller.returnValueFromBusinessService());
    }
}
