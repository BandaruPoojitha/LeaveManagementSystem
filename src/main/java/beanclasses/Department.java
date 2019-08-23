package beanclasses;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Department {
@Id
String departmentId;
@OneToMany(mappedBy = "department")
private List<Employee> createmployee=new ArrayList<Employee>();
@OneToOne(targetEntity=Employee.class)
Employee managerId;
public String getDepartmentId() {
	return departmentId;
}
public void setDepartmentId(String departmentId) {
	this.departmentId = departmentId;
}
public Employee getManagerId() {
	return managerId;
}
public void setManagerId(Employee managerId) {
	this.managerId = managerId;
}
//@Override
//public String toString() {
//	return "Department [departmentId=" + departmentId + ", createmployee=" + createmployee + ", managerId=" + managerId
//			+ "]";
//}

}
