package start;

import html.MatchHtml;
import html.MatchMapHtml;
import html.PlayerMapHtml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import database.DB;
import beans.GamePlayer;
import beans.GameTeam;
import beans.GeneralMatch;
import beans.GeneralPlayer;
import beans.GeneralTeam;
import beans.SeasonPlayer;
import beans.SeasonTeam;

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
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void getPlayer() {
		PlayerMapHtml playerMap = new PlayerMapHtml("http://www.basketball-reference.com/players/z/");
		ArrayList<GeneralPlayer> generalPlayerList = playerMap.getGeneralPlayerList();
		for (int i = 0; i < generalPlayerList.size(); i++) {
			// db.update(generalPlayerList.get(i).insertTableStr());
			System.out.println(generalPlayerList.get(i).toString());
		}
		ArrayList<String> detailPlayerUrlList = playerMap.getDetailPlayerUrlList();
		for (int i = 0; i < detailPlayerUrlList.size(); i++) {
			System.out.println(detailPlayerUrlList.get(i).toString());
		}
	}

	public void getTeam() {

	}

	public void createDB() {
		DB db = DB.getInstance();
		db.update(new GamePlayer().createTableStr());
		db.update(new GameTeam().createTableStr());
		db.update(new GeneralMatch().createTableStr());
		db.update(new GeneralPlayer().createTableStr());
		db.update(new GeneralTeam().createTableStr());
		db.update(new SeasonPlayer().createTableStr());
		db.update(new SeasonTeam().createTableStr());
	}// 建立数据库表格
}
