package cc.d_z.easyorm.utils.test;

import static org.junit.Assert.assertArrayEquals;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Test;

import cc.d_z.easyorm.test.dao.beans.People;
import static cc.d_z.easyorm.utils.FieldUtils.*;

/**
 * @author davy <br>
 *         2014年2月10日 上午10:37:59 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class FieldUtilsTest extends cc.d_z.easyorm.test.Test {

	/**
	 * Test method for.
	 * 
	 * {@link cc.d_z.easyorm.utils.FieldUtils#sortField(Field[]);
	 */
	@Test
	public final void testSortField() {
		try {
			Field[] fields1 = new Field[] { People.class.getField("name"), People.class.getField("id") };
			Field[] fields2 = new Field[] { People.class.getField("id"), People.class.getField("name") };
			fields1 = sortField(fields1);
			fields2 = sortField(fields2);
			assertArrayEquals(fields1, fields2);
		} catch (Exception e) {
			fail(e);
		}
	}
	/**
	 * Test method for.
	 * 
	 * {@link cc.d_z.easyorm.utils.FieldUtils#getFieldsName(Field[]);
	 */
	@Test
	public final void testGetFieldNames() {
		People people = new People();
		String[] names1 = new String[] { "id", "name", "age" };
		Field[] fields = people.getClass().getFields();
		String[] names2 = getFieldsName(fields);
		Arrays.sort(names1);
		Arrays.sort(names2);
		assertArrayEquals(names1, names2);
	}
	/**
	 * Test method for.
	 * 
	 * {@link cc.d_z.easyorm.utils.FieldUtils#getFieldsValue(Field[], Object);
	 */
	@Test
	public final void testGetFieldValues() {
		People people = new People();
		people.id=1l;
		people.name="davy";
		people.age=25;
		Object[] values1 = new Object[] { 1l, "davy", 25 };
		Field[] fields = people.getClass().getFields();
		Object[] values2 = null;
		try {
			values2 = getFieldsValue(fields, people);
		} catch (IllegalAccessException e) {
			fail(e);
		}
		assertArrayEquals(values1, values2);
	}

}
