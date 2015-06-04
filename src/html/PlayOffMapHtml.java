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

import beans.PlayOffSeries;

public class PlayOffMapHtml extends HtmlReader {
	private ArrayList<PlayOffSeries> playOffSeriesList;
	private final int PLAYOFF_SERIES_TABLE = 0;
	private final int COLUMN_NUM = 22;
	private String startDate;
	private String finishDate;

	public PlayOffMapHtml(String urlString) {
		super(urlString);
		if (super.getIsSucceed()) {
			playOffSeriesList = new ArrayList<PlayOffSeries>(128);
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
			if (nodeList.elementAt(this.PLAYOFF_SERIES_TABLE) instanceof TableTag) {
				TableTag tag = (TableTag) nodeList.elementAt(this.PLAYOFF_SERIES_TABLE);
				TableRow[] rows = tag.getRows();
				if (rows != null) {
					for (int i = 0; i < rows.length; i++) {
						this.dealWithOneSeries(rows[i]);
					}
				}
			}
		}
	}

	private void dealWithOneSeries(TableRow row) {
		if (row != null) {
			TableColumn[] columns = row.getColumns();
			if (columns != null && columns.length == this.COLUMN_NUM) {
				String[] cellString = new String[this.COLUMN_NUM];
				for (int i = 0; i < this.COLUMN_NUM; i++) {
					cellString[i] = columns[i].toPlainTextString();
				}
				int year = super.toInt(cellString[0]);
				String league = cellString[1];
				String series = cellString[2];
				this.getStartAndFinishDate(cellString[3]);
				String winTeam = this.getTeamName(cellString[5]);
				String loseTeam = this.getTeamName(cellString[12]);
				int winTeamWin = super.toInt(cellString[6]);
				int loseTeamWin = super.toInt(cellString[13]);
				String[] fields = { "year", "winTeam", "loseTeam", "league", "series", "startDate", "finishDate", "winTeamWin", "loseTeamWin" };
				Object[] contents = { year, winTeam, loseTeam, league, series, startDate, finishDate, winTeamWin, loseTeamWin };
				PlayOffSeries playOffSeries = new PlayOffSeries();
				boolean isSucceed = super.AutoEncapsulate(playOffSeries, fields, contents);
				if (isSucceed) {
					this.playOffSeriesList.add(playOffSeries);
				}
			}
		}
	}

	private String getTeamName(String str) {
		if (str != null) {
			String part[] = str.split("\\(");
			if (part != null && part.length <= 2) {
				return part[0].trim();
			}
		}
		return null;
	}

	private void getStartAndFinishDate(String str) {
		if (str != null) {
			String part[] = str.split(",");
			if (part != null && part.length == 2) {
				String year = part[1].trim();
				String[] subPart = part[0].trim().split("-");
				if (subPart != null && subPart.length == 2) {
					String startDayAndMonth = this.getMonthAndDay(subPart[0].trim());
					String finishDayAndMonth = this.getMonthAndDay(subPart[1].trim());
					this.startDate = year + "-" + startDayAndMonth;
					this.finishDate = year + "-" + finishDayAndMonth;
				}
			}
		}
	}

	private String getMonthAndDay(String str) {
		if (str != null) {
			String part[] = str.split(" ");
			if (part != null && part.length == 2) {
				String day = part[1].trim();
				String month = part[0].trim();
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
				if (month != null) {
					return month + "-" + day;
				}
			}
		}
		return null;
	}

	public ArrayList<PlayOffSeries> getPlayOffSeriesList() {
		return this.playOffSeriesList;
	}
}
