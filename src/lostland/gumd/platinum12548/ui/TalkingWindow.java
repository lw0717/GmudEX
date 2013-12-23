/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：TalkingWindow.java <p>
 * 创建时间：2013-8-24 上午10:13:06 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import java.util.ArrayList;

import android.util.Log;

import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.ui.core.GmudWindow;

/**
 * 类名：TalkingWindow <p>
 * 说明：对话窗口
 * @author 12548
 */
public class TalkingWindow extends GmudWindow {
	
	String s;
	
	boolean splited;
	boolean b=false;
	
	ArrayList<String> pages;
	BLGGraphics g = (BLGGraphics) game.getGraphics();
	public int page = 0;
	
	/**
	 * @return 分页数
	 */
	public int getPages() {
		return pages.size();
	}

	public TalkingWindow(GmudGame game, String s  ,boolean splited) {
		super(game, 0, 0, GameConstants.FBWIDTH, 27);
		Log.e("talking", "Spliting:"+s);
		this.splited = splited;
		if(splited)
			pages = g.splitString(s, 2);
		else
			pages = g.splitString(s, FontSize.FT_12PX, 174, 2);
	}

	public TalkingWindow(GmudGame game, ArrayList<String> pages,boolean splited) {
		super(game, 0, 0, GameConstants.FBWIDTH, 27);
		this.pages = pages;
		b = true;
	}
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.GmudWindow#draw()
	 */
	@Override
	public void draw() {
		this.drawBackground();

		if(!b)
			g.drawSplitedText(pages.get(page), 4, 2, FontSize.FT_12PX);
		else
			g.drawText(pages.get(page), 6, 2, FontSize.FT_12PX, this.width - 10);
	}

}
