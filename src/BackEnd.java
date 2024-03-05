import javax.swing.*;
import java.util.HashMap;
import java.util.TreeMap;

public class BackEnd{
    private double num1 = 0;
    private double num2 = 0;
    private double result = 0;
    private char operation = ' ';
    protected String showedText = "";
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
    public String handleModernOperations(String text_in, String text_out){
        showedText = text_out;
        

        if (Character.isDigit(text_in.charAt(0))){
            showedText = showedText.concat(text_in);
        }

        else {
            switch (text_in){
                case ".":
                    if (showedText.isEmpty()){
                        showedText = showedText.concat("0.");
                    }
                    else if (showedText.contains(".")){
                        showedText = showedText.concat(".");
                    }
                    break;

                case "+":
                case "-":
                case "*":
                case "÷":
                    num1 = Double.parseDouble(showedText);
                    operation = text_in.charAt(0);
                    showedText = showedText = "";
                    break;

                case "=":
                    num2 = Double.parseDouble(showedText);

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
                                showedText = "Zero division error. You can't divide by 0";
                            }
                            break;
                    }
                    num1 = result;
                    // if result is a round number remove final zero, if not show it as double
                    int temp = (int) Math.floor(result);
                    if (result == Math.floor(result)){
                        showedText = String.valueOf(temp);
                    }
                    else {
                        showedText = String.valueOf(result);
                    }
                    break;

                case "C":
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    showedText = "";
                    break;

                case "←":
                    showedText = (showedText.substring(0, showedText.length() -1));
                    break;

                case "%":
                    num1 = Double.parseDouble(showedText);
                    showedText = String.valueOf(num1 / 100);
                    break;

                case "²√×":
                    num1 = Double.parseDouble(text_out);
                    showedText = String.valueOf(Math.sqrt(num1));
                    break;

                case "×²":
                    num1 = Double.parseDouble(showedText);
                    showedText = String.valueOf(num1 * num1);
                    break;

                case "+/-":
                    num1 = Double.parseDouble(showedText);
                    showedText = String.valueOf(num1 * -1);
                    break;
            }
        }
        return showedText;
    }

    // RomanCalculator logic
    public String handleRomanOperations(String text_in, String text_out){
        String showedText = text_out;

        if (Character.isUpperCase(text_in.charAt(0))){
            showedText = showedText.concat(text_in);
        }

        else {
            switch (text_in){
                case "+":
                case "-":
                case "÷":
                case "*":
                    num1 =  convertRomanToInt(showedText);
                    if (num1 <= 0 || num1 > 4000){
                        showedText = "Wrong Roman numeral";
                        break;
                    }
                    showedText = "";
                    operation = text_in.charAt(0);

                    break;
                case "=":
                    num2 = convertRomanToInt(showedText);
                    if (num2 <= 0 || num2 > 4000){
                        showedText = "Wrong Roman numeral";
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
                                showedText = "Zero division error";
                            }
                            break;
                    }
                    num1 = result;
                    int temp = Integer.valueOf((int) result);
                    showedText = convertIntToRoman(temp);
                    break;

                case "×²":
                    num1 = convertRomanToInt(showedText);
                    int n = (int) ((int) num1 * num1);
                    showedText = convertIntToRoman(n);
                    break;

                case "←":
                    String str = showedText;
                    showedText = str.substring(0, str.length() -1);
                    break;

                case "#":
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    showedText = "";
                    break;
            }
        }
        return showedText;
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