package com.github.ksgfk.snake.game;

import java.util.Random;

/**
 * KSGFK 创建于 2018/10/27.
 */
class SnakeFood {
    int x;
    int y;

    SnakeFood() {
        Random random = new Random();
        x = random.nextInt(20) * 20 + 20;
        y = random.nextInt(20) * 20 + 20;
    }
}
