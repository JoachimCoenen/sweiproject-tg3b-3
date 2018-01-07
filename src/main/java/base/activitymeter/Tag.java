package base.activitymeter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {

	
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
	public static final String SEPARATOR_CHARS = "_ \\-";
	
	public static final String WORD_CHARS = "\\w";
	
	public static final String VALID_CHARS = SEPARATOR_CHARS + WORD_CHARS;
	
	public static final String VALID_TAG_REGEX = String.join("", "[", WORD_CHARS, "][", VALID_CHARS, "]*[", WORD_CHARS, "]"); // regEx expression: '[\w][\w\-_ ]*[\w]' == "[\\w][\\w\\-_ ]*[\\w]"
	

	public static boolean isValidTag(Tag tag) {
		return tag.getName().matches("[\\w][\\w_ \\-]*[\\w]");
	}

}