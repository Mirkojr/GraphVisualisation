package src.model;
import java.util.*;

public class Grafo<T extends Node>{

    private Map<T, List<T>> grafo;

    public Grafo(){
        this.grafo = new HashMap<>();
    }

    public Map<T, List<T>> getGrafo(){
        return this.grafo;
    }
    public void addVertice(T vertice){
        grafo.put(vertice, new LinkedList<T>());
    }
    
    public ArrayList<T> getVertices(){
        return new ArrayList<T>(grafo.keySet());
    }

    public void addEdge(T source, T destination, boolean bidirectional){
        //add vertice if not present
        if(!grafo.containsKey(source)){
            addVertice(source);
        }
        if(!grafo.containsKey(destination)){
            addVertice(destination);
        }

        //add the edge
        grafo.get(source).add(destination);
        if(bidirectional){
            grafo.get(destination).add(source);
        }
    }

    public void removeVertice(Node vertice){
        grafo.remove(vertice);
        for(T node: grafo.keySet()){
            grafo.get(node).remove(vertice);
        }
    }
    public void calculateNodePositions(int width, int height) {
        int totalNodes = getVertices().size();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 3;
    
        ArrayList<T> nodes = getVertices();
        for (int i = 0; i < totalNodes; i++) {
            nodes.get(i).calculatePos(i, totalNodes, centerX, centerY, radius);
        }
    }

  
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder(); //eficiency and performance
        for(T vertice: grafo.keySet()){
            builder.append(vertice.toString() + ": ");
            for (T vizinho : grafo.get(vertice)){
                builder.append(vizinho.toString() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}