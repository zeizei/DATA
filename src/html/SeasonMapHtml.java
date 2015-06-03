package html;

import java.util.ArrayList;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class SeasonMapHtml extends HtmlReader {
	private ArrayList<String> seasonUrlList;
	private ArrayList<String> seasonList;
	private final int SEASON_TABLE = 0;
	private final int COLUMN_NUM = 9;

	public SeasonMapHtml(String urlString) {
		super(urlString);
		if (super.getIsSucceed()) {
			seasonUrlList = new ArrayList<String>(128);
			seasonList = new ArrayList<String>(128);
			this.readPage();
		}
	}

	private void readPage() {
		Parser parser = Parser.createParser(super.getHtmlString(), super.getCodeSet());
		NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
		NodeList nodeList = null;
		try {
			nodeList = parser.extractAllNodesThatMatch(tableFilter);// 找到网页中的table
		} catch (ParserException e) {
			e.printStackTrace();
		}
		if (nodeList != null) {
			if (nodeList.elementAt(this.SEASON_TABLE) instanceof TableTag) {
				TableTag tag = (TableTag) nodeList.elementAt(this.SEASON_TABLE);
				TableRow[] rows = tag.getRows();
				if (rows != null) {
					for (int i = 0; i < rows.length; i++) {
						this.dealWithOneSeason(rows[i]);
					}
				}
			}
		}
	}

	private void dealWithOneSeason(TableRow row) {
		if (row != null) {
			TableColumn[] columns = row.getColumns();
			if (columns != null && columns.length == this.COLUMN_NUM) {
				String season = columns[0].toPlainTextString();
				String tempUrl = columns[0].getStringText();
				String part[] = tempUrl.split("\"");
				if (part != null && part.length == 3) {
					String seasonUrl = super.getHomeUrlString() + part[1];
					this.seasonList.add(season);
					this.seasonUrlList.add(seasonUrl);
				}
			}
		}
	}

	public ArrayList<String> getSeasonUrlList() {
		return seasonUrlList;
	}

	public ArrayList<String> getSeasonList() {
		return seasonList;
	}
}
