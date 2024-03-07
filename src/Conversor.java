import java.util.HashMap;
import java.util.TreeMap;

public class Conversor {
    private TreeMap<Integer, String> numTreeMap; // for implement method convert Int to Roman #
    private HashMap<String, Integer> keyMap; // for implement method convert Roman to Int
    public Conversor(){
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
