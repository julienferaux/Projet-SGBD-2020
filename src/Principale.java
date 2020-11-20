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
    private static JButton [] boutonTab;
    private static JFrame frame;

    public static void main(String[] args) {
        initialisation_connextion();
        initialiserListener();
        frame=new JFrame();


        JTextField zone_saisie;
        zone_saisie = new JTextField(10);
        zone_saisie.setPreferredSize(new Dimension(200,30));

        JPanel recherche= new JPanel(new GridLayout(1,3));
        recherche.add(texteQuestion = new JLabel("Donné l'adresse email d'un chercheur ici ->",JLabel.CENTER));
        recherche.add(zone_saisie);

        JButton boutonValider = new JButton("Valider");
        recherche.add(boutonValider);


        boutonValider.addActionListener(new ActionListener(){
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
        String [] nomBouton = {"Question1","Question2","Question3","Question4","Question5","Question6","Question7","Question8.creation","Question8.destruction"};
        boutonTab = new JButton[nomBouton.length];

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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,800));
        frame.setResizable(false);

    }

    public static void initialiserListener(){

        actionBouton = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texteBe = e.getActionCommand();
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
                        texteQuestion.setText("Apuyer sur Valider ->");
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
                    case "Question8.creation":
                        numQuestion = 8;
                        texteQuestion.setText("Question 8");
                        jq.setText("texte de création des triggers");
                        break;
                    case "Question8.destruction":
                        numQuestion = 9;
                        texteQuestion.setText("Donné nom du trigger à detruire ->");
                }
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
            case (8) :
                sqlControleur.creerTrigger(reponce.getText());
                break;
            case (9) :
                sqlControleur.supprTrigger(recherche);
        }
    }
    public static void initialisation_connextion() {
        JFrame frameTMP = new JFrame();

        JPanel jpHaut = new JPanel();

        jpHaut.setLayout(new GridLayout(2,2));
        jpHaut.add(new JLabel("Nom d'utilisateur :"));

        JTextField jtf_utilisateur = new JTextField();
        jpHaut.add(jtf_utilisateur);

        jpHaut.add(new JLabel("mot de passe :"));
        JTextField jtf_mdp = new JTextField();
        jpHaut.add(jtf_mdp);

        frameTMP.setLayout(new GridLayout(2,1));
        frameTMP.add(jpHaut);
        JButton bouton_connexion = new JButton("Connexion");
        frameTMP.add(bouton_connexion);

        bouton_connexion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String utilisateur = jtf_utilisateur.getText();
                String mdp = jtf_mdp.getText();
                boolean retour = sqlControleur.testConnextion(utilisateur,mdp);
                if (!retour){
                    jtf_mdp.setText("erreur");
                    jtf_utilisateur.setText("erreur");
                }
                else {
                    frame.setVisible(true);
                    frameTMP.dispose();
                }
            }
        });
        frameTMP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTMP.setLocation(200,200);
        frameTMP.setSize(new Dimension(300,300));
        frameTMP.setResizable(false);
        frameTMP.setVisible(true);
    }
}