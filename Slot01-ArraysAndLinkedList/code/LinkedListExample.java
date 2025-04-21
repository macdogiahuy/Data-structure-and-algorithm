public class LinkedListExample {
    // Định nghĩa cấu trúc Node
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    // Class LinkedList với các thao tác cơ bản
    static class LinkedList {
        Node head;
        
        // Thêm node vào đầu danh sách
        public void addFirst(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }
        
        // Thêm node vào cuối danh sách
        public void addLast(int data) {
            Node newNode = new Node(data);
            
            if (head == null) {
                head = newNode;
                return;
            }
            
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        
        // Xóa node đầu tiên
        public void removeFirst() {
            if (head == null) return;
            head = head.next;
        }
        
        // Tìm kiếm một giá trị
        public boolean search(int key) {
            Node current = head;
            while (current != null) {
                if (current.data == key) return true;
                current = current.next;
            }
            return false;
        }
        
        // In danh sách
        public void printList() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }
    
    public static void main(String[] args) {
        // Khởi tạo LinkedList
        LinkedList list = new LinkedList();
        
        // Thêm phần tử vào danh sách
        System.out.println("Thêm các phần tử 1, 2, 3, 4, 5 vào danh sách:");
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.printList();
        
        // Thêm phần tử vào đầu
        System.out.println("\nThêm số 0 vào đầu danh sách:");
        list.addFirst(0);
        list.printList();
        
        // Xóa phần tử đầu
        System.out.println("\nXóa phần tử đầu tiên:");
        list.removeFirst();
        list.printList();
        
        // Tìm kiếm phần tử
        int searchKey = 3;
        System.out.println("\nTìm kiếm giá trị " + searchKey + ":");
        if (list.search(searchKey)) {
            System.out.println("Tìm thấy " + searchKey + " trong danh sách");
        } else {
            System.out.println("Không tìm thấy " + searchKey + " trong danh sách");
        }
    }
}