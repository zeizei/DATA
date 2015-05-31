package html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

class HtmlReader {
	private boolean isSucceed;
	private String urlString;
	private URL url;
	private InputStream inputStream;
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	private String htmlString;

	HtmlReader(String urlString) {
		this.urlString = urlString;
		if (this.urlString == null) {
			this.isSucceed = false;
		}
		else {
			this.isSucceed = true;
		}
		if (isSucceed) {
			try {
				this.url = new URL(urlString);
			} catch (MalformedURLException e) {
				this.isSucceed = false;
				e.printStackTrace();
			}
			if (isSucceed) {
				try {
					inputStream = url.openStream();
				} catch (IOException e) {
					this.isSucceed = false;
					e.printStackTrace();
				}
				if (isSucceed) {
					inputStreamReader = new InputStreamReader(inputStream);
					bufferedReader = new BufferedReader(inputStreamReader);
					String temp = null;
					StringBuffer allContent = new StringBuffer();
					try {
						while ((temp = bufferedReader.readLine()) != null) {
							allContent = allContent.append(temp).append("\n");
						}
					} catch (IOException e) {
						this.isSucceed = false;
						e.printStackTrace();
					}
					if (isSucceed) {
						this.htmlString = allContent.toString();
					}
				}
			}
		}
	}

	protected boolean getIsSucceed() {
		return isSucceed;
	}

	protected String getHtmlString() {
		return htmlString;
	}

	protected String getCodeSet() {
		return "utf-8";
	}

	protected String getHomeUrlString() {
		return "http://www.basketball-reference.com";
	}

	protected double toDouble(String str) {
		double result = 0;
		if (str != null) {
			try {
				result = Double.parseDouble(str);
			} catch (NumberFormatException e) {
				result = 0;
			}
		}
		return result;
	}

	protected int toInt(String str) {
		int result = 0;
		if (str != null) {
			try {
				result = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				result = 0;
			}
		}
		return result;
	}

	protected boolean AutoEncapsulate(Object object, String[] fields, Object[] contents) {
		boolean isSucceed = true;
		if (object != null && fields != null && contents != null && fields.length == contents.length) {
			Class<?> c = object.getClass();
			for (int i = 0; i < fields.length; i++) {
				StringBuffer buffer = new StringBuffer();
				try {
					Field field = c.getDeclaredField(fields[i]);
					Class<?> MethodType = field.getType();
					buffer.append("set");
					buffer.append(fields[i].substring(0, 1).toUpperCase());
					buffer.append(fields[i].substring(1));
					Method method = c.getDeclaredMethod(buffer.toString(), MethodType);
					method.invoke(object, contents[i]);
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
}
