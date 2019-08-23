package Hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import beanclasses.ApplyLeave;
import beanclasses.BalanceLeaves;
import beanclasses.Bean;
import beanclasses.Department;
import beanclasses.Employee;
import beanclasses.Login;
//import leave.LeaveDetails;

public class LoginValidation {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentPU");
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	public ArrayList<String> getType(String id, String pass) {
		ArrayList<String> arraylist = new ArrayList<>();
		Login l = new Login();

		l = entityManager.find(Login.class, id);
		if (l != null) {
			if (l.getPassword().equals(pass)) {

				arraylist.add(l.getEmployeeId().getEmployeeId());

				arraylist.add(l.getEmployeeType());

				return arraylist;
			} else
				return null;
		}
		return null;
	}

	public void closeEm() {
		entityManager.close();
	}

}
