import javax.swing.*;
import java.util.HashMap;
import java.util.TreeMap;

public class BackEnd{
    private double num1 = 0;
    private double num2 = 0;
    private double result = 0;
    private char operation = ' ';
    protected String showText = ""; 
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
    public String handleModernOperations(String text1, String text2){

        if (Character.isDigit(text1.charAt(0))){
            showText = text2.concat(text1);
        }

        else {
            switch (text1){
                case ".":
                    if (text2.isEmpty()){
                        showText = text2.concat("0.");
                    }
                    else if (text2.contains(".")){
                        showText = text2.concat(".");
                    }
                    break;

                case "+":
                case "-":
                case "*":
                case "÷":
                    num1 = Double.parseDouble(text2);
                    operation = text1.charAt(0);
                    showText = "";
                    break;

                case "=":
                    num2 = Double.parseDouble(text2);

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
                                showText = "Zero division error. You can't divide by 0";
                            }
                            break;
                    }
                    num1 = result;
                    // if result is a round number remove final zero, if not show it as double
                    int temp = (int) Math.floor(result);
                    if (result == Math.floor(result)){
                        showText = String.valueOf(temp);
                    }
                    else {
                        showText = String.valueOf(result);
                    }
                    break;

                case "C":
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    showText = "";
                    break;

                case "←":
                    showText = (text2.substring(0, text2.length() -1));
                    break;

                case "%":
                    num1 = Double.parseDouble(text2);
                    showText = String.valueOf(num1 / 100);
                    break;

                case "²√×":
                    num1 = Double.parseDouble(text2);
                    showText = String.valueOf(Math.sqrt(num1));
                    break;

                case "×²":
                    num1 = Double.parseDouble(text2);
                    showText = String.valueOf(num1 * num1);
                    break;

                case "+/-":
                    num1 = Double.parseDouble(text2);
                    showText = String.valueOf(num1 * -1);
                    break;
            }
        }
        return showText;
    }

    // RomanCalculator logic
    public String handleRomanOperations(String text1, String text2){

        if (Character.isUpperCase(text1.charAt(0))){
            showText = text2.concat(text1);
        }

        else {
            switch (text1){
                case "+":
                case "-":
                case "÷":
                case "*":
                    num1 =  convertRomanToInt(text2);
                    if (num1 <= 0 || num1 > 4000){
                        showText = "Wrong Roman numeral";
                        break;
                    }
                    showText = "";
                    operation = text1.charAt(0);

                    break;
                case "=":
                    num2 = convertRomanToInt(text2);
                    if (num2 <= 0 || num2 > 4000){
                        showText = "Wrong Roman numeral";
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
                                showText = "Zero division error";
                            }
                            break;
                    }
                    num1 = result;
                    int temp = Integer.valueOf((int) result);
                    showText = convertIntToRoman(temp);
                    break;

                case "×²":
                    num1 = convertRomanToInt(text2);
                    int n = (int) ((int) num1 * num1);
                    showText = convertIntToRoman(n);
                    break;

                case "←":
                    showText = text2.substring(0, text2.length() -1);
                    break;

                case "#":
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    showText = "";
                    break;
            }
        }
        return showText;
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
        int res = 0;
        int prev = 0;
        for (int i = str.length() -1; i >= 0; i--){
            String currentSymbol = String.valueOf(str.charAt(i)); // Convert char to String
            int curr = keyMap.get(currentSymbol); // Use the String as key to get the value from keyMap
            if (curr < prev){
                res -= curr;
            }
            else{
                res += curr;
            }
            prev = curr;
        }
        return res;
    }
}