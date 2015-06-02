package beans;

public class GeneralPlayer extends Bean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键 playerName,birthday
	protected String playerName;
	private int startYear;
	private int finishYear;
	private String position;
	private String height;
	private String weight;
	protected String birthday;
	private String collage;

	public String getPlayerName() {
		if (this.playerName.contains("'")) {
			this.playerName = this.playerName.replace('\'', '-');
		}
		return playerName;
	}

	public void setPlayerName(String playerName) {
		if (playerName.contains("'")) {
			playerName = playerName.replace('\'', '-');
		}
		this.playerName = playerName;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getFinishYear() {
		return finishYear;
	}

	public void setFinishYear(int finishYear) {
		this.finishYear = finishYear;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCollage() {
		if (this.collage.contains("'")) {
			this.collage = this.collage.replace('\'', '-');
		}
		return this.collage;
	}

	public void setCollage(String collage) {
		if (collage.contains("'")) {
			collage = collage.replace('\'', '-');
		}
		this.collage = collage;
	}
}
