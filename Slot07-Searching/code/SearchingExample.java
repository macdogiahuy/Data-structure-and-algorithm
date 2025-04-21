/**
 * Minh họa các thuật toán tìm kiếm trong Java
 */
public class SearchingExample {
    
    // 1. Tìm kiếm tuyến tính
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
    
    // 2. Tìm kiếm nhị phân - Cách đệ quy
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
    
    // 3. Tìm kiếm nhị phân - Cách vòng lặp
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
    
    // 4. Tìm phần tử gần nhất
    public static int findNearest(int[] arr, int key) {
        if (arr == null || arr.length == 0) return -1;
        
        // Nếu key nhỏ hơn phần tử đầu tiên
        if (key <= arr[0]) return 0;
        
        // Nếu key lớn hơn phần tử cuối cùng
        if (key >= arr[arr.length - 1]) return arr.length - 1;
        
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == key) return mid;
            
            if (arr[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        // So sánh phần tử left và right để tìm gần nhất
        if (Math.abs(arr[left] - key) < Math.abs(arr[right] - key)) {
            return left;
        }
        return right;
    }
    
    // 5. Đếm số lần xuất hiện
    public static int countOccurrences(int[] arr, int key) {
        int firstIndex = findFirstOccurrence(arr, key);
        if (firstIndex == -1) return 0;
        
        int lastIndex = findLastOccurrence(arr, key);
        return lastIndex - firstIndex + 1;
    }
    
    // Tìm vị trí xuất hiện đầu tiên
    private static int findFirstOccurrence(int[] arr, int key) {
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
    
    // Tìm vị trí xuất hiện cuối cùng
    private static int findLastOccurrence(int[] arr, int key) {
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
    
    // 6. So sánh hiệu suất các thuật toán
    public static void comparePerformance(int[] arr, int key) {
        long startTime;
        
        // Linear Search
        startTime = System.nanoTime();
        int linearResult = linearSearch(arr, key);
        long linearTime = System.nanoTime() - startTime;
        
        // Binary Search (Recursive)
        startTime = System.nanoTime();
        int binaryRecResult = binarySearchRecursive(arr, key, 0, arr.length - 1);
        long binaryRecTime = System.nanoTime() - startTime;
        
        // Binary Search (Iterative)
        startTime = System.nanoTime();
        int binaryIterResult = binarySearchIterative(arr, key);
        long binaryIterTime = System.nanoTime() - startTime;
        
        System.out.println("\nSo sánh hiệu suất tìm kiếm " + key + ":");
        System.out.println("Linear Search: " + linearTime + " ns (index: " + linearResult + ")");
        System.out.println("Binary Search (Recursive): " + binaryRecTime + " ns (index: " + binaryRecResult + ")");
        System.out.println("Binary Search (Iterative): " + binaryIterTime + " ns (index: " + binaryIterResult + ")");
    }
    
    public static void main(String[] args) {
        // Mảng đã sắp xếp để test
        int[] arr = {1, 2, 2, 2, 3, 4, 4, 5, 6, 7, 8, 9};
        
        // 1. Test các phương pháp tìm kiếm cơ bản
        int key = 4;
        System.out.println("1. Tìm kiếm giá trị " + key + ":");
        System.out.println("Linear Search: " + linearSearch(arr, key));
        System.out.println("Binary Search (Recursive): " + 
                          binarySearchRecursive(arr, key, 0, arr.length - 1));
        System.out.println("Binary Search (Iterative): " + binarySearchIterative(arr, key));
        
        // 2. Tìm phần tử gần nhất
        int target = 7;
        int nearestIndex = findNearest(arr, target);
        System.out.println("\n2. Phần tử gần nhất với " + target + ": " + 
                          arr[nearestIndex] + " (index: " + nearestIndex + ")");
        
        // 3. Đếm số lần xuất hiện
        int countKey = 2;
        System.out.println("\n3. Số lần xuất hiện của " + countKey + ": " + 
                          countOccurrences(arr, countKey));
        
        // 4. So sánh hiệu suất
        comparePerformance(arr, 8);
    }
}