import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListenerGraph extends MouseAdapter{
    
    private Grafo<Node> grafo;
    public Node nodeToMove;
    public boolean dragging = false;

    MouseListenerGraph(Grafo<Node> grafo){
        super();
        this.grafo = grafo;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Node node = isNodeClicked(x, y);
        if (node != null) {
            nodeToMove = node;
            dragging = true;
        }
        else{
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(nodeToMove != null){
            nodeToMove.x = x;
            nodeToMove.y = y;
            nodeToMove = null;
            dragging = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        if(dragging){
            nodeToMove.x = e.getX();
            nodeToMove.y = e.getY();
        }
    }

    private Node isNodeClicked(int x, int y) {
        for (Node node : grafo.getVertices()) {
            int nodeX = node.x;
            int nodeY = node.y;
            int nodeRadius = node.radius; 

            if (Math.pow(x - nodeX, 2) + Math.pow(y - nodeY, 2) <= Math.pow(nodeRadius, 2)) {
            return node;
            }
        }
        return null;
    }
    
}
