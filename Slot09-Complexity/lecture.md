# Slot 09: Độ phức tạp thuật toán (Algorithm Complexity)

## 🎯 Mục tiêu
- Hiểu được khái niệm độ phức tạp thuật toán
- Nắm vững cách phân tích độ phức tạp thời gian và không gian
- Phân biệt được các loại độ phức tạp phổ biến
- Áp dụng được kiến thức để đánh giá và tối ưu thuật toán
- So sánh được hiệu quả của các thuật toán khác nhau

## 📝 Nội dung

### 1. Khái niệm độ phức tạp thuật toán

#### 1.1. Định nghĩa
- Độ phức tạp thời gian (Time Complexity)
  * Số lượng phép toán cơ bản cần thực hiện
  * Đánh giá tốc độ thực thi của thuật toán
  
- Độ phức tạp không gian (Space Complexity)
  * Lượng bộ nhớ cần sử dụng
  * Bao gồm bộ nhớ tạm thời và dữ liệu đầu vào

#### 1.2. Kí hiệu Big-O
- Biểu diễn cận trên của độ phức tạp
- Thể hiện tốc độ tăng trưởng tối đa
- Bỏ qua hằng số và thành phần có bậc thấp

### 2. Các loại độ phức tạp phổ biến

#### 2.1. O(1) - Constant Time
```java
public static int getFirst(int[] arr) {
    return arr[0];  // Luôn thực hiện 1 phép toán
}
```

#### 2.2. O(log n) - Logarithmic Time
```java
public static int binarySearch(int[] arr, int key) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == key) return mid;
        if (arr[mid] < key) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

#### 2.3. O(n) - Linear Time
```java
public static int sum(int[] arr) {
    int total = 0;
    for (int num : arr) {  // n phép toán
        total += num;
    }
    return total;
}
```

#### 2.4. O(n log n) - Linearithmic Time
```java
public static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);      // T(n/2)
        mergeSort(arr, mid + 1, right); // T(n/2)
        merge(arr, left, mid, right);   // O(n)
    }
}
```

#### 2.5. O(n²) - Quadratic Time
```java
public static void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // swap
            }
        }
    }
}
```

#### 2.6. O(2ⁿ) - Exponential Time
```java
public static int fibonacci(int n) {
    if (n <= 1) return n;
    return fibonacci(n-1) + fibonacci(n-2);
}
```

### 3. So sánh các độ phức tạp

| Độ phức tạp | n=10 | n=100 | n=1000 |
|-------------|------|--------|---------|
| O(1) | 1 | 1 | 1 |
| O(log n) | 3.3 | 6.6 | 10 |
| O(n) | 10 | 100 | 1000 |
| O(n log n) | 33 | 664 | 9,966 |
| O(n²) | 100 | 10,000 | 1,000,000 |
| O(2ⁿ) | 1,024 | 2¹⁰⁰ | 2¹⁰⁰⁰ |

### 4. Phân tích độ phức tạp

#### 4.1. Quy tắc cơ bản
1. Phép gán, phép tính: O(1)
2. Vòng lặp: Nhân với số lần lặp
3. Câu lệnh điều kiện: Max của các nhánh
4. Hàm đệ quy: T(n) = aT(n/b) + f(n)

#### 4.2. Ví dụ phân tích
```java
void example(int n) {
    int sum = 0;                    // O(1)
    for (int i = 0; i < n; i++) {  // n lần
        for (int j = i; j < n; j++) {  // (n-i) lần
            sum += i * j;           // O(1)
        }
    }
}
// Độ phức tạp: O(n²)
```

### 5. Tối ưu hóa thuật toán

#### 5.1. Các kỹ thuật tối ưu
1. Sử dụng cấu trúc dữ liệu phù hợp
2. Tránh tính toán lặp lại (memoization)
3. Sử dụng thuật toán hiệu quả hơn
4. Trade-off giữa thời gian và không gian

#### 5.2. Ví dụ tối ưu Fibonacci
```java
// Cách 1: Đệ quy - O(2ⁿ)
int fib1(int n) {
    if (n <= 1) return n;
    return fib1(n-1) + fib1(n-2);
}

// Cách 2: Dynamic Programming - O(n)
int fib2(int n) {
    int[] dp = new int[n+1];
    dp[0] = 0; dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
```

## 💻 Bài tập thực hành

### Bài 1: Phân tích độ phức tạp
Viết và phân tích độ phức tạp của hàm tìm số lớn thứ k trong mảng.
```java
// Sử dụng QuickSelect: O(n) trung bình, O(n²) xấu nhất
int findKthLargest(int[] nums, int k);
```

### Bài 2: So sánh hiệu năng
Cài đặt và so sánh thời gian thực thi của các thuật toán sắp xếp với các kích thước đầu vào khác nhau.

### Bài 3: Tối ưu thuật toán
Tối ưu thuật toán tìm tổng các cặp số có hiệu bằng k trong mảng.

## 📚 Tài liệu tham khảo
- [Big-O Cheat Sheet](https://www.bigocheatsheet.com/)
- [Algorithm Analysis](https://www.geeksforgeeks.org/analysis-of-algorithms-set-1-asymptotic-analysis/)
- [Master Theorem](https://www.programiz.com/dsa/master-theorem)