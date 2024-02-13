package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RegisterFrame extends JFrame implements KeyListener, ActionListener {
    private JTextField login_input;
    private JPasswordField password_input;

    private JPasswordField checkpsw_input;
    private JLabel label_error;
    public RegisterFrame(){

        this.setSize(350,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(new Color(31,29,101));

        JPanel panel=new JPanel();
        panel.setPreferredSize(new Dimension(300,450));
        panel.setLayout(new FlowLayout());

        ImageIcon logo=new ImageIcon("src/arturos.png");
        JLabel label_logo=new JLabel();
        label_logo.setIcon(logo);

        JPanel panel_input=new JPanel();
        panel_input.setPreferredSize(new Dimension(300,300));
        panel_input.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));

        JLabel login=new JLabel("Username");

        JLabel password=new JLabel("Password");

        JLabel checkpsw = new JLabel("Conferma Password");


        login_input=new JTextField();
        login_input.setPreferredSize(new Dimension(250,30));
        login_input.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(0, 184, 230)));
        login_input.setBackground(null);
        login_input.addKeyListener(this);

        password_input=new JPasswordField();
        password_input.setPreferredSize(new Dimension(250,30));
        password_input.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(0, 184, 230)));
        password_input.setBackground(null);
        password_input.addKeyListener(this);


        checkpsw_input=new JPasswordField();
        checkpsw_input.setPreferredSize(new Dimension(250,30));
        checkpsw_input.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(0, 184, 230)));
        checkpsw_input.setBackground(null);
        checkpsw_input.addKeyListener(this);


        label_error=new JLabel();
        label_error.setForeground(Color.red);

        panel.add(label_logo);
        panel_input.add(login);
        panel_input.add(login_input);
        panel_input.add(password);
        panel_input.add(password_input);
        panel_input.add(checkpsw);
        panel_input.add(checkpsw_input);
        panel_input.add(label_error);
        panel.add(panel_input);
        this.add(panel);


        this.setVisible(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("you typed:"+e.getKeyCode());
        if(e.getKeyCode()==10) {
            if (login_input.getText().equals("")) {
                label_error.setText("Login can't be empty");
                return;
            }
            if (password_input.getText().equals("")) {
                label_error.setText("Password can't be empty");
                return;
            }
            if (checkpsw_input.getText().equals("")) {
                label_error.setText("Please confirm the new password");
                return;
            }

            Controller controller=new Controller();
            if( controller.loginUtente(login_input.getText(),password_input.getText()) != null){
                this.setVisible(false);
                new FramePasseggero();
            }
            else if(controller.loginCompagnia(login_input.getText(),password_input.getText())!=null){
                this.setVisible(false);
                new FrameCompagnia();
            }
            else{
                label_error.setText("C'è stato un probelma con la registrazione...riprova piu tardi");
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
