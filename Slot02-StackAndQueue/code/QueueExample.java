/**
 * Minh họa cài đặt và sử dụng Queue trong Java
 */
public class QueueExample {
    static class Queue {
        private class Node {
            String data;
            Node next;
            
            Node(String data) {
                this.data = data;
                this.next = null;
            }
        }
        
        private Node front;  // Phần tử đầu queue
        private Node rear;   // Phần tử cuối queue
        private int size;    // Số lượng phần tử
        
        public Queue() {
            front = null;
            rear = null;
            size = 0;
        }
        
        // Thêm phần tử vào cuối queue
        public void enqueue(String value) {
            Node newNode = new Node(value);
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
            System.out.println("Đã thêm '" + value + "' vào queue");
        }
        
        // Lấy và xóa phần tử ở đầu queue
        public String dequeue() {
            if (isEmpty()) {
                System.out.println("Queue rỗng!");
                return null;
            }
            
            String value = front.data;
            front = front.next;
            size--;
            
            if (front == null) {
                rear = null;
            }
            
            System.out.println("Đã lấy '" + value + "' ra khỏi queue");
            return value;
        }
        
        // Xem phần tử ở đầu queue không xóa
        public String peek() {
            if (isEmpty()) {
                System.out.println("Queue rỗng!");
                return null;
            }
            return front.data;
        }
        
        // Kiểm tra queue rỗng
        public boolean isEmpty() {
            return front == null;
        }
        
        // Lấy số lượng phần tử
        public int size() {
            return size;
        }
    }
    
    // Class mô phỏng hàng đợi in ấn
    static class PrintQueue {
        private Queue queue;
        
        public PrintQueue() {
            queue = new Queue();
        }
        
        // Thêm tài liệu vào hàng đợi in
        public void addDocument(String document) {
            queue.enqueue(document);
        }
        
        // In tài liệu (lấy ra từ đầu hàng đợi)
        public void printDocument() {
            String doc = queue.dequeue();
            if (doc != null) {
                System.out.println("Đang in: " + doc);
            }
        }
        
        // Hiển thị số lượng tài liệu đang chờ
        public void showQueueStatus() {
            System.out.println("Số tài liệu đang chờ: " + queue.size());
            if (!queue.isEmpty()) {
                System.out.println("Tài liệu tiếp theo: " + queue.peek());
            }
        }
    }
    
    public static void main(String[] args) {
        // Kiểm tra các thao tác cơ bản của Queue
        System.out.println("1. Kiểm tra các thao tác cơ bản của Queue:");
        Queue queue = new Queue();
        
        queue.enqueue("Item 1");
        queue.enqueue("Item 2");
        queue.enqueue("Item 3");
        
        System.out.println("Phần tử đầu queue: " + queue.peek());
        System.out.println("Kích thước hiện tại: " + queue.size());
        
        queue.dequeue();
        queue.dequeue();
        
        System.out.println("Queue rỗng? " + queue.isEmpty());
        
        // Mô phỏng hàng đợi in ấn
        System.out.println("\n2. Mô phỏng hệ thống in ấn:");
        PrintQueue printQueue = new PrintQueue();
        
        printQueue.addDocument("Report.pdf");
        printQueue.addDocument("Letter.doc");
        printQueue.addDocument("Image.jpg");
        
        System.out.println("\nTrạng thái hàng đợi in:");
        printQueue.showQueueStatus();
        
        System.out.println("\nBắt đầu in:");
        printQueue.printDocument();
        printQueue.showQueueStatus();
        
        printQueue.printDocument();
        printQueue.showQueueStatus();
    }
}