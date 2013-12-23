/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：DummyStatus.java <p>
 * 创建时间：2013-8-8 下午6:31:54 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.battle.proc;

import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.battle.ViewScreen;

/**
 * 类名：DummyStatus <p>
 * 说明：为了解决一个莫名其妙的bug而莫名其妙地设置的类………………
 * @author 12548
 */
public class DummyStatus implements Status {

	boolean b = true;
	
	/**
	 * 
	 */
	public DummyStatus() {
		// TODO 自动生成的构造函数存根
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
		GmudWorld.bs.setStatus(AttackStatus.ts);
		return false;
	}

}
