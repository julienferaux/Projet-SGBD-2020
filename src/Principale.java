import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principale {

    private static ActionListener actionBouton;

    private static int numQuestion = 1;
    private static JLabel texteQuestion;

    public static void main(String[] args) {
        initialiserListener();
        JFrame frame=new JFrame();


        JTextField zone_saisie;
        zone_saisie = new JTextField(10);
        zone_saisie.setPreferredSize(new Dimension(200,30));

        JPanel recherche= new JPanel(new GridLayout(1,2));
        recherche.add(texteQuestion = new JLabel("Question 1      ",JLabel.CENTER));
        recherche.add(zone_saisie);

        zone_saisie.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getID() == 1001){
                    System.out.println("bibite");
                    zone_saisie.setText("zdazd\nzadaz");
                }
            }
        });



        JPanel question = new JPanel(new GridLayout(8,1));
        String [] nomBouton = {"bouton1","bouton2","bouton3","bouton4","bouton5","bouton6","bouton7","bouton8"};
        JButton [] boutonTab = new JButton[8];

        for (int i = 0; i < nomBouton.length; i++){
            boutonTab[i]=new JButton(nomBouton[i]);
            question.add(boutonTab[i]);
            boutonTab[i].addActionListener(actionBouton);
        }




        frame.getContentPane().add(recherche,BorderLayout.NORTH);
        frame.add(question, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,800));
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void initialiserListener(){

        actionBouton = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texteBe = e.getActionCommand();
                //System.out.println(texteBe);
                switch (texteBe){
                    case "bouton1":
                        numQuestion = 1;
                        texteQuestion.setText("Donné l'adresse d'un auteur ici ->");
                        break;
                    case "bouton2":
                        numQuestion = 2;
                        texteQuestion.setText("Question 2");
                        break;
                    case "bouton3":
                        numQuestion = 3;
                        texteQuestion.setText("Question 3");
                        break;
                    case "bouton4":
                        numQuestion = 4;
                        texteQuestion.setText("Question 4");
                        break;
                    case "bouton5":
                        numQuestion = 5;
                        texteQuestion.setText("Question 5");
                        break;
                    case "bouton6":
                        numQuestion = 6;
                        texteQuestion.setText("Question 6");
                        break;
                    case "bouton7":
                        numQuestion = 7;
                        texteQuestion.setText("Question 7");
                        break;
                    case "bouton8":
                        numQuestion = 8;
                        texteQuestion.setText("Question 8");
                        break;
                }
                System.out.println(numQuestion);
            }
        };
    }
}