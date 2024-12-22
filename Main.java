import java.awt.Color;

import src.model.Grafo;
import src.model.Node;
import src.view.Window;

public class Main{

    public static void main(String[] args){
        Grafo<Node> grafo = new Grafo<>();
        System.out.println("Inicializando o programa...");
        
        // Adiciona vértices e arestas
        Node node0 = new Node(0, Color.RED);
        Node node1 = new Node(1, Color.BLACK);
        Node node2 = new Node(2, Color.BLUE);
        Node node3 = new Node(3, Color.GREEN);
        Node node4 = new Node(4, Color.YELLOW);
        Node node5 = new Node(5, Color.ORANGE);
        Node node6 = new Node(6, Color.PINK);
        Node node7 = new Node(7, Color.MAGENTA);
        Node node8 = new Node(8, Color.CYAN);
        Node node9 = new Node(9, Color.GRAY);
        grafo.addVertice(node0);
        grafo.addVertice(node1);
        grafo.addVertice(node2);
        grafo.addVertice(node3);
        grafo.addVertice(node4);
        grafo.addVertice(node5);
        grafo.addVertice(node6);
        grafo.addVertice(node7);
        grafo.addVertice(node8);
        grafo.addVertice(node9);
        grafo.addEdge(node0, node1, true);
        grafo.addEdge(node1, node2, true);
        grafo.addEdge(node2, node3, true);
        grafo.addEdge(node3, node4, true);
        grafo.addEdge(node4, node5, true);
        grafo.addEdge(node5, node6, true);
        grafo.addEdge(node6, node7, true);
        grafo.addEdge(node7, node8, true);
        // Imprime o grafo
        @SuppressWarnings("unused")
        Window window = new Window(800, 600, grafo);
        System.out.println(grafo);
    }
}