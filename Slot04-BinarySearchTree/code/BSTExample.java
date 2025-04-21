/**
 * Minh họa cài đặt và sử dụng Binary Search Tree trong Java
 */
public class BSTExample {
    // Định nghĩa cấu trúc node
    static class Node {
        int data;
        Node left, right;
        
        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }
    
    // Class Binary Search Tree
    static class BST {
        Node root;
        
        BST() {
            root = null;
        }
        
        // 1. Thêm node mới
        void insert(int key) {
            root = insertRec(root, key);
        }
        
        private Node insertRec(Node root, int key) {
            if (root == null) {
                return new Node(key);
            }
            
            if (key < root.data)
                root.left = insertRec(root.left, key);
            else if (key > root.data)
                root.right = insertRec(root.right, key);
            
            return root;
        }
        
        // 2. Tìm kiếm node
        Node search(int key) {
            return searchRec(root, key);
        }
        
        private Node searchRec(Node root, int key) {
            if (root == null || root.data == key)
                return root;
            
            if (key < root.data)
                return searchRec(root.left, key);
            
            return searchRec(root.right, key);
        }
        
        // 3. Xóa node
        void delete(int key) {
            root = deleteRec(root, key);
        }
        
        private Node deleteRec(Node root, int key) {
            if (root == null) return null;
            
            if (key < root.data)
                root.left = deleteRec(root.left, key);
            else if (key > root.data)
                root.right = deleteRec(root.right, key);
            else {
                // Node với một hoặc không có con
                if (root.left == null)
                    return root.right;
                else if (root.right == null)
                    return root.left;
                
                // Node có hai con: lấy node nhỏ nhất bên phải
                root.data = minValue(root.right);
                root.right = deleteRec(root.right, root.data);
            }
            return root;
        }
        
        // Tìm giá trị nhỏ nhất trong cây
        private int minValue(Node root) {
            int minv = root.data;
            while (root.left != null) {
                minv = root.left.data;
                root = root.left;
            }
            return minv;
        }
        
        // 4. Các phương pháp duyệt cây
        // Duyệt theo thứ tự trước (Pre-order)
        void preorder() {
            System.out.print("Duyệt theo thứ tự trước: ");
            preorderRec(root);
            System.out.println();
        }
        
        private void preorderRec(Node root) {
            if (root != null) {
                System.out.print(root.data + " ");
                preorderRec(root.left);
                preorderRec(root.right);
            }
        }
        
        // Duyệt theo thứ tự giữa (In-order)
        void inorder() {
            System.out.print("Duyệt theo thứ tự giữa: ");
            inorderRec(root);
            System.out.println();
        }
        
        private void inorderRec(Node root) {
            if (root != null) {
                inorderRec(root.left);
                System.out.print(root.data + " ");
                inorderRec(root.right);
            }
        }
        
        // Duyệt theo thứ tự sau (Post-order)
        void postorder() {
            System.out.print("Duyệt theo thứ tự sau: ");
            postorderRec(root);
            System.out.println();
        }
        
        private void postorderRec(Node root) {
            if (root != null) {
                postorderRec(root.left);
                postorderRec(root.right);
                System.out.print(root.data + " ");
            }
        }
        
        // 5. Kiểm tra BST hợp lệ
        boolean isBST() {
            return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        private boolean isBSTUtil(Node node, int min, int max) {
            if (node == null)
                return true;
            
            if (node.data <= min || node.data >= max)
                return false;
            
            return isBSTUtil(node.left, min, node.data) && 
                   isBSTUtil(node.right, node.data, max);
        }
    }
    
    public static void main(String[] args) {
        BST tree = new BST();
        
        // 1. Thêm các node
        System.out.println("1. Thêm các node vào BST:");
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
        
        // 2. Hiển thị cây theo các cách duyệt khác nhau
        System.out.println("\n2. Các cách duyệt cây:");
        tree.preorder();
        tree.inorder();
        tree.postorder();
        
        // 3. Tìm kiếm node
        System.out.println("\n3. Tìm kiếm node:");
        int searchKey = 40;
        Node result = tree.search(searchKey);
        System.out.println("Tìm node " + searchKey + ": " + 
                          (result != null ? "Tìm thấy!" : "Không tìm thấy!"));
        
        // 4. Xóa node
        System.out.println("\n4. Xóa node:");
        int deleteKey = 30;
        System.out.println("Trước khi xóa " + deleteKey + ":");
        tree.inorder();
        
        tree.delete(deleteKey);
        System.out.println("Sau khi xóa " + deleteKey + ":");
        tree.inorder();
        
        // 5. Kiểm tra tính hợp lệ của BST
        System.out.println("\n5. Kiểm tra BST:");
        System.out.println("Cây có phải là BST hợp lệ? " + tree.isBST());
    }
}