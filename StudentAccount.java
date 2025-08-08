package model;

import abstractclass.Account;
import util.PasswordHasher;

public class StudentAccount extends Account {
    private Student student;
    
    public StudentAccount(String username, String password, Student student) {
        super(username, PasswordHasher.hash(password));
        this.student = student;
    }
    
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && 
               PasswordHasher.verify(password, this.password);
    }
    
    @Override
    public void logout() {
        System.out.println("Student " + username + " logged out");
    }
    
    public Student getStudent() {
        return student;
    }
}