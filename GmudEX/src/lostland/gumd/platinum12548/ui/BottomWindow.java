/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BottomWindow.java <p>
 * 创建时间：2013-7-26 上午9:59:25 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.ui.core.GmudWindow;

/**
 * 类名：BottomWindow <p>
 * 说明：底部窗口啊哈
 * @author 12548
 */
public class BottomWindow extends GmudWindow {

	
	static final int TOP = GameConstants.FBHEIGHT/2 - 14;
	static final int LEFT = GameConstants.FBWIDTH / 2 - 80;
	static final int WIDTH = 161;
	static final int HEIGHT = 15;
	
	static final int PADDING_TOP = 2;
	static final int PADDING_LEFT = 6;
	
	String text;
	public BottomWindow(GmudGame game, String text) {
		super(game, LEFT, TOP, WIDTH, HEIGHT);
		this.text = text;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.GmudWindow#draw()
	 */
	@Override
	public void draw() {
		drawBackground();
		BLGGraphics g = (BLGGraphics) game.getGraphics();
		g.drawText(text, LEFT + PADDING_LEFT, TOP + PADDING_TOP, FontSize.FT_12PX);
	}

}
