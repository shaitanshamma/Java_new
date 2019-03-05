package ru.geekbrains.catch_the_drop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static java.lang.Math.random;

public class GameWindow extends JFrame {
private static GameWindow game_window; // объявили переменную, которая хранит информацию об объекте
                                        // доступная для методов во всем классе
    private static Image back; // для работы с картинками класс Image
    private static Image drop; // объявили переменную типа Image
    private static Image stop; //
    private  static float drop_left = 100; // хранит левый угол капли по оси Х
    private static  float drop_top = -100;
    private static long last_frame_time;
    private static float drop_v =200;
    private static int score;
    public static void main(String[] args) throws IOException {
        back = ImageIO.read(GameWindow.class.getResourceAsStream("Lilies.jpg")); //ввели переменные
        drop = ImageIO.read(GameWindow.class.getResourceAsStream("HourGlass.png"));
        stop = ImageIO.read(GameWindow.class.getResourceAsStream("StopWatch.png"));
	game_window = new GameWindow(); //создали объект GameWindow на который ссылается переменная
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // закрытие окна
        game_window.setLocation(200,100);
        game_window.setSize(906,408);
        game_window.setResizable(false);
        last_frame_time = System.nanoTime(); // задали время
        GameField game_field = new GameField();// создали переменную и дали ссылку на объект Геймфилд
        game_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x =e.getX();
                int y =e.getY();
                float drop_right = drop_left + drop.getWidth(null);
                float drop_botton = drop_top + drop.getHeight(null);
                boolean is_drop = x>=drop_left && x <= drop_right && y<= drop_botton && y >=drop_top;
                if(is_drop){
                    drop_top = -100;
                    drop_left = (int) (Math.random()*(game_field.getWidth()-drop.getWidth(null)));
                    drop_v = drop_v + 20;
                    score++;
                    game_window.setTitle("Ваш счет " + score);
                }
            }
        });
        game_window.add(game_field);// добавили объект в окно
        game_window.setVisible(true);
    }
    private static void onRepaint(Graphics g) { // добавляем метод для рисования
        long current_time = System.nanoTime();
        float delta = (current_time - last_frame_time)*0.000000001f;
        last_frame_time = current_time;
        drop_top = drop_top  + drop_v* delta;
        g.drawImage(back, 0,0, null); //нарисовали картинки
        g.drawImage(drop, (int)drop_left, (int)drop_top , null); // обрезали дробную часть (int)
        if (drop_top > game_window.getHeight()) g.drawImage(stop, 300, 100, null);

    }

    private static class GameField extends JPanel { // создали класс с наследованием для панелей
    @Override
        protected void paintComponent (Graphics g){ //переопределение метода для рисования не из JPanel, а нашего
        super.paintComponent(g);//именно метод из JPanel
        onRepaint(g);
        repaint(); // чтобы отрисовывалось постоянно
    }


    }
}
