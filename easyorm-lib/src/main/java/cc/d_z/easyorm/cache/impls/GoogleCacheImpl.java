package cc.d_z.easyorm.cache.impls;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.d_z.easyorm.beans.DAOBean;
import cc.d_z.easyorm.cache.ICache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * @author davy <br>
 *         2014年2月23日 下午8:39:08 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class GoogleCacheImpl implements ICache {
	private final Cache<DAOBean, DAOBean> cache;
	private static final Logger logger = LoggerFactory.getLogger(GoogleCacheImpl.class);

	public GoogleCacheImpl() {
		this(3600 * 1000l, 10000l);
	}

	public GoogleCacheImpl(long ttlInMs, long maxSize) {
		cache = CacheBuilder.newBuilder().expireAfterAccess(ttlInMs, TimeUnit.MILLISECONDS).maximumSize(maxSize).removalListener(new RemovalListener<DAOBean, DAOBean>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.google.common.cache.RemovalListener#onRemoval(com.google.
			 * common.cache.RemovalNotification)
			 */
			@Override
			public void onRemoval(RemovalNotification<DAOBean, DAOBean> notification) {
				logger.trace("缓存中的" + notification.getKey() + "由于" + notification.getCause() + "的原因，被删除了");
			}
		}).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.d_z.easyorm.cache.ICache#put(cc.d_z.easyorm.beans.DAOBean)
	 */
	@Override
	public <D extends DAOBean> void put(D key, D value) {
		if (key != null && value != null) {
			cache.put(key, value);
			logger.trace("将key:" + key + ",value:" + value + "放入缓存");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.d_z.easyorm.cache.ICache#get(cc.d_z.easyorm.beans.DAOBean)
	 */
	@Override
	public <D extends DAOBean> D get(D daoBean) {
		return (D) cache.getIfPresent(daoBean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.d_z.easyorm.cache.ICache#remove(cc.d_z.easyorm.beans.DAOBean)
	 */
	@Override
	public <D extends DAOBean> void remove(D daoBean) {
		cache.invalidate(daoBean);
	}

}
