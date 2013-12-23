/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：StartScreen.java <p>
 * 创建时间：2013-9-5 下午8:18:55 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.SavingScreen;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.IInput;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;

/**
 * 类名：StartScreen <p>
 * 说明：
 * @author 12548
 */
public class FinishScreen extends CScreen {

	public static boolean flag = false;

	String s = "";

	int x =172;

	public FinishScreen(IGame game) {
		super(game);

		switch(GmudWorld.bs.enemyid)
		{
		case 124:
			s="我是谁：不要以为你真的打败了我，这只是剧情的安排。其实还有两个更精采的结……" +
					"                        画外音：快下场！你已经说的够多了。                " +
					"我走到六芒星阵中央，按下了时空转换的按钮。     " +
					"   。。。。。。                    _END_";
			break;
		case 125:
			s="东方求败：为了所谓的正义，可以不择手段吗？我只是做了自己想做的事，我错了吗？" +
					"倒是你，一直生活在正义的阴影下，失去了自我，那才是可悲呢。" +
					"                住口！你这样的人也配谈正义吗？" +
					"                    东方求败：想杀人灭口吗？可惜我还不想死。再见了。" +
					"  不知什么时候东方求败打开了时空转换装置。" +
					"看着他渐渐变小的背影，我毫不犹豫地冲了上去。" +
					"                 。。。。。。                    _END_";
			break;
		case 126:
			s = "道德和尚：你赢了。但是请施主记住，正义终将战胜邪恶。只可惜你却看不到那一天了。" +
					"我慢慢走近，一刀劈了下去。" +
					"就象砍进棉花里，没有任何声音，道德和尚在一瞬间消失了。难道说。。。              " +
					"轰－！                        我惊异地发现，这一刀斩在了时空转换装置上。          " +
					"周围的景物在迅速的变更，感觉如同掉进了无尽的深渊。     。。。。。。                     _END_";
			break;
		}

	}

	boolean b=false;

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		x-=2;
		if(flag){
			new SavingScreen(GmudWorld.game).save();
			GmudWorld.ms.X = 2;
			GmudWorld.ms.Y = 3;
			GmudWorld.game.setScreen(GmudWorld.ms);
		}
		game.getInput().getKeyEvents();
		game.getInput().getTouchEvents();
		IInput i= game.getInput();
		if(i.isTouchDown(0))
			SingleTouchHandler.flag = 9;

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		BLGGraphics g=(BLGGraphics) game.getGraphics();
		g.clear(GameConstants.BG_COLOR);

		g.drawText(s, 0, x/10, FontSize.FT_16PX, 320);

		g.drawRect(0, 0, 320, 16, GameConstants.BG_COLOR);
		g.drawText("==================结局==================", 0, 0, FontSize.FT_16PX,999);
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

}
