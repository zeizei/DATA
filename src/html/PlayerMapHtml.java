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

import beans.GeneralPlayer;

public class PlayerMapHtml extends HtmlReader {
	private ArrayList<GeneralPlayer> generalPlayerList;// 球员基本信息链表
	private ArrayList<String> detailPlayerUrlList;// 球员详细信息网页地址链接链表
	private final int PLAYER_TABLE = 0;
	private final int PLAYER_TABLE_COLUMN_NUM = 8;// 球员基本信息表格对的列数
	private final int PLAYER_NAME = 0;
	private final int START_YEAR = 1;
	private final int FINISH_YEAR = 2;
	private final int POSITION = 3;
	private final int HEIGHT = 4;
	private final int WEIGHT = 5;
	private final int BIRTHDAY = 6;
	private final int COLLEGE = 7;

	public PlayerMapHtml(String urlString) {
		super(urlString);
		if (super.getIsSucceed()) {
			this.generalPlayerList = new ArrayList<GeneralPlayer>(1024);
			this.detailPlayerUrlList = new ArrayList<String>(1024);
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
			if (nodeList.elementAt(this.PLAYER_TABLE) instanceof TableTag) {
				TableTag tag = (TableTag) nodeList.elementAt(this.PLAYER_TABLE);
				TableRow[] rows = tag.getRows();
				if (rows != null) {
					for (int i = 0; i < rows.length; i++) {
						this.dwOnePlayer(rows[i]);
					}
				}
			}// 处理常规赛比赛
		}
	}

	private void dwOnePlayer(TableRow row) {
		if (row != null) {
			TableColumn[] columns = row.getColumns();
			if (columns != null && columns.length == this.PLAYER_TABLE_COLUMN_NUM) {
				String playerName = this.getPlayerNameAndFillUrl(columns[this.PLAYER_NAME].getStringText());
				String playerId = this.detailPlayerUrlList.get(detailPlayerUrlList.size() - 1);
				playerId = playerId.substring(46, playerId.length() - 5);
				int startYear = super.toInt(columns[this.START_YEAR].toPlainTextString());
				int finishYear = super.toInt(columns[this.FINISH_YEAR].toPlainTextString());
				String position = columns[this.POSITION].toPlainTextString();
				String height = columns[this.HEIGHT].toPlainTextString();
				String weight = columns[this.WEIGHT].toPlainTextString();
				String birthday = this.getBirthday(columns[this.BIRTHDAY].toPlainTextString());
				String collage = columns[this.COLLEGE].toPlainTextString();
				if (playerName != null) {
					GeneralPlayer generalPlayer = new GeneralPlayer();
					String[] fields = { "playerId", "playerName", "startYear", "finishYear", "position", "height", "weight", "birthday", "collage" };
					Object[] contents = { playerId, playerName, startYear, finishYear, position, height, weight, birthday, collage };
					boolean isSucceed = generalPlayer.AutoEncapsulate(fields, contents);
					if (isSucceed) {
						this.generalPlayerList.add(generalPlayer);
					}
				}
			}
		}
	}

	private String getBirthday(String plainTextString) {
		if (plainTextString != null) {
			String[] part = plainTextString.split(",");
			if (part != null && part.length == 2) {
				String year = part[1].trim();
				String monthAndDay = part[0].trim();
				if (year != null && monthAndDay != null) {
					String subPart[] = monthAndDay.split(" ");
					if (subPart != null && subPart.length == 2) {
						String day = subPart[1].trim();
						if (day != null && day.length() == 1) {
							day = "0" + day;
						}
						String month = subPart[0].trim();
						switch (month) {
						case "January":
							month = "01";
							break;
						case "February":
							month = "02";
							break;
						case "March":
							month = "03";
							break;
						case "April":
							month = "04";
							break;
						case "May":
							month = "05";
							break;
						case "June":
							month = "06";
							break;
						case "July":
							month = "07";
							break;
						case "August":
							month = "08";
							break;
						case "September":
							month = "09";
							break;
						case "October":
							month = "10";
							break;
						case "November":
							month = "11";
							break;
						case "December":
							month = "12";
							break;
						default:
							month = null;
							break;
						}
						if (year != null && month != null && day != null) {
							StringBuffer buffer = new StringBuffer();
							buffer.append(year).append("-").append(month).append("-").append(day);
							return buffer.toString();
						}
					}
				}
			}
		}
		return null;
	}

	private String getPlayerNameAndFillUrl(String str) {
		String[] part = str.split("\"");
		if (part != null && part.length == 3) {
			String detailPlayerUrl = super.getHomeUrlString() + part[1];
			this.detailPlayerUrlList.add(detailPlayerUrl);
			String playerName = part[2];
			playerName = playerName.substring(1);
			String subPart[] = playerName.split("</a>");
			if (subPart != null) {
				playerName = subPart[0];
				return playerName;
			}
		}
		return null;
	}

	public ArrayList<GeneralPlayer> getGeneralPlayerList() {
		return this.generalPlayerList;

	}

	public ArrayList<String> getDetailPlayerUrlList() {
		return this.detailPlayerUrlList;
	}
}
