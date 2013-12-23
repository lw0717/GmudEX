/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：ExplorerMenuScreen.java <p>
 * 创建时间：2013-8-21 上午10:23:44 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import android.graphics.Color;
import android.graphics.Rect;
import lostland.gumd.platinum12548.FontSize;
import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.MainCharTile;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.ui.core.DialogScreen;

/**
 * 类名：ExplorerMenuScreen <p>
 * 说明：
 * @author 12548
 */
public class ExplorerMenuScreen extends DialogScreen {

	public String HighLightBtnNames[] = null;//
	public String ListViewBtnName[] = null;//
	
	public int selectedButtonIndex = -1;
	public int selectedSubitemIndexes[] = null;
	
	protected Rect boundRect;
	
	protected int hilightBtnDrawX, hilightBtnDrawY;
	protected int subitemDrawX, subitemDrawY;
	
	protected int itemstartY, subitemstartY;
	protected int topitemindex = 0, topsubitemindex = 0;
	
	protected int maxLines = 1;
	
	// ------------- Animation stuff -----------------------------
	public double v_y_hilightBtn = 0;// 高亮按钮的y方向速度
	public double v_y_subitem = 0;// 清单的y方向速度
	protected double a_y = 0.05;//y方向上所有物体的"加速度"(负值，为了减速)
	//public final int updateInterval = 2;// 1/0.2
	//protected int updateCntr = 0;
	
	/**
	 * ctor
	 * @param game 游戏对象
	 * @param x 菜单的左上角
	 * @param y 菜单的右下角
	 * @param maxLines 最大文字行数
	 */
	public ExplorerMenuScreen(IGame game, int x, int y, int width,int maxLines) {
		super(game);
		// TODO 自动生成的构造函数存根
		hilightBtnDrawX = x + 2;
		subitemDrawX = x + 26 + 4;// OR 3
		hilightBtnDrawY = y + 2;
		subitemDrawY = y + 2;
		this.maxLines = maxLines;
		
		boundRect = new Rect(x, y, x + width,y + 5 * 12);
		
		selectedButtonIndex = 0;
		
		HighLightBtnNames = new String[]
				{
					"这1", "这2", "这3", "这4", "这5", "这6", "这7", "这8", "这9", "这10",
					"这11", "这12", "这13", "这14", "这15", "这16", "这17", "这18", "这19",
					"这20", "这21", "这22", "这23", "这24", "这25", "这26", "这27", "这28",
					"这29", "这30", "这31", "这32", "这33", "这34", "这35", "这36", "这37",
					"这38"
				};
		ListViewBtnName = new String[]{"测试输出", "一次次地"};
		v_y_hilightBtn = -5;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchDown(int, int)
	 */
	@Override
	protected void onTouchDown(int tx, int ty) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchMove(int, int)
	 */
	@Override
	protected void onTouchMove(int tx, int ty) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchUp(int, int)
	 */
	@Override
	protected void onTouchUp(int tx, int ty) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onClick(int, int)
	 */
	@Override
	protected void onClick(int tx, int ty) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		// TODO 自动生成的方法存
		
		// 减速度
		double valuetoadd = v_y_hilightBtn < 0?Math.abs(a_y) : (-1 * Math.abs(a_y));
		
		// 速度减为0 或者 即将越界 - 暂时只有判断 高亮按钮的,清单的还没写...
		if(v_y_hilightBtn * (v_y_hilightBtn + valuetoadd) <= 0
				|| hilightBtnDrawY + v_y_hilightBtn < boundRect.bottom - HighLightBtnNames.length * 12
				|| hilightBtnDrawY + v_y_hilightBtn > boundRect.top + 2)
		{
			if(hilightBtnDrawY + v_y_hilightBtn < boundRect.bottom - HighLightBtnNames.length * 12)
			{
				hilightBtnDrawY = boundRect.bottom - HighLightBtnNames.length * 12;
			}
			else if(hilightBtnDrawY + v_y_hilightBtn > boundRect.top + 2)
			{
				hilightBtnDrawY = boundRect.top + 2;
			}
			
			if(hilightBtnDrawY > boundRect.bottom - HighLightBtnNames.length * 12)
				v_y_hilightBtn = -5;
			else
				v_y_hilightBtn = 5;
		}
		else
		{
			hilightBtnDrawY += v_y_hilightBtn;
			v_y_hilightBtn += valuetoadd;
		}
		
		/*if(v_y_item > 0)
		{
			itemdrawY += v_y_item;
			v_y_item -= a_y;
		}*/
		
		BLGGraphics g = (BLGGraphics)game.getGraphics();
		g.SetClipRect(boundRect);
		
		GmudWorld.mapTile.drawMap(GmudWorld.ms.map, GmudWorld.ms.X, GmudWorld.ms.Y);
		GmudWorld.cnm.draw(MainCharTile.currentDirection, GmudWorld.cnm.currentStep, MainCharTile.X, MainCharTile.Y);
		
		DrawBackground(g);
		DrawOutline(g);
		DrawHilgtBtns(g);
		DrawListView(g);
		
		g.ResetClipRect();
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
	
	//-------------------- internal stuff -------------------------------
	protected void DrawBackground(BLGGraphics g)
	{
		g.drawRect(boundRect.left, boundRect.top,
				boundRect.width(), boundRect.height(),GameConstants.BG_COLOR);
	}
	
	protected void DrawOutline(BLGGraphics g)
	{
		g.drawRectOutline(boundRect.left, boundRect.top,
				boundRect.width(), boundRect.height(),Color.BLACK);
		g.drawLine(boundRect.left + 26, boundRect.top, boundRect.left + 26,
				boundRect.bottom, Color.BLACK);
	}
	
	// 绘制左边的高亮按钮
	protected void DrawHilgtBtns(BLGGraphics g)
	{
		if(HighLightBtnNames == null)
			return;
		
		int _currentY = hilightBtnDrawY;
		int _font_color = Color.BLACK;
		int _line_cnt = 0;
		for (int i = 0; i < HighLightBtnNames.length; i++)
		{
			if(i < topitemindex)
				continue;
			
			if(++_line_cnt > maxLines)
				break;
			
			_font_color = Color.BLACK;
			
			if(selectedButtonIndex == i)
			{
				g.drawRect(hilightBtnDrawX, _currentY,
					HighLightBtnNames[i].length() * 12, 12, _font_color);
				_font_color = GameConstants.BG_COLOR;
			}
			
			g.drawSplitedText(HighLightBtnNames[i], hilightBtnDrawX,
					_currentY, FontSize.FT_12PX, _font_color);
			
			_currentY += 12;
		}
	}
	
	// 绘制右边的清单
	protected void DrawListView(BLGGraphics g)
	{
		if(ListViewBtnName == null)
			return;
		
		int _currentY = subitemDrawY;
		int _line_cnt = 0;
		
		for (int i = 0; i < ListViewBtnName.length; i++)
		{
			if(i < topsubitemindex)
				continue;
			
			if(++_line_cnt > maxLines)
				break;
			
			g.drawRectOutline(subitemDrawX + 5, _currentY + 3, 5, 5, Color.BLACK);
			
			if(contains(ListViewBtnName, i))
			{
				// 尚未实现！！！！！
				//用DrawPixel画个对勾
			}
			
			g.drawSplitedText(ListViewBtnName[i], subitemDrawX + 15, _currentY, FontSize.FT_12PX);
			
			_currentY += 12;
		}
	}
	
	public static<T> boolean contains( final T[] array, final T val)
	{
		if(array == null)
			return false;
		for( final T e : array)
			if( e == val || e != null && e.equals(val))
				return true;
		return false;
	}
}
