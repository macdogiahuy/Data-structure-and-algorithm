# Slot 12: Chủ đề nâng cao (AVL, Heap, Red-Black Trees)

## 🎯 Mục tiêu
- Hiểu được nguyên lý và đặc điểm của cây cân bằng AVL
- Nắm vững cấu trúc và các thao tác trên Heap
- Hiểu được cơ chế tự cân bằng của Red-Black Trees
- Phân tích và so sánh ưu nhược điểm của các cấu trúc
- Áp dụng được các cấu trúc nâng cao vào bài toán thực tế

## 📝 Nội dung

### 1. AVL Trees

#### 1.1. Định nghĩa
- Cây nhị phân tìm kiếm tự cân bằng
- Độ cao hai cây con chênh lệch không quá 1
- Cần cân bằng lại sau mỗi thao tác thêm/xóa

#### 1.2. Các thao tác xoay
```java
// Xoay phải
private Node rightRotate(Node y) {
    Node x = y.left;
    Node T2 = x.right;
    
    x.right = y;
    y.left = T2;
    
    y.height = max(height(y.left), height(y.right)) + 1;
    x.height = max(height(x.left), height(x.right)) + 1;
    
    return x;
}

// Xoay trái
private Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;
    
    y.left = x;
    x.right = T2;
    
    x.height = max(height(x.left), height(x.right)) + 1;
    y.height = max(height(y.left), height(y.right)) + 1;
    
    return y;
}
```

#### 1.3. Các trường hợp mất cân bằng
1. Left Left Case (LL)
   - Xoay phải một lần
2. Right Right Case (RR)
   - Xoay trái một lần
3. Left Right Case (LR)
   - Xoay trái con trái
   - Xoay phải node hiện tại
4. Right Left Case (RL)
   - Xoay phải con phải
   - Xoay trái node hiện tại

### 2. Heap

#### 2.1. Định nghĩa
- Cây nhị phân hoàn chỉnh
- Thỏa mãn tính chất heap
  * Max Heap: node cha lớn hơn node con
  * Min Heap: node cha nhỏ hơn node con

#### 2.2. Các thao tác cơ bản
```java
// Thêm phần tử
void insert(int key) {
    heap[size] = key;
    bubbleUp(size++);
}

// Lấy và xóa phần tử root
int extractRoot() {
    int root = heap[0];
    heap[0] = heap[--size];
    bubbleDown(0);
    return root;
}
```

#### 2.3. Ứng dụng
- Priority Queue
- Heap Sort
- Graph algorithms (Dijkstra, Prim)
- Median maintenance

### 3. Red-Black Trees

#### 3.1. Tính chất
1. Mỗi node có màu đỏ hoặc đen
2. Root luôn màu đen
3. Lá (NULL) là node đen
4. Node đỏ không có con đỏ
5. Mọi đường đi từ root đến lá có cùng số node đen

#### 3.2. Thao tác cân bằng
```java
void insert(int key) {
    Node node = bstInsert(key);  // Thêm như BST thông thường
    fixViolation(node);          // Sửa vi phạm tính chất Red-Black
}

void fixViolation(Node node) {
    Node parent = null;
    Node grandParent = null;
    
    while (node != root && node.color == RED && node.parent.color == RED) {
        parent = node.parent;
        grandParent = parent.parent;
        
        // Xử lý các trường hợp vi phạm
        if (parent == grandParent.left) {
            handleLeftParentCase(node, parent, grandParent);
        } else {
            handleRightParentCase(node, parent, grandParent);
        }
    }
    
    root.color = BLACK;
}
```

### 4. So sánh các cấu trúc

| Tiêu chí | AVL Tree | Red-Black Tree | Heap |
|----------|----------|----------------|------|
| Cân bằng | Nghiêm ngặt | Linh hoạt hơn | Không cân bằng |
| Độ cao | O(log n) | O(log n) | O(log n) |
| Tìm kiếm | O(log n) | O(log n) | O(n) |
| Thêm/Xóa | O(log n) | O(log n) | O(log n) |
| Không gian | O(n) | O(n) | O(n) |

### 5. Hướng dẫn cài đặt

#### 5.1. AVL Tree
1. Cài đặt thao tác thêm/xóa cơ bản
2. Tính toán độ cao và hệ số cân bằng
3. Xác định loại mất cân bằng
4. Thực hiện phép xoay phù hợp

#### 5.2. Heap
1. Sử dụng mảng để lưu trữ
2. Cài đặt thao tác thêm (bubbleUp)
3. Cài đặt thao tác xóa (bubbleDown)
4. Xử lý trường hợp resize

#### 5.3. Red-Black Tree
1. Cài đặt thao tác thêm BST
2. Xác định vi phạm tính chất
3. Xoay và đổi màu để sửa vi phạm
4. Đảm bảo tính chất sau mỗi thao tác

## 💻 Bài tập thực hành

### Bài 1: AVL Tree Dictionary
Xây dựng từ điển sử dụng AVL Tree với các chức năng:
- Thêm/xóa từ
- Tìm kiếm từ
- In theo thứ tự từ điển
- Đếm số từ theo prefix

### Bài 2: Top K Elements
Sử dụng Heap để tìm k phần tử lớn nhất trong luồng dữ liệu:
```java
class TopK {
    void add(int num);     // Thêm số mới
    int[] getTopK();       // Lấy k số lớn nhất
    void removeTop();      // Xóa số lớn nhất
}
```

### Bài 3: Interval Tree
Cài đặt Red-Black Tree để quản lý các khoảng thời gian:
```java
class Interval {
    int start, end;
}

class IntervalTree {
    void insert(Interval i);           // Thêm khoảng
    void delete(Interval i);           // Xóa khoảng
    Interval[] findOverlapping(int t); // Tìm các khoảng chứa t
}
```

## 📚 Tài liệu tham khảo
- [AVL Trees](https://www.geeksforgeeks.org/avl-tree-set-1-insertion/)
- [Binary Heap](https://www.programiz.com/dsa/binary-heap)
- [Red-Black Trees](https://www.cs.usfca.edu/~galles/visualization/RedBlack.html)