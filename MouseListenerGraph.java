import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListenerGraph extends MouseAdapter{
    
    Grafo<Node> grafo;
    MouseListenerGraph(Grafo<Node> grafo){
        super();
        this.grafo = grafo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Node node = isNodeClicked(x, y);
        if (node != null) {
            System.out.println("Node clicked at: (" + x + ", " + y + ")");
        }
        else{
            System.out.println("Node not clicked at: (" + x + ", " + y + ")");
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
