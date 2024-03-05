import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ModernCal extends Calculator{
    private BackEnd logic;

    public JButton decButton, sRootButton, percentButton, negButton;
    private Font font = new Font("Arial", Font.ITALIC, 20);
    public ModernCal() {
        super();
        logic = new BackEnd();
    }

    @Override
    public void setFrame() {
        super.setFrame();
        frame.setTitle("Modern Calculator");

        // Set location at Center left of the screen
        frame.setLocation(180, screenSize.height / 2 - frame.getHeight() / 2);

    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void setTextField() {
        super.setTextField();
    }

    @Override
    public void setPanel() {
        panel = new JPanel();
        panel.setBounds(50, 180, 300, 300);
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        panel.add(percentButton);
        panel.add(squButton);
        panel.add(sRootButton);
        panel.add(divButton);

        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);
        panel.add(addButton);

        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);
        panel.add(subButton);

        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);
        panel.add(mulButton);

        panel.add(negButton);
        panel.add(numberButton[0]);
        panel.add(decButton);
        panel.add(equButton);

    }

    @Override
    public void addComponentsToFrame() {
        super.addComponentsToFrame();
    }

    @Override
    public void setButtons() {
        numberButton = new JButton[10];
        functionButton = new JButton[12];

        // Function buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("÷"); // Alt + 246
        equButton = new JButton("=");

        negButton = new JButton("+/-");
        delButton = new JButton("←"); //  alt 27
        clrButton = new JButton("C");
        squButton = new JButton("×²"); // Alt 0215 + Alt 0178
        sRootButton = new JButton("²√×"); // Alt 0178 + Alt 251 + Atl 0215
        percentButton = new JButton("%");
        decButton = new JButton(".");

        // Adding buttons to Array
        functionButton[0] = addButton;
        functionButton[1] = subButton;
        functionButton[2] = mulButton;
        functionButton[3] = divButton;
        functionButton[4] = equButton;
        functionButton[5] = decButton;
        functionButton[6] = negButton;
        functionButton[7] = delButton;
        functionButton[8] = clrButton;
        functionButton[9] = squButton;
        functionButton[10] = sRootButton;
        functionButton[11] = percentButton;
        // receive notification when the button in selected,
        // setting button font and focusable status
        for (int i = 0; i < 12; i++) {
            functionButton[i].addActionListener(this);
            functionButton[i].setFont(font);
            functionButton[i].setFocusable(false);
        }
        // Position of this button on the frame
        delButton.setBounds(250, 110, 100, 50);
        clrButton.setBounds(50, 110, 100, 50);
        clrButton.setBackground(Color.PINK);
        // Create number buttons and receive notification when the button in selected,
        // setting button font and focusable status
        for (int i = 0; i < 10; i++) {
            numberButton[i] = new JButton(String.valueOf(i));
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(font);
            numberButton[i].setFocusable(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String buttonText = e.getActionCommand();
        String screenText = textField.getText();
        // call to a method that perform the arithmetic operations
        textField.setText(logic.handleModernOperations(buttonText, screenText));

    }

}


