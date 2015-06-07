package beans;

public class SeasonTeam extends Bean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	protected String teamName;// 球队名称
	protected String season;// 赛季
	// 球队普通赛季数据
	private double numOfGame;// 比赛场数
	private double minute;// 总时间
	//
	private double totalHit;// 总命中数
	private double totalShot;// 总出手数
	private double shot;// 总命中率
	private double threeHit;// 三分命中数
	private double threeShot;// 三分出手数
	private double three;// 三分命中率
	private double twoShot;// 两分出手数
	private double twoHit;// 两分命中数
	private double two;// 两分命中率
	private double freeHit;// 罚球命中数
	private double freeShot;// 罚球出手数
	private double free;// 罚球命中率
	private double offRebound;// 前场篮板
	private double defRebound;// 后场篮板
	private double totRebound;// 总篮板
	private double assist;// 助攻数
	private double steal;// 抢断数
	private double block;// 盖帽数
	private double fault;// 失误
	private double foul;// 犯规
	private double point;// 得分
	// 对手普通比赛数据
	private double oppTotalHit;// 总命中数
	private double oppTotalShot;// 总出手数
	private double oppShot;// 总命中率
	private double oppThreeHit;// 三分命中数
	private double oppThreeShot;// 三分出手数
	private double oppThree;// 三分命中率
	private double oppTwoShot;// 两分出手数
	private double oppTwoHit;// 两分命中数
	private double oppTwo;// 两分命中率
	private double oppFreeHit;// 罚球命中数
	private double oppFreeShot;// 罚球出手数
	private double oppFree;// 罚球命中率
	private double oppOfdRebound;// 前场篮板
	private double oppDfdRebound;// 后场篮板
	private double oppTotRebound;// 总篮板
	private double oppAssist;// 助攻数
	private double oppSteal;// 抢断数
	private double oppBlock;// 盖帽数
	private double oppFault;// 失误
	private double oppFoul;// 犯规
	private double oppPoint;// 得分
	// 球队高级赛季数据
	private double avgAge;// 球队平均年龄
	private double numOfWin;// 赢场数
	private double numOfLose;// 输场数
	private double pointOfWin;// 净胜分
	private double strengthOfSchedule;//
	private double simpleRatingSystem;//
	private double offEFF;// 进攻效率
	private double defEFF;// 防守效率
	private double pace;// 回合数
	private double freeEFF;// 罚球效率
	private double threeEFF;// 三分效率
	private double realShot;// 真实命中率
	private double shotEFF;// 投篮效率
	private double faultEFF;// 失误率
	private double offReboundEFF;// 进攻篮板效率
	private double freePerFieldGoal;
	private double oppShotEFF;// 投篮效率
	private double oppFaultEFF;// 失误率
	private double defReboundEFF;// 防守篮板效率
	private double oppFreePerFieldGoal;//
	private String arean;// 球馆
	private double attendance;// 上座人数

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public double getNumOfGame() {
		return numOfGame;
	}

	public void setNumOfGame(double numOfGame) {
		this.numOfGame = numOfGame;
	}

	public double getMinute() {
		return minute;
	}

	public void setMinute(double minute) {
		this.minute = minute;
	}

	public double getTotalHit() {
		return totalHit;
	}

	public void setTotalHit(double totalHit) {
		this.totalHit = totalHit;
	}

	public double getTotalShot() {
		return totalShot;
	}

	public void setTotalShot(double totalShot) {
		this.totalShot = totalShot;
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

	public double getTwoShot() {
		return twoShot;
	}

	public void setTwoShot(double twoShot) {
		this.twoShot = twoShot;
	}

	public double getTwoHit() {
		return twoHit;
	}

	public void setTwoHit(double twoHit) {
		this.twoHit = twoHit;
	}

	public double getTwo() {
		return two;
	}

	public void setTwo(double two) {
		this.two = two;
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

	public double getOppTotalHit() {
		return oppTotalHit;
	}

	public void setOppTotalHit(double oppTotalHit) {
		this.oppTotalHit = oppTotalHit;
	}

	public double getOppTotalShot() {
		return oppTotalShot;
	}

	public void setOppTotalShot(double oppTotalShot) {
		this.oppTotalShot = oppTotalShot;
	}

	public double getOppShot() {
		return oppShot;
	}

	public void setOppShot(double oppShot) {
		this.oppShot = oppShot;
	}

	public double getOppThreeHit() {
		return oppThreeHit;
	}

	public void setOppThreeHit(double oppThreeHit) {
		this.oppThreeHit = oppThreeHit;
	}

	public double getOppThreeShot() {
		return oppThreeShot;
	}

	public void setOppThreeShot(double oppThreeShot) {
		this.oppThreeShot = oppThreeShot;
	}

	public double getOppThree() {
		return oppThree;
	}

	public void setOppThree(double oppThree) {
		this.oppThree = oppThree;
	}

	public double getOppTwoShot() {
		return oppTwoShot;
	}

	public void setOppTwoShot(double oppTwoShot) {
		this.oppTwoShot = oppTwoShot;
	}

	public double getOppTwoHit() {
		return oppTwoHit;
	}

	public void setOppTwoHit(double oppTwoHit) {
		this.oppTwoHit = oppTwoHit;
	}

	public double getOppTwo() {
		return oppTwo;
	}

	public void setOppTwo(double oppTwo) {
		this.oppTwo = oppTwo;
	}

	public double getOppFreeHit() {
		return oppFreeHit;
	}

	public void setOppFreeHit(double oppFreeHit) {
		this.oppFreeHit = oppFreeHit;
	}

	public double getOppFreeShot() {
		return oppFreeShot;
	}

	public void setOppFreeShot(double oppFreeShot) {
		this.oppFreeShot = oppFreeShot;
	}

	public double getOppFree() {
		return oppFree;
	}

	public void setOppFree(double oppFree) {
		this.oppFree = oppFree;
	}

	public double getOppOfdRebound() {
		return oppOfdRebound;
	}

	public void setOppOfdRebound(double oppOfdRebound) {
		this.oppOfdRebound = oppOfdRebound;
	}

	public double getOppDfdRebound() {
		return oppDfdRebound;
	}

	public void setOppDfdRebound(double oppDfdRebound) {
		this.oppDfdRebound = oppDfdRebound;
	}

	public double getOppTotRebound() {
		return oppTotRebound;
	}

	public void setOppTotRebound(double oppTotRebound) {
		this.oppTotRebound = oppTotRebound;
	}

	public double getOppAssist() {
		return oppAssist;
	}

	public void setOppAssist(double oppAssist) {
		this.oppAssist = oppAssist;
	}

	public double getOppSteal() {
		return oppSteal;
	}

	public void setOppSteal(double oppSteal) {
		this.oppSteal = oppSteal;
	}

	public double getOppBlock() {
		return oppBlock;
	}

	public void setOppBlock(double oppBlock) {
		this.oppBlock = oppBlock;
	}

	public double getOppFault() {
		return oppFault;
	}

	public void setOppFault(double oppFault) {
		this.oppFault = oppFault;
	}

	public double getOppFoul() {
		return oppFoul;
	}

	public void setOppFoul(double oppFoul) {
		this.oppFoul = oppFoul;
	}

	public double getOppPoint() {
		return oppPoint;
	}

	public void setOppPoint(double oppPoint) {
		this.oppPoint = oppPoint;
	}

	public double getAvgAge() {
		return avgAge;
	}

	public void setAvgAge(double avgAge) {
		this.avgAge = avgAge;
	}

	public double getNumOfWin() {
		return numOfWin;
	}

	public void setNumOfWin(double numOfWin) {
		this.numOfWin = numOfWin;
	}

	public double getNumOfLose() {
		return numOfLose;
	}

	public void setNumOfLose(double numOfLose) {
		this.numOfLose = numOfLose;
	}

	public double getPointOfWin() {
		return pointOfWin;
	}

	public void setPointOfWin(double pointOfWin) {
		this.pointOfWin = pointOfWin;
	}

	public double getStrengthOfSchedule() {
		return strengthOfSchedule;
	}

	public void setStrengthOfSchedule(double strengthOfSchedule) {
		this.strengthOfSchedule = strengthOfSchedule;
	}

	public double getSimpleRatingSystem() {
		return simpleRatingSystem;
	}

	public void setSimpleRatingSystem(double simpleRatingSystem) {
		this.simpleRatingSystem = simpleRatingSystem;
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

	public double getPace() {
		return pace;
	}

	public void setPace(double pace) {
		this.pace = pace;
	}

	public double getFreeEFF() {
		return freeEFF;
	}

	public void setFreeEFF(double freeEFF) {
		this.freeEFF = freeEFF;
	}

	public double getThreeEFF() {
		return threeEFF;
	}

	public void setThreeEFF(double threeEFF) {
		this.threeEFF = threeEFF;
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

	public double getFaultEFF() {
		return faultEFF;
	}

	public void setFaultEFF(double faultEFF) {
		this.faultEFF = faultEFF;
	}

	public double getOffReboundEFF() {
		return offReboundEFF;
	}

	public void setOffReboundEFF(double offReboundEFF) {
		this.offReboundEFF = offReboundEFF;
	}

	public double getFreePerFieldGoal() {
		return freePerFieldGoal;
	}

	public void setFreePerFieldGoal(double freePerFieldGoal) {
		this.freePerFieldGoal = freePerFieldGoal;
	}

	public double getOppShotEFF() {
		return oppShotEFF;
	}

	public void setOppShotEFF(double oppShotEFF) {
		this.oppShotEFF = oppShotEFF;
	}

	public double getOppFaultEFF() {
		return oppFaultEFF;
	}

	public void setOppFaultEFF(double oppFaultEFF) {
		this.oppFaultEFF = oppFaultEFF;
	}

	public double getDefReboundEFF() {
		return defReboundEFF;
	}

	public void setDefReboundEFF(double defReboundEFF) {
		this.defReboundEFF = defReboundEFF;
	}

	public double getOppFreePerFieldGoal() {
		return oppFreePerFieldGoal;
	}

	public void setOppFreePerFieldGoal(double oppFreePerFieldGoal) {
		this.oppFreePerFieldGoal = oppFreePerFieldGoal;
	}

	public String getArean() {
		return arean;
	}

	public void setArean(String arean) {
		this.arean = arean;
	}

	public double getAttendance() {
		return attendance;
	}

	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}
}
