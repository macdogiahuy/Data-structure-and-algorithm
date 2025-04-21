# Slot 05: Đồ thị (Graph)

## 🎯 Mục tiêu
- Hiểu được khái niệm và thành phần cơ bản của đồ thị
- Nắm vững các cách biểu diễn đồ thị
- Thực hiện được các thuật toán duyệt đồ thị
- Áp dụng được các thuật toán tìm đường đi ngắn nhất
- Xây dựng được các ứng dụng thực tế với đồ thị

## 📝 Nội dung

### 1. Khái niệm cơ bản về đồ thị

#### 1.1. Định nghĩa
- Đồ thị G = (V, E) gồm tập đỉnh V và tập cạnh E
- Cạnh là kết nối giữa hai đỉnh
- Đồ thị có thể có hướng hoặc vô hướng

#### 1.2. Các khái niệm quan trọng
- Đỉnh kề (Adjacent vertices)
- Bậc của đỉnh (Degree)
- Đường đi (Path)
- Chu trình (Cycle)
- Đồ thị liên thông (Connected graph)

### 2. Biểu diễn đồ thị

#### 2.1. Ma trận kề (Adjacency Matrix)
```java
class Graph {
    private int V; // Số đỉnh
    private int[][] adjMatrix; // Ma trận kề
    
    public Graph(int v) {
        V = v;
        adjMatrix = new int[V][V];
    }
    
    // Thêm cạnh vào đồ thị vô hướng
    public void addEdge(int v1, int v2) {
        adjMatrix[v1][v2] = 1;
        adjMatrix[v2][v1] = 1;
    }
}
```

#### 2.2. Danh sách kề (Adjacency List)
```java
class Graph {
    private int V;
    private ArrayList<ArrayList<Integer>> adjList;
    
    public Graph(int v) {
        V = v;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int v1, int v2) {
        adjList.get(v1).add(v2);
        adjList.get(v2).add(v1);
    }
}
```

### 3. Các thuật toán duyệt đồ thị

#### 3.1. Duyệt theo chiều rộng (BFS)
```java
public void BFS(int start) {
    boolean[] visited = new boolean[V];
    Queue<Integer> queue = new LinkedList<>();
    
    visited[start] = true;
    queue.add(start);
    
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
}
```

#### 3.2. Duyệt theo chiều sâu (DFS)
```java
public void DFS(int start) {
    boolean[] visited = new boolean[V];
    DFSUtil(start, visited);
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
```

### 4. Thuật toán tìm đường đi ngắn nhất

#### 4.1. Thuật toán Dijkstra
```java
public void dijkstra(int start) {
    int[] distance = new int[V];
    boolean[] visited = new boolean[V];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[start] = 0;
    
    for (int count = 0; count < V - 1; count++) {
        int u = minDistance(distance, visited);
        visited[u] = true;
        
        for (int v = 0; v < V; v++) {
            if (!visited[v] && adjMatrix[u][v] != 0 && 
                distance[u] != Integer.MAX_VALUE &&
                distance[u] + adjMatrix[u][v] < distance[v]) {
                distance[v] = distance[u] + adjMatrix[u][v];
            }
        }
    }
}
```

#### 4.2. Thuật toán Floyd-Warshall
```java
public void floydWarshall() {
    int[][] dist = new int[V][V];
    for (int i = 0; i < V; i++)
        for (int j = 0; j < V; j++)
            dist[i][j] = adjMatrix[i][j];
            
    for (int k = 0; k < V; k++) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][k] != Integer.MAX_VALUE && 
                    dist[k][j] != Integer.MAX_VALUE &&
                    dist[i][k] + dist[k][j] < dist[i][j])
                    dist[i][j] = dist[i][k] + dist[k][j];
            }
        }
    }
}
```

### 5. Ứng dụng thực tế
- Mạng xã hội (Social Networks)
- Hệ thống GPS và bản đồ
- Mạng máy tính
- Lập lịch và tối ưu hóa

## 💻 Bài tập thực hành

### Bài 1: Kiểm tra tính liên thông
Viết chương trình kiểm tra một đồ thị có liên thông hay không sử dụng DFS hoặc BFS.
```java
public boolean isConnected() {
    boolean[] visited = new boolean[V];
    DFSUtil(0, visited);
    
    for (boolean v : visited)
        if (!v) return false;
    return true;
}
```

### Bài 2: Đếm số thành phần liên thông
Viết chương trình đếm số thành phần liên thông trong đồ thị vô hướng.
```
Input: Đồ thị với V đỉnh và E cạnh
Output: Số thành phần liên thông
```

### Bài 3: Tìm đường đi ngắn nhất
Cài đặt thuật toán Dijkstra để tìm đường đi ngắn nhất giữa hai đỉnh trong đồ thị có trọng số.
```
Input: Đồ thị có trọng số, đỉnh nguồn và đích
Output: Độ dài đường đi ngắn nhất và đường đi
```

## 📚 Tài liệu tham khảo
- [Graph Data Structure And Algorithms](https://www.geeksforgeeks.org/graph-data-structure-and-algorithms/)
- [Introduction to Graph Theory](https://www.programiz.com/dsa/graph)
- [Shortest Path Algorithms](https://www.baeldung.com/java-dijkstra)