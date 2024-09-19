import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Des extends JPanel {
    private Map map;
    private Interaction interaction;
    private int status = 0;
    private Timer timer;

    public Des(Map map, JFrame frame) {
        this.map = map;
        interaction = new Interaction(getWidth(), getHeight(), map, this);
        addMouseListener(interaction);
        addMouseMotionListener(interaction);
        frame.addKeyListener(interaction);

        // Initialize the timer with a 100ms delay between each generation
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (status != 0) {
                    map.nextGeneration();
                    repaint();
                }
            }
        });
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        if (status != 0) {
            timer.start();  // Start the simulation
        } else {
            timer.stop();   // Stop the simulation
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        interaction.setHeight(height);
        interaction.setWidth(width);
        int nbw = map.getWidth();
        int nbh = map.getHeight();

        int tcw = width / nbw - 2;
        int tch = height / nbh - 2;

        int padding = 2;

        for (int w = 0; w < nbw; w++) {
            for (int h = 0; h < nbh; h++) {
                if (map.getPixel(w, h)) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                int x = w * (tcw + padding);
                int y = h * (tch + padding);
                g.fillRect(x, y, tcw, tch);
            }
        }
    }

    // This method can be used to start the simulation via an external call
    public void startSimulation() {
        setStatus(1);  // Set status to 1 (running)
    }

    // This method can be used to stop the simulation
    public void stopSimulation() {
        setStatus(0);  // Set status to 0 (stopped)
    }
}
