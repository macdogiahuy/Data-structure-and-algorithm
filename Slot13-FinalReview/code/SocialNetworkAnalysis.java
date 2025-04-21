/**
 * Hệ thống phân tích mạng xã hội sử dụng đồ thị và các thuật toán tìm đường
 */
import java.util.*;

public class SocialNetworkAnalysis {
    
    // Cấu trúc đồ thị sử dụng danh sách kề
    private Map<String, Set<String>> graph;
    
    // Cache cho kết quả tính toán
    private Map<String, Map<String, Integer>> distanceCache;
    private Map<String, List<String>> friendSuggestionCache;
    
    public SocialNetworkAnalysis() {
        graph = new HashMap<>();
        distanceCache = new HashMap<>();
        friendSuggestionCache = new HashMap<>();
    }
    
    // Thêm người dùng mới
    public void addUser(String userId) {
        if (!graph.containsKey(userId)) {
            graph.put(userId, new HashSet<>());
            System.out.println("Đã thêm người dùng: " + userId);
        }
    }
    
    // Thêm kết nối bạn bè
    public void addFriendship(String user1, String user2) {
        // Kiểm tra người dùng tồn tại
        if (!graph.containsKey(user1) || !graph.containsKey(user2)) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }
        
        // Thêm kết nối hai chiều
        graph.get(user1).add(user2);
        graph.get(user2).add(user1);
        
        // Xóa cache khi có thay đổi
        clearCache(user1);
        clearCache(user2);
        
        System.out.println("Đã thêm kết nối giữa " + user1 + " và " + user2);
    }
    
    // Lấy danh sách bạn bè
    public List<String> getFriends(String userId) {
        if (!graph.containsKey(userId)) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }
        return new ArrayList<>(graph.get(userId));
    }
    
    // Tìm bạn chung giữa hai người dùng
    public List<String> getMutualFriends(String user1, String user2) {
        if (!graph.containsKey(user1) || !graph.containsKey(user2)) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }
        
        List<String> mutualFriends = new ArrayList<>();
        Set<String> friends1 = graph.get(user1);
        Set<String> friends2 = graph.get(user2);
        
        for (String friend : friends1) {
            if (friends2.contains(friend)) {
                mutualFriends.add(friend);
            }
        }
        
        return mutualFriends;
    }
    
    // Tính khoảng cách kết nối giữa hai người dùng (BFS)
    public int getDistance(String user1, String user2) {
        // Kiểm tra cache
        if (distanceCache.containsKey(user1) && 
            distanceCache.get(user1).containsKey(user2)) {
            return distanceCache.get(user1).get(user2);
        }
        
        if (!graph.containsKey(user1) || !graph.containsKey(user2)) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }
        
        // Sử dụng BFS để tìm đường đi ngắn nhất
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> distances = new HashMap<>();
        
        queue.offer(user1);
        distances.put(user1, 0);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distances.get(current);
            
            if (current.equals(user2)) {
                // Lưu kết quả vào cache
                distanceCache.computeIfAbsent(user1, k -> new HashMap<>())
                           .put(user2, currentDist);
                return currentDist;
            }
            
            for (String friend : graph.get(current)) {
                if (!distances.containsKey(friend)) {
                    distances.put(friend, currentDist + 1);
                    queue.offer(friend);
                }
            }
        }
        
        return -1; // Không tìm thấy đường đi
    }
    
    // Gợi ý kết bạn dựa trên bạn chung và khoảng cách
    public List<String> suggestFriends(String userId) {
        // Kiểm tra cache
        if (friendSuggestionCache.containsKey(userId)) {
            return new ArrayList<>(friendSuggestionCache.get(userId));
        }
        
        if (!graph.containsKey(userId)) {
            throw new IllegalArgumentException("Người dùng không tồn tại");
        }
        
        // Map lưu điểm số của mỗi người dùng tiềm năng
        Map<String, Double> scores = new HashMap<>();
        Set<String> friends = graph.get(userId);
        
        // Duyệt qua bạn của bạn
        for (String friend : friends) {
            for (String friendOfFriend : graph.get(friend)) {
                if (!friendOfFriend.equals(userId) && !friends.contains(friendOfFriend)) {
                    // Tính điểm dựa trên số bạn chung và khoảng cách
                    int mutualCount = getMutualFriends(userId, friendOfFriend).size();
                    int distance = getDistance(userId, friendOfFriend);
                    
                    double score = mutualCount * 0.7 + (1.0 / distance) * 0.3;
                    scores.merge(friendOfFriend, score, Double::sum);
                }
            }
        }
        
        // Sắp xếp theo điểm số và lấy top 10
        List<String> suggestions = scores.entrySet().stream()
            .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
            .limit(10)
            .map(Map.Entry::getKey)
            .toList();
        
        // Lưu vào cache
        friendSuggestionCache.put(userId, suggestions);
        
        return suggestions;
    }
    
    // Xóa cache khi có thay đổi trong đồ thị
    private void clearCache(String userId) {
        distanceCache.remove(userId);
        friendSuggestionCache.remove(userId);
    }
    
    public static void main(String[] args) {
        SocialNetworkAnalysis network = new SocialNetworkAnalysis();
        
        // 1. Thêm người dùng
        System.out.println("1. Thêm người dùng:");
        String[] users = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank"};
        for (String user : users) {
            network.addUser(user);
        }
        
        // 2. Thêm kết nối
        System.out.println("\n2. Thêm kết nối bạn bè:");
        network.addFriendship("Alice", "Bob");
        network.addFriendship("Bob", "Charlie");
        network.addFriendship("Charlie", "David");
        network.addFriendship("David", "Eve");
        network.addFriendship("Eve", "Frank");
        network.addFriendship("Alice", "Charlie");
        network.addFriendship("Bob", "Eve");
        
        // 3. Kiểm tra danh sách bạn bè
        System.out.println("\n3. Danh sách bạn bè:");
        System.out.println("Bạn bè của Alice: " + network.getFriends("Alice"));
        System.out.println("Bạn bè của Bob: " + network.getFriends("Bob"));
        
        // 4. Tìm bạn chung
        System.out.println("\n4. Bạn chung:");
        System.out.println("Bạn chung giữa Alice và Bob: " + 
                          network.getMutualFriends("Alice", "Bob"));
        
        // 5. Tính khoảng cách kết nối
        System.out.println("\n5. Khoảng cách kết nối:");
        System.out.println("Khoảng cách Alice - Frank: " + 
                          network.getDistance("Alice", "Frank"));
        
        // 6. Gợi ý kết bạn
        System.out.println("\n6. Gợi ý kết bạn:");
        System.out.println("Gợi ý cho Alice: " + network.suggestFriends("Alice"));
        System.out.println("Gợi ý cho Frank: " + network.suggestFriends("Frank"));
        
        // 7. Kiểm tra cache
        System.out.println("\n7. Kiểm tra hiệu quả cache:");
        long start = System.nanoTime();
        network.getDistance("Alice", "Frank");
        System.out.println("Lần đầu: " + (System.nanoTime() - start) / 1000 + " microseconds");
        
        start = System.nanoTime();
        network.getDistance("Alice", "Frank");
        System.out.println("Lần sau (có cache): " + (System.nanoTime() - start) / 1000 + " microseconds");
    }
}