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

import beans.GamePlayer;
import beans.GameTeam;

public class MatchHtml extends HtmlReader {
	private ArrayList<GamePlayer> gamePlayerList;
	private ArrayList<GameTeam> gameTeamList;
	private final int NORMAL_TABLE_COLUMN_NUM = 21;// 普通数据表格列数
	private final int ADVANCE_TABLE_COLUMN_NUM = 16;// 高级数据表格列数
	private final int QUARTER_POINT_TABLE = 8;// 每节比赛得分表格
	private final int HOME_NORMAL_TABLE = 10;// 主队普通数据
	private final int HOME_ADVANCE_TABLE = 11;// 主队高级数据
	private final int GUEST_NORMAL_TABLE = 12;// 客队普通数据
	private final int GUEST_ADVANCE_TABLE = 13;// 客队

	public MatchHtml(String urlString) {
		super(urlString);
		if (super.getIsSucceed()) {
			gamePlayerList = new ArrayList<GamePlayer>(32);
			gameTeamList = new ArrayList<GameTeam>(4);
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
			this.dwNormalTable(nodeList, this.HOME_NORMAL_TABLE);
			this.dwNormalTable(nodeList, this.GUEST_NORMAL_TABLE);
			this.dwAdvanceTable(nodeList, this.HOME_ADVANCE_TABLE);
			this.dwAdvanceTable(nodeList, this.GUEST_ADVANCE_TABLE);
		}
	}

	private void dwAdvanceTable(NodeList nodeList, int advTable) {
		if (nodeList.elementAt(advTable) instanceof TableTag) {
			TableTag tag = (TableTag) nodeList.elementAt(advTable);
			TableRow[] rows = tag.getRows();
			if (rows != null) {
				int i = 2;
				while (i < rows.length && rows[i] != null && rows[i].getColumnCount() == this.ADVANCE_TABLE_COLUMN_NUM) {
					this.dwOnePlayerAdvance(rows[i]);
					i++;
				}
				i++;
				while (i < rows.length && rows[i] != null && rows[i].getColumnCount() == this.ADVANCE_TABLE_COLUMN_NUM) {
					this.dwOnePlayerAdvance(rows[i]);
					i++;
				}
			}
		}
	}// 处理高级比赛数据

	private void dwNormalTable(NodeList nodeList, int norTable) {
		if (nodeList.elementAt(norTable) instanceof TableTag) {
			TableTag tag = (TableTag) nodeList.elementAt(norTable);
			TableRow[] rows = tag.getRows();
			if (rows != null) {
				int i = 2;
				while (i < rows.length && rows[i] != null && rows[i].getColumnCount() == this.NORMAL_TABLE_COLUMN_NUM) {
					this.dwOnePlayerNormal(rows[i], 1);
					i++;
				}
				i++;
				while (i < rows.length && rows[i] != null && rows[i].getColumnCount() == this.NORMAL_TABLE_COLUMN_NUM) {
					this.dwOnePlayerNormal(rows[i], 0);
					i++;
				}
			}
		}
	}// 处理普通比赛数据

	private void dwOnePlayerNormal(TableRow tableRow, int isStart) {
		if (tableRow != null && tableRow.getColumnCount() == this.NORMAL_TABLE_COLUMN_NUM) {
			TableColumn[] columns = tableRow.getColumns();
			for (int i = 0; i < this.NORMAL_TABLE_COLUMN_NUM; i++) {
				System.out.print(columns[i].toPlainTextString());
				System.out.print("--" + isStart);
			}
			System.out.println();
		}
	}// 处理一个球员的普通比赛数据

	private void dwOnePlayerAdvance(TableRow tableRow) {
		if (tableRow != null && tableRow.getColumnCount() == this.ADVANCE_TABLE_COLUMN_NUM) {
			TableColumn[] columns = tableRow.getColumns();
			for (int i = 0; i < this.ADVANCE_TABLE_COLUMN_NUM; i++) {
				System.out.print(columns[i].toPlainTextString());
				System.out.print("--");
			}
			System.out.println();
		}
	}// 处理中一个球员的高级比赛数据

	public ArrayList<GamePlayer> getGamePlayerList() {
		return gamePlayerList;
	}

	public ArrayList<GameTeam> getGameTeamList() {
		return gameTeamList;
	}
}
