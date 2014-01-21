package cc.d_z.easyorm.dao;

import java.util.List;

import cc.d_z.easyorm.beans.DAOBean;

// TODO: Auto-generated Javadoc
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
	 * @param <B>
	 *            the generic type
	 * @param bs
	 *            the bs
	 * @return the list
	 */
	public <B extends DAOBean> List<B> insert(B... bs);

	/**
	 * Delete.
	 * 
	 * @param <B>
	 *            the generic type
	 * @param bs
	 *            the bs
	 * @return the list
	 */
	public <B extends DAOBean> List<B> delete(B... bs);

	/**
	 * Update.
	 * 
	 * @param <B>
	 *            the generic type
	 * @param bs
	 *            the bs
	 * @return the list
	 */
	public <B extends DAOBean> List<B> update(B... bs);

	/**
	 * Select.
	 * 
	 * @param <B>
	 *            the generic type
	 * @param bs
	 *            the bs
	 * @return the list
	 */
	public <B extends DAOBean> List<B> select(B... bs) throws Exception;

}
