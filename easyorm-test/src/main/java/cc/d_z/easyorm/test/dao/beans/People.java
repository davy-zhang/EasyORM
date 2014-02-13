package cc.d_z.easyorm.test.dao.beans;

import cc.d_z.easyorm.annotations.Unique;
import cc.d_z.easyorm.beans.DAOBean;

/**
 * @author davy <br>
 *         2014年1月22日 上午2:42:24 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class People extends DAOBean {
	@Unique
	public Long id;
	public String name;
	public Integer age;
	public Double hight;
}
