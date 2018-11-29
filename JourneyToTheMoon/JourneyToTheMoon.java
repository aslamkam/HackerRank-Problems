import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Graph {
private int V;   // No. of vertices

// Array  of lists for Adjacency List Representation
private LinkedList<Integer> adj[];

// Constructor
Graph(int v) {
    V = v;
    adj = new LinkedList[v];
    for (int i=0; i<v; ++i)
        adj[i] = new LinkedList();
}

//Function to add an edge into the graph
void addEdge(int v, int w){
    adj[v].add(w);  // Add w to v's list.
}

// A function used by DFS
void DFSUtil(int v,boolean visited[]){
    // Mark the current node as visited and print it
    visited[v] = true;
    System.out.print(v+" ");

    // Recur for all the vertices adjacent to this vertex
    Iterator<Integer> i = adj[v].listIterator();
    while (i.hasNext())
    {
        int n = i.next();
        if (!visited[n])
            DFSUtil(n, visited);
    }
}

// The function to do DFS traversal. It uses recursive DFSUtil()
boolean[] DFS(int v) {
    // Mark all the vertices as not visited(set as
    // false by default in java)
    boolean visited[] = new boolean[V];

    // Call the recursive helper function to print DFS traversal
    DFSUtil(v, visited);
    return visited;
}
}

public class JourneyToTheMoon {
    // Complete the journeyToMoon function below.
    static int journeyToMoon(int n, int[][] astronaut) {
        Graph monty = new Graph(n);
        for (int i = 0; i < astronaut.length; n++){
            monty.addEdge(astronaut[i][0], astronaut[i][1]);
            monty.addEdge(astronaut[i][1], astronaut[i][0]);
        }
        List<Integer[]> sets = new ArrayList<Integer[]>();
        boolean[] visited = new boolean[n];
        boolean[] temp;
        int startnode = 0;

        while(startnode != -1){
            startnode = java.util.Arrays.asList(visited).indexOf(false);
            if(startnode != -1){
                temp = monty.DFS(startnode);
                combine(visited, temp);
                sets.add(boolToInt(temp));
            }
        }
        int sum = 0;
        int curLen = 0;
        for (int i = 0; i < sets.size(); i++){
            curLen = sets.get(i).length;
            for (int y = i+1; y < sets.size(); y++){
                sum+=(curLen*sets.get(y).length);
            }
        }
        return sum;
    }

    static void combine(boolean[] visited, boolean[] newInfo){
        for (int i = 0; i < visited.length; i ++){
            visited[i] = visited[i] || newInfo[i];
        }
    }
    static Integer[] boolToInt (boolean[] set){
        List<Integer> convertedSet = new ArrayList<Integer>();
        for (int i = 0; i < set.length; i ++){
            if (set[i]){
                convertedSet.add(i);
            }
        }
        Integer[] listConSet = new Integer[convertedSet.size()];
        for (int i = 0; i < convertedSet.size(); i ++){
            listConSet[i] = convertedSet.get(i);
        }
        return listConSet;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Output.txt"));

        String[] np = scanner.nextLine().split(" ");

        int n = Integer.parseInt(np[0]);

        int p = Integer.parseInt(np[1]);

        int[][] astronaut = new int[p][2];

        for (int i = 0; i < p; i++) {
            String[] astronautRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int astronautItem = Integer.parseInt(astronautRowItems[j]);
                astronaut[i][j] = astronautItem;
            }
        }

        int result = journeyToMoon(n, astronaut);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
