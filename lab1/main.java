import java.util.*;

public class Main {
    public static boolean isFibonacci(int n) {
        if (n < 0) return false;
        return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4);
    }

    private static boolean isPerfectSquare(long x) {
        long s = (long) Math.sqrt(x);
        return s * s == x;
    }

    public static void solveTask1(int[][] matrix) {
        System.out.println("--- TASK 1 ---");
        System.out.println("Initial matrix:");
        printMatrix(matrix);

        int rows = matrix.length;
        int[] resultVector = new int[rows];

        for (int i = 0; i < rows; i++) {
            int minFib = Integer.MAX_VALUE;
            
            for (int j = 0; j < matrix[i].length; j++) {
                int val = matrix[i][j];
                if (isFibonacci(val)) {
                    if (val < minFib) {
                        minFib = val;
                    }
                }
            }
            
            if (minFib == Integer.MAX_VALUE) {
                resultVector[i] = -1; 
            } else {
                resultVector[i] = minFib;
            }
        }

        System.out.println("Resulting vector of minimum Fibonacci numbers for each row:");
        System.out.println(Arrays.toString(resultVector));
        System.out.println();
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }


    // ==========================================


    public static abstract class Person implements Comparable<Person> {
        protected String name;
        protected int age;
        protected String gender;

        public Person(String name, int age, String gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Person other) {
            return this.name.compareTo(other.name);
        }

        @Override
        public abstract String toString();
    }


    public static class Teacher extends Person {
        private String position;
        private int experience; 

        public Teacher(String name, int age, String gender, String position, int experience) {
            super(name, age, gender);
            this.position = position;
            this.experience = experience;
        }

        public int getExperience() {
            return experience;
        }

        @Override
        public String toString() {
            return String.format("Teacher [Name: %s, Age: %d, Gender: %s, Position: %s, Experience: %d yrs.]", 
                    name, age, gender, position, experience);
        }
    }


    public static class Student extends Person {
        private String faculty;
        private double averageGrade; 

        public Student(String name, int age, String gender, String faculty, double averageGrade) {
            super(name, age, gender);
            this.faculty = faculty;
            this.averageGrade = averageGrade;
        }

        public double getAverageGrade() {
            return averageGrade;
        }

        @Override
        public String toString() {
            return String.format("Student [Name: %s, Age: %d, Gender: %s, Faculty: %s, Avg. grade: %.2f]", 
                    name, age, gender, faculty, averageGrade);
        }
    }


    public static void main(String[] args) {
        
        int[][] matrix = {
            {4, 8, 10, 15},   
            {12, 15, 21, 30}, 
            {9, 11, 4, 6},  
            {13, 12, 34, 31}    
        };
        solveTask1(matrix);

        Person[] people = {
            new Student("Oleksandr", 19, "Male", "Applied Mathematics", 92.5),
            new Teacher("Mariia", 45, "Female", "Associate Professor", 15),
            new Student("Viktoriia", 18, "Female", "Cybersecurity", 95.0),
            new Teacher("Ivan", 32, "Male", "Assistant", 4),
            new Student("Andrii", 20, "Male", "Applied Mathematics", 95.0)
        };

        System.out.println("--- TASK 2 ---");
        
        Arrays.sort(people);
        System.out.println("1. List of people sorted by name:");
        for (Person p : people) {
            System.out.println("   " + p);
        }
        System.out.println();
     
        Teacher minExpTeacher = null;
        for (Person p : people) {
            if (p instanceof Teacher) {
                Teacher t = (Teacher) p;
                if (minExpTeacher == null || t.getExperience() < minExpTeacher.getExperience()) {
                    minExpTeacher = t;
                }
            }
        }
        
        System.out.println("2. Teacher with minimum experience:");
        if (minExpTeacher != null) {
            System.out.println("   " + minExpTeacher);
        } else {
            System.out.println("   No teachers found in the list.");
        }
        System.out.println();

        double maxGrade = -1.0;
        for (Person p : people) {
            if (p instanceof Student) {
                Student s = (Student) p;
                if (s.getAverageGrade() > maxGrade) {
                    maxGrade = s.getAverageGrade();
                }
            }
        }

        System.out.println("3. Students with maximum average grade (" + maxGrade + "):");
        boolean foundStudent = false;
        for (Person p : people) {
            if (p instanceof Student) {
                Student s = (Student) p;
                
                if (Double.compare(s.getAverageGrade(), maxGrade) == 0) {
                    System.out.println("   " + s);
                    foundStudent = true;
                }
            }
        }
        if (!foundStudent) {
            System.out.println("No students found in the list.");
        }
    }
}
