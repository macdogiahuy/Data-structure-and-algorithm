/**
 * Minh họa các thuật toán sắp xếp trong Java
 */
public class SortingExample {
    
    // 1. Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Đổi chỗ arr[j] và arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // Nếu không có phần tử nào được đổi chỗ -> mảng đã sắp xếp
            if (!swapped) break;
        }
    }
    
    // 2. Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            // Đổi chỗ phần tử nhỏ nhất với phần tử đầu
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
    
    // 3. Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    
    // 4. Merge Sort
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
        
        // Copy dữ liệu vào mảng tạm
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];
        
        // Trộn hai mảng tạm vào mảng arr
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        // Copy các phần tử còn lại của L[] nếu có
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        
        // Copy các phần tử còn lại của R[] nếu có
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    // 5. Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Đổi chỗ arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Đổi chỗ arr[i+1] và arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    // Hàm tiện ích để in mảng
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // Hàm tạo mảng ngẫu nhiên
    public static int[] generateRandomArray(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }
    
    // So sánh hiệu suất các thuật toán
    public static void comparePerformance(int size) {
        int[] arr1 = generateRandomArray(size, 1000);
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();
        int[] arr4 = arr1.clone();
        int[] arr5 = arr1.clone();
        
        System.out.println("\nSo sánh hiệu suất với mảng kích thước " + size + ":");
        
        long startTime = System.nanoTime();
        bubbleSort(arr1);
        System.out.println("Bubble Sort: " + (System.nanoTime() - startTime) / 1000000.0 + " ms");
        
        startTime = System.nanoTime();
        selectionSort(arr2);
        System.out.println("Selection Sort: " + (System.nanoTime() - startTime) / 1000000.0 + " ms");
        
        startTime = System.nanoTime();
        insertionSort(arr3);
        System.out.println("Insertion Sort: " + (System.nanoTime() - startTime) / 1000000.0 + " ms");
        
        startTime = System.nanoTime();
        mergeSort(arr4, 0, arr4.length - 1);
        System.out.println("Merge Sort: " + (System.nanoTime() - startTime) / 1000000.0 + " ms");
        
        startTime = System.nanoTime();
        quickSort(arr5, 0, arr5.length - 1);
        System.out.println("Quick Sort: " + (System.nanoTime() - startTime) / 1000000.0 + " ms");
    }
    
    public static void main(String[] args) {
        // 1. Demo với mảng nhỏ
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Mảng ban đầu:");
        printArray(arr);
        
        bubbleSort(arr.clone());
        System.out.println("\nSau khi sắp xếp bằng Bubble Sort:");
        printArray(arr);
        
        // 2. So sánh hiệu suất với các kích thước mảng khác nhau
        comparePerformance(1000);    // Mảng nhỏ
        comparePerformance(10000);   // Mảng vừa
        comparePerformance(100000);  // Mảng lớn
    }
}