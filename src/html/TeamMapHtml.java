package html;

import java.util.ArrayList;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class TeamMapHtml extends HtmlReader {
	private ArrayList<String> teamSeasonMapUrlList;
	private ArrayList<String> teamNameList;
	private final int FIRST_TEAM_LINK = 33;// 球队链接的第一个
	private final int LAST_TEAM_LINK = 85;// 球队连接的最后一个

	public TeamMapHtml(String urlString) {
		super(urlString);
		if (super.getIsSucceed()) {
			teamSeasonMapUrlList = new ArrayList<String>(128);
			teamNameList = new ArrayList<String>(128);
			this.readPage();
		}
	}

	private void readPage() {
		Parser parser = Parser.createParser(super.getHtmlString(), super.getCodeSet());
		NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
		NodeList nodeList = null;
		try {
			nodeList = parser.extractAllNodesThatMatch(linkFilter);// 找到网页中的table
			for (int i = this.FIRST_TEAM_LINK; i <= this.LAST_TEAM_LINK; i++) {
				if (nodeList != null && nodeList.size() >= this.LAST_TEAM_LINK && nodeList.elementAt(i) instanceof LinkTag) {
					LinkTag linkTag = (LinkTag) nodeList.elementAt(i);
					String link = linkTag.getLink();
					String teamName = linkTag.getLinkText();
					if (link != null && teamName != null) {
						this.teamSeasonMapUrlList.add(super.getHomeUrlString() + link);
						this.teamNameList.add(teamName);
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getTeamSeasonMapUrlList() {
		return teamSeasonMapUrlList;
	}

	public ArrayList<String> getTeamNameList() {
		return teamNameList;
	}
}
