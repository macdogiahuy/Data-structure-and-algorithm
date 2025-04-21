/**
 * Minh họa cài đặt Interval Tree sử dụng Red-Black Tree để quản lý các khoảng thời gian
 */
public class IntervalTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    // Node trong cây
    static class Node {
        Interval interval;    // Khoảng thời gian
        int max;             // Giá trị end lớn nhất trong cây con này
        boolean color;       // Màu của node
        Node left, right;    // Con trái, phải
        
        Node(Interval interval) {
            this.interval = interval;
            this.max = interval.end;
            this.color = RED;
        }
    }
    
    // Lớp biểu diễn khoảng thời gian
    static class Interval {
        int start, end;
        
        public Interval(int start, int end) {
            if (start > end) {
                throw new IllegalArgumentException("Invalid interval");
            }
            this.start = start;
            this.end = end;
        }
        
        // Kiểm tra có giao với khoảng khác không
        public boolean overlaps(Interval other) {
            return this.start <= other.end && other.start <= this.end;
        }
        
        // Kiểm tra có chứa thời điểm t không
        public boolean contains(int t) {
            return start <= t && t <= end;
        }
        
        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
    
    private Node root;
    
    // Kiểm tra node có màu đỏ không
    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }
    
    // Xoay trái
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        
        h.max = max3(h.interval.end, 
                    maxEnd(h.left), 
                    maxEnd(h.right));
        x.max = max3(x.interval.end,
                    h.max,
                    maxEnd(x.right));
        
        return x;
    }
    
    // Xoay phải
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        
        h.max = max3(h.interval.end,
                    maxEnd(h.left),
                    maxEnd(h.right));
        x.max = max3(x.interval.end,
                    maxEnd(x.left),
                    h.max);
        
        return x;
    }
    
    // Đảo màu
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    
    // Lấy giá trị max end trong cây con
    private int maxEnd(Node node) {
        if (node == null) return Integer.MIN_VALUE;
        return node.max;
    }
    
    // Lấy max của 3 số
    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
    
    // Thêm khoảng thời gian mới
    public void insert(Interval interval) {
        root = insert(root, interval);
        root.color = BLACK;
    }
    
    private Node insert(Node h, Interval interval) {
        if (h == null) return new Node(interval);
        
        int cmp = interval.start - h.interval.start;
        if (cmp < 0) h.left = insert(h.left, interval);
        else h.right = insert(h.right, interval);
        
        // Cập nhật max
        h.max = max3(h.interval.end,
                    maxEnd(h.left),
                    maxEnd(h.right));
        
        // Cân bằng cây Red-Black
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        
        return h;
    }
    
    // Tìm các khoảng chứa thời điểm t
    public Interval[] findOverlapping(int t) {
        java.util.List<Interval> result = new java.util.ArrayList<>();
        findOverlapping(root, t, result);
        return result.toArray(new Interval[0]);
    }
    
    private void findOverlapping(Node node, int t, java.util.List<Interval> result) {
        if (node == null) return;
        
        if (node.interval.contains(t)) {
            result.add(node.interval);
        }
        
        // Chỉ duyệt những nhánh có thể chứa t
        if (node.left != null && node.left.max >= t) {
            findOverlapping(node.left, t, result);
        }
        if (node.right != null && node.interval.start <= t) {
            findOverlapping(node.right, t, result);
        }
    }
    
    // In cây theo thứ tự inorder
    public void printTree() {
        System.out.println("Interval Tree:");
        printInOrder(root, "");
    }
    
    private void printInOrder(Node node, String indent) {
        if (node == null) return;
        
        printInOrder(node.right, indent + "  ");
        System.out.printf("%s%s (max=%d) [%s]%n", 
                         indent, 
                         node.interval, 
                         node.max, 
                         node.color == RED ? "RED" : "BLACK");
        printInOrder(node.left, indent + "  ");
    }
    
    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();
        
        // 1. Thêm các khoảng thời gian
        System.out.println("1. Thêm các khoảng thời gian:");
        tree.insert(new Interval(15, 20));
        tree.insert(new Interval(10, 30));
        tree.insert(new Interval(17, 19));
        tree.insert(new Interval(5, 20));
        tree.insert(new Interval(12, 15));
        tree.printTree();
        
        // 2. Tìm các khoảng chứa thời điểm
        int[] points = {13, 16, 21};
        for (int t : points) {
            System.out.println("\nCác khoảng chứa thời điểm " + t + ":");
            Interval[] overlapping = tree.findOverlapping(t);
            for (Interval interval : overlapping) {
                System.out.println(interval);
            }
        }
    }
}