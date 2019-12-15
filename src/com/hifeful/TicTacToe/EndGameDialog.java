package com.hifeful.TicTacToe;

import javax.swing.*;
import java.awt.*;

public class EndGameDialog extends JDialog {
    public EndGameDialog (JFrame frame, GamePanel aGamePanel, String alert) {
        super(frame,true);
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        var labelPanel = new JPanel(new GridBagLayout());
        var endAlert = new JLabel(alert);
        endAlert.setFont(new Font("Arial", Font.BOLD, 36));
        endAlert.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(endAlert);

        add(labelPanel, BorderLayout.CENTER);

        var buttonPanel = new JPanel();
        var newGameButton = new JButton("New game");
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFocusPainted(false);
        newGameButton.setFont(new Font("Tahoma", Font.BOLD, 24));
        newGameButton.addActionListener(event ->  {
            aGamePanel.newGame();
            dispose();
        });
        buttonPanel.add(newGameButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(400, 400));
        pack();
        setLocation((frame.getX() + frame.getWidth() / 2) - getWidth() / 2,
                (frame.getY() + frame.getHeight() / 2) - getHeight() / 2);
    }
}
