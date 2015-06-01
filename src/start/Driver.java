package start;

import html.MatchHtml;
import html.MatchMapHtml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import beans.GamePlayer;
import beans.GameTeam;
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
		for (int i = 0; i < detailMatchUrlList.size(); i++) {
			System.out.println("-------------------------" + detailMatchUrlList.get(i) + "-------------------------------------------");
			System.out.println("-------------------------" + generalMatchList.get(i) + "-----------------------------------------------");
			MatchHtml match = new MatchHtml(detailMatchUrlList.get(i), generalMatchList.get(i));
			HashMap<String, GamePlayer> gamePlayerMap = match.getGamePlayerList();
			for (Entry<String, GamePlayer> temp : gamePlayerMap.entrySet()) {
				System.out.println(temp.getValue().toString());
			}
			HashMap<String, GameTeam> gameTeamMap = match.getGameTeamList();
			for (Entry<String, GameTeam> temp : gameTeamMap.entrySet()) {
				System.out.println(temp.getValue().toString());
				for (int j = 0; j < temp.getValue().getQuarterPoint().length; j++) {
					System.out.print(temp.getValue().getQuarterPoint()[j] + "--");
				}
				System.out.println();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Driver driver = new Driver();
		// driver.testScheduleHtml();
		driver.testMatchHtml();
	}
}
