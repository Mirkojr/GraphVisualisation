package src;

import src.model.Node;

public class Config {
    public static boolean addVertexActive = false;
    public static boolean addEdgeActive = false;
    public static boolean removeVertexActive = false;
    public static boolean removeEdgeActive = false;
    
    public static Node firstNode = null;
    private static Config instance = new Config();
    private Config(){}

    public static Config getInstance(){
        return instance;
    }

    
}
