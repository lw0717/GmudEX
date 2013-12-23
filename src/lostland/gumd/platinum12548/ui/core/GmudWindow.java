/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：GmudWindow.java <p>
 * 创建时间：2013-6-30 下午7:52:01 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.blgframework.IGraphics;
import android.graphics.Rect;

/**
 * 类名：GmudWindow <p>
 * 说明：在屏幕显示的窗口类的基类。
 * @author 12548
 */
public abstract class GmudWindow {

	protected Rect rect;
	public int x;
	public int y;
	protected int width;
	public int height;
	protected GmudGame game;
	
	protected boolean bordered = true;
	

	/**
	 * @return bordered
	 */
	public boolean isBordered() {
		return bordered;
	}


	/**
	 * @param bordered 要设置的 bordered
	 */
	public void setBordered(boolean bordered) {
		this.bordered = bordered;
	}


	/**
	 * @param game 游戏对象
	 * @param x 左上角顶点x坐标
	 * @param y 左上角顶点y坐标
	 * @param width 宽度
	 * @param height 高度
	 */
	public GmudWindow(GmudGame game,int x,int y,int width,int height)
	{
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		this.rect= new Rect(x,y,x+width,y+height);
	}
	
	
	public boolean inBound(int x,int y)
	{
		return rect.contains(x, y);
	}
	
	protected void drawBackground()
	{
		IGraphics g = game.getGraphics();
		if(this.bordered)
		{
			g.drawRect(x, y, width, height, 0);
			g.drawRect(x+1, y+1, width-2, height-2, GameConstants.BG_COLOR);
		}
		else
		{
			g.drawRect(x, y, width, height, GameConstants.BG_COLOR);
		}
	}
	
	public void resize()
	{
		this.rect= new Rect(x,y,x+width,y+height);
	}
	
	abstract public void draw();
	
}
