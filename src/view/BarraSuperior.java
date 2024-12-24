package src.view;
import java.awt.Color;
import java.awt.Font;

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
    JMenuItem removeEdgeItem;

    Grafo<Node> grafo;

    public BarraSuperior(Grafo<Node> grafo){
        this.grafo = grafo;

        vertexOptions = new JMenu("V-options");

        edgeOptions = new JMenu("E-options");

        addEdgeItem = new JMenuItem("Adicionar Aresta");
        addVertexItem = new JMenuItem("Adicionar Vertice");
        removeVertexItem = new JMenuItem("Remover Vertice");
        removeEdgeItem = new JMenuItem("Remover Aresta");
        
        Font fontePadrao = new Font("Arial", java.awt.Font.BOLD, 12);
        
        addEdgeItem.setForeground(Color.BLUE);
        addEdgeItem.setFont(fontePadrao);

        addVertexItem.setFont(fontePadrao);
        addVertexItem.setForeground(Color.BLUE);

        removeVertexItem.setFont(fontePadrao);
        removeVertexItem.setForeground(Color.RED);
        
        removeEdgeItem.setFont(fontePadrao);
        removeEdgeItem.setForeground(Color.RED);

        vertexOptions.add(addVertexItem);
        vertexOptions.add(removeVertexItem);
        
        edgeOptions.add(addEdgeItem);
        edgeOptions.add(removeEdgeItem);
        
        add(vertexOptions);
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

        removeEdgeItem.addActionListener(e -> {
            Config.removeEdgeActive = true;
        });
    }
    
}
