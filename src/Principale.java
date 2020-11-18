import javax.swing.*;
import java.awt.*;

public class Principale {
    public static void main(String[] args) {
        JFrame frame=new JFrame();


        JTextField zone_saisie;
        zone_saisie = new JTextField(10);
        zone_saisie.setPreferredSize(new Dimension(200,30));

        JPanel recherche= new JPanel(new GridLayout(1,2));
        recherche.add(new JLabel("Truc a chercher     ",JLabel.CENTER));
        recherche.add(zone_saisie);

        frame.getContentPane().add(recherche,BorderLayout.NORTH);


        JPanel question = new JPanel(new GridLayout(8,1));

        JButton button = new JButton("Button 2 (CENTER)");
        button.setPreferredSize(new Dimension(100,100));

        question.add(button);

        BorderLayout border = new BorderLayout();

        frame.add(question, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,800));
        frame.setVisible(true);
    }
}
