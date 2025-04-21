# Slot 08: Hash Table (Bảng băm)

## 🎯 Mục tiêu
- Hiểu được cấu trúc và nguyên lý hoạt động của Hash Table
- Nắm vững các phương pháp hàm băm và xử lý đụng độ
- Phân tích được độ phức tạp của các thao tác trên Hash Table
- Cài đặt được Hash Table với các chiến lược xử lý đụng độ khác nhau
- Áp dụng Hash Table vào các bài toán thực tế

## 📝 Nội dung

### 1. Giới thiệu Hash Table

#### 1.1. Định nghĩa
- Cấu trúc dữ liệu lưu trữ dữ liệu theo cặp key-value
- Sử dụng hàm băm để ánh xạ key thành chỉ số trong mảng
- Cho phép truy cập dữ liệu với độ phức tạp O(1) trong trường hợp tốt nhất

#### 1.2. Thành phần chính
- Hash Function (Hàm băm)
- Bucket Array (Mảng chứa dữ liệu)
- Collision Resolution (Phương pháp xử lý đụng độ)

### 2. Hàm băm (Hash Function)

#### 2.1. Yêu cầu của hàm băm tốt
- Tính nhất quán
- Phân bố đều
- Tốc độ tính toán nhanh
- Tỷ lệ đụng độ thấp

#### 2.2. Các phương pháp băm phổ biến
```java
// 1. Phương pháp chia
public int hash(K key) {
    return Math.abs(key.hashCode()) % tableSize;
}

// 2. Phương pháp nhân
public int hash(K key) {
    double A = 0.6180339887; // (sqrt(5) - 1) / 2
    double temp = A * Math.abs(key.hashCode());
    return (int)(tableSize * (temp - Math.floor(temp)));
}
```

### 3. Xử lý đụng độ (Collision Resolution)

#### 3.1. Phương pháp kết nối (Chaining)
```java
public class HashNode<K, V> {
    K key;
    V value;
    HashNode<K, V> next;
    
    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class HashTable<K, V> {
    private ArrayList<HashNode<K, V>> bucketArray;
    private int numBuckets;
    private int size;
    
    public void put(K key, V value) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);
    }
}
```

#### 3.2. Địa chỉ mở (Open Addressing)
1. Dò tuyến tính (Linear Probing)
```java
int probe(int key, int i) {
    return (hash(key) + i) % tableSize;
}
```

2. Dò bậc hai (Quadratic Probing)
```java
int probe(int key, int i) {
    return (hash(key) + i*i) % tableSize;
}
```

3. Băm kép (Double Hashing)
```java
int probe(int key, int i) {
    return (hash1(key) + i * hash2(key)) % tableSize;
}
```

### 4. Phân tích hiệu năng

#### 4.1. Độ phức tạp thời gian
| Thao tác | Trường hợp tốt nhất | Trường hợp xấu nhất |
|----------|-------------------|-------------------|
| Insert | O(1) | O(n) |
| Search | O(1) | O(n) |
| Delete | O(1) | O(n) |

#### 4.2. Hệ số tải (Load Factor)
- α = n/m (n: số phần tử, m: kích thước bảng)
- Ảnh hưởng đến hiệu năng và xác suất đụng độ
- Thường giữ α < 0.75 để đảm bảo hiệu năng

### 5. Ứng dụng thực tế
- Cài đặt từ điển
- Bộ nhớ cache
- Bảng ký hiệu trong compiler
- Quản lý session trong web server
- Loại bỏ phần tử trùng lặp

## 💻 Bài tập thực hành

### Bài 1: Cài đặt Hash Table
Cài đặt Hash Table sử dụng phương pháp kết nối với các thao tác:
- put(key, value)
- get(key)
- remove(key)
- containsKey(key)

### Bài 2: Tìm phần tử trùng lặp
Sử dụng Hash Table để tìm phần tử xuất hiện nhiều hơn một lần trong mảng.
```java
Input: [1, 2, 3, 1, 3, 6, 6]
Output: [1, 3, 6]
```

### Bài 3: Nhóm Anagram
Viết chương trình nhóm các từ là anagram của nhau sử dụng Hash Table.
```java
Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
Output: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
```

## 📚 Tài liệu tham khảo
- [Hash Table in Java](https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html)
- [Hash Functions](https://www.geeksforgeeks.org/hash-functions-and-list-types-of-hash-functions/)
- [Collision Resolution Techniques](https://www.cs.cmu.edu/~adamchik/15-121/lectures/Hashing/hashing.html)