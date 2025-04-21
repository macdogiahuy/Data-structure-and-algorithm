/**
 * Minh họa hệ thống quản lý sinh viên kết hợp nhiều cấu trúc dữ liệu và thuật toán
 */
import java.util.*;

public class StudentManagement {
    
    // Lớp Student với các thuộc tính cơ bản
    static class Student {
        private String id;
        private String name;
        private double gpa;
        private String major;
        
        public Student(String id, String name, double gpa, String major) {
            this.id = id;
            this.name = name;
            this.gpa = gpa;
            this.major = major;
        }
        
        // Getters
        public String getId() { return id; }
        public String getName() { return name; }
        public double getGpa() { return gpa; }
        public String getMajor() { return major; }
        
        // Setters
        public void setName(String name) { this.name = name; }
        public void setGpa(double gpa) { this.gpa = gpa; }
        public void setMajor(String major) { this.major = major; }
        
        @Override
        public String toString() {
            return String.format("ID: %-10s | Tên: %-20s | GPA: %.2f | Ngành: %s", 
                               id, name, gpa, major);
        }
    }
    
    // Lớp quản lý sinh viên
    static class StudentManager {
        private List<Student> students;          // Danh sách sinh viên
        private Map<String, Student> studentMap; // Map để tìm kiếm nhanh theo ID
        
        public StudentManager() {
            students = new ArrayList<>();
            studentMap = new HashMap<>();
        }
        
        // Thêm sinh viên mới
        public void addStudent(Student student) {
            if (studentMap.containsKey(student.getId())) {
                System.out.println("Sinh viên với ID " + student.getId() + " đã tồn tại!");
                return;
            }
            
            students.add(student);
            studentMap.put(student.getId(), student);
            System.out.println("Đã thêm sinh viên: " + student.getName());
        }
        
        // Xóa sinh viên
        public void removeStudent(String id) {
            Student student = studentMap.remove(id);
            if (student != null) {
                students.remove(student);
                System.out.println("Đã xóa sinh viên: " + student.getName());
            } else {
                System.out.println("Không tìm thấy sinh viên với ID: " + id);
            }
        }
        
        // Tìm sinh viên theo ID
        public Student findStudent(String id) {
            return studentMap.get(id);
        }
        
        // Sắp xếp theo GPA
        public void sortByGPA() {
            Collections.sort(students, (s1, s2) -> 
                Double.compare(s2.getGpa(), s1.getGpa())); // Giảm dần
        }
        
        // Sắp xếp theo tên
        public void sortByName() {
            Collections.sort(students, (s1, s2) -> 
                s1.getName().compareTo(s2.getName())); // Tăng dần
        }
        
        // Tính điểm trung bình của lớp
        public double getAverageGPA() {
            if (students.isEmpty()) return 0.0;
            
            return students.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
        }
        
        // Thống kê số sinh viên theo xếp loại
        public Map<String, Integer> getGradeDistribution() {
            Map<String, Integer> distribution = new HashMap<>();
            
            for (Student student : students) {
                String grade = getGradeFromGPA(student.getGpa());
                distribution.merge(grade, 1, Integer::sum);
            }
            
            return distribution;
        }
        
        // Chuyển đổi GPA sang xếp loại
        private String getGradeFromGPA(double gpa) {
            if (gpa >= 9.0) return "Xuất sắc";
            if (gpa >= 8.0) return "Giỏi";
            if (gpa >= 7.0) return "Khá";
            if (gpa >= 5.0) return "Trung bình";
            return "Yếu";
        }
        
        // In danh sách sinh viên
        public void printStudents() {
            if (students.isEmpty()) {
                System.out.println("Danh sách sinh viên trống!");
                return;
            }
            
            System.out.println("\nDanh sách sinh viên:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
    
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        
        // 1. Thêm sinh viên mới
        System.out.println("1. Thêm sinh viên:");
        manager.addStudent(new Student("SV001", "Nguyễn Văn A", 8.5, "CNTT"));
        manager.addStudent(new Student("SV002", "Trần Thị B", 7.8, "CNTT"));
        manager.addStudent(new Student("SV003", "Lê Văn C", 9.2, "CNTT"));
        manager.addStudent(new Student("SV004", "Phạm Thị D", 6.5, "CNTT"));
        manager.addStudent(new Student("SV005", "Hoàng Văn E", 8.9, "CNTT"));
        
        // 2. In danh sách ban đầu
        manager.printStudents();
        
        // 3. Sắp xếp và in theo GPA
        System.out.println("\n3. Danh sách sau khi sắp xếp theo GPA:");
        manager.sortByGPA();
        manager.printStudents();
        
        // 4. Sắp xếp và in theo tên
        System.out.println("\n4. Danh sách sau khi sắp xếp theo tên:");
        manager.sortByName();
        manager.printStudents();
        
        // 5. Tìm kiếm sinh viên
        System.out.println("\n5. Tìm kiếm sinh viên:");
        String searchId = "SV003";
        Student found = manager.findStudent(searchId);
        if (found != null) {
            System.out.println("Tìm thấy sinh viên: " + found);
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + searchId);
        }
        
        // 6. Tính điểm trung bình
        System.out.println("\n6. Điểm trung bình của lớp: " + 
                          String.format("%.2f", manager.getAverageGPA()));
        
        // 7. Thống kê phân loại
        System.out.println("\n7. Thống kê phân loại:");
        Map<String, Integer> distribution = manager.getGradeDistribution();
        for (Map.Entry<String, Integer> entry : distribution.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " sinh viên");
        }
        
        // 8. Xóa sinh viên
        System.out.println("\n8. Xóa sinh viên:");
        manager.removeStudent("SV002");
        manager.printStudents();
    }
}