package com.hifeful.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

public class MainFrame extends JFrame {
    private final int DEFAULT_WIDTH = 600;
    private final int DEFAULT_HEIGHT = 600;

    private int width;
    private int height;
    private int left;
    private int top;

    private JPanel labelPanel;

    public MainFrame() {
        preferences();

        setPreferredSize(new Dimension(width, height));
        setLocation(left, top);

        createLabel();
        add(new GamePanel(labelPanel), BorderLayout.CENTER);

        pack();
    }

    private void createLabel() {
        labelPanel = new JPanel();

        var statusLabel = new JLabel();
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        labelPanel.add(statusLabel, BorderLayout.CENTER);

        labelPanel.setBorder(BorderFactory.createEtchedBorder(Color.RED, Color.RED));
        labelPanel.setBackground(Color.WHITE);

        add(labelPanel, BorderLayout.NORTH);
    }

    private void preferences()
    {
        Preferences root = Preferences.userRoot();
        Preferences node = root.node("/com/hifeful/TicTacToe");

        width = node.getInt("width", DEFAULT_WIDTH);
        height = node.getInt("height", DEFAULT_HEIGHT);
        left = node.getInt("left", 0);
        top = node.getInt("top", 0);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                node.putInt("width", getWidth());
                node.putInt("height", getHeight());
                node.putInt("left", getX());
                node.putInt("top", getY());
            }
        });
    }
}
