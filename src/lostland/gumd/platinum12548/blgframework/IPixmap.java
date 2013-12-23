/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：IPixmap.java <p>
 * 创建时间：2013-5-19 下午7:16:16 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.blgframework;

import lostland.gumd.platinum12548.blgframework.IGraphics.PixmapFormat;

/**
 * 类名：IPixmap <p>
 * 说明：
 * @author 12548
 */
public interface IPixmap {
	public int getWidth();
	
	public int getHeight();
	
	public PixmapFormat getFormat();
	
	public void dispose();
}
