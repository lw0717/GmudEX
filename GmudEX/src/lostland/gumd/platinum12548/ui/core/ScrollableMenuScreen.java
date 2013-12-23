/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：ScrollableMenuScreen.java <p>
 * 创建时间：2013-12-22 下午5:53:02 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import android.util.Log;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.ui.AutoButton;

/**
 * 类名：ScrollableMenuScreen <p>
 * 说明：
 * @author 12548
 */
public abstract class ScrollableMenuScreen extends ButtonControlledScreen {

	
	protected int max;
	protected int x,y;
	
	protected SMBorder nborder;
	public AutoButton buttons[];
	
	boolean showmax;
	
	public int cursor = 0;
	
	protected int hcursor = 0;
	
	public abstract void drawbg();
	public abstract void onCancel();
	public abstract void onClick(int i);
	
	/**
	 * @param game
	 */
	public ScrollableMenuScreen(IGame game,String[] s,int x, int y,int width,int max,boolean showmax) {
		super(game);
		Log.w("SMS", "C0");
		this.x = x;
		this.y = y;
		Log.w("SMS", "C1");
		this.max = showmax?max:Math.min(max,s.length);
		this.nborder = new SMBorder((GmudGame) game,x,y,width,this.max*12+2);
		this.buttons = new AutoButton[s.length];
		Log.w("SMS", "C2");
		for(int i = 0; i < buttons.length;i++)
		{
			this.buttons[i] = new AutoButton((GmudGame) game, x+1, y + 1 + 12 * i, s[i]);
		}
		
		Log.w("SMS", "CO");
		this.showmax = showmax;
		
	}

	protected void re(String[] s,int x, int y,int width,int max) {
		Log.w("SMS", "RE0");
		this.x = x;
		this.y = y;
		
		this.max = showmax?max:Math.min(max,s.length);
		this.max = Math.min(max,s.length);
		this.nborder = new SMBorder((GmudGame) game,x,y,width,this.max*12+2);
		this.buttons = new AutoButton[s.length];
		
		for(int i = 0; i < buttons.length;i++)
		{
			this.buttons[i] = new AutoButton((GmudGame) game, x+1,y + 1 + 12 * i, s[i]);
		}
		refresh();
		Log.w("SMS", "REO");
	}
	
	
	public void refresh()
	{
		if(cursor >= buttons.length)
			cursor = 0;
		if(cursor<0)
			cursor = buttons.length-1;
		
		while(cursor >= hcursor + max)hcursor++;
		while(cursor < hcursor)hcursor--;
		
		for(int i = hcursor; i < Math.min(hcursor + max, buttons.length);i++)
		{
			buttons[i].y = nborder.y + 1 + 12 * (i-hcursor);
			
			if(cursor == i)
				buttons[i].setBordered(true);
			else
				buttons[i].setBordered(false);
		}
	}
	
	
	public class SMBorder extends GmudWindow {
		
		public SMBorder(GmudGame game, int x, int y, int width, int height) {
			super(game, x, y, width, height);
			this.bordered = true;
		}

		/* （非 Javadoc）
		 * @see lostland.gumd.platinum12548.ui.core.GmudWindow#draw()
		 */
		@Override
		public void draw() {
			drawBackground();
		}
		
	}
	

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#onButtonClick(lostland.gumd.platinum12548.ui.core.NewButton)
	 */
	@Override
	public void onButtonClick(NewButton b) {
		switch(b)
		{
		case NB_BACK:
			onCancel();
			break;
		case NB_ENTER:
			onClick(cursor);
			break;
		case NB_NULL:
			break;
		case NB_RIGHT:
		case NB_DOWN:
			cursor++;
			if(cursor >= buttons.length)
				cursor = 0;
			refresh();
			break;
		case NB_UP:
		case NB_LEFT:
			cursor--;
			if(cursor<0)
				cursor = buttons.length-1;
			refresh();
			break;
		default:
			break;
		
		}
	}

	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#show()
	 */
	@Override
	public void show() {
		drawbg();
		nborder.draw();
		for(int i = hcursor; i < Math.min(hcursor + max, buttons.length);i++)
		{
			buttons[i].draw();
		}
	}

}
