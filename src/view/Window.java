package src.view;
import javax.swing.*;

import src.model.Grafo;
import src.model.Node;

import java.awt.event.*;


public class Window extends JFrame {

    private GraphPanel graphPanel;
    private AnimatedButton updateGraphButton;
    private Grafo<Node> grafo;

    public Window(int width, int height, GraphPanel graphPanel) {
        setSize(width, height);
        this.graphPanel = graphPanel;
    }
    public void setGraph(Grafo<Node> grafo) {
        this.grafo = grafo;
    }

    public void initialize() {
        grafo.calculateNodePositions(getWidth(), getHeight());

        graphPanel = new GraphPanel(grafo);
        graphPanel.setBounds(0, 0, getWidth(), getHeight());
        graphPanel.setLayout(null);

        updateGraphButton = new AnimatedButton("Mudar disposicao", getWidth()/ 4, getHeight()/15);
        updateGraphButton.addActionListener(e -> {
            grafo.calculateNodePositions(getWidth(), getHeight());
            graphPanel.repaint();
        });

        graphPanel.add(updateGraphButton);
        add(graphPanel);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                grafo.calculateNodePositions(getWidth(), getHeight());
                updateGraphButton.setSize(getWidth() / 4, getHeight() / 10);
                graphPanel.setSize(getWidth(), getHeight());
                graphPanel.repaint();
            }
        });

        BarraSuperior barraSuperior = new BarraSuperior(grafo);
        graphPanel.add(barraSuperior);
        setJMenuBar(barraSuperior);
        setLayout(null);
        setTitle("Graph Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}