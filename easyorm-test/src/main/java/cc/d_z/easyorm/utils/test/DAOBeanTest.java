package cc.d_z.easyorm.utils.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cc.d_z.easyorm.test.dao.beans.People;

/**
 * @author davy <br>
 *         2014年1月22日 上午2:43:45 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class DAOBeanTest extends cc.d_z.easyorm.test.Test{

	/**
	 * Test method for
	 * {@link cc.d_z.easyorm.beans.DAOBean#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		fail();
	}

	/**
	 * Test method for {@link cc.d_z.easyorm.beans.DAOBean#toString()}.
	 */
	@Test
	public void testToString() {
		final String toString = "People [id=1, name=davy, age=null]";
		People people = new People();
		people.id = 1l;
		people.name = "davy";
		assertEquals(people.toString(), toString);
	}

}
