package base.activitymeter;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {

	//@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Long id;
	
	@Id
	private String name;

	public Tag() {
	}

	public Tag(String name) {
		this.name = name;
		// this.id = UUID.nameUUIDFromBytes(name.getBytes()).getMostSignificantBits();
	}

	// public Long getId() {
	//	return UUID.nameUUIDFromBytes(name.getBytes()).getMostSignificantBits();
	// 	return id;
	// }

	//public void setId(Long id) {
	//	this.id = id;
	//}

	public String getName() {
		return name;
	}

	//public void setName(String name) {
	//	this.name = name;
	//}
	
	@Override
	public String toString() {
		return name;
		
	}

}