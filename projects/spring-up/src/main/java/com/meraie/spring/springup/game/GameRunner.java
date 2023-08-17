package com.meraie.spring.springup.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
    @Autowired
    private Game game;

    /*
    This is Dependency Injection of the type : Constructor based
    Variable is mentioned to be autowired marking the dependency and it is populated using
    the constructor
     */
    public GameRunner(Game game) {
        this.game = game;
    }

    public void run() {
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
