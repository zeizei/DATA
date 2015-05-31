package start;

import html.MatchHtml;
import html.MatchMapHtml;

import java.util.ArrayList;

import beans.GeneralMatch;

public class Driver {
	public void testMatchMapHtml() {
		MatchMapHtml schedule = new MatchMapHtml("http://www.basketball-reference.com/leagues/NBA_2015_games.html");
		ArrayList<GeneralMatch> generalMatchList = schedule.getGeneralMatchList();
		ArrayList<String> detailMatchUrlList = schedule.getDetailMatchUrlList();
		for (int i = 0; i < generalMatchList.size(); i++) {
			System.out.println(generalMatchList.get(i));
		}
		for (int i = 0; i < detailMatchUrlList.size(); i++) {
			System.out.println(detailMatchUrlList.get(i));
		}
	}

	public void testMatchHtml() {
		MatchMapHtml schedule = new MatchMapHtml("http://www.basketball-reference.com/leagues/NBA_2015_games.html");
		ArrayList<GeneralMatch> generalMatchList = schedule.getGeneralMatchList();
		ArrayList<String> detailMatchUrlList = schedule.getDetailMatchUrlList();
		MatchHtml match = new MatchHtml(detailMatchUrlList.get(0), generalMatchList.get(0));
	}

	public static void main(String[] args) {
		Driver driver = new Driver();
		// driver.testScheduleHtml();
		driver.testMatchHtml();
	}
}
