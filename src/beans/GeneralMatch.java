package beans;

public class GeneralMatch extends Bean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	protected String date;// 比赛日期
	protected String homeTeam;// 主场球队
	//
	private String guestTeam;// 客场球队
	private int homePoint;// 主场球队得分
	private int guestPoint;// 客场球队得分
	private int isPlayOff;// 是否季后赛

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