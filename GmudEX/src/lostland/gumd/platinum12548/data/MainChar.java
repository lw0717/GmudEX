/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：MainChar.java <p>
 * 创建时间：2013-7-26 上午9:49:38 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.data;

import java.util.Arrays;

import lostland.gumd.platinum12548.GmudWorld;

/**
 * 类名：MainChar <p>
 * 说明：辣过主角……
 * @author 12548
 */
public class MainChar extends Npc {

	public int month = 0;
	
	public int food;
	public int drink;
	
	public int looking = 30;        
	public int potential = 0;
	   
	public int learning[];  //学习进度  
	
	public int inventory[]; // 拥有物品数量（所有物品）
	
	
	public void drop(int item,int count)
	{
		inventory[item]-=count;
		refreshItems();
	}
	
	
	public boolean only(int item)
	{
		if(item==77)return true;
		boolean flag1 = false, flag2 = true;
		for(int i:this.itemsckd)
			if(i == item)
				flag1 = true;
		if(inventory[item]>1)
			flag2 = false;
		return flag1 && flag2;
	}
	
	public void refreshItems()
	{
		inventory[0] = 1;
		int cnt = 0;
		for(int i = 0; i < inventory.length; i++)
			if(inventory[i]>0)
				cnt++;
		if(cnt == 0)
			items = new int[]{0};
		else
		{
			items = new int[cnt];
			int p=0;
			for(int i = 0; i < inventory.length; i++)
				if(inventory[i]>0)
					items[p++] = i;
		}
	}
	
	public void give(int i)
	{
		super.give(i);
		inventory[i]++;
		refreshItems();
	}
	
	public boolean have(int i)
	{
		return inventory[i]>0;
	}
	
	public int getFoodmax()
	{
		return 75 + this.str * 15;
	}
	
	public int getWatermax()
	{
		return 50 + this.str * 15;
	}
	
	public int getface()
	{
		return looking + this.skills[10] / 10;
	}

	public MainChar() {
		super();
		this.fame = 128;
		this.sp = 100;
		this.hp = 100;
		this.maxhp = 100;
		this.fp = 0;
		this.maxfp = 0;
		this.ads = 0;
		this.gold = 100;
		
		this.learning = new int[42];
		Arrays.fill(learning, 0);
		
		this.inventory = new int[GmudWorld.wp.length];
		this.items = new int[]{42};
		Arrays.fill(inventory, 0);
		inventory[0] = 1;
		inventory[42] = 1;
		this.refreshItems();
		
		this.skillsckd = new int[]{-1,-1,-1,-1,-1};
		this.itemsckd = new int[]{0};
	}




	/**
	 * @param potential 要设置的 potential
	 */
	public void setPotential(int potential) {
		this.potential = potential;
	}

}
