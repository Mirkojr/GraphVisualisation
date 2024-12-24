package src.controller;

import src.view.*;
import src.model.*;

public class MainController {
    
    private Grafo<Node> grafo;
    private Window window;
    private GraphPanel graphPanel;

    public MainController(Window window, Grafo<Node> grafo, GraphPanel graphPanel) {
        this.grafo = new Grafo<>();
        this.window = window;
        this.graphPanel = graphPanel;
        window.setGraph(grafo);
        
    }

    public void run() {
        window.initialize();
    }
}
