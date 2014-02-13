package cc.d_z.easyorm.test.dao.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import cc.d_z.easyorm.dao.IDAO;
import cc.d_z.easyorm.dao.impls.BasicDAOImpl;
import cc.d_z.easyorm.test.dao.beans.People;

/**
 * @author davy <br>
 *         2014年2月10日 下午7:50:31 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class BasicDaoImplTest extends cc.d_z.easyorm.test.Test {
	private static final IDAO dao = new BasicDAOImpl("test", "me", 3306, "root", "");

	/**
	 * Test method for
	 * {@link cc.d_z.easyorm.dao.impls.BasicDAOImpl#insert(cc.d_z.easyorm.beans.DAOBean)}
	 * .
	 */
	@Test
	public void testInsertD() {
		People people = new People();
		people.age = 25;
		people.name = "davy";
		people.hight=1.82;
		try {
			dao.insert(people);
		} catch (Exception e) {
			fail(e);
		}
	}

	/**
	 * Test method for
	 * {@link cc.d_z.easyorm.dao.impls.BasicDAOImpl#delete(cc.d_z.easyorm.beans.DAOBean)}
	 * .
	 */
	@Test
	public void testDelete() {
		People people = new People();
		people.id = 3l;
//		people.name = "davy";
		people.age = 26;
		try {
			dao.delete(people);
		} catch (Exception e) {
			fail(e);
		}
	}

	/**
	 * Test method for
	 * {@link cc.d_z.easyorm.dao.impls.BasicDAOImpl#update(cc.d_z.easyorm.beans.DAOBean)}
	 * .
	 */
	@Test
	public void testUpdate() {
		People people = new People();
		people.id = 15l;
		people.name = "davy";
		people.age = 25;
		people.hight=1.83;
		try {
			dao.update(people);
		} catch (Exception e) {
			fail(e);
		}
	}

	/**
	 * Test method for
	 * {@link cc.d_z.easyorm.dao.impls.BasicDAOImpl#select(cc.d_z.easyorm.beans.DAOBean)}
	 * .
	 */
	@Test
	public void testSelect() {
		People people = new People();
		try {
			List<People> ps = dao.select(people);
			for (People p : ps) {
				System.out.println(p);
			}
		} catch (Exception e) {
			fail(e);
		}
	}

	/**
	 * Test method for {@link cc.d_z.easyorm.dao.impls.BasicDAOImpl#insert(D[])}
	 * .
	 */
	public void testInsertDArray() {
		People people1 = new People();
		people1.age = 25;
		people1.id = 1l;
		people1.name = "davy1";
		People people2 = new People();
		people2.id = 2l;
		people2.age = 25;
		people2.name = "davy2";
		List<Entry<People, Exception>> rs = dao.insert(people1, people2);
		for (Entry<People, Exception> entry : rs) {
			System.out.println(entry.getKey());
			entry.getValue().printStackTrace();
		}
	}
}
