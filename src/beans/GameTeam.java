package beans;

public class GameTeam extends Bean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	protected String teamName;// 球队名称
	protected String date;// 比赛日期
	// 球队普通比赛数据
	private String quarterPoint;// 每节比赛得分（包括加时赛）
	private double minute;// 比赛总时间
	private double totHit;// 总命中
	private double totShot;// 总出手
	private double shot;// 命中率
	private double threeHit;// 三分命中数
	private double threeShot;// 三分出手数
	private double three;// 三分命中率
	private double freeHit;// 罚球命中数
	private double freeShot;// 罚球出手数
	private double free;// 罚球命中率
	private double offRebound;// 进攻篮板
	private double defRebound;// 防守篮板
	private double totRebound;// 总篮板
	private double assist;// 助攻
	private double steal;// 抢断
	private double block;// 盖帽
	private double fault;// 失误
	private double foul;// 犯规
	private double point;// 得分

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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getQuarterPoint() {
		return quarterPoint;
	}

	public void setQuarterPoint(String quarterPoint) {
		this.quarterPoint = quarterPoint;
	}

	public double getMinute() {
		return minute;
	}

	public void setMinute(double minute) {
		this.minute = minute;
	}

	public double getTotHit() {
		return totHit;
	}

	public void setTotHit(double totHit) {
		this.totHit = totHit;
	}

	public double getTotShot() {
		return totShot;
	}

	public void setTotShot(double totShot) {
		this.totShot = totShot;
	}

	public double getShot() {
		return shot;
	}

	public void setShot(double shot) {
		this.shot = shot;
	}

	public double getThreeHit() {
		return threeHit;
	}

	public void setThreeHit(double threeHit) {
		this.threeHit = threeHit;
	}

	public double getThreeShot() {
		return threeShot;
	}

	public void setThreeShot(double threeShot) {
		this.threeShot = threeShot;
	}

	public double getThree() {
		return three;
	}

	public void setThree(double three) {
		this.three = three;
	}

	public double getFreeHit() {
		return freeHit;
	}

	public void setFreeHit(double freeHit) {
		this.freeHit = freeHit;
	}

	public double getFreeShot() {
		return freeShot;
	}

	public void setFreeShot(double freeShot) {
		this.freeShot = freeShot;
	}

	public double getFree() {
		return free;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public double getOffRebound() {
		return offRebound;
	}

	public void setOffRebound(double offRebound) {
		this.offRebound = offRebound;
	}

	public double getDefRebound() {
		return defRebound;
	}

	public void setDefRebound(double defRebound) {
		this.defRebound = defRebound;
	}

	public double getTotRebound() {
		return totRebound;
	}

	public void setTotRebound(double totRebound) {
		this.totRebound = totRebound;
	}

	public double getAssist() {
		return assist;
	}

	public void setAssist(double assist) {
		this.assist = assist;
	}

	public double getSteal() {
		return steal;
	}

	public void setSteal(double steal) {
		this.steal = steal;
	}

	public double getBlock() {
		return block;
	}

	public void setBlock(double block) {
		this.block = block;
	}

	public double getFault() {
		return fault;
	}

	public void setFault(double fault) {
		this.fault = fault;
	}

	public double getFoul() {
		return foul;
	}

	public void setFoul(double foul) {
		this.foul = foul;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
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
}
