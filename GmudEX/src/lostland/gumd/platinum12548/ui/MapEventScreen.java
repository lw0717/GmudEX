/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：MapEventScreen.java <p>
 * 创建时间：2013-12-22 下午2:08:47 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.GameEvent;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;

/**
 * 类名：MapEventScreen <p>
 * 说明：
 * @author 12548
 */
public class MapEventScreen extends CScreen {

	boolean b = true;
	
	int eid;
	
	/**
	 * @param game
	 */
	public MapEventScreen(IGame game,int eid) {
		super(game);
		this.eid = eid;
		b=true;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		GmudWorld.ms.present(deltaTime);
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#pause()
	 */
	@Override
	public void pause() {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#resume()
	 */
	@Override
	public void resume() {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#dispose()
	 */
	@Override
	public void dispose() {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.BasicScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		if(b)
		{
			GameEvent.callEvent(eid);
//			b = false;
		}
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#isStable()
	 */
	@Override
	public boolean isStable() {
		return false;
	}

	
	
}
