/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：AnotherDummyStatus.java <p>
 * 创建时间：2013-8-23 下午4:44:00 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;

/**
 * 类名：AnotherDummyStatus <p>
 * 说明：为了解决bug，一个DummyStatus已经不够用了……
 * @author 12548
 */
public class AnotherDummyStatus implements Status {

	Status ts;
	boolean b=true;
	/**
	 * 
	 */
	public AnotherDummyStatus(Status s) {
		ts = s;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.battle.proc.Status#execute()
	 */
	@Override
	public boolean execute() {
		if(b)
		{
			b=false;
			GmudWorld.game.setScreen(new ViewScreen(GmudWorld.game));
		}else
		GmudWorld.bs.setStatus(ts);
		return false;
	}

}
