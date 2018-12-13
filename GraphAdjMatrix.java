package OptionalAssignment;

public class GraphAdjMatrix implements Graph {

    int vertices;
    int edges;
    int adjMatrix[][];
//    int newAdjMatrix[][];

    /*
     *  weight - the weight for the edge of the MST
     *  parent -
     */
    class Results{
        int weight;
        int parent;
    }

    class Graph{

        int vertices;
        int newAdjMatrix[][];
        int weight;

        Graph(int vertices, int weight){

            this.weight = weight;
            this.vertices = vertices;
            newAdjMatrix = new int[vertices][vertices];
            for(int i=0; i<vertices; i++)
                for(int k=0; k<vertices; k++)
                    newAdjMatrix[i][k] = weight;
        }
    }

    /**
     * Constructor for a Matrix in creation of the Minimum Spanning Tree
     *
     * @param vertices the total number of vertices in the tree
     */
    public GraphAdjMatrix(int vertices){
        if(vertices<0)
            throw new IllegalArgumentException();
        Graph g = new Graph(vertices, edges);
        this.vertices = vertices;
        this.edges = 0;
        adjMatrix = new int[vertices][vertices];
    }

    @Override
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, 0);
    }

    @Override
    public void topologicalSort() { }

    /**
     * Add an edge to the graph with an emphasis on weight of connection of two vertices
     *
     * @param v1 one of the vertices in connection
     * @param v2 the other vertex in connection
     * @param weight the value of strength of the edge for this connection
     */
    @Override
    public void addEdge(int v1, int v2, int weight) {
        try{

            adjMatrix[v1][v2] = weight;
            adjMatrix[v2][v1] = weight;
            edges++;

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("The vertices do not exist.");
        }


    }

    /**
     * Get the edge between two vertices
     *
     * @param v1 the first vertex in connection to another vertex
     * @param v2 other vertex in connection to original vertex
     * @return the value of weight of edge between two vertices; 0 if otherwise not present
     */
    @Override
    public int getEdge(int v1, int v2){

        try{
            return adjMatrix[v1][v2];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Vertices do not exist.");
            return 0;
        }

    }

    /**
     * Create a Minimum Spanning Tree
     *
     * @return weight of the MST
     */
    @Override
    public int createSpanningTree() {
        boolean[] minSTree = new boolean[vertices];
        Results[] results = new Results[vertices];
        int parent[] = new int[vertices];
        int keys[] = new int[vertices];

        for(int x=0; x<vertices; x++){
            keys[x] = Integer.MAX_VALUE;
            results[x] = new Results();
        }

        keys[0]= 0;
        results[0] = new Results();
        results[0].parent=-1;  // parent vertex has no path; signifies the start of path

        // create Minimum Spanning Tree (MST)
        for(int i=0; i<vertices; i++){
            int vertex = getMinVertex(minSTree, keys);
            minSTree[vertex] = true;

            for(int n=0; n<vertices; n++){
                if(adjMatrix[vertex][n] > 0){
                    if(minSTree[n]==false && (adjMatrix[vertex][n] < keys[n])){
                        keys[n] = adjMatrix[vertex][n];
                        results[n].parent = vertex;
                        results[n].weight = keys[n];

                    }

                }
            }
        }

        int tree_weight = 0;  // the weight of the tree
        for(int y=0;y<vertices; y++)
            tree_weight+=results[y].weight;
        return tree_weight;
    }

    /**
     * Retrieve the minimum value of the vertex
     *
     * @param minSpanTree MST of true and false statements
     * @param keys the values of the
     * @return the minimum vertex
     */
    public int getMinVertex(boolean[] minSpanTree, int[] keys){
        int min_key = Integer.MAX_VALUE;
        int vertex = -1;
        for(int i=0; i<vertices; i++){
            if(minSpanTree[i]==false && min_key>keys[i]){
                min_key = keys[i];
                vertex = i;
            }
        }
        return vertex;
    }


}
