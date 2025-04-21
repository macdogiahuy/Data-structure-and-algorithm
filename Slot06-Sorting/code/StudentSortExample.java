/**
 * Minh họa sắp xếp đối tượng và sử dụng Comparator trong Java
 */
import java.util.*;

public class StudentSortExample {
    // Lớp Student với các thuộc tính cơ bản
    static class Student implements Comparable<Student> {
        private String name;
        private double score;
        private int age;
        
        public Student(String name, double score, int age) {
            this.name = name;
            this.score = score;
            this.age = age;
        }
        
        // Getter methods
        public String getName() { return name; }
        public double getScore() { return score; }
        public int getAge() { return age; }
        
        // Phương thức toString để in thông tin sinh viên
        @Override
        public String toString() {
            return String.format("Tên: %-20s | Điểm: %4.1f | Tuổi: %d", name, score, age);
        }
        
        // Comparable interface implementation - Sắp xếp mặc định theo điểm
        @Override
        public int compareTo(Student other) {
            return Double.compare(this.score, other.score);
        }
    }
    
    // Các Comparator tùy chỉnh
    static class NameComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getName().compareTo(s2.getName());
        }
    }
    
    static class AgeComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return Integer.compare(s1.getAge(), s2.getAge());
        }
    }
    
    // Sắp xếp mảng Student sử dụng thuật toán Insertion Sort
    public static void insertionSort(Student[] arr, Comparator<Student> comparator) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Student key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    
    // In danh sách sinh viên
    public static void printStudents(Student[] students, String message) {
        System.out.println("\n" + message);
        for (Student s : students) {
            System.out.println(s);
        }
    }
    
    public static void main(String[] args) {
        // Tạo danh sách sinh viên mẫu
        Student[] students = {
            new Student("Nguyen Van A", 8.5, 20),
            new Student("Tran Thi B", 7.5, 19),
            new Student("Le Van C", 9.0, 21),
            new Student("Pham Thi D", 8.0, 20),
            new Student("Hoang Van E", 7.0, 22)
        };
        
        // 1. Sắp xếp theo điểm số (sử dụng Comparable)
        System.out.println("1. Sắp xếp theo điểm số:");
        Student[] scoreSort = students.clone();
        Arrays.sort(scoreSort);
        printStudents(scoreSort, "Sau khi sắp xếp theo điểm:");
        
        // 2. Sắp xếp theo tên (sử dụng NameComparator)
        System.out.println("\n2. Sắp xếp theo tên:");
        Student[] nameSort = students.clone();
        insertionSort(nameSort, new NameComparator());
        printStudents(nameSort, "Sau khi sắp xếp theo tên:");
        
        // 3. Sắp xếp theo tuổi (sử dụng AgeComparator)
        System.out.println("\n3. Sắp xếp theo tuổi:");
        Student[] ageSort = students.clone();
        insertionSort(ageSort, new AgeComparator());
        printStudents(ageSort, "Sau khi sắp xếp theo tuổi:");
        
        // 4. Sắp xếp theo nhiều tiêu chí (tuổi, rồi đến điểm)
        System.out.println("\n4. Sắp xếp theo tuổi và điểm:");
        Student[] multiSort = students.clone();
        Arrays.sort(multiSort, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int ageCompare = Integer.compare(s1.getAge(), s2.getAge());
                if (ageCompare != 0) return ageCompare;
                return Double.compare(s2.getScore(), s1.getScore()); // Điểm cao đến thấp
            }
        });
        printStudents(multiSort, "Sau khi sắp xếp theo tuổi và điểm:");
        
        // 5. Sử dụng Java 8 Stream API để sắp xếp
        System.out.println("\n5. Sắp xếp sử dụng Stream API:");
        Student[] streamSort = Arrays.stream(students)
            .sorted(Comparator.comparing(Student::getScore).reversed())
            .toArray(Student[]::new);
        printStudents(streamSort, "Sau khi sắp xếp theo điểm (giảm dần) using Stream:");
    }
}