/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BottomDialog.java <p>
 * 创建时间：2013-5-21 下午4:07:07 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import lostland.gmud.platinum12548.R;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.data.Skill;
import lostland.gumd.platinum12548.ui.NotificationScreen;
import lostland.gumd.platinum12548.ui.core.YesNoScreen;

/**
 * 类名：ItemDialog <p>
 * 说明：
 * @author 12548
 */
public class ItemDialog extends Dialog {

	private Window mWindow;

	ExpandableListView elv;

	private  List<String> groupArray;  
	private  List<List<String>> childArray;  

	List<String> tempArray = new  ArrayList<String>(); 

	int sticking[][] = new int[10][200];

	/**
	 * @param context
	 */
	public ItemDialog(Context context,final boolean menu) {
		super(context);
		mWindow=this.getWindow();
		mWindow.setBackgroundDrawable(new ColorDrawable(0));
		mWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		WindowManager.LayoutParams lp=mWindow.getAttributes();
		lp.gravity=Gravity.CENTER;
		lp.dimAmount=0.0001f;
		mWindow.setAttributes(lp);
		setCanceledOnTouchOutside(true);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mWindow.setContentView(R.layout.sklnitm);

		Log.w("ItemDialog", "loading");

		Arrays.fill(sticking[0],0);
		Arrays.fill(sticking[1],0);
		Arrays.fill(sticking[2],0);
		Arrays.fill(sticking[3],0);
		Arrays.fill(sticking[4],0);
		Arrays.fill(sticking[5],0);

		groupArray = new  ArrayList<String>();  
		childArray = new  ArrayList<List<String>>();  

		Log.w("SkillDialog", "loading");

		groupArray.add("食物");
		groupArray.add("药物");

		if(menu){
			groupArray.add("武器");
			groupArray.add("装备");
			groupArray.add("其他");
			groupArray.add("丢弃");
		}

		Log.w("SkillDialog", "loading");
		ArrayList<String> tempArray0 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind == 0)
			{
				tempArray0.add(GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i]);
				sticking[0][tempArray0.size()-1] = i;
			}
		childArray.add(tempArray0);


		ArrayList<String> tempArray1 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind == 1)
			{
				tempArray1.add(GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i]);
				sticking[1][tempArray1.size()-1] = i;
			}
		childArray.add(tempArray1);

		if(menu){
			ArrayList<String> tempArray2 = new  ArrayList<String>();  
			for(int i:GmudWorld.mc.items)
				if(i >0 && GmudWorld.wp[i].kind == 2)
				{
					boolean b = false;
					for(int j : GmudWorld.mc.itemsckd)
						if(j == i)
							b=true;
					tempArray2.add( GmudWorld.wp[i].name +(b?"（已装备）":"") + "x" + GmudWorld.mc.inventory[i]);
					sticking[2][tempArray2.size()-1] = i;
				}
			childArray.add(tempArray2);

			ArrayList<String> tempArray3 = new  ArrayList<String>();  
			for(int i:GmudWorld.mc.items)
				if(i >0 && GmudWorld.wp[i].kind == 3)
				{
					boolean b = false;
					for(int j : GmudWorld.mc.itemsckd)
						if(j == i)
							b=true;
					tempArray3.add( GmudWorld.wp[i].name +(b?"（已装备）":"") + "x" + GmudWorld.mc.inventory[i]);
					sticking[3][tempArray3.size()-1] = i;
				}
			childArray.add(tempArray3);

			ArrayList<String> tempArray4 = new  ArrayList<String>();  
			for(int i:GmudWorld.mc.items)
				if(i >0 && GmudWorld.wp[i].kind >= 4)
				{
					tempArray4.add( GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i]);
					sticking[4][tempArray4.size()-1] = i;
				}
			childArray.add(tempArray4);

			ArrayList<String> tempArray5 = new  ArrayList<String>();  
			for(int i:GmudWorld.mc.items)
				if(i >0 && i!=77)
				{
					tempArray5.add( GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i]);
					sticking[5][tempArray5.size()-1] = i;
				}
			childArray.add(tempArray5);

			Log.w("SkillDialog", "loading");
		}


		elv = (ExpandableListView) findViewById(R.id.expandableListView1);
		final ExpandableAdapter ea = new ExpandableAdapter(GmudWorld.game);
		elv.setAdapter(ea);
		Log.w("SkillDialog", "loading");

		elv.setOnChildClickListener(new OnChildClickListener(){

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				int j = sticking[groupPosition][childPosition];
				boolean flag = true;
				Log.i("ChildClick", "group"+groupPosition+"child"+childPosition+"skill"+j);
				switch(groupPosition)
				{
				case 0:
					int a = GmudWorld.wp[j].a3;
					int b = GmudWorld.wp[j].a4;
					if(GmudWorld.mc.food < GmudWorld.mc.getFoodmax()){
						GmudWorld.mc.drop(j, 1);
						GmudWorld.mc.food += a;
						GmudWorld.mc.drink += b;
					}
					break;
				case 1:
					switch(GmudWorld.wp[j].subkind)
					{
					case 0:
						if(GmudWorld.mc.hp < GmudWorld.mc.maxhp)
						{
							GmudWorld.mc.drop(j, 1);
							GmudWorld.mc.cure((int) (GmudWorld.mc.maxhp * ((GmudWorld.wp[j].a3+1)*0.25)));
						}
						break;
					case 1:
						GmudWorld.mc.drop(j, 1);
						GmudWorld.mc.maxfp += GmudWorld.wp[j].a3;
						break;
					}

					break;
				case 2:
					if(GmudWorld.mc.itemsckd[0] == j)
						GmudWorld.mc.itemsckd[0] = 0;
					else
						GmudWorld.mc.itemsckd[0] = j;
					break;
				case 3:
					int place = GmudWorld.wp[j].subkind;
					int t[] = new int[0];
					if(GmudWorld.mc.equips(j))
					{
						for(int i:GmudWorld.mc.itemsckd)
							if(i != j)
								t=GmudWorld.push_back(t, i);
					}else{
						for(int i:GmudWorld.mc.itemsckd)
							if(GmudWorld.wp[i].kind != 3 || GmudWorld.wp[i].subkind != place)
								t=GmudWorld.push_back(t, i);
						t=GmudWorld.push_back(t, j);
					}
					GmudWorld.mc.itemsckd = t;

					break;
				case 4:
					if(GmudWorld.wp[j].kind == 4)
					{
						flag = false;
						if(j == 81 && GmudWorld.mc.sex != 2)
						{
							GmudWorld.game.setScreen(new YesNoScreen(GmudWorld.game,"真的要修炼吗？"){

								@Override
								protected void onYes() {
									GmudWorld.game.setScreen(new YesNoScreen(GmudWorld.game,"不后悔吗？"){

										@Override
										protected void onYes() {
											GmudWorld.mc.sex = 2;
											GmudWorld.game.setScreen(GmudWorld.ms);
										}

										@Override
										protected void onNo() {
											GmudWorld.game.setScreen(GmudWorld.ms);
										}

									});

								}

								@Override
								protected void onNo() {
									GmudWorld.game.setScreen(GmudWorld.ms);
								}

							});
						}
						else
						{
							if(GmudWorld.mc.skills[Skill.KIND_ZHISHI] <= 0)
								GmudWorld.game.setScreen(new NotificationScreen(GmudWorld.game,GmudWorld.ms,"你还是个文盲！"));
							else if(GmudWorld.mc.faction < 7 && GmudWorld.mc.faction > 0)
							{
								GmudWorld.game.setScreen(new NotificationScreen(GmudWorld.game,GmudWorld.ms,"还是先学好本门武功吧"));
							}
							else
							{
								GmudWorld.mc.faction = 7;
								new ReadingDialog(GmudWorld.game).show(j);
							}
						}
					}
					break;
				case 5:
					if(!GmudWorld.mc.only(j))
						GmudWorld.mc.drop(j, 1);
					break;
				}

				if(!menu && flag)
					cancel();
				else{
					reload();
					ea.notifyDataSetChanged();
				};
				return false;
			}

		});
		Log.w("ItemDialog", "loading");

	}



	//ExpandableListView的Adapter   
	public  class  ExpandableAdapter extends  BaseExpandableListAdapter  
	{  
		Activity activity;  

		public  ExpandableAdapter(Activity a)  
		{  
			activity = a;  
		}

		public  Object getChild(int  groupPosition, int  childPosition)  
		{  
			return  childArray.get(groupPosition).get(childPosition);  
		}

		public  long  getChildId(int  groupPosition, int  childPosition)  
		{  
			return  childPosition;  
		}
		public  int  getChildrenCount(int  groupPosition)  
		{  
			return  childArray.get(groupPosition).size();  
		}  
		public  View getChildView(int  groupPosition, int  childPosition,  
				boolean  isLastChild, View convertView, ViewGroup parent)  
		{  
			String string = childArray.get(groupPosition).get(childPosition);  
			return  getGenericView(string);  
		}  
		// group method stub   
		public  Object getGroup(int  groupPosition)  
		{  
			return  groupArray.get(groupPosition);  
		}  
		public  int  getGroupCount()  
		{  
			return  groupArray.size();  
		}  
		public  long  getGroupId(int  groupPosition)  
		{  
			return  groupPosition;  
		}  
		public  View getGroupView(int  groupPosition, boolean  isExpanded,  
				View convertView, ViewGroup parent)  
		{  
			String string = groupArray.get(groupPosition);  
			return  getGenericView(string);  
		}  
		// View stub to create Group/Children 's View   
		public  TextView getGenericView(String string)  
		{  
			// Layout parameters for the ExpandableListView   
			AbsListView.LayoutParams layoutParams = new  AbsListView.LayoutParams(  
					ViewGroup.LayoutParams.MATCH_PARENT, 64 );  
			TextView text = new  TextView(GmudWorld.game);  
			text.setLayoutParams(layoutParams);  
			// Center the text vertically   
			text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);  
			// Set the text starting position   
			text.setPadding(36 , 0 , 0 , 0 );  
			text.setTextColor(0xff000000);
			text.setText(string);  
			return  text;  
		}  
		public  boolean  hasStableIds()  
		{
			return  false ;  
		}

		public  boolean  isChildSelectable(int  groupPosition, int  childPosition)  
		{  
			return  true ;
		}

	}


	void reload()
	{
		Arrays.fill(sticking[0],0);
		Arrays.fill(sticking[1],0);
		Arrays.fill(sticking[2],0);
		Arrays.fill(sticking[3],0);
		Arrays.fill(sticking[4],0);
		Arrays.fill(sticking[5],0);

		groupArray = new  ArrayList<String>();  
		childArray = new  ArrayList<List<String>>();  

		Log.w("SkillDialog", "loading");

		groupArray.add("食物");
		groupArray.add("药物");

		groupArray.add("武器");
		groupArray.add("装备");
		groupArray.add("其他");
		groupArray.add("丢弃");

		Log.w("SkillDialog", "loading");
		ArrayList<String> tempArray0 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind == 0)
			{
				tempArray0.add(GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i]);
				sticking[0][tempArray0.size()-1] = i;
			}
		childArray.add(tempArray0);


		ArrayList<String> tempArray1 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind == 1)
			{
				tempArray1.add(GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i]);
				sticking[1][tempArray1.size()-1] = i;
			}
		childArray.add(tempArray1);

		ArrayList<String> tempArray2 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind == 2)
			{
				boolean b = false;
				for(int j : GmudWorld.mc.itemsckd)
					if(j == i)
						b=true;
				tempArray2.add( GmudWorld.wp[i].name +(b?"（已装备）":"") + "x" + GmudWorld.mc.inventory[i]);
				sticking[2][tempArray2.size()-1] = i;
			}
		childArray.add(tempArray2);

		ArrayList<String> tempArray3 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind == 3)
			{
				boolean b = false;
				for(int j : GmudWorld.mc.itemsckd)
					if(j == i)
						b=true;
				tempArray3.add( GmudWorld.wp[i].name +(b?"（已装备）":"") + "x" + GmudWorld.mc.inventory[i]);
				sticking[3][tempArray3.size()-1] = i;
			}
		childArray.add(tempArray3);

		ArrayList<String> tempArray4 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind >= 4)
			{
				tempArray4.add( GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i]);
				sticking[4][tempArray4.size()-1] = i;
			}
		childArray.add(tempArray4);

		ArrayList<String> tempArray5 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && i!=77)
			{
				tempArray5.add( GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i]);
				sticking[5][tempArray5.size()-1] = i;
			}
		childArray.add(tempArray5);

		Log.w("SkillDialog", "loading");


	}

}
