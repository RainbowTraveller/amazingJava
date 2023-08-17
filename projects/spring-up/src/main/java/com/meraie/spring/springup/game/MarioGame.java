package com.meraie.spring.springup.game;

import org.springframework.stereotype.Component;

@Component
public class MarioGame implements Game {
    public void up() {
        System.out.println("Jump Up");
    }

    public void down() {
        System.out.println("Go Down");
    }

    public void left() {
        System.out.println("Turn Left");
    }

    public void right() {
        System.out.println("Turn Right");
    }
}
