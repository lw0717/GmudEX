/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：GmudNPC.java <p>
 * 创建时间：2013-5-30 下午9:08:39 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import android.graphics.Point;

/**
 * 类名：GmudNPC <p>
 * 说明：
 * @author 12548
 */
public class GmudNPC {

	public static final int NPC_IMGINDEX[] = new int[]{
		6, 46, 21, 58, 23, 24, 28, 22, 6, 47,
		13, 14, 16, 61, 54, 58, 51, 22, 40, 26,
		59, 60, 25, 55, 43, 63, 29, 53, 1, 56,
		57, 64, 59, 18, 26, 55, 50, 64, 20, 55,
		3, 15, 41, 54, 51, 4, 62, 24, 18, 59,
		49, 21, 6, 9, 7, 31, 40, 38, 3, 34,
		4, 3, 45, 35, 33, 32, 46, 44, 2, 47,
		26, 45, 61, 20, 20, 26, 26, 26, 62, 41,
		24, 21, 52, 27, 55, 22, 8, 45, 0, 61,
		60, 49, 1, 58, 24, 24, 19, 19, 19, 20,
		41, 62, 64, 3, 17, 58, 34, 1, 26, 5,
		24, 23, 58, 51, 26, 45, 4, 34, 19, 26,
		29, 20, 55, 30, 15, 3, 14, 10
	};

	GmudGame game;
	Point npcImgIndex[];
	
	public GmudNPC(GmudGame game){
		this.game= game;
		int i;
		this.npcImgIndex=new Point[NPC_IMGINDEX.length];
		for(i=0;i<npcImgIndex.length;i++)
		{
			int t=NPC_IMGINDEX[i]+440;
			npcImgIndex[i]=new Point(t%8, t/8);
		}
	}
	
	public void draw(int x,int y,int npcid)
	{
		if(GmudWorld.npc[npcid].dead)
		{
			GmudWorld.mapTile.draw(x, y, 4, 15);
		}
		else if(npcid == GmudWorld.npc.length-1)
		{
			if(GmudWorld.npc[GmudWorld.npc.length-1].sex == 0)
			{
				GmudWorld.mapTile.draw(x, y, 2, 56 +1);
			}else
			{
				GmudWorld.mapTile.draw(x, y, 2, 55 +1);
			}
		}else
			GmudWorld.mapTile.draw(x, y, npcImgIndex[npcid].x, npcImgIndex[npcid].y +1);
	}
	
	public void drawBiased(int x,int y,int dx,int dy,int npcid)
	{
		if(GmudWorld.npc[npcid].dead)
		{
			GmudWorld.mapTile.drawBiased(x, y, 4, 15,dx,dy);
		}
		else if(npcid == GmudWorld.npc.length-1)
		{
			if(GmudWorld.npc[GmudWorld.npc.length-1].sex == 0)
			{
				GmudWorld.mapTile.drawBiased(x, y, 2, 56 +1,dx,dy);
			}else
			{
				GmudWorld.mapTile.drawBiased(x, y, 2, 55 +1,dx,dy);
			}
		}else
		GmudWorld.mapTile.drawBiased(x, y, npcImgIndex[npcid].x, npcImgIndex[npcid].y +1, dx, dy);
	}
}
