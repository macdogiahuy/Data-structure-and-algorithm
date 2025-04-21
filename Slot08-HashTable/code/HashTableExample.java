/**
 * Minh họa cài đặt và sử dụng Hash Table trong Java
 */
import java.util.ArrayList;

public class HashTableExample {
    
    // Node lưu trữ cặp key-value
    static class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;
        
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    
    // Cài đặt Hash Table sử dụng phương pháp kết nối (chaining)
    static class HashTable<K, V> {
        private ArrayList<HashNode<K, V>> bucketArray;
        private int numBuckets;
        private int size;
        
        public HashTable() {
            bucketArray = new ArrayList<>();
            numBuckets = 10;
            size = 0;
            
            // Khởi tạo bucketArray
            for (int i = 0; i < numBuckets; i++) {
                bucketArray.add(null);
            }
        }
        
        public int size() { return size; }
        public boolean isEmpty() { return size() == 0; }
        
        // Hàm băm
        private int getBucketIndex(K key) {
            int hashCode = key.hashCode();
            return Math.abs(hashCode % numBuckets);
        }
        
        // Thêm cặp key-value
        public void put(K key, V value) {
            int bucketIndex = getBucketIndex(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);
            
            // Kiểm tra xem key đã tồn tại chưa
            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }
            
            // Thêm node mới vào đầu bucket
            size++;
            head = bucketArray.get(bucketIndex);
            HashNode<K, V> newNode = new HashNode<K, V>(key, value);
            newNode.next = head;
            bucketArray.set(bucketIndex, newNode);
            
            // Nếu load factor > 0.7, tăng kích thước bảng
            if ((1.0 * size)/numBuckets >= 0.7) {
                ArrayList<HashNode<K, V>> temp = bucketArray;
                bucketArray = new ArrayList<>();
                numBuckets = 2 * numBuckets;
                size = 0;
                
                for (int i = 0; i < numBuckets; i++) {
                    bucketArray.add(null);
                }
                
                for (HashNode<K, V> node : temp) {
                    while (node != null) {
                        put(node.key, node.value);
                        node = node.next;
                    }
                }
            }
        }
        
        // Lấy giá trị từ key
        public V get(K key) {
            int bucketIndex = getBucketIndex(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);
            
            while (head != null) {
                if (head.key.equals(key)) {
                    return head.value;
                }
                head = head.next;
            }
            
            return null;
        }
        
        // Xóa cặp key-value
        public V remove(K key) {
            int bucketIndex = getBucketIndex(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);
            
            HashNode<K, V> prev = null;
            while (head != null) {
                if (head.key.equals(key)) {
                    break;
                }
                prev = head;
                head = head.next;
            }
            
            if (head == null) {
                return null;
            }
            
            size--;
            if (prev != null) {
                prev.next = head.next;
            } else {
                bucketArray.set(bucketIndex, head.next);
            }
            
            return head.value;
        }
        
        // Kiểm tra key tồn tại
        public boolean containsKey(K key) {
            return get(key) != null;
        }
    }
    
    public static void main(String[] args) {
        // Tạo hash table lưu tên và điểm số
        HashTable<String, Integer> scores = new HashTable<>();
        
        // 1. Thêm dữ liệu
        System.out.println("1. Thêm điểm số của học sinh:");
        scores.put("Alice", 95);
        scores.put("Bob", 89);
        scores.put("Charlie", 91);
        scores.put("David", 88);
        scores.put("Eva", 93);
        
        // 2. Truy xuất dữ liệu
        System.out.println("\n2. Lấy điểm số:");
        System.out.println("Điểm của Alice: " + scores.get("Alice"));
        System.out.println("Điểm của Charlie: " + scores.get("Charlie"));
        
        // 3. Kiểm tra tồn tại
        System.out.println("\n3. Kiểm tra tồn tại:");
        System.out.println("Bob có trong danh sách? " + scores.containsKey("Bob"));
        System.out.println("Frank có trong danh sách? " + scores.containsKey("Frank"));
        
        // 4. Xóa dữ liệu
        System.out.println("\n4. Xóa dữ liệu:");
        scores.remove("Bob");
        System.out.println("Sau khi xóa Bob, danh sách có Bob? " + scores.containsKey("Bob"));
        
        // 5. Cập nhật dữ liệu
        System.out.println("\n5. Cập nhật điểm:");
        scores.put("Alice", 97);  // Cập nhật điểm Alice
        System.out.println("Điểm mới của Alice: " + scores.get("Alice"));
        
        // 6. Thông tin về kích thước
        System.out.println("\n6. Thông tin Hash Table:");
        System.out.println("Số lượng học sinh: " + scores.size());
        System.out.println("Hash Table rỗng? " + scores.isEmpty());
    }
}