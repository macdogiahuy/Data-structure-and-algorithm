/**
 * Minh họa cài đặt từ điển sử dụng cây AVL
 */
public class AVLTreeDictionary {
    
    // Node trong cây AVL
    static class Node {
        String word;          // Từ
        String meaning;       // Nghĩa
        int height;          // Chiều cao node
        Node left, right;    // Con trái, phải
        
        Node(String word, String meaning) {
            this.word = word;
            this.meaning = meaning;
            this.height = 1;  // Node lá có chiều cao 1
        }
    }
    
    private Node root;
    private int size;
    
    // Lấy chiều cao của node
    private int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }
    
    // Lấy hệ số cân bằng của node
    private int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }
    
    // Cập nhật chiều cao của node
    private void updateHeight(Node node) {
        if (node == null) return;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }
    
    // Xoay phải
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        updateHeight(y);
        updateHeight(x);
        
        return x;
    }
    
    // Xoay trái
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        updateHeight(x);
        updateHeight(y);
        
        return y;
    }
    
    // Thêm từ mới vào từ điển
    public void insert(String word, String meaning) {
        root = insertRec(root, word, meaning);
        size++;
    }
    
    private Node insertRec(Node node, String word, String meaning) {
        // 1. Thực hiện thêm BST bình thường
        if (node == null) {
            return new Node(word, meaning);
        }
        
        int cmp = word.compareTo(node.word);
        if (cmp < 0) {
            node.left = insertRec(node.left, word, meaning);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, word, meaning);
        } else {
            // Cập nhật nghĩa nếu từ đã tồn tại
            node.meaning = meaning;
            size--;
            return node;
        }
        
        // 2. Cập nhật chiều cao
        updateHeight(node);
        
        // 3. Kiểm tra và cân bằng cây
        int balance = getBalance(node);
        
        // Left Left Case
        if (balance > 1 && word.compareTo(node.left.word) < 0) {
            return rightRotate(node);
        }
        
        // Right Right Case
        if (balance < -1 && word.compareTo(node.right.word) > 0) {
            return leftRotate(node);
        }
        
        // Left Right Case
        if (balance > 1 && word.compareTo(node.left.word) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        // Right Left Case
        if (balance < -1 && word.compareTo(node.right.word) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        return node;
    }
    
    // Tìm nghĩa của từ
    public String search(String word) {
        Node node = searchRec(root, word);
        return node == null ? null : node.meaning;
    }
    
    private Node searchRec(Node node, String word) {
        if (node == null) return null;
        
        int cmp = word.compareTo(node.word);
        if (cmp == 0) return node;
        if (cmp < 0) return searchRec(node.left, word);
        return searchRec(node.right, word);
    }
    
    // Đếm số từ bắt đầu bằng prefix
    public int countByPrefix(String prefix) {
        return countByPrefixRec(root, prefix);
    }
    
    private int countByPrefixRec(Node node, String prefix) {
        if (node == null) return 0;
        
        int count = 0;
        if (node.word.startsWith(prefix)) {
            count = 1;
        }
        
        if (prefix.compareTo(node.word) <= 0) {
            count += countByPrefixRec(node.left, prefix);
        }
        if (prefix.compareTo(node.word) >= 0) {
            count += countByPrefixRec(node.right, prefix);
        }
        
        return count;
    }
    
    // In từ điển theo thứ tự
    public void printInOrder() {
        System.out.println("Từ điển (" + size + " từ):");
        printInOrderRec(root);
    }
    
    private void printInOrderRec(Node node) {
        if (node == null) return;
        
        printInOrderRec(node.left);
        System.out.printf("%s: %s%n", node.word, node.meaning);
        printInOrderRec(node.right);
    }
    
    // Kiểm tra tính cân bằng của cây
    public boolean isBalanced() {
        return isBalancedRec(root);
    }
    
    private boolean isBalancedRec(Node node) {
        if (node == null) return true;
        
        int balance = getBalance(node);
        if (Math.abs(balance) > 1) return false;
        
        return isBalancedRec(node.left) && isBalancedRec(node.right);
    }
    
    public static void main(String[] args) {
        AVLTreeDictionary dict = new AVLTreeDictionary();
        
        // 1. Thêm một số từ
        System.out.println("1. Thêm từ vào từ điển:");
        dict.insert("hello", "xin chào");
        dict.insert("world", "thế giới");
        dict.insert("computer", "máy tính");
        dict.insert("algorithm", "thuật toán");
        dict.insert("data", "dữ liệu");
        dict.printInOrder();
        
        // 2. Tìm kiếm từ
        System.out.println("\n2. Tìm kiếm từ:");
        String[] wordsToSearch = {"hello", "world", "test"};
        for (String word : wordsToSearch) {
            String meaning = dict.search(word);
            if (meaning != null) {
                System.out.println(word + ": " + meaning);
            } else {
                System.out.println(word + ": Không tìm thấy");
            }
        }
        
        // 3. Đếm từ theo prefix
        System.out.println("\n3. Đếm từ theo prefix:");
        String[] prefixes = {"co", "a", "x"};
        for (String prefix : prefixes) {
            int count = dict.countByPrefix(prefix);
            System.out.println("Số từ bắt đầu bằng '" + prefix + "': " + count);
        }
        
        // 4. Kiểm tra tính cân bằng
        System.out.println("\n4. Kiểm tra tính cân bằng:");
        System.out.println("Cây có cân bằng? " + dict.isBalanced());
    }
}
