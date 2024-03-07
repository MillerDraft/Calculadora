
public class BackEnd{
    public double handleOperation(String operation, double num1, double num2){
        double result = 0;

        switch (operation){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0){
                    result = num1 / num2;
                }
                break;
           /* case "²√×":
                result = Math.sqrt(num1);
                break;
            case "×²":
                result = num1 * num1;
                break;
            case "+/-":
                result = num1 * (-1);
                break;
            case "%":
                result = num1 / 100;
                break;

            */
        }
        return result;

    }
}

