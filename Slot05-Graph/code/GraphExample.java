/**
 * Minh họa cài đặt và các thuật toán trên đồ thị trong Java
 */
import java.util.*;

public class GraphExample {
    // 1. Đồ thị biểu diễn bằng ma trận kề
    static class MatrixGraph {
        private int V;              // Số đỉnh
        private int[][] adjMatrix;  // Ma trận kề
        
        public MatrixGraph(int v) {
            V = v;
            adjMatrix = new int[V][V];
        }
        
        // Thêm cạnh vào đồ thị vô hướng
        public void addEdge(int v1, int v2) {
            adjMatrix[v1][v2] = 1;
            adjMatrix[v2][v1] = 1;
        }
        
        // Duyệt BFS sử dụng ma trận kề
        public void BFS(int start) {
            boolean[] visited = new boolean[V];
            Queue<Integer> queue = new LinkedList<>();
            
            visited[start] = true;
            queue.add(start);
            System.out.print("BFS từ đỉnh " + start + ": ");
            
            while (!queue.isEmpty()) {
                int v = queue.poll();
                System.out.print(v + " ");
                
                for (int i = 0; i < V; i++) {
                    if (adjMatrix[v][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
            System.out.println();
        }
        
        // Duyệt DFS sử dụng ma trận kề
        public void DFS(int start) {
            boolean[] visited = new boolean[V];
            System.out.print("DFS từ đỉnh " + start + ": ");
            DFSUtil(start, visited);
            System.out.println();
        }
        
        private void DFSUtil(int v, boolean[] visited) {
            visited[v] = true;
            System.out.print(v + " ");
            
            for (int i = 0; i < V; i++) {
                if (adjMatrix[v][i] == 1 && !visited[i]) {
                    DFSUtil(i, visited);
                }
            }
        }
    }
    
    // 2. Đồ thị biểu diễn bằng danh sách kề
    static class ListGraph {
        private int V;
        private ArrayList<ArrayList<Integer>> adjList;
        
        public ListGraph(int v) {
            V = v;
            adjList = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }
        
        // Thêm cạnh vào đồ thị vô hướng
        public void addEdge(int v1, int v2) {
            adjList.get(v1).add(v2);
            adjList.get(v2).add(v1);
        }
        
        // Duyệt BFS sử dụng danh sách kề
        public void BFS(int start) {
            boolean[] visited = new boolean[V];
            Queue<Integer> queue = new LinkedList<>();
            
            visited[start] = true;
            queue.add(start);
            System.out.print("BFS từ đỉnh " + start + ": ");
            
            while (!queue.isEmpty()) {
                int v = queue.poll();
                System.out.print(v + " ");
                
                for (int n : adjList.get(v)) {
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
            System.out.println();
        }
        
        // Duyệt DFS sử dụng danh sách kề
        public void DFS(int start) {
            boolean[] visited = new boolean[V];
            System.out.print("DFS từ đỉnh " + start + ": ");
            DFSUtil(start, visited);
            System.out.println();
        }
        
        private void DFSUtil(int v, boolean[] visited) {
            visited[v] = true;
            System.out.print(v + " ");
            
            for (int n : adjList.get(v)) {
                if (!visited[n]) {
                    DFSUtil(n, visited);
                }
            }
        }
        
        // Kiểm tra tính liên thông của đồ thị
        public boolean isConnected() {
            boolean[] visited = new boolean[V];
            DFSUtil(0, visited);
            
            for (boolean v : visited) {
                if (!v) return false;
            }
            return true;
        }
        
        // Đếm số thành phần liên thông
        public int countComponents() {
            boolean[] visited = new boolean[V];
            int count = 0;
            
            for (int v = 0; v < V; v++) {
                if (!visited[v]) {
                    DFSUtil(v, visited);
                    count++;
                }
            }
            
            return count;
        }
    }
    
    public static void main(String[] args) {
        // 1. Thử nghiệm với ma trận kề
        System.out.println("1. Đồ thị biểu diễn bằng ma trận kề:");
        MatrixGraph matrixGraph = new MatrixGraph(6);
        matrixGraph.addEdge(0, 1);
        matrixGraph.addEdge(0, 2);
        matrixGraph.addEdge(1, 3);
        matrixGraph.addEdge(2, 3);
        matrixGraph.addEdge(3, 4);
        matrixGraph.addEdge(4, 5);
        
        matrixGraph.BFS(0);
        matrixGraph.DFS(0);
        
        // 2. Thử nghiệm với danh sách kề
        System.out.println("\n2. Đồ thị biểu diễn bằng danh sách kề:");
        ListGraph listGraph = new ListGraph(6);
        listGraph.addEdge(0, 1);
        listGraph.addEdge(0, 2);
        listGraph.addEdge(1, 3);
        listGraph.addEdge(2, 3);
        listGraph.addEdge(3, 4);
        listGraph.addEdge(4, 5);
        
        listGraph.BFS(0);
        listGraph.DFS(0);
        
        // 3. Kiểm tra tính liên thông
        System.out.println("\n3. Kiểm tra tính chất của đồ thị:");
        System.out.println("Đồ thị có liên thông? " + listGraph.isConnected());
        System.out.println("Số thành phần liên thông: " + listGraph.countComponents());
    }
}