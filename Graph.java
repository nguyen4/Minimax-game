//Author: Tom Nguyen

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

    int vertices;
    ArrayList<LinkedList<Neighbor>> AdjList;

    public Graph(int vertices){
        this.vertices = vertices;
        AdjList = new ArrayList<LinkedList<Neighbor>>();

        for(int i = 0; i < vertices; i++){
            AdjList.add(new LinkedList<Neighbor>());
        }
    }

    public void addEdge(int u, int v, int player){

        for(int i = 0; i < vertices; i++){
            if (AdjList.get(i).get(0).vertex == u){
                AdjList.get(i).add(new Neighbor(v, player));
            }
        }
    }

    public String toString(int player){
        
        String s = new String();
        for(int i = 0; i < AdjList.get(0).size(); i++){
            
            int u = AdjList.get(i).get(0).vertex;
            s += Integer.toString(u) + "->";
            
            for(int j = 0; j < AdjList.size(); j++){
                int p = AdjList.get(i).get(j).player;
                int v = AdjList.get(i).get(j).vertex;
                if ( p == player){
                    s += Integer.toString(v) + "->";
                }
            }
            s += "\n";
        }
        return s;
    }

    static class Neighbor {
        int vertex;
        int player;

        public Neighbor(int vertex, int player){
            this.vertex = vertex;
            this.player = player;
        }
    }
}   