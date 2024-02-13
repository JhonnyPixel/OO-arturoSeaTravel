package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

public class ChangePasswordFrame extends JFrame implements ActionListener {
    JTextField login = new JTextField();
    JPasswordField newPsw = new JPasswordField();
    JPasswordField oldPsw = new JPasswordField();
    JButton Submitpassword = new JButton("Conferma");
    JLabel StatusLabel = new JLabel("error");
    public ChangePasswordFrame(){
        this.setSize(new Dimension(500,600));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout(0,0));
        this.setResizable(false);





        JPanel changePsw = new JPanel();
        JLabel label = new JLabel("Cambia la Password");
        label.setFont(new Font("Serif", Font.PLAIN, 25));
        label.setPreferredSize(new Dimension(450,50));
        changePsw.setPreferredSize(new Dimension(450,200));

        JLabel oldLabel = new JLabel("Inserisci la login:");
        oldLabel.setPreferredSize(new Dimension(450,25));
        login.setPreferredSize(new Dimension (450,25));
        login.addActionListener(this);
        
        JLabel newLabel = new JLabel("Inserisci la nuova password:");
        newLabel.setPreferredSize(new Dimension(450,25));
        newPsw.setPreferredSize(new Dimension (450,25));
        newPsw.addActionListener(this);
        
        JLabel ConfirmLabel = new JLabel("Inserisci la vecchia password:");
        ConfirmLabel.setPreferredSize(new Dimension(450,25));
        oldPsw.setPreferredSize(new Dimension (450,25));
        oldPsw.addActionListener(this);
        
        Submitpassword.setPreferredSize(new Dimension(100,50));
        Submitpassword.setBackground(Color.GREEN);
        Submitpassword.addActionListener(this);

        StatusLabel.setPreferredSize(new Dimension(500, 200));
        StatusLabel.setHorizontalAlignment(JLabel.CENTER);
        StatusLabel.setVisible(false);


        changePsw.add(label);
        changePsw.add(oldLabel);
        changePsw.add(login);
        changePsw.add(newLabel);
        changePsw.add(newPsw);
        changePsw.add(ConfirmLabel);
        changePsw.add(oldPsw);
        changePsw.add(Submitpassword);
        changePsw.add(StatusLabel);


        this.add(changePsw);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Submitpassword){
            try {
                Controller.getController().change_password(login.getText(), new String(newPsw.getPassword()), new String(oldPsw.getPassword()));
                this.setVisible(false);
            }
            catch (SQLException err){
                System.out.println("error while changing password: "+err.getMessage());
                StatusLabel.setText("<html><p style=\"width:300px;color:red\">"+err.getMessage()+"</p></html>");
                StatusLabel.setVisible(true);
            }
        }
        
    }
}
