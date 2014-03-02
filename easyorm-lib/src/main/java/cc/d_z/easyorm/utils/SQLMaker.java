package cc.d_z.easyorm.utils;

import static cc.d_z.easyorm.utils.DAOBeanUtils.getNotNullFields;
import static cc.d_z.easyorm.utils.FieldUtils.getFieldsName;
import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.lang3.StringUtils.removeEnd;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.d_z.easyorm.beans.DAOBean;

/**
 * @author davy <br>
 *         2014年2月23日 下午9:34:06 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class SQLMaker {
	private static final Logger logger = LoggerFactory.getLogger(SQLMaker.class);

	public static <D extends DAOBean> String makeInsertSQL(D daoBean, String[] notNullCols) {
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

	public static <D extends DAOBean> String makeSelectSQL(D daoBean) throws IllegalArgumentException, IllegalAccessException {
		String[] notNullCols = getFieldsName(getNotNullFields(daoBean));
		logger.trace("notNullCols是:" + Arrays.toString(notNullCols));
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
	
	public static <D extends DAOBean> String makeSelectOneSQL(D daoBean) throws IllegalArgumentException, IllegalAccessException {
		String sql=makeSelectSQL(daoBean);
		sql+=" limit 1";
		return sql;
	}
	
	
	

	public static <D extends DAOBean> String makeUpdateSQL(D daoBean, String[] uniqueCols, String[] allCols) {
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

	public static <D extends DAOBean> String makeDeleteSQL(D daoBean, String[] uniqueCols) {
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

	public static <D extends DAOBean> String getTableName(D daoBean) {
		return lowerCase(daoBean.getClass().getSimpleName());
	}
}
