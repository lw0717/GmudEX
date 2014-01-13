/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：LoadingScreen.java <p>
 * 创建时间：2013-5-20 下午4:34:51 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lostland.gumd.platinum12548.battle.BattleScreen;
import lostland.gumd.platinum12548.blgframework.BasicScreen;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.blgframework.IGraphics;
import lostland.gumd.platinum12548.blgframework.IGraphics.PixmapFormat;
import lostland.gumd.platinum12548.blgframework.impl.BLGFileIO;
import lostland.gumd.platinum12548.blgframework.impl.BLGGame;
import lostland.gumd.platinum12548.blgframework.impl.SingleTouchHandler;
import lostland.gumd.platinum12548.data.Gesture;
import lostland.gumd.platinum12548.data.Item;
import lostland.gumd.platinum12548.data.MainChar;
import lostland.gumd.platinum12548.data.Npc;
import lostland.gumd.platinum12548.data.Skill;
import lostland.gumd.platinum12548.ui.MainMenuScreen;
import lostland.gumd.platinum12548.ui.StartScreen;
import lostland.gumd.platinum12548.ui.TalkingScreen;
import lostland.gumd.platinum12548.ui.core.NewButton;
import android.content.SharedPreferences;
import android.util.Log;



/**
 * 类名：LoadingScreen <p>
 * 说明：
 * @author 12548
 */
public class LoadingScreen extends CScreen {

	boolean b=false;
	/**
	 * @param game
	 */
	public LoadingScreen(IGame game) {
		super(game);
	}



	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		
		Log.e("Loading", "Loading Start");
		BasicScreen.b = false;

		BLGFileIO f=(BLGFileIO) game.getFileIO();
		IGraphics g=game.getGraphics();

		if(!b)
		{
			Assets.loading = g.newPixmap("loading.png", PixmapFormat.RGB565);
			return;
		}
		
		loadAssets();
		
		//		AssetManager mgr = ((Context)game).getAssets();
		//		Assets.songti = Typeface.createFromAsset(mgr, "simhei.ttf");
		GmudWorld.cnm=new MainCharTile((GmudGame) game);
		GmudWorld.mapTile=new MapTile((GmudGame) game);
		GmudWorld.npcc=new GmudNPC((GmudGame) game);
		GmudWorld.skill = new Skill[Skill.SKILL_COUNT];
		Skill.init();
		GmudWorld.zs = new Gesture[220];
		Gesture.init();
		GmudWorld.wp = new Item[82];
		Item.init();
		

		
		///////////////////////////////////////////////////
		TalkingScreen.m1npc = -1;
		TalkingScreen.m2itm = -1;
		TalkingScreen.m3npc = -1;
		
		TalkingScreen.mpp = 0;
//		TalkingScreen.nextBadman = 0f;
//		
//		
//		TalkingScreen.hunting = false;
		TalkingScreen.m1 = false;
		TalkingScreen.m2 = false;
		TalkingScreen.m3 = false;
		TalkingScreen.mdone = false;
		TalkingScreen.pdone = false;
		
		
		/////////////////////////////////////////////////




		



//		for(int i=0;i<7;i++)
//			for(int j:GameConstants.master_faction[i])
//			{
//				GmudWorld.npc[j].faction = i;
//			}
		

		Log.i("Loading Screen", "Loading");

//		for(int i=0;i<GmudWorld.npc.length-1;i++)
//			if(GmudWorld.wp[GmudWorld.npc[i].items[0]].kind!=2)
//				GmudWorld.npc[i].items=GmudWorld.push_top(GmudWorld.npc[i].items,0);

		GmudWorld.game.newint = new int[1000];
		GmudWorld.game.newbool = new boolean[200];
		
		
		
		Log.i("Loading Screen", "Loadingb");
		GmudWorld.mms = new MainMenuScreen(game);
		Log.i("Loading Screen", "Loadingb");
		GmudWorld.ms = new MapScreen(game);
		Log.i("Loading Screen", "Loadingb");
		GmudWorld.bs = new BattleScreen(game);

		Log.i("Loading Screen", "Loadingb");

		SharedPreferences sp = f.getPreferences();
		MapScreen.zlEnabled = sp.getBoolean("zlgyxz", false);
//		MapScreen.btnsEnabled = sp.getBoolean("dtajcz", true);
		GmudGame.classickeymap = sp.getBoolean("标准键位", false);
		NewButton.menubutton = sp.getBoolean("显示菜单键", true);
		GmudGame.backkeyquit = sp.getBoolean("返回键退出", true);

		if(sp.getBoolean("newgame", true) || (sp.getInt("SaveFileVersion", 0) != 2))
		{
			Log.e("Load", "New Got");
			game.setScreen(new StartScreen(game));
		}
		else
		{
			try{
				loadSavefile();
//				if(BLGGame.exit)
				{
					BLGGame.exit = false;
//					GmudWorld.mc.itemsckd = new int[]{0};
//					GmudWorld.mc.skillsckd = new int[]{-1,-1,-1,-1,-1};

					GmudWorld.ms.X = 0;
					GmudWorld.ms.Y = 1;
					MainCharTile.X=4;
					MainCharTile.Y=3;
					MainCharTile.currentDirection= Direction.DOWN;
					GmudWorld.ms.map = GmudWorld.map[0];
				}
			}finally{
				game.setScreen(GmudWorld.ms);
			}
			Log.i("Loading Screen", "Done Loading");
		}

	}

	@SuppressWarnings("unused")
	private int ReadInt16Signed(InputStream is)
	{
		int result = 0;
		result = ReadInt(2, is);
		if(result > 0x7FFF)
			result -= 0x10000;

		return result;
	}
	private int ReadInt(int byteCnt,InputStream is)
	{
		int result = 0;
		for(int i = 0; i < byteCnt; i++)
		{
			try {
				result |= ((is.read() & 0xFF) << (i << 3));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return result;
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {

		IGraphics g = game.getGraphics();

		g.drawPixmap(Assets.loading, 0, 0);

		b=true;

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#pause()
	 */
	@Override
	public void pause() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#resume()
	 */
	@Override
	public void resume() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#dispose()
	 */
	@Override
	public void dispose() {
		// TODO 自动生成的方法存根

	}


	public void loadAssets()
	{
		IGraphics g=game.getGraphics();
		
		Assets.maincharTile=g.newPixmap("mainchartile.png", PixmapFormat.ARGB8888);
		Assets.girl=g.newPixmap("girl.png", PixmapFormat.ARGB8888);
		Assets.mapTile=g.newPixmap("gmudtile.png", PixmapFormat.ARGB8888);
		Assets.arrow=g.newPixmap("arrow.png", PixmapFormat.ARGB8888);
		Assets.empty=g.newPixmap("empty.png", PixmapFormat.ARGB8888);
		Assets.filled=g.newPixmap("filled.png", PixmapFormat.ARGB8888);
		Assets.checked=g.newPixmap("checked.png", PixmapFormat.ARGB8888);
		Assets.vs=g.newPixmap("vs.png", PixmapFormat.ARGB8888);
		Assets.hpfp=g.newPixmap("hpfp.png", PixmapFormat.ARGB8888);
		Assets.boom=g.newPixmap("boom.png", PixmapFormat.ARGB8888);

		Assets.left=g.newPixmap("left.png", PixmapFormat.ARGB8888);
		Assets.right=g.newPixmap("right.png", PixmapFormat.ARGB8888);
		
		Assets.nbup=g.newPixmap("nbup.png", PixmapFormat.ARGB8888);
		Assets.nbdown=g.newPixmap("nbdown.png", PixmapFormat.ARGB8888);
		Assets.nbleft=g.newPixmap("nbleft.png", PixmapFormat.ARGB8888);
		Assets.nbright=g.newPixmap("nbright.png", PixmapFormat.ARGB8888);
		Assets.nbenter=g.newPixmap("nbenter.png", PixmapFormat.ARGB8888);
		Assets.nbback=g.newPixmap("nbback.png", PixmapFormat.ARGB8888);
		Assets.nbmenu=g.newPixmap("nbmenu.png", PixmapFormat.ARGB8888);
		
		final int Lasc = 3072;// Added by 教头20130729
		final int L1 = 182736;
		final int L2 = 243648;
		
		Assets.ascii12 = new byte[Lasc];// Added by 教头20130729
		Assets.charGBK12 = new byte[L1];
		Assets.charGBK16 = new byte[L2];
		
		BLGFileIO f=(BLGFileIO) game.getFileIO();
		InputStream is = null;
		try {
			is = f.readAsset("ascii.bin");// Added by 教头20130729
			is.read(Assets.ascii12);// Added by 教头20130729
			is = f.readAsset("gb12.bin");
			is.read(Assets.charGBK12);
			is = f.readAsset("gb16.bin");
			is.read(Assets.charGBK16);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

		
		loadMaps();
		loadNpcs();
	}
	
	
	public void loadMaps()
	{
		BLGFileIO f=(BLGFileIO) game.getFileIO();
		InputStream is = null;
		
		final int MAP_COUNT=48;
		GmudWorld.map=new GmudMap[MAP_COUNT];
		try {
			int i,j,k,l;
			for(i=0;i<MAP_COUNT;i++)
			{
				is=f.readAsset(i+".mapx");
				int width,height;
				width=is.read();
				height=is.read();


				GmudWorld.map[i]=new GmudMap(width, height,i);
				for(j=0;j<3;j++)
					for(k=0;k<height;k++)
						for(l=0;l<width;l++)
						{
							int tx=is.read();
							int ty=is.read();
							GmudWorld.map[i].setPoint(j, l, k, tx, ty);
						}

				is=f.readAsset(i+".trg");
				width=is.read();
				height=is.read();
				for(k=0;k<height;k++)
					for(l=0;l<width;l++)
					{
						int b1=is.read();
						int b2=is.read();
						int b3=is.read();
						int b4=is.read();

						if((b1&2)  > 0)
						{
							GmudWorld.map[i].setWalkable(l, k, GmudMap.MP_CHANGETO);
							GmudWorld.map[i].setEvent(l, k, b2*1000000+b3*1000+b4);
						}
						else if((b1&1)>0)
						{
							GmudWorld.map[i].setWalkable(l, k, GmudMap.MP_WALKABLE);
							if((b2&1)>0)
							{
								GmudWorld.map[i].setWalkable(l, k, GmudMap.MP_NPC);
								GmudWorld.map[i].setEvent(l, k, b3);
							}
						}
						else
						{
							if(b2==0)
								GmudWorld.map[i].setWalkable(l, k, GmudMap.MP_UNWALKABLE);
							else
							{
								if((b2&1)>0)
									GmudWorld.map[i].setWalkable(l, k, GmudMap.MP_NPC);
								else
									GmudWorld.map[i].setWalkable(l, k, GmudMap.MP_EVENT);
								GmudWorld.map[i].setEvent(l, k, b3);
							}
						}
					}
				is = null;
			}

		} catch (IOException e) {
			Log.e("Loading Screen", "map loading error!");
			e.printStackTrace();
		}

	}
	
	
	public void loadNpcs()
	{
		
		InputStream is = null;
		BLGFileIO f=(BLGFileIO) game.getFileIO();
		
		// Block added by 教头 0730
		// Read NPC data
		try {
			is = f.readAsset("npc.dat");
			is.skip(12);//skip useless bytes "白金英雄坛说"
			int npcCnt = ReadInt(4, is);
			is.skip(npcCnt * 4);// 索引区

			GmudWorld.npc = new Npc[npcCnt+1+5];

			byte[] buffer = new byte[400];
			Npc t_npc = null;
			for (int ptr_npc = 0; ptr_npc < npcCnt; ptr_npc++)
			{
				t_npc = new Npc();
				t_npc.id = ptr_npc;

				// readName
				int tmp;
				for (tmp = 0; tmp < buffer.length; tmp++)
				{
					buffer[tmp] = (byte)(is.read());
					if(buffer[tmp] == 0)
						break;
				}
				byte[] nameBuffer = new byte[tmp];//tmp最后多了个0

				for (int it = 0; it < nameBuffer.length; it++) {
					nameBuffer[it] = buffer[it];
				}
				//  NAME...
				t_npc.name = new String(nameBuffer, "GBK");
				t_npc.sex = is.read();
				t_npc.age = (is.read() & 0xFF);

				t_npc.fame = ReadInt(4, is);

				if(t_npc.fame>128)t_npc.fame=256-t_npc.fame;

				t_npc.hit = ReadInt(2, is);
				t_npc.evd = ReadInt(2, is);
				t_npc.atk = ReadInt(2, is);
				t_npc.def = ReadInt(2, is);
				t_npc.exp = ReadInt(4, is);
				t_npc.ads = ReadInt(2, is);
				t_npc.str = ReadInt(2, is);
				t_npc.agi = ReadInt(2, is);
				t_npc.wxg = ReadInt(2, is);
				t_npc.bon = ReadInt(2, is);
				t_npc.sp = ReadInt(2, is);
				t_npc.hp = ReadInt(2, is);
				t_npc.fp = ReadInt(2, is);
				t_npc.maxfp = ReadInt(2, is);

				t_npc.maxhp = ReadInt(2, is);

				//				is.skip(2);// cuz you don't have HpUpperLimit for npc Class..

				t_npc.gold = ReadInt(2, is);
				//				t_npc.saying = ReadInt(2, is);

				is.skip(2);

				int itemCnt = ReadInt(2, is);
				int[] items = new int[itemCnt];
				for (int it = 0; it < itemCnt; it++) 
				{
					items[it] = ReadInt(2, is);
				}
				int equipCnt = ReadInt(2, is);
				int[] equips = new int[equipCnt];
				for (int it = 0; it < equipCnt; it++)
				{
					equips[it] = ReadInt(2, is);
				}

				t_npc.itemsckd = equips;

				if(equipCnt == 0)
				{
					t_npc.itemsckd = new int[]{0};
				}


				t_npc.items = new int[itemCnt + equipCnt];
				for (int it = 0; it < itemCnt + equipCnt; it++)
				{
					if(it < itemCnt)
						t_npc.items[it] = items[it];
					else
						t_npc.items[it] = equips[it - itemCnt];
				}

				int skillCnt = ReadInt(2, is);
				int[] skillIDs = new int[skillCnt],skillLvls = new int[skillCnt];
				t_npc.skills = new int[Skill.SKILL_COUNT];
				for(int i = 0; i< Skill.SKILL_COUNT;i++)
				{
					t_npc.skills[i] = 0;
				}

				for (int it = 0; it < skillCnt; it++)
				{
					skillIDs[it] = ReadInt(2, is);
					skillLvls[it] = ReadInt(2, is);
					t_npc.skills[skillIDs[it]] = skillLvls[it];  //酱紫不就好了嘛！
				}

				t_npc.skillsckd = new int[5];
				for (int it = 0; it < 5; it++)
				{
					t_npc.skillsckd[it] = ReadInt(2, is);
					t_npc.skillsckd[it] = t_npc.skillsckd[it] != 65535 ? t_npc.skillsckd[it] : -1;
				}

				int goodsCnt = ReadInt(2, is);
				if(goodsCnt>0)t_npc.trading = 1;
				int[] goods = new int[goodsCnt];
				for(int it = 0; it < goodsCnt; it++)
				{
					goods[it] = ReadInt(2, is);
				}

				t_npc.sells = goods;
				//TODO:想办法变成 trading ID...

				// finally .. desc
				for (tmp = 0; tmp < buffer.length; tmp++)
				{
					buffer[tmp] = (byte)(is.read() & 0xFF);
					if(buffer[tmp] == 0)
						break;
				}
				byte[] descBuffer = new byte[tmp];//tmp最后多了个0

				for (int it = 0; it < descBuffer.length; it++) {
					descBuffer[it] = buffer[it];
				}
				//  终了
				t_npc.des = new String(descBuffer, "GBK");

				GmudWorld.npc[ptr_npc] = t_npc;
			}
			t_npc = null;

			for(int itmid=0;itmid<100;itmid++)
			{
				switch(itmid)
				{
				case 68: // 拳经
					push(250,1, itmid);
					push(200,40, itmid);
					break;
				case 69: // 焦黄纸页
					push(250,8, itmid);
					push(250,7, itmid);
					break;
				case 70:
					push(250,3, itmid);
					push(200,29, itmid);
					break;
				case 71:
					push(250,0, itmid);
					push(250,20, itmid);
					break;
				case 81:
					push(250,10, itmid);
					push(100,41, itmid);
					break;
				default:
					break;
				}

			}
			
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		Log.i("Loading Screen", "Loading");

		GmudWorld.npc[GmudWorld.npc.length-1] = new Npc();
		GmudWorld.npc[30].qingjiaoable = true;
		GmudWorld.npc[30].trading = 101;
		GmudWorld.npc[6].qingjiaoable = true;
		GmudWorld.npc[6].trading = 101;
		GmudWorld.npc[10].trading = 2;


		for(int i:GmudWorld.teachers)
			GmudWorld.npc[i].trading = 101;
		
		for(int i=1;i<7;i++)
			for(int j:GmudWorld.t_faction[i])
				GmudWorld.npc[j].faction = i;
		
	}
	
	
	
	public void loadSavefile(){


		
		BLGFileIO f = (BLGFileIO) game.getFileIO();

		Log.i("Loading Screen", "Loading file");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(f.readFile("gmudp12548")));
			Log.i("Loading Screen", "Loading file");
			GmudWorld.mc = new MainChar();
			GmudWorld.mc.name = in.readLine();

			Log.i("Loading Screen", "Name loaded:"+GmudWorld.mc.name);


			Log.i("Loading Screen", "Loading file");

			GmudWorld.mc.ads = Integer.parseInt(in.readLine());
			GmudWorld.mc.age = Integer.parseInt(in.readLine());
			GmudWorld.mc.month = Integer.parseInt(in.readLine());
			GmudWorld.mc.agi = Integer.parseInt(in.readLine());
			GmudWorld.mc.atk = Integer.parseInt(in.readLine());
			GmudWorld.mc.bon = Integer.parseInt(in.readLine());
			GmudWorld.mc.def = Integer.parseInt(in.readLine());
			GmudWorld.mc.drink = Integer.parseInt(in.readLine());
			GmudWorld.mc.evd = Integer.parseInt(in.readLine());
			GmudWorld.mc.setExp(Integer.parseInt(in.readLine()));
			GmudWorld.mc.faction = Integer.parseInt(in.readLine());
			GmudWorld.mc.fame = Integer.parseInt(in.readLine());
			GmudWorld.mc.food = Integer.parseInt(in.readLine());
			GmudWorld.mc.fp = Integer.parseInt(in.readLine());
			GmudWorld.mc.gold = Integer.parseInt(in.readLine());
			GmudWorld.mc.hit = Integer.parseInt(in.readLine());
			GmudWorld.mc.hp = Integer.parseInt(in.readLine());
			GmudWorld.mc.looking = Integer.parseInt(in.readLine());
			GmudWorld.mc.maxfp = Integer.parseInt(in.readLine());
			GmudWorld.mc.maxhp = Integer.parseInt(in.readLine());
			GmudWorld.mc.setPotential(Integer.parseInt(in.readLine()));
			GmudWorld.mc.sex = Integer.parseInt(in.readLine());
			GmudWorld.mc.sp = Integer.parseInt(in.readLine());
			GmudWorld.mc.str = Integer.parseInt(in.readLine());
			GmudWorld.mc.wxg = Integer.parseInt(in.readLine());

			Log.i("Loading Screen", "Loading file");

			int i;

			for(i=0;i<6;i++)
			{
				((GmudGame)game).hasstone[i] = Boolean.parseBoolean(in.readLine());
			}

			for(i=0;i<GmudWorld.npc.length;i++)
			{
				GmudWorld.npc[i].qingjiaoable = Boolean.parseBoolean(in.readLine());
				GmudWorld.npc[i].dead = Boolean.parseBoolean(in.readLine());
			}


			for(i=0;i<GmudWorld.mc.skills.length;i++)
			{
				GmudWorld.mc.skills[i] = Integer.parseInt(in.readLine());
			}

			GmudWorld.mc.skillsckd = new int[ Integer.parseInt(in.readLine()) ];
			
			Log.w("Loading",GmudWorld.mc.skillsckd.length+" skills checked");
			for(i=0;i<GmudWorld.mc.skillsckd.length;i++)
			{
				GmudWorld.mc.skillsckd[i] = Integer.parseInt(in.readLine());
			}
			
			for(i=0;i<GmudWorld.mc.learning.length;i++)
			{
				GmudWorld.mc.learning[i] = Integer.parseInt(in.readLine());
			}

			for(i=0;i<GmudWorld.mc.inventory.length;i++)
			{
				GmudWorld.mc.inventory[i] = Integer.parseInt(in.readLine());
			}

			GmudWorld.mc.itemsckd = new int[ Integer.parseInt(in.readLine()) ];
			Log.w("Loading",GmudWorld.mc.itemsckd.length+" items checked");
			for(i=0;i<GmudWorld.mc.itemsckd.length;i++)
			{
				GmudWorld.mc.itemsckd[i] = Integer.parseInt(in.readLine());
			}
			
			

			GmudWorld.mc.refreshItems();

			
			for(i=0;i<GmudWorld.game.newint.length;i++)
			{
				GmudWorld.game.newint[i] = Integer.parseInt(in.readLine());
			}
			
			
			for(i=0;i<GmudWorld.game.newbool.length;i++)
			{
				GmudWorld.game.newbool[i] = Boolean.parseBoolean(in.readLine());
			}


			chksum = Long.parseLong(in.readLine());
			
			Log.i("Loading Screen", "Loading file");

			if(!BasicScreen.check())
			{
				SharedPreferences sp = f.getPreferences();
				while(!sp.edit().putBoolean("newgame", true).commit())
					;
				game.oo();
				Log.e("Loading", "存档校验失败");
			}

			Log.i("Loading Screen", "File loaded");

		} catch (IOException e) {
			Log.e("Loading","存档载入错误！");
			SingleTouchHandler.flag = 1000;
			SharedPreferences sp = f.getPreferences();
			while(!sp.edit().putBoolean("newgame", true).commit())
				;
			game.oo();
			e.printStackTrace();
		}
		catch(NumberFormatException e)
		{
			Log.e("Loading","存档格式错误！");
			SharedPreferences sp = f.getPreferences();
			while(!sp.edit().putBoolean("newgame", true).commit())
				;
			game.oo();
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		
		
		
	}
	
	public void push(int lvl,int i,int itmid)
	{	
		if(GmudWorld.npc[Item.getitmnpc(itmid)] == null)
			GmudWorld.npc[Item.getitmnpc(itmid)] = new Npc();
		GmudWorld.npc[Item.getitmnpc(itmid)].trading = 102;
		if(GmudWorld.npc[Item.getitmnpc(itmid)].skills == null)
			GmudWorld.npc[Item.getitmnpc(itmid)].skills = new int[Skill.SKILL_COUNT];
		GmudWorld.npc[Item.getitmnpc(itmid)].skills[i] = lvl;
	}
}
