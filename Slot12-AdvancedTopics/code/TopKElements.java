/**
 * Minh họa sử dụng Min Heap để duy trì K phần tử lớn nhất trong luồng dữ liệu
 */
import java.util.*;

public class TopKElements {
    
    static class MinHeap {
        private int[] heap;
        private int size;
        private final int capacity;
        
        public MinHeap(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            heap = new int[capacity];
        }
        
        private int parent(int i) { return (i - 1) / 2; }
        private int leftChild(int i) { return 2 * i + 1; }
        private int rightChild(int i) { return 2 * i + 2; }
        
        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
        
        // Thêm phần tử mới
        public void insert(int key) {
            if (size == capacity) {
                if (key > heap[0]) {
                    heap[0] = key;
                    bubbleDown(0);
                }
            } else {
                heap[size] = key;
                bubbleUp(size++);
            }
        }
        
        // Lấy phần tử nhỏ nhất
        public int getMin() {
            if (size == 0) throw new IllegalStateException("Heap is empty");
            return heap[0];
        }
        
        // Xóa và trả về phần tử nhỏ nhất
        public int extractMin() {
            if (size == 0) throw new IllegalStateException("Heap is empty");
            
            int min = heap[0];
            heap[0] = heap[--size];
            bubbleDown(0);
            
            return min;
        }
        
        // Điều chỉnh heap từ dưới lên
        private void bubbleUp(int index) {
            while (index > 0 && heap[parent(index)] > heap[index]) {
                swap(index, parent(index));
                index = parent(index);
            }
        }
        
        // Điều chỉnh heap từ trên xuống
        private void bubbleDown(int index) {
            int minIndex = index;
            int left = leftChild(index);
            int right = rightChild(index);
            
            if (left < size && heap[left] < heap[minIndex]) {
                minIndex = left;
            }
            if (right < size && heap[right] < heap[minIndex]) {
                minIndex = right;
            }
            
            if (minIndex != index) {
                swap(index, minIndex);
                bubbleDown(minIndex);
            }
        }
        
        // Lấy tất cả phần tử theo thứ tự tăng dần
        public int[] getSorted() {
            int[] result = new int[size];
            MinHeap temp = new MinHeap(size);
            System.arraycopy(heap, 0, temp.heap, 0, size);
            temp.size = size;
            
            for (int i = 0; i < size; i++) {
                result[i] = temp.extractMin();
            }
            
            return result;
        }
    }
    
    static class TopK {
        private final int k;
        private MinHeap minHeap;
        
        public TopK(int k) {
            this.k = k;
            this.minHeap = new MinHeap(k);
        }
        
        // Thêm số mới vào danh sách
        public void add(int num) {
            minHeap.insert(num);
        }
        
        // Lấy k số lớn nhất (theo thứ tự tăng dần)
        public int[] getTopK() {
            return minHeap.getSorted();
        }
        
        // Xóa số lớn nhất trong k số
        public int removeTop() {
            // Lấy tất cả phần tử trừ phần tử nhỏ nhất
            int[] current = minHeap.getSorted();
            minHeap = new MinHeap(k);
            for (int i = 0; i < current.length - 1; i++) {
                minHeap.insert(current[i]);
            }
            return current[current.length - 1];
        }
    }
    
    public static void main(String[] args) {
        int k = 3; // Duy trì 3 số lớn nhất
        TopK topK = new TopK(k);
        
        // 1. Test thêm số
        System.out.println("1. Thêm các số:");
        int[] numbers = {4, 1, 7, 3, 8, 5};
        for (int num : numbers) {
            System.out.println("Thêm " + num);
            topK.add(num);
            System.out.println("Top " + k + " hiện tại: " + 
                             Arrays.toString(topK.getTopK()));
        }
        
        // 2. Test xóa số lớn nhất
        System.out.println("\n2. Xóa số lớn nhất:");
        int removed = topK.removeTop();
        System.out.println("Đã xóa: " + removed);
        System.out.println("Top " + k + " sau khi xóa: " + 
                         Arrays.toString(topK.getTopK()));
        
        // 3. Test thêm số sau khi xóa
        System.out.println("\n3. Thêm số mới sau khi xóa:");
        topK.add(6);
        System.out.println("Top " + k + " sau khi thêm 6: " + 
                         Arrays.toString(topK.getTopK()));
    }
}