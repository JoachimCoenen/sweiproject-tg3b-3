package base;

import org.junit.Test;

public class HomeControllerTest {

	private static final String ENDSEQUENCE = "</html>";

	@Test
	public void testIndex() {
		new HomeController().index().contains(ENDSEQUENCE);
	}

}
