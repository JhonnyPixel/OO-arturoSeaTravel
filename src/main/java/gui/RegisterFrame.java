package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class RegisterFrame extends JFrame implements KeyListener, ActionListener {

    private JTextField usernameInput;
    private JTextField loginInput;
    private JPasswordField passwordInput;
    private JSpinner etaInput;
    private JLabel label_error;

    private LoginFrame loginFrame;
    public RegisterFrame(LoginFrame logFrame){

        this.setSize(350,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(new Color(31,29,101));

        this.loginFrame=logFrame;

        JPanel panel=new JPanel();
        panel.setPreferredSize(new Dimension(300,450));
        panel.setLayout(new FlowLayout());

        ImageIcon logo=new ImageIcon("src/arturos.png");
        JLabel label_logo=new JLabel();
        label_logo.setIcon(logo);

        JPanel panel_input=new JPanel();
        panel_input.setPreferredSize(new Dimension(300,300));
        panel_input.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));

        JLabel username=new JLabel("Username");

        JLabel login=new JLabel("Login");

        JLabel password = new JLabel("Password");

        JLabel eta=new JLabel("Eta");


        usernameInput=new JTextField();
        usernameInput.setPreferredSize(new Dimension(250,30));
        usernameInput.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(0, 184, 230)));
        usernameInput.setBackground(null);
        usernameInput.addKeyListener(this);

        loginInput=new JTextField();
        loginInput.setPreferredSize(new Dimension(250,30));
        loginInput.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(0, 184, 230)));
        loginInput.setBackground(null);
        loginInput.addKeyListener(this);


        passwordInput=new JPasswordField();
        passwordInput.setPreferredSize(new Dimension(250,30));
        passwordInput.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(0, 184, 230)));
        passwordInput.setBackground(null);
        passwordInput.addKeyListener(this);

        SpinnerModel etaModel=new SpinnerNumberModel(10,10,100,1);
        etaInput=new JSpinner(etaModel);
        etaInput.setPreferredSize(new Dimension(250,30));
        etaInput.setBackground(null);
        etaInput.addKeyListener(this);


        label_error=new JLabel();
        label_error.setForeground(Color.red);

        panel.add(label_logo);
        panel_input.add(username);
        panel_input.add(usernameInput);
        panel_input.add(login);
        panel_input.add(loginInput);
        panel_input.add(password);
        panel_input.add(passwordInput);
        panel_input.add(eta);
        panel_input.add(etaInput);
        panel_input.add(label_error);
        panel.add(panel_input);
        this.add(panel);


        this.setVisible(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("you typed:"+e.getKeyCode());
        if(e.getKeyCode()==10) {
            if (usernameInput.getText().equals("")) {
                label_error.setText("Username can't be empty");
                return;
            }
            if (loginInput.getText().equals("")) {
                label_error.setText("Login can't be empty");
                return;
            }
            if (passwordInput.getText().equals("")) {
                label_error.setText("Password confirm the new password");
                return;
            }


            try{
                Controller.getController().registerPasseggero(usernameInput.getText(),loginInput.getText(),passwordInput.getText(),Integer.valueOf(etaInput.getValue().toString()));
                this.setVisible(false);
                loginFrame.setVisible(true);
            }
            catch (SQLException err){
                label_error.setText(err.getMessage());
                System.out.println(err.getMessage());
            }

        }
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
