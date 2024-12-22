package src.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.Config;
import src.model.Grafo;
import src.model.Node;

public class MouseListenerGraph extends MouseAdapter {

    private Grafo<Node> grafo;
    private Node draggedNode = null;
    private int offsetX, offsetY;
    private JPanel frame;

    private boolean[] valuesConfig = {Config.addVertexActive, Config.addEdgeActive, Config.removeVertexActive};

    public MouseListenerGraph(JPanel frame, Grafo<Node> grafo) {
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

    @Override
    public void mousePressed(MouseEvent e) {
        if (Config.addEdgeActive) {
            handleAddEdge(e);
        } else if (Config.addVertexActive) {
            handleAddVertex(e);
        } else if (Config.removeVertexActive) {
            handleRemoveVertex(e);
        } else {
            handleDragStart(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        draggedNode = null;
        frame.repaint();
    }

    private void handleAddEdge(MouseEvent e) {
        if (Config.firstNode == null) {
            for (Node node : grafo.getVertices()) {
                if (isMouseOverNode(e, node)) {
                    Config.firstNode = node;
                    return;
                }
            }
        } else {
            for (Node node : grafo.getVertices()) {
                if (isMouseOverNode(e, node)) {
                    if (node == Config.firstNode) {
                        showWarning("Same node selected, please select another node");
                        Config.firstNode = null;
                        Config.addEdgeActive = false;
                        return;
                    }
                    grafo.addEdge(Config.firstNode, node, true);
                    Config.firstNode = null;
                    Config.addEdgeActive = false;
                    frame.repaint();
                    return;
                }
            }
        }
    }

    private void handleAddVertex(MouseEvent e) {
        Node newNode = new Node(grafo.getVertices().size(), java.awt.Color.BLACK, e.getX(), e.getY());
        grafo.addVertice(newNode);
        Config.addVertexActive = false;
        frame.repaint();
    }

    private void handleRemoveVertex(MouseEvent e) {
        for (Node node : grafo.getVertices()) {
            if (isMouseOverNode(e, node)) {
                grafo.removeVertice(node);
                Config.removeVertexActive = false;
                frame.repaint();
                return;
            }
        }
    }

    private void handleDragStart(MouseEvent e) {
        for (Node node : grafo.getVertices()) {
            if (isMouseOverNode(e, node)) {
                draggedNode = node;
                offsetX = e.getX() - node.x;
                offsetY = e.getY() - node.y;
                return;
            }
        }
    }

    private boolean isMouseOverNode(MouseEvent e, Node node) {
        int dx = e.getX() - node.x;
        int dy = e.getY() - node.y;
        return dx * dx + dy * dy <= node.radius * node.radius;
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
