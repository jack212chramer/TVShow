package com.dao.persons;

public class Role {

	private int person_id;
	private String person;
	private int role;
	private int show_id;
	private String character_name;
	private String image;
	private int roleId;
	
	
	public Role(){}
	
	public Role(int person_id, String person, int role, int show_id, String character_name,String image) {
		super();
		this.person_id = person_id;
		this.person = person;
		this.role = role;
		this.show_id = show_id;
		this.character_name = character_name;
		this.image=image;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getShow_id() {
		return show_id;
	}

	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}

	public String getCharacter_name() {
		return character_name;
	}

	public void setCharacter_name(String character_name) {
		this.character_name = character_name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
