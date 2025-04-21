/**
 * Hệ thống xử lý giao dịch kết hợp nhiều cấu trúc dữ liệu
 */
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TransactionSystem {
    
    // Lớp Transaction với các thông tin giao dịch
    static class Transaction {
        private String id;
        private double amount;
        private long timestamp;
        private String status;
        
        public Transaction(String id, double amount, long timestamp) {
            this.id = id;
            this.amount = amount;
            this.timestamp = timestamp;
            this.status = "PENDING";
        }
        
        // Getters
        public String getId() { return id; }
        public double getAmount() { return amount; }
        public long getTimestamp() { return timestamp; }
        public String getStatus() { return status; }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        @Override
        public String toString() {
            return String.format("Transaction[id=%s, amount=%.2f, time=%d, status=%s]",
                               id, amount, timestamp, status);
        }
    }
    
    // Cấu trúc TreeMap để lưu trữ giao dịch theo thời gian
    private TreeMap<Long, List<Transaction>> timeIndex;
    
    // HashMap để tìm kiếm nhanh theo ID
    private ConcurrentHashMap<String, Transaction> idIndex;
    
    // PriorityQueue để duy trì top K giao dịch
    private PriorityQueue<Transaction> topTransactions;
    
    // Lock để đồng bộ hóa các thao tác
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    public TransactionSystem() {
        timeIndex = new TreeMap<>();
        idIndex = new ConcurrentHashMap<>();
        topTransactions = new PriorityQueue<>((t1, t2) -> 
            Double.compare(t2.getAmount(), t1.getAmount()));
    }
    
    // Thêm giao dịch mới
    public void addTransaction(Transaction t) {
        lock.writeLock().lock();
        try {
            // Thêm vào các index
            idIndex.put(t.getId(), t);
            timeIndex.computeIfAbsent(t.getTimestamp(), k -> new ArrayList<>())
                    .add(t);
            topTransactions.offer(t);
            
            // Cập nhật trạng thái
            t.setStatus("COMPLETED");
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    // Tìm giao dịch theo ID
    public Transaction getTransaction(String id) {
        return idIndex.get(id);
    }
    
    // Lấy các giao dịch trong 24h gần nhất
    public List<Transaction> getLast24Hours() {
        lock.readLock().lock();
        try {
            long now = System.currentTimeMillis();
            long oneDayAgo = now - 24 * 60 * 60 * 1000;
            
            List<Transaction> result = new ArrayList<>();
            for (Map.Entry<Long, List<Transaction>> entry : 
                 timeIndex.tailMap(oneDayAgo).entrySet()) {
                if (entry.getKey() <= now) {
                    result.addAll(entry.getValue());
                }
            }
            return result;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    // Tính tổng giá trị giao dịch trong khoảng thời gian
    public double getTotalAmount(long start, long end) {
        lock.readLock().lock();
        try {
            double total = 0;
            for (Map.Entry<Long, List<Transaction>> entry : 
                 timeIndex.subMap(start, true, end, true).entrySet()) {
                for (Transaction t : entry.getValue()) {
                    total += t.getAmount();
                }
            }
            return total;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    // Lấy K giao dịch có giá trị lớn nhất
    public List<Transaction> getTopK(int k) {
        lock.readLock().lock();
        try {
            List<Transaction> result = new ArrayList<>();
            PriorityQueue<Transaction> pq = new PriorityQueue<>(topTransactions);
            
            for (int i = 0; i < k && !pq.isEmpty(); i++) {
                result.add(pq.poll());
            }
            
            return result;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    // In thống kê hệ thống
    public void printStats() {
        lock.readLock().lock();
        try {
            System.out.println("\nThống kê hệ thống:");
            System.out.println("Tổng số giao dịch: " + idIndex.size());
            System.out.println("Giao dịch trong 24h: " + getLast24Hours().size());
            
            if (!timeIndex.isEmpty()) {
                System.out.println("Giao dịch cũ nhất: " + 
                                 timeIndex.firstEntry().getValue().get(0));
                System.out.println("Giao dịch mới nhất: " + 
                                 timeIndex.lastEntry().getValue().get(0));
            }
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        TransactionSystem system = new TransactionSystem();
        
        // 1. Thêm một số giao dịch
        System.out.println("1. Thêm các giao dịch:");
        long now = System.currentTimeMillis();
        
        system.addTransaction(new Transaction("T1", 1000, now - 25*60*60*1000));
        system.addTransaction(new Transaction("T2", 2000, now - 12*60*60*1000));
        system.addTransaction(new Transaction("T3", 3000, now - 6*60*60*1000));
        system.addTransaction(new Transaction("T4", 4000, now - 1*60*60*1000));
        system.addTransaction(new Transaction("T5", 5000, now));
        
        // 2. Tìm kiếm theo ID
        System.out.println("\n2. Tìm kiếm giao dịch:");
        Transaction t = system.getTransaction("T3");
        System.out.println("Giao dịch T3: " + t);
        
        // 3. Lấy giao dịch 24h gần đây
        System.out.println("\n3. Giao dịch 24h gần đây:");
        List<Transaction> recent = system.getLast24Hours();
        recent.forEach(System.out::println);
        
        // 4. Tính tổng giá trị trong khoảng thời gian
        System.out.println("\n4. Tổng giá trị giao dịch 12h qua:");
        double total = system.getTotalAmount(now - 12*60*60*1000, now);
        System.out.println("Tổng: " + total);
        
        // 5. Top 3 giao dịch lớn nhất
        System.out.println("\n5. Top 3 giao dịch lớn nhất:");
        List<Transaction> topK = system.getTopK(3);
        topK.forEach(System.out::println);
        
        // 6. In thống kê
        system.printStats();
        
        // 7. Test concurrent access
        System.out.println("\n7. Test concurrent access:");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Thêm giao dịch concurrent
        for (int i = 6; i <= 8; i++) {
            final int index = i;  // Create a final copy for the lambda
            executor.submit(() -> {
                String id = "T" + index;
                system.addTransaction(new Transaction(id, 1000 * index, now));
                System.out.println("Đã thêm giao dịch " + id);
            });
        }
        
        // Tìm kiếm concurrent
        for (int i = 1; i <= 3; i++) {
            final int index = i;  // Create a final copy for the lambda
            executor.submit(() -> {
                String id = "T" + index;
                Transaction found = system.getTransaction(id);
                System.out.println("Tìm thấy giao dịch " + found);
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        
        // In thống kê cuối cùng
        system.printStats();
    }
}