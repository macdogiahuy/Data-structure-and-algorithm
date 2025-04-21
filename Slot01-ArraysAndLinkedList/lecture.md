# Slot 01: Array và LinkedList

## 🎯 Mục tiêu
- Hiểu được cấu trúc và đặc điểm của Array và LinkedList
- Nắm vững các thao tác cơ bản trên Array và LinkedList
- So sánh được ưu nhược điểm của Array và LinkedList
- Biết cách áp dụng Array và LinkedList vào các bài toán thực tế

## 📝 Nội dung

### 1. Array (Mảng)
#### 1.1. Định nghĩa
- Array là cấu trúc dữ liệu lưu trữ các phần tử cùng kiểu dữ liệu trong các ô nhớ liên tiếp
- Các phần tử được đánh chỉ số từ 0 đến (n-1), với n là kích thước mảng

#### 1.2. Đặc điểm
- Kích thước cố định khi khởi tạo
- Truy cập ngẫu nhiên với độ phức tạp O(1)
- Thêm/xóa phần tử phải dịch chuyển các phần tử khác

#### 1.3. Các thao tác cơ bản
```java
// Khởi tạo
int[] arr = new int[5];
int[] arr = {1, 2, 3, 4, 5};

// Truy cập phần tử
int element = arr[index];

// Cập nhật phần tử
arr[index] = newValue;

// Duyệt mảng
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

### 2. LinkedList (Danh sách liên kết)
#### 2.1. Định nghĩa
- LinkedList là cấu trúc dữ liệu gồm các node liên kết với nhau
- Mỗi node chứa dữ liệu và con trỏ đến node tiếp theo

#### 2.2. Đặc điểm
- Kích thước động, có thể thay đổi trong quá trình chạy
- Truy cập tuần tự với độ phức tạp O(n)
- Thêm/xóa nhanh chóng nếu biết vị trí node

#### 2.3. Các thao tác cơ bản
```java
class Node {
    int data;
    Node next;
    
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Thêm node vào đầu
public void addFirst(int data) {
    Node newNode = new Node(data);
    newNode.next = head;
    head = newNode;
}

// Thêm node vào cuối
public void addLast(int data) {
    Node newNode = new Node(data);
    if (head == null) {
        head = newNode;
        return;
    }
    Node current = head;
    while (current.next != null) {
        current = current.next;
    }
    current.next = newNode;
}
```

### 3. So sánh Array và LinkedList

| Tiêu chí | Array | LinkedList |
|----------|-------|------------|
| Bộ nhớ | Liên tục | Phân tán |
| Kích thước | Cố định | Động |
| Truy cập | O(1) | O(n) |
| Thêm/Xóa đầu | O(n) | O(1) |
| Thêm/Xóa cuối | O(1) | O(n) |
| Thêm/Xóa giữa | O(n) | O(n) |

## 💻 Bài tập thực hành

### Bài 1: Đảo ngược mảng
Viết chương trình đảo ngược một mảng số nguyên không sử dụng mảng phụ.
```
Input: [1, 2, 3, 4, 5]
Output: [5, 4, 3, 2, 1]
```

### Bài 2: Quản lý danh sách sinh viên
Tạo một LinkedList để quản lý danh sách sinh viên với các thao tác:
- Thêm sinh viên vào đầu/cuối danh sách
- Xóa sinh viên theo mã số
- Tìm kiếm sinh viên theo tên
- In danh sách sinh viên

### Bài 3: Trộn hai mảng đã sắp xếp
Cho hai mảng số nguyên đã được sắp xếp tăng dần, hãy trộn chúng thành một mảng mới cũng được sắp xếp tăng dần.
```
Input: arr1 = [1, 3, 5], arr2 = [2, 4, 6]
Output: [1, 2, 3, 4, 5, 6]
```

## 📚 Tài liệu tham khảo
- [Java Array Documentation](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Java LinkedList Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html)