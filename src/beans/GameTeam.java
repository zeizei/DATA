package beans;

public class GameTeam {
	// 主键
	private String date;// 比赛日期
	private String teamName;// 球队名称
	// 球队普通比赛数据
	private int isHome;// 是否主场
	private int[] quarterPoint;// 每节比赛得分（包括季后赛）
	private double minute;// 比赛总时间
	private int totHit;// 总命中
	private int totShot;// 总出手
	private double shot;// 命中率
	private int threeHit;// 三分命中数
	private int threeShot;// 三分出手数
	private double three;// 三分命中率
	private int freeHit;// 罚球命中数
	private int freeShot;// 罚球出手数
	private double free;// 罚球命中率
	private int offRebound;// 进攻篮板
	private int defRebound;// 防守篮板
	private int totRebound;// 总篮板
	private int assist;// 助攻
	private int steal;// 抢断
	private int block;// 盖帽
	private int fault;// 失误
	private int foul;// 犯规
	private int point;// 得分
	private int plus;// 贡献值

	// 球队高级比赛信息
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
	private double offEFF;// 进攻效率
	private double defEFF;// 防守效率

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getIsHome() {
		return isHome;
	}

	public void setIsHome(int isHome) {
		this.isHome = isHome;
	}

	public double getMinute() {
		return minute;
	}

	public void setMinute(double minute) {
		this.minute = minute;
	}

	public int getTotHit() {
		return totHit;
	}

	public void setTotHit(int totHit) {
		this.totHit = totHit;
	}

	public int getTotShot() {
		return totShot;
	}

	public void setTotShot(int totShot) {
		this.totShot = totShot;
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

	public int getOffRebound() {
		return offRebound;
	}

	public void setOffRebound(int offRebound) {
		this.offRebound = offRebound;
	}

	public int getDefRebound() {
		return defRebound;
	}

	public void setDefRebound(int defRebound) {
		this.defRebound = defRebound;
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

	public int getPlus() {
		return plus;
	}

	public void setPlus(int plus) {
		this.plus = plus;
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

	public double getOffEFF() {
		return offEFF;
	}

	public void setOffEFF(double offEFF) {
		this.offEFF = offEFF;
	}

	public double getDefEFF() {
		return defEFF;
	}

	public void setDefEFF(double defEFF) {
		this.defEFF = defEFF;
	}

	public int[] getQuarterPoint() {
		return quarterPoint;
	}

	public void setQuarterPoint(int[] quarterPoint) {
		this.quarterPoint = quarterPoint;
	}
}
