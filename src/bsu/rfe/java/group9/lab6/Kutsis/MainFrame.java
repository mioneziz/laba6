package bsu.rfe.java.group9.lab6.Kutsis;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    // Константы, задающие размер окна приложения, если оно
// не распахнуто на весь экран
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem pauseMenuItem1;
    private JMenuItem resumeMenuItem1;
    // Поле, по которому прыгают мячи
    private Field field = new Field();
    // Конструктор главного окна приложения
    public MainFrame() {
        super("Программирование и синхронизация потоков");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        setExtendedState(MAXIMIZED_BOTH);
// Создать меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if (!pauseMenuItem.isEnabled() &&
                        !resumeMenuItem.isEnabled()) {
// Ни один из пунктов меню не являются
// доступными - сделать доступным "Паузу"
                    pauseMenuItem.setEnabled(true);
                    pauseMenuItem1.setEnabled(true);
                    resumeMenuItem1.setEnabled(false);
                }
            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);
        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Приостановить движение"){
            public void actionPerformed(ActionEvent event) {
                field.pause();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
                pauseMenuItem1.setEnabled(false);
                resumeMenuItem1.setEnabled(true);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);
        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                pauseMenuItem1.setEnabled(true);
                resumeMenuItem1.setEnabled(true);
                resumeMenuItem.setEnabled(true);
            }

        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);
        Action pauseAction1 = new AbstractAction("Приостановить движение мячей с малой скоростью"){
            public void actionPerformed(ActionEvent event) {
                field.pause1();
                pauseMenuItem.setEnabled(true);
                pauseMenuItem1.setEnabled(false);
                resumeMenuItem1.setEnabled(true);
                resumeMenuItem.setEnabled(true);
            }
        };
        pauseMenuItem1 = controlMenu.add(pauseAction1);
        pauseMenuItem1.setEnabled(false);

        Action resumeAction1 = new AbstractAction("Возобновить движение мячей  малой скорости"){
            public void actionPerformed(ActionEvent event) {
                field.resume1();
                resumeMenuItem1.setEnabled(true);
                pauseMenuItem1.setEnabled(true);
                pauseAction.setEnabled(true);
                resumeMenuItem.setEnabled(true);
            }
        };
        resumeMenuItem1 = controlMenu.add(resumeAction1);
        resumeMenuItem1.setEnabled(false);



// Добавить в центр граничной компоновки поле Field
        getContentPane().add(field, BorderLayout.CENTER);
    }
    // Главный метод приложения
    public static void main(String[] args) {
// Создать и сделать видимым главное окно приложения
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
