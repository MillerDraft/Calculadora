import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.TreeMap;


public class RomanCalculator extends Calculator{
    private int num1 = 0, num2 = 0, result = 0;
    private JButton b1, b2, b3, b4, b5, b6, b7, b8;
    private JButton b9, b10, b11, b12, b13, b14;
    private static TreeMap<Integer, String> numTreeMap; // for implement method convert Int to Roman #
    private static HashMap<String, Integer> keyMap; // for implement method convert Roman to Int
    //Constructor
    public RomanCalculator() {
        super();
        numTreeMap = new TreeMap<>(); // Initializing TreeMap and add (k, v)
        numTreeMap.put(1000, "M");    // This data structure keeps the pair(k, v)
        numTreeMap.put(900, "CM");    // ordered in ascending order.
        numTreeMap.put(500, "D");
        numTreeMap.put(400, "CD");
        numTreeMap.put(100, "C");
        numTreeMap.put(90, "XC");
        numTreeMap.put(50, "L");
        numTreeMap.put(40, "XL");
        numTreeMap.put(10, "X");
        numTreeMap.put(9, "IX");
        numTreeMap.put(5, "V");
        numTreeMap.put(4, "IV");
        numTreeMap.put(1, "I");

        keyMap = new HashMap<>(); // Initializing Map and add (k, v)
        keyMap.put("I", 1);
        keyMap.put("II", 2);
        keyMap.put("III", 3);
        keyMap.put("IV", 4);
        keyMap.put("V", 5);
        keyMap.put("VI",6);
        keyMap.put("VII", 7);
        keyMap.put("VIII", 8);
        keyMap.put("IX", 9);
        keyMap.put("X", 10);
        keyMap.put("L", 50);
        keyMap.put("C", 100);
        keyMap.put("D", 500);
        keyMap.put("M", 1000);
    }

    @Override
    public void init() {
        super.init();
    }
    // Override Frame method to set name to the Frame
    @Override
    public void setFrame() {
        super.setFrame();
        frame.setTitle("Roman Calculator");
        // Set location at Center right of the screen
        frame.setLocation(screenWidth - frame.getWidth() - 180, screenHeight / 2 - frame.getHeight() / 2);
    }

    @Override
    public void setTextField() {
        super.setTextField();
    }
    // Override Panel method to change panel configuration
    @Override
    public void setPanel() {
        panel = new JPanel();
        panel.setBounds(50, 180, 300, 300);
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        panel.add(numberButton[0]);
        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);
        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);
        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);
        panel.add(numberButton[10]);
        panel.add(numberButton[11]);
        panel.add(numberButton[12]);
        panel.add(numberButton[13]);
        panel.add(squButton);
        panel.add(addButton);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(equButton);

    }

    @Override
    public void addComponentsToFrame() {
        super.addComponentsToFrame();
    }
    // Override Buttons method to create buttons with Roman #, and eliminate
    // some of the button of the parent class that Roman didn't use in their math
    @Override
    public void setButtons() {
        super.setButtons();
        numberButton = new JButton[14];
        functionButton = new JButton[8];

        addButton = new JButton("+");
        subButton = new JButton("–");
        mulButton = new JButton("*");
        divButton = new JButton("÷");
        equButton = new JButton("=");
        delButton = new JButton("←");
        clrButton = new JButton("#");
        squButton = new JButton("×²");



        functionButton[0] = addButton;
        functionButton[1] = subButton;
        functionButton[2] = mulButton;
        functionButton[3] = divButton;
        functionButton[4] = equButton;
        functionButton[5] = delButton;
        functionButton[6] = clrButton;
        functionButton[7] = squButton;


        for (int i = 0; i < 8; i++) {
            functionButton[i].addActionListener(this);
            functionButton[i].setFont(myFont);
            functionButton[i].setFocusable(false);
        }
        // New position of these buttons on frame
        delButton.setBounds(250, 110, 100, 50);
        clrButton.setBounds(50, 110, 100, 50);
        clrButton.setBackground(Color.CYAN);
        // Roman # buttons
        b1 = new JButton("I");
        b2 = new JButton("II");
        b3 = new JButton("III");
        b4 = new JButton("IV");
        b5 = new JButton("V");
        b6 = new JButton("VI");
        b7 = new JButton("VII");
        b8 = new JButton("VIII");
        b9 = new JButton("IX");
        b10 = new JButton("X");
        b11 = new JButton("L");
        b12 = new JButton("C");
        b13 = new JButton("D");
        b14 = new JButton("M");

        // Adding Roman buttons to Array
        numberButton[0] = b1;
        numberButton[1] = b2;
        numberButton[2] = b3;
        numberButton[3] = b4;
        numberButton[4] = b5;
        numberButton[5] = b6;
        numberButton[6] = b7;
        numberButton[7] = b8;
        numberButton[8] = b9;
        numberButton[9] = b10;
        numberButton[10] = b11;
        numberButton[11] = b12;
        numberButton[12] = b13;
        numberButton[13] = b14;
        // setting properties
        for (int i = 0; i < 14; i++) {
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(myFont);
            numberButton[i].setFocusable(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        String text = e.getActionCommand();

        if (Character.isUpperCase(text.charAt(0))){
            textField.setText(textField.getText().concat(text));
        }
        if (button == addButton){
            operator = '+';
            num1 =  convertRomanToInt(textField.getText());
            textField.setText("");
        }
        else if (button == subButton){
            operator = '-';
            num1 =  convertRomanToInt(textField.getText());
            textField.setText("");
        }
        else if (button == mulButton){
            operator = '*';
            num1 =  convertRomanToInt(textField.getText());
            textField.setText("");
        }
        else if (button == divButton){
            operator = '/';
            num1 =  convertRomanToInt(textField.getText());
            textField.setText("");
        }
        else if (button == equButton){
            num2 = convertRomanToInt(textField.getText());
            switch(operator){
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if(num2 != 0){
                        result = num1 / num2;
                        break;
                    }
            }
            num1 = result;
            textField.setText(convertIntToRoman(result));
        }
        else if (button == squButton){
            num1 =  convertRomanToInt(textField.getText());
            num1 *= num1;
            textField.setText(convertIntToRoman(num1));
        }
        else if (button == clrButton){
            num1 = 0;
            num2 = 0;
            result = 0;
            textField.setText("");
        }
        else if (button == delButton){
            String s = textField.getText();
            textField.setText(s.substring(0, s.length() - 1));
        }

    }
    // Method to convert Roman # to Int
    public int convertRomanToInt(String str){
        int ans = 0;
        int prev = 0;
        for (int i = str.length() -1; i >= 0; i--){
            String currentSymbol = String.valueOf(str.charAt(i)); // Convert char to String
            int curr = keyMap.get(currentSymbol); // Use the String as key to get the value from keyMap
            if (curr < prev){
                ans -= curr;
            }
            else{
                ans += curr;
            }
            prev = curr;
        }
        return ans;
    }

    // Method to convert Int to Roman #
    public String convertIntToRoman(int num){
        if (num <= 0 || num > 4000){
            textField.setText("Wrong Roman numeral");
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0){
            int biggestVal = numTreeMap.floorKey(num); // Find the biggest number in TreeMap <= num
            String romanNumeral = numTreeMap.get(biggestVal); // get the Roman # of the number <= num
            if (biggestVal <=  num){
                sb.append(romanNumeral);
                num -= biggestVal;
            }
        }
        return sb.toString();

    }

}


