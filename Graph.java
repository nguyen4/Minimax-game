//Author: Tom Nguyen

import java.util.ArrayList;
import java.util.LinkedList;

//undirected graph
public class Graph {

    int vertices;
    ArrayList<LinkedList<Neighbor>> AdjList;

    public Graph(int vertices){
        this.vertices = vertices + 1;
        AdjList = new ArrayList<LinkedList<Neighbor>>();

        for(int i = 0; i < this.vertices; i++){
            AdjList.add(new LinkedList<Neighbor>()); 
        }
    }

    public void addEdge(int u, int v, int player){
        AdjList.get(u).add(new Neighbor(v, player));
        AdjList.get(v).add(new Neighbor(u, player));

        if (triangleFound(u, v, player)){           //FIXME: delete this if statement after done testing
            System.out.println(toString(player));
        }
    }

    public boolean triangleFound(int u, int v, int player){
        
        //if there are less than two neighbors, then there can't be a triangle
        if (AdjList.get(v).size() < 2){
            return false;
        }

        for (int j = 0; j < AdjList.get(v).size(); j++){

            Neighbor w = AdjList.get(v).get(j);
            if (player != w.player) {      //if not same player, skip
                continue;
            }
            if (w.vertex == u || w.vertex == v){  //a triangle must have three distinct points
                continue;
            }
            
            //if w is a neighbor of u and v, then there is a cycle of length 3 where u is the start and end. 
            if (isNeighbor(w, u) && isNeighbor(w, v)){
                return true;
            }
        }
        return false;

    }

    public boolean isNeighbor(Neighbor w, int v){
        int u = w.vertex;
        int p = w.player;
        for (int j = 0; j < AdjList.get(u).size(); j++){
            
            if (AdjList.get(u).get(j).vertex == v && 
                AdjList.get(u).get(j).player == p){
                return true;
            }
        }
        return false;
    }

    public String toString(int player){
        
        String s = new String();
        s += "Player " + Integer.toString(player) + "\n";
        for(int i = 1; i < AdjList.size(); i++){
            int u = i;
            s += Integer.toString(u);
            
            if (AdjList.get(i).isEmpty()){
                s += "\n";
                continue;
            }

            for(int j = 0; j < AdjList.get(i).size(); j++){
                int v = AdjList.get(i).get(j).vertex;
                int p = AdjList.get(i).get(j).player;
                if (p == player){
                    s += "->" + Integer.toString(v);
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