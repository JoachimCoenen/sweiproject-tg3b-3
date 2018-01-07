package base.activitymeter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {

	//@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Long id;
	
	@Id
	private /*final*/ String name; // cannot be final, because of the way spring boot instantiates its @Entity objects.

	public Tag() {
	}

	public Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
		
	}
	

	/**
	 * Characters ,that are considered to separate words <i>within</i>  a tag.
	 * 
	 * This is used when comparing Tags.
	 */
	public static final String separatorChars = "_ \\-";
	
	public static final String wordChars = "\\w";
	
	public static final String validChars = separatorChars + wordChars;
	
	public static final String validTagRegEx = String.join("", "[", wordChars, "][", validChars, "]*[", wordChars, "]"); // regEx expression: '[\w][\w\-_ ]*[\w]' == "[\\w][\\w\\-_ ]*[\\w]"
	

	public static boolean isValidTag(Tag tag) {
		return tag.getName().matches("[\\w][\\w_ \\-]*[\\w]");
	}

}