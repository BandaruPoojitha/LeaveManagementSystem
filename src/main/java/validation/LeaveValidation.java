package validation;


import java.util.ArrayList;
import java.util.Date;

import Hibernate.EmployeeDAO;
import Hibernate.LoginValidation;
import beanclasses.ApplyLeave;
import beanclasses.Employee;
import bussinesslogic.Days;

public class LeaveValidation {
	static int dayscount=0;
	ArrayList<ApplyLeave> arraylist=null;
	EmployeeDAO employeeDAO=new EmployeeDAO();
	
	boolean strechLeaves(Date startdate,int noofdays) {
		System.out.println("1poo");
		while(noofdays+dayscount<5 ) {
			arraylist=employeeDAO.validLeaves(startdate);
			if(arraylist.isEmpty()) {
				return true;
			}
			else {
			 dayscount+=arraylist.get(0).getNoOfDays();
			startdate= (Date) arraylist.get(0).getStartdate();
			System.out.println(dayscount+"********poo************"+startdate);
		
			}
	}
		return false;
	}
	public boolean leaveValid(String leaveType,Date startdate,Date enddate,Employee applyTo,String reason,String count) {
		System.out.println("2poo");
	 int days=Integer.valueOf(count);
	 ApplyLeave al=new ApplyLeave();
	
	 System.out.println(days+" lop or paid");
	EmployeeDAO employeeDAO=new EmployeeDAO();
		Days d=new Days();
		int noofdays=(int) d.daysBetween(startdate, enddate);
		System.out.println(noofdays+"  days");
		if(strechLeaves(startdate,noofdays)) {
			al.setStartdate(startdate);
			al.setEnddate(enddate);
			al.setReason(reason);
			al.setApplyTo(applyTo);
			al.setNoOfDays(noofdays);
			al.setLeaveType(leaveType);
			al.setStatus("processing");
				if(leaveType.equals("Paid")) {
					al.setLeaveType("Paid");
					if(days >=noofdays) {
						employeeDAO.addLeave(al);
					}
					else if(days<noofdays) {
						leaveType="LOP";
						if(noofdays<=3) {
							int totalLop=employeeDAO.validLOP(startdate,enddate);
							if((totalLop+noofdays)<=3) {
								employeeDAO.addLeave(al);
							}
						}
					}
				}
				else if(leaveType.equals("LOP")) {
					if(noofdays<=3) {  
						System.out.println(startdate+"  hi"+enddate);
						System.out.println(startdate.getMonth()+"Month"+enddate.getMonth());
						int totalLop=employeeDAO.validLOP(startdate,enddate);
						if((totalLop+noofdays)<=3) {
							employeeDAO.addLeave(al);
						}
						else {
							return false;
						}
					}
				}
			
		}
		else {
			System.out.println("leave cannot be applied");
			return false;
		}
		return true;
	}
}