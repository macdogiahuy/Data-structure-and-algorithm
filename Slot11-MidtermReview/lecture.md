# Slot 11: Ôn tập giữa kỳ và Mini Project

## 🎯 Mục tiêu
- Ôn tập tổng hợp các kiến thức đã học
- Thực hiện một mini project tích hợp nhiều cấu trúc dữ liệu và thuật toán
- Củng cố kỹ năng phân tích và giải quyết vấn đề
- Tăng cường khả năng thiết kế và cài đặt hệ thống

## 📝 Nội dung

### 1. Ôn tập kiến thức

#### 1.1. Cấu trúc dữ liệu cơ bản
1. Array và LinkedList
   - Đặc điểm và ứng dụng
   - Độ phức tạp các thao tác
   - So sánh ưu/nhược điểm

2. Stack và Queue
   - Nguyên lý LIFO và FIFO
   - Các phương thức cơ bản
   - Ứng dụng thực tế

3. Tree và Binary Search Tree
   - Cấu trúc và đặc điểm
   - Các phép duyệt cây
   - Các thao tác cơ bản

4. Graph
   - Biểu diễn đồ thị
   - Duyệt đồ thị (BFS/DFS)
   - Ứng dụng đồ thị

5. Hash Table
   - Hàm băm và xử lý đụng độ
   - Hiệu năng và tối ưu hóa
   - Ứng dụng thực tế

#### 1.2. Thuật toán
1. Đệ quy
   - Nguyên lý và cách hoạt động
   - Tối ưu hóa đệ quy
   - Các dạng bài tập

2. Sắp xếp
   - Các thuật toán cơ bản
   - So sánh hiệu năng
   - Khi nào dùng thuật toán nào

3. Tìm kiếm
   - Tuyến tính và nhị phân
   - Điều kiện áp dụng
   - Độ phức tạp

### 2. Mini Project: Hệ thống Quản lý Thư viện

#### 2.1. Mô tả
Xây dựng hệ thống quản lý thư viện với các chức năng:
- Quản lý sách và độc giả
- Mượn/trả sách
- Tìm kiếm và gợi ý sách
- Thống kê và báo cáo

#### 2.2. Yêu cầu chức năng

1. Quản lý sách:
```java
class Book {
    String id;
    String title;
    String author;
    String category;
    int quantity;
    int available;
}

interface BookManager {
    void addBook(Book book);
    void removeBook(String id);
    void updateBook(Book book);
    Book findBook(String id);
    List<Book> searchBooks(String keyword);
    List<Book> getBooksByCategory(String category);
}
```

2. Quản lý độc giả:
```java
class Reader {
    String id;
    String name;
    List<BorrowRecord> borrowHistory;
}

interface ReaderManager {
    void addReader(Reader reader);
    void removeReader(String id);
    Reader findReader(String id);
    List<BorrowRecord> getBorrowHistory(String readerId);
}
```

3. Quản lý mượn/trả:
```java
class BorrowRecord {
    String id;
    String readerId;
    String bookId;
    Date borrowDate;
    Date dueDate;
    Date returnDate;
}

interface BorrowManager {
    void borrowBook(String readerId, String bookId);
    void returnBook(String readerId, String bookId);
    List<BorrowRecord> getOverdueBooks();
}
```

#### 2.3. Yêu cầu cài đặt

1. Cấu trúc dữ liệu:
   - Sử dụng HashMap cho tìm kiếm nhanh theo ID
   - Sử dụng ArrayList cho danh sách linh hoạt
   - Sử dụng BST cho tìm kiếm theo khoảng
   - Sử dụng Queue cho xử lý đặt sách

2. Thuật toán:
   - Tìm kiếm nhị phân cho sách
   - Sắp xếp cho hiển thị và báo cáo
   - Đệ quy cho duyệt cây phân loại

3. Tối ưu hóa:
   - Cache cho dữ liệu thường dùng
   - Index cho tìm kiếm nhanh
   - Batch processing cho thao tác hàng loạt

### 3. Hướng dẫn thực hiện

#### 3.1. Thiết kế
1. Phân tích yêu cầu chi tiết
2. Thiết kế các lớp và giao diện
3. Xác định quan hệ giữa các đối tượng
4. Lựa chọn cấu trúc dữ liệu phù hợp

#### 3.2. Cài đặt
1. Cài đặt các lớp cơ bản
2. Cài đặt các chức năng theo module
3. Tích hợp các module
4. Xử lý ngoại lệ và validation

#### 3.3. Kiểm thử
1. Unit test cho từng module
2. Integration test cho hệ thống
3. Performance test cho các thao tác quan trọng

### 4. Tiêu chí đánh giá
1. Thiết kế và cấu trúc code (30%)
2. Chức năng và tính chính xác (40%)
3. Hiệu năng và tối ưu hóa (20%)
4. Xử lý ngoại lệ và validation (10%)

## 💻 Bài tập ôn tập

### 1. Cấu trúc dữ liệu
Triển khai một cấu trúc Priority Queue sử dụng Heap

### 2. Thuật toán
Cài đặt thuật toán tìm đường đi ngắn nhất trong đồ thị

### 3. Tối ưu hóa
Cải thiện hiệu năng của hệ thống cache đơn giản

## 📚 Tài liệu tham khảo
- [Java Collections Framework](https://docs.oracle.com/javase/tutorial/collections/index.html)
- [Design Patterns](https://refactoring.guru/design-patterns)
- [Clean Code](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882)