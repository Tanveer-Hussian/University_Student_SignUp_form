import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SignUp_Form {

    JFrame frame = new JFrame("OOP Final Paper");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JButton button = new JButton();
    JButton history = new JButton();

    JTextField RollNo = new JTextField();
    JTextField name = new JTextField();
    JTextField department = new JTextField();

    JTextArea textArea = new JTextArea();

    JLabel batch = new JLabel();
    JLabel RollNoLabel = new JLabel();
    JLabel nameLabel = new JLabel();
    JLabel departmentLabel = new JLabel();

    Container container = new Container();
    String[] batches = {"19","20","21","22","23"};
    String model;


    public SignUp_Form(){

        frame.setSize(700,650);
        frame.setLayout(new GridLayout());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container.setBounds(0,0,600,600);
        container.setVisible(true);

        panel1.setBounds(0,50,300,120);

        JList list = new JList(batches);
        list.setVisibleRowCount(3);


        batch.setText("Choose Batch");
        batch.setBounds(0,0,80,80);

        RollNoLabel.setText("Roll No");
        RollNoLabel.setFont(new Font("MV bolli",Font.PLAIN,21));
        RollNoLabel.setBounds(20,150,80,80);

        nameLabel.setText("Name");
        nameLabel.setFont(new Font("MV bolli",Font.PLAIN,21));
        nameLabel.setBounds(20,240,80,80);

        departmentLabel.setText("Department");
        departmentLabel.setFont(new Font("MV bolli",Font.PLAIN,20));
        departmentLabel.setBounds(20,340,105,80);


        RollNo.setBounds(160,170,110,50);
        name.setBounds(160,260,110,50);
        department.setBounds(160,350,110,50);


        button.setText("Submit");
        button.setBounds(50,450,100,60);
        button.setFocusable(false);

        history.setText("History");
        history.setBounds(180,450,100,60);
        history.setFocusable(false);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rollnumberText = RollNo.getText().toString();
                String nameText = name.getText().toString();
                String departmentText = department.getText().toString();

                try {
                    FileOutputStream stream = new FileOutputStream("UserData.txt", true);

                    if (!RollNo.getText().toString().isEmpty()&&!name.getText().toString().isEmpty()
                            &&!department.getText().toString().isEmpty()) {

                        stream.write((" Form \n").getBytes());
                        stream.write((" Roll No: " + rollnumberText + "\n Name: " + nameText + "\n Department: "
                                + departmentText + "\n Batch: " + model + "\n").getBytes());

                        RollNo.setText(null);
                        name.setText(null);
                        department.setText(null);

                        stream.close();
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }}});

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2){
                    model = (String) list.getSelectedValue();

                }
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    FileInputStream inputStream = new FileInputStream("UserData.txt");
                    int a;
                    StringBuilder text = new StringBuilder();
                    while ((a = inputStream.read())!= -1){
                        text.append((char)a);
                    }
                    textArea.setText(text.toString());
                    inputStream.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                panel2.setBounds(300,0,400,600);
                textArea.setBackground(Color.GREEN);
                panel2.add(textArea);
            }

        });

        panel1.add(new JScrollPane(list));
        panel1.add(batch);




        container.add(panel1);
        container.add(panel2);
        container.add(RollNoLabel);
        container.add(RollNo);
        container.add(nameLabel);
        container.add(name);
        container.add(departmentLabel);
        container.add(department);
        container.add(button);
        container.add(history);

        frame.add(container);
        frame.revalidate();

    }

    public static void main(String[] args) {
        SignUp_Form signUpForm = new SignUp_Form();

    }
}
