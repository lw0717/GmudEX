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

/**
 * 类名：BottomDialog <p>
 * 说明：
 * @author 12548
 */
public class SkillDialog extends Dialog {

	private Window mWindow;

	ExpandableListView elv;

	private  List<String> groupArray;  
	private  List<List<String>> childArray;  

	List<String> tempArray = new  ArrayList<String>(); 

	int sticking[][] = new int[10][200];

	/**
	 * @param context
	 */
	public SkillDialog(Context context) {
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

		Log.w("SkillDialog", "loading");

		Arrays.fill(sticking[0],0);
		Arrays.fill(sticking[1],0);
		Arrays.fill(sticking[2],0);
		Arrays.fill(sticking[3],0);
		Arrays.fill(sticking[4],0);
		Arrays.fill(sticking[5],0);

		groupArray = new  ArrayList<String>();  
		childArray = new  ArrayList<List<String>>();  

		Log.w("SkillDialog", "loading");

		groupArray.add("拳脚");
		groupArray.add("兵刃");
		groupArray.add("内功");
		groupArray.add("轻功");
		groupArray.add("招架");
		groupArray.add("知识");

		Log.w("SkillDialog", "loading");
		ArrayList<String> tempArray0 = new  ArrayList<String>();  
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >0 && GmudWorld.skill[i].kind == Skill.KIND_QUANJIAO)
			{
				boolean b = false;
				if(GmudWorld.mc.skillsckd[0] == i)
					b=true;
				tempArray0.add(GmudWorld.skill[i].name+(b?"（已装备）":"") + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]);
				sticking[0][tempArray0.size()-1] = i;
			}
		childArray.add(tempArray0);


		ArrayList<String> tempArray1 = new  ArrayList<String>();  
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >0 && GmudWorld.skill[i].kind == Skill.KIND_BINGREN)
			{
				boolean b = false;
				if(GmudWorld.mc.skillsckd[1] == i)
					b=true;
				tempArray1.add(GmudWorld.skill[i].name+((b?"（已装备）":"")  + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]));
				sticking[1][tempArray1.size()-1] = i;
			}
		childArray.add(tempArray1);

		ArrayList<String> tempArray2 = new  ArrayList<String>();  
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >0 && GmudWorld.skill[i].kind == Skill.KIND_NEIGONG)
			{
				boolean b = false;
				if(GmudWorld.mc.skillsckd[3] == i)
					b=true;
				tempArray2.add(GmudWorld.skill[i].name+((b?"（已装备）":"")  + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]));
				sticking[2][tempArray2.size()-1] = i;
			}
		childArray.add(tempArray2);

		ArrayList<String> tempArray3 = new  ArrayList<String>();  
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >0 && GmudWorld.skill[i].kind == Skill.KIND_QINGGONG)
			{
				boolean b = false;
				if(GmudWorld.mc.skillsckd[2] == i)
					b=true;
				tempArray3.add(GmudWorld.skill[i].name+((b?"（已装备）":"") + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]));
				sticking[3][tempArray3.size()-1] = i;
			}
		childArray.add(tempArray3);

		ArrayList<String> tempArray4 = new  ArrayList<String>();  
		int ii = 8;
		if(GmudWorld.mc.skills[ii]>0){
			tempArray4.add(GmudWorld.skill[ii].name + " " + GmudWorld.pj[GmudWorld.mc.skills[ii]/5] + "" + GmudWorld.mc.skills[ii] + "/" + GmudWorld.mc.learning[ii]);
			sticking[4][tempArray4.size()-1] = ii;
		}
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >10 && (GmudWorld.skill[i].kind == Skill.KIND_BINGREN  || GmudWorld.skill[i].kind == Skill.KIND_QUANJIAO))
			{
				boolean b = false;
				if(GmudWorld.mc.skillsckd[4] == i)
					b=true;
				tempArray4.add(GmudWorld.skill[i].name+((b?"（已装备）":"") + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]));
				sticking[4][tempArray4.size()-1] = i;
			}
		childArray.add(tempArray4);

		ArrayList<String> tempArray5 = new  ArrayList<String>();  
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >0 && GmudWorld.skill[i].kind == Skill.KIND_ZHISHI)
			{
				tempArray5.add(GmudWorld.skill[i].name + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]);
				sticking[5][tempArray5.size()-1] = i;
			}
		childArray.add(tempArray5);

		Log.w("SkillDialog", "loading");


		elv = (ExpandableListView) findViewById(R.id.expandableListView1);

		final ExpandableAdapter a = new ExpandableAdapter(GmudWorld.game);
		elv.setAdapter(a);
		Log.w("SkillDialog", "loading");

		elv.setOnChildClickListener(new OnChildClickListener(){

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				int j = sticking[groupPosition][childPosition];
				Log.i("ChildClick", "group"+groupPosition+"child"+childPosition+"skill"+j);
				if(j >10 && GmudWorld.skill[j].kind != Skill.KIND_ZHISHI)
				{
					int c[] = {0,1,3,2,4};
					if(GmudWorld.mc.skillsckd[c[groupPosition]] == j)
						GmudWorld.mc.skillsckd[c[groupPosition]] = -1;
					else{
						GmudWorld.mc.skillsckd[c[groupPosition]] = j;
						if(groupPosition<=1)
							GmudWorld.mc.skillsckd[4] = j;
					}

					Log.i("ChildClick", "checked skill:"+ j + "on" + c[groupPosition]);

					reload();
					a.notifyDataSetChanged();
				}

				return false;
			}

		});
		Log.w("SkillDialog", "loading");

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

		groupArray.add("拳脚");
		groupArray.add("兵刃");
		groupArray.add("内功");
		groupArray.add("轻功");
		groupArray.add("招架");
		groupArray.add("知识");

		Log.w("SkillDialog", "loading");
		ArrayList<String> tempArray0 = new  ArrayList<String>();  
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >0 && GmudWorld.skill[i].kind == Skill.KIND_QUANJIAO)
			{
				boolean b = false;
				if(GmudWorld.mc.skillsckd[0] == i)
					b=true;
				tempArray0.add(GmudWorld.skill[i].name+(b?"（已装备）":"") + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]);
				sticking[0][tempArray0.size()-1] = i;
			}
		childArray.add(tempArray0);


		ArrayList<String> tempArray1 = new  ArrayList<String>();  
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >0 && GmudWorld.skill[i].kind == Skill.KIND_BINGREN)
			{
				boolean b = false;
				if(GmudWorld.mc.skillsckd[1] == i)
					b=true;
				tempArray1.add(GmudWorld.skill[i].name+((b?"（已装备）":"")  + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]));
				sticking[1][tempArray1.size()-1] = i;
			}
		childArray.add(tempArray1);

		ArrayList<String> tempArray2 = new  ArrayList<String>();  
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >0 && GmudWorld.skill[i].kind == Skill.KIND_NEIGONG)
			{
				boolean b = false;
				if(GmudWorld.mc.skillsckd[3] == i)
					b=true;
				tempArray2.add(GmudWorld.skill[i].name+((b?"（已装备）":"")  + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]));
				sticking[2][tempArray2.size()-1] = i;
			}
		childArray.add(tempArray2);

		ArrayList<String> tempArray3 = new  ArrayList<String>();  
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >0 && GmudWorld.skill[i].kind == Skill.KIND_QINGGONG)
			{
				boolean b = false;
				if(GmudWorld.mc.skillsckd[2] == i)
					b=true;
				tempArray3.add(GmudWorld.skill[i].name+((b?"（已装备）":"") + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]));
				sticking[3][tempArray3.size()-1] = i;
			}
		childArray.add(tempArray3);

		ArrayList<String> tempArray4 = new  ArrayList<String>();  
		int ii = 8;
		if(GmudWorld.mc.skills[ii]>0){
			tempArray4.add(GmudWorld.skill[ii].name + " " + GmudWorld.pj[GmudWorld.mc.skills[ii]/5] + "" + GmudWorld.mc.skills[ii] + "/" + GmudWorld.mc.learning[ii]);
			sticking[4][tempArray4.size()-1] = ii;
		}
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >10 && (GmudWorld.skill[i].kind == Skill.KIND_BINGREN  || GmudWorld.skill[i].kind == Skill.KIND_QUANJIAO))
			{
				boolean b = false;
				if(GmudWorld.mc.skillsckd[4] == i)
					b=true;
				tempArray4.add(GmudWorld.skill[i].name+((b?"（已装备）":"") + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]));
				sticking[4][tempArray4.size()-1] = i;
			}
		childArray.add(tempArray4);

		ArrayList<String> tempArray5 = new  ArrayList<String>();  
		for(int i = 0;i<GmudWorld.mc.skills.length;i++)
			if(GmudWorld.mc.skills[i] >0 && GmudWorld.skill[i].kind == Skill.KIND_ZHISHI)
			{
				tempArray5.add(GmudWorld.skill[i].name + " " + GmudWorld.pj[GmudWorld.mc.skills[i]/5] + "" + GmudWorld.mc.skills[i] + "/" + GmudWorld.mc.learning[i]);
				sticking[5][tempArray5.size()-1] = i;
			}
		childArray.add(tempArray5);

		Log.w("SkillDialog", "loading");


	}

}
