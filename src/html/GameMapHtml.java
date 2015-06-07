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

import beans.GeneralGame;

public class GameMapHtml extends HtmlReader {
	private ArrayList<GeneralGame> generalGameList;// 比赛简略信息链表
	private ArrayList<String> detailGameUrlList;// 比赛详细信息链接链表
	private final int REGULAR_TABLE = 0;// 常规赛表格
	private final int PLAYOFF_TABLE = 1;// 季后赛表格
	private final int COLUMN_NUM = 8;// 表的列数
	private final int DATE = 0;// 比赛日期
	private final int DETAIL_GAME = 1;// 详细比赛信息链接
	private final int HOME_TEAM = 4;// 主队链接
	private final int HOME_TEAM_POINT = 5;// 主队得分
	private final int GUEST_TEAM = 2;// 客队链接
	private final int GUEST_TEAM_POINT = 3;// 客队得分

	public GameMapHtml(String urlString) {
		super(urlString);
		if (super.getIsSucceed()) {
			this.generalGameList = new ArrayList<GeneralGame>(2048);
			this.detailGameUrlList = new ArrayList<String>(2048);
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
			if (nodeList.elementAt(this.REGULAR_TABLE) instanceof TableTag) {
				TableTag tag = (TableTag) nodeList.elementAt(this.REGULAR_TABLE);
				TableRow[] rows = tag.getRows();
				if (rows != null) {
					for (int i = 0; i < rows.length; i++) {
						this.dealWithOneGame(rows[i], 0);
					}
				}
			}// 处理常规赛比赛
			if ((nodeList.size() > this.PLAYOFF_TABLE) && (nodeList.elementAt(this.PLAYOFF_TABLE) instanceof TableTag)) {
				TableTag tag = (TableTag) nodeList.elementAt(this.PLAYOFF_TABLE);
				TableRow[] rows = tag.getRows();
				if (rows != null) {
					for (int i = 0; i < rows.length; i++) {
						this.dealWithOneGame(rows[i], 1);
					}
				}
			}// 处理季后赛比赛
		}
	}

	private void dealWithOneGame(TableRow row, int isPlayOff) {
		if (row != null) {
			TableColumn[] columns = row.getColumns();
			if (columns != null && columns.length == this.COLUMN_NUM) {
				String detailGameUrl = this.getOneDetailGameUrl(columns[this.DETAIL_GAME].getStringText());// 得到详细比赛信息链接
				if (detailGameUrl != null && detailGameUrl.length() >= 47) {// http://www.basketball-reference.com/boxscores/201410280LAL.html
					String gameId = detailGameUrl.substring(46, detailGameUrl.length() - 5);
					String date = this.getDate(columns[this.DATE].toPlainTextString());
					String homeTeam = columns[this.HOME_TEAM].toPlainTextString();
					String homePointStr = columns[this.HOME_TEAM_POINT].toPlainTextString();
					int homePoint = this.toIntPoint(homePointStr);
					String guestTeam = columns[this.GUEST_TEAM].toPlainTextString();
					String guestPointStr = columns[this.GUEST_TEAM_POINT].toPlainTextString();
					int guestPoint = this.toIntPoint(guestPointStr);
					if (gameId != null) {
						GeneralGame generalGame = new GeneralGame();
						String[] fields = new String[] { "gameId", "date", "homeTeam", "homePoint", "guestTeam", "guestPoint", "isPlayOff" };
						Object[] contents = new Object[] { gameId, date, homeTeam, homePoint, guestTeam, guestPoint, isPlayOff };
						boolean isSucceed = generalGame.AutoEncapsulate(fields, contents);
						if (isSucceed) {
							this.generalGameList.add(generalGame);
							this.detailGameUrlList.add(detailGameUrl);
						}
					}
				}
			}
		}
	}// 处理一场比赛

	private int toIntPoint(String pointStr) {
		int result = -1;
		if (pointStr != null && (pointStr = pointStr.trim()) != null) {
			try {
				result = Integer.parseInt(pointStr);
			} catch (NumberFormatException e) {
				result = -1;
			}
		}
		return result;
	}

	private String getDate(String plainText) {
		if (plainText != null) {
			String part[] = plainText.split(",");
			if (part != null && part.length == 3) {
				String year = part[2].trim();
				String monthAndDay = part[1].trim();
				if (year != null && monthAndDay != null) {
					String str[] = monthAndDay.split(" ");
					if (str != null && str.length == 2) {
						String day = str[1].trim();
						if (day != null && day.length() == 1) {
							day = "0" + day;
						}
						String month = str[0].trim();
						switch (month) {
						case "Jan":
							month = "01";
							break;
						case "Feb":
							month = "02";
							break;
						case "Mar":
							month = "03";
							break;
						case "Apr":
							month = "04";
							break;
						case "May":
							month = "05";
							break;
						case "Jun":
							month = "06";
							break;
						case "Jul":
							month = "07";
							break;
						case "Aug":
							month = "08";
							break;
						case "Sep":
							month = "09";
							break;
						case "Oct":
							month = "10";
							break;
						case "Nov":
							month = "11";
							break;
						case "Dec":
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
	}// 得到比赛日期

	private String getOneDetailGameUrl(String text) {
		if (text != null) {
			String part[] = text.split("\"");
			if (part != null && part.length == 3) {
				String behindUrl = part[1];
				String url = super.getHomeUrlString() + behindUrl;
				return url;
			}
		}
		return null;
	}// 得到一场比赛详细信息的链接

	public ArrayList<GeneralGame> getGeneralGameList() {
		return generalGameList;
	}// 得到一个赛季的比赛简略信息

	public ArrayList<String> getDetailGameUrlList() {
		return detailGameUrlList;
	}// 得到一个赛季所有比赛详细信息的链表
}
