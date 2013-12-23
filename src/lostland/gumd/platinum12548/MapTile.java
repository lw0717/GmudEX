/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：MapTile.java <p>
 * 创建时间：2013-5-21 下午12:23:50 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import lostland.gumd.platinum12548.blgframework.impl.BLGGraphics;
import lostland.gumd.platinum12548.blgframework.impl.BLGPixmap;
import android.util.Log;


/**
 * 类名：MapTile <p>
 * 说明：
 * @author 12548
 */
@SuppressWarnings("unused")
public class MapTile extends Tileset {

	public final static int TILE_WIDTH=32;
	
	private GmudGame game;
	
	/**
	 * 不要使用这个构造函数！
	 */
	public MapTile(BLGPixmap pixmap, int tileWidth, int tileHeight) {
		super(pixmap, tileWidth, tileHeight);
	}
	
	public MapTile(GmudGame game)
	{
		super((BLGPixmap) Assets.mapTile,TILE_WIDTH,TILE_WIDTH);
		this.game=game;
	}
	
	/**
	 * @param x 从0到4的整数。
	 * @param y 从0到2的整数。
	 * @param srcX 从0到7的整数。
	 * @param srcY 从0到63的整数。
	 */
	public void draw(int x,int y ,int srcX,int srcY)
	{
		super.draw((BLGGraphics) game.getGraphics(), x*TILE_WIDTH, y*TILE_WIDTH, srcX, srcY);
	}
	
	public void drawBiased(int x,int y,int srcX,int srcY,int dx,int dy)
	{
		super.draw((BLGGraphics) game.getGraphics(), x*TILE_WIDTH+dx, y*TILE_WIDTH+dy, srcX,srcY);
	}
	
	public void drawMap(GmudMap map,int mapX,int mapY)
	{
		int i,j,k;
		for(i=0;i<3;i++)
		{
			for(j=0;j<MapScreen.C_ROWS;j++)
				for(k=0;k<MapScreen.C_COLUMNS;k++)
					if(map.getTileX(i, j+mapX, k+mapY)>-1 && map.getTileY(i, j+mapX, k+mapY)>-1)
					{
						//Log.v("drawMap:","drawing:screen("+j+","+k+")is tile("+map.getTileX(i, j+mapX, k+mapY)+","+map.getTileY(i, j+mapX, k+mapY)+")in layer"+i);
						this.draw(j,k,map.getTileX(i, j+mapX, k+mapY),map.getTileY(i, j+mapX, k+mapY));
					}
		}
		for(j=0;j<MapScreen.C_ROWS;j++)
			for(k=0;k<MapScreen.C_COLUMNS;k++)
				if(map.getWalkable(j+mapX, k+mapY)==GmudMap.MP_NPC)
					GmudWorld.npcc.draw(j, k, map.getEvent(j+mapX, k+mapY));
	}

	public void drawMapWalking(GmudMap map,int x,int y,int dx,int dy)
	{
		int i,j,k;
		for(i=0;i<3;i++)
			for(j=-1;j<=MapScreen.C_ROWS;j++)
				for(k=-1;k<=MapScreen.C_COLUMNS;k++)
					if(map.getTileX(i, j+x, k+y)>-1 && map.getTileY(i, j+x, k+y)>-1)
						this.drawBiased(j, k, map.getTileX(i, j+x,k+y), map.getTileY(i, j+x, k+y), dx, dy);
		for(j=-1;j<=MapScreen.C_ROWS;j++)
			for(k=-1;k<=MapScreen.C_COLUMNS;k++)
				if(map.getWalkable(j+x, k+y)==GmudMap.MP_NPC)
					GmudWorld.npcc.drawBiased(j, k, dx, dy, map.getEvent(j+x, k+y));
	}
}
