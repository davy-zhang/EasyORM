package cc.d_z.easyorm.utils;

import static cc.d_z.easyorm.utils.FieldUtils.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cc.d_z.easyorm.annotations.Unique;
import cc.d_z.easyorm.beans.DAOBean;

public class DAOBeanUtils {
	private enum Type {
		field, value;
	}

	@SuppressWarnings("unchecked")
	public static Field[] getNotNullFields(DAOBean bean) throws IllegalArgumentException, IllegalAccessException {
		List<Field> fieldList = getNotNull(bean, Type.field);
		return (Field[]) fieldList.toArray(new Field[fieldList.size()]);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	public static boolean isSame(DAOBean daoBean, Field[] thisFields, DAOBean other, Field[] otherFields) throws IllegalArgumentException, IllegalAccessException {
		thisFields = sortField(thisFields);
		otherFields = sortField(otherFields);
		boolean fieldSame = daoBean != null && other != null && ArrayUtils.isSame(thisFields, otherFields);
		boolean valueSame = false;
		if (fieldSame) {
			Object[] daoBeanValue = getValue(daoBean, thisFields);
			Object[] otherValue = getValue(other, otherFields);
			valueSame = ArrayUtils.isSame(daoBeanValue, otherValue);
		}
		return fieldSame && valueSame;
	}

	public static Object[] getValue(DAOBean daoBean, Field[] fields) throws IllegalArgumentException, IllegalAccessException {
		if (fields == null)
			return null;
		Object[] values = new Object[fields.length];
		if (daoBean != null) {
			for (int i = 0; i < fields.length; i++) {
				Object value = fields[i].get(daoBean);
				values[i] = value;
			}
		}
		return values;
	}

	public static Object[] getNotNullValue(DAOBean daoBean) throws IllegalArgumentException, IllegalAccessException {
		@SuppressWarnings("unchecked")
		List<Object> fieldList = getNotNull(daoBean, Type.value);
		return (Object[]) fieldList.toArray();
	}

	public static Field[] getUniqueField(DAOBean daoBean) {
		if (daoBean == null)
			return null;
		Field[] fields = daoBean.getClass().getFields();
		List<Field> uniqueFields = new ArrayList<Field>();
		for (Field field : fields)
			if (field.isAnnotationPresent(Unique.class))
				uniqueFields.add(field);
		return uniqueFields.toArray(new Field[uniqueFields.size()]);
	}
}
