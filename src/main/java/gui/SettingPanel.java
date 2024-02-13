package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SettingPanel extends JPanel{


    JButton changeuBtn;
    JButton changepBtn;
    JFrame ChangeUserPanel;

    JFrame ChangepswPanel;

    public SettingPanel() {

        this.setPreferredSize(new Dimension(600, 700));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        Controller controller= Controller.getController();


        JPanel dati = new JPanel();
        dati.setPreferredSize(new Dimension(600, 200));
        JLabel label = new JLabel("I tuoi dati");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("sans serif", Font.BOLD, 45));
        label.setPreferredSize(new Dimension(600, 50));
        JLabel Usernamerow = new JLabel("Username: "+controller.getNome(controller.getUtente()));
        JLabel Idrow = new JLabel("Id: "+controller.getIdUtente());

        Usernamerow.setPreferredSize(new Dimension(300, 30));
        Idrow.setPreferredSize(new Dimension(300, 30));


        JPanel impostazioni = new JPanel();
        impostazioni.setPreferredSize(new Dimension(600, 100));
        JLabel impLabel = new JLabel("Impostazioni");
        impLabel.setIcon(new ImageIcon("src/impostazioni.png"));
        impLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        impLabel.setHorizontalAlignment(JLabel.LEFT);


        JPanel changeuser = new JPanel();
        changeuser.setPreferredSize(new Dimension(600, 100));
        JButton changeuBtn = new JButton("Cambia login");
        changeuBtn.addActionListener(e -> {
            new ChangeUserFrame();
        });


        JPanel changepsw = new JPanel();
        changepsw.setPreferredSize(new Dimension(600, 100));
        JButton changepBtn = new JButton("Cambia password");
        changepBtn.addActionListener(e -> {
            new ChangePasswordFrame();
        });


        dati.add(label);
        dati.add(Usernamerow);
        dati.add(Idrow);

        impostazioni.add(impLabel);

        changeuser.add(changeuBtn);

        changepsw.add(changepBtn);


        this.add(dati);
        this.add(impostazioni);
        this.add(changeuser);
        this.add(changepsw);
        setVisible(true);
    }


}