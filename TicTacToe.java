import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {

    private JPanel gameBoard;
    private JButton[] buttons;
    private JLabel statusLabel;
    private boolean gameStarted = false;
    private char currentPlayer = 'X';

    public TicTacToe() {
        setTitle("Крестики Нолики by David");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);

        gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(3, 3));
        add(gameBoard, BorderLayout.CENTER);

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            gameBoard.add(buttons[i]);
        }

        statusLabel = new JLabel("X ходит");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int index = -1;
        for (int i = 0; i < 9; i++) {
            if (buttons[i] == button) {
                index = i;
                break;
            }
        }

        if (index != -1 && button.getText().equals("")) {
            button.setText(String.valueOf(currentPlayer));
            button.setForeground(currentPlayer == 'X' ? Color.BLUE : Color.RED);
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            statusLabel.setText(currentPlayer + " ходит");

            if (checkForWin()) {
                gameStarted = false;
                for (int i = 0; i < 9; i++) {
                    buttons[i].setEnabled(false);
                }
                if(currentPlayer=='X') {
                    statusLabel.setText('O' + " побеждает!");
                }else{
                    statusLabel.setText('X' + " побеждает!");
                }
            }
        }
    }

    public boolean checkForWin() {
        // горизонталь
        for (int i = 0; i < 9; i += 3) {
            if (!buttons[i].getText().equals("") &&
                    buttons[i].getText().equals(buttons[i + 1].getText()) &&
                    buttons[i + 1].getText().equals(buttons[i + 2].getText())) {
                return true;
            }
        }

        // вертикаль
        for (int i = 0; i < 3; i++) {
            if (!buttons[i].getText().equals("") &&
                    buttons[i].getText().equals(buttons[i + 3].getText()) &&
                    buttons[i + 3].getText().equals(buttons[i + 6].getText())) {
                return true;
            }
        }

        // диагональ
        if (!buttons[0].getText().equals("") &&
                buttons[0].getText().equals(buttons[4].getText()) &&
                buttons[4].getText().equals(buttons[8].getText())) {
            return true;
        }
        if (!buttons[2].getText().equals("") &&
                buttons[2].getText().equals(buttons[4].getText()) &&
                buttons[4].getText().equals(buttons[6].getText())) {
            return true;
        }

        return false;
    }
}