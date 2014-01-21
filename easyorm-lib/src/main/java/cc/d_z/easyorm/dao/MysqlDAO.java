package cc.d_z.easyorm.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The Class MysqlDAO.
 * 
 * @author davy <br>
 *         2014年1月19日 下午10:25:46 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 *         <p>
 *         如果你使用mysql做为DB,那么你可以直接继承该类,此类会自动创建连接池(使用dbcp),自动配置一些你不需要关心但实际中要有的配置。
 *         </p>
 */
public class MysqlDAO {

	/** The jdbc template. */
	protected JdbcTemplate jdbcTemplate;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MysqlDAO.class);

	/** The db name. */
	protected String dbName;

	/** The dbip. */
	protected String dbip;

	/** The dbport. */
	protected int dbport;

	/** The db user name. */
	protected String dbUserName;

	/** The db password. */
	protected String dbPassword;

	/**
	 * Instantiates a new mysql dao.
	 * 
	 * @param dbName
	 *            the db name
	 * @param dbip
	 *            the dbip
	 * @param dbport
	 *            the dbport
	 * @param dbUserName
	 *            the db user name
	 * @param dbPassword
	 *            the db password
	 */
	public MysqlDAO(String dbName, String dbip, int dbport, String dbUserName, String dbPassword) {
		super();
		this.dbName = dbName;
		this.dbip = dbip;
		this.dbport = dbport;
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassword;
		this.jdbcTemplate = createJdbcTemplate();
		logger.debug("构建了" + this);
	}

	/**
	 * <p>
	 * 这个方法可以获取一个spring的JdbcTemplate,如果默认的DAO的方法无法满足你的需求,你可以调用此方法来执行一些复杂的sql。
	 * </p>
	 * .
	 * 
	 * @return the jdbc template
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * Creates the jdbc template.
	 * 
	 * @return the jdbc template
	 */
	private JdbcTemplate createJdbcTemplate() {
		DataSource dataSource = createDataSource();
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		return jt;
	}

	/**
	 * Creates the data source.
	 * 
	 * @return the data source
	 */
	private DataSource createDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		setDataSourceCommon(dataSource);
		String url = makeUrl();
		dataSource.setUrl(url);
		dataSource.setUsername(dbUserName);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	/**
	 * Make url.
	 * 
	 * @return the string
	 */
	private String makeUrl() {
		return "jdbc:mysql://" + dbip + ":" + dbport + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8";
	}

	/**
	 * Sets the data source common.
	 * 
	 * @param dataSource
	 *            the new data source common
	 */
	private void setDataSourceCommon(BasicDataSource dataSource) {
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setInitialSize(5);
		dataSource.setMaxActive(30);
		dataSource.setMaxIdle(5);
		dataSource.setMaxWait(-1);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(3600000);
		dataSource.setMinEvictableIdleTimeMillis(18000000);
		dataSource.setTestOnBorrow(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MysqlDao [dbName=" + dbName + ", dbip=" + dbip + ", dbport=" + dbport + ", dbUserName=" + dbUserName + ", dbPassword=" + dbPassword + "]";
	}

}
