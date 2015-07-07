package home.lev;

import home.lev.config.ApplConfig;
import home.lev.services.IStudentDAO;
import home.lev.services.StudentDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplConfig.class)
//@Transactional
//@TransactionConfiguration(defaultRollback = true)
public class MainFlowTest {
    @Autowired
    IStudentDAO studentDAO;

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Test
    public void mainFlowTest(){
        String name = "Vasia";
        Student student = new Student();
        student.setName(name);
        studentDAO.saveStudent(student);
        student.setName("non_name");
        Student studentFromDB = hibernateTemplate.get(Student.class, student.getId());
        Assert.assertEquals("Check name of User in DB", name, studentFromDB.getName());


    }
}
