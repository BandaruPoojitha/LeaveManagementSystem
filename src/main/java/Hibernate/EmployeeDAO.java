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
import beanclasses.Employee;
import bussinesslogic.CancelLeaveValiation;

public class EmployeeDAO {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentPU");
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	public String getManagerId() {
		Bean bean = new Bean();

		Employee employee = entityManager.find(Employee.class, bean.getEmployeeId());

		return employee.getManagerId();

		// TODO Auto-generated method stub

	}

	public int getPaid() {
		// TODO Auto-generated method stub

		Bean b = new Bean();
		Employee l = entityManager.find(Employee.class, b.getEmployeeId());
		Query query = entityManager.createQuery("SELECT e FROM BalanceLeaves e WHERE e.employeeId=:employeeId");
		query.setParameter("employeeId", l);
		List<BalanceLeaves> resultList = query.getResultList();
		System.out.println(resultList.get(0).getPaid() + " leaves");
		// System.out.println(x+" PaidLeaves");

		return resultList.get(0).getPaid();
	}

	public int getLOP() {
		// TODO Auto-generated method stub
		System.out.println("into login getPai");
		Bean b = new Bean();
		System.out.println("employee id iss" + b.getEmployeeId());

		Employee l = entityManager.find(Employee.class, b.getEmployeeId());
		Query query = entityManager.createQuery("SELECT e FROM BalanceLeaves e WHERE e.employeeId=:employeeId");
		query.setParameter("employeeId", l);
		List<BalanceLeaves> resultList = query.getResultList();
		System.out.println(resultList.get(0).getLOP() + " leaves");
		// System.out.println(x+" PaidLeaves");

		return resultList.get(0).getLOP();
	}

	public ArrayList<String> getLeaves() {
		// TODO Auto-generated method stub
		ArrayList<String> al = new ArrayList<>();
		Bean b = new Bean();
		Employee e = new Employee();
		e.setEmployeeId(b.getEmployeeId());
		Query query = entityManager
				.createQuery("SELECT e FROM ApplyLeave e WHERE e.applyTo=:employeeId and e.status=:sta");
		query.setParameter("employeeId", e);
		query.setParameter("sta", "processing");
		ArrayList<ApplyLeave> resultList = (ArrayList<ApplyLeave>) query.getResultList();
		for (int i = 0; i < resultList.size(); i++) {
			al.add(resultList.get(i).getEmployeeId().getEmployeeId());
			al.add(resultList.get(i).getLeaveType());
			al.add(resultList.get(i).getStartdate().toString());
			al.add(resultList.get(i).getEnddate().toString());
			al.add(resultList.get(i).getApplyTo().getEmployeeId());
			al.add(resultList.get(i).getReason());
			al.add(resultList.get(i).getStatus());
			al.add(String.valueOf(resultList.get(i).getSno()));
		}
		return al;
	}

	public void updateApplyTo(String mgrid, int sno) {

		entityManager.getTransaction().begin();

		Employee e = new Employee();
		e.setEmployeeId(mgrid);
		ApplyLeave al = entityManager.find(ApplyLeave.class, sno);
		al.setApplyTo(e);
		entityManager.persist(al);
		entityManager.getTransaction().commit();

	}

	public void closeEm() {
		entityManager.close();
	}

	public void updateStatus(String value, int sno) {
		int lop = -1, paid = -1, sno1 = -1;
		entityManager.getTransaction().begin();

		ApplyLeave al = entityManager.find(ApplyLeave.class, sno);
		if (value.contentEquals("accept")) {

			int days = al.getNoOfDays();
			Employee employeeId = al.getEmployeeId();
			String type = al.getLeaveType();
			if (type.contentEquals("LOP")) {
				Query query = entityManager
						.createQuery("SELECT e FROM BalanceLeaves e WHERE e.employeeId=:employeeId ");
				query.setParameter("employeeId", employeeId);
				ArrayList<BalanceLeaves> resultList = (ArrayList<BalanceLeaves>) query.getResultList();
				sno1 = resultList.get(0).getSerialno();
				lop = resultList.get(0).getLOP();
				System.out.println(sno + "   balanceleaves sno");
				BalanceLeaves bl = entityManager.find(BalanceLeaves.class, sno1);
				bl.setLOP(lop + days);
				entityManager.persist(bl);
			} else if (type.contentEquals("Paid")) {
				Query query = entityManager
						.createQuery("SELECT e FROM BalanceLeaves e WHERE e.employeeId=:employeeId ");
				query.setParameter("employeeId", employeeId);
				ArrayList<BalanceLeaves> resultList = (ArrayList<BalanceLeaves>) query.getResultList();
				sno1 = resultList.get(0).getSerialno();
				paid = resultList.get(0).getPaid();
				BalanceLeaves bl = entityManager.find(BalanceLeaves.class, sno1);
				bl.setPaid(paid - days);
				entityManager.persist(bl);
			}

			al.setStatus(value);
			entityManager.persist(al);
			entityManager.getTransaction().commit();

		} else if (value.contentEquals("reject")) {

			al.setStatus(value);
			entityManager.persist(al);
			entityManager.getTransaction().commit();

		}
	}

	public void addLeave(ApplyLeave al) {

		Bean b = new Bean();
		Employee e = new Employee();
		entityManager.getTransaction().begin();

		e.setEmployeeId(b.getEmployeeId());
		al.setEmployeeId(e);
		entityManager.persist(al);
		entityManager.getTransaction().commit();

	}

	public int validLOP(Date startdate, Date enddate) {
		int startmonth = startdate.getMonth() + 1;
		int endmonth = enddate.getMonth() + 1;
		int count = 0;
		ArrayList<ApplyLeave> al = new ArrayList<>();
		Bean b = new Bean();
		Employee e = new Employee();
		e.setEmployeeId(b.getEmployeeId());
		Query query = entityManager.createQuery(
				"select e from ApplyLeave e where (MONTH(e.enddate)=:emon) and (MONTH(e.startdate)=:smon) and (e.LeaveType=:type) and (e.employeeId=:employeeId)");
		query.setParameter("emon", endmonth);
		query.setParameter("smon", startmonth);
		query.setParameter("type", "LOP");
		query.setParameter("employeeId", e);
		ArrayList<ApplyLeave> resultList = (ArrayList<ApplyLeave>) query.getResultList();
		if (resultList.isEmpty()) {
			return count;
		} else {
			for (int i = 0; i <resultList.size(); i++) {
				count += resultList.get(i).getNoOfDays();
			}

		}
		return count;
	}

	public ArrayList<ApplyLeave> validLeaves(Date startdate) {
		Bean bean = new Bean();
		Employee e = new Employee();
		e.setEmployeeId(bean.getEmployeeId());

		Query query = entityManager
				.createQuery("SELECT e FROM ApplyLeave e WHERE e.employeeId=:employeeId and e.enddate=:end ");
		query.setParameter("employeeId", e);
		query.setParameter("end", startdate);
		ArrayList<ApplyLeave> results = (ArrayList<ApplyLeave>) query.getResultList();

		return results;
	}

	public ArrayList<Integer> leaveBalance() {
		ArrayList<Integer> al = new ArrayList<>();
		Bean b = new Bean();
		Employee e = new Employee();
		e.setEmployeeId(b.getEmployeeId());
		Query query = entityManager.createQuery("SELECT e FROM BalanceLeaves e WHERE e.employeeId=:employeeId ");
		query.setParameter("employeeId", e);
		ArrayList<BalanceLeaves> resultList = (ArrayList<BalanceLeaves>) query.getResultList();
		for (int i = 0; i < resultList.size(); i++) {
			al.add(resultList.get(i).getLOP());
			al.add(resultList.get(i).getPaid());
		}
		// TODO Auto-generated method stub
		return al;
	}

	public ArrayList<String> trackLeaveDetails() {
		// TODO Auto-generated method stub
		
		ArrayList<String> al = new ArrayList<>();
		Bean b = new Bean();
		Employee e = new Employee();
		e.setEmployeeId(b.getEmployeeId());
		Query query = entityManager.createQuery("SELECT e FROM ApplyLeave e WHERE e.employeeId=:employeeId ");
		query.setParameter("employeeId", e);
		ArrayList<ApplyLeave> resultList = (ArrayList<ApplyLeave>) query.getResultList();
		for (int i = 0; i < resultList.size(); i++) {

			al.add(resultList.get(i).getEmployeeId().getEmployeeId());
			al.add(resultList.get(i).getLeaveType());
			al.add(resultList.get(i).getStartdate().toString());
			al.add(resultList.get(i).getEnddate().toString());
			al.add(resultList.get(i).getApplyTo().getEmployeeId());
			al.add(resultList.get(i).getReason());
			al.add(resultList.get(i).getStatus());
			al.add(String.valueOf(resultList.get(i).getSno()));

		}
		// TODO Auto-generated method stub
		return al;

	}

	public ArrayList<String> leaveDetails() {
		// TODO Auto-generated method stub
		CancelLeaveValiation cancelleave = new CancelLeaveValiation();
		ArrayList<String> al = new ArrayList<>();
		Bean b = new Bean();
		Employee e = new Employee();
		e.setEmployeeId(b.getEmployeeId());
		Query query = entityManager.createQuery("SELECT e FROM ApplyLeave e WHERE e.employeeId=:employeeId ");
		query.setParameter("employeeId", e);
		ArrayList<ApplyLeave> resultList = (ArrayList<ApplyLeave>) query.getResultList();
		for (int i = 0; i < resultList.size(); i++) {
			if (cancelleave.cancelLeave(resultList, i)) {
				al.add(resultList.get(i).getEmployeeId().getEmployeeId());
				al.add(resultList.get(i).getLeaveType());
				al.add(resultList.get(i).getStartdate().toString());
				al.add(resultList.get(i).getEnddate().toString());
				al.add(resultList.get(i).getApplyTo().getEmployeeId());
				al.add(resultList.get(i).getReason());
				al.add(resultList.get(i).getStatus());
				al.add(String.valueOf(resultList.get(i).getSno()));
			}
		}
		// TODO Auto-generated method stub
		return al;

	}

	public void cancelLeave(int sno) {
		// TODO Auto-generated method stub
		System.out.println("sno  ::" + sno);
		entityManager.getTransaction().begin();
		System.out.println(251);
		ApplyLeave al = entityManager.find(ApplyLeave.class, sno);
		// em.persist(al);
		System.out.println(al.getNoOfDays() + " dayscanclelevae");
		int days = al.getNoOfDays();
		String type = al.getLeaveType();
		Employee e = al.getEmployeeId();
		if (al.getStatus().contentEquals("accept")) {
			if (type.contentEquals("LOP")) {
				Query query = entityManager
						.createQuery("SELECT e FROM BalanceLeaves e WHERE e.employeeId=:employeeId ");
				query.setParameter("employeeId", e);
				ArrayList<BalanceLeaves> resultList = (ArrayList<BalanceLeaves>) query.getResultList();
				days = resultList.get(0).getLOP() - days;
				int x = resultList.get(0).getSerialno();
				BalanceLeaves bl = entityManager.find(BalanceLeaves.class, x);
				bl.setLOP(days);
				entityManager.persist(bl);
			} else if (type.contentEquals("Paid")) {
				Query query = entityManager
						.createQuery("SELECT e FROM BalanceLeaves e WHERE e.employeeId=:employeeId ");
				query.setParameter("employeeId", e);
				ArrayList<BalanceLeaves> resultList = (ArrayList<BalanceLeaves>) query.getResultList();
				days = resultList.get(0).getPaid() + days;
				int x = resultList.get(0).getSerialno();
				BalanceLeaves bl = entityManager.find(BalanceLeaves.class, x);
				bl.setPaid(days);
				entityManager.persist(bl);
			}
		}
		entityManager.remove(al);
		entityManager.getTransaction().commit();
	}
}
