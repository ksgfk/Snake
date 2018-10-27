package com.github.ksgfk.snake.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import com.github.ksgfk.snake.util.Time;

/**
 * KSGFK 创建于 2018/10/21.
 */
public class Snake extends JComponent implements Runnable {
    /*初始棋盘x,y轴起始坐标*/
    private static int INIT_X = 20;
    private static int INIT_Y = 20;
    /*初始棋盘x,y轴终止坐标*/
    private static int INIT_SIZE_X = 400;
    private static int INIT_SIZE_Y = 400;
    /*初始蛇头x,y坐标*/
    private static int HEAD_X = 20;
    private static int HEAD_Y = 20;
    /*储存蛇的身体*/
    private static ArrayList<SnakeBody> snakeBody = new ArrayList<>();
    /*按键输入,蛇的初始方向为右*/
    private static int direction = 39;
    /*回合数*/
    private static int round;
    /*蛇的食物*/
    private static SnakeFood sf;
    /*初始蛇长*/
    private static int snakeLong = 3;
    /*游戏速度*/
    private static final long speed = 100;

    /**
     * 画蛇的身体
     *
     * @param graphics emm...图形?
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        /*蛇吃到食物后身体生成*/
        SnakeBody head = snakeBody.get(0);
        if (head.x == sf.x & head.y == sf.y) {
            SnakeBody s = new SnakeBody(snakeBody.get(snakeLong - 1).x, snakeBody.get(snakeLong - 1).y);
            snakeBody.add(s);
            snakeLong++;
        }
        /*棋盘背景生成*/
        graphics.setColor(Color.GRAY);
        graphics.fillRect(INIT_X, INIT_Y, INIT_SIZE_X, INIT_SIZE_Y);
        /*棋盘网格生成*/
        graphics.setColor(Color.BLACK);
        for (int a = 0; a < 21; a++) {
            int x1 = INIT_X;
            int x2 = INIT_X + INIT_SIZE_X;
            int y1 = INIT_Y + a * 20;
            int y2 = INIT_Y + a * 20;
            graphics.drawLine(x1, y1, x2, y2);
            graphics.drawLine(y1, x1, y2, x2);
        }
        /*蛇的身体生成*/
        graphics.setColor(Color.LIGHT_GRAY);
        for (SnakeBody c : snakeBody) {
            graphics.fillRect(c.x + 1, c.y + 1, 19, 19);
        }
        /*食物生成*/
        graphics.setColor(Color.GREEN);
        for (SnakeBody sb : snakeBody) {
            /*生成在蛇身上的话,重新生成*/
            while (true) {
                if (sf.x == sb.x & sf.y == sb.y) {
                    System.out.println(Time.get() + "[食物生成]:" + "生成在蛇身上");

                    sf = new SnakeFood();
                    graphics.fillRect(sf.x + 1, sf.y + 1, 19, 19);
                } else {
                    graphics.fillRect(sf.x + 1, sf.y + 1, 19, 19);
                    break;
                }
            }
        }
    }

    /**
     * 构造方法
     */
    public Snake() {
        sf = new SnakeFood();
        /*初始化蛇身体*/
        for (int a = 0; a < snakeLong; a++) {
            SnakeBody b3 = new SnakeBody(INIT_X + 40 - a * 20, INIT_Y);
            snakeBody.add(b3);
        }
    }

    /**
     * 获取蛇移动方向
     *
     * @return 蛇当前方向
     */
    public static int getDirection() {
        return direction;
    }

    /**
     * 设置蛇移动方向
     *
     * @param direction 按键数值
     */
    public static void setDirection(int direction) {
        Snake.direction = direction;
    }

    /**
     * 设置蛇的移动方向
     *
     * @param keyCode 按键的数值
     * @param head    蛇头
     */
    private static void setSnakeMove(int keyCode, SnakeBody head) {
        switch (keyCode) {
            case 38:
                System.out.println(Time.get() + "[回合数:" + round + "] 当前方向:上");
                HEAD_Y = HEAD_Y - 20;
                SnakeBody snakeBody2 = new SnakeBody(head.x, head.y - 20);
                snakeBody.add(0, snakeBody2);
                snakeBody.remove(snakeBody.size() - 1);
                break;
            case 40:
                System.out.println(Time.get() + "[回合数:" + round + "] 当前方向:下");
                HEAD_Y = HEAD_Y + 20;
                SnakeBody snakeBody3 = new SnakeBody(head.x, head.y + 20);
                snakeBody.add(0, snakeBody3);
                snakeBody.remove(snakeBody.size() - 1);
                break;
            case 37:
                System.out.println(Time.get() + "[回合数:" + round + "] 当前方向:左");
                HEAD_X = HEAD_X - 20;
                SnakeBody snakeBody4 = new SnakeBody(head.x - 20, head.y);
                snakeBody.add(0, snakeBody4);
                snakeBody.remove(snakeBody.size() - 1);
                break;
            case 39:
                System.out.println(Time.get() + "[回合数:" + round + "] 当前方向:右");
                HEAD_X = HEAD_X + 20;
                SnakeBody snakeBody5 = new SnakeBody(head.x + 20, head.y);
                snakeBody.add(0, snakeBody5);
                snakeBody.remove(snakeBody.size() - 1);
                break;
            case 27:
                System.out.println(Time.get() + "退出");
                System.out.println(Time.get() + "[主线程]:贪吃蛇结束");
                System.exit(0);
                break;
            case -1:
                /*失败*/
                int result = JOptionPane.showConfirmDialog(null, "你输了", "游戏结果", 0);
                if (result == 0) {
                    System.out.println(Time.get() + "[主线程]:贪吃蛇结束");
                    System.exit(0);
                } else {
                    System.out.println(Time.get() + "[主线程]:贪吃蛇结束");
                    System.exit(0);
                }
                break;
            default:
                System.out.println(Time.get() + "无效输入!!!");
                break;
        }
    }

    /**
     * 胜负判定
     */
    private void isWin() {
        System.out.println(Time.get() + "[蛇头坐标]:(" + snakeBody.get(0).x + "," + snakeBody.get(0).y + ")");

        /*撞墙判定*/
        if (snakeBody.get(0).x < INIT_X || snakeBody.get(0).x > INIT_SIZE_X || snakeBody.get(0).y < INIT_Y || snakeBody.get(0).y > INIT_SIZE_Y) {
            System.out.println(Time.get() + "[胜负判定]:" + "失败!撞到墙了!");
            direction = -1;
        }

        /*吃到自己判定*/
        for (int a = 1; a < snakeBody.size(); a++) {
            if (snakeBody.get(0).x == snakeBody.get(a).x & snakeBody.get(0).y == snakeBody.get(a).y) {
                System.out.println(Time.get() + "[胜负判定]:" + "失败!吃到自己了!");
                direction = -1;
                break;
            }
        }
    }

    /**
     * 线程开始
     */
    @Override
    public void run() {
        System.out.println(Time.get() + "[主线程]:贪吃蛇开始");

        for (round = 0; round < 1000000; round++) {
            try {
                Thread.sleep(speed);

                SnakeBody head = snakeBody.get(0);
                setSnakeMove(direction, head);
                isWin();
                Snake.this.repaint(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
