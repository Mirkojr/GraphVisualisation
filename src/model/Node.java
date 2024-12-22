package src.model;
import java.awt.Color;
import java.util.Random;

public class Node {
    
    public int x;
    public int y;
    public int valor;
    public int nodeWidth = 30;
    public int nodeHeight = 30;
    public int radius = nodeWidth/2;
    public Color cor;
    
    public Node(int valor){
        this.x = 0;
        this.y = 0;
        this.valor = valor;
    }

    public Node(int valor, Color cor){
        this(valor);
        this.cor = cor;
    }

    public Node(int valor, Color cor, int x, int y){
        this(valor, cor);
        this.x = x;
        this.y = y;
    }


    public void calculatePos(int index, int totalNodes, int centerX, int centerY, int radius) {
        Random random = new Random();
        double angle = random.nextDouble() * 2 * Math.PI * index / totalNodes;
        double distance = Math.sqrt(random.nextDouble()) * radius;
        this.x = centerX + (int) (distance * Math.cos(angle));
        this.y = centerY + (int) (distance * Math.sin(angle));
    }

    @Override 
    public String toString(){
        return "(" + this.valor + ")";
    }
}
