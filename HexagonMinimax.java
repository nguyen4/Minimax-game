public class HexagonMinimax {
    
    public static void main(String [] args){
    
        //player moves
        int A = 1;
        int B = 2;
        int C = 3;
        int D = 4;
        int E = 5;
        int F = 6;
        Graph Hexagon = new Graph(6);

        //player one
        Hexagon.addEdge(1, 2, 1);

        Hexagon.addEdge(2, 3, 2);

        Hexagon.addEdge(2, 6, 1);

        Hexagon.addEdge(3, 6, 2);

        Hexagon.addEdge(1, 6, 1);
    }
}