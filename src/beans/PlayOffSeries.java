package beans;

public class PlayOffSeries extends Bean {
	private static final long serialVersionUID = 1L;
	// 主键
	protected int year;
	protected String winTeam;
	protected String loseTeam;
	protected String series;
	//
	private String league;
	private String startDate;
	private String finishDate;
	private int winTeamWin;
	private int loseTeamWin;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getWinTeam() {
		return winTeam;
	}

	public void setWinTeam(String winTeam) {
		this.winTeam = winTeam;
	}

	public String getLoseTeam() {
		return loseTeam;
	}

	public void setLoseTeam(String loseTeam) {
		this.loseTeam = loseTeam;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public int getWinTeamWin() {
		return winTeamWin;
	}

	public void setWinTeamWin(int winTeamWin) {
		this.winTeamWin = winTeamWin;
	}

	public int getLoseTeamWin() {
		return loseTeamWin;
	}

	public void setLoseTeamWin(int loseTeamWin) {
		this.loseTeamWin = loseTeamWin;
	}
}
