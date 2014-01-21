package cc.d_z.easyorm.dao;

import java.util.List;

import cc.d_z.easyorm.beans.DAOBean;

/**
 * The Interface IDAO.
 * 
 * @author davy <br>
 *         2014年1月19日 下午10:25:17 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */

//TODO 此接口需要改动
public interface IDAO {

	/**
	 * Insert.
	 * 
	 * @param <D>
	 *            the generic type
	 * @param bs
	 *            the bs
	 * @return the list
	 */
	public <D extends DAOBean> List<D> insert(D... bs);

	/**
	 * Delete.
	 * 
	 * @param <D>
	 *            the generic type
	 * @param bs
	 *            the bs
	 * @return the list
	 */
	public <D extends DAOBean> List<D> delete(D... bs);

	/**
	 * Update.
	 * 
	 * @param <D>
	 *            the generic type
	 * @param bs
	 *            the bs
	 * @return the list
	 */
	public <D extends DAOBean> List<D> update(D... bs);

	/**
	 * Select.
	 * 
	 * @param <D>
	 *            the generic type
	 * @param bs
	 *            the bs
	 * @return the list
	 */
	public <D extends DAOBean> List<D> select(D... bs) throws Exception;

}
