package gui;

import javax.swing.*;
import java.awt.*;

public class RSureMessage extends JFrame {


    JButton SiBtn;

    JButton NoBtn;
    public RSureMessage(){
        this.setSize(400,150);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 150));
        JLabel message = new JLabel("Sei sicuro di voler Cancellare la Corsa?");
        message.setFont(new Font("sans serif", Font.BOLD, 20));

        SiBtn = new JButton("si");
        SiBtn.setPreferredSize(new Dimension (50,25));

        NoBtn = new JButton("no");
        NoBtn.setPreferredSize(new Dimension (50,25));



        panel.add(message);
        panel.add(SiBtn);
        panel.add(NoBtn);

        this.add(panel);
        this.setVisible(true);
    }
}