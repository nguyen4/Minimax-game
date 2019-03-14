//Author: Tom Nguyen

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;

public class Graph {
    
    private int vertices;
    private ArrayList<Vertex> AdjList;
    private ArrayList<Vertex> visited;

    public Graph(int vertices, int[] names){
        this.vertices = vertices;
        this.AdjList = new ArrayList<Vertex>();

        for(int i = 0; i < vertices; i++){
            Vertex vertex = new Vertex(names[i]);
            AdjList.add(vertex);
        }
    }

    Graph(Graph g){
        vertices = g.vertices;
        AdjList = g.AdjList;
    }
    
    /*
    //Uniform cost search
    public void ShortestPath(int start, int destination){
        
        Vertex startVertex = findVertex(start);
        Vertex destVertex = findVertex(destination);
        int count = 0;

        if (startVertex == null && destVertex == null){
            System.out.println("Start or Destination vertex does not exist");
            return;
        }

        //create the fringe priority queue and add the starting vertex with cost of 0
        Path startPath = new Path(startVertex, 0);
        PriorityQueue<Path> fringe = new PriorityQueue<Path>(new PathComparator());
        fringe.add(startPath);

        //initialize visited list
        visited = new ArrayList<Vertex>();
        visited.add(startVertex);
        
        while (!fringe.isEmpty()){
            
            count +=1;

            //if the first item in the fringe has the destination, return that path
            Path currPath = fringe.poll();

            if ((currPath.lastVertex().id == destVertex.id) && currPath != null){
                System.out.println("\n----------------------------\n");
                currPath.printPath();
                System.out.println("Shortest Path:" + currPath + " Total cost: " + currPath.cost);
                return;
            }

            //Look at the last vertex in path
            Vertex currVertex = currPath.lastVertex();
            ArrayList<Edge> neighbors = currVertex.neighbors;

            //find all neighbors of vertex and make new paths
            for (int i = 0; i < neighbors.size(); i++){

                Edge edge = neighbors.get(i);
                Vertex newVertex = findVertex(edge.end);                    //find and return vertex of neighbor

                //if neighbor has not been visited, then add to new path and update visited list
                if(!(isVisited(newVertex))){
                    
                    Path newPath = new Path(currPath.path, currPath.cost);  //clone current path to new path
                    newPath.addToPath(newVertex, edge.weight);              //add vertex and update cost to newpath
                    fringe.add(newPath);                                    //add newPath to Fringe

                }
            }
            
            //mark as visited
            visited.add(currVertex);                                        
        }
        return;
    }
    */
    /*
    public boolean isVisited(Vertex x){
        
        for (Vertex y: this.visited){
            if (x.equals(y)){
                return true;
            }
        }
        return false;
    }
    */

    public Vertex findVertex(int ID){
        
        for (Vertex x: this.AdjList){
            if (x.id == ID){
                return x;
            }
        }
        return null;
    }

    public void addEdge(int v1, int v2, int p){ 

        for (int i = 0; i < AdjList.size(); i++){
            if (v1 == AdjList.get(i).id){
                Edge edge = new Edge(v1, v2, p);
                AdjList.get(i).newEdge(edge);
            }
        }
    }

    public void printGraph(){

        System.out.println( "\nGraph\n----------------------------\n");
        for (int i = 0; i < AdjList.size(); i++){
            AdjList.get(i).printEdges();
        }
    }

    public void printVertices(){
        
        for(int i = 0; i < AdjList.size(); i++){
            System.out.println(AdjList.get(i).id);
        }
    }

    public void printSize(){
        System.out.println(vertices);
    }   
}

class Edge {
        int first;
        int end;
        int player;

        public Edge(int first, int end, int player){
            this.first = first;
            this.end = end;
            this.player = player;
        }
}

class Vertex {
    int id;
    ArrayList<Edge> neighbors = new ArrayList<Edge>();

    public Vertex(int id){
        this.id = id;
    }

    public void newEdge(Edge edge){
        neighbors.add(edge);
    }

    public void printEdges(){
        for (int i = 0; i < neighbors.size(); i++){
            String vertexOutput = "vertex " + id + " is connected to " + neighbors.get(i).end + " by player " + neighbors.get(i).player;
            System.out.println(vertexOutput);
        }
    }

}

class Path {
    ArrayList<Vertex> path;
    int cost;

    public Path(ArrayList<Vertex> path, int cost){
        this.path = new ArrayList<Vertex>(path);
        this.cost = cost;
    }

    public Path(Vertex vertex, int cost){
        path = new ArrayList<Vertex>();
        path.add(vertex);
        this.cost = cost;
    }

    public void printPath(){

        for (int i = 0; i < path.size() - 1; i++){
            Vertex v1 = path.get(i);
            Vertex v2 = path.get(i+1);
            System.out.println( "Vertex " + v1.id + " to vertex " + v2.id + " " +
                                "(Edge player of " + v1.findCost(v2.id) + ")");

        }
    }

    public String toString(){

        String string = new String();
        for (int i = 0; i < path.size(); i++){
            if (path.size() == 1){
                string += "[" + Integer.toString(path.get(i).id) + "]";
            }
            else if (i == 0){
                string += "[" + Integer.toString(path.get(i).id) + ", ";
            }
            else if (i == path.size()-1){
                string += Integer.toString(path.get(i).id) + "]";
            }
            else {
                string += Integer.toString(path.get(i).id) + ", ";
            }
        }
        return string;
    }

    public Vertex lastVertex(){
        return path.get(path.size() - 1);
    }
/*
    public void addToPath(Vertex x, int weight){
        this.path.add(x);
        this.cost += weight;
    }
*/
    //copy constructor
    Path(Path p) {
        this.path = new ArrayList<Vertex>(p.path);
        this.cost = p.cost;
    }
}

class PathComparator implements Comparator<Path> {

    //Override compare() method of Comparator in ascending order
    public int compare(Path p1, Path p2){
        if(p1.cost < p2.cost){
            return -1;
        }
        else if (p1.cost > p2.cost){
            return 1;
        }
        return 0;
    }
}