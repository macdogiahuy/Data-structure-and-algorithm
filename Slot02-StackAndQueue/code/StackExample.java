/**
 * Minh họa cài đặt và sử dụng Stack trong Java
 */

public class StackExample {
    static class Stack {
        private int maxSize; // Kích thước tối đa của stack
        private int[] array; // Mảng lưu trữ phần tử
        private int top; // Chỉ số đỉnh stack

        // Khởi tạo stack
        public Stack(int size) {
            maxSize = size;
            array = new int[maxSize];
            top = -1;
        }

        // Thêm phần tử vào đỉnh stack
        public void push(int value) {
            if (top < maxSize - 1) {
                array[++top] = value;
                System.out.println("Đã thêm " + value + " vào stack");
            } else {
                System.out.println("Stack đầy!");
            }
        }

        // Lấy và xóa phần tử ở đỉnh stack
        public int pop() {
            if (!isEmpty()) {
                int value = array[top--];
                System.out.println("Đã lấy " + value + " ra khỏi stack");
                return value;
            }
            System.out.println("Stack rỗng!");
            return -1;
        }

        // Xem phần tử ở đỉnh stack không xóa
        public int peek() {
            if (!isEmpty()) {
                return array[top];
            }
            System.out.println("Stack rỗng!");
            return -1;
        }

        // Kiểm tra stack rỗng
        public boolean isEmpty() {
            return top == -1;
        }

        // Kiểm tra stack đầy
        public boolean isFull() {
            return top == maxSize - 1;
        }

        // Lấy số lượng phần tử hiện tại
        public int size() {
            return top + 1;
        }
    }

    // Hàm kiểm tra tính hợp lệ của dấu ngoặc
    public static boolean checkBrackets(String expression) {
        Stack stack = new Stack(expression.length());

        for (char c : expression.toCharArray()) {
            if (c == '(') {
                stack.push(1); // Đánh dấu 1 cho dấu mở ngoặc
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false; // Thiếu dấu mở ngoặc
                }
                stack.pop();
            }
        }

        return stack.isEmpty(); // True nếu các dấu ngoặc khớp đôi
    }

    public static void main(String[] args) {
        // Kiểm tra các thao tác cơ bản
        System.out.println("1. Kiểm tra các thao tác cơ bản của Stack:");
        Stack stack = new Stack(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Phần tử ở đỉnh stack: " + stack.peek());
        System.out.println("Kích thước hiện tại: " + stack.size());

        stack.pop();
        stack.pop();

        System.out.println("Stack rỗng? " + stack.isEmpty());

        // Kiểm tra tính hợp lệ của dấu ngoặc
        System.out.println("\n2. Kiểm tra tính hợp lệ của dấu ngoặc:");
        String expr1 = "((()))";
        String expr2 = "((())";

        System.out.println("Biểu thức '" + expr1 + "' hợp lệ? " + checkBrackets(expr1));
        System.out.println("Biểu thức '" + expr2 + "' hợp lệ? " + checkBrackets(expr2));
    }
}