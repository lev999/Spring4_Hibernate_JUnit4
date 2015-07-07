package home.lev.config;

import home.lev.Student;
import home.lev.services.IStudentDAO;
import home.lev.services.StudentDAO;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class ApplConfig implements TransactionManagementConfigurer {

    @Bean
    public IStudentDAO IStudentDAO(){
        return new StudentDAO();
    }

    @Bean
    public HibernateTemplate hibernateTemplate(){
        return new HibernateTemplate(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory(){
        return new LocalSessionFactoryBuilder(dataSource())
                .addAnnotatedClasses(Student.class)
                .buildSessionFactory();
    }


    private DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/student");
        dataSource.setUsername("root");
        dataSource.setPassword("123qwee");

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        return new HibernateTransactionManager(sessionFactory());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}
