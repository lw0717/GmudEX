/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BLGFastRenderView.java <p>
 * 创建时间：2013-5-19 下午10:37:35 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework.impl;

import lostland.gumd.platinum12548.ui.core.ButtonControlledScreen;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 类名：BLGFastRenderView <p>
 * 说明：
 * @author 12548
 */
@SuppressLint("ViewConstructor")
public class BLGFastRenderView extends SurfaceView implements Runnable {
	BLGGame game;
	Bitmap framebuffer;
	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running = false;
	
	public static float multiplier = 1f;

	public BLGFastRenderView(BLGGame game, Bitmap framebuffer) {
		super(game);
		this.game = game;
		this.framebuffer = framebuffer;
		this.holder = getHolder();
	}

	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}

	@SuppressLint("NewApi")
	public void run() {
		Rect dstRect = new Rect();
		long startTime = System.nanoTime();
		while (running) {
			if (!holder.getSurface().isValid())
				continue;
			float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
			startTime = System.nanoTime();

			game.getCurrentScreen().render(deltaTime);
			game.getCurrentScreen().present(deltaTime);

			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);
			// Log.i("seesee",dstRect.flattenToString());
			// dstRect.right=640;
			// dstRect.bottom =384;
			
			Rect newRect = new Rect(dstRect.left,dstRect.top,(int) ((dstRect.right - dstRect.left) * multiplier + dstRect.left),(int) ((dstRect.bottom - dstRect.top) * multiplier + dstRect.top));
			
			
			canvas.drawBitmap(framebuffer, null, newRect, null);
			
			if(game.getCurrentScreen() instanceof ButtonControlledScreen)
			{
			
				game.SecondFB.setHasAlpha(true);
				canvas.drawBitmap(game.SecondFB, null, newRect, null);
			
			}
			
			holder.unlockCanvasAndPost(canvas);
		}
	}

	public void pause() {
		running = false;
		while (true) {
			try {
				renderThread.join();
				break;
			} catch (InterruptedException e) {
				// retry
			}
		}
	}
}
