import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Parent Class
public abstract class Calculator implements ActionListener {
    protected JFrame frame;
    protected JTextField textField;
    protected JButton[] numberButton;
    protected JButton[] functionButton;
    protected JButton addButton, subButton, mulButton, divButton;
    protected JButton delButton, equButton, clrButton, squButton;
    protected JPanel panel;
    protected Dimension screenSize;
    protected int screenWidth;
    protected int screenHeight;

    protected Font myFont = new Font("Times new roman", Font.PLAIN, 20);

    public Calculator() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
    }
    // method to start the program
    public void init() {
        setFrame();
        setTextField();
        setButtons();
        setPanel();

        addComponentsToFrame();

    }
    // Frame method
    public void setFrame() {
        frame = new JFrame();
        frame.setSize(420, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        // frame.setLocationRelativeTo(null); to place at the center of the screen

    }
    // Text Field method
    public void setTextField() {
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);
    }
    // Panel method
    public void setPanel() {}

    // Add element to frame
    public void addComponentsToFrame() {
        frame.add(textField);
        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.setVisible(true);
    }
    // Button method
    public void setButtons() {}
    // Method to receive and handle operations when buttons are selected
    @Override
    public void actionPerformed(ActionEvent e) {}


}
