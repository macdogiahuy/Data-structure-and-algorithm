/**
 * Minh họa xử lý biểu thức toán học sử dụng Stack
 */
import java.util.*;

public class ExpressionEvaluator {
    
    // Kiểm tra xem ký tự có phải là toán tử không
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    // Lấy độ ưu tiên của toán tử
    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }
    
    // Chuyển đổi biểu thức trung tố sang hậu tố
    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        // Loại bỏ khoảng trắng và chuyển sang mảng ký tự
        char[] chars = infix.replaceAll("\\s+", "").toCharArray();
        
        for (char c : chars) {
            // Nếu là số, thêm vào kết quả
            if (Character.isDigit(c)) {
                postfix.append(c);
            }
            // Nếu là dấu mở ngoặc, đưa vào stack
            else if (c == '(') {
                stack.push(c);
            }
            // Nếu là dấu đóng ngoặc
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(' ').append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // Loại bỏ dấu mở ngoặc
                }
            }
            // Nếu là toán tử
            else if (isOperator(c)) {
                postfix.append(' '); // Ngăn cách với số trước đó
                while (!stack.isEmpty() && stack.peek() != '(' && 
                       getPrecedence(stack.peek()) >= getPrecedence(c)) {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }
        
        // Xử lý các toán tử còn lại trong stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression"; // Thiếu dấu đóng ngoặc
            }
            postfix.append(' ').append(stack.pop());
        }
        
        return postfix.toString().trim();
    }
    
    // Tính giá trị biểu thức hậu tố
    public static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");
        
        for (String token : tokens) {
            // Nếu là số
            if (token.matches("\\d+")) {
                stack.push(Double.parseDouble(token));
            }
            // Nếu là toán tử
            else if (token.length() == 1 && isOperator(token.charAt(0))) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression");
                }
                
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                
                switch (token.charAt(0)) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                    case '/':
                        if (operand2 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        stack.push(operand1 / operand2);
                        break;
                }
            }
        }
        
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }
        
        return stack.pop();
    }
    
    // Kiểm tra tính hợp lệ của biểu thức
    public static boolean isValidExpression(String expr) {
        Stack<Character> stack = new Stack<>();
        char[] chars = expr.toCharArray();
        
        for (char c : chars) {
            if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
            else if (!Character.isDigit(c) && !isOperator(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        // Test các biểu thức
        String[] expressions = {
            "3 + 4 * 2",
            "(3 + 4) * 2",
            "5 * (6 + 2) - 12 / 4",
            "1 + 2 + 3",
            "((3 + 4) * (5 - 2))",
            "3 + 4 )",  // Invalid
            "2 + * 3"   // Invalid
        };
        
        System.out.println("Đánh giá các biểu thức:");
        for (String expr : expressions) {
            System.out.println("\nBiểu thức: " + expr);
            
            if (!isValidExpression(expr)) {
                System.out.println("Biểu thức không hợp lệ!");
                continue;
            }
            
            try {
                String postfix = infixToPostfix(expr);
                System.out.println("Biểu thức hậu tố: " + postfix);
                
                double result = evaluatePostfix(postfix);
                System.out.println("Kết quả: " + result);
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
}