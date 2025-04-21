# Slot 10: Buổi thực hành 1

## 🎯 Mục tiêu
- Ôn tập và củng cố kiến thức từ các buổi học trước
- Thực hành giải quyết các bài toán tổng hợp
- Phân tích và tối ưu hóa giải pháp
- Cải thiện kỹ năng lập trình và debug

## 📝 Nội dung

### 1. Ôn tập kiến thức

#### 1.1. Cấu trúc dữ liệu đã học
- Array và LinkedList
- Stack và Queue
- Binary Search Tree
- Hash Table

#### 1.2. Thuật toán đã học
- Đệ quy
- Các thuật toán sắp xếp
- Các thuật toán tìm kiếm
- Xử lý đồ thị cơ bản

### 2. Bài tập thực hành

#### 2.1. Bài 1: Quản lý sinh viên
Xây dựng hệ thống quản lý sinh viên với các chức năng:
- Thêm/sửa/xóa thông tin sinh viên
- Sắp xếp theo điểm hoặc họ tên
- Tìm kiếm sinh viên
- Tính điểm trung bình của lớp
- Thống kê số sinh viên theo xếp loại

```java
class Student {
    String id;
    String name;
    double gpa;
    // ... các thuộc tính khác
}

class StudentManager {
    // Cài đặt các phương thức quản lý
    void addStudent(Student s);
    void removeStudent(String id);
    Student findStudent(String id);
    void sortByGPA();
    void sortByName();
    double getAverageGPA();
    Map<String, Integer> getGradeDistribution();
}
```

#### 2.2. Bài 2: Xử lý biểu thức toán học
Viết chương trình tính giá trị biểu thức toán học:
- Chuyển biểu thức trung tố sang hậu tố
- Tính giá trị biểu thức hậu tố
- Kiểm tra tính hợp lệ của biểu thức

```java
class ExpressionEvaluator {
    // Chuyển biểu thức trung tố sang hậu tố
    String infixToPostfix(String expr);
    
    // Tính giá trị biểu thức hậu tố
    double evaluatePostfix(String postfix);
    
    // Kiểm tra tính hợp lệ
    boolean isValidExpression(String expr);
}
```

#### 2.3. Bài 3: Từ điển đơn giản
Xây dựng từ điển sử dụng cấu trúc dữ liệu phù hợp:
- Thêm/xóa từ
- Tìm kiếm từ
- Gợi ý từ (autocomplete)
- Lưu và tải từ file

```java
class Dictionary {
    // Cài đặt các phương thức từ điển
    void addWord(String word, String meaning);
    void removeWord(String word);
    String findMeaning(String word);
    List<String> getSuggestions(String prefix);
    void saveToFile(String filename);
    void loadFromFile(String filename);
}
```

### 3. Yêu cầu cài đặt

#### 3.1. Bài 1: Quản lý sinh viên
1. Sử dụng ArrayList hoặc LinkedList để lưu trữ sinh viên
2. Cài đặt các thuật toán sắp xếp phù hợp
3. Sử dụng HashMap để tối ưu tìm kiếm
4. Xử lý các trường hợp ngoại lệ

#### 3.2. Bài 2: Xử lý biểu thức
1. Sử dụng Stack để xử lý biểu thức
2. Xử lý các toán tử +, -, *, /, ()
3. Kiểm tra tính hợp lệ của dấu ngoặc
4. Xử lý các trường hợp đặc biệt

#### 3.3. Bài 3: Từ điển
1. Sử dụng HashTable hoặc TreeMap để lưu từ
2. Cài đặt trie cho chức năng gợi ý từ
3. Xử lý file I/O
4. Tối ưu hiệu năng tìm kiếm

### 4. Tiêu chí đánh giá
- Tính chính xác của giải pháp
- Hiệu quả về mặt thời gian và không gian
- Khả năng xử lý các trường hợp đặc biệt
- Chất lượng mã nguồn
- Giao diện người dùng (nếu có)

## 💡 Gợi ý làm bài

### 1. Quản lý sinh viên
- Sử dụng `Comparator` để sắp xếp linh hoạt
- Tối ưu tìm kiếm bằng HashMap với key là ID
- Sử dụng Stream API để tính toán thống kê

### 2. Xử lý biểu thức
- Xây dựng bảng ưu tiên toán tử
- Sử dụng hai Stack cho chuyển đổi trung tố-hậu tố
- Kiểm tra kỹ các trường hợp đặc biệt

### 3. Từ điển
- Thiết kế cấu trúc Trie hiệu quả
- Sử dụng StringBuilder cho xử lý chuỗi
- Đọc/ghi file theo batch để tối ưu hiệu năng

## 📚 Tài liệu tham khảo
- [Java Collections Framework](https://docs.oracle.com/javase/tutorial/collections/index.html)
- [Data Structures Practice Problems](https://www.geeksforgeeks.org/data-structures/)
- [Algorithm Problem Solving Strategies](https://www.programiz.com/dsa)