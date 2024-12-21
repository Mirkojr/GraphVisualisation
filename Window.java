//Purpose: Class that creates the window for the graph
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class Window extends JFrame{
    private Grafo<Node> grafo;

    private AnimatedButton updateGraphButton;
    private JLabel tamanhoButao;

    public Window(int width, int height, Grafo<Node> grafo) {   
        setSize(width, height);
        this.grafo = grafo;
        
        grafo.calculateNodePositions(getWidth(), getHeight());
    
        updateGraphButton = new AnimatedButton("Mudar disposicao");
        updateGraphButton.addActionListener(e -> {
            grafo.calculateNodePositions(getWidth(), getHeight());
            repaint();
        });
        add(updateGraphButton);


        tamanhoButao = new JLabel("Tamanho do butao");
        tamanhoButao.setBounds(10, 50, 300, 30);
        updateGraphButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                tamanhoButao.setText("Tamanho do butao: " + updateGraphButton.getWidth() + "x" + updateGraphButton.getHeight());
            }
        });
        add(tamanhoButao);

        addComponentListener(new ComponentAdapter (){
            @Override
            public void componentResized(ComponentEvent e){
                grafo.calculateNodePositions(getWidth(), getHeight());
                repaint();
            }
        });
    
        initialize();
    }

    private void initialize() {
        setLayout(null);
        setTitle("Graph Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setVisible(true);
        addMouseListener(new MouseListenerGraph(grafo));
    }

    private void drawNodes(Graphics g) {
        int totalNodes = grafo.getVertices().size();

        ArrayList<Node> nodes = grafo.getVertices();
        for (int i = 0; i < totalNodes; i++) {
            Node node = nodes.get(i);
            g.fillOval(node.x - node.nodeWidth/2, node.y - node.nodeHeight/2, node.nodeWidth, node.nodeHeight); //draw node as a small circle
            g.drawString(String.valueOf(node.valor), node.x - node.nodeWidth/2, node.y - node.nodeHeight); //draw node value
        }
    }

    private void drawEdges(Graphics g){
        ArrayList<Node> nodes = grafo.getVertices();
        for(Node node: nodes){
            for(Node vizinho: grafo.getGrafo().get(node)){
                g.drawLine(node.x, node.y, vizinho.x, vizinho.y);
            }
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        drawNodes(g);
        g.setColor(java.awt.Color.RED);
        drawEdges(g);
    }

    
}