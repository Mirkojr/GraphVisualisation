import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Window extends JFrame {
    private Grafo<Node> grafo;
    private Node draggedNode = null;
    private int offsetX, offsetY;
    private BufferedImage buffer;

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

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
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

        // Adicionando o MouseListener para arrastar os nós
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
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
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggedNode != null) {
                    draggedNode.x = e.getX() - offsetX;
                    draggedNode.y = e.getY() - offsetY;
                    repaint();
                }
            }
        });
    }

    private boolean isMouseOverNode(MouseEvent e, Node node) {
        int dx = e.getX() - node.x;
        int dy = e.getY() - node.y;
        return dx * dx + dy * dy <= node.radius * node.radius;
    }

    private void drawNodes(Graphics2D g2) {
        ArrayList<Node> nodes = grafo.getVertices();
        for (Node node : nodes) {
            g2.setColor(Color.RED); // Definindo cor para os nós
            g2.fillOval(node.x - node.nodeWidth / 2, node.y - node.nodeHeight / 2, node.nodeWidth, node.nodeHeight); // Desenha o nó como um círculo
            g2.setColor(Color.BLACK); // Cor do texto (valor do nó)
            g2.drawString(String.valueOf(node.valor), node.x - node.nodeWidth / 2, node.y - node.nodeHeight / 2); // Desenha o valor do nó
        }
    }

    private void drawEdges(Graphics2D g2) {
        ArrayList<Node> nodes = grafo.getVertices();
        g2.setColor(Color.BLUE); // Definir cor das arestas
        for (Node node : nodes) {
            for (Node vizinho : grafo.getGrafo().get(node)) {
                g2.drawLine(node.x, node.y, vizinho.x, vizinho.y); // Desenha as arestas entre os nós
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        Graphics2D g2 = buffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        drawEdges(g2); // Desenha as arestas primeiro
        drawNodes(g2); // Depois desenha os nós
        g.drawImage(buffer, 0, 0, null);
        g2.dispose();
    }
}
