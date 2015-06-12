package html;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TeamImageHtml {
	public TeamImageHtml() {
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.sportslogos.net/teams/list_by_league/18/American_Basketball_Association/ABA/logos/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc != null) {
			Elements contents = doc.getElementsByClass("logoWall");
			// Elements currentTeamLi = contents.get(0).getElementsByTag("li");
			// for (int i = 0; i < currentTeamLi.size(); i++) {
			// this.dwCurrentTeamLi(currentTeamLi.get(i));
			// }
			Elements pastTeamLi = contents.get(1).getElementsByTag("li");
			for (int i = 0; i < pastTeamLi.size(); i++) {
				this.dwPastTeamLi(pastTeamLi.get(i));
			}
		}
	}

	private void dwPastTeamLi(Element element) {
		Element a = element.getElementsByTag("a").first();
		Element img = element.getElementsByTag("img").first();
		String imgSrc = img.toString().substring(10, img.toString().length() - 4).trim();
		String teamName = a.text();
		String temp = element.text();
		String seasonStr = temp.substring(teamName.length()).trim();
		String startSeason = seasonStr.substring(0, 7).trim().replace('/', '-');
		String finishSeason = startSeason;
		if (seasonStr.length() == 17) {
			finishSeason = seasonStr.substring(10).trim().replace('/', '-');
		}
		String imgName = teamName + " " + startSeason + " " + finishSeason;
		try {
			URL url = new URL(imgSrc);
			DataInputStream dis = new DataInputStream(url.openStream());
			String newImageName = "D:/SE3/team_logo/" + imgName + ".png";// 建立一个新的文件
			FileOutputStream fos = new FileOutputStream(new File(newImageName));
			byte[] buffer = new byte[1024];
			int length;// 开始填充数据
			while ((length = dis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			dis.close();
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void dwCurrentTeamLi(Element element) {
		Element img = element.getElementsByTag("img").first();
		String imgSrc = img.toString().substring(10, img.toString().length() - 4).trim();
		String temp = element.text();
		String teamName = temp.substring(0, temp.length() - 14).trim();
		String startSeason = temp.substring(temp.length() - 7).trim().replace('/', '-');
		String finishSeason = "2014-15";
		String imgName = teamName + " " + startSeason + " " + finishSeason;
		System.out.println(temp);
		System.out.println(teamName);
		System.out.println(imgSrc);
		System.out.println(startSeason);
		try {
			URL url = new URL(imgSrc);
			DataInputStream dis = new DataInputStream(url.openStream());
			String newImageName = "D:/SE3/team_logo/" + imgName + ".png";// 建立一个新的文件
			FileOutputStream fos = new FileOutputStream(new File(newImageName));
			byte[] buffer = new byte[1024];
			int length;// 开始填充数据
			while ((length = dis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			dis.close();
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		new TeamImageHtml();
	}
}
