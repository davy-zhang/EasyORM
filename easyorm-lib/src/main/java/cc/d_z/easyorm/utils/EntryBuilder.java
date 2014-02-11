package cc.d_z.easyorm.utils;

import java.util.Map.Entry;

/**
 * @author davy <br>
 *         2014年1月27日 下午6:36:18 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class EntryBuilder {
	public static <K, V> Entry<K, V> build(final K key, final V value) {
		return new Entry<K, V>() {

			@Override
			public K getKey() {
				return key;
			}

			@Override
			public V getValue() {
				return value;
			}

			@Override
			public V setValue(V value) {
				return value;
			}
		};
	}
}
