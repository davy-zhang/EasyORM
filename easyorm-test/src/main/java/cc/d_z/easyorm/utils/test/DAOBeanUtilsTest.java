package cc.d_z.easyorm.utils.test;

import static cc.d_z.easyorm.utils.DAOBeanUtils.getNotNullFields;
import static cc.d_z.easyorm.utils.DAOBeanUtils.getNotNullValue;
import static cc.d_z.easyorm.utils.DAOBeanUtils.getUniqueField;
import static cc.d_z.easyorm.utils.DAOBeanUtils.getValue;
import static cc.d_z.easyorm.utils.DAOBeanUtils.isSame;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import static cc.d_z.easyorm.utils.FieldUtils.*;

import java.lang.reflect.Field;

import org.junit.Test;

import cc.d_z.easyorm.test.dao.beans.People;

/**
 * The Class DAOBeanUtilsTest.
 * 
 * @author davy <br>
 *         2014年1月22日 上午2:20:51 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class DAOBeanUtilsTest extends cc.d_z.easyorm.test.Test {

	/**
	 * Test method for.
	 * 
	 * {@link cc.d_z.easyorm.utils.DAOBeanUtils#getNotNullFields(cc.d_z.easyorm.beans.DAOBean)}
	 * .
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
		} catch (Exception e) {
			fail(e);
		}
	}

	/**
	 * Test method for.
	 * 
	 * {@link cc.d_z.easyorm.utils.DAOBeanUtils#getNotNullValue(cc.d_z.easyorm.beans.DAOBean)}
	 * .
	 */
	@Test
	public final void testGetNotNullValue() {
		try {
			People people = new People();
			Object[] values = new Object[] { 1l, "davy" };
			people.id = 1l;
			people.name = "davy";
			Object[] notNullValue = getNotNullValue(people);
			assertArrayEquals(values, notNullValue);
		} catch (Exception e) {
			fail(e);
		}
	}

	/**
	 * Test method for.
	 * 
	 * {@link cc.d_z.easyorm.utils.DAOBeanUtils#isSame(cc.d_z.easyorm.beans.DAOBean, java.lang.reflect.Field[], cc.d_z.easyorm.beans.DAOBean, java.lang.reflect.Field[])}
	 * .
	 */
	@Test
	public final void testIsSame() {
		try {
			Field[] fields1 = new Field[] { People.class.getField("name"), People.class.getField("id") };
			Field[] fields2 = new Field[] { People.class.getField("id"), People.class.getField("name") };
			People people1 = new People();
			people1.id = 1l;
			people1.name = "davy";
			people1.age = 25;
			People people2 = new People();
			people2.id = 1l;
			people2.name = "davy";
			people2.age = 24;
			assertTrue(isSame(people1, fields1, people2, fields2));
		} catch (Exception e) {
			fail(e);
		}
	}

	/**
	 * Test method for.
	 * 
	 * {@link cc.d_z.easyorm.utils.DAOBeanUtils#getValue(cc.d_z.easyorm.beans.DAOBean, Field[])}
	 * .
	 */
	@Test
	public final void testGetValue() {
		try {
			final Object[] returnValues = new Object[] { 1l, "davy" };
			People people = new People();
			people.id = 1l;
			people.name = "davy";
			Field[] fields = new Field[] { people.getClass().getField("id"), people.getClass().getField("name") };
			Object[] values = getValue(people, fields);
			assertArrayEquals(returnValues, values);
		} catch (Exception e) {
			fail(e);
		}
	}


	/**
	 * Test method for.
	 * 
	 * {@link cc.d_z.easyorm.utils.DAOBeanUtils#getUniqueField(cc.d_z.easyorm.beans.DAOBean)
	 * ;
	 */
	@Test
	public final void testGetUniqueField() {
		try {
			People people = new People();
			Field[] fields1 = getUniqueField(people);
			Field[] fields2 = new Field[] { People.class.getField("id") };
			fields1 = sortField(fields1);
			fields2 = sortField(fields2);
			assertArrayEquals(fields1, fields2);
		} catch (Exception e) {
			fail(e);
		}
	}
}
