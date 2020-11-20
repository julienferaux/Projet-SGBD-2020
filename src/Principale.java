import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Principale {
    private static SQLControleur sqlControleur = new SQLControleur();

    private static ActionListener actionBouton;
    private static int numQuestion = 1;
    private static JLabel texteQuestion, jq;
    private static JTextArea reponce;

    public static void main(String[] args) {
        initialiserListener();
        JFrame frame=new JFrame();


        JTextField zone_saisie;
        zone_saisie = new JTextField(10);
        zone_saisie.setPreferredSize(new Dimension(200,30));

        JPanel recherche= new JPanel(new GridLayout(1,3));
        recherche.add(texteQuestion = new JLabel("Donné l'adresse email d'un chercheur ici ->",JLabel.CENTER));
        recherche.add(zone_saisie);

        JButton boutonChercher = new JButton("Chercher");
        recherche.add(boutonChercher);


        boutonChercher.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getID() == 1001){
                    try {
                        repondreQuestion(numQuestion,zone_saisie.getText());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    zone_saisie.setText("");
                }
            }
        });



        JPanel question = new JPanel(new GridLayout(9,1));
        String [] nomBouton = {"Question1","Question2","Question3","Question4","Question5","Question6","Question7","Question8","Question9"};
        JButton [] boutonTab = new JButton[nomBouton.length];

        for (int i = 0; i < nomBouton.length; i++){
            boutonTab[i]=new JButton(nomBouton[i]);
            question.add(boutonTab[i]);
            boutonTab[i].addActionListener(actionBouton);
        }




        jq = new JLabel("1. Détermination de la liste des articles écrits par un auteur donné.");
        recherche.add(jq);

        reponce = new JTextArea(2,2);



        JPanel dessus = new JPanel(new GridLayout(2,1));
        dessus.add(recherche);
        dessus.add(jq);


        frame.getContentPane().add(dessus,BorderLayout.NORTH);
        frame.add(question, BorderLayout.WEST);

        frame.getContentPane().add(new JScrollPane(reponce));
        //frame.add(reponce);

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
                    case "Question1":
                        numQuestion = 1;
                        texteQuestion.setText("Donné l'adresse email d'un chercheur ici ->");
                        jq.setText("1. Détermination de la liste des articles écrits par un auteur donné.");
                        break;
                    case "Question2":
                        numQuestion = 2;
                        texteQuestion.setText("Donné l'adresse email d'un chercheur ici ->");
                        jq.setText("2. Affichage de la liste des co-auteurs ayant travaillé avec un chercheur donné.");
                        break;
                    case "Question3":
                        numQuestion = 3;
                        texteQuestion.setText("Apuyer sur chercher ->");
                        jq.setText("3. Affichage de la liste des laboratoires de chaque chercheur.");
                        break;
                    case "Question4":
                        numQuestion = 4;
                        texteQuestion.setText("Donné le nombre d’article minimum ->");
                        jq.setText("4. Affichage la liste des chercheurs ayant annoté au moins un nombre donné d articles.");
                        break;
                    case "Question5":
                        numQuestion = 5;
                        texteQuestion.setText("Donné l'adresse email d'un chercheur ici ->");
                        jq.setText("5. Calcul de la moyenne des notes données par un chercheur donné.");
                        break;
                    case "Question6":
                        numQuestion = 6;
                        texteQuestion.setText("Donné le nom d'un laboratoire ici ->");
                        jq.setText("<html>Pour chaque chercheur d'un laboratoire donné, afficher le nombre d'articles<br>lpubliés, le nombre et la moyenne des notes obtenues. On classera les<br>chercheurs par ordre décroissant du nombre d'articles publiés.</html>");
                        break;
                    case "Question7":
                        numQuestion = 7;
                        texteQuestion.setText("Donné le Titre d'un article ici ->");
                        jq.setText("<html>Verification que la note maximale d'un article donné n'a pas été attribuée par<br>un chercheur appartenant au meme laboratoire que l'un des auteurs de cet article</html>");
                        break;
                    case "Question8":
                        numQuestion = 8;
                        texteQuestion.setText("Question 8");
                        jq.setText("<html>TRIGGERS<br></html>");
                        break;
                }
                System.out.println(numQuestion);
            }
        };
    }
    public static void repondreQuestion(int nbQ,String recherche) throws SQLException {
        switch (nbQ){
            case (1) :
                reponce.setText(sqlControleur.q1(recherche));
                break;
            case (2) :
                reponce.setText(sqlControleur.q2(recherche));
                break;
            case (3) :
                reponce.setText(sqlControleur.q3());
                break;
            case (4) :
                reponce.setText(sqlControleur.q4(Integer.parseInt(recherche)));
                break;
            case (5) :
                reponce.setText(sqlControleur.q5(recherche));
                break;
            case (6) :
                reponce.setText(sqlControleur.q6(recherche));
                break;
            case (7) :
                reponce.setText(sqlControleur.q7(recherche));
                break;
                /*
            case (8) :
                System.out.println("coucou");
                sqlControleur.q8();
                break;
            */
        }
    }
}