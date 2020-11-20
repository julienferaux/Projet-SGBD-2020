import java.sql.*;

public class SQLControleur {

    private  String url = "jdbc:oracle:thin:@charlemagne.iutnc.univ-lorraine.fr:1521:infodb";

    public void q1() throws SQLException {
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
}
