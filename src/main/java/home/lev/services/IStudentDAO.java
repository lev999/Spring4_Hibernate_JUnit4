package home.lev.services;

import home.lev.Student;

public interface IStudentDAO {
    public Student getStudentById(int id);
    public  void saveStudent(Student student);
}
