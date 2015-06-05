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

import beans.GamePlayer;
import beans.GameTeam;
import beans.GeneralMatch;

public class MatchHtml extends HtmlReader {
	private HashMap<String, GamePlayer> gamePlayerMap;
	private HashMap<String, GameTeam> gameTeamMap;
	private String[] advanceFields = { "realShot", "shotEFF", "threeEFF", "freeEFF", "offReboundEFF", "defReboundEFF", "totReboundEFF", "assistEFF", "stealEFF", "blockEFF", "faultEFF", "useEFF",
			"offEFF", "defEFF" };
	private NodeList nodeList;
	private String homeTeam;
	private String guestTeam;
	private String date;
	private final int NORMAL_TABLE_COLUMN_NUM = 20;// 普通数据表格列数/////2000之后为21
	private final int ADVANCE_TABLE_COLUMN_NUM = 16;// 高级数据表格列数
	private int QUARTER_POINT_TABLE;// 每节比赛得分表格
	private int HOME_NORMAL_TABLE;// 主队普通数据
	private int HOME_ADVANCE_TABLE;// 主队高级数据
	private int GUEST_NORMAL_TABLE;// 客队普通数据
	private int GUEST_ADVANCE_TABLE;// 客队

	public MatchHtml(String urlString, GeneralMatch generalMatch) {
		super(urlString);
		if (super.getIsSucceed()) {
			if (generalMatch != null) {
				this.homeTeam = generalMatch.getHomeTeam();
				this.guestTeam = generalMatch.getGuestTeam();
				this.date = generalMatch.getDate();
				if (this.homeTeam != null && this.guestTeam != null && this.date != null) {
					gamePlayerMap = new HashMap<String, GamePlayer>();
					gameTeamMap = new HashMap<String, GameTeam>();
					this.readPage();
				}
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
		if (nodeList != null) {
			int i = 0;
			while (i < nodeList.size()) {
				if (nodeList.elementAt(i) instanceof TableTag) {
					TableTag table = (TableTag) nodeList.elementAt(i);
					int rowNum = table.getRowCount();
					if (rowNum > 3) {
						TableRow row = table.getRow(3);
						int columnNum = row.getColumnCount();
						if (columnNum == this.NORMAL_TABLE_COLUMN_NUM) {
							if (nodeList.elementAt(i + 1) instanceof TableTag) {
								TableTag nextTable = (TableTag) nodeList.elementAt(i + 1);
								int rowCount = nextTable.getRowCount();
								if (rowCount > 3) {
									TableRow row3 = nextTable.getRow(3);
									int columnCount = row3.getColumnCount();
									if (columnCount == this.ADVANCE_TABLE_COLUMN_NUM) {
										break;
									}
								}
							}
						}
					}
				}
				i++;
			}// 用两个表格做判断
			this.QUARTER_POINT_TABLE = i - 2;
			this.HOME_NORMAL_TABLE = i;
			this.HOME_ADVANCE_TABLE = i + 1;
			this.GUEST_NORMAL_TABLE = i + 2;
			this.GUEST_ADVANCE_TABLE = i + 3;
			this.readNormalTable(this.HOME_NORMAL_TABLE);
			this.readNormalTable(this.GUEST_NORMAL_TABLE);
			this.readAdvanceTable(this.HOME_ADVANCE_TABLE);
			this.readAdvanceTable(this.GUEST_ADVANCE_TABLE);
		}
	}// 解析每场比赛的网页

	private void readAdvanceTable(int advTable) {
		if (nodeList.elementAt(advTable) instanceof TableTag) {
			TableTag tag = (TableTag) nodeList.elementAt(advTable);
			TableRow[] rows = tag.getRows();
			if (rows != null) {
				String teamName = homeTeam;
				if (advTable == this.GUEST_ADVANCE_TABLE) {
					teamName = guestTeam;
				}
				int i = 2;
				while (i < rows.length && rows[i] != null && rows[i].getColumnCount() == this.ADVANCE_TABLE_COLUMN_NUM) {
					this.dwOnePlayerAdvance(rows[i]);
					i++;
				}
				i++;
				while (i < rows.length - 1 && rows[i] != null && rows[i].getColumnCount() == this.ADVANCE_TABLE_COLUMN_NUM) {
					this.dwOnePlayerAdvance(rows[i]);

					i++;
				}
				this.dwOneTeamAdvance(rows[rows.length - 1], teamName);
			}
		}
	}// 读取比赛高级数据表格

	private void readNormalTable(int norTable) {
		if (nodeList.elementAt(norTable) instanceof TableTag) {
			TableTag tag = (TableTag) nodeList.elementAt(norTable);
			TableRow[] rows = tag.getRows();
			if (rows != null) {
				String teamName = homeTeam;
				if (norTable == this.GUEST_NORMAL_TABLE) {
					teamName = guestTeam;
				}
				int i = 2;
				while (i < rows.length && rows[i] != null && rows[i].getColumnCount() == this.NORMAL_TABLE_COLUMN_NUM) {
					this.dwOnePlayerNormal(rows[i], 1, teamName);
					i++;
				}
				i++;
				while (i < rows.length - 1 && rows[i] != null && rows[i].getColumnCount() == this.NORMAL_TABLE_COLUMN_NUM) {
					this.dwOnePlayerNormal(rows[i], 0, teamName);
					i++;
				}
				this.dwOneTeamNormal(rows[rows.length - 1], teamName);
			}
		}
	}// 读取比赛普通数据表格

	private void dwOnePlayerNormal(TableRow tableRow, int isStart, String teamName) {
		if (tableRow != null && tableRow.getColumnCount() == this.NORMAL_TABLE_COLUMN_NUM) {
			TableColumn[] columns = tableRow.getColumns();
			String[] cellString = new String[this.NORMAL_TABLE_COLUMN_NUM];
			Double[] cell = new Double[this.NORMAL_TABLE_COLUMN_NUM - 1];
			for (int i = 0; i < this.NORMAL_TABLE_COLUMN_NUM; i++) {
				cellString[i] = columns[i].toPlainTextString();
			}
			String playerName = cellString[0];
			String minuteString = cellString[1];
			cell[0] = this.getMinute(minuteString);
			for (int i = 1; i < this.NORMAL_TABLE_COLUMN_NUM - 1; i++) {
				cell[i] = this.toDouble(cellString[i + 1]);
			}
			String[] tableFields = { "minute", "totHit", "totShot", "shot", "threeHit", "threeShot", "three", "freeHit", "freeShot", "free", "offRebound", "defRebound", "totRebound", "assist",
					"steal", "block", "fault", "foul", "point" };// //////////////2000之后还有一个"plus"
			GamePlayer gamePlayer = new GamePlayer();
			boolean isTableSucceed = gamePlayer.AutoEncapsulate(tableFields, cell);
			String[] generalFields = { "date", "playerName", "teamName", "isStart" };
			Object[] contents = { this.date, playerName, teamName, isStart };
			boolean isGeneralSucceed = gamePlayer.AutoEncapsulate(generalFields, contents);
			if (isTableSucceed && isGeneralSucceed) {
				this.gamePlayerMap.put(playerName, gamePlayer);
			}
		}
	}// 处理一个球员的比赛普通数据

	private double getMinute(String minuteString) {
		double minute = 0;
		String[] part = minuteString.split(":");
		if (part != null) {
			if (part.length == 2) {
				minute = this.toDouble(part[0]) + this.toDouble(part[1]) / 60.0;
				minute = super.cutTail(minute, 1);
			}
			else if (part.length == 1) {
				minute = this.toDouble(part[0]);
			}
		}
		return minute;
	}

	private void dwOneTeamNormal(TableRow tableRow, String teamName) {
		if (tableRow != null && tableRow.getColumnCount() == this.NORMAL_TABLE_COLUMN_NUM) {
			TableColumn[] columns = tableRow.getColumns();
			String[] cellString = new String[this.NORMAL_TABLE_COLUMN_NUM];
			Double[] cell = new Double[this.NORMAL_TABLE_COLUMN_NUM - 1];// 2000之后为-2
			for (int i = 0; i < this.NORMAL_TABLE_COLUMN_NUM; i++) {
				cellString[i] = columns[i].toPlainTextString();
			}
			String minuteString = cellString[1];
			cell[0] = this.getMinute(minuteString);
			for (int i = 1; i < this.NORMAL_TABLE_COLUMN_NUM - 1; i++) {// 2000之后为-2
				cell[i] = this.toDouble(cellString[i + 1]);
			}
			String[] tableFields = { "minute", "totHit", "totShot", "shot", "threeHit", "threeShot", "three", "freeHit", "freeShot", "free", "offRebound", "defRebound", "totRebound", "assist",
					"steal", "block", "fault", "foul", "point" };
			GameTeam gameTeam = new GameTeam();
			boolean isTableSucceed = gameTeam.AutoEncapsulate(tableFields, cell);
			String[] generalFields = { "date", "teamName", "quarterPoint" };
			String quarterPoint = this.getQuarterPoint(teamName);
			if (quarterPoint != null) {
				Object[] contents = { this.date, teamName, quarterPoint };
				boolean isGeneralSucceed = gameTeam.AutoEncapsulate(generalFields, contents);
				if (isTableSucceed && isGeneralSucceed) {
					this.gameTeamMap.put(teamName, gameTeam);
				}
			}
		}
	}// 处理一个球队的比赛普通数据

	private String getQuarterPoint(String teamName) {
		if (nodeList.elementAt(this.QUARTER_POINT_TABLE) instanceof TableTag) {
			TableTag tag = (TableTag) nodeList.elementAt(this.QUARTER_POINT_TABLE);
			TableRow[] rows = tag.getRows();
			if (rows != null && rows.length == 4) {
				TableRow row = rows[2];
				if (teamName.equals(this.guestTeam)) {
					row = rows[3];
				}
				TableColumn[] columns = row.getColumns();
				if (columns != null && columns.length > 2) {
					String[] cellString = new String[columns.length - 2];
					for (int i = 0; i < cellString.length; i++) {
						cellString[i] = columns[i + 1].toPlainTextString();
					}
					int[] result = new int[cellString.length];
					for (int i = 0; i < cellString.length; i++) {
						result[i] = super.toInt(cellString[i]);
					}
					StringBuffer buffer = new StringBuffer();
					for (int i = 0; i < result.length - 1; i++) {
						buffer.append(result[i]).append("---");
					}
					buffer.append(result[result.length - 1]);
					return buffer.toString();
				}
			}
		}
		return null;
	}

	private void dwOnePlayerAdvance(TableRow tableRow) {
		if (tableRow != null && tableRow.getColumnCount() == this.ADVANCE_TABLE_COLUMN_NUM) {
			TableColumn[] columns = tableRow.getColumns();
			String[] cellString = new String[this.ADVANCE_TABLE_COLUMN_NUM];
			Double[] cell = new Double[this.ADVANCE_TABLE_COLUMN_NUM - 2];
			for (int i = 0; i < this.ADVANCE_TABLE_COLUMN_NUM; i++) {
				cellString[i] = columns[i].toPlainTextString();
			}
			String playerName = cellString[0];
			for (int i = 0; i < this.ADVANCE_TABLE_COLUMN_NUM - 2; i++) {
				cell[i] = this.toDouble(cellString[i + 2]);
			}
			if (this.gamePlayerMap.containsKey(playerName)) {
				GamePlayer gamePlayer = this.gamePlayerMap.get(playerName);
				boolean isSucceed = gamePlayer.AutoEncapsulate(this.advanceFields, cell);
				if (!isSucceed) {
					this.gamePlayerMap.remove(playerName);
				}
			}
		}
	}// 处理中一个球员的比赛高级数据

	private void dwOneTeamAdvance(TableRow tableRow, String teamName) {
		if (tableRow != null && tableRow.getColumnCount() == this.ADVANCE_TABLE_COLUMN_NUM && teamName != null) {
			TableColumn[] columns = tableRow.getColumns();
			String[] cellString = new String[this.ADVANCE_TABLE_COLUMN_NUM];
			Double[] cell = new Double[this.ADVANCE_TABLE_COLUMN_NUM - 2];
			for (int i = 0; i < this.ADVANCE_TABLE_COLUMN_NUM; i++) {
				cellString[i] = columns[i].toPlainTextString();
			}
			for (int i = 0; i < this.ADVANCE_TABLE_COLUMN_NUM - 2; i++) {
				cell[i] = this.toDouble(cellString[i + 2]);
			}
			if (this.gameTeamMap.containsKey(teamName)) {
				GameTeam gameTeam = this.gameTeamMap.get(teamName);
				boolean isSucceed = gameTeam.AutoEncapsulate(this.advanceFields, cell);
				if (!isSucceed) {
					this.gameTeamMap.remove(gameTeam);
				}
			}
		}
	}// 处理一个球队的比赛高级数据

	public HashMap<String, GamePlayer> getGamePlayerMap() {
		return gamePlayerMap;
	}

	public HashMap<String, GameTeam> getGameTeamMap() {
		return gameTeamMap;
	}
}
