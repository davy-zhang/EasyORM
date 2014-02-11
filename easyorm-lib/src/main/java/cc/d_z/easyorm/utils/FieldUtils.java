package cc.d_z.easyorm.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author davy <br>
 *         2014年2月10日 下午10:29:32 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class FieldUtils extends org.apache.commons.lang3.reflect.FieldUtils {
	public static Field[] sortField(Field[] fields) {
		if (fields == null)
			return null;
		List<Field> fieldList = Arrays.asList(fields);
		Collections.sort(fieldList, new Comparator<Field>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 * java.lang.Object)
			 */
			@Override
			public int compare(Field f1, Field f2) {
				return f1.getName().hashCode() - f2.getName().hashCode();
			}
		});
		return fieldList.toArray(new Field[fieldList.size()]);
	}

	public static String[] getFieldsName(Field[] fields) {
		if (fields == null || fields.length == 0)
			return null;
		String[] s = new String[fields.length];
		for (int i = 0; i < fields.length; i++)
			s[i] = fields[i].getName();
		return s;
	}

	public static Object[] getFieldsValue(Field[] fields, Object obj) throws IllegalAccessException {
		if (fields == null || fields.length == 0 || obj == null)
			return null;
		Object[] os = new Object[fields.length];
		for (int i = 0; i < fields.length; i++) {
			os[i] = readField(obj,  fields[i].getName());
		}
		return os;

	}
}
