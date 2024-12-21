import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class Window extends JFrame{
    private Grafo<Node> grafo;
    private int nodeWidth;
    private int nodeHeight;
    public Window(int width, int height, Grafo<Node> grafo) {   
        setSize(width, height);
        this.grafo = grafo;
        nodeWidth = 20;
        nodeHeight = 20;
        grafo.calculateNodePositions(getWidth(), getHeight());
        JButton butao = new JButton("Mudar disposição ");
        butao.setBounds(10, 10, 150, 30);
        butao.addActionListener(e -> {
            grafo.calculateNodePositions(getWidth(), getHeight());
            repaint();
        });
        add(butao);
        addComponentListener(new ComponentAdapter (){
            //component adapter is a class that implements the component listener interface
            //it is used to override only the methods of the interface that we need
            //in this case, we only need the componentResized method
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
    }

    private void drawNodes(Graphics g) {
        int totalNodes = grafo.getVertices().size();

        ArrayList<Node> nodes = grafo.getVertices();
        for (int i = 0; i < totalNodes; i++) {
            Node node = nodes.get(i);
            g.fillOval(node.x - nodeWidth/2, node.y - nodeHeight/2, nodeWidth, nodeHeight); //draw node as a small circle
            g.drawString(String.valueOf(node.valor), node.x - nodeWidth/2, node.y - nodeHeight); //draw node value
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