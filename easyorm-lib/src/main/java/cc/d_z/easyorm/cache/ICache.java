package cc.d_z.easyorm.cache;

import cc.d_z.easyorm.beans.DAOBean;

/**
 * @author davy <br>
 *         2014年2月23日 下午8:27:11 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public interface ICache {
	public <D extends DAOBean> void put(D key,D value);

	public <D extends DAOBean> D get(D key);

	public <D extends DAOBean> void remove(D key);
}
