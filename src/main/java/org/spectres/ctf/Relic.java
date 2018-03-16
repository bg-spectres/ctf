package org.spectres.ctf;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "relics")
public class Relic {
	@Id
	private Integer relic_id;
	
	private String relic_name;
	private Integer relic_price;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date added;
	
	private Integer in_stock;

	public Integer getRelic_id() {
		return relic_id;
	}

	public void setRelic_id(Integer relic_id) {
		this.relic_id = relic_id;
	}

	public String getRelic_name() {
		return relic_name;
	}

	public void setRelic_name(String relic_name) {
		this.relic_name = relic_name;
	}

	public Integer getRelic_price() {
		return relic_price;
	}

	public void setRelic_price(Integer relic_price) {
		this.relic_price = relic_price;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public Integer getIn_stock() {
		return in_stock;
	}

	public void setIn_stock(Integer in_stock) {
		this.in_stock = in_stock;
	}

	public Integer getId() {
		return relic_id;
	}
	
	public String getDisplayName() {
		return relic_name;
	}
	
	@Override
	public String toString() {
		return "Relic 	id: " + getRelic_id() +
				"		name: " + getRelic_name() +
				"		price: " + getRelic_price()
				;
	}
}
