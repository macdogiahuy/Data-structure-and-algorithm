# Slot 07: Các thuật toán Tìm kiếm (Searching Algorithms)

## 🎯 Mục tiêu
- Hiểu được các thuật toán tìm kiếm cơ bản và nâng cao
- Phân tích được độ phức tạp của từng thuật toán
- Cài đặt được các thuật toán tìm kiếm bằng Java
- So sánh và lựa chọn được thuật toán phù hợp cho từng bài toán
- Áp dụng được các thuật toán tìm kiếm vào các bài toán thực tế

## 📝 Nội dung

### 1. Tìm kiếm tuyến tính (Linear Search)

#### 1.1. Nguyên lý
- Duyệt tuần tự từng phần tử trong mảng
- So sánh từng phần tử với giá trị cần tìm
- Phù hợp với mảng chưa sắp xếp

#### 1.2. Cài đặt
```java
public static int linearSearch(int[] arr, int key) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == key) {
            return i;
        }
    }
    return -1;
}
```

#### 1.3. Độ phức tạp
- Thời gian: O(n)
- Không gian: O(1)

### 2. Tìm kiếm nhị phân (Binary Search)

#### 2.1. Nguyên lý
- Yêu cầu mảng đã sắp xếp
- Chia đôi khoảng tìm kiếm sau mỗi bước
- So sánh với phần tử giữa để xác định nửa chứa giá trị cần tìm

#### 2.2. Cài đặt đệ quy
```java
public static int binarySearchRecursive(int[] arr, int key, int left, int right) {
    if (left > right) {
        return -1;
    }
    
    int mid = left + (right - left) / 2;
    
    if (arr[mid] == key) {
        return mid;
    }
    
    if (arr[mid] > key) {
        return binarySearchRecursive(arr, key, left, mid - 1);
    }
    
    return binarySearchRecursive(arr, key, mid + 1, right);
}
```

#### 2.3. Cài đặt vòng lặp
```java
public static int binarySearchIterative(int[] arr, int key) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == key) {
            return mid;
        }
        
        if (arr[mid] > key) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    
    return -1;
}
```

#### 2.4. Độ phức tạp
- Thời gian: O(log n)
- Không gian: O(1) cho vòng lặp, O(log n) cho đệ quy

### 3. Các biến thể của tìm kiếm nhị phân

#### 3.1. Tìm phần tử đầu tiên
```java
public static int findFirstOccurrence(int[] arr, int key) {
    int left = 0;
    int right = arr.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == key) {
            result = mid;
            right = mid - 1;  // Tiếp tục tìm bên trái
        }
        else if (arr[mid] > key) {
            right = mid - 1;
        }
        else {
            left = mid + 1;
        }
    }
    
    return result;
}
```

#### 3.2. Tìm phần tử cuối cùng
```java
public static int findLastOccurrence(int[] arr, int key) {
    int left = 0;
    int right = arr.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == key) {
            result = mid;
            left = mid + 1;  // Tiếp tục tìm bên phải
        }
        else if (arr[mid] > key) {
            right = mid - 1;
        }
        else {
            left = mid + 1;
        }
    }
    
    return result;
}
```

### 4. Tìm kiếm nội suy (Interpolation Search)

#### 4.1. Nguyên lý
- Cải tiến của binary search cho dữ liệu phân bố đều
- Dự đoán vị trí của phần tử dựa trên giá trị tìm kiếm

#### 4.2. Cài đặt
```java
public static int interpolationSearch(int[] arr, int key) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left <= right && key >= arr[left] && key <= arr[right]) {
        if (left == right) {
            if (arr[left] == key) return left;
            return -1;
        }
        
        int pos = left + (((right - left) * (key - arr[left])) / 
                         (arr[right] - arr[left]));
        
        if (arr[pos] == key)
            return pos;
        
        if (arr[pos] < key)
            left = pos + 1;
        else
            right = pos - 1;
    }
    return -1;
}
```

### 5. So sánh các thuật toán

| Thuật toán | Độ phức tạp TB | Yêu cầu | Ưu điểm | Nhược điểm |
|------------|----------------|----------|---------|------------|
| Linear Search | O(n) | Không | Đơn giản, phù hợp mảng nhỏ | Chậm với mảng lớn |
| Binary Search | O(log n) | Mảng đã sắp xếp | Nhanh với mảng lớn | Yêu cầu sắp xếp |
| Interpolation Search | O(log log n) | Mảng sắp xếp, phân bố đều | Rất nhanh với dữ liệu đều | Kém hiệu quả với dữ liệu không đều |

## 💻 Bài tập thực hành

### Bài 1: Tìm kiếm phần tử gần nhất
Viết hàm tìm phần tử trong mảng đã sắp xếp có giá trị gần nhất với giá trị cho trước.
```
Input: arr = [1, 2, 4, 5, 6, 8, 9], key = 7
Output: 6 hoặc 8
```

### Bài 2: Đếm số lần xuất hiện
Viết chương trình đếm số lần xuất hiện của một phần tử trong mảng đã sắp xếp sử dụng binary search.
```
Input: arr = [1, 2, 2, 2, 3, 4, 4], key = 2
Output: 3
```

### Bài 3: Tìm kiếm trong mảng xoay
Viết hàm tìm kiếm một phần tử trong mảng đã sắp xếp nhưng bị xoay vòng.
```
Input: arr = [5, 6, 7, 1, 2, 3, 4], key = 2
Output: 4
```

## 📚 Tài liệu tham khảo
- [Searching Algorithms in Java](https://www.geeksforgeeks.org/searching-algorithms/)
- [Binary Search Tutorial](https://www.hackerearth.com/practice/algorithms/searching/binary-search/tutorial/)
- [Advanced Binary Search](https://www.topcoder.com/community/competitive-programming/tutorials/binary-search)