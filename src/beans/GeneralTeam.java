package beans;

public class GeneralTeam extends Bean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	private String teamName;
	private String location;
	private int seasons;
	//
	private int playOff;
	private int championship;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getSeasons() {
		return seasons;
	}

	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}

	public int getPlayOff() {
		return playOff;
	}

	public void setPlayOff(int playOff) {
		this.playOff = playOff;
	}

	public int getChampionship() {
		return championship;
	}

	public void setChampionship(int championship) {
		this.championship = championship;
	}
}
