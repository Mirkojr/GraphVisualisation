package src;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GraphPanel extends JPanel {
    private Grafo<Node> grafo;

    private BufferedImage buffer;

    public GraphPanel(Grafo<Node> grafo) {
        this.grafo = grafo;
        setBackground(Color.WHITE);
        MouseListenerGraph mouseListener = new MouseListenerGraph(this, grafo);
    }


    private void drawNodes(Graphics2D g2) {
        ArrayList<Node> nodes = grafo.getVertices();
        for (Node node : nodes) {
            g2.setColor(node.cor); 
            g2.fillOval(node.x - node.nodeWidth / 2, node.y - node.nodeHeight / 2, node.nodeWidth, node.nodeHeight); 
            g2.setColor(Color.BLACK);
            g2.drawString(String.valueOf(node.valor), node.x - node.nodeWidth / 2, node.y - node.nodeHeight / 2); 
        }
    }

    private void drawEdges(Graphics2D g2) {
        ArrayList<Node> nodes = grafo.getVertices();
        g2.setColor(Color.BLUE); 
        for (Node node : nodes) {
            for (Node vizinho : grafo.getGrafo().get(node)) {
                g2.drawLine(node.x, node.y, vizinho.x, vizinho.y);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        drawEdges(g2); 
        drawNodes(g2); 
        g.drawImage(buffer, 0, 0, null);
        g2.dispose();
    }
}