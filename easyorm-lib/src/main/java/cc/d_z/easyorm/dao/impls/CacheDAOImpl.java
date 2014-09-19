package cc.d_z.easyorm.dao.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.d_z.easyorm.beans.DAOBean;
import cc.d_z.easyorm.cache.ICache;
import cc.d_z.easyorm.cache.impls.GoogleCacheImpl;
import cc.d_z.easyorm.dao.IDAO;

/**
 * @author davy <br>
 *         2014年3月2日 下午10:04:54 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class CacheDAOImpl extends BasicDAOImpl implements IDAO {
	private static final Logger logger = LoggerFactory.getLogger(CacheDAOImpl.class);
	protected ICache cache;

	/**
	 * @param dbName
	 * @param dbip
	 * @param dbport
	 * @param dbUserName
	 * @param dbPassword
	 */
	public CacheDAOImpl(String dbName, String dbip, int dbport, String dbUserName, String dbPassword) {
		super(dbName, dbip, dbport, dbUserName, dbPassword);
		cache = new GoogleCacheImpl();
	}

	/**
	 * @param dbName
	 * @param dbip
	 * @param dbport
	 * @param dbUserName
	 * @param dbPassword
	 */
	public CacheDAOImpl(String dbName, String dbip, int dbport, String dbUserName, String dbPassword, long ttlInMs, long maxSize) {
		super(dbName, dbip, dbport, dbUserName, dbPassword);
		cache = new GoogleCacheImpl(ttlInMs, maxSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cc.d_z.easyorm.dao.impls.BasicDAOImpl#delete(cc.d_z.easyorm.beans.DAOBean
	 * )
	 */
	@Override
	public <D extends DAOBean> void delete(D daoBean) throws Exception {
		super.delete(daoBean);
		cache.remove(daoBean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cc.d_z.easyorm.dao.impls.BasicDAOImpl#update(cc.d_z.easyorm.beans.DAOBean
	 * )
	 */
	@Override
	public <D extends DAOBean> void update(D daoBean) throws Exception {
		super.update(daoBean);
		cache.remove(daoBean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cc.d_z.easyorm.dao.impls.BasicDAOImpl#selectOne(cc.d_z.easyorm.beans.
	 * DAOBean)
	 */
	@Override
	public <D extends DAOBean> D selectOne(D daoBean) throws Exception {
		D value = cache.get(daoBean);
		if (value == null) {
			value = super.selectOne(daoBean);
			if (value != null)
				cache.put(daoBean, value);
		}
		return value;
	}
}
