package com.hifeful.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Random;

public class GamePanel extends JComponent {
    private JLabel[] cells;
    private char[] drawnCells;
    private boolean turn;

    private JLabel statusLabel;

    public GamePanel(JPanel aLabelPanel) {
        statusLabel = (JLabel) aLabelPanel.getComponent(0);
        drawnCells = new char[9];

        Random rd = new Random();
        turn = rd.nextBoolean();

        if (turn) statusLabel.setText("O TURN");
        else statusLabel.setText("X TURN");

        setLayout(new GridLayout(3, 3));
        cells = new JLabel[9];
        var mouserHandler = new MouseHandler();
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new JLabel();
            cells[i].addMouseListener(mouserHandler);
            add(cells[i]);
        }

        addMouseListener(new MouseHandler());
    }

    public void paintComponent(Graphics g) {

        var g2 = (Graphics2D) g;

        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        int widthThreshold = getWidth() / 3;
        int heightThreshold = getHeight() / 3;

        g2.setPaint(Color.BLACK);
        g2.setStroke(new BasicStroke(8));

        for (int i = 1; i <= 2 ; i++) {
            g2.draw(new Line2D.Double(0, ((double) heightThreshold * i),
                                getWidth(), ((double) heightThreshold * i)));
            g2.draw(new Line2D.Double(((double) widthThreshold * i), 0,
                                      ((double) widthThreshold * i), getHeight()));
        }
    }

    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            Component source = e.getComponent();

            if (!(source instanceof JLabel)) return;

            JLabel label = (JLabel) source;

            for (int i = 0; i < cells.length; i++) {
                if (cells[i].equals(label)) {
                    if (drawnCells[i] != 'x' && drawnCells[i] != 'o') {
                        if (turn) {
                            System.out.println(label.getX() + " " + label.getY());
//                            label.setIcon(new ImageIcon(GamePanel.class.getResource("/images/biblethump.png")));
                        }
                        else {

                        }
                        drawnCells[i] = 'x';
                        System.out.println("THAT SHIT DON'T EQUAL X OR O");
                    }
                    else
                        System.out.println("THAT SHIT EQUALS X OR O");
                }
            }
        }
    }

//    public Dimension getPreferredSize() { return new Dimension() }
}
