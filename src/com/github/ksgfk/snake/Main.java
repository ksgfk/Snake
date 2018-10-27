package com.github.ksgfk.snake;

import com.github.ksgfk.snake.game.Snake;
import com.github.ksgfk.snake.util.Time;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KSGFK 创建于 2018/10/21.
 */
public class Main {

    private static int x = 800;
    private static int y = 480;

    public static void main(String[] args) {

        Snake snake = new Snake();
        Thread thread = new Thread(snake);
        JFrame jFrame = new JFrame();

        jFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                System.out.println(Time.get() + "[检测按键]:" + keyCode);
                /*让蛇不能倒着走*/
                if (keyCode == 37 & Snake.getDirection() == 39) {
                    System.out.println(Time.get() + "[检测按键]:不能让蛇倒着走!");
                    Snake.setDirection(Snake.getDirection());
                } else if (keyCode == 38 & Snake.getDirection() == 40) {
                    System.out.println(Time.get() + "[检测按键]:不能让蛇倒着走!");
                    Snake.setDirection(Snake.getDirection());
                } else if (keyCode == 39 & Snake.getDirection() == 37) {
                    System.out.println(Time.get() + "[检测按键]:不能让蛇倒着走!");
                    Snake.setDirection(Snake.getDirection());
                } else if (keyCode == 40 & Snake.getDirection() == 38) {
                    System.out.println(Time.get() + "[检测按键]:不能让蛇倒着走!");
                    Snake.setDirection(Snake.getDirection());
                } else {
                    Snake.setDirection(keyCode);
                }
            }
        });
        jFrame.add(snake);


        jFrame.setTitle("贪吃蛇Demo");//标题
        jFrame.setSize(x, y);//尺寸
        jFrame.setLocationRelativeTo(null);//界面居中
        jFrame.setResizable(true);//界面大小可调整？
        jFrame.setVisible(true);//设置界面可见
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击红叉关闭

        thread.start();
    }
}
