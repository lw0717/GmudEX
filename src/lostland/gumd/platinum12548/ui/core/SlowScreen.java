/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：SlowScreen.java <p>
 * 创建时间：2013-8-30 上午12:18:27 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;

/**
 * 类名：SlowScreen <p>
 * 说明：随时间变化
 * @author 12548
 */
public abstract class SlowScreen extends FixedScreen {

	
	/**
	 * @param game
	 * @param bg
	 */
	public SlowScreen(IGame game, CScreen bg, float ticktime) {
		super(game,bg);
		this.tickTime = ticktime;
	}

	public float tickTime;
	
	public float time = 0.0f;

	public abstract void tick();

	public abstract void draw();

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		time += deltaTime;
		if(time >= tickTime)
		{
			time -= tickTime;
			tick();
		}
		
	}


	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onTouchDown(int, int)
	 */
	@Override
	protected void onTouchDown(int tx, int ty) {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onTouchMove(int, int)
	 */
	@Override
	protected void onTouchMove(int tx, int ty) {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onTouchUp(int, int)
	 */
	@Override
	protected void onTouchUp(int tx, int ty) {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#onClick(int, int)
	 */
	@Override
	protected void onClick(int tx, int ty) {
		// TODO 自动生成的方法存根
		
	}


}
