package org.spectres.ctf;

public enum Table {
	USERS("users"),
	RELICS("relics"),
	SECRETS("secrets");
	
    public static final Table[] ALL = { USERS, RELICS, SECRETS };

    
	public String name;
	
	Table () {
	}
	
	Table(String name) {
		this.name = name;
	}
}
