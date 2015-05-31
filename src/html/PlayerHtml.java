package html;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class PlayerHtml extends HtmlReader {
	private final int totalNormal = 1;

	public PlayerHtml(String urlString) {
		super(urlString);
	}

	public void readPlayerPage() {

		try {
			Parser myParser = Parser.createParser(super.getHtmlString(), "utf-8");
			NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
			NodeList nodeList = myParser.extractAllNodesThatMatch(tableFilter);
			if ((nodeList != null) && (nodeList.elementAt(this.totalNormal) instanceof TableTag)) {
				TableTag tag = (TableTag) nodeList.elementAt(this.totalNormal);
				TableRow[] rows = tag.getRows();
				System.out.println("----------------------table total--------------------------------");
				// 循环读取每一行
				for (int j = 0; j < rows.length; j++) {
					TableRow row = rows[j];
					TableColumn[] columns = row.getColumns();
					if (columns != null && columns.length >= 1 && columns[0].toPlainTextString() != null && columns[0].toPlainTextString().length() >= 7) {
						System.out.print(columns[0].toPlainTextString().substring(0, 7) + "  ");// （按照自己需要的格式输出）
					}
					for (int k = 1; k < columns.length; k++) {
						System.out.print(columns[k].toPlainTextString() + "  ");// （按照自己需要的格式输出）
					}
					System.out.println();
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		PlayerHtml playerHtml = new PlayerHtml("http://www.basketball-reference.com/teams/HOU/2015.html");
		playerHtml.readPlayerPage();
	}
}