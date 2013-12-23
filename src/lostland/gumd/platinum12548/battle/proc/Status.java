/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：Status.java <p>
 * 创建时间：2013-8-5 下午5:29:28 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc;

/**
 * 类名：Status <p>
 * 说明：战斗者当前状态
 * @author 12548
 */
public interface Status {

	
	/**
	 * 执行当前动作。
	 * @return 当前回合是否结束。
	 */
	public boolean execute();
}
