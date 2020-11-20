import java.sql.*;

public class SQLControleur {

    private final String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
    private String utilisateur, motdepasse;
    private Connection con;


    public Boolean testConnextion(String utilisateur, String mdp){
        boolean res = false;
        try {
             this.con = DriverManager.getConnection(url, utilisateur, mdp);
            res = true;
            this.utilisateur = utilisateur;
            this.motdepasse=mdp;


        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return res;
    }

    public String q1(String recherche) throws SQLException {
        StringBuilder res = new StringBuilder();
        String user = utilisateur;
        String mdp = motdepasse;
        this.con = DriverManager.getConnection(url, user, mdp);
        //recherche par email
        String req1 = "select ARTICLE.TITRE, RESUME, TYPEARTICLE\n" +
                "from ARTICLE inner join ECRIRE on ARTICLE.TITRE = ECRIRE.TITRE\n" +
                "where EMAIL = ?";
        PreparedStatement st1 = this.con.prepareStatement(req1);

        st1.setString(1, recherche);
        st1.execute();
        ResultSet rs1 = st1.getResultSet();

        res.append("q1 email : \n");
        while (rs1.next()){
            res.append("titre : ").append(rs1.getString(1)).append("\nresume : ")
                    .append(rs1.getString(2))
                    .append("\ntype : ").append(rs1.getString(3)).append("\n").append("\n");
        }
        res.append("\n");


        System.out.println(res);
        return res.toString();
    }

    public String q2(String recherche) throws SQLException {
        StringBuilder res = new StringBuilder();
        String user = utilisateur;
        String mdp = motdepasse;
        this.con = DriverManager.getConnection(url, user, mdp);

        String req1 = "select NOMCHERCHEUR, PRENOMCHERCHEUR, CHERCHEUR.EMAIL\n" +
                "from ECRIRE inner join CHERCHEUR on ECRIRE.EMAIL = CHERCHEUR.EMAIL\n" +
                "where TITRE = (select TITRE from ECRIRE where EMAIL = ?) and CHERCHEUR.EMAIL != ?";
        PreparedStatement st1 = this.con.prepareStatement(req1);

        st1.setString(1, recherche);
        st1.setString(2, recherche);

        st1.execute();
        ResultSet rs1 = st1.getResultSet();
        res.append("co-chercheur de ").append(recherche).append(" : \n").append("\n");
        while (rs1.next()) {
            res.append(rs1.getString(1))
                    .append(" ").append(rs1.getString(2))
                    .append(" ").append(rs1.getString(3));
        }


        return res.toString();
    }

    public String q3() throws SQLException {
        StringBuilder res = new StringBuilder();
        String user = utilisateur;
        String mdp = motdepasse;
        this.con = DriverManager.getConnection(url, user, mdp);

        Statement st = this.con.createStatement();
        String req1 = "select EMAIL\n" +
                "from CHERCHEUR";

        String req2 = "select NOMLABO\n" +
                "from TRAVAILLER inner join CHERCHEUR on CHERCHEUR.EMAIL = TRAVAILLER.EMAIL\n" +
                "where CHERCHEUR.EMAIL = ?";
        PreparedStatement pst = this.con.prepareStatement(req2);

        st.execute(req1);
        ResultSet rs1 = st.getResultSet();

        while (rs1.next()) {
            String chercheur = rs1.getString(1);
            res.append(chercheur).append(" : ");
            pst.setString(1, chercheur);
            pst.execute();
            ResultSet rs2 = pst.getResultSet();
            while (rs2.next()) {
                res.append("    ").append(rs2.getString(1));
            }
            res.append("\n");
        }
        res.append("\n");


        return res.toString();
    }

    public String q4(int nbArticle) throws SQLException {
        StringBuilder res= new StringBuilder();
        String user = utilisateur;
        String mdp = motdepasse;
        this.con = DriverManager.getConnection(url, user, mdp);

        String req1 = "select distinct\n" +
                "                EMAIL\n" +
                "from ANNOTER A1\n" +
                "where (select count(EMAIL)\n" +
                "       from ANNOTER\n" +
                "       where EMAIL = A1.EMAIL\n" +
                "       group by EMAIL) >= ?";

        PreparedStatement pst1 = this.con.prepareStatement(req1);
        pst1.setInt(1, nbArticle);
        pst1.execute();
        ResultSet rs1 = pst1.getResultSet();
        while (rs1.next()) {
            res.append(rs1.getString(1)).append("\n");
        }
        res.append("\n");

        return res.toString();
    }


    public String q5(String recherche) throws SQLException {
        String res ="";
        String user = utilisateur;
        String mdp = motdepasse;
        this.con = DriverManager.getConnection(url, user, mdp);

        String req1 = "select AVG(NOTE)\n" +
                "from NOTER\n" +
                "where EMAIL = ?";

        PreparedStatement pst1 = this.con.prepareStatement(req1);
        pst1.setString(1, recherche);

        pst1.execute();
        ResultSet rs1 = pst1.getResultSet();

        rs1.next();
        res+=("moy : " + rs1.getFloat(1));


        return res;
    }

    public String q6(String recherche) throws SQLException {
        String res ="";
        String user = utilisateur;
        String mdp = motdepasse;
        this.con = DriverManager.getConnection(url, user, mdp);

        String labo = "Department of Computer and Information Science University of Pennsylvania";

        String req1 = "select NOMCHERCHEUR, PRENOMCHERCHEUR, CHERCHEUR.EMAIL\n" +
                "from LABORATOIRE\n" +
                "         inner join TRAVAILLER on LABORATOIRE.NOMLABO = TRAVAILLER.NOMLABO\n" +
                "         inner join CHERCHEUR on CHERCHEUR.EMAIL = TRAVAILLER.EMAIL\n" +
                "where LABORATOIRE.NOMLABO = ?";

        String req2 = "select count(distinct ECRIRE.TITRE), count(NOTE), ROUND(avg(NOTE), 2)\n" +
                "from ECRIRE inner join NOTER on ECRIRE.TITRE = NOTER.TITRE\n" +
                "where ECRIRE.EMAIL = ?";

        PreparedStatement pst1 = this.con.prepareStatement(req1);
        PreparedStatement pst2 = this.con.prepareStatement(req2);

        pst1.setString(1, labo);
        pst1.execute();
        ResultSet rs1 = pst1.getResultSet();

        res += ("chercheurs du lab " + labo + " : \n");
        while (rs1.next()) {
            res += ("    " + rs1.getString(1) + " " + rs1.getString(2) + " : ");
            pst2.setString(1, rs1.getString(3));
            pst2.execute();
            ResultSet rs2 = pst2.getResultSet();
            rs2.next();
            res += ("        nombre d'article : " + rs2.getInt(1)
                    + "  nombre de note recu : " + rs2.getInt(2)
                    + "  moy note : " + rs2.getFloat(3) + "\n");
        }
        res += ("\n");


        return res;
    }


    public String q7(String recherche) throws SQLException {
        String res = "";
        String user = utilisateur;
        String mdp = motdepasse;
        this.con = DriverManager.getConnection(url, user, mdp);

        String req1 = "select EMAIL\n" +
                "from NOTER\n" +
                "where TITRE = ?\n" +
                "  and NOTE = (select max(NOTE)\n" +
                "              from NOTER\n" +
                "              where TITRE = ?)";

        String req2 = "select distinct NOTER.EMAIL\n" +
                "from NOTER\n" +
                "         inner join TRAVAILLER on NOTER.EMAIL = TRAVAILLER.EMAIL\n" +
                "where NOMLABO = ANY (select NOMLABO\n" +
                "                     from ECRIRE\n" +
                "                              inner join TRAVAILLER on ECRIRE.EMAIL = TRAVAILLER.EMAIL\n" +
                "                     where TITRE = ?)";

        PreparedStatement pst1 = this.con.prepareStatement(req1);
        PreparedStatement pst2 = this.con.prepareStatement(req2);

        pst1.setString(1, recherche);
        pst1.setString(2, recherche);
        pst2.setString(1, recherche);

        pst1.execute();
        pst2.execute();

        ResultSet rs1 = pst1.getResultSet();

        rs1.next();
        String emailNoteMax = rs1.getString(1);

        ResultSet rs2 = pst2.getResultSet();

         res = "La note maximal de cet article n'a pas été donnée par un chercheur du meme laboratoire.";//réponse a la question

        while (rs2.next()){
            if(rs2.getString(1).equals(emailNoteMax)){
                res = "La note maximal de cet article a été donnée par un chercheur du meme laboratoire.";
                break;
            }
        }
        System.out.println(res);

        return res;
    }

    public void creerTrigger(String codeTrigger) throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        this.con = DriverManager.getConnection(url, user, mdp);

        Statement stt = this.con.createStatement();

        stt.execute(codeTrigger);
        this.con.commit();
    }
    public void supprTrigger(String nomTrigger) throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        this.con = DriverManager.getConnection(url, user, mdp);

        Statement stt = this.con.createStatement();

        String req1 = "drop trigger "+nomTrigger;

        stt.execute(req1);
        this.con.commit();
    }

}
