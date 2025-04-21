# Slot 13: Ôn tập cuối kỳ và Thực hành tổng hợp

## 🎯 Mục tiêu
- Ôn tập toàn bộ kiến thức đã học
- Thực hành giải quyết các bài toán phức hợp
- Cải thiện kỹ năng phân tích và tối ưu thuật toán
- Chuẩn bị cho kỳ thi cuối kỳ

## 📝 Nội dung

### 1. Tổng quan kiến thức

#### 1.1. Cấu trúc dữ liệu
1. Array và LinkedList
   - Lưu trữ tuyến tính
   - Truy cập ngẫu nhiên vs tuần tự
   - Thêm/xóa đầu, cuối, giữa

2. Stack và Queue
   - LIFO vs FIFO
   - Ứng dụng trong duyệt đồ thị
   - Xử lý biểu thức và scheduling

3. Tree và Binary Search Tree
   - Lưu trữ phân cấp
   - Các phép duyệt
   - Thêm/xóa/tìm kiếm

4. Advanced Trees (AVL, Red-Black)
   - Cơ chế tự cân bằng
   - Rotation và recoloring
   - Trade-offs trong hiệu năng

5. Graph
   - Biểu diễn (ma trận, danh sách)
   - Duyệt (BFS, DFS)
   - Đường đi ngắn nhất

6. Hash Table
   - Hàm băm
   - Xử lý đụng độ
   - Hiệu năng và resizing

#### 1.2. Thuật toán

1. Recursion và Backtracking
   - Chia để trị
   - Memoization
   - State space exploration

2. Sorting
   - Comparison based
   - Non-comparison based
   - Stability và in-place

3. Searching
   - Tuyến tính vs nhị phân
   - Interpolation search
   - Pattern matching

4. Graph Algorithms
   - Dijkstra
   - Prim/Kruskal
   - Topological sort

### 2. Bài tập tổng hợp

#### 2.1. Bài 1: Hệ thống xử lý giao dịch
Xây dựng hệ thống xử lý giao dịch tài chính với các yêu cầu:
```java
class Transaction {
    String id;
    double amount;
    long timestamp;
    String status;
}

interface TransactionSystem {
    void addTransaction(Transaction t);        // Thêm giao dịch mới
    Transaction getTransaction(String id);     // Tìm giao dịch theo ID
    List<Transaction> getLast24Hours();        // Lấy giao dịch 24h gần đây
    double getTotalAmount(long start, long end); // Tổng tiền trong khoảng thời gian
    List<Transaction> getTopK(int k);         // K giao dịch lớn nhất
}
```

Yêu cầu:
- Sử dụng Hash Table để tìm kiếm nhanh theo ID
- Sử dụng Binary Search Tree để tìm kiếm theo thời gian
- Sử dụng Heap để duy trì top K
- Xử lý concurrent access an toàn

#### 2.2. Bài 2: Social Network Analysis
Phân tích mạng xã hội với các chức năng:
```java
interface SocialNetwork {
    void addUser(String userId);              // Thêm người dùng
    void addFriendship(String user1, String user2); // Kết bạn
    List<String> getFriends(String userId);   // Lấy danh sách bạn
    List<String> getMutualFriends(String user1, String user2); // Bạn chung
    int getDistance(String user1, String user2); // Khoảng cách kết nối
    List<String> suggestFriends(String userId); // Gợi ý kết bạn
}
```

Yêu cầu:
- Sử dụng đồ thị để biểu diễn mối quan hệ
- Áp dụng BFS/DFS để tìm đường đi
- Tối ưu bộ nhớ với số lượng user lớn
- Cài đặt thuật toán gợi ý hiệu quả

#### 2.3. Bài 3: File System
Cài đặt hệ thống quản lý file đơn giản:
```java
interface FileSystem {
    void createDirectory(String path);        // Tạo thư mục
    void createFile(String path, byte[] content); // Tạo file
    byte[] readFile(String path);            // Đọc file
    void deleteFile(String path);            // Xóa file/thư mục
    List<String> listDirectory(String path); // Liệt kê nội dung
    void move(String source, String dest);   // Di chuyển file/thư mục
}
```

Yêu cầu:
- Sử dụng cây để biểu diễn cấu trúc thư mục
- Xử lý path hợp lệ và tương đối
- Quản lý bộ nhớ hiệu quả
- Hỗ trợ undo/redo operations

### 3. Hướng dẫn ôn tập

#### 3.1. Phương pháp học
1. Tổng hợp kiến thức
   - Tạo mind map các chủ đề
   - Liên kết các concept
   - Xác định trọng tâm

2. Luyện tập
   - Bắt đầu từ bài đơn giản
   - Tăng dần độ khó
   - Time box cho mỗi bài

3. Review code
   - Đọc lại code đã viết
   - Tối ưu và clean up
   - Thêm comment và documentation

#### 3.2. Chuẩn bị thi
1. Ôn lý thuyết
   - Định nghĩa và khái niệm
   - Độ phức tạp thuật toán
   - Use cases và trade-offs

2. Thực hành
   - Giải nhanh các bài cơ bản
   - Phân tích bài toán phức
   - Tối ưu giải pháp

3. Chiến lược làm bài
   - Đọc kỹ yêu cầu
   - Lập plan giải quyết
   - Kiểm tra edge cases

## 💻 Bài tập ôn tập

### 1. LRU Cache
Cài đặt bộ nhớ đệm với policy Least Recently Used:
```java
class LRUCache<K,V> {
    void put(K key, V value);  // Thêm/cập nhật entry
    V get(K key);             // Lấy giá trị
    void remove(K key);       // Xóa entry
    int size();               // Số lượng entry
}
```

### 2. Expression Parser
Xây dựng parser cho biểu thức toán học:
```java
class ExpressionParser {
    double evaluate(String expr);  // Tính giá trị
    String toPostfix(String expr); // Chuyển sang postfix
    AST buildAST(String expr);     // Xây dựng AST
}
```

### 3. Graph Algorithms
Cài đặt các thuật toán đồ thị nâng cao:
```java
class GraphAlgorithms {
    List<Edge> getMST();          // Minimum Spanning Tree
    Map<Vertex,Integer> SSSP();   // Single Source Shortest Path
    boolean isBipartite();        // Kiểm tra đồ thị hai màu
}
```

## 📚 Tài liệu tham khảo
- [Algorithms, 4th Edition](https://algs4.cs.princeton.edu/home/)
- [LeetCode Problems](https://leetcode.com/problemset/all/)
- [GeeksforGeeks DSA](https://www.geeksforgeeks.org/data-structures/)