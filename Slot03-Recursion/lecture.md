# Slot 03: Đệ quy (Recursion)

## 🎯 Mục tiêu
- Hiểu được khái niệm và cách hoạt động của đệ quy
- Nắm vững các thành phần cơ bản của giải thuật đệ quy
- Phân biệt được các dạng đệ quy khác nhau
- Biết cách tối ưu hóa đệ quy với đệ quy đuôi
- Áp dụng đệ quy để giải quyết các bài toán thực tế

## 📝 Nội dung

### 1. Khái niệm đệ quy
#### 1.1. Định nghĩa
- Đệ quy là phương pháp giải quyết bài toán bằng cách chia nhỏ thành các bài toán con cùng dạng
- Một hàm đệ quy là hàm tự gọi chính nó với dữ liệu vào nhỏ hơn

#### 1.2. Các thành phần của giải thuật đệ quy
1. **Điều kiện cơ sở (Base Case)**
   - Trường hợp đơn giản nhất có thể giải quyết trực tiếp
   - Điều kiện dừng đệ quy

2. **Công thức đệ quy (Recursive Case)**
   - Công thức tính toán dựa trên các bài toán con
   - Phải đảm bảo tiến dần đến điều kiện cơ sở

### 2. Các dạng đệ quy

#### 2.1. Đệ quy tuyến tính (Linear Recursion)
- Hàm chỉ gọi đệ quy một lần trong thân hàm
```java
public static int factorial(int n) {
    if (n <= 1) return 1;  // Base case
    return n * factorial(n - 1);  // Recursive case
}
```

#### 2.2. Đệ quy nhị phân (Binary Recursion)
- Hàm gọi đệ quy hai lần trong thân hàm
```java
public static int fibonacci(int n) {
    if (n <= 1) return n;  // Base case
    return fibonacci(n - 1) + fibonacci(n - 2);  // Recursive case
}
```

#### 2.3. Đệ quy đuôi (Tail Recursion)
- Lời gọi đệ quy là thao tác cuối cùng của hàm
- Có thể tối ưu thành vòng lặp
```java
public static int factorialTail(int n, int acc) {
    if (n <= 1) return acc;  // Base case
    return factorialTail(n - 1, n * acc);  // Tail recursive call
}
```

### 3. Ứng dụng của đệ quy

#### 3.1. Duyệt cây và đồ thị
```java
public void preorderTraversal(TreeNode node) {
    if (node == null) return;
    System.out.print(node.val + " ");
    preorderTraversal(node.left);
    preorderTraversal(node.right);
}
```

#### 3.2. Chia để trị (Divide and Conquer)
```java
public static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}
```

#### 3.3. Quay lui (Backtracking)
```java
public static void generatePermutations(char[] arr, int pos) {
    if (pos == arr.length) {
        System.out.println(String.valueOf(arr));
        return;
    }
    for (int i = pos; i < arr.length; i++) {
        swap(arr, pos, i);
        generatePermutations(arr, pos + 1);
        swap(arr, pos, i);  // backtrack
    }
}
```

### 4. Tối ưu hóa đệ quy

#### 4.1. Đệ quy có nhớ (Memoization)
```java
Map<Integer, Integer> memo = new HashMap<>();

public int fibonacciMemo(int n) {
    if (memo.containsKey(n)) return memo.get(n);
    if (n <= 1) return n;
    
    int result = fibonacciMemo(n - 1) + fibonacciMemo(n - 2);
    memo.put(n, result);
    return result;
}
```

#### 4.2. Chuyển đệ quy thành vòng lặp
```java
public static int factorialIterative(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}
```

## 💻 Bài tập thực hành

### Bài 1: Tính tổng dãy số
Viết hàm đệ quy tính tổng các số từ 1 đến n.
```java
// Input: n = 5
// Output: 15 (1 + 2 + 3 + 4 + 5)
```

### Bài 2: Tháp Hà Nội
Giải bài toán Tháp Hà Nội với đệ quy:
- Di chuyển n đĩa từ cột nguồn sang cột đích
- Sử dụng cột phụ làm trung gian
- In ra các bước di chuyển

### Bài 3: Tìm đường trong mê cung
Viết chương trình tìm đường đi trong mê cung bằng đệ quy:
- Mê cung biểu diễn bằng ma trận 0-1
- 1 là đường đi, 0 là tường
- Tìm đường từ điểm bắt đầu đến điểm kết thúc

## 📚 Tài liệu tham khảo
- [Recursion in Java](https://www.geeksforgeeks.org/recursion-in-java/)
- [Tail Recursion](https://www.baeldung.com/java-tail-recursion)
- [Recursion vs Iteration](https://www.geeksforgeeks.org/recursion-vs-iteration/)