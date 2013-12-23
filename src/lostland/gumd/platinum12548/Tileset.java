/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：Tileset.java <p>
 * 创建时间：2013-5-20 下午4:16:12 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.blgframework.impl.BLGPixmap;

/**
 * 类名：Tileset <p>
 * 说明：
 * @author 12548
 */
public abstract class Tileset {
	BLGPixmap pixmap;
	int tileWidth;
	int tileHeight;
	
	/**
	 * @param pixmap
	 * @param tileWidth
	 * @param tileHeight
	 */
	public Tileset(BLGPixmap pixmap, int tileWidth, int tileHeight) {
		this.pixmap = pixmap;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	/**
	 * @return pixmap
	 */
	public BLGPixmap getPixmap() {
		return pixmap;
	}

	/**
	 * @return tileWidth
	 */
	public int getTileWidth() {
		return tileWidth;
	}

	/**
	 * @return tileHeight
	 */
	public int getTileHeight() {
		return tileHeight;
	}
	
	/**
	 * 注意：srcX和srcY为要画的tile块在tileset中的坐标（从0开始）。
	 */
	public void draw(BLGGraphics g,int x,int y, int srcX,int srcY)
	{
		g.drawPixmap(pixmap, x, y, srcX*tileWidth, srcY*tileHeight, tileWidth+1, tileHeight+1);
	}
	
}
