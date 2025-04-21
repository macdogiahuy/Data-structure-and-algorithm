/**
 * Minh họa ứng dụng Hash Table vào các bài toán thực tế
 */
import java.util.*;

public class HashTablePractice {
    
    // Bài 1: Tìm các phần tử trùng lặp trong mảng
    public static ArrayList<Integer> findDuplicates(int[] arr) {
        // Sử dụng HashMap để đếm số lần xuất hiện
        HashMap<Integer, Integer> countMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        
        // Đếm số lần xuất hiện của mỗi phần tử
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        // Thêm các phần tử xuất hiện nhiều hơn 1 lần vào kết quả
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        
        return result;
    }
    
    // Bài 2: Nhóm các từ anagram
    public static ArrayList<ArrayList<String>> groupAnagrams(String[] words) {
        // Sử dụng HashMap để nhóm các từ có cùng dạng anagram
        HashMap<String, ArrayList<String>> anagramMap = new HashMap<>();
        
        for (String word : words) {
            // Tạo key bằng cách sắp xếp các ký tự của từ
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            // Thêm từ vào nhóm anagram tương ứng
            if (!anagramMap.containsKey(key)) {
                anagramMap.put(key, new ArrayList<>());
            }
            anagramMap.get(key).add(word);
        }
        
        // Chuyển các nhóm vào ArrayList kết quả
        return new ArrayList<>(anagramMap.values());
    }
    
    // Bài 3: Tìm cặp số có tổng cho trước
    public static int[] findPairWithSum(int[] arr, int targetSum) {
        // Sử dụng HashSet để lưu các số đã xét
        HashSet<Integer> seen = new HashSet<>();
        
        for (int num : arr) {
            int complement = targetSum - num;
            if (seen.contains(complement)) {
                return new int[]{complement, num};
            }
            seen.add(num);
        }
        
        return null;
    }
    
    // Bài 4: Kiểm tra hoán vị
    public static boolean isPermutation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        // Sử dụng HashMap để đếm ký tự
        HashMap<Character, Integer> charCount = new HashMap<>();
        
        // Đếm ký tự trong str1
        for (char c : str1.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Kiểm tra str2
        for (char c : str2.toCharArray()) {
            if (!charCount.containsKey(c)) {
                return false;
            }
            charCount.put(c, charCount.get(c) - 1);
            if (charCount.get(c) == 0) {
                charCount.remove(c);
            }
        }
        
        return charCount.isEmpty();
    }
    
    public static void main(String[] args) {
        // 1. Test tìm phần tử trùng lặp
        System.out.println("1. Tìm phần tử trùng lặp:");
        int[] arr = {1, 2, 3, 1, 3, 6, 6};
        ArrayList<Integer> duplicates = findDuplicates(arr);
        System.out.println("Các phần tử trùng lặp: " + duplicates);
        
        // 2. Test nhóm anagram
        System.out.println("\n2. Nhóm các từ anagram:");
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        ArrayList<ArrayList<String>> anagramGroups = groupAnagrams(words);
        System.out.println("Các nhóm anagram:");
        for (ArrayList<String> group : anagramGroups) {
            System.out.println(group);
        }
        
        // 3. Test tìm cặp số có tổng cho trước
        System.out.println("\n3. Tìm cặp số có tổng cho trước:");
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] pair = findPairWithSum(numbers, target);
        if (pair != null) {
            System.out.println("Cặp số có tổng " + target + ": " + 
                             pair[0] + " và " + pair[1]);
        }
        
        // 4. Test kiểm tra hoán vị
        System.out.println("\n4. Kiểm tra hoán vị:");
        String str1 = "hello";
        String str2 = "olleh";
        System.out.println("'" + str1 + "' và '" + str2 + "' là hoán vị? " + 
                          isPermutation(str1, str2));
    }
}