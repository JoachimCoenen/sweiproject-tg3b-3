package base.activitymeter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActivityControllerTest {

	private static final Long ID = (long) 42;
	private static final Long NONSENSE_TEST_ID = (long) 666;

	@Test(expected = NullPointerException.class)
	public void testListAll() {
		assertNotNull(new ActivityController().listAll());
	}

	@Test(expected = NullPointerException.class)
	public void testFind() {
		assertNull(new ActivityController().find(ID));
	}

	@Test(expected = NullPointerException.class)
	public void testDelete() {
		new ActivityController().delete(ID);
	}

	@Test(expected = NullPointerException.class)
	public void testCreate() {
		new ActivityController().create(new Activity());
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateNull() {
		assertNull(new ActivityController().update(NONSENSE_TEST_ID, new Activity()));
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateNotNull() {
		assertNotNull(new ActivityController().update(ID, new Activity()));
	}

}
