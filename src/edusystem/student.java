package edusystem;

import static edusystem.EduSystem.admins_list;
import static edusystem.EduSystem.students_list;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class student {
    
    private String id;
    private String type;
    private String password;
    private String firstName;
    private String lastName;
    private String major;
    private double average;
    private HashMap<String, course> student_courses = null;
    
    public student(String id, String type, String password, String firstName, String lastName, String major, double average) {
        this.id = id;
        this.type = type;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.average = average;
        this.student_courses = new HashMap<String, course>(); 
        setCourses();
    }
    
    private void setCourses() {
        
        String id = null;
        String name = null;
        String examDate = null;
        String classTime = null;
        int credits = 0;
        String grade = null;
        String condition = null;
        
        File student_file = new File(this.id + ".txt");
        try {
            student_file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FileReader r_courses = null;
        try {
            r_courses = new FileReader(student_file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader b_courses = new BufferedReader(r_courses);
        String line = null;
        try {
            while ((line = b_courses.readLine()) != null) {
                if(line.equals("{")) {
                    id = b_courses.readLine();
                    name = b_courses.readLine();
                    examDate = b_courses.readLine();
                    classTime = b_courses.readLine();
                    line = b_courses.readLine();
                    credits = Integer.parseInt(line);
                    grade = b_courses.readLine();
                    condition = b_courses.readLine();
                }
                student_courses.put(id, new course(id, name, examDate, classTime, credits, grade, condition));
            }
        }
        catch (IOException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void appendCourse(String id) {
        FileWriter w_courses = null;
        
        try {
            w_courses = new FileWriter(this.id + ".txt", true);
        } catch (IOException ex) {
            Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedWriter b_courses = new BufferedWriter(w_courses);
        PrintWriter p_courses = new PrintWriter(b_courses);
        
        p_courses.println("{");
        p_courses.println(student_courses.get(id).getId());
        p_courses.println(student_courses.get(id).getName());
        p_courses.println(student_courses.get(id).getExamDate());
        p_courses.println(student_courses.get(id).getClassTime());
        p_courses.println(student_courses.get(id).getCredits());
        p_courses.println(student_courses.get(id).getGrade());
        p_courses.println(student_courses.get(id).getCondition());
        p_courses.println("}\n");
        
        try {
            p_courses.flush();
            b_courses.flush();
            w_courses.close();
        } catch (IOException ex) {
            Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeUsersFile() {
        course course1 = null;
        
        FileWriter w_courses = null;
        
        try {
            w_courses = new FileWriter(this.id + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedWriter b_courses = new BufferedWriter(w_courses);
        PrintWriter p_courses = new PrintWriter(b_courses);
        
        for(String id: student_courses.keySet()) {
            course1 = student_courses.get(id);
            
            p_courses.println("{");
            p_courses.println(course1.getId());
            p_courses.println(course1.getName());
            p_courses.println(course1.getExamDate());
            p_courses.println(course1.getClassTime());
            p_courses.println(course1.getCredits());
            p_courses.println(course1.getGrade());
            p_courses.println(course1.getCondition());
            p_courses.println("}\n");
        }
        
        try {
            p_courses.flush();
            b_courses.flush();
            w_courses.close();
        } catch (IOException ex) {
            Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMajor() {
        return major;
    }

    public double getAverage() {
        return average;
    }

    public HashMap<String, course> getStudent_courses() {
        return student_courses;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setAverage(double average) {
        this.average = average;
    }
    
    public void calculateAverage() {
        int credits = 0;
        int n = 0;
        double grade = 0.0;
        double sum = 0.0;
        double average = 0.0;
        
        for(String id: student_courses.keySet()) {
            if(student_courses.get(id).getGrade().equals("-")) {
                continue;
            }
            grade = Double.parseDouble(student_courses.get(id).getGrade());
            credits =  student_courses.get(id).getCredits();
            
            sum += (credits * grade);
            n += credits;
        }
        
        average = round(sum/n);
        setAverage(average);
    }
    
    public static double round(double average){
        average = average * 100.0;
        long temp = Math.round(average);
        return (double) temp / 100.0;
    }
}