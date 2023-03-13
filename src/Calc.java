
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calc extends JFrame {
    private JTextField text;//поле для ввода цифр и действий
    private double number1 = 0, number2 = 0;//переменные для хранения операндов
    private int operation = 0;//номер действия
    private String fullNumber = "";//переменная для хранения числа из текстового поля
    private String numButtons[] = {"C","x²","√x","/","7","8","9","*","4","5","6","-","1","2","3","+","Exit","0",".","="};

    public Calc() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        var c = getContentPane();
        var panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);

        JButton buttons[] = new JButton[20];
        var font = new Font("arial",Font.PLAIN,20);
        int x = 2, y = 80;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(font);
            buttons[i].setSize(75,70);//размер каждой кнопки
            buttons[i].setLocation(x,y);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setBorderPainted(false);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String titleButton = button.getText();

                        if (titleButton.equals("Exit")) {
                            System.exit(0);//завершаем программу
                            fullNumber = "";
                        } else if (titleButton.equals("C")) {
                            text.setText("0");
                            number1 = 0;
                            number2 = 0;
                            fullNumber = "";
                        } else if (titleButton.equals("=")) {
                            number2 = Double.parseDouble(text.getText());
                            text.setText(calculate());
                            fullNumber = "";
                        } else if (titleButton.equals("+")) {
                            operation = 1;
                            number1 = Double.parseDouble(text.getText());
                            text.setText("+");
                            fullNumber = "";
                        } else if (titleButton.equals("-")) {
                            operation = 2;
                            number1 = Double.parseDouble(text.getText());
                            text.setText("-");
                            fullNumber = "";
                        } else if (titleButton.equals("*")) {
                            operation = 3;
                            number1 = Double.parseDouble(text.getText());
                            text.setText("*");
                            fullNumber = "";
                        } else if (titleButton.equals("/")) {
                            operation = 4;
                            number1 = Double.parseDouble(text.getText());
                            text.setText("/");
                            fullNumber = "";
                        } else if (titleButton.equals("x²")) {
                            operation = 5;
                            number1 = Double.parseDouble(text.getText());
                            text.setText(calculate());
                            fullNumber = "";
                        } else if (titleButton.equals("√x")) {
                            operation = 6;
                            number1 = Double.parseDouble(text.getText());
                            text.setText(calculate());
                            fullNumber = "";
                        } else if (titleButton.equals(".")) {
                            if (text.getText().equals("0")) {
                                fullNumber = "0" + titleButton;
                                text.setText(fullNumber);
                            } else if (fullNumber.contains(".")) {
                                text.setText(fullNumber);
                            } else {
                                fullNumber += titleButton;
                                text.setText(fullNumber);
                            }
                        } else {
                            fullNumber += titleButton;
                            text.setText(fullNumber);
                        }
                    }
            });
            panel.add(buttons[i]);
            buttons[i].setText(numButtons[i]);
            x += 77;
            if ((i + 1) % 4 == 0) {
                x = 2;
                y += 72;
            }
        }
        text = new JTextField("0");
        text.setFont(new Font("serif",Font.BOLD,40));
        text.setForeground(Color.BLACK);
        text.setBackground(Color.LIGHT_GRAY);
        text.setBounds(0,0,310,80);
        text.setHorizontalAlignment(JTextField.RIGHT);
        text.setEditable(false);
        panel.add(text);
        c.add(panel);
        setSize(327,480);
    }

    /**
     * Метод проверяет есть ли у числа дробная часть
     * @param num - проверяемое число
     * @return - возвращает true если число с дробной частью и false - если число целое
     */
    private boolean isDouble(double num) { // Метод проверяет дробную часть числа
        double fraction = num - (int) num;
        return fraction != 0.0;
    }

    /**
     * Метод вычисляет выражение в зависимости от операции
     * @return - возвращает результат операции
     */
    private String calculate() {
        double result = 0;
        switch (operation) {
            case 1:
                result = number1 + number2;
                break;
            case 2:
                result = number1 - number2;
                break;
            case 3:
                result = number1 * number2;
                break;
            case 4:
                if (number2 == 0) {
                    try {
                        throw new Exception("На 0 делить нельзя!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                result = number1 / number2;
                break;
            case 5:
                result = number1*number1;
                break;
            case 6:
                result = Math.sqrt(number1);
        }
        result = Math.rint(1000000000 * result) / 1000000000;
        return (isDouble(result)) ? "" + result : "" + (int) result;
    }

    public static void main(String[] args) {
        Calc frame = new Calc();
        frame.setResizable(false);
        frame.setTitle("Калькулятор v1");
        frame.setVisible(true);
    }
}
