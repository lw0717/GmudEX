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

/**
 * 类名：SellingDialog <p>
 * 说明：
 * @author 12548
 */
public class SellingDialog extends Dialog {

	private Window mWindow;

	ExpandableListView elv;

	private  List<String> groupArray;  
	private  List<List<String>> childArray;  

	List<String> tempArray = new  ArrayList<String>(); 

	int sticking[][] = new int[10][200];

	/**
	 * @param context
	 */
	public SellingDialog(Context context,final boolean menu) {
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
		}

		Log.w("SkillDialog", "loading");
		ArrayList<String> tempArray0 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind == 0)
			{
				tempArray0.add(GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i] + "出售价格：" + (int)(GmudWorld.wp[i].cost * 0.7));
				sticking[0][tempArray0.size()-1] = i;
			}
		childArray.add(tempArray0);


		ArrayList<String> tempArray1 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind == 1)
			{
				tempArray1.add(GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i] + "出售价格：" + (int)(GmudWorld.wp[i].cost * 0.7));
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
					tempArray2.add( GmudWorld.wp[i].name +(b?"（已装备）":"") + "x" + GmudWorld.mc.inventory[i] + "出售价格：" + (int)(GmudWorld.wp[i].cost * 0.7));
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
					tempArray3.add( GmudWorld.wp[i].name +(b?"（已装备）":"") + "x" + GmudWorld.mc.inventory[i] + "出售价格：" + (int)(GmudWorld.wp[i].cost * 0.7));
					sticking[3][tempArray3.size()-1] = i;
				}
			childArray.add(tempArray3);

			ArrayList<String> tempArray4 = new  ArrayList<String>();  
			for(int i:GmudWorld.mc.items)
				if(i >0 && GmudWorld.wp[i].kind >= 4 && i!=77)
				{
					tempArray4.add( GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i] + "出售价格：" + (int)(GmudWorld.wp[i].cost * 0.7));
					sticking[4][tempArray4.size()-1] = i;
				}
			childArray.add(tempArray4);

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
				Log.i("ChildClick", "group"+groupPosition+"child"+childPosition+"skill"+j);
				
				
				
					if(!GmudWorld.mc.only(j)){
						GmudWorld.mc.drop(j, 1);
						GmudWorld.mc.gold += GmudWorld.wp[j].cost * 0.7;
					}

				reload();
				ea.notifyDataSetChanged();

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
		
		Log.w("SkillDialog", "loading");
		ArrayList<String> tempArray0 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind == 0)
			{
				tempArray0.add(GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i] + "出售价格：" + (int)(GmudWorld.wp[i].cost * 0.7));
				sticking[0][tempArray0.size()-1] = i;
			}
		childArray.add(tempArray0);


		ArrayList<String> tempArray1 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind == 1)
			{
				tempArray1.add(GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i] + "出售价格：" + (int)(GmudWorld.wp[i].cost * 0.7));
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
				tempArray2.add( GmudWorld.wp[i].name +(b?"（已装备）":"") + "x" + GmudWorld.mc.inventory[i] + "出售价格：" + (int)(GmudWorld.wp[i].cost * 0.7));
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
				tempArray3.add( GmudWorld.wp[i].name +(b?"（已装备）":"") + "x" + GmudWorld.mc.inventory[i] + "出售价格：" + (int)(GmudWorld.wp[i].cost * 0.7));
				sticking[3][tempArray3.size()-1] = i;
			}
		childArray.add(tempArray3);

		ArrayList<String> tempArray4 = new  ArrayList<String>();  
		for(int i:GmudWorld.mc.items)
			if(i >0 && GmudWorld.wp[i].kind >= 4 && i!=77)
			{
				tempArray4.add( GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i] + "出售价格：" + (int)(GmudWorld.wp[i].cost * 0.7));
				sticking[4][tempArray4.size()-1] = i;
			}
		childArray.add(tempArray4);

		Log.w("SkillDialog", "loading");


	}

}
