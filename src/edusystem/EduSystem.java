/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edusystem;

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

/**
 *
 * @author Ali
 */
public class EduSystem {
    
    public static HashMap<String, student> students_list = new HashMap<String, student>();
    public static HashMap<String, admin> admins_list = new HashMap<String, admin>();
    
    public static HashMap<String, course> courses_list = new HashMap<String, course>();
    static {
        
        File courses = new File("courses.txt");
        
        try {
                courses.createNewFile();
        } 
        catch (IOException ex) {
                Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String id = null;
        String name = null;
        String examDate = null;
        String classTime = null;
        int credits = 0;
        
        FileReader r_courses = null;
        try {
            r_courses = new FileReader("courses.txt");
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
                }
                courses_list.put(id, new course(id, name, examDate, classTime, credits));
            }
        }
        catch (IOException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setUsers() {
        
        File users = new File("users.txt");
        
        try {
                users.createNewFile();
        } 
        catch (IOException ex) {
                Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String id = null;
        String type = null;
        String password = null;
        String firstName = null;
        String lastName = null;
        String major = null;
        double average = 0.0;
        
        FileReader r_users = null;
        try {
            r_users = new FileReader(users);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader b_users = new BufferedReader(r_users);
        String line = null;
        try {
            while ((line = b_users.readLine()) != null) {
                if(line.equals("{")) {
                    id = b_users.readLine();
                    type = b_users.readLine();
                    password = b_users.readLine();
                    firstName = b_users.readLine();
                    lastName = b_users.readLine();
                    if(type.equals("Student")) {
                        major = b_users.readLine();
                        line = b_users.readLine();
                        average = Double.parseDouble(line);
                        students_list.put(id, new student(id, type, password, firstName, lastName, major, average));
                    } else {
                        admins_list.put(id, new admin(id, type, password, firstName, lastName));
                    }
                }
            }
        }
        catch (IOException ex) {
                Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void appendUser(String id, String type) {
        FileWriter w_users = null;
        
        try {
            w_users = new FileWriter("users.txt", true);
        } catch (IOException ex) {
            Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedWriter b_users = new BufferedWriter(w_users);
        PrintWriter p_users = new PrintWriter(b_users);
        
        if(type.equals("Student")) {
            p_users.println("{");
            p_users.println(students_list.get(id).getId());
            p_users.println(students_list.get(id).getType());
            p_users.println(students_list.get(id).getPassword());
            p_users.println(students_list.get(id).getFirstName());
            p_users.println(students_list.get(id).getLastName());
            p_users.println(students_list.get(id).getMajor());
            p_users.println(students_list.get(id).getAverage());
            p_users.println("}");
        } else if(type.equals("Admin")) {
            p_users.println("{");
            p_users.println(admins_list.get(id).getId());
            p_users.println(admins_list.get(id).getType());
            p_users.println(admins_list.get(id).getPassword());
            p_users.println(admins_list.get(id).getFirstName());
            p_users.println(admins_list.get(id).getLastName());
            p_users.println("}");
        }
        
        try {
            p_users.flush();
            b_users.flush();
            w_users.close();
        } catch (IOException ex) {
            Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void appendCourse(String id) {
        FileWriter w_courses = null;
        
        try {
            w_courses = new FileWriter("courses.txt", true);
        } catch (IOException ex) {
            Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedWriter b_courses = new BufferedWriter(w_courses);
        PrintWriter p_courses = new PrintWriter(b_courses);
        
        p_courses.println("{");
        p_courses.println(courses_list.get(id).getId());
        p_courses.println(courses_list.get(id).getName());
        p_courses.println(courses_list.get(id).getExamDate());
        p_courses.println(courses_list.get(id).getClassTime());
        p_courses.println(courses_list.get(id).getCredits());
        p_courses.println("}");
        
        try {
            p_courses.flush();
            b_courses.flush();
            w_courses.close();
        } catch (IOException ex) {
            Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void changeUsersFile() {
        student student1 = null;
        admin admin1 = null;
        
        FileWriter w_users = null;
        
        try {
            w_users = new FileWriter("users.txt");
        } catch (IOException ex) {
            Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedWriter b_users = new BufferedWriter(w_users);
        PrintWriter p_users = new PrintWriter(b_users);
        
        for(String id: EduSystem.students_list.keySet()) {
            student1 = EduSystem.students_list.get(id);
            
            p_users.println("{");
            p_users.println(student1.getId());
            p_users.println(student1.getType());
            p_users.println(student1.getPassword());
            p_users.println(student1.getFirstName());
            p_users.println(student1.getLastName());
            p_users.println(student1.getMajor());
            p_users.println(student1.getAverage());
            p_users.println("}\n");
        }
        
        for(String id: EduSystem.admins_list.keySet()) {
            admin1 = EduSystem.admins_list.get(id);
            
            p_users.println("{");
            p_users.println(admin1.getId());
            p_users.println(admin1.getType());
            p_users.println(admin1.getPassword());
            p_users.println(admin1.getFirstName());
            p_users.println(admin1.getLastName());
            p_users.println("}\n");
        }
        
        try {
            p_users.flush();
            b_users.flush();
            w_users.close();
        } catch (IOException ex) {
            Logger.getLogger(EduSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        setUsers();
        login login1 = new login();
        login1.setVisible(true);
    }
    
}
