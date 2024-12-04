package rmi_api;

import java.io.Serializable;

public class EntityPersonne implements  Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int id;
	
	public String name ;
	
	public EntityPersonne(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	int age ;
	
}
