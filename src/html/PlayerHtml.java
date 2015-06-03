package html;

import java.util.HashMap;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import beans.SeasonPlayer;

public class PlayerHtml extends HtmlReader {
	private String playerName;
	private NodeList nodeList;
	private HashMap<String, SeasonPlayer> seasonPlayerMap;
	private final int REGULAR_NORMAL_TABLE = 0;
	private final int REGULAR_ADVANCE_TABLE = 4;
	private final int PLAYOFF_NORMAL_TABLE = 5;
	private final int PLAYOFF_ADVANCE_TABLE = 9;
	private final int NORMAL_TABLE_COLUMN_NUM = 30;
	private final int ADVANCE_TABLE_COLUMN_NUM = 29;

	public PlayerHtml(String urlString, String playerName) {
		super(urlString);
		if (super.getIsSucceed()) {
			if (playerName != null) {
				this.playerName = playerName;
				seasonPlayerMap = new HashMap<String, SeasonPlayer>();
				this.readPage();
			}
		}
	}

	private void readPage() {
		Parser parser = Parser.createParser(super.getHtmlString(), super.getCodeSet());
		NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
		this.nodeList = null;
		try {
			nodeList = parser.extractAllNodesThatMatch(tableFilter);// 找到网页中的table
		} catch (ParserException e) {
			e.printStackTrace();
		}
		if (this.nodeList != null) {
			this.readNormalTable(this.REGULAR_NORMAL_TABLE);
			this.readNormalTable(this.PLAYOFF_NORMAL_TABLE);
			this.readAdvanceTable(this.REGULAR_ADVANCE_TABLE);
			this.readAdvanceTable(this.PLAYOFF_ADVANCE_TABLE);
		}
	}

	private void readNormalTable(int norTable) {
		int isPlayOff = 0;
		if (norTable == this.PLAYOFF_NORMAL_TABLE) {
			isPlayOff = 1;
		}
		if (nodeList.elementAt(norTable) instanceof TableTag) {
			TableTag tag = (TableTag) nodeList.elementAt(norTable);
			TableRow[] rows = tag.getRows();
			if (rows != null) {
				for (int i = 0; i < rows.length; i++) {
					this.dwOneSeasonNormal(rows[i], isPlayOff);
				}
			}
		}
	}// 读取普通数据表格

	private void readAdvanceTable(int advTable) {
		int isPlayOff = 0;
		if (advTable == this.PLAYOFF_ADVANCE_TABLE) {
			isPlayOff = 1;
		}
		if (nodeList.elementAt(advTable) instanceof TableTag) {
			TableTag tag = (TableTag) nodeList.elementAt(advTable);
			TableRow[] rows = tag.getRows();
			if (rows != null) {
				for (int i = 0; i < rows.length; i++) {
					this.dwOneSeasonAdvance(rows[i], isPlayOff);
				}
			}
		}
	}// 读取高级数据表格

	private void dwOneSeasonNormal(TableRow row, int isPlayOff) {
		if (row != null && row.getColumnCount() == this.NORMAL_TABLE_COLUMN_NUM) {
			TableColumn[] columns = row.getColumns();
			String str = columns[0].toPlainTextString();
			if (str != null && str.length() >= 7 && str.charAt(4) == '-') {
				String[] cellString = new String[this.NORMAL_TABLE_COLUMN_NUM];
				Double[] cell = new Double[this.NORMAL_TABLE_COLUMN_NUM - 5];
				for (int i = 0; i < this.NORMAL_TABLE_COLUMN_NUM; i++) {
					cellString[i] = columns[i].toPlainTextString();
				}
				for (int i = 0; i < this.NORMAL_TABLE_COLUMN_NUM - 5; i++) {
					cell[i] = super.toDouble(cellString[i + 5]);
				}
				String season = cellString[0].substring(0, 7);
				double age = super.toDouble(cellString[1]);
				String teamName = cellString[2];
				String position = cellString[4];
				SeasonPlayer seasonPlayer = new SeasonPlayer();
				String[] fields = { "playerName", "season", "teamName", "position", "isPlayOff", "age" };
				Object[] contents = { playerName, season, teamName, position, isPlayOff, age };
				boolean iGeneralSucceed = super.AutoEncapsulate(seasonPlayer, fields, contents);
				String[] tableFields = { "numOfGame", "numOfStart", "minute", "totalHit", "totalShot", "shot", "threeHit", "threeShot", "three", "twoShot", "twoHit", "two", "shotEFF", "freeHit",
						"freeShot", "free", "ofdRebound", "dfdRebound", "totRebound", "assist", "steal", "block", "fault", "foul", "point" };
				boolean isTableSucceed = super.AutoEncapsulate(seasonPlayer, tableFields, cell);
				String key = season + teamName + String.valueOf(isPlayOff);
				if (iGeneralSucceed && isTableSucceed) {
					this.seasonPlayerMap.put(key, seasonPlayer);
				}
			}
		}
	}

	private void dwOneSeasonAdvance(TableRow row, int isPlayOff) {
		if (row != null && row.getColumnCount() == this.ADVANCE_TABLE_COLUMN_NUM) {
			TableColumn[] columns = row.getColumns();
			String str = columns[0].toPlainTextString();
			if (str != null && str.length() >= 7 && str.charAt(4) == '-') {
				String[] cellString = new String[this.ADVANCE_TABLE_COLUMN_NUM];
				Double[] cell = new Double[this.ADVANCE_TABLE_COLUMN_NUM - 7];
				for (int i = 0; i < this.ADVANCE_TABLE_COLUMN_NUM; i++) {
					cellString[i] = columns[i].toPlainTextString();
				}
				for (int i = 0; i < this.ADVANCE_TABLE_COLUMN_NUM - 7; i++) {
					cell[i] = super.toDouble(cellString[i + 7]);
				}
				String season = cellString[0].substring(0, 7);
				String teamName = cellString[2];
				String key = season + teamName + String.valueOf(isPlayOff);
				SeasonPlayer seasonPlayer = this.seasonPlayerMap.get(key);
				String[] tableFields = { "playerEFF", "realShot", "threeEFF", "freeEFF", "offReboundEFF", "defReboundEFF", "totReboundEFF", "assistEFF", "stealEFF", "blockEFF", "faultEFF", "useEFF",
						"offWinShare", "offWinShare", "defWinShare", "winShare", "winSharePer48", "offBoxPM", "offBoxPM", "defBoxPM", "BoxPM", "replaceValue" };
				// 有重复是因为网页上有对应的列但却没有内容，为空列
				boolean isTableSucceed = super.AutoEncapsulate(seasonPlayer, tableFields, cell);
				if (!isTableSucceed) {
					this.seasonPlayerMap.remove(key);
				}
			}
		}
	}

	public HashMap<String, SeasonPlayer> getSeasonPlayerList() {
		return seasonPlayerMap;
	}
}