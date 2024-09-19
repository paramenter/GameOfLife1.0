import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class Interaction implements MouseListener, MouseMotionListener, KeyListener {
    private int width, height;
    private Map map;
    private Des des;
    private Set<String> visitedPoints; // Store visited points during dragging

    public Interaction(int width, int height, Map map, Des des) {
        this.width = width;
        this.height = height;
        this.map = map;
        this.des = des;
        this.visitedPoints = new HashSet<>(); // Initialize visited points
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        togglePixel(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Clear visited points at the beginning of a new drag
        visitedPoints.clear();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // You could implement functionality here if needed
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implement if needed
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implement if needed
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        togglePixel(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Implement if needed
    }

    // Utility method to toggle pixel based on mouse event
    private void togglePixel(MouseEvent e) {
        int nbw = map.getMap().length;
        int nbh = map.getMap()[0].length;

        int tcw = width / nbw - 2;
        int tch = height / nbh - 2;
        int padding = 2;

        int x = e.getX() / (tcw + padding);
        int y = e.getY() / (tch + padding);
        String pointKey = x + "," + y;

        if (!visitedPoints.contains(pointKey)) {
            map.setNextPixel(x, y);
            visitedPoints.add(pointKey);  // Mark this point as visited
            des.repaint();  // Repaint after modifying the map
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No action needed here
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle "Enter" key for advancing the generation
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            map.nextGeneration();
            des.repaint();
        }

        // Handle "Space" key for pausing/starting the simulation
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (des.getStatus() == 1) {
                des.setStatus(0);  // Pause the simulation
                des.stopSimulation();
            } else {
                des.setStatus(1);  // Start the simulation
                des.startSimulation();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No action needed here
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
