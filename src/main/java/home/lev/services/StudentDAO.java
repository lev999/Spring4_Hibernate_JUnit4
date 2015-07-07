package home.lev.services;

import home.lev.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class StudentDAO implements IStudentDAO{
    @Autowired
    HibernateTemplate hibernateTemplate;

    public Student getStudentById(int id){
        return hibernateTemplate.get(Student.class,id);
    }

    @Override
    public void saveStudent(Student student) {
        hibernateTemplate.saveOrUpdate(student);
    }


}
