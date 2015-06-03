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

import beans.GeneralMatch;

public class MatchMapHtml extends HtmlReader {
	private ArrayList<GeneralMatch> generalmatchList;// 比赛简略信息链表
	private ArrayList<String> detailMatchUrlList;// 比赛详细信息链接链表
	private final int REGULAR_TABLE = 0;// 常规赛表格
	private final int PLAYOFF_TABLE = 1;// 季后赛表格
	private final int COLUMN_NUM = 8;// 表的列数
	private final int DATE = 0;// 比赛日期
	private final int DETAIL_MATCH = 1;// 详细比赛信息链接
	private final int HOME_TEAM = 2;// 主队链接
	private final int HOME_TEAM_POINT = 3;// 主队得分
	private final int GUEST_TEAM = 4;// 客队链接
	private final int GUEST_TEAM_POINT = 5;// 客队得分

	public MatchMapHtml(String urlString) {
		super(urlString);
		if (super.getIsSucceed()) {
			this.generalmatchList = new ArrayList<GeneralMatch>(2048);
			this.detailMatchUrlList = new ArrayList<String>(2048);
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
						this.dealWithOneMatch(rows[i], 0);
					}
				}
			}// 处理常规赛比赛
			if ((nodeList.size() > this.PLAYOFF_TABLE) && (nodeList.elementAt(this.PLAYOFF_TABLE) instanceof TableTag)) {
				TableTag tag = (TableTag) nodeList.elementAt(this.PLAYOFF_TABLE);
				TableRow[] rows = tag.getRows();
				if (rows != null) {
					for (int i = 0; i < rows.length; i++) {
						this.dealWithOneMatch(rows[i], 1);
					}
				}
			}// 处理季后赛比赛
		}
	}

	private void dealWithOneMatch(TableRow row, int isPlayOff) {

		if (row != null) {
			TableColumn[] columns = row.getColumns();
			if (columns != null && columns.length == this.COLUMN_NUM) {
				String date = this.getDate(columns[this.DATE].toPlainTextString());
				String homeTeam = columns[this.HOME_TEAM].toPlainTextString();
				String homePointStr = columns[this.HOME_TEAM_POINT].toPlainTextString();
				int homePoint = this.toIntPoint(homePointStr);
				String guestTeam = columns[this.GUEST_TEAM].toPlainTextString();
				String guestPointStr = columns[this.GUEST_TEAM_POINT].toPlainTextString();
				int guestPoint = this.toIntPoint(guestPointStr);
				boolean isCorrect = false;
				if (homePoint != -1 && guestPoint != -1 && homeTeam != null && guestTeam != null && (homeTeam = homeTeam.trim()) != null && (guestTeam = guestTeam.trim()) != null) {
					isCorrect = true;
				}
				if (isCorrect) {
					GeneralMatch generalMatch = new GeneralMatch();
					generalMatch.setDate(date);
					generalMatch.setHomeTeam(homeTeam);
					generalMatch.setHomePoint(homePoint);
					generalMatch.setGuestTeam(guestTeam);
					generalMatch.setGuestPoint(guestPoint);
					generalMatch.setIsPlayOff(isPlayOff);
					this.generalmatchList.add(generalMatch);
					this.getOneDetailMatchUrl(columns[this.DETAIL_MATCH].getStringText());// 得到详细比赛信息链接
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

	private void getOneDetailMatchUrl(String text) {
		if (text != null) {
			String part[] = text.split("\"");
			if (part != null && part.length == 3) {
				String behindUrl = part[1];
				String url = super.getHomeUrlString() + behindUrl;
				this.detailMatchUrlList.add(url);
			}
		}
	}// 得到一场比赛详细信息的链接

	public ArrayList<GeneralMatch> getGeneralMatchList() {
		return generalmatchList;
	}// 得到一个赛季的比赛简略信息

	public ArrayList<String> getDetailMatchUrlList() {
		return detailMatchUrlList;
	}// 得到一个赛季所有比赛详细信息的链表
}
