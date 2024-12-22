package src;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MouseListenerGraph extends MouseAdapter{
    

    private Grafo<Node> grafo;
    private Node draggedNode = null;
    private int offsetX, offsetY;
    private JPanel frame;

    boolean[] valuesConfig = {Config.addVertexActive, Config.addEdgeActive, Config.removeVertexActive};
    
    public MouseListenerGraph(JPanel frame, Grafo<Node> grafo){
        this.grafo = grafo;
        this.frame = frame;
        frame.addMouseListener(this);
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggedNode != null) {
                    draggedNode.x = e.getX() - offsetX;
                    draggedNode.y = e.getY() - offsetY;
                    frame.repaint();
                }
            }
        });

    }
    public void mousePressed(MouseEvent e) {
        if (Config.addEdgeActive){
            if(Config.firstNode == null){
                for (Node node : grafo.getVertices()) {
                    if (isMouseOverNode(e, node)) {
                        Config.firstNode = node;
                        break;
                    }
                }
            }
            else{
                for (Node node : grafo.getVertices()) {
                    if (isMouseOverNode(e, node)) {
                        if( node == Config.firstNode){
                            Config.firstNode = null;
                            Config.addEdgeActive = false;
                            JOptionPane.showMessageDialog(frame, "Same node selected, please select another node", "Warning", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        grafo.addEdge(Config.firstNode, node, true);
                        Config.firstNode = null;
                        Config.addEdgeActive = false;
                        frame.repaint();
                        break;
                    }
                }
            }
        }
        if (Config.addVertexActive){
            grafo.addVertice(new Node(grafo.getVertices().size(), java.awt.Color.BLACK, e.getX(), e.getY()));
            Config.addVertexActive = false;
            frame.repaint();
        }
        if(Config.removeVertexActive){
            for (Node node : grafo.getVertices()) {
                if (isMouseOverNode(e, node)) {
                    grafo.removeVertice(node);
                    Config.removeVertexActive = false;
                    frame.repaint();
                    break;
                }
            }
        }
        for (Node node : grafo.getVertices()) {
            if (isMouseOverNode(e, node)) {
                draggedNode = node;
                offsetX = e.getX() - node.x;
                offsetY = e.getY() - node.y;
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        draggedNode = null;
        frame.repaint();
    }

    private boolean isMouseOverNode(MouseEvent e, Node node) {
        int dx = e.getX() - node.x;
        int dy = e.getY() - node.y;
        return dx * dx + dy * dy <= node.radius * node.radius;
    }
}
