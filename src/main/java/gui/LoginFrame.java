package gui;

import MODEL.Utente;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Math;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class LoginFrame extends JFrame implements KeyListener {
    private JTextField login_input;
    private JPasswordField password_input;
    private JLabel label_error;


    public LoginFrame(){

        this.setSize(350,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(new Color(255,255,255));


        JPanel panel=new JPanel();
        //panel.setBackground(Color.lightGray); --->
        panel.setPreferredSize(new Dimension(300,450));
        panel.setLayout(new FlowLayout());

        ImageIcon logo=new ImageIcon("src/arturos.png");
        JLabel label_logo=new JLabel();
        label_logo.setIcon(logo);

        JPanel panel_input=new JPanel();
        panel_input.setPreferredSize(new Dimension(300,300));
        //panel_input.setBackground(Color.pink); --->
        panel_input.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));


        JLabel label_login=new JLabel();
        label_login.setText(" Login ");

        JLabel label_password=new JLabel();
        label_password.setText("Password");

        login_input=new JTextField();
        password_input=new JPasswordField();
        login_input.setPreferredSize(new Dimension(250,30));
        password_input.setPreferredSize(new Dimension(250,30));
        login_input.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(0, 184, 230)));
        password_input.setBorder(BorderFactory.createMatteBorder(0,0,3,0,new Color(0, 184, 230)));
        login_input.setBackground(null);
        password_input.setBackground(null);
        login_input.addKeyListener(this);
        password_input.addKeyListener(this);

        label_error=new JLabel("    ");
        label_error.setForeground(Color.red);

        JButton registrati=new JButton("<HTML><u>Sei nuovo? Registrati</u></HTML>");

        registrati.setPreferredSize(new Dimension(300,20));
        registrati.setHorizontalTextPosition(JLabel.CENTER);
        registrati.setHorizontalAlignment(JLabel.CENTER);
        registrati.setBorder(null);
        registrati.setBackground(new Color(255,255,255,0));
        registrati.setForeground(new Color(0,0,0));
        registrati.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registrati.setFocusable(false);
        registrati.addActionListener(e->{
            this.setVisible(false);
            new RegisterFrame();
        });



        panel.add(label_logo);
        panel_input.add(label_login);
        panel_input.add(login_input);
        panel_input.add(label_password);
        panel_input.add(password_input);
        panel_input.add(label_error);
        panel_input.add(registrati);
        panel.add(panel_input);
        this.add(panel);


        this.setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

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
            Controller controller=Controller.getController();
            Utente utente;
            if( (utente=controller.loginUtente(login_input.getText(),password_input.getText()))!= null){
                controller.setUtenteLoggato(utente);
                this.setVisible(false);
                new FramePasseggero();
            }
            else if((utente=controller.loginCompagnia(login_input.getText(),password_input.getText()))!=null){
                controller.setUtenteLoggato(utente);
                this.setVisible(false);
                new FrameCompagnia();
            }
            else{
                label_error.setText("Nessun utente trovato...registrati");
            }

        }
    }



}
