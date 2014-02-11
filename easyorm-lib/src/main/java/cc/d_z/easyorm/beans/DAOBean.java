package cc.d_z.easyorm.beans;

import java.io.Serializable;
import java.lang.reflect.Field;

import static org.apache.commons.lang3.ArrayUtils.*;
import static org.apache.commons.lang3.StringUtils.*;
import static cc.d_z.easyorm.utils.DAOBeanUtils.*;

/**
 * The Class DAOBean.
 * 
 * @author davy <br>
 *         2014年1月12日 下午10:04:35 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class DAOBean implements Serializable{

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;
		return equals((DAOBean) other);
	}

	private boolean equals(DAOBean other) {
		try {
			Field[] thisUniqueFields = getUniqueField(this);
			Field[] otherUniqueFields = getUniqueField(other);
			if (thisUniqueFields.length != 0) {
				return isSame(this, thisUniqueFields, other, otherUniqueFields);
			} else {
				Field[] thisFields = getNotNullFields(this);
				Field[] otherFields = getNotNullFields(other);
				return isSame(this, thisFields, other, otherFields);
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getSimpleName()).append(" [");
		Field[] fields = this.getClass().getFields();
		if (isNotEmpty(fields)) {
			for (Field field : fields) {
				try {
					sb.append(field.getName()).append("=").append(field.get(this)).append(", ");
				} catch (Exception e) {
					sb.append("获取值时异常");
				}
			}
			sb = new StringBuffer(removeEnd(sb.toString(), ", "));
		}
		sb.append("]");
		return sb.toString();
	}
}
