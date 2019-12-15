package com.hifeful.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;

public class GamePanel extends JComponent {
    private JLabel[] cells;
    private char[] drawnCells;
    private boolean turn;
    private int turnsCounter;

    private JFrame ownerFrame;
    private JLabel statusLabel;

    public GamePanel(JFrame aOwnerFrame, JPanel aLabelPanel) {
        ownerFrame = aOwnerFrame;
        statusLabel = (JLabel) aLabelPanel.getComponent(0);

        cellsSettings();
    }

    private void cellsSettings() {
        drawnCells = new char[9];
        turnsCounter = 0;

        Random rd = new Random();
        turn = rd.nextBoolean();

        System.out.println(turn);

        if (turn) {
            statusLabel.setText("O TURN");
            statusLabel.paintImmediately(statusLabel.getVisibleRect());
            System.out.println("That's OOOOOOO");
        }
        else {
            statusLabel.setText("X TURN");
            statusLabel.paintImmediately(statusLabel.getVisibleRect());
            System.out.println("That's XXXXXXXXX");
        }

        setLayout(new GridLayout(3, 3));
        cells = new JLabel[9];
        var mouserHandler = new MouseHandler();
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new JLabel(String.valueOf(i)) {
                public void paintComponent(Graphics g) { // damn... Each JLabel has own resolution...
                    // Which stats from 0... Damn...
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setPaint(Color.BLACK);

                    if (drawnCells[Integer.parseInt(getText())] == 'x') {
                        g2.draw(new Line2D.Double(15, 15, getWidth() - 15, getHeight() - 15));
                        g2.draw(new Line2D.Double(getWidth() - 15, 15, 15, getHeight() - 15));
                    }
                    else if (drawnCells[Integer.parseInt(getText())] == 'o') {
                        var circle = new Ellipse2D.Double();
                        circle.setFrameFromCenter((double)getWidth() / 2, (double)getHeight() / 2, ((double)getWidth() / 2) + 100, ((double)getHeight() / 2) + 100);
                        g2.draw(circle);
                    }
                }
            };
            cells[i].addMouseListener(mouserHandler);
            add(cells[i]);
        }
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
                            drawnCells[i] = 'o';
                            label.repaint();
                            turn = false;

                            turnsCounter++;
                            if (turnsCounter >= 3) {
                                if (checkWin(true)) {
                                    revalidate();
                                    return;
                                }
                            }

                            statusLabel.setText("X TURN");
                        }
                        else {
                            drawnCells[i] = 'x';
                            label.repaint();
                            turn = true;

                            turnsCounter++;
                            if (turnsCounter >= 3) {
                                if (checkWin(false)) {
                                    revalidate();
                                    return;
                                }
                            }

                            statusLabel.setText("O TURN");
                        }
                        System.out.println("THAT SHIT DON'T EQUAL X OR O");
                    }
                    else
                        System.out.println("THAT SHIT EQUALS X OR O");

                    return;
                }
            }
        }
    }

    private boolean checkWin(boolean sign) {
        if (sign) {
            if (
                    (drawnCells[0] == 'o' && drawnCells[1] == 'o' && drawnCells[2] == 'o') ||
                    (drawnCells[2] == 'o' && drawnCells[5] == 'o' && drawnCells[8] == 'o') ||
                    (drawnCells[8] == 'o' && drawnCells[7] == 'o' && drawnCells[6] == 'o') ||
                    (drawnCells[6] == 'o' && drawnCells[3] == 'o' && drawnCells[0] == 'o') ||
                    (drawnCells[3] == 'o' && drawnCells[4] == 'o' && drawnCells[5] == 'o') ||
                    (drawnCells[1] == 'o' && drawnCells[4] == 'o' && drawnCells[7] == 'o') ||
                    (drawnCells[0] == 'o' && drawnCells[4] == 'o' && drawnCells[8] == 'o') ||
                    (drawnCells[2] == 'o' && drawnCells[4] == 'o' && drawnCells[6] == 'o')
            ) {
                System.out.println("O WINS");
                new EndGameDialog(ownerFrame, this, "O WON").setVisible(true);
                return true;
            }

            if (turnsCounter == 9) {
                System.out.println("DRAW");
                new EndGameDialog(ownerFrame, this, "DRAW").setVisible(true);
                return true;
            }
        }
        else {
            if (
                    (drawnCells[0] == 'x' && drawnCells[1] == 'x' && drawnCells[2] == 'x') ||
                    (drawnCells[2] == 'x' && drawnCells[5] == 'x' && drawnCells[8] == 'x') ||
                    (drawnCells[8] == 'x' && drawnCells[7] == 'x' && drawnCells[6] == 'x') ||
                    (drawnCells[6] == 'x' && drawnCells[3] == 'x' && drawnCells[0] == 'x') ||
                    (drawnCells[3] == 'x' && drawnCells[4] == 'x' && drawnCells[5] == 'x') ||
                    (drawnCells[1] == 'x' && drawnCells[4] == 'x' && drawnCells[7] == 'x') ||
                    (drawnCells[0] == 'x' && drawnCells[4] == 'x' && drawnCells[8] == 'x') ||
                    (drawnCells[2] == 'x' && drawnCells[4] == 'x' && drawnCells[6] == 'x')
            ) {
                System.out.println("X WINS");
                new EndGameDialog(ownerFrame, this, "X WON").setVisible(true);
                return true;
            }

            if (turnsCounter == 9) {
                System.out.println("DRAW");
                new EndGameDialog(ownerFrame, this, "DRAW").setVisible(true);
                return true;
            }
        }

        return false;
    }

    public void newGame() {
        removeAll();
        cellsSettings();
        repaint();
    }
}
