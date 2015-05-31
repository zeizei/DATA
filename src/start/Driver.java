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
		MatchHtml match = new MatchHtml("http://www.basketball-reference.com/boxscores/201410280LAL.html");
	}

	public static void main(String[] args) {
		Driver driver = new Driver();
		// driver.testScheduleHtml();
		driver.testMatchHtml();
	}
}
