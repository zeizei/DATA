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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import beans.SeasonPlayer;

public class PlayerHtml extends HtmlReader {
	private String playerUrl;
	private String playerName;
	private Document doc;
	private HashMap<String, SeasonPlayer> seasonPlayerMap;
	private final int NORMAL_TABLE_COLUMN_NUM = 30;
	private final int ADVANCE_TABLE_COLUMN_NUM = 29;

	public PlayerHtml(String urlString, String playerName) {
		super(urlString);
		if (super.getIsSucceed()) {
			if (playerName != null) {
				this.playerUrl = urlString;
				this.playerName = playerName;
				seasonPlayerMap = new HashMap<String, SeasonPlayer>();
				doc = Jsoup.parse(super.getHtmlString(), super.getHomeUrlString());
				this.readPage();
			}
		}
	}

	private void readPage() {
		TableTag totalTable = this.getTableTagById("div_totals");
		TableTag advancedTable = this.getTableTagById("div_advanced");
		TableTag playOfftotalTable = this.getTableTagById("div_playoffs_totals");
		TableTag playOffAdvancedTable = this.getTableTagById("div_playoffs_advanced");
		this.readNormalTable(totalTable, 0);
		this.readNormalTable(playOfftotalTable, 1);
		this.readAdvanceTable(advancedTable, 0);
		this.readAdvanceTable(playOffAdvancedTable, 1);
	}

	private TableTag getTableTagById(String id) {
		Element totals = doc.getElementById(id);
		if (totals != null) {
			Parser parser = Parser.createParser(totals.html(), super.getCodeSet());
			NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
			NodeList nodeList = null;
			try {
				nodeList = parser.extractAllNodesThatMatch(tableFilter);// 找到网页中的table
			} catch (ParserException e) {
				e.printStackTrace();
			}
			if (nodeList != null) {
				if (nodeList.elementAt(0) instanceof TableTag) {
					TableTag tableTag = (TableTag) nodeList.elementAt(0);
					return tableTag;
				}
			}
		}
		return null;
	}// 根据相应的div的id得到对应的表格

	private void readNormalTable(TableTag tag, int isPlayOff) {
		if (tag != null) {
			TableRow[] rows = tag.getRows();
			if (rows != null) {
				for (int i = 0; i < rows.length; i++) {
					this.dwOneSeasonNormal(rows[i], isPlayOff);
				}
			}
		}
	}// 读取普通数据表格

	private void readAdvanceTable(TableTag tag, int isPlayOff) {
		if (tag != null) {
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
			if ((str != null && str.length() >= 7 && str.charAt(4) == '-') || (str != null) && str.equals("Career")) {
				String[] cellString = new String[this.NORMAL_TABLE_COLUMN_NUM];
				Double[] cell = new Double[this.NORMAL_TABLE_COLUMN_NUM - 5];
				for (int i = 0; i < this.NORMAL_TABLE_COLUMN_NUM; i++) {
					cellString[i] = columns[i].toPlainTextString();
				}
				for (int i = 0; i < this.NORMAL_TABLE_COLUMN_NUM - 5; i++) {
					cell[i] = super.toDouble(cellString[i + 5]);
				}
				String season = "Career";
				if (cellString[0].length() >= 7) {
					season = cellString[0].substring(0, 7);
				}
				String playerId = this.playerUrl.substring(46, this.playerUrl.length() - 5);
				double age = super.toDouble(cellString[1]);
				String teamName = cellString[2];
				String position = cellString[4];
				SeasonPlayer seasonPlayer = new SeasonPlayer();
				String[] fields = { "playerId", "season", "teamName", "playerName", "position", "isPlayOff", "age" };
				Object[] contents = { playerId, season, teamName, playerName, position, isPlayOff, age };
				boolean iGeneralSucceed = seasonPlayer.AutoEncapsulate(fields, contents);
				String[] tableFields = { "numOfGame", "numOfStart", "minute", "totalHit", "totalShot", "shot", "threeHit", "threeShot", "three", "twoShot", "twoHit", "two", "shotEFF", "freeHit",
						"freeShot", "free", "offRebound", "defRebound", "totRebound", "assist", "steal", "block", "fault", "foul", "point" };
				boolean isTableSucceed = seasonPlayer.AutoEncapsulate(tableFields, cell);
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
			if ((str != null && str.length() >= 7 && str.charAt(4) == '-') || (str != null) && str.equals("Career")) {
				String[] cellString = new String[this.ADVANCE_TABLE_COLUMN_NUM];
				Double[] cell = new Double[this.ADVANCE_TABLE_COLUMN_NUM - 7];
				for (int i = 0; i < this.ADVANCE_TABLE_COLUMN_NUM; i++) {
					cellString[i] = columns[i].toPlainTextString();
				}
				for (int i = 0; i < this.ADVANCE_TABLE_COLUMN_NUM - 7; i++) {
					cell[i] = super.toDouble(cellString[i + 7]);
				}
				String season = "Career";
				if (cellString[0].length() >= 7) {
					season = cellString[0].substring(0, 7);
				}
				String teamName = cellString[2];
				String key = season + teamName + String.valueOf(isPlayOff);
				SeasonPlayer seasonPlayer = this.seasonPlayerMap.get(key);
				String[] tableFields = { "playerEFF", "realShot", "threeEFF", "freeEFF", "offReboundEFF", "defReboundEFF", "totReboundEFF", "assistEFF", "stealEFF", "blockEFF", "faultEFF", "useEFF",
						"offWinShare", "offWinShare", "defWinShare", "winShare", "winSharePer48", "offBoxPM", "offBoxPM", "defBoxPM", "BoxPM", "replaceValue" };
				// 有重复是因为网页上有对应的列但却没有内容，为空列
				boolean isTableSucceed = seasonPlayer.AutoEncapsulate(tableFields, cell);
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
