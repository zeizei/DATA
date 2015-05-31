package beans;

public class SeasonTeam {
	// 主键
	private String teamName;
	private String season;
	// 球队普通赛季数据
	private int numOfGame;// 比赛场数
	private double minute;// 总时间
	//
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
	// 对手普通比赛数据
	private int oppTotalHit;// 总命中数
	private int oppTotalShot;// 总出手数
	private double oppShot;// 总命中率
	private int oppThreeHit;// 三分命中数
	private int oppThreeShot;// 三分出手数
	private double oppThree;// 三分命中率
	private int oppTwoShot;// 两分出手数
	private int oppTwoHit;// 两分命中数
	private double oppTwo;// 两分命中率
	private int oppFreeHit;// 罚球命中数
	private int oppFreeShot;// 罚球出手数
	private double oppFree;// 罚球命中率
	private int oppDfdRebound;// 后场篮板
	private int oppOfdRebound;// 前场篮板
	private int oppTotRebound;// 总篮板
	private int oppAssist;// 助攻数
	private int oppSteal;// 抢断数
	private int oppBlock;// 盖帽数
	private int oppFault;// 失误
	private int oppFoul;// 犯规
	private int oppPoint;// 得分
	// 球队高级赛季数据
	private int numOfWin;// 赢场数
	private int numOfLose;// 输场数
	private double winRate;// 胜率
	private double pace;// 回合数
	private double offEFF;// 进攻效率
	private double defEFF;// 防守效率
	private double offReboundEFF;// 进攻篮板效率
	private double defReboundEFF;// 防守篮板效率
	private double shotEFF;// 投篮效率
	private double threeEFF;// 三分效率
	private double freeEFF;// 罚球效率

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

	public int getNumOfGame() {
		return numOfGame;
	}

	public void setNumOfGame(int numOfGame) {
		this.numOfGame = numOfGame;
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

	public int getOppTotalHit() {
		return oppTotalHit;
	}

	public void setOppTotalHit(int oppTotalHit) {
		this.oppTotalHit = oppTotalHit;
	}

	public int getOppTotalShot() {
		return oppTotalShot;
	}

	public void setOppTotalShot(int oppTotalShot) {
		this.oppTotalShot = oppTotalShot;
	}

	public double getOppShot() {
		return oppShot;
	}

	public void setOppShot(double oppShot) {
		this.oppShot = oppShot;
	}

	public int getOppThreeHit() {
		return oppThreeHit;
	}

	public void setOppThreeHit(int oppThreeHit) {
		this.oppThreeHit = oppThreeHit;
	}

	public int getOppThreeShot() {
		return oppThreeShot;
	}

	public void setOppThreeShot(int oppThreeShot) {
		this.oppThreeShot = oppThreeShot;
	}

	public double getOppThree() {
		return oppThree;
	}

	public void setOppThree(double oppThree) {
		this.oppThree = oppThree;
	}

	public int getOppTwoShot() {
		return oppTwoShot;
	}

	public void setOppTwoShot(int oppTwoShot) {
		this.oppTwoShot = oppTwoShot;
	}

	public int getOppTwoHit() {
		return oppTwoHit;
	}

	public void setOppTwoHit(int oppTwoHit) {
		this.oppTwoHit = oppTwoHit;
	}

	public double getOppTwo() {
		return oppTwo;
	}

	public void setOppTwo(double oppTwo) {
		this.oppTwo = oppTwo;
	}

	public int getOppFreeHit() {
		return oppFreeHit;
	}

	public void setOppFreeHit(int oppFreeHit) {
		this.oppFreeHit = oppFreeHit;
	}

	public int getOppFreeShot() {
		return oppFreeShot;
	}

	public void setOppFreeShot(int oppFreeShot) {
		this.oppFreeShot = oppFreeShot;
	}

	public double getOppFree() {
		return oppFree;
	}

	public void setOppFree(double oppFree) {
		this.oppFree = oppFree;
	}

	public int getOppDfdRebound() {
		return oppDfdRebound;
	}

	public void setOppDfdRebound(int oppDfdRebound) {
		this.oppDfdRebound = oppDfdRebound;
	}

	public int getOppOfdRebound() {
		return oppOfdRebound;
	}

	public void setOppOfdRebound(int oppOfdRebound) {
		this.oppOfdRebound = oppOfdRebound;
	}

	public int getOppTotRebound() {
		return oppTotRebound;
	}

	public void setOppTotRebound(int oppTotRebound) {
		this.oppTotRebound = oppTotRebound;
	}

	public int getOppAssist() {
		return oppAssist;
	}

	public void setOppAssist(int oppAssist) {
		this.oppAssist = oppAssist;
	}

	public int getOppSteal() {
		return oppSteal;
	}

	public void setOppSteal(int oppSteal) {
		this.oppSteal = oppSteal;
	}

	public int getOppBlock() {
		return oppBlock;
	}

	public void setOppBlock(int oppBlock) {
		this.oppBlock = oppBlock;
	}

	public int getOppFault() {
		return oppFault;
	}

	public void setOppFault(int oppFault) {
		this.oppFault = oppFault;
	}

	public int getOppFoul() {
		return oppFoul;
	}

	public void setOppFoul(int oppFoul) {
		this.oppFoul = oppFoul;
	}

	public int getOppPoint() {
		return oppPoint;
	}

	public void setOppPoint(int oppPoint) {
		this.oppPoint = oppPoint;
	}

	public int getNumOfWin() {
		return numOfWin;
	}

	public void setNumOfWin(int numOfWin) {
		this.numOfWin = numOfWin;
	}

	public int getNumOfLose() {
		return numOfLose;
	}

	public void setNumOfLose(int numOfLose) {
		this.numOfLose = numOfLose;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public double getPace() {
		return pace;
	}

	public void setPace(double pace) {
		this.pace = pace;
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
}
