/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：DoubleScrollScreen.java <p>
 * 创建时间：2013-12-23 下午4:47:53 <p>
 * 所属项目：GmudEX <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.core;

import android.util.Log;
import lostland.gumd.platinum12548.blgframework.IGame;

/**
 * 类名：DoubleScrollScreen <p>
 * 说明：
 * @author 12548
 */
public abstract class DoubleScrollScreen extends ButtonControlledScreen {
	
	public InnerScrollView v1,v2;
	protected int layer = 0;
	
	protected String s1[], s2[][];
	
	protected int x;
	protected int y;
	protected int w1;
	protected  int w2;
	protected  int max;
	
	public abstract void cancel();
	
	
	/**
	 * @param game
	 */
	public DoubleScrollScreen(IGame game,int x,int y,int w1,int w2,int max) {
		super(game);
		this.x = x;
		this.y = y;
		this.w1 = w1;
		this.w2 = w2;
		this.max = max;
		gets();
		v1 = new InnerScrollView(game, s1, x, y, w1, max);
		re();
	}

	public abstract void gets();
	
	public void re()
	{
		gets();

		v2 = new InnerScrollView(game,s2[v1.cursor],x+w1-1,y,w2,max);

		v1.refresh();
		v2.refresh();
		
		if(layer==0)
		{
			for(GmudWindow i : v2.buttons)
			{
				i.setBordered(false);
			}
		}
		
		
	}
	
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#onButtonClick(lostland.gumd.platinum12548.ui.core.NewButton)
	 */
	@Override
	public void onButtonClick(NewButton b) {
		if(b == NewButton.NB_ENTER)
		{
			if(layer == 0)
			{
				if(s2[v1.cursor].length<=0)return;
				layer++;
				re();
			}
			else
			{
				onClick(v2.cursor);
				re();
				if(s2[v1.cursor].length<=0)layer--;
			}
			
		}
		else if(b == NewButton.NB_BACK)
		{
			if(layer==1)
			{
				layer--;
				re();
			}
			else
			{
				cancel();
			}
		}
		else
		{
			if(layer == 0)
				v1.onButtonClick(b);
			else
				v2.onButtonClick(b);
			re();
		}
	}

	public abstract void onClick(int cursor);

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#show()
	 */
	@Override
	protected void show() {
		drawbg();
		v1.show();
		v2.show();
	}

	
	
	public abstract void drawbg();
	
	protected class InnerScrollView extends ScrollableMenuScreen {

		/**
		 * @param game
		 * @param s
		 * @param x
		 * @param y
		 * @param width
		 * @param max
		 */
		public InnerScrollView(IGame game, String[] s, int x, int y, int width,
				int max) {
			super(game, s, x, y, width, max,true);
			// TODO 自动生成的构造函数存根
		}

		/* （非 Javadoc）
		 * @see lostland.gumd.platinum12548.ui.core.ScrollableMenuScreen#drawbg()
		 */
		@Override
		public void drawbg() {
			
		}



		/* （非 Javadoc）
		 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#onButtonDown(lostland.gumd.platinum12548.ui.core.NewButton)
		 */
		@Override
		protected void onButtonDown(NewButton b) {
			// TODO 自动生成的方法存根
			
		}

		/* （非 Javadoc）
		 * @see lostland.gumd.platinum12548.ui.core.ScrollableMenuScreen#onClick(int)
		 */
		@Override
		public void onClick(int i) {
			Log.e("ISV", "error");
		}

		/* （非 Javadoc）
		 * @see lostland.gumd.platinum12548.ui.core.ScrollableMenuScreen#onCancel()
		 */
		@Override
		public void onCancel() {
			// TODO 自动生成的方法存根
			
		}
		
	}
	
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#onButtonDown(lostland.gumd.platinum12548.ui.core.NewButton)
	 */
	@Override
	protected void onButtonDown(NewButton b) {
		// DO NOTHING
	}
}
