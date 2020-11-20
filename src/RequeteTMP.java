import java.sql.*;

public class RequeteTMP {
    private static final String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";

    public static void main(String[] args) throws SQLException {
        q1();
    }

    public static void q1() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);
        //recherche par email
        String req1 = "select ARTICLE.TITRE, RESUME, TYPEARTICLE\n" +
                "from ARTICLE inner join ECRIRE on ARTICLE.TITRE = ECRIRE.TITRE\n" +
                "where EMAIL = ?";
        PreparedStatement st1 = con.prepareStatement(req1);

        String email = "lynch@theory.csail.mit.edu";
        st1.setString(1, email);
        st1.execute();
        ResultSet rs1 = st1.getResultSet();

        System.out.println("q1 email : \n");
        while (rs1.next()) {
            System.out.println("titre : " + rs1.getString(1) +
                    "\nresume : " + rs1.getString(2) +
                    "\ntype : " + rs1.getString(3) + "\n");
        }
        System.out.println("\n");


        //recherche par nom+prenom
        String req2 = "select ARTICLE.TITRE, RESUME, TYPEARTICLE\n" +
                "from ARTICLE inner join ECRIRE on ARTICLE.TITRE = ECRIRE.TITRE\n" +
                "    inner join CHERCHEUR on ECRIRE.EMAIL = CHERCHEUR.EMAIL\n" +
                "where NOMCHERCHEUR = ? and PRENOMCHERCHEUR = ?";
        PreparedStatement st2 = con.prepareStatement(req2);

        String nom = "Lynch";
        String prenom = "Nancy";
        st2.setString(1, nom);
        st2.setString(2, prenom);

        st2.execute();
        ResultSet rs2 = st2.getResultSet();

        System.out.println("q1 nom+prenom : \n");
        while (rs2.next()) {
            System.out.println("titre : " + rs2.getString(1) +
                    "\nresume : " + rs2.getString(2) +
                    "\ntype : " + rs2.getString(3) + "\n");
        }
        System.out.println("\n");
        con.close();
    }

    public static void q2() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);

        String req1 = "select NOMCHERCHEUR, PRENOMCHERCHEUR, CHERCHEUR.EMAIL\n" +
                "from ECRIRE inner join CHERCHEUR on ECRIRE.EMAIL = CHERCHEUR.EMAIL\n" +
                "where TITRE = (select TITRE from ECRIRE where EMAIL = ?) and CHERCHEUR.EMAIL != ?";
        PreparedStatement st1 = con.prepareStatement(req1);

        String email = "chris@lri.fr";
        st1.setString(1, email);
        st1.setString(2, email);

        st1.execute();
        ResultSet rs1 = st1.getResultSet();
        System.out.println("co-chercheur de " + email + " : \n");
        while (rs1.next()) {
            System.out.println(rs1.getString(1)
                    + " " + rs1.getString(2)
                    + " " + rs1.getString(3));
        }


        con.close();
    }

    public static void q3() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
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
            System.out.println(chercheur + " : ");
            pst.setString(1, chercheur);
            pst.execute();
            ResultSet rs2 = pst.getResultSet();
            while (rs2.next()) {
                System.out.println("    " + rs2.getString(1));
            }
            System.out.println("\n");
        }
        System.out.println("\n");

        con.close();
    }

    public static void q4() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);
        int nbAnnotation = 3;

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
            System.out.println(rs1.getString(1));
        }
        System.out.println("\n");

        con.close();
    }

    public static void q5() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);

        String email = "lamport@microsoft.com";
        String req1 = "select AVG(NOTE)\n" +
                "from NOTER\n" +
                "where EMAIL = ?";

        PreparedStatement pst1 = con.prepareStatement(req1);
        pst1.setString(1, email);

        pst1.execute();
        ResultSet rs1 = pst1.getResultSet();

        rs1.next();
        System.out.println("moy : " + rs1.getFloat(1));

        con.close();
    }

    public static void q6() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
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

        System.out.println("chercheurs du lab " + labo + " : \n");
        while (rs1.next()) {
            System.out.println("    " + rs1.getString(1) + " " + rs1.getString(2) + " : ");
            pst2.setString(1, rs1.getString(3));
            pst2.execute();
            ResultSet rs2 = pst2.getResultSet();
            rs2.next();
            System.out.println("        nombre d'article : " + rs2.getInt(1)
                    + "  nombre de note recu : " + rs2.getInt(2)
                    + "  moy note : " + rs2.getFloat(3) + "\n");
        }
        System.out.println("\n");

        con.close();
    }

    public static void q7() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);

        String titre = "Adding Structure to Unstructured Data";

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

        boolean res = false;//réponse a la question

        while (rs2.next()){
            if(rs2.getString(1).equals(emailNoteMax)){
                res = true;
                break;
            }
        }

        System.out.println(res);

        con.close();
    }

    public static void q8() throws SQLException {
        String triggerQuestionA = "";
        String triggerQuestionB = "";
    }

}
