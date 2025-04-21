/**
 * Minh họa cài đặt từ điển đơn giản với tính năng gợi ý từ
 */
import java.io.*;
import java.util.*;

public class SimpleDictionary {
    
    // Cấu trúc node Trie cho tính năng gợi ý từ
    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        String meaning;  // Nghĩa của từ nếu là node cuối
        
        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
            meaning = null;
        }
    }
    
    // Lớp từ điển chính
    static class Dictionary {
        private TrieNode root;
        private Map<String, String> wordMap; // Để tìm kiếm nhanh
        
        public Dictionary() {
            root = new TrieNode();
            wordMap = new HashMap<>();
        }
        
        // Thêm từ mới
        public void addWord(String word, String meaning) {
            // Thêm vào Map
            wordMap.put(word.toLowerCase(), meaning);
            
            // Thêm vào Trie
            TrieNode current = root;
            for (char c : word.toLowerCase().toCharArray()) {
                current.children.putIfAbsent(c, new TrieNode());
                current = current.children.get(c);
            }
            current.isEndOfWord = true;
            current.meaning = meaning;
            
            System.out.println("Đã thêm từ: " + word);
        }
        
        // Xóa từ
        public void removeWord(String word) {
            word = word.toLowerCase();
            if (wordMap.remove(word) != null) {
                // Xóa khỏi Trie (chỉ đánh dấu không phải từ kết thúc)
                TrieNode current = root;
                for (char c : word.toCharArray()) {
                    if (!current.children.containsKey(c)) {
                        return;
                    }
                    current = current.children.get(c);
                }
                current.isEndOfWord = false;
                current.meaning = null;
                
                System.out.println("Đã xóa từ: " + word);
            } else {
                System.out.println("Không tìm thấy từ: " + word);
            }
        }
        
        // Tìm nghĩa của từ
        public String findMeaning(String word) {
            return wordMap.get(word.toLowerCase());
        }
        
        // Lấy danh sách gợi ý từ với prefix cho trước
        public List<String> getSuggestions(String prefix) {
            List<String> suggestions = new ArrayList<>();
            prefix = prefix.toLowerCase();
            
            // Tìm node cuối của prefix
            TrieNode current = root;
            for (char c : prefix.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return suggestions;
                }
                current = current.children.get(c);
            }
            
            // Tìm tất cả từ có prefix này
            findWordsFromNode(current, prefix, suggestions);
            return suggestions;
        }
        
        private void findWordsFromNode(TrieNode node, String prefix, List<String> suggestions) {
            if (node.isEndOfWord) {
                suggestions.add(prefix);
            }
            
            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                findWordsFromNode(entry.getValue(), prefix + entry.getKey(), suggestions);
            }
        }
        
        // Lưu từ điển vào file
        public void saveToFile(String filename) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                for (Map.Entry<String, String> entry : wordMap.entrySet()) {
                    writer.println(entry.getKey() + "," + entry.getValue());
                }
                System.out.println("Đã lưu từ điển vào file: " + filename);
            } catch (IOException e) {
                System.out.println("Lỗi khi lưu file: " + e.getMessage());
            }
        }
        
        // Tải từ điển từ file
        public void loadFromFile(String filename) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", 2);
                    if (parts.length == 2) {
                        addWord(parts[0], parts[1]);
                    }
                }
                System.out.println("Đã tải từ điển từ file: " + filename);
            } catch (IOException e) {
                System.out.println("Lỗi khi đọc file: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        
        // 1. Thêm một số từ
        System.out.println("1. Thêm từ vào từ điển:");
        dictionary.addWord("Hello", "Xin chào");
        dictionary.addWord("World", "Thế giới");
        dictionary.addWord("Help", "Giúp đỡ");
        dictionary.addWord("Happy", "Hạnh phúc");
        dictionary.addWord("Health", "Sức khỏe");
        
        // 2. Tìm nghĩa của từ
        System.out.println("\n2. Tìm nghĩa của từ:");
        String word = "Hello";
        String meaning = dictionary.findMeaning(word);
        System.out.println("Nghĩa của '" + word + "': " + meaning);
        
        // 3. Gợi ý từ
        System.out.println("\n3. Gợi ý từ với prefix 'He':");
        List<String> suggestions = dictionary.getSuggestions("He");
        System.out.println("Các từ gợi ý: " + suggestions);
        
        // 4. Lưu từ điển vào file
        System.out.println("\n4. Lưu và tải từ điển:");
        dictionary.saveToFile("dictionary.txt");
        
        // 5. Xóa một từ
        System.out.println("\n5. Xóa từ:");
        dictionary.removeWord("World");
        
        // 6. Tạo từ điển mới và tải từ file
        Dictionary newDictionary = new Dictionary();
        newDictionary.loadFromFile("dictionary.txt");
        
        // 7. Kiểm tra từ đã tải
        System.out.println("\n7. Kiểm tra từ điển đã tải:");
        System.out.println("Nghĩa của 'Hello': " + newDictionary.findMeaning("Hello"));
        System.out.println("Nghĩa của 'Health': " + newDictionary.findMeaning("Health"));
    }
}