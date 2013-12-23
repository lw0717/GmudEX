/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：GmudMap.java <p>
 * 创建时间：2013-5-21 下午12:41:17 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import android.graphics.Point;

/**
 * 类名：GmudMap <p>
 * 说明：地图（显示）
 * @author 12548
 */
public class GmudMap {
	
	public static final int MP_WALKABLE=-1;
	public static final int MP_UNWALKABLE=0;
	public static final int MP_CHANGETO=-1;//不要管他……
	public static final int MP_NPC=2;
	public static final int MP_EVENT=3;
	
	
	
	public static final String MAP_NAME[] = new String[] {   //map name
		//"平安镇", "商家堡", "玉女峰", "五指山", "冰火岛", "武当山", "大雪山", "黑森林", "灵心观", "铸剑谷", 
		//"桃花源"
		"家中", "平安镇西", "老婆婆家", "裁缝店", "豆腐店", "平安镇东", "村长家", "衙门", "商业街", "杂货店",
		"药店", "当铺", "兵器行", "客栈", "平安镇北郊", "平安镇西郊", "平安镇南郊", "平安镇东郊", "大雪山寨", "大雪山西客房二",
		"大雪山西花园", "大雪山西客房一", "大雪山东客房二", "大雪山东花园", "大雪山东客房一", "武当山脚", "武当山", "山间盆地", "五指山", "红莲教",
		"玉女峰", "时空的尽头", "冰火岛渡口", "冰火岛西厢外", "冰火岛西厢", "冰火岛东厢外", "冰火岛东厢", "冰火岛", "商家堡", "商家堡大厅", 
		"商家堡二楼", "商家堡客房一", "商家堡客房二", "商家堡客房三", "商家堡客房四", "失落的世界", "蛇女之心", "武馆"
		};

	
	Point p[][][];
	int i,j,k;
	public int width,height;
	public int id;

	/**
	 * 获取地图id。
	 * @return 地图的id
	 */
	public int getId() {
		return id;
	}

	public GmudMap(int width,int height,int id)
	{
		p=new Point[4][width][height];
		for(i=0;i<4;i++)
			for(j=0;j<width;j++)
				for(k=0;k<height;k++)
					p[i][j][k]=new Point(-1,-1);
		this.width=width;
		this.height=height;
		this.id = id;
	}
	
	public void setPoint(int z,int x,int y,int tileX,int tileY)
	{
		p[z][x][y].x=tileX;
		p[z][x][y].y=tileY;
	}
	
	public int getTileX(int z,int x,int y)
	{
		if(x<0 || x>=width ||y<0 || y>=height)
			return 0;
		return p[z][x][y].x;
	}
	
	public int getTileY(int z,int x,int y)
	{
		if(x<0 || x>=width ||y<0 || y>=height)
			return 0;
		return p[z][x][y].y;
	}
	
	public void setWalkable(int x,int y,int walkable)
	{
		p[3][x][y].x=walkable;
	}
	
	public void setEvent(int x,int y,int eventid)
	{
		p[3][x][y].y=eventid;
	}
	
	public int getWalkable(int x,int y)
	{
		if(x<0 || x>=width ||y<0 || y>=height)
			return 0;
		return p[3][x][y].x;
	}
	
	public int getEvent(int x,int y)
	{
		if(x<0 || x>=width ||y<0 || y>=height)
			return 0;
		return p[3][x][y].y;
	}
	
	public boolean isWalkable(int x,int y)
	{
		if( (x<0) || (x>=width) || (y<0) || (y>=height))
			return false;
		
		return p[3][x][y].x==MP_WALKABLE;
	}
	
	
}
