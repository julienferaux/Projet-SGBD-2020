import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

public class CloseConOnExit implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            SQLControleur.con.close();
            System.out.println("close");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
