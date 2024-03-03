"Updated MultiCalculator"
Change the position of both calculators on the screen.
Calculator class now has attibutes and method that are using in both child classes, particular methods and attributes were moved to each child class.
Resizing calculators is no longer available.
Unnecessary button decimal eliminated from RomanCalculator class.
Unnecessay method setFont eliminated.
Each calculator has a different font.

### Next step
- In ModernCal, separate the logic to do the calculations into another class.
The goal is that ModernCal's responsibility is to handle user interaction, 
while the new class will handle the calculation's logic.

### 24.03.03
Great the BackEnd now performs the actual calculations

Next step 
- BackEnd must only deal with `int` or `string` in its methods, and it should not be aware of UI objects like buttons or textfields.
E.g. `public void performOperation(string operation, string num1, string num2) { ... }`
- ModernCal and RomanCalculator must handle their own button's events. 
They should decide which operator was pressed (e.g. button.getText(), and the value from the textfield, and then call a method in the backed with the values already parsed)
E.g. 
``` java
public void handleOperations(Object source, JTextField textField) { 
    ...
    case "=":
        num2 = Double.parseDouble(textField.getText());
        result = backend.performOperation(operation, num1, num2);
        ...
        textField.setText(String.valueOf(result));
    ...    
    }
```
