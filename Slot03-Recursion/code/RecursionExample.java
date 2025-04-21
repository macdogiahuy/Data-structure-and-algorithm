/**
 * Minh họa các dạng đệ quy và ứng dụng trong Java
 */
public class RecursionExample {
    
    // 1. Đệ quy tuyến tính - Tính giai thừa
    public static long factorial(int n) {
        // Base case
        if (n <= 1) return 1;
        // Recursive case
        return n * factorial(n - 1);
    }
    
    // 2. Đệ quy nhị phân - Dãy Fibonacci
    public static long fibonacci(int n) {
        // Base case
        if (n <= 1) return n;
        // Recursive case
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // 3. Đệ quy đuôi - Tính giai thừa tối ưu
    public static long factorialTail(int n, long acc) {
        // Base case
        if (n <= 1) return acc;
        // Tail recursive call
        return factorialTail(n - 1, n * acc);
    }
    
    // 4. Đệ quy có nhớ - Fibonacci với memoization
    private static long[] memo;
    
    public static long fibonacciMemo(int n) {
        if (memo == null) {
            memo = new long[n + 1];
        }
        
        // Kiểm tra giá trị đã tính
        if (memo[n] != 0) return memo[n];
        
        // Base case
        if (n <= 1) return n;
        
        // Recursive case với lưu trữ kết quả
        memo[n] = fibonacciMemo(n - 1) + fibonacciMemo(n - 2);
        return memo[n];
    }
    
    // 5. Bài toán Tháp Hà Nội
    public static void towerOfHanoi(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("Di chuyển đĩa 1 từ cột " + from + " sang cột " + to);
            return;
        }
        
        towerOfHanoi(n - 1, from, aux, to);
        System.out.println("Di chuyển đĩa " + n + " từ cột " + from + " sang cột " + to);
        towerOfHanoi(n - 1, aux, to, from);
    }
    
    // 6. Tìm đường trong mê cung
    public static boolean solveMaze(int[][] maze, int x, int y, int[][] solution) {
        // Kiểm tra điểm đích (góc phải dưới)
        if (x == maze.length - 1 && y == maze[0].length - 1 && maze[x][y] == 1) {
            solution[x][y] = 1;
            return true;
        }
        
        // Kiểm tra có thể đi tiếp không
        if (isValidMove(maze, x, y)) {
            solution[x][y] = 1;
            
            // Đi sang phải
            if (solveMaze(maze, x, y + 1, solution)) return true;
            // Đi xuống dưới
            if (solveMaze(maze, x + 1, y, solution)) return true;
            
            // Backtrack nếu không tìm được đường
            solution[x][y] = 0;
        }
        
        return false;
    }
    
    private static boolean isValidMove(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 1;
    }
    
    public static void main(String[] args) {
        System.out.println("1. Tính giai thừa:");
        System.out.println("5! = " + factorial(5));
        System.out.println("5! (đệ quy đuôi) = " + factorialTail(5, 1));
        
        System.out.println("\n2. Dãy Fibonacci:");
        System.out.println("Fibonacci(7) = " + fibonacci(7));
        System.out.println("Fibonacci(7) (có nhớ) = " + fibonacciMemo(7));
        
        System.out.println("\n3. Tháp Hà Nội với 3 đĩa:");
        towerOfHanoi(3, 'A', 'C', 'B');
        
        System.out.println("\n4. Tìm đường trong mê cung:");
        int[][] maze = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
        };
        
        int[][] solution = new int[maze.length][maze[0].length];
        
        if (solveMaze(maze, 0, 0, solution)) {
            System.out.println("Tìm thấy đường đi:");
            for (int i = 0; i < solution.length; i++) {
                for (int j = 0; j < solution[0].length; j++) {
                    System.out.print(solution[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Không tìm thấy đường đi!");
        }
    }
}