/**
 * Minh họa các bài tập thực hành về độ phức tạp thuật toán
 */
public class PracticeProblems {
    
    // Bài 1: Tìm phần tử lớn thứ k sử dụng QuickSelect
    // Độ phức tạp trung bình: O(n), xấu nhất: O(n²)
    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private static int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];
        
        int pivotIndex = partition(nums, left, right);
        
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }
    
    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        
        swap(nums, i, right);
        return i;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // Bài 2: Tìm các cặp số có hiệu bằng k
    // Cách 1: Brute Force - O(n²)
    public static int countPairsBruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    count++;
                }
            }
        }
        return count;
    }
    
    // Cách 2: Tối ưu sử dụng HashSet - O(n)
    public static int countPairsOptimized(int[] nums, int k) {
        java.util.HashSet<Integer> set = new java.util.HashSet<>();
        int count = 0;
        
        // Thêm tất cả các số vào HashSet
        for (int num : nums) {
            set.add(num);
        }
        
        // Kiểm tra từng số
        for (int num : nums) {
            if (set.contains(num + k)) {
                count++;
            }
            if (set.contains(num - k)) {
                count++;
            }
            set.remove(num); // Tránh đếm trùng
        }
        
        return count;
    }
    
    // Bài 3: So sánh hiệu năng của hai cách giải
    public static void compareApproaches() {
        System.out.println("So sánh hiệu năng hai cách giải bài toán tìm cặp số:\n");
        
        // Test với các kích thước khác nhau
        int[] sizes = {100, 1000, 10000};
        
        for (int size : sizes) {
            System.out.println("Kích thước mảng: " + size);
            
            // Tạo mảng ngẫu nhiên
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = (int) (Math.random() * size);
            }
            
            int k = 5; // Giá trị hiệu cần tìm
            
            // Test Brute Force
            if (size <= 1000) { // Skip for large sizes
                long startTime = System.nanoTime();
                int resultBF = countPairsBruteForce(arr, k);
                long endTime = System.nanoTime();
                System.out.println("Brute Force O(n²): " + 
                                 (endTime - startTime) / 1000000.0 + " ms");
                System.out.println("Số cặp tìm được: " + resultBF);
            }
            
            // Test Optimized
            long startTime = System.nanoTime();
            int resultOpt = countPairsOptimized(arr, k);
            long endTime = System.nanoTime();
            System.out.println("Optimized O(n): " + 
                             (endTime - startTime) / 1000000.0 + " ms");
            System.out.println("Số cặp tìm được: " + resultOpt);
            
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // 1. Test tìm phần tử lớn thứ k
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("Phần tử lớn thứ " + k + ": " + 
                          findKthLargest(nums, k));
        
        // 2. So sánh hiệu năng hai cách giải bài toán tìm cặp số
        System.out.println();
        compareApproaches();
    }
}