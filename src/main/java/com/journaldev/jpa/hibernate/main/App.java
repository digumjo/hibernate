package com.journaldev.jpa.hibernate.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.journaldev.jpa.hibernate.model.Employee;
import com.journaldev.jpa.hibernate.model.Employee1;

public class App {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		System.out.println("Starting Transaction");
		entityManager.getTransaction().begin();
		Employee1 employee = new Employee1();
		//employee.setName("Pankaj");
		System.out.println("Saving Employee to Database");

		//Query q = entityManager.createNativeQuery("insert into account (user_id, username) values ('1', 'krupa')");
		Query q = entityManager.createNativeQuery("select * from Employee e, account a where a.user_id = e.employee_id");
		//q.executeUpdate();
		@SuppressWarnings("unchecked")
		List<Object[]> list = q.getResultList();
		List<String> nameList = new ArrayList<>();
		for(Object[] li : list) {
			nameList.add((String)li[1]);
		}
		
		entityManager.getTransaction().commit();
		System.out.println("Generated Employee ID = " + nameList.toString());

		/*// get an object using primary key.
		Employee emp = entityManager.find(Employee.class, employee.getEmployeeId());
		System.out.println("got object " + emp.getName() + " " + emp.getEmployeeId());

		// get all the objects from Employee table
		@SuppressWarnings("unchecked")
		List<Employee> listEmployee = entityManager.createQuery("SELECT e FROM Employee e").getResultList();

		if (listEmployee == null) {
			System.out.println("No employee found . ");
		} else {
			for (Employee empl : listEmployee) {
				System.out.println("Employee name= " + empl.getName() + ", Employee id " + empl.getEmployeeId());
			}
		}
		// remove and entity
		entityManager.getTransaction().begin();
		System.out.println("Deleting Employee with ID = " + emp.getEmployeeId());
		entityManager.remove(emp);
		entityManager.getTransaction().commit();*/

		// close the entity manager
		entityManager.close();
		entityManagerFactory.close();

	}
}
