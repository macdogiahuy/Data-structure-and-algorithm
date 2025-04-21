/**
 * Minh họa tìm kiếm trong mảng đã sắp xếp nhưng bị xoay vòng
 * Ví dụ: [5, 6, 7, 1, 2, 3, 4] là mảng [1, 2, 3, 4, 5, 6, 7] đã bị xoay
 */
public class SearchRotatedArray {
    
    // Tìm kiếm trong mảng đã xoay
    public static int search(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Nếu tìm thấy key
            if (arr[mid] == key) {
                return mid;
            }
            
            // Nếu nửa bên trái được sắp xếp
            if (arr[left] <= arr[mid]) {
                // Kiểm tra xem key có nằm trong nửa trái không
                if (key >= arr[left] && key < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Nếu nửa bên phải được sắp xếp
            else {
                // Kiểm tra xem key có nằm trong nửa phải không
                if (key > arr[mid] && key <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    // Tìm điểm xoay (pivot) trong mảng
    public static int findPivot(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (mid < right && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            
            if (mid > left && arr[mid - 1] > arr[mid]) {
                return mid - 1;
            }
            
            if (arr[left] >= arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return -1;
    }
    
    // Xoay mảng theo số vị trí cho trước
    public static void rotateArray(int[] arr, int k) {
        k = k % arr.length; // Chuẩn hóa k
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
    }
    
    // Hàm đảo ngược mảng từ vị trí start đến end
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    
    // In mảng
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // 1. Tạo mảng đã sắp xếp
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Mảng ban đầu:");
        printArray(arr);
        
        // 2. Xoay mảng
        int k = 3; // Số vị trí xoay
        rotateArray(arr, k);
        System.out.println("\nMảng sau khi xoay " + k + " vị trí:");
        printArray(arr);
        
        // 3. Tìm điểm xoay
        int pivotIndex = findPivot(arr);
        System.out.println("\nĐiểm xoay (pivot) ở vị trí: " + pivotIndex);
        
        // 4. Tìm kiếm các giá trị trong mảng đã xoay
        int[] keysToSearch = {1, 4, 7, 8};
        System.out.println("\nTìm kiếm trong mảng đã xoay:");
        
        for (int key : keysToSearch) {
            int index = search(arr, key);
            if (index != -1) {
                System.out.println("Tìm thấy " + key + " tại vị trí: " + index);
            } else {
                System.out.println("Không tìm thấy " + key + " trong mảng");
            }
        }
        
        // 5. So sánh hiệu suất với tìm kiếm tuyến tính
        System.out.println("\nSo sánh hiệu suất tìm kiếm giá trị 4:");
        
        // Tìm kiếm tuyến tính
        long startTime = System.nanoTime();
        int linearResult = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                linearResult = i;
                break;
            }
        }
        long linearTime = System.nanoTime() - startTime;
        
        // Tìm kiếm trong mảng xoay
        startTime = System.nanoTime();
        int rotatedResult = search(arr, 4);
        long rotatedTime = System.nanoTime() - startTime;
        
        System.out.println("Linear Search: " + linearTime + " ns (index: " + linearResult + ")");
        System.out.println("Rotated Array Search: " + rotatedTime + " ns (index: " + rotatedResult + ")");
    }
}