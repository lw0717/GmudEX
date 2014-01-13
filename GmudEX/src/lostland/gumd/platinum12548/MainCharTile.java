/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：MainCharTile.java <p>
 * 创建时间：2013-5-20 下午4:54:37 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import lostland.gumd.platinum12548.blgframework.impl.BLGPixmap;

/**
 * 类名：MainCharTile <p>
 * 说明：
 * @author 12548
 */
public class MainCharTile extends Tileset {

	final static int MAINCHAR_WIDTH=32;
	final static int MAINCHAR_HEIGHT=48;
	
	final static int WALK_STEPS=4;
	
	GmudGame game;
	public int currentStep=0;
	public static int X,Y;
	BLGPixmap pixmap2;
	
	public static Direction currentDirection;
	
	/**
	 * 不要使用这个构造函数。
	 * @param pixmap
	 * @param tileWidth
	 * @param tileHeight
	 */
	public MainCharTile(BLGPixmap pixmap, int tileWidth, int tileHeight) {
		super(pixmap, tileWidth, tileHeight);
	}

	public MainCharTile(GmudGame game)
	{
		super((BLGPixmap) Assets.maincharTile,MAINCHAR_WIDTH,MAINCHAR_HEIGHT);
		this.game=game;
		pixmap2 = (BLGPixmap) Assets.girl;
	}
	
	
	
	/**
	 * @param direction 主角朝向
	 * @param step 主角步伐，对4取余后0、2为静止，1为出右脚，3为出左脚。
	 * @param x 通常为1、2、3三种。
	 * @param y 通常为1.
	 */
	public void draw(Direction direction,int step,int x,int y)
	{
		BLGPixmap pixmap = this.pixmap;
		if(GmudWorld.mc.sex > 0) pixmap = pixmap2;
		
		
		game.getGraphics().drawPixmap(pixmap, x*MAINCHAR_WIDTH, (y+1)*MAINCHAR_WIDTH-MAINCHAR_HEIGHT
				, (step%4)*tileWidth, direction.maincharTileY()*tileHeight, tileWidth, tileHeight);
		currentStep=step%4;
		currentDirection= direction;
		X=x;
		Y=y;
	}
	
	/**
	 * 画出原地踏步的下一帧。
	 * @param direction 方向
	 * @param x
	 * @param y
	 */
	public void drawOriWalking(Direction direction,int x,int y)
	{
		BLGPixmap pixmap = this.pixmap;
		if(GmudWorld.mc.sex > 0) pixmap = pixmap2;
		
		game.getGraphics().drawPixmap(pixmap, x*MAINCHAR_WIDTH, (y+1)*MAINCHAR_WIDTH-MAINCHAR_HEIGHT
				, currentStep*tileWidth, direction.maincharTileY()*tileHeight, tileWidth, tileHeight);
		currentDirection= direction;
	}
	
	/**
	 * 画出地图内左右移动的下一帧。
	 * @param direction 方向，只能为LEFT或RIGHT。
	 * @param step 走出的第N-1步（走到下一个格子共需WALK_STEPS步）
	 * @param x 出发点x坐标，1、2或3。
	 */
	public void drawInnerWalking(Direction direction,int step, int x,int y)
	{
		BLGPixmap pixmap = this.pixmap;
		if(GmudWorld.mc.sex > 0) pixmap = pixmap2;
		
		int dx=(direction.dx())*MAINCHAR_WIDTH*(step+1)/WALK_STEPS;
		int dy=direction.dy()*MAINCHAR_WIDTH*(step+1)/WALK_STEPS;
		game.getGraphics().drawPixmap(pixmap, x*MAINCHAR_WIDTH+dx, (y+1)*MAINCHAR_WIDTH-MAINCHAR_HEIGHT+dy
				, currentStep*tileWidth, direction.maincharTileY()*tileHeight, tileWidth, tileHeight);
		currentDirection= direction;
	}
	
	public static int absX()
	{
		return GmudWorld.ms.X+X;
	}
	
	public static int absY()
	{
		return GmudWorld.ms.Y+Y;
	}
	
	public static int frontX()
	{
		return X+ currentDirection.dx();
	}
	
	public static int frontY()
	{
		return Y+ currentDirection.dy();
	}
	
	public static int frontAbsX()
	{
		return absX()+ currentDirection.dx();
	}
	
	public static int frontAbsY()
	{
		return absY()+ currentDirection.dy();
	}
}
