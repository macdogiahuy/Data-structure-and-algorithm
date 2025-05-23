public class ArrayExample {
    public static void main(String[] args) {
        // Khởi tạo mảng
        int[] numbers = {1, 2, 3, 4, 5};
        
        // In mảng ban đầu
        System.out.println("Mảng ban đầu:");
        printArray(numbers);
        
        // Đảo ngược mảng
        reverseArray(numbers);
        System.out.println("\nMảng sau khi đảo ngược:");
        printArray(numbers);
        
        // Tính tổng các phần tử
        int sum = calculateSum(numbers);
        System.out.println("\nTổng các phần tử: " + sum);
    }
    
    // Hàm đảo ngược mảng
    public static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    
    // Hàm tính tổng mảng
    public static int calculateSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }
    
    // Hàm in mảng
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}