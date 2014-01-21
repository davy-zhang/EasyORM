/**
 * 
 */
package cc.d_z.easyorm.dao.impls;

import java.util.List;

import cc.d_z.easyorm.beans.DAOBean;
import cc.d_z.easyorm.dao.IDAO;
import cc.d_z.easyorm.dao.MysqlDAO;

/**
 * @author davy <br>
 * 2014年1月21日 下午9:32:54 <br>
 * <B>The default encoding is UTF-8 </B><br>
 * email: davy@d-z.cc<br>
 * <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class BasicDAOImpl extends MysqlDAO implements IDAO{

	/**
	 * @param dbName
	 * @param dbip
	 * @param dbport
	 * @param dbUserName
	 * @param dbPassword
	 */
	public BasicDAOImpl(String dbName, String dbip, int dbport, String dbUserName, String dbPassword) {
		super(dbName, dbip, dbport, dbUserName, dbPassword);
	}

	/* (non-Javadoc)
	 * @see cc.d_z.easyorm.dao.IDAO#insert(cc.d_z.easyorm.beans.DAOBean[])
	 */
	@Override
	public <B extends DAOBean> List<B> insert(B... bs) {
		// TODO BasicDAOImpl的insert方法还没实现，
		return null;
	}

	/* (non-Javadoc)
	 * @see cc.d_z.easyorm.dao.IDAO#delete(cc.d_z.easyorm.beans.DAOBean[])
	 */
	@Override
	public <B extends DAOBean> List<B> delete(B... bs) {
		// TODO BasicDAOImpl的delete方法还没实现
		return null;
	}

	/* (non-Javadoc)
	 * @see cc.d_z.easyorm.dao.IDAO#update(cc.d_z.easyorm.beans.DAOBean[])
	 */
	@Override
	public <B extends DAOBean> List<B> update(B... bs) {
		// TODO BasicDAOImpl的update方法还没实现
		return null;
	}

	/* (non-Javadoc)
	 * @see cc.d_z.easyorm.dao.IDAO#select(cc.d_z.easyorm.beans.DAOBean[])
	 */
	@Override
	public <B extends DAOBean> List<B> select(B... bs) throws Exception {
		// TODO BasicDAOImpl的select方法还没实现
		return null;
	}
 
}
