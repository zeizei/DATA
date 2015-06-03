package html;

public class SeasonHtml extends HtmlReader {
	private String season;

	public SeasonHtml(String urlString, String season) {
		super(urlString);
		if (super.getIsSucceed()) {
			this.season = season;
		}
	}
}
