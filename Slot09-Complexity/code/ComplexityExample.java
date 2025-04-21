/**
 * Minh họa các độ phức tạp thuật toán và so sánh hiệu năng
 */
public class ComplexityExample {
    
    // 1. O(1) - Constant Time
    public static int getFirst(int[] arr) {
        return arr[0];
    }
    
    // 2. O(log n) - Logarithmic Time
    public static int binarySearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            if (arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        
        return -1;
    }
    
    // 3. O(n) - Linear Time
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    // 4. O(n log n) - Linearithmic Time
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
    
    // 5. O(n²) - Quadratic Time
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // 6. O(2ⁿ) - Exponential Time
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // 7. O(n) - Tối ưu Fibonacci với Dynamic Programming
    public static int fibonacciDP(int n) {
        if (n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[n];
    }
    
    // So sánh hiệu năng các thuật toán với kích thước đầu vào khác nhau
    public static void comparePerformance() {
        System.out.println("So sánh hiệu năng các thuật toán:\n");
        
        // Test với các kích thước khác nhau
        int[] sizes = {10, 100, 1000, 10000};
        
        for (int size : sizes) {
            System.out.println("Kích thước mảng: " + size);
            
            // Tạo mảng ngẫu nhiên
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = (int) (Math.random() * size);
            }
            
            // 1. O(1) - Constant Time
            long startTime = System.nanoTime();
            getFirst(arr);
            long endTime = System.nanoTime();
            System.out.println("O(1) - Constant Time: " + 
                             (endTime - startTime) / 1000.0 + " microseconds");
            
            // 2. O(n) - Linear Time
            startTime = System.nanoTime();
            findMax(arr);
            endTime = System.nanoTime();
            System.out.println("O(n) - Linear Time: " + 
                             (endTime - startTime) / 1000.0 + " microseconds");
            
            // 3. O(n log n) - Linearithmic Time
            int[] arrCopy = arr.clone();
            startTime = System.nanoTime();
            mergeSort(arrCopy, 0, arrCopy.length - 1);
            endTime = System.nanoTime();
            System.out.println("O(n log n) - Merge Sort: " + 
                             (endTime - startTime) / 1000.0 + " microseconds");
            
            // 4. O(n²) - Quadratic Time
            if (size <= 1000) {  // Skip for large sizes
                arrCopy = arr.clone();
                startTime = System.nanoTime();
                bubbleSort(arrCopy);
                endTime = System.nanoTime();
                System.out.println("O(n²) - Bubble Sort: " + 
                                 (endTime - startTime) / 1000.0 + " microseconds");
            }
            
            // 5. Compare Fibonacci implementations
            if (size == 10) {  // Only test small numbers for recursive
                int n = 10;
                startTime = System.nanoTime();
                fibonacci(n);
                endTime = System.nanoTime();
                System.out.println("O(2ⁿ) - Recursive Fibonacci: " + 
                                 (endTime - startTime) / 1000.0 + " microseconds");
                
                startTime = System.nanoTime();
                fibonacciDP(n);
                endTime = System.nanoTime();
                System.out.println("O(n) - Dynamic Programming Fibonacci: " + 
                                 (endTime - startTime) / 1000.0 + " microseconds");
            }
            
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // So sánh hiệu năng các thuật toán
        comparePerformance();
    }
}