package src.view;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import src.Config;
import src.model.Grafo;
import src.model.Node;

public class BarraSuperior extends JMenuBar{
    
    JMenu vertexOptions;
    JMenu edgeOptions;

    JMenuItem addEdgeItem;
    JMenuItem addVertexItem;
    JMenuItem removeVertexItem;
    Grafo<Node> grafo;

    public BarraSuperior(Grafo<Node> grafo){
        this.grafo = grafo;

        vertexOptions = new JMenu("V-options");

        edgeOptions = new JMenu("E-options");

        addEdgeItem = new JMenuItem("Adicionar Aresta");
        addVertexItem = new JMenuItem("Adicionar Vertice");
        removeVertexItem = new JMenuItem("Remover Vertice");
        addVertexItem.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        removeVertexItem.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        addVertexItem.setForeground(java.awt.Color.BLUE);
        removeVertexItem.setForeground(java.awt.Color.RED);
        vertexOptions.add(addVertexItem);
        vertexOptions.add(removeVertexItem);
        vertexOptions.setBounds(200, 0, 100, 20);
        add(vertexOptions);

        addEdgeItem.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        addEdgeItem.setForeground(java.awt.Color.BLUE);
        edgeOptions.add(addEdgeItem);
        add(edgeOptions);
        addListeners();
    }

    public void addListeners(){
        addVertexItem.addActionListener(e -> {
            if(!Config.removeVertexActive){
                Config.addVertexActive = true;
            }
        });

        removeVertexItem.addActionListener(e -> {
            Config.removeVertexActive = true;
        });

        addEdgeItem.addActionListener(e -> {
            Config.addEdgeActive = true;
        });
    }
    
}
