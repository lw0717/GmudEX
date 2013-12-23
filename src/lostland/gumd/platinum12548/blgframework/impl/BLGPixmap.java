/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BLGPixmap.java <p>
 * 创建时间：2013-5-19 下午9:47:56 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework.impl;

import lostland.gumd.platinum12548.blgframework.IPixmap;
import lostland.gumd.platinum12548.blgframework.IGraphics.PixmapFormat;
import android.graphics.Bitmap;


/**
 * 类名：BLGPixmap <p>
 * 说明：
 * @author 12548
 */
public class BLGPixmap implements IPixmap
{
	Bitmap bitmap;
	PixmapFormat format;
	
	public BLGPixmap(Bitmap bitmap, PixmapFormat format)
	{
		this.bitmap= bitmap;
		this.format= format;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IPixmap#getWidth()
	 */
	@Override
	public int getWidth() {
		return bitmap.getWidth();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IPixmap#getHeight()
	 */
	@Override
	public int getHeight() {
		return bitmap.getHeight();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IPixmap#getFormat()
	 */
	@Override
	public PixmapFormat getFormat() {
		return format;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.IPixmap#dispose()
	 */
	@Override
	public void dispose() {
		bitmap.recycle();
	}
	
}
