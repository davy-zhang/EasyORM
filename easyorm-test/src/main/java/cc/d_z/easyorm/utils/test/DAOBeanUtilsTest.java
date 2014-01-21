package cc.d_z.easyorm.utils.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import cc.d_z.easyorm.test.dao.beans.People;
import cc.d_z.easyorm.utils.ArrayUtils;
import static cc.d_z.easyorm.utils.DAOBeanUtils.*;

/**
 * @author davy <br>
 *         2014年1月22日 上午2:20:51 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class DAOBeanUtilsTest extends cc.d_z.easyorm.test.Test{

	/**
	 * Test method for
	 * {@link cc.d_z.easyorm.utils.DAOBeanUtils#getNotNullFields(cc.d_z.easyorm.beans.DAOBean)}
	 * .
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public final void testGetNotNullFields() {
		try {
			People people = new People();
			Field[] fields = new Field[] { people.getClass().getField("id"), people.getClass().getField("name") };
			people.id = 1l;
			people.name = "davy";
			Field[] notNullFields = getNotNullFields(people);
			assertArrayEquals(fields, notNullFields);
		} catch (Exception e){
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link cc.d_z.easyorm.utils.DAOBeanUtils#isSame(cc.d_z.easyorm.beans.DAOBean, java.lang.reflect.Field[], cc.d_z.easyorm.beans.DAOBean, java.lang.reflect.Field[])}
	 * .
	 */
	@Test
	public final void testIsSame() {
		fail();
	}

}
