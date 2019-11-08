import java.util.Stack;

public class Solution {
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        Graph graph = new Graph(cities, n);
        int lib = graph.count_trees() * c_lib;
        int roads = graph.min_amount_of_nodes_to_connect() * c_road;
        //return the minimum between making a library in every city or making in one city and joining roads
        return Long.min(lib + roads, (c_lib * n));
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        int c_lib = 6;
        int c_road = 1;
        matrix[0][1] = 1;
        matrix[1][0] = 1;
        matrix[2][0] = 1;
        matrix[0][2] = 1;
        matrix[3][0] = 1;
        matrix[0][3] = 1;
        System.out.println(roadsAndLibraries(5, c_lib, c_road, matrix));
    }

    //given a possible forest -- two none combined trees
    //find edges that dont lead to a cycle
    //run dfs on every node to identify amount of trees -- in count trees function
    //multiply amount of different trees by cost of lib
    //multiple amount of edges by cost of road
    //add two products to get result
    static class Graph {
        int V;
        int[][] adjMatrix;
        //boolean array to monitor if a node is part of another tree
        boolean[] is_Parent;
        int[] arr;
        //constructor
        Graph(int[][] matrix, int vertex) {
            this.adjMatrix = matrix;
            this.V = vertex;
            this.is_Parent = new boolean[V];
            this.arr = new int[V];
            for (int i = 0; i < V; i++) {
                arr[i] = -1;
            }
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
                        stack.push(adjMatrix[i][start]);
                        visited[i] = true;
                        i = 0;
                    }
                }
            }
        }

        //check : https://www.geeksforgeeks.org/count-number-trees-forest/ for more info.
        int count_trees() {
            //create array of visited
            boolean[] visited = new boolean[V];
            int result = 0;
            //For each vertex(goes from 1 till V) run dfs on it
            //dfs will visit every node connected to the starting point and mark its visited to true
            //if a node is not visited after the dfs has been run, then its in another tree in the forest
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    is_Parent[i] = true;
                    DFS_util(visited, i);
                    result++;
                }
            }
            return result;
        }

        int min_amount_of_nodes_to_connect() {
            int count = 0;
            for (int i = 0; i < adjMatrix.length; i++) {
                for (int j = 0; j < adjMatrix[i].length; j++) {
                    if (arr[j] == -1 && !is_Parent[j]) {
                        arr[j] = i;
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
