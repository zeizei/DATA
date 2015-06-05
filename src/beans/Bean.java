package beans;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

	public boolean AutoEncapsulate(String[] fields, Object[] contents) {
		boolean isSucceed = true;
		if (fields != null && contents != null && fields.length == contents.length) {
			Class<?> c = this.getClass();
			for (int i = 0; i < fields.length; i++) {
				StringBuffer buffer = new StringBuffer();
				try {
					Field field = c.getDeclaredField(fields[i]);
					Class<?> MethodType = field.getType();
					buffer.append("set");
					buffer.append(fields[i].substring(0, 1).toUpperCase());
					buffer.append(fields[i].substring(1));
					Method method = c.getDeclaredMethod(buffer.toString(), MethodType);
					method.invoke(this, contents[i]);
				} catch (NoSuchFieldException e) {
					isSucceed = false;
					e.printStackTrace();
				} catch (SecurityException e) {
					isSucceed = false;
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					isSucceed = false;
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					isSucceed = false;
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					isSucceed = false;
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					isSucceed = false;
					e.printStackTrace();
				}
			}
		}
		return isSucceed;
	}

	public Object[] getSpeContent(String[] fields) {
		if (fields != null) {
			Object[] objects = new Object[fields.length];
			Class<?> c = this.getClass();
			for (int i = 0; i < fields.length; i++) {
				StringBuffer buffer = new StringBuffer();
				try {
					buffer.append("get");
					buffer.append(fields[i].substring(0, 1).toUpperCase());
					buffer.append(fields[i].substring(1));
					Method method = c.getMethod(buffer.toString());
					objects[i] = method.invoke(this);
				} catch (SecurityException e) {
					e.printStackTrace();
					return null;
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					return null;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return null;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					return null;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					return null;
				}
			}
			return objects;
		}
		return null;
	}
}
