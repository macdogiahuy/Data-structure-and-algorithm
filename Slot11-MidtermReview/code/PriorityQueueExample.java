/**
 * Minh họa cài đặt Priority Queue sử dụng Binary Heap
 */
public class PriorityQueueExample {
    
    static class PriorityQueue<T extends Comparable<T>> {
        private T[] heap;
        private int size;
        private static final int DEFAULT_CAPACITY = 10;
        
        @SuppressWarnings("unchecked")
        public PriorityQueue() {
            heap = (T[]) new Comparable[DEFAULT_CAPACITY];
            size = 0;
        }
        
        // Thêm phần tử vào queue
        public void enqueue(T element) {
            if (size == heap.length) {
                resize();
            }
            
            heap[size] = element;
            bubbleUp(size);
            size++;
        }
        
        // Lấy và xóa phần tử có độ ưu tiên cao nhất
        public T dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            
            T root = heap[0];
            heap[0] = heap[size - 1];
            size--;
            if (!isEmpty()) {
                bubbleDown(0);
            }
            
            return root;
        }
        
        // Xem phần tử có độ ưu tiên cao nhất
        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            return heap[0];
        }
        
        // Kiểm tra queue rỗng
        public boolean isEmpty() {
            return size == 0;
        }
        
        // Lấy kích thước queue
        public int size() {
            return size;
        }
        
        // Điều chỉnh heap sau khi thêm
        private void bubbleUp(int index) {
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                
                if (heap[index].compareTo(heap[parentIndex]) < 0) {
                    swap(index, parentIndex);
                    index = parentIndex;
                } else {
                    break;
                }
            }
        }
        
        // Điều chỉnh heap sau khi xóa
        private void bubbleDown(int index) {
            while (true) {
                int leftChild = 2 * index + 1;
                int rightChild = 2 * index + 2;
                int smallest = index;
                
                if (leftChild < size && heap[leftChild].compareTo(heap[smallest]) < 0) {
                    smallest = leftChild;
                }
                
                if (rightChild < size && heap[rightChild].compareTo(heap[smallest]) < 0) {
                    smallest = rightChild;
                }
                
                if (smallest == index) {
                    break;
                }
                
                swap(index, smallest);
                index = smallest;
            }
        }
        
        // Đổi chỗ hai phần tử
        private void swap(int i, int j) {
            T temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
        
        // Tăng kích thước mảng khi đầy
        @SuppressWarnings("unchecked")
        private void resize() {
            T[] newHeap = (T[]) new Comparable[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
        
        // In queue theo từng level
        public void printHeap() {
            if (isEmpty()) {
                System.out.println("Queue rỗng");
                return;
            }
            
            int level = 0;
            int levelSize = 1;
            int index = 0;
            
            while (index < size) {
                System.out.print("Level " + level + ": ");
                for (int i = 0; i < levelSize && index < size; i++) {
                    System.out.print(heap[index++] + " ");
                }
                System.out.println();
                
                level++;
                levelSize *= 2;
            }
        }
    }
    
    // Lớp Task để test Priority Queue
    static class Task implements Comparable<Task> {
        private String name;
        private int priority;  // Số càng nhỏ, độ ưu tiên càng cao
        
        public Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
        
        @Override
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority);
        }
        
        @Override
        public String toString() {
            return String.format("(%s, priority=%d)", name, priority);
        }
    }
    
    public static void main(String[] args) {
        // Test với Integer
        System.out.println("1. Test Priority Queue với Integer:");
        PriorityQueue<Integer> numberQueue = new PriorityQueue<>();
        
        numberQueue.enqueue(5);
        numberQueue.enqueue(2);
        numberQueue.enqueue(8);
        numberQueue.enqueue(1);
        numberQueue.enqueue(9);
        
        System.out.println("Sau khi thêm các số:");
        numberQueue.printHeap();
        
        System.out.println("\nLấy ra 3 phần tử:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Dequeue: " + numberQueue.dequeue());
        }
        
        // Test với Task
        System.out.println("\n2. Test Priority Queue với Task:");
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        
        taskQueue.enqueue(new Task("Print", 3));
        taskQueue.enqueue(new Task("Emergency", 1));
        taskQueue.enqueue(new Task("Clean", 4));
        taskQueue.enqueue(new Task("Update", 2));
        
        System.out.println("Sau khi thêm các task:");
        taskQueue.printHeap();
        
        System.out.println("\nXử lý các task theo thứ tự ưu tiên:");
        while (!taskQueue.isEmpty()) {
            System.out.println("Processing: " + taskQueue.dequeue());
        }
    }
}