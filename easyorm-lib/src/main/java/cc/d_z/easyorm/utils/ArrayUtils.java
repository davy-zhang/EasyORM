package cc.d_z.easyorm.utils;


public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {
	public static boolean isSame(Object[] array1,Object[] array2){
		boolean same=false;
		if(isSameLength(array1, array2)&&isSameType(array1, array2)){
			for(int i=0;i<array1.length;i++){
				same=array1[i].equals(array2[i])&&same;
			}
		}
		return same;
	}
}
