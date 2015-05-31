package html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
}
