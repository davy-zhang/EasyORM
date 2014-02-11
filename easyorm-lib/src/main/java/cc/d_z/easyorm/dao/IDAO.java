package cc.d_z.easyorm.dao;

import java.util.List;
import java.util.Map.Entry;

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

public interface IDAO {

	/**
	 * Insert.
	 * 
	 * @param <D>
	 *            the generic type
	 * @param daoBean
	 *            the dao bean
	 * @return the list
	 */
	public <D extends DAOBean> List<Entry<D, Exception>> insert(D... daoBean);

	/**
	 * Insert.
	 * 
	 * @param <D>
	 *            the generic type
	 * @param daoBean
	 *            the dao bean
	 * @throws Exception
	 *             the exception
	 */
	public <D extends DAOBean> void insert(D daoBean) throws Exception;

	/**
	 * Delete.
	 * 
	 * @param <D>
	 *            the generic type
	 * @param daoBean
	 *            the dao bean
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public <D extends DAOBean> void delete(D daoBean) throws Exception;

	/**
	 * Update.
	 * 
	 * @param <D>
	 *            the generic type
	 * @param daoBean
	 *            the dao bean
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public <D extends DAOBean> void update(D daoBean) throws Exception;

	/**
	 * Select.
	 * 
	 * @param <D>
	 *            the generic type
	 * @param daoBean
	 *            the dao bean
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public <D extends DAOBean> List<D> select(D daoBean) throws Exception;

}
