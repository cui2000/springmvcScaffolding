package demo.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
public class User  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;
	@Column(name="age")
	private int age;
	@Column(name="name")
	private String name;
	@Column(name="sex")
	private String sex;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}
	public int getAge(){
		return age;
	}

	public void setAge(int age){
		this.age = age;
	}
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	public String getSex(){
		return sex;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

}