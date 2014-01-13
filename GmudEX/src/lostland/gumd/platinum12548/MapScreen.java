/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名： java <p>
 * 创建时间：2013-5-20 下午4:52:07 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import android.util.Log;
import lostland.gmud.platinum12548.BuildConfig;
import lostland.gumd.platinum12548.blgframework.BasicScreen;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.IGraphics;
import lostland.gumd.platinum12548.blgframework.IInput;
import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;
import lostland.gumd.platinum12548.ui.MapEventScreen;
import lostland.gumd.platinum12548.ui.NpcMenuScreen;
import lostland.gumd.platinum12548.ui.core.ButtonControlledScreen;
import lostland.gumd.platinum12548.ui.core.GmudWindow;
import lostland.gumd.platinum12548.ui.core.NewButton;

/**
 * 类名：MapScreen <p>
 * 说明：在地图上的主界面。
 * @author 12548
 */
@SuppressWarnings("unused")
public class MapScreen extends ButtonControlledScreen {

	public static final int C_COLUMNS=6;
	public static final int C_ROWS=10;

	public static final int WS_INNER=1;
	public static final int WS_OUTSIDE=2;

	public static boolean b=false;
	final float STEP_TIME=0.06f;

	float tickTime=0.0f,rTime=0.0f;
	int fCount=0,fps;
	public static boolean walking=false;
	public static int walkStep,walkState;
	public int X,Y;

	public GmudMap map;

	public int round=0,number=0;


	float x,y,z;
	int tx,ty,downX,downY;
	boolean outsided;
	boolean c=true;

	GmudWindow btnUp,btnDown,btnLeft,btnRight,btnEnter,btnMenu;


	public static boolean zlEnabled = false;
	public static boolean btnsEnabled = false;

	/**
	 * @param game
	 */
	public MapScreen(IGame game) {
		super(game);

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);


		if(!b)b=true;
		BasicScreen.b = true;


		if(yearTime >= 300)
		{
			GmudWorld.mc.month++;
			yearTime -=300;
		}

		if(GmudWorld.mc.month >=12)
		{
			GmudWorld.mc.month -=12;
			GmudWorld.mc.age++;
		}

		if(hungerTime>=15.0)
		{
			if(!BasicScreen.check())
			{
				Log.e("cheat",""+this.toString());
				SingleTouchHandler.flag = 999;
			}
			hungerTime -= 15.0;
			GmudWorld.mc.food--;
			GmudWorld.mc.drink--;
			if(GmudWorld.mc.food<0)GmudWorld.mc.food=0;
			if(GmudWorld.mc.drink<0)GmudWorld.mc.drink=0;
			GmudWorld.mc.rec(5);
			if(GmudWorld.mc.food > 0 && GmudWorld.mc.drink > 0){
				GmudWorld.mc.hp += GmudWorld.mc.maxhp * 0.01;
				if(GmudWorld.mc.hp>GmudWorld.mc.maxhp)GmudWorld.mc.hp=GmudWorld.mc.maxhp;
			}
			BasicScreen.recheck();
		}

		IInput i= game.getInput();
		synchronized(this){
			x=i.getAccelX();
			y=i.getAccelY();
			z=i.getAccelZ();
			tx=SingleTouchHandler.touchX;
			ty=SingleTouchHandler.touchY;
//			pretd=td;
//			td=i.isTouchDown(0);
		}

		game.getInput().getKeyEvents();
		game.getInput().getTouchEvents();

		if(walking)
		{
			tickTime+= deltaTime;
			if(tickTime>STEP_TIME)
			{
				tickTime-=STEP_TIME;
				walkStep++;
				GmudWorld.cnm.currentStep=(GmudWorld.cnm.currentStep+1)%4;
				if(walkStep >= MainCharTile.WALK_STEPS)
				{
					if(walkState == WS_INNER)
					{
						MainCharTile.X += MainCharTile.currentDirection.dx();
						MainCharTile.Y += MainCharTile.currentDirection.dy();
					}
					else
					{
						X += MainCharTile.currentDirection.dx();
						Y += MainCharTile.currentDirection.dy();
					}
					walkStep=0;
					walking=false;
				}
			}
		}
		else if(map.getEvent(MainCharTile.absX(),MainCharTile.absY())>-1)
		{
			int t=map.getEvent(MainCharTile.absX(),MainCharTile.absY());
			map=GmudWorld.map[t/1000000];
			t%=1000000;

			switch(MainCharTile.currentDirection)
			{
			case DOWN:
				if(MainCharTile.Y >= C_COLUMNS-2){
					MainCharTile.X=(C_ROWS+1)/2;
					MainCharTile.Y=2;
				}
				break;
			case LEFT:
				if(MainCharTile.X <= 1){
					MainCharTile.X=5;
					MainCharTile.Y=(C_COLUMNS+1)/2;
				}
				break;
			case RIGHT:
				if(MainCharTile.X >= C_ROWS-2){
					MainCharTile.X=4;
					MainCharTile.Y=(C_COLUMNS+1)/2;
				}
				break;
			case UP:
				if(MainCharTile.Y <= 1){
					MainCharTile.X=(C_ROWS+1)/2;
					MainCharTile.Y=3;
				}
				break;
			default:
				break;

			}



			X=t/1000-MainCharTile.X;
			t%=1000;
			Y=t-MainCharTile.Y;
			
			if(X<0 && MainCharTile.currentDirection == Direction.RIGHT)
			{
				int tt = -X;
				X = 0;
				MainCharTile.X-=tt;
			}
			
			if(Y<0 && MainCharTile.currentDirection == Direction.DOWN)
			{
				int tt = -Y;
				Y = 0;
				MainCharTile.Y-=tt;
			}
			
			if(map.width < 10)
			{
				int tt = -X;
				X = 0;
				MainCharTile.X-=tt;
			}
			
		}
		else
		{
			if(!nbDetect())
				if(zlEnabled)
					accelWalkDetect();
		}

	}



	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#pause()
	 */
	@Override
	public void pause() {
		b=false;
		//		Log.w("MapScreen", "onPause");
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#resume()
	 */
	@Override
	public void resume() {
		BasicScreen.recheck();
		//		Log.w("MapScreen", "onResume");
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#dispose()
	 */
	@Override
	public void dispose() {
		// TODO 自动生成的方法存根

	}


	boolean nbDetect()
	{
		if(isDown)
		{
			switch(NewButton.inbound(otx, oty))
			{
			case NB_DOWN:
				MainCharTile.currentDirection= Direction.DOWN;
				if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
						Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
				{
					walkProcess();
					return true;
				}
				break;
			case NB_LEFT:
				MainCharTile.currentDirection= Direction.LEFT;
				if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
						Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
				{
					walkProcess();
					return true;
				}
				break;
			case NB_RIGHT:
				MainCharTile.currentDirection= Direction.RIGHT;
				if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
						Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
				{
					walkProcess();
					return true;
				}
				break;
			case NB_UP:
				MainCharTile.currentDirection= Direction.UP;
				if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
						Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
				{
					walkProcess();
					return true;
				}
				break;
			default:
				break;
			}
		}
		return false;
	}

	
	//
	//	boolean touchDetect() {
	//
	//		int touchX = tx/32;
	//		int touchY = ty/32;
	//
	//		if(td){
	//			if(!pretd){
	//				downX  = touchX;
	//				downY  = touchY;
	//				outsided = false;
	//			}else{
	//				if(!(downX==touchX && downY==touchY)){
	//					if(touchX==downX && touchY<downY){
	//						MainCharTile.currentDirection= Direction.UP;
	//						if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
	//								Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
	//							walkProcess();
	//					}
	//					else if(touchX==downX && touchY>downY){
	//						MainCharTile.currentDirection= Direction.DOWN;
	//						if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
	//								Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
	//							walkProcess();
	//					}
	//					else if(touchX<downX && touchY==downY){
	//						MainCharTile.currentDirection= Direction.LEFT;
	//						if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
	//								Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
	//							walkProcess();		
	//					}
	//					else if(touchX>downX && touchY==downY){
	//						MainCharTile.currentDirection= Direction.RIGHT;
	//						if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
	//								Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
	//							walkProcess();
	//					}
	//					outsided = true;
	//				}
	//			}
	//			return true;
	//		}else if(pretd){
	//			if(outsided){
	//				if(downX==touchX && downY==touchY){
	//					if(map.getWalkable(MainCharTile.frontAbsX(), MainCharTile.frontAbsY())==GmudMap.MP_NPC)
	//						if(!GmudWorld.npc[map.getEvent(MainCharTile.frontAbsX(), MainCharTile.frontAbsY())].dead)
	//						{
	//							if(map.getEvent(MainCharTile.frontAbsX(), MainCharTile.frontAbsY()) == GmudWorld.npc.length-1){
	//								GmudWorld.npc[GmudWorld.npc.length-1].badman();
	//								GmudWorld.npc[GmudWorld.npc.length-1].setDifficulty(0.8f + (round+number)*0.05f);
	//							}
	//							game.setScreen(new NpcMenuScreen(game,map.getEvent(MainCharTile.frontAbsX(), MainCharTile.frontAbsY())));
	//						}
	//						else
	//							;//Do nothing
	//					else if(map.getWalkable(MainCharTile.frontAbsX(), MainCharTile.frontAbsY())== GmudMap.MP_EVENT)
	//					{
	//						eventProcess(map.getEvent(MainCharTile.frontAbsX(), MainCharTile.frontAbsY()));
	//					}
	//					else
	//					{
	//						game.setScreen(GmudWorld.mms);
	//					}
	//				}
	//			}
	//			else
	//			{
	//
	//				//				if(!(downX==touchX && downY==touchY)){
	//				//					if(touchX==downX && touchY<downY){
	//				//						MainCharTile.currentDirection= Direction.UP;
	//				//						if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
	//				//								Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
	//				//						{
	//				//							walkProcess();
	//				//							return true;
	//				//						}
	//				//
	//				//					}
	//				//					else if(touchX==downX && touchY>downY){
	//				//						MainCharTile.currentDirection= Direction.DOWN;
	//				//						if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
	//				//								Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
	//				//						{
	//				//							walkProcess();
	//				//							return true;
	//				//						}
	//				//
	//				//					}
	//				//					else if(touchX<downX && touchY==downY){
	//				//						MainCharTile.currentDirection= Direction.LEFT;
	//				//						if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
	//				//								Y+MainCharTile.Y +MainCharTile.currentDirection.dy())){
	//				//							walkProcess();
	//				//							return true;
	//				//						}
	//				//
	//				//					}
	//				//					else if(touchX>downX && touchY==downY){
	//				//						MainCharTile.currentDirection= Direction.RIGHT;
	//				//						if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
	//				//								Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
	//				//						{
	//				//							walkProcess();
	//				//							return true;
	//				//						}
	//				//					}
	//				//				}
	//
	//				int absTouchX=touchX+MapScreen.X;
	//				int absTouchY=touchY+MapScreen.Y;
	//
	//
	//				if(touchX==MainCharTile.X && touchY==MainCharTile.Y)
	//				{
	//					game.setScreen(GmudWorld.mms);
	//				}
	//				else
	//				{
	//					switch(MapScreen.map.getWalkable(absTouchX, absTouchY))
	//					{
	//					case GmudMap.MP_WALKABLE:
	//						break;
	//					case GmudMap.MP_UNWALKABLE:
	//						break;
	//					case GmudMap.MP_EVENT:
	//						if(GmudWorld.near(touchX, touchY, MainCharTile.X,MainCharTile.Y))
	//							GameEvent.callEvent(MapScreen.map.getEvent(absTouchX, absTouchY));
	//						break;
	//					case GmudMap.MP_NPC:
	//						if(GmudWorld.near(touchX, touchY, MainCharTile.X,MainCharTile.Y))
	//							if(!GmudWorld.npc[map.getEvent(absTouchX, absTouchY)].dead)
	//							{
	//								if(map.getEvent(absTouchX, absTouchY) == GmudWorld.npc.length-1){
	//									GmudWorld.npc[GmudWorld.npc.length-1].badman();
	//									GmudWorld.npc[GmudWorld.npc.length-1].setDifficulty(0.8f + (round+number)*0.05f);
	//								}
	//								game.setScreen(new NpcMenuScreen(game,map.getEvent(absTouchX, absTouchY)));
	//							}
	//					}
	//				}
	//			}
	//			return true;
	//		}
	//		return false;
	//	}



	void accelWalkDetect()
	{
		final float LINGMINDU = 3.0f;
		final float JINGQUEDU = 2.0f;
		float ax=Math.abs(x);
		float ay=Math.abs(y);
		int sgn=z>0?1:-1;

		if( sgn * x >sgn * LINGMINDU && ay<JINGQUEDU)
		{
			MainCharTile.currentDirection= Direction.DOWN;
			if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
					Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
				walkProcess();			
		}
		else if( sgn * x <-sgn * LINGMINDU && ay<JINGQUEDU)
		{
			MainCharTile.currentDirection= Direction.UP;
			if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
					Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
				walkProcess();			
		}
		else if( sgn * y <-sgn * LINGMINDU && ax<JINGQUEDU)
		{
			MainCharTile.currentDirection= Direction.LEFT;
			if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
					Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
				walkProcess();
		}
		else if( sgn * y > sgn * LINGMINDU && ax < JINGQUEDU)
		{
			MainCharTile.currentDirection= Direction.RIGHT;
			if( map.isWalkable( X+MainCharTile.X+MainCharTile.currentDirection.dx(),
					Y+MainCharTile.Y +MainCharTile.currentDirection.dy()))
				walkProcess();
		}
	}

	void walkProcess()
	{
		if((MainCharTile.frontAbsX()<=1 && MainCharTile.currentDirection == Direction.LEFT) || 
				(MainCharTile.frontAbsY()==0 && MainCharTile.currentDirection == Direction.UP) || 
				(MainCharTile.frontAbsX()>= map.width-2 && MainCharTile.currentDirection == Direction.RIGHT) || 
				(MainCharTile.frontAbsY()== map.height-1 && MainCharTile.currentDirection == Direction.DOWN) ||
				(MainCharTile.X<((C_ROWS-1)/2) && MainCharTile.currentDirection == Direction.RIGHT) ||
				(MainCharTile.X>((C_ROWS-1)/2) && MainCharTile.currentDirection == Direction.LEFT) ||
				(MainCharTile.Y<((C_COLUMNS-1)/2) && MainCharTile.currentDirection == Direction.DOWN) ||
				(MainCharTile.Y>((C_COLUMNS-1)/2) && MainCharTile.currentDirection == Direction.UP)  ||
				(X+C_ROWS>=map.width && MainCharTile.currentDirection == Direction.RIGHT) ||
				(X<=0 && MainCharTile.currentDirection == Direction.LEFT) ||
				(Y+C_COLUMNS>=map.height && MainCharTile.currentDirection == Direction.DOWN) ||
				(Y<=0 && MainCharTile.currentDirection == Direction.UP))
			walkState=WS_INNER;
		else
			walkState=WS_OUTSIDE;
		Log.d("walkProcess", "walk:"+walkState);
		walking=true;
	}

	void eventProcess(int eventid)
	{
//		game.setScreen(new MapEventScreen(game,eventid));
		
		BasicScreen.acr = false;
		GameEvent.callEvent(eventid);
		BasicScreen.recheck();
		BasicScreen.acr = true;
		Log.w("map", "acr reset");
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#onButtonDown(lostland.gumd.platinum12548.ui.core.NewButton)
	 */
	@Override
	protected void onButtonDown(NewButton b) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#onButtonClick(lostland.gumd.platinum12548.ui.core.NewButton)
	 */
	@Override
	public void onButtonClick(NewButton b) {

		if(!walking)
		{
			switch(b)
			{
			case NB_BACK:
				game.setScreen(GmudWorld.mms);
				break;
			case NB_DOWN:
				break;
			case NB_ENTER:
				if(map.getWalkable(MainCharTile.frontAbsX(), MainCharTile.frontAbsY())==GmudMap.MP_NPC)
					if(!GmudWorld.npc[map.getEvent(MainCharTile.frontAbsX(), MainCharTile.frontAbsY())].dead)
					{
						if(map.getEvent(MainCharTile.frontAbsX(), MainCharTile.frontAbsY()) == GmudWorld.npc.length-1){
							GmudWorld.npc[GmudWorld.npc.length-1].badman();
							GmudWorld.npc[GmudWorld.npc.length-1].setDifficulty(0.8f + (round+number)*0.05f);
						}
						game.setScreen(new NpcMenuScreen(game,map.getEvent(MainCharTile.frontAbsX(), MainCharTile.frontAbsY())));
					}
					else
						;//Do nothing
				else if(map.getWalkable(MainCharTile.frontAbsX(), MainCharTile.frontAbsY())== GmudMap.MP_EVENT)
				{
					eventProcess(map.getEvent(MainCharTile.frontAbsX(), MainCharTile.frontAbsY()));
				}
				break;
			case NB_LEFT:
				break;
			case NB_RIGHT:
				break;
			case NB_UP:
				break;
			case NB_MENU:
				game.setScreen(GmudWorld.mms);
				break;
			default:
				break;

			}
		}
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.ButtonControlledScreen#show()
	 */
	@Override
	protected void show() {
		//		rTime+=deltaTime;
		//		if(rTime<1.0f)
		//		{
		//			fCount++;
		//		}
		//		else
		//		{
		//			fps=fCount;
		//			fCount=0;
		//			rTime-=1.0;
		//		}
		BLGGraphics g=(BLGGraphics) game.getGraphics();
		g.clear(GameConstants.BG_COLOR);

		if((!walking) || walkState== WS_INNER)
			GmudWorld.mapTile.drawMap(map, X, Y);
		else
			GmudWorld.mapTile.drawMapWalking(map  , X, Y, MapTile.TILE_WIDTH*MainCharTile.currentDirection.inverse().dx()*walkStep/MainCharTile.WALK_STEPS, MapTile.TILE_WIDTH*MainCharTile.currentDirection.inverse().dy()*walkStep/MainCharTile.WALK_STEPS);

		if(walking)
			if(walkState== WS_INNER)
				GmudWorld.cnm.drawInnerWalking(MainCharTile.currentDirection, walkStep, MainCharTile.X,MainCharTile.Y);
			else
				GmudWorld.cnm.drawOriWalking(MainCharTile.currentDirection, MainCharTile.X,MainCharTile.Y);
		else
			GmudWorld.cnm.draw(MainCharTile.currentDirection, GmudWorld.cnm.currentStep, MainCharTile.X, MainCharTile.Y);

		if(b)
		{
			String s = GmudMap.MAP_NAME[map.getId()];
			g.drawRect(0, 0, s.length() * 12, 12, GameConstants.BG_COLOR);
			g.drawText(s, 0, 0, FontSize.FT_12PX);
		}

//		if(BuildConfig.DEBUG)
//		{
//			String s = MainCharTile.absX() + "," + MainCharTile.absY();
//			g.drawRect(0, 12, s.length() * 6, 12, GameConstants.BG_COLOR);
//			g.drawText(s, 0, 12, FontSize.FT_12PX);
//		}

		//		if(btnsEnabled)
		//		{
		//			btnUp.draw();
		//			btnDown.draw();
		//			btnLeft.draw();
		//			btnRight.draw();
		//			btnEnter.draw();
		//			btnMenu.draw();
		//		}
	}

}
