package beans;

public class GeneralGame extends Bean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	protected String gameId;// 比赛编号
	//
	private String date;// 比赛日期
	private String homeTeam;// 主场球队
	private String guestTeam;// 客场球队
	private int homePoint;// 主场球队得分
	private int guestPoint;// 客场球队得分
	private int isPlayOff;// 是否季后

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public int getHomePoint() {
		return homePoint;
	}

	public void setHomePoint(int homePoint) {
		this.homePoint = homePoint;
	}

	public int getGuestPoint() {
		return guestPoint;
	}

	public void setGuestPoint(int guestPoint) {
		this.guestPoint = guestPoint;
	}

	public int getIsPlayOff() {
		return isPlayOff;
	}

	public void setIsPlayOff(int isPlayOff) {
		this.isPlayOff = isPlayOff;
	}
}