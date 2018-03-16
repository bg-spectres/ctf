package org.spectres.ctf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "secrets")
public class Secret {

	@Id
	private Integer secret_id;
	
	private String secret;
	private String name;
	
	public Integer getSecret_id() {
		return secret_id;
	}
	public void setSecret_id(Integer secret_id) {
		this.secret_id = secret_id;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return secret_id;
	}
	
	public String getDisplayName() {
		return name;
	}
	
	@Override
	public String toString() {
		return 	"Secret id: " + getSecret_id() +
				"		name: " + getName() +
				"		secret: " + getSecret()
				;
	}
}
