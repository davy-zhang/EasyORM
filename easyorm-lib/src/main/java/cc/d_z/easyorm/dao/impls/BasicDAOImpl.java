/**
 * 
 */
package cc.d_z.easyorm.dao.impls;

import static cc.d_z.easyorm.utils.FieldUtils.*;
import static cc.d_z.easyorm.utils.DAOBeanUtils.*;
import static org.apache.commons.lang3.StringUtils.*;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;

import cc.d_z.easyorm.annotations.Unique;
import cc.d_z.easyorm.beans.DAOBean;
import cc.d_z.easyorm.dao.IDAO;
import cc.d_z.easyorm.dao.MysqlDAO;
import cc.d_z.easyorm.utils.ArrayUtils;
import cc.d_z.easyorm.utils.EntryBuilder;

/**
 * @author davy <br>
 *         2014年1月21日 下午9:32:54 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class BasicDAOImpl extends MysqlDAO implements IDAO {

	private static final Logger logger = LoggerFactory.getLogger(BasicDAOImpl.class);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.d_z.easyorm.dao.IDAO#insert(cc.d_z.easyorm.beans.DAOBean)
	 */
	@Override
	public <D extends DAOBean> void insert(D daoBean) throws Exception {
		if (daoBean == null) {
			String message = "准备使用" + this.getClass().getSimpleName() + "插入空";
			logger.warn(message);
			throw new NullPointerException(message);
		}
		logger.trace("准备使用" + this.getClass().getSimpleName() + "向" + dbName + "库的" + getTableName(daoBean) + "表中插入" + daoBean);
		String[] notNullCols = getFieldsName(getNotNullFields(daoBean));
		logger.trace("notNullCols是:" + Arrays.toString(notNullCols));
		Object[] notNullVals = getNotNullValue(daoBean);
		logger.trace("notNullVals是:" + Arrays.toString(notNullVals));

		String sql = makeInsertSql(daoBean, notNullCols);
		logger.trace("创建出的sql:" + sql + ",args:" + Arrays.toString(notNullVals));
		try {
			getJdbcTemplate().update(sql, notNullVals);
			logger.trace("插入" + daoBean + "成功");
		} catch (Exception e) {
			logger.error("准备向" + dbName + "库的" + getTableName(daoBean) + "表中插入" + daoBean + "时报错", e);
			throw e;
		}

	}

	private <D extends DAOBean> String makeInsertSql(D daoBean, String[] notNullCols) {
		String tableName = getTableName(daoBean);

		String sql = "insert into `" + tableName + "` ( ";
		for (int i = 0; notNullCols != null && i < notNullCols.length; i++) {
			sql += " `" + notNullCols[i] + "` ,";
		}
		sql = removeEnd(sql, ",") + ") values ( ";
		for (int i = 0; notNullCols != null && i < notNullCols.length; i++) {
			sql += "? ,";
		}
		sql = removeEnd(sql, ",") + ")";
		return sql;
	}

	private <D extends DAOBean> String makeSelectSql(D daoBean, String[] notNullCols) {
		String sql = "select * from `" + getTableName(daoBean) + "`";
		if (notNullCols != null && notNullCols.length != 0) {
			sql += " where ";
			for (String col : notNullCols) {
				sql += " `" + col + "` = ? and";
			}
			sql = removeEnd(sql, "and");
		}
		return sql;
	}

	private <D extends DAOBean> String makeUpdateSql(D daoBean, String[] uniqueCols, String[] allCols) {
		String sql = "update `" + getTableName(daoBean) + "` set ";
		if (allCols != null && allCols.length != 0) {
			for (String col : allCols) {
				sql += " `" + col + "` = ? ,";
			}
			sql = removeEnd(sql, ",");
		}
		if (uniqueCols != null && uniqueCols.length != 0) {
			sql += " where ";
			for (String col : uniqueCols) {
				sql += " `" + col + "` = ? and";
			}
			sql = removeEnd(sql, "and");
		}
		return sql;
	}

	private <D extends DAOBean> String makeDeleteSql(D daoBean, String[] uniqueCols) {
		String sql = "delete from `" + getTableName(daoBean) + "` ";
		if (uniqueCols != null && uniqueCols.length != 0) {
			sql += " where ";
			for (String col : uniqueCols) {
				sql += " `" + col + "` = ? and";
			}
			sql = removeEnd(sql, "and");
		}
		return sql;
	}

	private <D extends DAOBean> String getTableName(D daoBean) {
		return lowerCase(daoBean.getClass().getSimpleName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.d_z.easyorm.dao.IDAO#delete(cc.d_z.easyorm.beans.DAOBean)
	 */
	@Override
	public <D extends DAOBean> void delete(D daoBean) throws Exception {
		if (daoBean == null) {
			String message = "准备使用" + this.getClass().getSimpleName() + "删除空";
			logger.warn(message);
			throw new NullPointerException(message);
		}
		Object[] args = null;
		String sql = null;
		Field[] uniqueFields = getUniqueField(daoBean);
		logger.trace("准备向" + dbName + "库的" + getTableName(daoBean) + "表中删除" + daoBean);
		if (uniqueFields != null && uniqueFields.length != 0) {
			String[] uniqueCols = getFieldsName(uniqueFields);
			logger.trace("uniqueCols是:" + Arrays.toString(uniqueCols));
			args = getFieldsValue(uniqueFields, daoBean);

			sql = makeDeleteSql(daoBean, uniqueCols);

		} else {
			String[] notNullCols = getFieldsName(getNotNullFields(daoBean));
			logger.trace("notNullCols是:" + Arrays.toString(notNullCols));
			args = getNotNullValue(daoBean);
			sql = makeDeleteSql(daoBean, notNullCols);
		}
		logger.trace("删除的sql:" + sql + ",args:" + Arrays.toString(args));
		try {
			getJdbcTemplate().update(sql, args);
			logger.trace("删除daoBean:" + daoBean + "成功");
		} catch (Exception e) {
			logger.error("准备向" + dbName + "库的" + getTableName(daoBean) + "表中删除" + daoBean + "时报错", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.d_z.easyorm.dao.IDAO#update(cc.d_z.easyorm.beans.DAOBean)
	 */
	@Override
	public <D extends DAOBean> void update(D daoBean) throws Exception {
		if (daoBean == null) {
			String message = "准备使用" + this.getClass().getSimpleName() + "更新空";
			logger.warn(message);
			throw new NullPointerException(message);
		}
		Field[] uniqueFields = getUniqueField(daoBean);

		if (uniqueFields == null || uniqueFields.length == 0)
			throw new Exception("必须有唯一性约束，才能更新，详见@Unique的文档");
		logger.trace("准备向" + dbName + "库的" + getTableName(daoBean) + "表中更新" + daoBean);
		String[] uniqueCols = getFieldsName(uniqueFields);
		logger.trace("uniqueCols是:" + Arrays.toString(uniqueCols));
		Object[] uniqueVals = getFieldsValue(uniqueFields, daoBean);
		logger.trace("uniqueVals是:" + Arrays.toString(uniqueVals));
		Field[] allFields = daoBean.getClass().getFields();
		String[] allCols = getFieldsName(allFields);
		logger.trace("allCols是:" + Arrays.toString(allCols));
		Object[] allVals = getFieldsValue(allFields, daoBean);
		logger.trace("allVals是:" + Arrays.toString(allVals));

		String sql = makeUpdateSql(daoBean, uniqueCols, allCols);
		Object[] arags = ArrayUtils.addAll(allVals, uniqueVals);
		logger.trace("更新的sql:" + sql + ",args:" + Arrays.toString(arags));
		try {
			getJdbcTemplate().update(sql, arags);
			logger.trace("更新daoBean:" + daoBean + "成功");
		} catch (Exception e) {
			logger.error("准备向" + dbName + "库的" + getTableName(daoBean) + "表中更新" + daoBean + "时报错", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.d_z.easyorm.dao.IDAO#select(cc.d_z.easyorm.beans.DAOBean)
	 */
	@Override
	public <D extends DAOBean> List<D> select(D daoBean) throws Exception {
		if (daoBean == null) {
			logger.warn("准备使用" + this.getClass().getSimpleName() + "查询空");
			return null;
		}
		logger.trace("准备向" + dbName + "库的" + getTableName(daoBean) + "表中查询" + daoBean);
		String[] notNullCols = getFieldsName(getNotNullFields(daoBean));
		logger.trace("notNullCols是:" + Arrays.toString(notNullCols));
		Object[] notNullVals = getNotNullValue(daoBean);
		logger.trace("notNullVals是:" + Arrays.toString(notNullVals));

		String sql = makeSelectSql(daoBean, notNullCols);
		logger.trace("查询的sql:" + sql + ",args:" + Arrays.toString(notNullVals));
		List<D> results = null;
		try {
			results = getJdbcTemplate().query(sql, notNullVals, new DAOBeanRowMapper<D>(daoBean));
		} catch (Exception e) {
			logger.error("准备向" + dbName + "库的" + getTableName(daoBean) + "表中查询" + daoBean + "时报错", e);
			throw e;
		}
		logger.trace("查询出的results:" + results + ",daoBean:" + daoBean);
		return results;
	}

	class DAOBeanRowMapper<D extends DAOBean> implements RowMapper<D> {

		private D d;

		/**
		 * 
		 */
		public DAOBeanRowMapper(D d) {
			this.d = d;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 * int)
		 */
		@Override
		public D mapRow(ResultSet rs, int rowNum) throws SQLException {
			D nd = null;
			try {
				// String tableName = rs.getMetaData().getTableName(1);
				int colCount = rs.getMetaData().getColumnCount();
				nd = (D) d.getClass().newInstance();
				for (int i = 1; i <= colCount; i++) {
					String colName = rs.getMetaData().getColumnName(i);
					Object colVal = rs.getObject(i);
					Field field = nd.getClass().getField(colName);
					if (field != null) {
						field.set(nd, colVal);
					}
				}
			} catch (Exception e) {
				logger.error("DAOBeanRowMapper出错", e);
			}
			return nd;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.d_z.easyorm.dao.IDAO#insert(cc.d_z.easyorm.beans.DAOBean[])
	 */
	@Override
	public <D extends DAOBean> List<Entry<D, Exception>> insert(D... daoBean) {
		List<Entry<D, Exception>> rs = new ArrayList<Entry<D, Exception>>();
		for (D d : daoBean) {
			try {
				insert(d);
			} catch (Exception e) {
				rs.add(EntryBuilder.build(d, e));
			}
		}
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.d_z.easyorm.dao.IDAO#selectOne(cc.d_z.easyorm.beans.DAOBean)
	 */
	@Override
	public <D extends DAOBean> D selectOne(D daoBean) throws Exception {
		List<D> ds = select(daoBean);
		if (ds != null && ds.size() != 0) {
			return ds.get(0);
		} else {
			return null;
		}
	}

}
