package calculator.view;

import calculator.controller.CalcContoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class View extends JFrame {
    private String[] operatorsButtonsNames = {"+", "-", ".", "/", "*", "=", "<-"};
    @Autowired
    private CalcContoller controller;
    //Displays arguments and final result of calculation
    private JLabel monitorLabel;
    //first argument of expression
    private String firstArgument;
    //second argument of expression
    private String secondArgument;
    // Value of last used operator
    private String currentOperator;
    //Constants used to set dimensions of window
    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;

    public View() throws HeadlessException {
        setTitle("Calculator");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel buttonsPanel = new JPanel(new GridLayout(6, 6));
        setLayout(new BorderLayout());
        monitorLabel = new JLabel("0");
        monitorLabel.setFont(monitorLabel.getFont().deriveFont(20.0f));
        monitorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(monitorLabel, BorderLayout.NORTH);
        initButtons(buttonsPanel);
        add(buttonsPanel, BorderLayout.CENTER);
        JButton calculateButton = new JButton("=");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makeCalculations();
            }
        });
        calculateButton.setFont(calculateButton.getFont().deriveFont(20.0f));
        add(calculateButton, BorderLayout.SOUTH);
        setVisible(true);

    }

    // performing calculations
    private void makeCalculations() {
        if ((firstArgument != null) && (secondArgument != null)) {
            firstArgument = controller.makeCalculations(firstArgument, secondArgument, currentOperator);
            monitorLabel.setText(firstArgument);
            secondArgument = null;
            currentOperator = null;
        } else if (secondArgument == null && (currentOperator != null)) {
            secondArgument = monitorLabel.getText();
            firstArgument = controller.makeCalculations(firstArgument, secondArgument, currentOperator);
            secondArgument = null;
            currentOperator = null;
            monitorLabel.setText(firstArgument);
            return;
        }
    }

    /**
     * @param panel a panel, which contains buttons
     */
    private void initButtons(JPanel panel) {

        //Create calculator's button and add them to grid panel
        panel.add(createButton("0", ButtonType.NUMBER));
        panel.add(createButton("1", ButtonType.NUMBER));
        panel.add(createButton("2", ButtonType.NUMBER));
        panel.add(createButton("3", ButtonType.NUMBER));
        panel.add(createButton("4", ButtonType.NUMBER));
        panel.add(createButton("5", ButtonType.NUMBER));
        panel.add(createButton("6", ButtonType.NUMBER));
        panel.add(createButton("7", ButtonType.NUMBER));
        panel.add(createButton("8", ButtonType.NUMBER));
        panel.add(createButton("9", ButtonType.NUMBER));

        //Create Button for deleting last element in monitor label
        JButton deleteLastSymbolButton = new JButton("<-");
        deleteLastSymbolButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!monitorLabel.getText().equals("")) {
                    monitorLabel.setText(monitorLabel.getText().substring(0, monitorLabel.getText().length() - 1));
                    if (monitorLabel.getText().equals("")) monitorLabel.setText("0");
                }
            }
        });
        panel.add(deleteLastSymbolButton);

        // Button for clear all monitor label
        JButton clearButton = new JButton("C");
        clearButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                monitorLabel.setText("0");
                firstArgument = null;
                secondArgument = null;
                currentOperator = null;
            }
        });
        panel.add(clearButton);

        //Add buttons for operators
        panel.add(createButton("+", ButtonType.OPERATOR));
        panel.add(createButton("-", ButtonType.OPERATOR));
        panel.add(createButton("*", ButtonType.OPERATOR));
        panel.add(createButton("/", ButtonType.OPERATOR));
        panel.add(createButton(".", ButtonType.NUMBER));


    }

    /**
     * @param buttonValue the title for the new button
     * @param buttonType  one of two available button types: number or operator
     * @return new configured JButton element
     */
    private JButton createButton(final String buttonValue, ButtonType buttonType) {
        JButton button = new JButton(buttonValue);
        switch (buttonType) {
            case NUMBER:
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (monitorLabel.getText().equals("0"))
                            monitorLabel.setText(buttonValue);
                        else
                            monitorLabel.setText(monitorLabel.getText() + buttonValue);
                    }
                });
                break;
            case OPERATOR:
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if ((currentOperator != null) || (monitorLabel.getText().equals(""))) return;
                        if (firstArgument == null || (currentOperator == null)) {
                            firstArgument = monitorLabel.getText();
                            monitorLabel.setText("0");
                            currentOperator = buttonValue;
                            return;
                        }
                        makeCalculations();

                    }
                });
                break;
        }
        return button;
    }


    public CalcContoller getController() {
        return controller;
    }

    /**
     * @param controller main Controller of program, is used for communicating with model
     */
    public void setController(CalcContoller controller) {
        this.controller = controller;
    }

}
