package cc.d_z.easyorm.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cc.d_z.easyorm.beans.DAOBean;

public class DAOBeanUtils {
	private enum Type {
		field, value;
	}

	public static Field[] getNotNullFields(DAOBean bean) throws IllegalArgumentException, IllegalAccessException {
		List fieldList = getNotNull(bean, Type.field);
		return (Field[]) fieldList.toArray(new Field[fieldList.size()]);
	}

	@SuppressWarnings("rawtypes")
	private static List getNotNull(DAOBean bean, Type type) throws IllegalArgumentException, IllegalAccessException {
		List list = new ArrayList();
		if (bean != null) {
			Field[] fields = bean.getClass().getFields();
			for (Field field : fields) {
				Object value = field.get(bean);
				if (value != null && type != null)
					list.add(type.equals(Type.field) ? field : value);
			}
		}
		return list;
	}

	
	//TODO isSame还没实现完
	public static boolean isSame(DAOBean daoBean, Field[] thisFields, DAOBean other, Field[] otherFields) {
		boolean fieldSame = daoBean != null && other != null && ArrayUtils.isSame(thisFields, otherFields);
		boolean valueSame = false;
		if (fieldSame) {
//			fo
		}
		return fieldSame && valueSame;
	}

	//TODO getNotNullValue需要实现
//	public static Object[] getNotNullValue(DAOBean daoBean) {
//		List<Object> valueList = new ArrayList<Field>();
//		if (bean != null) {
//			Field[] fields = bean.getClass().getFields();
//			for (Field field : fields) {
//				Object value = field.get(bean);
//				if (value != null)
//					fieldList.add(field);
//			}
//		}
//		return fieldList.toArray(new Field[fieldList.size()]);
//	}
}
