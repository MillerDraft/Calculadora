import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class ReadCal extends Calculator {
    private JPanel textPanel;
    private JScrollPane jcp;
    private JLabel label;
    private JList<String> list;
    private JButton searchFile, selectLine, calculate, updateFile;
    private JButton up, down, right, left;
    private File filePath;
    private BufferedReader reader;
    private BufferedWriter writer;
    private JFileChooser fileChooser;
    private String mathResult;
    private BackEnd logic;
    private DefaultListModel<String> model;

    public ReadCal(){
        super();
        logic = new BackEnd();
        list = new JList<>();
        label = new JLabel();
        model = new DefaultListModel<>();
    }
    @Override
    public void init() {
        setFrame();
        setTextField();
        setTextPanel();
        setButtons();
        setPanel();

        addComponentsToFrame();
    }

    @Override
    public void setFrame() {
        super.setFrame();
        frame.setTitle("File reader calculator");
        frame.setLocation(840, screenHeight / 2 - frame.getHeight() / 2);
    }

    @Override
    public void setTextField() {
        super.setTextField();
        textField.setBounds(66, 20, 260, 30);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.CENTER);
        

    }

    public void setTextPanel() {
        textPanel = new JPanel();
        textPanel.setBounds(50, 80, 300, 200);
        textPanel.setLayout(new FlowLayout());
        textPanel.setFont(myFont);

        jcp = new JScrollPane(list);
        jcp.setPreferredSize(new Dimension(280, 110)); // Set the preferred size of the scroll pane

        // Set the number of visible rows in the list
        list.setVisibleRowCount(6); // Adjust the number of visible rows as needed

        textPanel.add(jcp);
        textPanel.add(label);

    }

    @Override
    public void setPanel() {
        panel = new JPanel();
        panel.setBounds(50, 300, 300, 200);
        panel.setLayout(new GridLayout(4, 0, 10, 10));

        panel.add(searchFile);
        panel.add(up);
        panel.add(selectLine);
        panel.add(down);
        panel.add(calculate);
        panel.add(updateFile);


    }

    @Override
    public void addComponentsToFrame() {
        frame.add(textField);
        frame.add(textPanel);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void setButtons() {
        // Create buttons
        searchFile = new JButton("Select File");
        selectLine = new JButton("Select Line");
        calculate = new JButton("Calculate");
        updateFile = new JButton("Update file");
        up = new JButton("Up");
        down = new JButton("Down");
        left = new JButton("Left");
        right = new JButton("Right");



        // Add buttons to Array
        functionButton = new JButton[6];

        functionButton[0] = searchFile;
        functionButton[1] = selectLine;
        functionButton[2] = calculate;
        functionButton[3] = updateFile;
        functionButton[4] = up;
        functionButton[5] = down;

        // Buttons properties
        for (int i = 0; i < functionButton.length; i++){
            if (i > 0){
                functionButton[i].setEnabled(false);
            }
            functionButton[i].setFocusable(false);
            functionButton[i].setFont(myFont);
            functionButton[i].addActionListener(this);
        }
    }

    // Buttons actions
    @Override
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        String buttonName = e.getActionCommand();
        fileChooser = new JFileChooser();
        String str = "";
        int selectedIndex;
        int numberOfLine;

        if (button == searchFile){

            int response = fileChooser.showOpenDialog(frame);

            if (response == JFileChooser.APPROVE_OPTION){
                filePath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                
                try {
                    reader = new BufferedReader(new FileReader(filePath));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        if (line.matches(".*\\d.*[+\\-*/].*\\d.*")) { // Check if the line contains numbers and math operations
                            model.addElement(line);
                        }
                    }

                    list.setModel(model);
                    reader.close();
                    numberOfLine = list.getModel().getSize();
                    textField.setText("Number of operations " + numberOfLine);
                }
                catch (IOException exc){
                    exc.printStackTrace();
                }
                calculate.setEnabled(true);
            }
            else{
                model.addElement("No File Selected");
                list.setModel(model);

            }
            selectLine.setEnabled(true);
        }

        else if (button == selectLine){
            up.setEnabled(true);
            down.setEnabled(true);
            left.setEnabled(true);
            right.setEnabled(true);
        }

        else if (button == up || button == down){
            selectedIndex = list.getSelectedIndex();

            switch (buttonName){
                case "Up":
                    if (selectedIndex == -1) {
                        selectedIndex = 0;
                    }
                    else if (selectedIndex > 0){
                        selectedIndex --;
                    }
                    break;
                case "Down":
                    if (selectedIndex == -1) {
                        selectedIndex = 0;
                    }
                    else if (selectedIndex < list.getModel().getSize() - 1){
                        selectedIndex ++;
                    }
                    break;
            }
            list.setSelectedIndex(selectedIndex);

            // Adjust visible rows to make the selected index visible in the list
            int topVisibleIndex = list.getFirstVisibleIndex();
            int bottomVisibleIndex = list.getLastVisibleIndex();

            if (selectedIndex < topVisibleIndex) {
                list.ensureIndexIsVisible(selectedIndex);
            }
            else if (selectedIndex > bottomVisibleIndex) {
                list.ensureIndexIsVisible(selectedIndex);
            }

            textField.setText("Line (" + (list.getSelectedIndex() + 1) + "):     " + list.getSelectedValue());
        }

        else if (button == calculate){
            str = list.getSelectedValue();
            boolean isFirstNum = true;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                if (Character.isDigit(ch) || (ch == '.' && (i > 0 && i < str.length() -1))){
                    sb.append(ch);
                }

                else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    if (isFirstNum) {
                        num1 = Double.parseDouble(sb.toString());
                        isFirstNum = false;
                        sb.setLength(0); // Reset the StringBuilder
                    }

                    operator = String.valueOf(ch);
                }
            }

            // Parse the second number after the loop
            num2 = Double.parseDouble(sb.toString());

            mathResult = String.valueOf(logic.handleOperation(operator, num1, num2));
            if (mathResult.endsWith(".0")){
                mathResult = mathResult.substring(0, mathResult.length() -2);
            }
            textField.setText(str.concat(" = ").concat(mathResult));
            updateFile.setEnabled(true);

        }

        else if (button == updateFile) {
            selectedIndex = list.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < model.getSize()) {
                String newValue = list.getSelectedValue().concat(" = ").concat(mathResult);
                System.out.println(textField.getText());
                model.set(selectedIndex, newValue);
                try {
                    writer = new BufferedWriter(new FileWriter(filePath));
                    for (int i = 0; i < model.size(); i++) {
                        writer.write(model.get(i));
                        writer.newLine();
                    }
                    writer.close(); // Ensure the writer is closed
                    textField.setText("File updated");
                    updateFile.setEnabled(false);
                    System.out.println("File updated successfully.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Error updating file: " + ex.getMessage());
                }
            }
            else {
                System.out.println("Invalid selected index: " + selectedIndex);
            }
            searchFile.setEnabled(true);
        }
    }

}
