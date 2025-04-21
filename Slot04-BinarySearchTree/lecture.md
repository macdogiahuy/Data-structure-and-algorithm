# Slot 04: Binary Search Tree (BST)

## 🎯 Mục tiêu
- Hiểu được cấu trúc và đặc điểm của Binary Search Tree
- Nắm vững các thao tác cơ bản trên BST
- Thực hiện được các phương pháp duyệt cây
- Phân tích được độ phức tạp của các thao tác
- Áp dụng BST vào các bài toán thực tế

## 📝 Nội dung

### 1. Cây nhị phân tìm kiếm (BST)

#### 1.1. Định nghĩa
- Là cây nhị phân với các đặc điểm:
  - Mọi node con bên trái có giá trị nhỏ hơn node cha
  - Mọi node con bên phải có giá trị lớn hơn node cha
  - Các cây con trái và phải đều là BST

#### 1.2. Cấu trúc node
```java
class Node {
    int data;       // Giá trị của node
    Node left;      // Con trỏ đến node con trái
    Node right;     // Con trỏ đến node con phải
    
    Node(int data) {
        this.data = data;
        left = right = null;
    }
}
```

### 2. Các thao tác cơ bản

#### 2.1. Thêm node mới
```java
public void insert(int key) {
    root = insertRec(root, key);
}

private Node insertRec(Node root, int key) {
    if (root == null) {
        return new Node(key);
    }
    
    if (key < root.data)
        root.left = insertRec(root.left, key);
    else if (key > root.data)
        root.right = insertRec(root.right, key);
    
    return root;
}
```

#### 2.2. Tìm kiếm node
```java
public Node search(int key) {
    return searchRec(root, key);
}

private Node searchRec(Node root, int key) {
    if (root == null || root.data == key)
        return root;
    
    if (key < root.data)
        return searchRec(root.left, key);
    
    return searchRec(root.right, key);
}
```

#### 2.3. Xóa node
```java
private Node deleteRec(Node root, int key) {
    if (root == null) return null;
    
    if (key < root.data)
        root.left = deleteRec(root.left, key);
    else if (key > root.data)
        root.right = deleteRec(root.right, key);
    else {
        // Node với một hoặc không có con
        if (root.left == null)
            return root.right;
        else if (root.right == null)
            return root.left;
            
        // Node có hai con
        root.data = minValue(root.right);
        root.right = deleteRec(root.right, root.data);
    }
    return root;
}
```

### 3. Các phương pháp duyệt cây

#### 3.1. Duyệt theo thứ tự trước (Pre-order)
- Thứ tự: Node hiện tại → Con trái → Con phải
```java
public void preorder(Node node) {
    if (node == null) return;
    
    System.out.print(node.data + " ");
    preorder(node.left);
    preorder(node.right);
}
```

#### 3.2. Duyệt theo thứ tự giữa (In-order)
- Thứ tự: Con trái → Node hiện tại → Con phải
- Cho kết quả các node theo thứ tự tăng dần
```java
public void inorder(Node node) {
    if (node == null) return;
    
    inorder(node.left);
    System.out.print(node.data + " ");
    inorder(node.right);
}
```

#### 3.3. Duyệt theo thứ tự sau (Post-order)
- Thứ tự: Con trái → Con phải → Node hiện tại
```java
public void postorder(Node node) {
    if (node == null) return;
    
    postorder(node.left);
    postorder(node.right);
    System.out.print(node.data + " ");
}
```

### 4. Phân tích độ phức tạp
| Thao tác | Trường hợp trung bình | Trường hợp xấu nhất |
|----------|----------------------|-------------------|
| Tìm kiếm | O(log n) | O(n) |
| Thêm | O(log n) | O(n) |
| Xóa | O(log n) | O(n) |

### 5. Ứng dụng thực tế
- Quản lý tập tin trong hệ điều hành
- Cài đặt từ điển và bộ lọc
- Tìm kiếm trong cơ sở dữ liệu
- Lập lịch và quản lý tài nguyên

## 💻 Bài tập thực hành

### Bài 1: Kiểm tra BST
Viết hàm kiểm tra một cây nhị phân có phải là BST hay không.
```java
public boolean isBST(Node root) {
    return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
}
```

### Bài 2: Tìm k node nhỏ nhất
Viết hàm tìm k node có giá trị nhỏ nhất trong BST.
```
Input: BST = [5,3,7,2,4,6,8], k = 3
Output: 4
```

### Bài 3: Tìm đường đi
Viết hàm tìm đường đi từ root đến một node trong BST.
```
Input: BST = [5,3,7,2,4], target = 4
Output: [5,3,4]
```

## 📚 Tài liệu tham khảo
- [Binary Search Tree - GeeksforGeeks](https://www.geeksforgeeks.org/binary-search-tree-data-structure/)
- [BST Implementation in Java](https://www.baeldung.com/java-binary-tree)
- [Tree Traversal Methods](https://www.programiz.com/dsa/tree-traversal)