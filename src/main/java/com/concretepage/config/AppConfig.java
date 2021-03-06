package com.concretepage.config;  

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.concretepage.dao.IPersonDao;
import com.concretepage.dao.PersonDao;
import com.concretepage.entity.Person;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

//@Configuration
@EnableTransactionManagement
public class AppConfig  implements TransactionManagementConfigurer{
	@Bean  
    public IPersonDao personDao() {  
        return new PersonDao();  
    }

	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}

	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(getDataSource())
		   .addAnnotatedClasses(Person.class)
		   .buildSessionFactory();
	}

	@Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/person");
	    dataSource.setUsername("root");
	    dataSource.setPassword("123qwee");
	 
	    return dataSource;
	}

	@Bean
	public HibernateTransactionManager hibTransMan(){
		return new HibernateTransactionManager(sessionFactory());
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return hibTransMan();
	}
}
 