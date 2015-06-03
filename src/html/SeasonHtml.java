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

import beans.SeasonTeam;

public class SeasonHtml extends HtmlReader {
	private String season;
	private HashMap<String, SeasonTeam> seasonTeamMap;
	private int TEAM_NORMAL_TABLE;// 球队普通数据表格
	private int OPP_NORMAL_TABLE;// 对手球队普通数据表格
	private int TEAM_ADVANCE_TABLE;// 球队高级数据表格
	private final int NORMAL_TABLE_COLUMN_NUM = 26;// 普通数据表格列数
	private final int ADVANCE_TABLE_COLUMN_NUM = 24;// 高级数据表格列数

	public SeasonHtml(String urlString, String season) {
		super(urlString);
		if (super.getIsSucceed()) {
			this.season = season;
			this.seasonTeamMap = new HashMap<String, SeasonTeam>(64);
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
									if (columnCount == this.NORMAL_TABLE_COLUMN_NUM) {
										break;
									}
								}
							}
						}
					}
				}
				i++;
			}// 用两个表格做判断
			this.TEAM_NORMAL_TABLE = i;
			this.OPP_NORMAL_TABLE = i + 1;
			this.TEAM_ADVANCE_TABLE = i + 2;
			if (nodeList.elementAt(TEAM_NORMAL_TABLE) instanceof TableTag && nodeList.elementAt(OPP_NORMAL_TABLE) instanceof TableTag && nodeList.elementAt(TEAM_ADVANCE_TABLE) instanceof TableTag) {
				this.readTeamNorTable((TableTag) nodeList.elementAt(TEAM_NORMAL_TABLE));
				this.readOppTeamNorTable((TableTag) nodeList.elementAt(OPP_NORMAL_TABLE));
				this.readTeamAdvTable((TableTag) nodeList.elementAt(TEAM_ADVANCE_TABLE));
			}
		}
	}

	private void readTeamAdvTable(TableTag advTable) {
		if (advTable != null) {
			TableRow[] rows = advTable.getRows();
			if (rows != null) {
				for (int i = 0; i < rows.length - 1; i++) {
					TableColumn[] columns = rows[i].getColumns();
					if (columns != null && columns.length == this.ADVANCE_TABLE_COLUMN_NUM) {
						String[] cellString = new String[this.ADVANCE_TABLE_COLUMN_NUM];
						Double[] cell = new Double[this.ADVANCE_TABLE_COLUMN_NUM - 4];
						for (int j = 0; j < this.ADVANCE_TABLE_COLUMN_NUM; j++) {
							cellString[j] = columns[j].toPlainTextString();
						}
						String teamName = cellString[1];
						if (teamName.endsWith("*")) {
							teamName = teamName.substring(0, teamName.length() - 1);
						}
						for (int k = 0; k < this.ADVANCE_TABLE_COLUMN_NUM - 4; k++) {
							cell[k] = super.toDouble(cellString[k + 2]);
						}
						if (teamName != null && this.season != null) {
							SeasonTeam seasonTeam = this.seasonTeamMap.get(teamName);
							String tableField[] = { "avgAge", "numOfWin", "numOfLose", "pointOfWin", "strengthOfSchedule", "simpleRatingSystem", "offEFF", "defEFF", "pace", "freeEFF", "threeEFF",
									"realShot", "shotEFF", "faultEFF", "offReboundEFF", "freePerFieldGoal", "oppShotEFF", "oppFaultEFF", "defReboundEFF", "oppFreePerFieldGoal" };

							boolean isTableSucceed = super.AutoEncapsulate(seasonTeam, tableField, cell);
							seasonTeam.setArean(cellString[this.ADVANCE_TABLE_COLUMN_NUM - 2]);
							String attendance = cellString[this.ADVANCE_TABLE_COLUMN_NUM - 1];
							String[] part = attendance.split(",");
							if (part != null) {
								attendance = "";
								for (int p = 0; p < part.length; p++) {
									attendance = attendance + part[p];
								}
							}
							seasonTeam.setAttendance(super.toDouble(attendance));
							if (!isTableSucceed) {
								this.seasonTeamMap.remove(teamName);
							}
						}
					}
				}
			}
		}
	}// 读取球队高级数据表格

	private void readOppTeamNorTable(TableTag oppNorTable) {
		if (oppNorTable != null) {
			TableRow[] rows = oppNorTable.getRows();
			if (rows != null) {
				for (int i = 0; i < rows.length - 1; i++) {
					TableColumn[] columns = rows[i].getColumns();
					if (columns != null && columns.length == this.NORMAL_TABLE_COLUMN_NUM) {
						String[] cellString = new String[this.NORMAL_TABLE_COLUMN_NUM];
						Double[] cell = new Double[this.NORMAL_TABLE_COLUMN_NUM - 5];
						for (int j = 0; j < this.NORMAL_TABLE_COLUMN_NUM; j++) {
							cellString[j] = columns[j].toPlainTextString();
						}
						String teamName = cellString[1];
						if (teamName.endsWith("*")) {
							teamName = teamName.substring(0, teamName.length() - 1);
						}
						for (int k = 0; k < this.NORMAL_TABLE_COLUMN_NUM - 5; k++) {
							cell[k] = super.toDouble(cellString[k + 4]);
						}
						if (teamName != null && this.season != null) {
							SeasonTeam seasonTeam = this.seasonTeamMap.get(teamName);
							String tableField[] = { "oppTotalHit", "oppTotalShot", "oppShot", "oppThreeHit", "oppThreeShot", "oppThree", "oppTwoShot", "oppTwoHit", "oppTwo", "oppFreeHit",
									"oppFreeShot", "oppFree", "oppOfdRebound", "oppDfdRebound", "oppTotRebound", "oppAssist", "oppSteal", "oppBlock", "oppFault", "oppFoul", "oppPoint" };
							boolean isTableSucceed = super.AutoEncapsulate(seasonTeam, tableField, cell);
							if (!isTableSucceed) {
								this.seasonTeamMap.remove(teamName);
							}
						}
					}
				}
			}
		}
	}// 读取对手普通数据表格

	private void readTeamNorTable(TableTag norTable) {
		if (norTable != null) {
			TableRow[] rows = norTable.getRows();
			if (rows != null) {
				for (int i = 0; i < rows.length - 1; i++) {
					TableColumn[] columns = rows[i].getColumns();
					if (columns != null && columns.length == this.NORMAL_TABLE_COLUMN_NUM) {
						String[] cellString = new String[this.NORMAL_TABLE_COLUMN_NUM];
						Double[] cell = new Double[this.NORMAL_TABLE_COLUMN_NUM - 5];
						for (int j = 0; j < this.NORMAL_TABLE_COLUMN_NUM; j++) {
							cellString[j] = columns[j].toPlainTextString();
						}
						String teamName = cellString[1];
						double numOfGame = super.toDouble(cellString[2]);
						double minute = super.toDouble(cellString[3]);
						if (teamName.endsWith("*")) {
							teamName = teamName.substring(0, teamName.length() - 1);
						}
						for (int k = 0; k < this.NORMAL_TABLE_COLUMN_NUM - 5; k++) {
							cell[k] = super.toDouble(cellString[k + 4]);
						}
						if (teamName != null && this.season != null) {
							SeasonTeam seasonTeam = new SeasonTeam();
							String generalField[] = { "teamName", "season", "numOfGame", "minute" };
							Object[] generalObject = { teamName, season, numOfGame, minute };
							boolean isGeneralSucceed = super.AutoEncapsulate(seasonTeam, generalField, generalObject);
							String tableField[] = { "totalHit", "totalShot", "shot", "threeHit", "threeShot", "three", "twoShot", "twoHit", "two", "freeHit", "freeShot", "free", "ofdRebound",
									"dfdRebound", "totRebound", "assist", "steal", "block", "fault", "foul", "point" };
							boolean isTableSucceed = super.AutoEncapsulate(seasonTeam, tableField, cell);
							if (isGeneralSucceed && isTableSucceed) {
								this.seasonTeamMap.put(teamName, seasonTeam);
							}
						}
					}
				}
			}
		}
	}// 读取球队普通数据表格

	public HashMap<String, SeasonTeam> getSeasonTeamMap() {
		return seasonTeamMap;
	}
}
