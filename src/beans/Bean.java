package beans;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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

	public String getCreateTableStr() {
		Field[] fields = this.getClass().getDeclaredFields();
		String primaryKey = "primary key (";
		StringBuffer buffer = new StringBuffer();
		buffer.append("create table ").append(this.getClass().getSimpleName().toLowerCase()).append(" (");
		if (fields != null) {
			for (int i = 1; i < fields.length; i++) {
				try {
					String fieldName = fields[i].getName();
					Class<?> cl = fields[i].getType();
					int modifier = fields[i].getModifiers();
					if (modifier == Modifier.PROTECTED) {
						primaryKey = primaryKey + fieldName + ",";
					}
					buffer.append(fieldName);
					if (cl.equals(double.class)) {
						buffer.append(" double not null,");
					}
					else if (cl.equals(String.class)) {
						buffer.append(" varchar(64) not null,");
					}
					else if (cl.equals(int.class)) {
						buffer.append(" int not null,");
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
			primaryKey = primaryKey.substring(0, primaryKey.length() - 1) + ")";
			buffer.append(primaryKey).append(")");
			return buffer.toString();
		}
		return null;
	}

	public String getInsertTableStr() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer value = new StringBuffer();
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into ").append(this.getClass().getSimpleName().toLowerCase()).append(" (");
		value.append(") values (");
		if (fields != null) {
			for (int i = 1; i < fields.length; i++) {
				try {
					fields[i].setAccessible(true);
					String fieldName = fields[i].getName();
					Class<?> cl = fields[i].getType();
					buffer.append(fieldName).append(",");
					if (cl.equals(double.class) || cl.equals(int.class)) {
						value.append(fields[i].get(this));
					}
					else if (cl.equals(String.class)) {
						value.append("'").append(fields[i].get(this)).append("'");
					}
					value.append(",");
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			buffer.trimToSize();
			buffer.deleteCharAt(buffer.capacity() - 1);
			value.trimToSize();
			value.deleteCharAt(value.capacity() - 1);
			buffer.append(value.toString()).append(")");
			return buffer.toString();
		}
		return null;
	}
}
