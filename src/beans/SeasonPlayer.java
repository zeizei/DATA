package beans;

public class SeasonPlayer {
	// 主键
	private String name;// 姓名
	private String season;// 赛季
	private String teamName;// 球队名称
	// 求普通赛季数据
	private int age;// 年龄
	private String position;// 位置
	private int numOfGame;// 参赛场数
	private int numOfStart;// 首发次数
	private double minute;// 上场时间
	private int totalHit;// 总命中数
	private int totalShot;// 总出手数
	private double shot;// 总命中率
	private int threeHit;// 三分命中数
	private int threeShot;// 三分出手数
	private double three;// 三分命中率
	private int twoShot;// 两分出手数
	private int twoHit;// 两分命中数
	private double two;// 两分命中率
	private int freeHit;// 罚球命中数
	private int freeShot;// 罚球出手数
	private double free;// 罚球命中率
	private int dfdRebound;// 后场篮板
	private int ofdRebound;// 前场篮板
	private int totRebound;// 总篮板
	private int assist;// 助攻数
	private int steal;// 抢断数
	private int block;// 盖帽数
	private int fault;// 失误
	private int foul;// 犯规
	private int point;// 得分
	// 球员高级赛季数据
	private double playerEFF;// 球员效率值
	private double realShot;// 真实命中率
	private double shotEFF;// 投篮效率
	private double threeEFF;// 三分效率
	private double freeEFF;// 罚球效率
	private double offReboundEFF;// 进攻篮板效率
	private double defReboundEFF;// 防守篮板效率
	private double totReboundEFF;// 总篮板效率
	private double assistEFF;// 助攻效率
	private double stealEFF;// 抢断效率
	private double blockEFF;// 盖帽效率
	private double faultEFF;// 失误率
	private double useEFF;// 使用率
	private double offWinShare;
	private double defWinShare;
	private double winShare;
	private double offBoxPM;
	private double defBoxPM;
	private double BoxPM;//
	private double replaceValue;// 替换价值

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getNumOfGame() {
		return numOfGame;
	}

	public void setNumOfGame(int numOfGame) {
		this.numOfGame = numOfGame;
	}

	public int getNumOfStart() {
		return numOfStart;
	}

	public void setNumOfStart(int numOfStart) {
		this.numOfStart = numOfStart;
	}

	public double getMinute() {
		return minute;
	}

	public void setMinute(double minute) {
		this.minute = minute;
	}

	public int getTotalHit() {
		return totalHit;
	}

	public void setTotalHit(int totalHit) {
		this.totalHit = totalHit;
	}

	public int getTotalShot() {
		return totalShot;
	}

	public void setTotalShot(int totalShot) {
		this.totalShot = totalShot;
	}

	public double getShot() {
		return shot;
	}

	public void setShot(double shot) {
		this.shot = shot;
	}

	public int getThreeHit() {
		return threeHit;
	}

	public void setThreeHit(int threeHit) {
		this.threeHit = threeHit;
	}

	public int getThreeShot() {
		return threeShot;
	}

	public void setThreeShot(int threeShot) {
		this.threeShot = threeShot;
	}

	public double getThree() {
		return three;
	}

	public void setThree(double three) {
		this.three = three;
	}

	public int getTwoShot() {
		return twoShot;
	}

	public void setTwoShot(int twoShot) {
		this.twoShot = twoShot;
	}

	public int getTwoHit() {
		return twoHit;
	}

	public void setTwoHit(int twoHit) {
		this.twoHit = twoHit;
	}

	public double getTwo() {
		return two;
	}

	public void setTwo(double two) {
		this.two = two;
	}

	public int getFreeHit() {
		return freeHit;
	}

	public void setFreeHit(int freeHit) {
		this.freeHit = freeHit;
	}

	public int getFreeShot() {
		return freeShot;
	}

	public void setFreeShot(int freeShot) {
		this.freeShot = freeShot;
	}

	public double getFree() {
		return free;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public int getDfdRebound() {
		return dfdRebound;
	}

	public void setDfdRebound(int dfdRebound) {
		this.dfdRebound = dfdRebound;
	}

	public int getOfdRebound() {
		return ofdRebound;
	}

	public void setOfdRebound(int ofdRebound) {
		this.ofdRebound = ofdRebound;
	}

	public int getTotRebound() {
		return totRebound;
	}

	public void setTotRebound(int totRebound) {
		this.totRebound = totRebound;
	}

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public int getSteal() {
		return steal;
	}

	public void setSteal(int steal) {
		this.steal = steal;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getFault() {
		return fault;
	}

	public void setFault(int fault) {
		this.fault = fault;
	}

	public int getFoul() {
		return foul;
	}

	public void setFoul(int foul) {
		this.foul = foul;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public double getPlayerEFF() {
		return playerEFF;
	}

	public void setPlayerEFF(double playerEFF) {
		this.playerEFF = playerEFF;
	}

	public double getRealShot() {
		return realShot;
	}

	public void setRealShot(double realShot) {
		this.realShot = realShot;
	}

	public double getShotEFF() {
		return shotEFF;
	}

	public void setShotEFF(double shotEFF) {
		this.shotEFF = shotEFF;
	}

	public double getThreeEFF() {
		return threeEFF;
	}

	public void setThreeEFF(double threeEFF) {
		this.threeEFF = threeEFF;
	}

	public double getFreeEFF() {
		return freeEFF;
	}

	public void setFreeEFF(double freeEFF) {
		this.freeEFF = freeEFF;
	}

	public double getOffReboundEFF() {
		return offReboundEFF;
	}

	public void setOffReboundEFF(double offReboundEFF) {
		this.offReboundEFF = offReboundEFF;
	}

	public double getDefReboundEFF() {
		return defReboundEFF;
	}

	public void setDefReboundEFF(double defReboundEFF) {
		this.defReboundEFF = defReboundEFF;
	}

	public double getTotReboundEFF() {
		return totReboundEFF;
	}

	public void setTotReboundEFF(double totReboundEFF) {
		this.totReboundEFF = totReboundEFF;
	}

	public double getAssistEFF() {
		return assistEFF;
	}

	public void setAssistEFF(double assistEFF) {
		this.assistEFF = assistEFF;
	}

	public double getStealEFF() {
		return stealEFF;
	}

	public void setStealEFF(double stealEFF) {
		this.stealEFF = stealEFF;
	}

	public double getBlockEFF() {
		return blockEFF;
	}

	public void setBlockEFF(double blockEFF) {
		this.blockEFF = blockEFF;
	}

	public double getFaultEFF() {
		return faultEFF;
	}

	public void setFaultEFF(double faultEFF) {
		this.faultEFF = faultEFF;
	}

	public double getUseEFF() {
		return useEFF;
	}

	public void setUseEFF(double useEFF) {
		this.useEFF = useEFF;
	}

	public double getOffWinShare() {
		return offWinShare;
	}

	public void setOffWinShare(double offWinShare) {
		this.offWinShare = offWinShare;
	}

	public double getDefWinShare() {
		return defWinShare;
	}

	public void setDefWinShare(double defWinShare) {
		this.defWinShare = defWinShare;
	}

	public double getWinShare() {
		return winShare;
	}

	public void setWinShare(double winShare) {
		this.winShare = winShare;
	}

	public double getOffBoxPM() {
		return offBoxPM;
	}

	public void setOffBoxPM(double offBoxPM) {
		this.offBoxPM = offBoxPM;
	}

	public double getDefBoxPM() {
		return defBoxPM;
	}

	public void setDefBoxPM(double defBoxPM) {
		this.defBoxPM = defBoxPM;
	}

	public double getBoxPM() {
		return BoxPM;
	}

	public void setBoxPM(double boxPM) {
		BoxPM = boxPM;
	}

	public double getReplaceValue() {
		return replaceValue;
	}

	public void setReplaceValue(double replaceValue) {
		this.replaceValue = replaceValue;
	}
}
