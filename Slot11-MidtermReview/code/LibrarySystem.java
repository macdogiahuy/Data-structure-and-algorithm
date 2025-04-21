/**
 * Hệ thống Quản lý Thư viện tích hợp nhiều cấu trúc dữ liệu và thuật toán
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LibrarySystem {
    
    // Lớp Book - Thông tin về sách
    static class Book {
        private String id;
        private String title;
        private String author;
        private String category;
        private int quantity;
        private int available;
        
        public Book(String id, String title, String author, String category, int quantity) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.category = category;
            this.quantity = quantity;
            this.available = quantity;
        }
        
        // Getters và Setters
        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getCategory() { return category; }
        public int getQuantity() { return quantity; }
        public int getAvailable() { return available; }
        
        public void setAvailable(int available) {
            this.available = available;
        }
        
        @Override
        public String toString() {
            return String.format("Book[ID: %s, Title: %s, Author: %s, Category: %s, Available: %d/%d]",
                               id, title, author, category, available, quantity);
        }
    }
    
    // Lớp Reader - Thông tin về độc giả
    static class Reader {
        private String id;
        private String name;
        private List<BorrowRecord> borrowHistory;
        
        public Reader(String id, String name) {
            this.id = id;
            this.name = name;
            this.borrowHistory = new ArrayList<>();
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        public List<BorrowRecord> getBorrowHistory() { return borrowHistory; }
        
        @Override
        public String toString() {
            return String.format("Reader[ID: %s, Name: %s]", id, name);
        }
    }
    
    // Lớp BorrowRecord - Thông tin mượn/trả sách
    static class BorrowRecord {
        private String id;
        private String readerId;
        private String bookId;
        private Date borrowDate;
        private Date dueDate;
        private Date returnDate;
        
        public BorrowRecord(String id, String readerId, String bookId) {
            this.id = id;
            this.readerId = readerId;
            this.bookId = bookId;
            this.borrowDate = new Date();
            // Thời hạn mượn 14 ngày
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(borrowDate);
            calendar.add(Calendar.DAY_OF_MONTH, 14);
            this.dueDate = calendar.getTime();
        }
        
        public void returnBook() {
            this.returnDate = new Date();
        }
        
        public boolean isOverdue() {
            if (returnDate != null) return false;
            return new Date().after(dueDate);
        }
        
        public String getId() { return id; }
        public String getReaderId() { return readerId; }
        public String getBookId() { return bookId; }
        public Date getBorrowDate() { return borrowDate; }
        public Date getDueDate() { return dueDate; }
        public Date getReturnDate() { return returnDate; }
    }
    
    // Quản lý thư viện
    static class LibraryManager {
        private Map<String, Book> books;        // ID -> Book
        private Map<String, Reader> readers;    // ID -> Reader
        private List<BorrowRecord> records;     // Lịch sử mượn/trả
        private Map<String, Set<String>> categoryBooks;  // Category -> Set of Book IDs
        private Map<String, Integer> bookPopularity;    // Book ID -> Số lần mượn
        
        public LibraryManager() {
            books = new HashMap<>();
            readers = new HashMap<>();
            records = new ArrayList<>();
            categoryBooks = new HashMap<>();
            bookPopularity = new HashMap<>();
        }
        
        // Thêm sách mới
        public void addBook(Book book) {
            books.put(book.getId(), book);
            
            // Cập nhật index theo category
            categoryBooks.computeIfAbsent(book.getCategory(), k -> new HashSet<>())
                       .add(book.getId());
                       
            System.out.println("Đã thêm sách: " + book.getTitle());
        }
        
        // Thêm độc giả
        public void addReader(Reader reader) {
            readers.put(reader.getId(), reader);
            System.out.println("Đã thêm độc giả: " + reader.getName());
        }
        
        // Mượn sách
        public void borrowBook(String readerId, String bookId) {
            Reader reader = readers.get(readerId);
            Book book = books.get(bookId);
            
            if (reader == null || book == null) {
                throw new IllegalArgumentException("Độc giả hoặc sách không tồn tại");
            }
            
            if (book.getAvailable() <= 0) {
                throw new IllegalStateException("Sách không còn để mượn");
            }
            
            // Tạo bản ghi mượn sách
            String recordId = String.format("BR%d", records.size() + 1);
            BorrowRecord record = new BorrowRecord(recordId, readerId, bookId);
            records.add(record);
            reader.getBorrowHistory().add(record);
            
            // Cập nhật số lượng sách
            book.setAvailable(book.getAvailable() - 1);
            
            // Cập nhật độ phổ biến
            bookPopularity.merge(bookId, 1, Integer::sum);
            
            System.out.printf("Độc giả %s đã mượn sách %s\n", reader.getName(), book.getTitle());
        }
        
        // Trả sách
        public void returnBook(String readerId, String bookId) {
            Reader reader = readers.get(readerId);
            Book book = books.get(bookId);
            
            if (reader == null || book == null) {
                throw new IllegalArgumentException("Độc giả hoặc sách không tồn tại");
            }
            
            // Tìm bản ghi mượn sách chưa trả
            Optional<BorrowRecord> record = reader.getBorrowHistory().stream()
                .filter(r -> r.getBookId().equals(bookId) && r.getReturnDate() == null)
                .findFirst();
                
            if (!record.isPresent()) {
                throw new IllegalStateException("Không tìm thấy bản ghi mượn sách");
            }
            
            record.get().returnBook();
            book.setAvailable(book.getAvailable() + 1);
            
            System.out.printf("Độc giả %s đã trả sách %s\n", reader.getName(), book.getTitle());
        }
        
        // Tìm sách theo từ khóa
        public List<Book> searchBooks(String keyword) {
            keyword = keyword.toLowerCase();
            List<Book> result = new ArrayList<>();
            
            for (Book book : books.values()) {
                if (book.getTitle().toLowerCase().contains(keyword) ||
                    book.getAuthor().toLowerCase().contains(keyword)) {
                    result.add(book);
                }
            }
            
            return result;
        }
        
        // Lấy sách theo thể loại
        public List<Book> getBooksByCategory(String category) {
            Set<String> bookIds = categoryBooks.getOrDefault(category, new HashSet<>());
            List<Book> result = new ArrayList<>();
            
            for (String id : bookIds) {
                result.add(books.get(id));
            }
            
            return result;
        }
        
        // Lấy danh sách sách quá hạn
        public List<BorrowRecord> getOverdueBooks() {
            return records.stream()
                .filter(BorrowRecord::isOverdue)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        }
        
        // Gợi ý sách dựa trên độ phổ biến
        public List<Book> getPopularBooks(int limit) {
            return bookPopularity.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(limit)
                .map(e -> books.get(e.getKey()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        }
    }
    
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();
        
        // Thêm sách
        System.out.println("1. Thêm sách mới:");
        library.addBook(new Book("B001", "Java Programming", "John Smith", "Programming", 3));
        library.addBook(new Book("B002", "Data Structures", "Jane Doe", "Programming", 2));
        library.addBook(new Book("B003", "Machine Learning", "Bob Wilson", "AI", 1));
        
        // Thêm độc giả
        System.out.println("\n2. Thêm độc giả:");
        library.addReader(new Reader("R001", "Alice Johnson"));
        library.addReader(new Reader("R002", "Bob Brown"));
        
        // Mượn sách
        System.out.println("\n3. Mượn sách:");
        library.borrowBook("R001", "B001");
        library.borrowBook("R002", "B001");
        
        // Tìm kiếm sách
        System.out.println("\n4. Tìm kiếm sách với từ khóa 'Java':");
        List<Book> searchResults = library.searchBooks("Java");
        searchResults.forEach(System.out::println);
        
        // Lấy sách theo thể loại
        System.out.println("\n5. Sách trong thể loại 'Programming':");
        List<Book> programmingBooks = library.getBooksByCategory("Programming");
        programmingBooks.forEach(System.out::println);
        
        // Trả sách
        System.out.println("\n6. Trả sách:");
        library.returnBook("R001", "B001");
        
        // Kiểm tra sách quá hạn
        System.out.println("\n7. Sách quá hạn:");
        List<BorrowRecord> overdueBooks = library.getOverdueBooks();
        overdueBooks.forEach(record -> 
            System.out.println("Book ID: " + record.getBookId() + 
                             ", Reader ID: " + record.getReaderId()));
        
        // Sách phổ biến
        System.out.println("\n8. Top 3 sách phổ biến nhất:");
        List<Book> popularBooks = library.getPopularBooks(3);
        popularBooks.forEach(System.out::println);
    }
}