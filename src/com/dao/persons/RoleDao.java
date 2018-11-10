package com.dao.persons;

import java.util.ArrayList;

public interface RoleDao {

	public Role getRole(int person_id,int show_id);
	
	public ArrayList<Role> getRolesPlayedByPerson(int person_id);
	
	public ArrayList<Role> getRoles(int show_id);
	
	public void addRole(Role role);
	
	public void updateRole(Role role);
	
	public void deleteRole(Role role);

	public void deleteRolesOfPerson(Person person);
}
