package com.dao.userDatabase;


public class User {
	
	private String id;
	
    private String username;
     
    private String password;
     
    private String email;

    private int privileges;
    
    private int bannedForDays;
    


	private String[] roles;
    
    public User(){}

	/**
	 * @param username
	 * @param password
	 * @param email
	 */
	public User(String username, String password, String email, String... roles) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	//getters and setters
	
    
	public int getBannedForDays() {
		return bannedForDays;
	}

	public void setBannedForDays(int bannedForDays) {
		this.bannedForDays = bannedForDays;
	}
	
	public String getUsername() {
		return username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPrivileges() {
		return privileges;
	}

	public void setPrivileges(int privileges) {
		this.privileges = privileges;
	}
     
    public String[] getRoles() {
		return roles;
	}

	public void setRoles(String... roles) {
		this.roles = roles;
	}
    
    
}