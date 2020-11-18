import java.sql.*;

public class RequeteTMP {
    private static final String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";

    public static void main(String[] args) throws SQLException {
        q2();
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
        while (rs1.next()){
            System.out.println("titre : "+rs1.getString(1)+
                    "\nresume : "+rs1.getString(2)+
                    "\ntype : "+rs1.getString(3)+"\n");
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
        while (rs2.next()){
            System.out.println("titre : "+rs2.getString(1)+
                    "\nresume : "+rs2.getString(2)+
                    "\ntype : "+rs2.getString(3)+"\n");
        }
        System.out.println("\n");
        con.close();
    }

    public static void q2() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);


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

        while (rs1.next()){
            String chercheur = rs1.getString(1);
            System.out.println(chercheur+" : ");
            pst.setString(1, chercheur);
            pst.execute();
            ResultSet rs2 = pst.getResultSet();
            while (rs2.next()){
                System.out.println("    "+rs2.getString(1));
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


        con.close();
    }

    public static void q5() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);


        con.close();
    }

    public static void q6() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);


        con.close();
    }

    public static void q7() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);


        con.close();
    }

    public static void q8() throws SQLException {
        String user = "schmit572u";
        String mdp = "kebab1234";
        Connection con = DriverManager.getConnection(url, user, mdp);


        con.close();
    }

}
