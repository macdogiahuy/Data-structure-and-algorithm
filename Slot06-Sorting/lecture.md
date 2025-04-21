# Slot 06: Các thuật toán Sắp xếp (Sorting Algorithms)

## 🎯 Mục tiêu
- Hiểu được nguyên lý hoạt động của các thuật toán sắp xếp cơ bản
- Phân tích được độ phức tạp của từng thuật toán
- Cài đặt được các thuật toán sắp xếp bằng Java
- So sánh và lựa chọn được thuật toán phù hợp cho từng bài toán
- Tối ưu hóa được hiệu suất sắp xếp trong các trường hợp thực tế

## 📝 Nội dung

### 1. Giới thiệu chung

#### 1.1. Phân loại thuật toán sắp xếp
1. Theo không gian bộ nhớ:
   - Sắp xếp tại chỗ (In-place): Bubble Sort, Selection Sort, Insertion Sort
   - Sắp xếp không tại chỗ (Out-of-place): Merge Sort

2. Theo tính ổn định:
   - Ổn định (Stable): Bubble Sort, Insertion Sort, Merge Sort
   - Không ổn định (Unstable): Selection Sort, Quick Sort

#### 1.2. Đánh giá thuật toán
- Độ phức tạp thời gian (Time Complexity)
- Độ phức tạp không gian (Space Complexity)
- Tính ổn định (Stability)
- Hiệu suất với dữ liệu thực tế

### 2. Các thuật toán sắp xếp cơ bản

#### 2.1. Bubble Sort
- So sánh và đổi chỗ các cặp phần tử liền kề
- Độ phức tạp: O(n²)
```java
void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n-1; i++) {
        for (int j = 0; j < n-i-1; j++) {
            if (arr[j] > arr[j+1]) {
                // Đổi chỗ arr[j] và arr[j+1]
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
}
```

#### 2.2. Selection Sort
- Tìm phần tử nhỏ nhất đưa về đầu dãy
- Độ phức tạp: O(n²)
```java
void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n-1; i++) {
        int min_idx = i;
        for (int j = i+1; j < n; j++) {
            if (arr[j] < arr[min_idx])
                min_idx = j;
        }
        // Đổi chỗ phần tử nhỏ nhất với phần tử đầu
        int temp = arr[min_idx];
        arr[min_idx] = arr[i];
        arr[i] = temp;
    }
}
```

#### 2.3. Insertion Sort
- Chèn từng phần tử vào vị trí thích hợp trong dãy đã sắp xếp
- Độ phức tạp: O(n²)
```java
void insertionSort(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}
```

### 3. Các thuật toán sắp xếp nâng cao

#### 3.1. Merge Sort
- Chia để trị: chia dãy thành 2 nửa, sắp xếp từng nửa và trộn lại
- Độ phức tạp: O(n log n)
```java
void mergeSort(int[] arr, int l, int r) {
    if (l < r) {
        int m = (l + r) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }
}

void merge(int[] arr, int l, int m, int r) {
    // Code trộn hai mảng con đã sắp xếp
}
```

#### 3.2. Quick Sort
- Chọn pivot, phân hoạch mảng và sắp xếp đệ quy
- Độ phức tạp trung bình: O(n log n)
```java
void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

int partition(int[] arr, int low, int high) {
    // Code phân hoạch mảng quanh pivot
}
```

### 4. So sánh các thuật toán

| Thuật toán | Độ phức tạp TB | Độ phức tạp KG | Ổn định |
|------------|----------------|----------------|---------|
| Bubble Sort | O(n²) | O(1) | Có |
| Selection Sort | O(n²) | O(1) | Không |
| Insertion Sort | O(n²) | O(1) | Có |
| Merge Sort | O(n log n) | O(n) | Có |
| Quick Sort | O(n log n) | O(log n) | Không |

### 5. Tối ưu hóa

#### 5.1. Cải tiến Bubble Sort
```java
void optimizedBubbleSort(int[] arr) {
    int n = arr.length;
    boolean swapped;
    for (int i = 0; i < n-1; i++) {
        swapped = false;
        for (int j = 0; j < n-i-1; j++) {
            if (arr[j] > arr[j+1]) {
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                swapped = true;
            }
        }
        if (!swapped) break;
    }
}
```

#### 5.2. Hybrid Sorting
- Kết hợp các thuật toán sắp xếp
- Ví dụ: Sử dụng Insertion Sort cho mảng nhỏ trong Merge Sort

## 💻 Bài tập thực hành

### Bài 1: So sánh hiệu suất
Viết chương trình so sánh thời gian thực thi của các thuật toán sắp xếp với các kích thước mảng khác nhau.
```
Input: Mảng số nguyên ngẫu nhiên
Output: Thời gian thực thi của từng thuật toán
```

### Bài 2: Sắp xếp đối tượng
Cài đặt các thuật toán sắp xếp cho một mảng các đối tượng Student theo điểm số.
```java
class Student {
    String name;
    double score;
}
```

### Bài 3: Custom Comparator
Viết chương trình sắp xếp mảng String theo độ dài, nếu bằng nhau thì sắp xếp theo thứ tự từ điển.

## 📚 Tài liệu tham khảo
- [Sorting Algorithms in Java](https://www.baeldung.com/java-sorting-arrays)
- [Java Arrays.sort()](https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#sort(java.lang.Object[]))
- [Complexity Analysis of Sorting Algorithms](https://www.geeksforgeeks.org/time-complexities-of-all-sorting-algorithms/)