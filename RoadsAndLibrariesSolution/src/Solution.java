import java.util.Stack;

public class Solution {

    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {

        return 0;
    }

    //given a possible forest -- two none combined trees
    //kruskal minimum spanning tree
    //run dfs on every node to identify amount of trees -- in count trees function
    //multiply amount of trees by cost of lib
    //multiple amount of edges by cost of road
    //add two products to get result
    class Graph {

        int V;
        int E;
        int[][] adjMatrix;

        //constructor
        Graph(int[][] matrix, int vertex, int edge) {
            this.adjMatrix = matrix;
            this.V = vertex;
            this.E = edge;
        }

        //dfs non recur
        void DFS_util(boolean[] visited, int start) {
            Stack<Integer> stack = new Stack<>();
            //push the first element of the adj matrix into stack
            stack.push(adjMatrix[start][0]);
            //set its visited to true
            visited[start] = true;
            while (!stack.isEmpty()) {
                stack.pop();
                for (int i = 0; i < adjMatrix[start].length; i++) {
                    if (adjMatrix[start][i] == 1 && !visited[i]) {
                        stack.push(adjMatrix[start][i]);
                    }
                }
            }
        }

        int count_trees() {
            //create array of visited
            boolean[] visited = new boolean[V];
            int result = 0;

            //For each vertex(goes from 1 till V) run dfs on it
            //dfs will visit every node connected to the starting point and mark its visited to true
            //if a node is not visited after the dfs has been run, then its in another tree in the forest
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    DFS_util(visited, i);
                    result++;
                }
            }
            return result;
        }

        int minimum_spanning_tree_kruskal() {


            return 0;
        }
    }


}
