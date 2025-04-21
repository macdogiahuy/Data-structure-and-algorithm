# Slot 02: Stack và Queue

## 🎯 Mục tiêu
- Hiểu được cấu trúc và đặc điểm của Stack và Queue
- Nắm vững các thao tác cơ bản trên Stack và Queue
- Hiểu được các ứng dụng thực tế của Stack và Queue
- Cài đặt được Stack và Queue bằng Array và LinkedList

## 📝 Nội dung

### 1. Stack (Ngăn xếp)
#### 1.1. Định nghĩa
- Stack là cấu trúc dữ liệu theo nguyên tắc LIFO (Last In First Out)
- Các phần tử được thêm vào và lấy ra từ cùng một đầu gọi là đỉnh (top)

#### 1.2. Các thao tác cơ bản
- push(element): Thêm phần tử vào đỉnh stack
- pop(): Lấy và xóa phần tử ở đỉnh stack
- peek()/top(): Xem phần tử ở đỉnh stack không xóa
- isEmpty(): Kiểm tra stack rỗng
- size(): Lấy số lượng phần tử

#### 1.3. Ứng dụng
- Quản lý lời gọi hàm (Call Stack)
- Kiểm tra dấu ngoặc trong biểu thức
- Chuyển đổi biểu thức trung tố sang hậu tố
- Undo/Redo trong các ứng dụng

### 2. Queue (Hàng đợi)
#### 2.1. Định nghĩa
- Queue là cấu trúc dữ liệu theo nguyên tắc FIFO (First In First Out)
- Thêm vào ở cuối (rear/tail), lấy ra ở đầu (front/head)

#### 2.2. Các thao tác cơ bản
- enqueue(element): Thêm phần tử vào cuối queue
- dequeue(): Lấy và xóa phần tử ở đầu queue
- peek()/front(): Xem phần tử ở đầu queue không xóa
- isEmpty(): Kiểm tra queue rỗng
- size(): Lấy số lượng phần tử

#### 2.3. Ứng dụng
- Xử lý tác vụ theo thứ tự (Task Processing)
- Bộ đệm trong xử lý dữ liệu (Buffer)
- Quản lý in ấn (Print Queue)
- Breadth-First Search trong đồ thị

### 3. Cài đặt Stack và Queue

#### 3.1. Cài đặt Stack bằng Array
```java
class ArrayStack {
    private int maxSize;
    private int[] stackArray;
    private int top;
    
    public ArrayStack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }
    
    public void push(int value) {
        if (top < maxSize - 1) {
            stackArray[++top] = value;
        }
    }
    
    public int pop() {
        if (!isEmpty()) {
            return stackArray[top--];
        }
        return -1;
    }
    
    public boolean isEmpty() {
        return (top == -1);
    }
}
```

#### 3.2. Cài đặt Queue bằng LinkedList
```java
class LinkedListQueue {
    private class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            next = null;
        }
    }
    
    private Node front, rear;
    
    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }
    
    public int dequeue() {
        if (!isEmpty()) {
            int value = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            return value;
        }
        return -1;
    }
    
    public boolean isEmpty() {
        return front == null;
    }
}
```

## 💻 Bài tập thực hành

### Bài 1: Kiểm tra dấu ngoặc
Viết chương trình sử dụng Stack để kiểm tra tính hợp lệ của các dấu ngoặc trong biểu thức.
```
Input: "((()))"
Output: Valid

Input: "((())"
Output: Invalid
```

### Bài 2: Mô phỏng in ấn
Viết chương trình sử dụng Queue để mô phỏng hàng đợi in ấn với các thao tác:
- Thêm tài liệu vào hàng đợi
- In tài liệu (lấy ra từ đầu hàng đợi)
- Hiển thị số lượng tài liệu đang chờ
- Hiển thị tài liệu tiếp theo sẽ được in

### Bài 3: Chuyển đổi số thập phân
Viết chương trình sử dụng Stack để chuyển đổi số thập phân sang nhị phân.
```
Input: 13
Output: 1101

Input: 42
Output: 101010
```

## 📚 Tài liệu tham khảo
- [Java Stack Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html)
- [Java Queue Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html)