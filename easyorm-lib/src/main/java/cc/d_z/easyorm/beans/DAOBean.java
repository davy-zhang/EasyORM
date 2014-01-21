package cc.d_z.easyorm.beans;

import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * The Class DAOBean.
 * 
 * @author davy <br>
 *         2014年1月12日 下午10:04:35 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class DAOBean {
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getSimpleName()).append(" [");
		Field[] fields = this.getClass().getFields();
		if (ArrayUtils.isNotEmpty(fields)) {
			for (Field field : fields) {
				try {
					sb.append(field.getName()).append("=").append(field.get(this)).append(", ");
				} catch (Exception e) {
					sb.append("获取值时异常");
				}
			}
			sb = new StringBuffer(StringUtils.removeEnd(sb.toString(), ", "));
		}
		sb.append("]");
		return sb.toString();
	}
}
