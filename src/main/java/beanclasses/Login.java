package beanclasses;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Login {
@Id
String Username;
@OneToOne
private Employee employeeId;

public String getUsername() {
	return Username;
}
public void setUsername(String username) {
	Username = username;
}
String password;
String employeeType;

public Employee getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(Employee employeeId) {
	this.employeeId = employeeId;
}
@Override
public String toString() {
	return "Login [Username=" + Username + ", employeeId=" + employeeId + ", password=" + password + ", employeeType="
			+ employeeType + "]";
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmployeeType() {
	return employeeType;
}
public void setEmployeeType(String employeeType) {
	this.employeeType = employeeType;
}
}
