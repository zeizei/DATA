package beans;

import java.io.Serializable;
import java.lang.reflect.Field;

public class Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer buffer = new StringBuffer();
		if (fields != null) {
			for (int i = 1; i < fields.length; i++) {
				try {
					fields[i].setAccessible(true);
					buffer.append(fields[i].get(this)).append("--");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return buffer.toString();
		}
		return null;
	}
}
