package ir.maktab.University.util;

import ir.maktab.University.entities.*;


public class Security {
    private static User user;
    private static Teacher teacher;
    private static Student student;
    private static Manager manager;
    private static String isUserAllow;
    private static Quiz quiz;

    public static Quiz getQuiz() {
        return quiz;
    }

    public static void setQuiz(Quiz quiz) {
        Security.quiz = quiz;
    }

    public static String getIsUserAllow() {
        return isUserAllow;
    }

    public static void setIsUserAllow(String isUserAllow) {
        Security.isUserAllow = isUserAllow;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Security.user = user;
    }

    public static Teacher getTeacher() {
        return teacher;
    }

    public static void setTeacher(Teacher teacher) {
        Security.teacher = teacher;
    }

    public static Student getStudent() {
        return student;
    }

    public static void setStudent(Student student) {
        Security.student = student;
    }

    public static Manager getManager() {
        return manager;
    }

    public static void setManager(Manager manager) {
        Security.manager = manager;
    }
}
