package com.concretepage;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.concretepage.config.AppConfig;
import com.concretepage.dao.IPersonDao;
import com.concretepage.entity.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class Spring4JUnit4Test {
  @Autowired
  private IPersonDao personDao;
  @Autowired
  private HibernateTemplate  hibernateTemplate;
  
  @Test
  public void savePersonTest(){
	  personDao.savePerson();
	  Person person = hibernateTemplate.get(Person.class, 1);
	  assertEquals("Ram", person.getName());
  }
}
