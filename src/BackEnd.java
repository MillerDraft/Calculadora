import javax.swing.*;
import java.util.HashMap;
import java.util.TreeMap;

public class BackEnd{
    private double num1 = 0;
    private double num2 = 0;
    private double result = 0;
    private char operation = ' ';
    private static TreeMap<Integer, String> numTreeMap; // for implement method convert Int to Roman #
    private static HashMap<String, Integer> keyMap; // for implement method convert Roman to Int
    public BackEnd(){
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

    // ModernCal logic
    public void handleModernOperations(String text, JTextField textField){
        

        if (Character.isDigit(text.charAt(0))){
            textField.setText(textField.getText().concat(text));
        }

        else {
            switch (text){
                case ".":
                    if (textField.getText().isEmpty()){
                        textField.setText("0.");
                    }
                    else if (!textField.getText().contains(".")){
                        textField.setText(textField.getText().concat("."));
                    }
                    break;

                case "+":
                case "-":
                case "*":
                case "÷":
                    num1 = Double.parseDouble(textField.getText());
                    operation = text.charAt(0);
                    textField.setText("");
                    break;

                case "=":
                    num2 = Double.parseDouble(textField.getText());

                    switch (operation){
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            result = num1 - num2;
                            break;
                        case '*':
                            result = num1 * num2;
                            break;
                        case '÷':
                            if (num2 != 0){
                                result = num1 / num2;
                            }
                            else {
                                textField.setText("Zero division error. You can't divide by 0");
                            }
                            break;
                    }
                    num1 = result;
                    // if result is a round number remove final zero, if not show it as double
                    int temp = (int) Math.floor(result);
                    if (result == Math.floor(result)){
                        textField.setText(String.valueOf(temp));
                    }
                    else {
                        textField.setText(String.valueOf(result));
                    }
                    break;

                case "C":
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    textField.setText("");
                    break;

                case "←":
                    String str = textField.getText();
                    textField.setText(str.substring(0, str.length() -1));
                    break;

                case "%":
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText(String.valueOf(num1 / 100));
                    break;

                case "²√×":
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText(String.valueOf(Math.sqrt(num1)));
                    break;

                case "×²":
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText(String.valueOf(num1 * num1));
                    break;

                case "+/-":
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText(String.valueOf(num1 * -1));
            }
        }
    }

    // RomanCalculator logic
    public void handleRomanOperations(String text, JTextField textField){
        if (Character.isUpperCase(text.charAt(0))){
            textField.setText(textField.getText().concat(text));
        }

        else {
            switch (text){
                case "+":
                case "-":
                case "÷":
                case "*":
                    num1 =  convertRomanToInt(textField.getText());
                    if (num1 <= 0 || num1 > 4000){
                        textField.setText("Wrong Roman numeral");
                        break;
                    }
                    textField.setText("");
                    operation = text.charAt(0);

                    break;
                case "=":
                    num2 = convertRomanToInt(textField.getText());
                    if (num2 <= 0 || num2 > 4000){
                        textField.setText("Wrong Roman numeral");
                        break;
                    }
                    switch (operation){
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            if (num1 > num2){
                                result = num1 - num2;
                            }
                            else result = 0;
                            break;
                        case '*':
                            result = num1 * num2;
                            break;
                        case '÷':
                            if (num2 != 0){
                                result = Math.floor(num1 / num2);
                            }
                            else {
                                textField.setText("Zero division error");
                            }
                            break;
                    }
                    num1 = result;
                    int temp = Integer.valueOf((int) result);
                    textField.setText(convertIntToRoman(temp));
                    break;

                case "×²":
                    num1 = convertRomanToInt(textField.getText());
                    int n = (int) ((int) num1 * num1);
                    textField.setText(convertIntToRoman(n));
                    break;

                case "←":
                    String str = textField.getText();
                    textField.setText(str.substring(0, str.length() -1));
                    break;

                case "#":
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    textField.setText("");
                    break;
            }
        }
    }

    // Method to convert Int to Roman #
    private String convertIntToRoman(int num){

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

    // Method to convert Roman # to Int
    private int convertRomanToInt(String str){
        int result = 0;
        int prev = 0;
        for (int i = str.length() -1; i >= 0; i--){
            String currentSymbol = String.valueOf(str.charAt(i)); // Convert char to String
            int curr = keyMap.get(currentSymbol); // Use the String as key to get the value from keyMap
            if (curr < prev){
                result -= curr;
            }
            else{
                result += curr;
            }
            prev = curr;
        }
        return result;
    }
}