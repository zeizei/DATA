package start;

import html.MatchHtml;
import html.MatchMapHtml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import database.DB;
import beans.GamePlayer;
import beans.GameTeam;
import beans.GeneralMatch;

public class Task {
	private DB db = DB.getInstance();

	public void getMatchs() {
		MatchMapHtml schedule = new MatchMapHtml("http://www.basketball-reference.com/leagues/NBA_2014_games.html");
		ArrayList<GeneralMatch> generalMatchList = schedule.getGeneralMatchList();
		ArrayList<String> detailMatchUrlList = schedule.getDetailMatchUrlList();
		for (int i = 0; i < detailMatchUrlList.size(); i++) {
			db.update(generalMatchList.get(i).insertTableStr());
			System.out.println("-------------------------" + detailMatchUrlList.get(i) + "-------------------------------------------");
			System.out.println("-------------------------" + generalMatchList.get(i) + "-----------------------------------------------");
			MatchHtml match = new MatchHtml(detailMatchUrlList.get(i), generalMatchList.get(i));
			HashMap<String, GamePlayer> gamePlayerMap = match.getGamePlayerList();
			for (Entry<String, GamePlayer> temp : gamePlayerMap.entrySet()) {
				db.update(temp.getValue().insertTableStr());
			}
			HashMap<String, GameTeam> gameTeamMap = match.getGameTeamList();
			for (Entry<String, GameTeam> temp : gameTeamMap.entrySet()) {
				db.update(temp.getValue().insertTableStr());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void getPlayer() {

	}

	public void getTeam() {

	}
}
