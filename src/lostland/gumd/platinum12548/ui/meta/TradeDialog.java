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

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import lostland.gmud.platinum12548.R;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.ui.LearningScreen;
import lostland.gumd.platinum12548.ui.NotificationScreen;

/**
 * 类名：BottomDialog <p>
 * 说明：
 * @author 12548
 */
public class TradeDialog extends Dialog {

	private Window mWindow;

	ListView lv;
	TextView tv1,tv2;

	ArrayList<String> list;
	int sticking[] = new int[200];


	/**
	 * @param context
	 */
	public TradeDialog(Context context) {
		super(context);
		Log.e("aha1", "真尼玛");
		mWindow=this.getWindow();
		mWindow.setBackgroundDrawable(new ColorDrawable(0));
		mWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		WindowManager.LayoutParams lp=mWindow.getAttributes();
		lp.gravity=Gravity.CENTER;
		lp.dimAmount=0.0001f;
		mWindow.setAttributes(lp);
		setCanceledOnTouchOutside(true);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mWindow.setContentView(R.layout.dialog_trade);
		Log.e("aha0", "真尼玛");
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);

		lv = (ListView) findViewById(R.id.lvRead);
		Arrays.fill(sticking, 0);
	}

	int s;

	private ArrayAdapter<String> aa;
	public void show(final int npcid)
	{
		list = new ArrayList<String>();
		switch(GmudWorld.npc[npcid].trading)
		{
		case 1:
			tv1.setText("要买什么你自己看看吧！");
			tv2.setText("金钱："+GmudWorld.mc.gold);
			
			for(int i:GmudWorld.npc[npcid].sells)
				push(GmudWorld.wp[i].name+"(价格："+GmudWorld.wp[i].cost+")",i);

			aa = new ArrayAdapter<String>(GmudWorld.game, android.R.layout.simple_expandable_list_item_1,list);
			
			lv.setAdapter(aa);

			lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					s = arg2;
					if(GmudWorld.wp[sticking[s]].cost <= GmudWorld.mc.gold && (!GmudWorld.mc.only(sticking[s])))
					{
						GmudWorld.mc.gold -= GmudWorld.wp[sticking[s]].cost;
						GmudWorld.mc.give(sticking[s]);
					}
					tv2.setText("金钱："+GmudWorld.mc.gold);
				}
			});
			break;
			
		case 2:
			tv1.setText("有什么不用的东西就拿来吧！");
			tv2.setText("金钱："+GmudWorld.mc.gold);
			
			re();
			
			lv.setAdapter(new ArrayAdapter<String>(GmudWorld.game, android.R.layout.simple_expandable_list_item_1,list));
			lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					s = arg2;
					if(!(GmudWorld.mc.inventory[sticking[s]] == 1 && GmudWorld.mc.equips(sticking[s])))
					{
						GmudWorld.mc.gold += (int)(GmudWorld.wp[sticking[s]].cost * 0.7);
						GmudWorld.mc.drop(sticking[s], 1);
//						tv2.setText("金钱："+GmudWorld.mc.gold);
						re();
//						aa.notifyDataSetChanged();
					}
					tv2.setText("金钱："+GmudWorld.mc.gold);
				}
			});
			break;
			
		case 101:
			tv1.setText("你想学什么就说吧！");
			tv2.setVisibility(View.INVISIBLE);
			for(int i=0;i<GmudWorld.npc[npcid].skills.length;i++)
				if(GmudWorld.npc[npcid].skills[i]>0)
					push(GmudWorld.skill[i].name + "x" + GmudWorld.npc[npcid].skills[i], i);
			lv.setAdapter(new ArrayAdapter<String>(GmudWorld.game, android.R.layout.simple_expandable_list_item_1,list));
			
			lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					s = arg2;
					if(!GmudWorld.mc.expcanlearn(GmudWorld.mc.skills[sticking[s]]+1))
						GmudWorld.game.setScreen(new NotificationScreen(GmudWorld.game,GmudWorld.ms,"你的武学经验不足，无法领会更深的功夫"));
					else if(GmudWorld.mc.skills[sticking[s]] > GmudWorld.npc[npcid].skills[sticking[s]])
						GmudWorld.game.setScreen(new NotificationScreen(GmudWorld.game,GmudWorld.ms,"你的功夫已经不输为师了，真是可喜可贺呀！"));
					else if(GmudWorld.mc.potential == 0)
					{
						GmudWorld.game.setScreen(new NotificationScreen(GmudWorld.game,GmudWorld.ms,"你的潜能已经发挥到极限了"));
						cancel();
						return;
					}
					else
					{
						GmudWorld.game.setScreen(new LearningScreen(GmudWorld.game,GmudWorld.ms,sticking[s], GmudWorld.npc[npcid].skills[sticking[s]]));
						cancel();
						return;
					}
				}
			});
			break;
		}

		super.show();
	}


	public void re()
	{
		Arrays.fill(sticking,0);
		list = new ArrayList<String>();
		for(int i:GmudWorld.mc.items)
			if(i!=0)
				push(GmudWorld.wp[i].name + "x" + GmudWorld.mc.inventory[i]+"(价格："+(int)(GmudWorld.wp[i].cost * 0.7)+")",i);
	}
	


	public void push(String s,int i)
	{
		list.add(s);
		sticking[list.size()-1] = i;
	}

}
