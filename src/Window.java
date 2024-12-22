package src;
import javax.swing.*;
import java.awt.event.*;


public class Window extends JFrame {

    private GraphPanel graphPanel;
    private AnimatedButton updateGraphButton;

    public Window(int width, int height, Grafo<Node> grafo) {
        setSize(width, height);


        grafo.calculateNodePositions(getWidth(), getHeight());

        graphPanel = new GraphPanel(grafo);
        graphPanel.setBounds(0, 0, width, height);
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

        initialize();
    }

    private void initialize() {
        setLayout(null);
        setTitle("Graph Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}