/**
 * Hệ thống quản lý file sử dụng cấu trúc cây với tính năng undo/redo
 */
import java.util.*;

public class FileSystem {
    
    // Node trong cây thư mục
    static class Node {
        private String name;
        private boolean isDirectory;
        private byte[] content;
        private Map<String, Node> children;
        private Node parent;
        
        public Node(String name, boolean isDirectory) {
            this.name = name;
            this.isDirectory = isDirectory;
            this.children = isDirectory ? new HashMap<>() : null;
        }
        
        public boolean isDirectory() { return isDirectory; }
        public String getName() { return name; }
        public byte[] getContent() { return content; }
        public void setContent(byte[] content) { this.content = content; }
        public Node getParent() { return parent; }
        public void setParent(Node parent) { this.parent = parent; }
        
        public Collection<Node> getChildren() {
            return children != null ? children.values() : null;
        }
        
        public Node getChild(String name) {
            return children != null ? children.get(name) : null;
        }
        
        public void addChild(Node child) {
            if (!isDirectory) throw new IllegalStateException("Not a directory");
            children.put(child.getName(), child);
            child.setParent(this);
        }
        
        public Node removeChild(String name) {
            if (!isDirectory) throw new IllegalStateException("Not a directory");
            Node child = children.remove(name);
            if (child != null) child.setParent(null);
            return child;
        }
    }
    
    // Interface cho các lệnh
    interface Command {
        void execute();
        void undo();
    }
    
    private final Node root;
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;
    
    public FileSystem() {
        root = new Node("/", true);
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    
    // Xử lý đường dẫn
    private String[] parsePath(String path) {
        if (!path.startsWith("/")) {
            throw new IllegalArgumentException("Path must start with /");
        }
        return path.substring(1).split("/");
    }
    
    // Tìm node theo đường dẫn
    private Node findNode(String path) {
        if (path.equals("/")) return root;
        
        Node current = root;
        for (String name : parsePath(path)) {
            if (name.isEmpty()) continue;
            current = current.getChild(name);
            if (current == null) return null;
        }
        return current;
    }
    
    // Tạo thư mục mới
    public void createDirectory(String path) {
        String[] parts = parsePath(path);
        if (parts.length == 0) throw new IllegalArgumentException("Invalid path");
        
        // Tìm thư mục cha
        Node parentNode = root;
        StringBuilder currentPath = new StringBuilder();
        
        for (int i = 0; i < parts.length - 1; i++) {
            if (parts[i].isEmpty()) continue;
            currentPath.append("/").append(parts[i]);
            Node next = parentNode.getChild(parts[i]);
            
            if (next == null) {
                throw new IllegalArgumentException(
                    "Parent directory doesn't exist: " + currentPath);
            }
            if (!next.isDirectory()) {
                throw new IllegalArgumentException(
                    "Not a directory: " + currentPath);
            }
            parentNode = next;
        }
        
        // Kiểm tra thư mục đích
        final String dirName = parts[parts.length - 1];
        if (parentNode.getChild(dirName) != null) {
            throw new IllegalArgumentException("Already exists: " + path);
        }
        
        // Tạo lệnh thêm thư mục mới
        final Node parent = parentNode;
        Command cmd = new Command() {
            final Node newDir = new Node(dirName, true);
            
            @Override
            public void execute() {
                parent.addChild(newDir);
            }
            
            @Override
            public void undo() {
                parent.removeChild(dirName);
            }
        };
        
        executeCommand(cmd);
    }
    
    // Tạo file mới
    public void createFile(String path, byte[] content) {
        String[] parts = parsePath(path);
        if (parts.length == 0) throw new IllegalArgumentException("Invalid path");
        
        Node parent = findNode("/" + String.join("/",
            Arrays.copyOfRange(parts, 0, parts.length - 1)));
            
        if (parent == null || !parent.isDirectory()) {
            throw new IllegalArgumentException("Invalid parent directory");
        }
        
        final String fileName = parts[parts.length - 1];
        if (parent.getChild(fileName) != null) {
            throw new IllegalArgumentException("File already exists");
        }
        
        Command cmd = new Command() {
            final Node parentNode = parent;
            final Node newFile = new Node(fileName, false);
            
            @Override
            public void execute() {
                newFile.setContent(content);
                parentNode.addChild(newFile);
            }
            
            @Override
            public void undo() {
                parentNode.removeChild(fileName);
            }
        };
        
        executeCommand(cmd);
    }
    
    // Đọc nội dung file
    public byte[] readFile(String path) {
        Node node = findNode(path);
        if (node == null || node.isDirectory()) {
            throw new IllegalArgumentException("File not found: " + path);
        }
        return node.getContent();
    }
    
    // Xóa file hoặc thư mục
    public void delete(String path) {
        Node node = findNode(path);
        if (node == null) {
            throw new IllegalArgumentException("Path not found: " + path);
        }
        if (node == root) {
            throw new IllegalArgumentException("Cannot delete root");
        }
        
        Command cmd = new Command() {
            final Node parent = node.getParent();
            final String name = node.getName();
            final Node deletedNode = node;
            
            @Override
            public void execute() {
                parent.removeChild(name);
            }
            
            @Override
            public void undo() {
                parent.addChild(deletedNode);
            }
        };
        
        executeCommand(cmd);
    }
    
    // Liệt kê nội dung thư mục
    public List<String> list(String path) {
        Node node = findNode(path);
        if (node == null || !node.isDirectory()) {
            throw new IllegalArgumentException("Directory not found: " + path);
        }
        
        List<String> result = new ArrayList<>();
        for (Node child : node.getChildren()) {
            result.add(child.getName() + (child.isDirectory() ? "/" : ""));
        }
        Collections.sort(result);
        return result;
    }
    
    // Di chuyển file/thư mục
    public void move(String source, String dest) {
        Node sourceNode = findNode(source);
        if (sourceNode == null) {
            throw new IllegalArgumentException("Source not found: " + source);
        }
        if (sourceNode == root) {
            throw new IllegalArgumentException("Cannot move root");
        }
        
        final Node destParent = findNode(dest);
        if (destParent == null || !destParent.isDirectory()) {
            throw new IllegalArgumentException("Invalid destination: " + dest);
        }
        
        Command cmd = new Command() {
            final Node sourceParent = sourceNode.getParent();
            final String sourceName = sourceNode.getName();
            final Node movedNode = sourceNode;
            
            @Override
            public void execute() {
                sourceParent.removeChild(sourceName);
                destParent.addChild(movedNode);
            }
            
            @Override
            public void undo() {
                destParent.removeChild(sourceName);
                sourceParent.addChild(movedNode);
            }
        };
        
        executeCommand(cmd);
    }
    
    // Thực thi lệnh với undo/redo
    private void executeCommand(Command cmd) {
        cmd.execute();
        undoStack.push(cmd);
        redoStack.clear();
    }
    
    // Hoàn tác lệnh cuối cùng
    public void undo() {
        if (undoStack.isEmpty()) {
            throw new IllegalStateException("Nothing to undo");
        }
        Command cmd = undoStack.pop();
        cmd.undo();
        redoStack.push(cmd);
    }
    
    // Làm lại lệnh đã hoàn tác
    public void redo() {
        if (redoStack.isEmpty()) {
            throw new IllegalStateException("Nothing to redo");
        }
        Command cmd = redoStack.pop();
        cmd.execute();
        undoStack.push(cmd);
    }
    
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        
        // 1. Tạo cấu trúc thư mục
        System.out.println("1. Tạo cấu trúc thư mục:");
        fs.createDirectory("/documents");
        fs.createDirectory("/documents/work");
        fs.createDirectory("/documents/personal");
        System.out.println("Root content: " + fs.list("/"));
        System.out.println("Documents content: " + fs.list("/documents"));
        
        // 2. Tạo và đọc file
        System.out.println("\n2. Tạo và đọc file:");
        fs.createFile("/documents/work/report.txt", 
                     "Quarterly Report".getBytes());
        System.out.println("Work content: " + fs.list("/documents/work"));
        System.out.println("File content: " + 
                          new String(fs.readFile("/documents/work/report.txt")));
        
        // 3. Di chuyển file
        System.out.println("\n3. Di chuyển file:");
        fs.move("/documents/work/report.txt", "/documents/personal");
        System.out.println("Work content after move: " + 
                          fs.list("/documents/work"));
        System.out.println("Personal content after move: " + 
                          fs.list("/documents/personal"));
        
        // 4. Test undo/redo
        System.out.println("\n4. Test undo/redo:");
        fs.undo();  // Undo move
        System.out.println("After undo - Work content: " + 
                          fs.list("/documents/work"));
        System.out.println("After undo - Personal content: " + 
                          fs.list("/documents/personal"));
        
        fs.redo();  // Redo move
        System.out.println("After redo - Work content: " + 
                          fs.list("/documents/work"));
        System.out.println("After redo - Personal content: " + 
                          fs.list("/documents/personal"));
        
        // 5. Xóa thư mục
        System.out.println("\n5. Xóa thư mục:");
        fs.delete("/documents/work");
        System.out.println("Documents content after delete: " + 
                          fs.list("/documents"));
        
        fs.undo();  // Undo delete
        System.out.println("Documents content after undo delete: " + 
                          fs.list("/documents"));
    }
}