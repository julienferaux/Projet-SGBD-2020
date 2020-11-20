import java.sql.*;

public class SQLControleur {

    private String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";
    private String utilisateur, motdepasse;


    public Boolean testConnextion(String utilisateur, String mdp){
        Boolean res = false;
        try {
            Connection con1 = DriverManager.getConnection(url, utilisateur, mdp);
            res = true;
            this.utilisateur = utilisateur;
            this.motdepasse=mdp;

            con1. close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return res;
    }

    public String q1(String recherche) throws SQLException {
        String res = "";
        String user = utilisateur;
        String mdp = motdepasse;
        Connection con = DriverManager.getConnection(url, user, mdp);
        //recherche par email
        String req1 = "select ARTICLE.TITRE, RESUME, TYPEARTICLE\n" +
                "from ARTICLE inner join ECRIRE on ARTICLE.TITRE = ECRIRE.TITRE\n" +
                "where EMAIL = ?";
        PreparedStatement st1 = con.prepareStatement(req1);

        String email = recherche;
        st1.setString(1, email);
        st1.execute();
        ResultSet rs1 = st1.getResultSet();

        res += "q1 email : \n";
        while (rs1.next()){
            res += ("titre : "+rs1.getString(1)+
                    "\nresume : "+rs1.getString(2)+
                    "\ntype : "+rs1.getString(3)+"\n"+"\n");
        }
        res += "\n";
        con.close();

        System.out.println(res);
        return res;
    }

    public String q2(String recherche) throws SQLException {
        String res = "";
        String user = utilisateur;
        String mdp = motdepasse;
        Connection con = DriverManager.getConnection(url, user, mdp);

        String req1 = "select NOMCHERCHEUR, PRENOMCHERCHEUR, CHERCHEUR.EMAIL\n" +
                "from ECRIRE inner join CHERCHEUR on ECRIRE.EMAIL = CHERCHEUR.EMAIL\n" +
                "where TITRE = (select TITRE from ECRIRE where EMAIL = ?) and CHERCHEUR.EMAIL != ?";
        PreparedStatement st1 = con.prepareStatement(req1);

        String email = recherche;
        st1.setString(1, email);
        st1.setString(2, email);

        st1.execute();
        ResultSet rs1 = st1.getResultSet();
        res +=("co-chercheur de " + email + " : \n"+"\n");
        while (rs1.next()) {
            res += (rs1.getString(1)
                    + " " + rs1.getString(2)
                    + " " + rs1.getString(3));
        }

        con.close();
        return res;
    }

    public String q3() throws SQLException {
        String res = "";
        String user = utilisateur;
        String mdp = motdepasse;
        Connection con = DriverManager.getConnection(url, user, mdp);

        Statement st = con.createStatement();
        String req1 = "select EMAIL\n" +
                "from CHERCHEUR";

        String req2 = "select NOMLABO\n" +
                "from TRAVAILLER inner join CHERCHEUR on CHERCHEUR.EMAIL = TRAVAILLER.EMAIL\n" +
                "where CHERCHEUR.EMAIL = ?";
        PreparedStatement pst = con.prepareStatement(req2);

        st.execute(req1);
        ResultSet rs1 = st.getResultSet();

        while (rs1.next()) {
            String chercheur = rs1.getString(1);
            res += (chercheur + " : ");
            pst.setString(1, chercheur);
            pst.execute();
            ResultSet rs2 = pst.getResultSet();
            while (rs2.next()) {
                res += ("    " + rs2.getString(1));
            }
            res += ("\n");
        }
        res += ("\n");

        con.close();
        return res;
    }

    public String q4(int nbArticle) throws SQLException {
        String res="";
        String user = utilisateur;
        String mdp = motdepasse;
        Connection con = DriverManager.getConnection(url, user, mdp);
        int nbAnnotation = nbArticle;

        String req1 = "select distinct\n" +
                "                EMAIL\n" +
                "from ANNOTER A1\n" +
                "where (select count(EMAIL)\n" +
                "       from ANNOTER\n" +
                "       where EMAIL = A1.EMAIL\n" +
                "       group by EMAIL) >= ?";

        PreparedStatement pst1 = con.prepareStatement(req1);
        pst1.setInt(1, nbAnnotation);
        pst1.execute();
        ResultSet rs1 = pst1.getResultSet();
        while (rs1.next()) {
            res += rs1.getString(1) + "\n";
        }
        res += ("\n");
        con.close();
        return res;
    }


    public String q5(String recherche) throws SQLException {
        String res ="";
        String user = utilisateur;
        String mdp = motdepasse;
        Connection con = DriverManager.getConnection(url, user, mdp);

        String email = recherche;
        String req1 = "select AVG(NOTE)\n" +
                "from NOTER\n" +
                "where EMAIL = ?";

        PreparedStatement pst1 = con.prepareStatement(req1);
        pst1.setString(1, email);

        pst1.execute();
        ResultSet rs1 = pst1.getResultSet();

        rs1.next();
        res+=("moy : " + rs1.getFloat(1));

        con.close();
        return res;
    }

    public String q6(String recherche) throws SQLException {
        String res ="";
        String user = utilisateur;
        String mdp = motdepasse;
        Connection con = DriverManager.getConnection(url, user, mdp);

        String labo = "Department of Computer and Information Science University of Pennsylvania";

        String req1 = "select NOMCHERCHEUR, PRENOMCHERCHEUR, CHERCHEUR.EMAIL\n" +
                "from LABORATOIRE\n" +
                "         inner join TRAVAILLER on LABORATOIRE.NOMLABO = TRAVAILLER.NOMLABO\n" +
                "         inner join CHERCHEUR on CHERCHEUR.EMAIL = TRAVAILLER.EMAIL\n" +
                "where LABORATOIRE.NOMLABO = ?";

        String req2 = "select count(distinct ECRIRE.TITRE), count(NOTE), ROUND(avg(NOTE), 2)\n" +
                "from ECRIRE inner join NOTER on ECRIRE.TITRE = NOTER.TITRE\n" +
                "where ECRIRE.EMAIL = ?";

        PreparedStatement pst1 = con.prepareStatement(req1);
        PreparedStatement pst2 = con.prepareStatement(req2);

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

        con.close();
        return res;
    }


    public String q7(String recherche) throws SQLException {
        String res = "";
        String user = utilisateur;
        String mdp = motdepasse;
        Connection con = DriverManager.getConnection(url, user, mdp);

        String titre = recherche;
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

        PreparedStatement pst1 = con.prepareStatement(req1);
        PreparedStatement pst2 = con.prepareStatement(req2);

        pst1.setString(1, titre);
        pst1.setString(2, titre);
        pst2.setString(1, titre);

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
        con.close();
        return res;
    }

    public void creerTrigger(String codeTrigger) throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);

        Statement stt = con.createStatement();

        stt.execute(codeTrigger);
        con.commit();
    }
    public void supprTrigger(String nomTrigger) throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);

        Statement stt = con.createStatement();

        String req1 = "drop trigger "+nomTrigger;

        stt.execute(req1);
        con.commit();
    }

}
