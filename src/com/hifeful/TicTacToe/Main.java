package com.hifeful.TicTacToe;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            var frame = new MainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("TicTacToe");
            frame.setIconImage(new ImageIcon(Main.class.getResource("/main/java/images/icon.png")).getImage());
            frame.setVisible(true);
        });
    }
}
