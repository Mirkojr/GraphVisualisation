import java.util.*;

public class Main{

    public static void main(String[] args){
        Grafo<Node> grafo = new Grafo<>();
        System.out.println("Inicializando o programa...");
        
        // Adiciona vértices e arestas
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
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
        Window window = new Window(800, 600, grafo);
        System.out.println(grafo);
    }
}