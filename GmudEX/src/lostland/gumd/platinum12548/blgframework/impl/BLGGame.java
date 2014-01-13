/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BLGGame.java <p>
 * 创建时间：2013-5-19 下午10:38:39 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework.impl;

import lostland.gumd.platinum12548.GameConstants;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.LoadingScreen;
import lostland.gumd.platinum12548.blgframework.BasicScreen;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IFileIO;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.IGraphics;
import lostland.gumd.platinum12548.blgframework.IInput;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


/**
 * 类名：BLGGame <p>
 * 说明：
 * @author 12548
 */
public class BLGGame extends Activity implements IGame {
	public BLGFastRenderView renderView;
	IGraphics graphics;
	IInput input;
	IFileIO fileIO;
	CScreen screen;
	WakeLock wakeLock;
	public Bitmap SecondFB;

	public static boolean exit = false;

	public static float scaleX;
	public static float scaleY;

	public static float s2x,s2y;
	
	
	public static int height,width;
	
	public static final int FB2W = GameConstants.FBWIDTH;
	public static final int FB2H = GameConstants.FBHEIGHT;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);


		//		Log.e("GmudGame", "creating");

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		//		Log.e("GmudGame", "creating1");

		//boolean isLandscape= getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = GameConstants.FBWIDTH;
		int frameBufferHeight = GameConstants.FBHEIGHT;

		//		Log.e("GmudGame", "creating11");

		Bitmap frameBuffer=Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.ARGB_8888);// ARGB8888 is instead

		//		Log.e("GmudGame", "creating111");


		width = getWindowManager().getDefaultDisplay().getWidth();
		height = getWindowManager().getDefaultDisplay().getHeight();
		
		
		
		
		SecondFB = Bitmap.createBitmap(FB2W, FB2H, Config.ARGB_8888);// ARGB8888 is instead
		//		
		//		Log.e("GmudGame", "creating1111");

		scaleX = (float) frameBufferWidth / width;
		scaleY = (float) frameBufferHeight / height;

		s2x =(float) FB2W / width;
		s2y =(float) FB2H / height;

		//		Log.e("GmudGame", "creating2");

		renderView = new BLGFastRenderView(this, frameBuffer);


		//		Log.e("GmudGame", "creating3");

		graphics = new BLGGraphics(getAssets(), frameBuffer,SecondFB);

		//		Log.e("GmudGame", "creating4");

		fileIO= new BLGFileIO(this);
		//TODO:.......
		input=new BLGInput(this, renderView, scaleX, scaleY,s2x,s2y);//^_^
		screen= getStartScreen();
		setContentView(renderView);



		//		Log.e("GmudGame", "creatd");

		PowerManager powerManager=(PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GMUDGame");
	}

	@Override
	public void onResume()
	{
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}

	@Override
	public void onPause()
	{
		//		new SavingScreen(this).save();
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();

		if (isFinishing())
			screen.dispose();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGame#getInput()
	 */
	@Override
	public IInput getInput() {
		return input;
	}
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGame#getFileIO()
	 */
	@Override
	public IFileIO getFileIO() {
		return fileIO;
	}
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGame#getGraphics()
	 */
	@Override
	public IGraphics getGraphics() {
		return graphics;
	}
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGame#setScreen(lostland.gumd.platinum12548.blgframework.CScreen)
	 */
	@Override
	public void setScreen(CScreen screen) {
		if(screen==null)
			throw new IllegalArgumentException("screen不能为null！");

		this.screen.npause();
		this.screen.dispose();
		screen.nresume();
		screen.render(0);
		this.screen=screen;
	}
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGame#getCurrentScreeen()
	 */
	@Override
	public CScreen getCurrentScreen() {
		return screen;
	}
	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IGame#getStartScreen()
	 */
	@Override
	public CScreen getStartScreen() {
		return new LoadingScreen(this);
	}


	public void oo(){
		exit = true;
		finish();
	}

	/* （非 Javadoc）
	 * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		//		LoadingScreen ls = new LoadingScreen(this);
		//		ls.update(0);
		//		new LoadingScreen(this).load();
		GmudWorld.ms.X = savedInstanceState.getInt("mx");
		GmudWorld.ms.Y = savedInstanceState.getInt("my");
		GmudWorld.ms.map = GmudWorld.map[savedInstanceState.getInt("map")];
	}

	/* （非 Javadoc）
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//		new SavingScreen(this).save();
		outState.putInt("mx", GmudWorld.ms.X);
		outState.putInt("my", GmudWorld.ms.Y);
		outState.putInt("map", GmudWorld.ms.map.id);
		outState.putLong("crc", BasicScreen.chksum);
		Log.w("game", "onSaveInstanceState");
	}

	/* （非 Javadoc）
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
//		if(!exit)
//			new SavingScreen(this).save();
		super.onDestroy();
	}
}
