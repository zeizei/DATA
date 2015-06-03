package start;

import html.MatchHtml;
import html.MatchMapHtml;
import html.PlayerMapHtml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import beans.GamePlayer;
import beans.GameTeam;
import beans.GeneralMatch;
import beans.GeneralPlayer;

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
			HashMap<String, GamePlayer> gamePlayerMap = match.getGamePlayerMap();
			for (Entry<String, GamePlayer> temp : gamePlayerMap.entrySet()) {
				System.out.println(temp.getValue().toString());
			}
			HashMap<String, GameTeam> gameTeamMap = match.getGameTeamMap();
			for (Entry<String, GameTeam> temp : gameTeamMap.entrySet()) {
				System.out.println(temp.getValue().toString());
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void testPlayerMapHtml() {
		PlayerMapHtml playerMap = new PlayerMapHtml("http://www.basketball-reference.com/players/a/");
		ArrayList<GeneralPlayer> generalPlayerList = playerMap.getGeneralPlayerList();
		for (int i = 0; i < generalPlayerList.size(); i++) {
			System.out.println(generalPlayerList.get(i).toString());
		}
		ArrayList<String> detailPlayerUrlList = playerMap.getDetailPlayerUrlList();
		for (int i = 0; i < detailPlayerUrlList.size(); i++) {
			System.out.println(detailPlayerUrlList.get(i).toString());
		}
	}

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.testPlayerMapHtml();
	}
}
