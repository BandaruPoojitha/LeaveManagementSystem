package Hibernate;

import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.constraints.Size;

import beanclasses.BalanceLeaves;
import beanclasses.Department;
import beanclasses.Employee;
import beanclasses.Login;

public class AdminDAO {
	EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("StudentPU");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	public ArrayList<String> getDepartmentDetails() {
		Query query = entityManager.createQuery("select d from Department d");
		ArrayList<Department> resultList = (ArrayList<Department>) query.getResultList();
		Iterator iterator=resultList.iterator();
		ArrayList<String> al=new ArrayList<String>();
		for(int i=0;i<resultList.size();i++) {
		al.add(resultList.get(i).getManagerId().getEmployeeId());
		al.add(resultList.get(i).getDepartmentId());
		}
		
		return al;
		// TODO Auto-generated method stub

	}


	public void closeEm() {
		entityManager.close();
	}
	public void addEmployee(Employee e) {
		entityManager.getTransaction().begin();
		System.out.println(53);
		Login l = new Login();
		l.setEmployeeId(e);
		l.setUsername(e.getEmployeeName() + e.getEmployeeId());
		l.setPassword(e.getEmployeeId() + e.getEmployeeName());
		l.setEmployeeType("employee");
		BalanceLeaves bl = new BalanceLeaves();
		bl.setEmployeeId(e);
		bl.setLOP(0);
		bl.setPaid(10);
		entityManager.persist(l);
		entityManager.persist(bl);
		entityManager.persist(e);
		entityManager.getTransaction().commit();

	}

	public void addDepartment(Department d) {
		entityManager.getTransaction().begin();
		System.out.println(72);
		entityManager.persist(d);
		entityManager.getTransaction().commit();

	}


	public void updateAddress(String employeeId, String address) {
		Employee employee=entityManager.find(Employee.class,employeeId);
          employee.setAddress(address);
          entityManager.getTransaction().begin();
          entityManager.persist(employee);
          entityManager.getTransaction().commit();
		// TODO Auto-generated method stub
		
	}


	public void updateContact(String employeeId, String contact) {
		// TODO Auto-generated method stub
		Employee employee=entityManager.find(Employee.class,employeeId);
        employee.setPhonenumber(contact);
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
		
	}


	public void updateEmail(String employeeId, String email) {
		// TODO Auto-generated method stub
		Employee employee=entityManager.find(Employee.class,employeeId);
        employee.setEmail(email);
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
	}

}
