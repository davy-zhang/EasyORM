package cc.d_z.easyorm.test.cache.impls.test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;

import cc.d_z.easyorm.beans.DAOBean;
import cc.d_z.easyorm.cache.ICache;
import cc.d_z.easyorm.cache.impls.GoogleCacheImpl;
import cc.d_z.easyorm.test.Test;
import cc.d_z.easyorm.test.dao.beans.People;

/**
 * @author davy <br>
 *         2014年2月23日 下午9:14:58 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class GoogleCacheImplTest extends Test {

	private ICache cache;

	private static final long ttl = 1000l;
	private static final long maxSize = 10l;

	@Before
	public void before() {
		cache = new GoogleCacheImpl(ttl, maxSize);
	}

	/**
	 * Test method for
	 * {@link cc.d_z.easyorm.cache.impls.GoogleCacheImpl#put(cc.d_z.easyorm.beans.DAOBean)}
	 * .
	 */
	@org.junit.Test
	public void testPut() {
		People people1 = new People();
		people1.id = 15l;
		People people2 = new People();
		people2.id = 15l;
		people2.name = "davy";
		cache.put(people1, people2);
	}

	/**
	 * Test method for
	 * {@link cc.d_z.easyorm.cache.impls.GoogleCacheImpl#get(cc.d_z.easyorm.beans.DAOBean)}
	 * .
	 */
	@org.junit.Test
	public void testGet() {
		testPut();
		People peopleOnlyID = new People();
		peopleOnlyID.id = 15l;
		People p = cache.get(peopleOnlyID);
		System.out.println(p);
	}

	/**
	 * Test method for
	 * {@link cc.d_z.easyorm.cache.impls.GoogleCacheImpl#remove(cc.d_z.easyorm.beans.DAOBean)}
	 * .
	 */
	@org.junit.Test
	public void testRemove() {
		testPut();
		People peopleOnlyID = new People();
		peopleOnlyID.id = 15l;
		cache.remove(peopleOnlyID);
		People p = cache.get(peopleOnlyID);
		System.out.println(p);
	}

	@org.junit.Test
	public void testTTL() {
		testPut();
		People peopleOnlyID = new People();
		peopleOnlyID.id = 16l;
		People p = cache.get(peopleOnlyID);
		System.out.println(p);
		try {
			TimeUnit.MILLISECONDS.sleep(ttl + 10000);
		} catch (InterruptedException e) {
			fail(e);
		}
		p = cache.get(peopleOnlyID);
		System.out.println(p);

	}

	@org.junit.Test
	public void testSize() {
		People people1 = new People();
		people1.id = 15l;
		people1.name = "davy";
		cache.put(people1,people1);

		People peopleOnlyID = new People();
		peopleOnlyID.id = 15l;
		People p = cache.get(peopleOnlyID);
		System.out.println(p);
		People people2 = new People();
		people2.id = 16l;
		people2.name = "davy2";
		cache.put(people2,people2);
		p = cache.get(peopleOnlyID);
		System.out.println(p);

	}

}
