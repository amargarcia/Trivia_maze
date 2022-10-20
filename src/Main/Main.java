package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] theArgs) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Trivia Maze");

        GameSetup gameSetupPanel = new GameSetup();
        window.add(gameSetupPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameSetupPanel.BeginGameThread();

    }
}
