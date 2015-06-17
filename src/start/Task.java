package start;

import html.GameHtml;
import html.GameMapHtml;
import html.PlayOffMapHtml;
import html.PlayerHtml;
import html.PlayerImageHtml;
import html.PlayerMapHtml;
import html.SeasonHtml;
import html.SeasonMapHtml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import beans.GamePlayer;
import beans.GameTeam;
import beans.GeneralGame;
import beans.GeneralPlayer;
import beans.PlayOffSeries;
import beans.SeasonPlayer;
import beans.SeasonTeam;
import database.DB;

public class Task {
	private DB db = DB.getInstance();

	public void getGames() {
		String before = "http://www.basketball-reference.com/leagues/NBA_";
		String urlString = null;
		String after = "_games.html";
		for (int m = 2015; m >= 2015; m--) {
			urlString = before + String.valueOf(m) + after;
			GameMapHtml schedule = new GameMapHtml(urlString);
			ArrayList<GeneralGame> generalGameList = schedule.getGeneralGameList();
			ArrayList<String> detailGameUrlList = schedule.getDetailGameUrlList();
			for (int i = detailGameUrlList.size() - 1; i < detailGameUrlList.size(); i++) {
				db.update(generalGameList.get(i).getInsertTableStr());
				System.out.println("-------------------------" + detailGameUrlList.get(i) + "-------------------------------------------");
				System.out.println("------" + generalGameList.get(i) + "-----------------------------------------------");
				GameHtml gameHtml = new GameHtml(detailGameUrlList.get(i), generalGameList.get(i));
				HashMap<String, GamePlayer> gamePlayerMap = gameHtml.getGamePlayerMap();
				for (Entry<String, GamePlayer> temp : gamePlayerMap.entrySet()) {
					db.update(temp.getValue().getInsertTableStr());
				}
				HashMap<String, GameTeam> gameTeamMap = gameHtml.getGameTeamMap();
				for (Entry<String, GameTeam> temp : gameTeamMap.entrySet()) {
					db.update(temp.getValue().getInsertTableStr());
				}
			}
		}
	}// 得到比赛简略信息和详细信息

	public void getPlayer() {
		String UrlString = "http://www.basketball-reference.com/players/a";
		for (int k = 0; k < 26; k++) {
			PlayerMapHtml playerMap = new PlayerMapHtml(UrlString);
			ArrayList<GeneralPlayer> generalPlayerList = playerMap.getGeneralPlayerList();
			for (int i = 0; i < generalPlayerList.size(); i++) {
				db.update(generalPlayerList.get(i).getInsertTableStr());
			}
			ArrayList<String> detailPlayerUrlList = playerMap.getDetailPlayerUrlList();
			for (int i = 0; i < detailPlayerUrlList.size(); i++) {
				System.out.println(detailPlayerUrlList.get(i));
				PlayerHtml playerHtml = new PlayerHtml(detailPlayerUrlList.get(i), generalPlayerList.get(i).getPlayerName());
				HashMap<String, SeasonPlayer> playerSeasonMap = playerHtml.getSeasonPlayerList();
				for (Entry<String, SeasonPlayer> temp : playerSeasonMap.entrySet()) {
					db.update(temp.getValue().getInsertTableStr());
				}
			}
			String before = UrlString;
			UrlString = before.substring(0, UrlString.length() - 1) + String.valueOf(((char) (before.charAt(before.length() - 1) + 1)));
		}
	}// 得到球员基本信息和每个赛季比赛信息

	public void getTeam() {
		String url = "http://www.basketball-reference.com/leagues";
		SeasonMapHtml seasonMapHtml = new SeasonMapHtml(url);
		ArrayList<String> seasonUrlList = seasonMapHtml.getSeasonUrlList();
		ArrayList<String> seasonList = seasonMapHtml.getSeasonList();
		for (int i = 0; i < seasonUrlList.size(); i++) {
			SeasonHtml seasonHtml = new SeasonHtml(seasonUrlList.get(i), seasonList.get(i));
			System.out.println(seasonUrlList.get(i));
			HashMap<String, SeasonTeam> seasonTeamMap = seasonHtml.getSeasonTeamMap();
			for (Entry<String, SeasonTeam> temp : seasonTeamMap.entrySet()) {
				db.update(temp.getValue().getInsertTableStr());
			}
		}
	}// 得到球队常规赛的每个赛季比赛信息

	public void getPlayOff() {
		String urlString = "http://www.basketball-reference.com/playoffs";
		PlayOffMapHtml playOffHtml = new PlayOffMapHtml(urlString);
		ArrayList<PlayOffSeries> playOffSeriesList = playOffHtml.getPlayOffSeriesList();
		for (int i = 0; i < playOffSeriesList.size(); i++) {
			db.update(playOffSeriesList.get(i).getInsertTableStr());
		}
	}

	public void getPlayerImage() {
		String UrlString = "http://www.basketball-reference.com/players/a";
		for (int k = 0; k < 26; k++) {
			PlayerMapHtml playerMap = new PlayerMapHtml(UrlString);
			ArrayList<String> detailPlayerUrlList = playerMap.getDetailPlayerUrlList();
			for (int i = 0; i < detailPlayerUrlList.size(); i++) {
				String htmlUrl = detailPlayerUrlList.get(i);
				PlayerImageHtml playerImage = new PlayerImageHtml(htmlUrl);
				if (!playerImage.getImageJPG()) {
					playerImage.getImagePNG();
				}
			}
			String before = UrlString;
			UrlString = before.substring(0, UrlString.length() - 1) + String.valueOf(((char) (before.charAt(before.length() - 1) + 1)));
		}
	}

	public void fixNumOfWinAndLose() {
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.basketball-reference.com/leagues").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc != null) {
			Elements table = doc.getElementsByTag("table");
			Elements trs = table.get(0).getElementsByTag("tr");
			for (int i = 0; i < trs.size(); i++) {
				Elements tds = trs.get(i).getElementsByTag("td");
				if (tds != null && tds.size() > 0) {
					Elements a = tds.get(0).getElementsByTag("a");
					String url = "http://www.basketball-reference.com" + a.get(0).attr("href");
					String season = a.get(0).html();
					System.out.println(url);
					System.out.println(season);
					this.fixOneSeason(url, season);
				}
			}
		}
	}

	private void fixOneSeason(String url, String season) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc != null) {
			Element west = doc.getElementById("W_standings");
			Element east = doc.getElementById("E_standings");
			Element _standings = doc.getElementById("_standings");
			if (west != null && east != null) {
				this.dwOneDivision(west, season);
				this.dwOneDivision(east, season);
			}
			else {
				this.dwOneDivision(_standings, season);
			}

		}
	}

	public void dwOneDivision(Element ele, String season) {
		if (ele != null) {
			Elements teams = ele.getElementsByClass("full_table");
			for (int i = 0; i < teams.size(); i++) {
				Elements tds = teams.get(i).getElementsByTag("td");
				String teamName = tds.get(0).getElementsByTag("a").text();
				try {
					double numOfWin = Double.parseDouble(tds.get(1).text());
					double numOfLose = Double.parseDouble(tds.get(2).text());
					String sql = "update seasonteam set numOfWin = " + numOfWin + ",numOfLose = " + numOfLose + " where teamName = '" + teamName + "' and season = '" + season + "'";
					DB.getInstance().update(sql);
				} catch (NumberFormatException e) {
					System.out.println(teamName + season);
				}
			}
		}
	}

	public void createDB() {
		DB db = DB.getInstance();
		db.update(new GamePlayer().getCreateTableStr());
		db.update(new GameTeam().getCreateTableStr());
		db.update(new GeneralGame().getCreateTableStr());
		db.update(new GeneralPlayer().getCreateTableStr());
		db.update(new SeasonPlayer().getCreateTableStr());
		db.update(new SeasonTeam().getCreateTableStr());
		db.update(new PlayOffSeries().getCreateTableStr());
	}// 建立数据库表格
}
