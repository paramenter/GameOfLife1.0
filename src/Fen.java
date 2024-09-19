import javax.swing.*;

public class Fen extends JFrame {
    public Fen(Map map) {
        super("Fen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Des(map, this));
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
