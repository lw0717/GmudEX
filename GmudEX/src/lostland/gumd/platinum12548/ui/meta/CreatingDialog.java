/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：BottomDialog.java <p>
 * 创建时间：2013-5-21 下午4:07:07 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui.meta;

import lostland.gmud.platinum12548.R;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.data.MainChar;
import lostland.gumd.platinum12548.ui.StartScreen;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

/**
 * 类名：BottomDialog <p>
 * 说明：
 * @author 12548
 */
public class CreatingDialog extends Dialog {

	private Window mWindow;
	

	ToggleButton tb;
	
	
	EditText etName;
	EditText etStr;
	EditText etAgi;
	EditText etBon;
	EditText etWxg;
	
	Button btnOK;
	
	/**
	 * @param context
	 */
	public CreatingDialog(Context context) {
		super(context);
		mWindow=this.getWindow();
		mWindow.setBackgroundDrawable(new ColorDrawable(0));
		mWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		WindowManager.LayoutParams lp=mWindow.getAttributes();
		lp.gravity=Gravity.CENTER;
		lp.dimAmount=0.0001f;
		mWindow.setAttributes(lp);
		setCanceledOnTouchOutside(false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mWindow.setContentView(R.layout.dialog_create);

		etName = (EditText) findViewById(R.id.etName);
		
		btnOK = (Button) findViewById(R.id.btnOK);
//		etStr = (EditText) findViewById(R.id.etStr);
//		etAgi = (EditText) findViewById(R.id.etAgi);
//		etBon = (EditText) findViewById(R.id.etBon);
//		etWxg = (EditText) findViewById(R.id.etWxg);
		
		
		btnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try{
					
//					int str = Integer.parseInt(etStr.getText().toString());
//					int agi = Integer.parseInt(etAgi.getText().toString());
//					int bon = Integer.parseInt(etBon.getText().toString());
//					int wxg = Integer.parseInt(etWxg.getText().toString());
//					
//					if(str>=10 && agi>=10 && bon>=10 && wxg>=10 && str<=30 && agi<=30 && bon<=30 && wxg<=30 && str+agi+bon+wxg <= 80)
//					{
						GmudWorld.mc = new MainChar();
						GmudWorld.mc.name = etName.getText().toString();
//						GmudWorld.mc.str = str;
//						GmudWorld.mc.agi = agi;
//						GmudWorld.mc.bon = bon;
//						GmudWorld.mc.wxg = wxg;
//						
//						Arrays.fill(GmudWorld.mc.skills,0);
//						Arrays.fill(GmudWorld.mc.skillsckd,-1);
//						Arrays.fill(GmudWorld.mc.inventory, 0);
//						GmudWorld.mc.inventory[42] = 1;
//						GmudWorld.mc.refreshItems();
//						
//						GmudWorld.mc.sex = str<15?1:0;
//						
//						GmudWorld.mc.looking = (int) (30 - str + Math.random() * (30 - wxg/2));
//						
//						GmudWorld.mc.food = 100;
//						GmudWorld.mc.drink = 100;
//						
						StartScreen.flag = true;
						
						cancel();
//					}
//					
					
//					new SavingScreen(GmudWorld.game).save();
					
				} catch(NumberFormatException e){
					e.printStackTrace();
				}
			}
		});	
	}
	
}
