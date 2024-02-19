package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ChangeUserFrame extends JFrame implements ActionListener {
    JTextField oldLogin = new JTextField();
    JTextField newLogin = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton Submituser = new JButton("Conferma");
    JLabel errorLabel = new JLabel("");



    public ChangeUserFrame(){
        this.setSize(new Dimension(500,600));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout(0,0));
        this.setResizable(false);


        JLabel label = new JLabel("Cambia l'username");
        label.setFont(new Font("Serif", Font.PLAIN, 25));
        label.setPreferredSize(new Dimension(450,50));
        label.setBackground(Color.orange);



        JPanel changeuser = new JPanel();
        changeuser.setPreferredSize(new Dimension(450,200));

        label = new JLabel("Cambia l'username");
        label.setFont(new Font("Serif", Font.PLAIN, 25));
        label.setPreferredSize(new Dimension(450,50));
        label.setBackground(Color.orange);

        JLabel oldLabel = new JLabel("Inserisci la vecchia login:");
        oldLabel.setPreferredSize(new Dimension(450,25));
        oldLogin.setPreferredSize(new Dimension (450,25));
        oldLogin.addActionListener(this);

        JLabel newLabel = new JLabel("Inserisci la nuova login:");
        newLabel.setPreferredSize(new Dimension(450,25));
        newLogin.setPreferredSize(new Dimension (450,25));
        newLogin.addActionListener(this);
        
        JLabel ConfirmLabel = new JLabel("Inserisci la password");
        ConfirmLabel.setPreferredSize(new Dimension(450,25));
        passwordField.setPreferredSize(new Dimension (450,25));
        passwordField.addActionListener(this);

        Submituser.setPreferredSize(new Dimension(100,50));
        Submituser.setBackground(Color.GREEN);
        Submituser.addActionListener(this);

        errorLabel.setPreferredSize(new Dimension(500, 200));
        errorLabel.setHorizontalAlignment(JLabel.CENTER);


        changeuser.add(label);
        changeuser.add(oldLabel);
        changeuser.add(oldLogin);
        changeuser.add(newLabel);
        changeuser.add(newLogin);
        changeuser.add(ConfirmLabel);
        changeuser.add(passwordField);
        changeuser.add(Submituser);
        changeuser.add(errorLabel);


        this.add(changeuser);
        this.setVisible(true);



        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Submituser){
            try {
                Controller.getController().change_login(oldLogin.getText(), newLogin.getText(), new String(passwordField.getPassword()));
                this.setVisible(false);
            }
            catch (SQLException err){
                System.out.println("error while changing login: "+err.getMessage());
                errorLabel.setText("<html><p style=\"width:300px;color:red\">"+err.getMessage()+"</p></html>");
                errorLabel.setVisible(true);
            }
        }
    }
}
