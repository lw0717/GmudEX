/**
 * 安卓白金英雄坛制作组 <p>
 * 文件名：TalkingScreen.java <p>
 * 创建时间：2013-8-24 上午11:31:15 <p>
 * 所属项目：GmudTest <p>
 * @author 12548 <p>
 */
package lostland.gumd.platinum12548.ui;

import android.util.Log;
import lostland.gumd.platinum12548.GmudGame;
import lostland.gumd.platinum12548.GmudMap;
import lostland.gumd.platinum12548.GmudWorld;
import lostland.gumd.platinum12548.MainCharTile;
import lostland.gumd.platinum12548.MapScreen;
import lostland.gumd.platinum12548.battle.DummyWindow;
import lostland.gumd.platinum12548.blgframework.BasicScreen;
import lostland.gumd.platinum12548.blgframework.CScreen;
import lostland.gumd.platinum12548.blgframework.IGame;
import lostland.gumd.platinum12548.ui.core.DialogScreen;

/**
 * 类名：TalkingScreen <p>
 * 说明：
 * @author 12548
 */
public class TalkingScreen extends DialogScreen {

	public static CScreen ts;

	public static String text[][] =
		{{"这位客官也是来吃豆腐的么? ","我的豆腐又白又嫩,你不想尝尝? "},
		{"我最爱吃糖葫芦了,你喜欢吃吗? ","嘻嘻,昨天捡到个亮闪闪的东西,戴上去晕头转向,真好玩. "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"真奇怪,我条案上的肉不见了,真是急人! "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"客官买花吗? "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"高价收购,价格公道,童叟无欺 "},
		{"善恶常在一念之间,施主要好生把握. ","色即是空,空即是色. "},
		{"潘小莲的豆腐真好吃. ","每次去豆腐店,总要买上一斤二两. "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"听说有一个六芒星阵,可以任意转换时空. ","打开六芒星阵,需要六块三角石板. "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"挑件趁手的兵器吧 "},
		{"你也喜欢钓鱼吗,南郊有个鱼塘,里面有好多鱼呀! "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"人生得意须尽欢,莫使金樽空对月. "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"终日在江湖上混,身边总要备些伤药吧 "},
		{"又大又甜的糖葫芦呦 "},
		{"顾先生说过,寸金难买寸光阴呦. ","顾先生真有学问,光毛笔就一大堆,我昨天还捡了一支. ","人之初性本善...上大人孔已己...下面是... "},
		{"这位客官,想做身衣服吗? "},
		{"真是不巧,我的老花镜不见了,现在什么都做不了..唉! "},
		{"努力吧，干活儿去吧！"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"嘿嘿,没了我胡屠户,就只能吃混毛猪 "},
		{"这位客官进来喝杯茶歇歇腿吧! ","客官想来点什么,别看我们店小,东西可不少! "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"这些官没一个不贪的,几个小钱就能让他们比孙子还听话! "},
		{"居家过日子,买点杂货? "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"都是那个贼人,把我害成这个样子,我...我一直记得他! ","我家小爷的书缺了两页,所以总是说瞧不懂,那两页就在那贼人身上 "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"正宗的十八学士茶花,只有我们大理才有 "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"我家主人真是学识渊博,那天她吟诵的清心普善咒真是博大精深 ","真可惜,当时没有纸笔,没办法将那篇清心普善咒记录下来 "},
		{"我门尊主不但武功精深,文采更是不凡. ","我们四姐妹从小就跟随尊主,一直行影不离. "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"以前公子常吟诵“小红低唱我吹萧”,如今萧在人无,唉! ","这里大家都对我很好,可是我．．． "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"唉!来了这么多天也没能见到花讽院主,你说是不是我带的礼物太少了 "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"为了吃,为了吃,一天都在忙. ","我一顿能吃10个包子,是不是该申请急你死记录? ","师傅总是不给我吃饱,嘻嘻...我偷了他的... "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"天气寒冷,喝碗稣油茶暖暖身子吧 "},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"&$$^&^^(^("},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"$n打了个哈哈：今天的天气真是，哈哈。","我什么也都不知道，就算知道也不说，打死你我也不说。","$n看了你一眼，转身又忙自己的事情去了。","没看到我在忙吗？你还是找别人CHAT去吧。","$n睁大眼睛望着你，显然不知道你在说什么。"},
		{"小兔崽子儿,我看你是不想活了."}};

	int npcid = -1;
	TalkingWindow window;
	String s[];

	public static int m1npc = -1,m2itm = -1,m3npc = -1;
	public static boolean m1=false,m2=false,m3=false,mdone=false,pdone = false;
	public static int mpp = 0;
	public static final String spp[] = {"杀人","放火","挑水","劈柴","扫地"};
	public static int ex,ey;
	boolean alldead;

	public static int map;

	/**
	 * @param game
	 */
	public TalkingScreen(IGame game,int npcid) {
		super(game);
		Log.e("talking", "npc:"+npcid);
		this.border = new DummyWindow((GmudGame)game);
		this.npcid = npcid;
		Log.e("talking", "npc:"+npcid);
		switch(npcid)
		{
		case 2: //捕快
			if(GmudWorld.mc.fame < 128)
				window = new TalkingWindow((GmudGame) game,"你不是通缉犯吗？来人，给我拿下！", false);
			else if(GmudWorld.game.hunting)
			{
				window = new TalkingWindow((GmudGame) game,"在下不是请你去收服"+GmudWorld.npc[GmudWorld.npc.length-1].name+"吗？", false);
			}
			else if(GmudWorld.game.nextBadman<MapScreen.getGameTime() && !(GmudWorld.game.nextBadman == 0))
			{
				window = new TalkingWindow((GmudGame) game,"多谢，奸邪暂时已经除尽了，请休息一下吧！", false);
			}
			else
			{
				boolean ok = true;

				map = (int) (Math.random() * GmudWorld.map.length - 3);

				do
				{
					Log.e("恶人生成", "1");
					ok = true;
					ex = (int) (Math.random() * (GmudWorld.map[map].width - 2) + 1);
					ey = (int) (Math.random() * (GmudWorld.map[map].height - 2) + 1);
					for(int i = ex-1; i<=ex+1;i++)
					{
						for(int j = ey-1;j<=ey+1;j++)
						{
							if(GmudWorld.map[map].getEvent(i, j)>0 && GmudWorld.map[map].getWalkable(i, j)==GmudMap.MP_CHANGETO)
								ok = false;
						}
					}
					if(GmudWorld.map[map].getEvent(ex, ey) > 0 || GmudWorld.map[map].getWalkable(ex, ey) == GmudMap.MP_UNWALKABLE)
						ok = false;
				}while(!ok);
				Log.e("恶人生成", "2");
				GmudWorld.npc[GmudWorld.npc.length-1].sex = (int) (Math.random() * 2);
				String s1[] = new String[] {"赵","钱","孙","李","周","吴","郑","王"};
				String s2[][] = new String[][]{{"英","雄","豪","杰"},{"梅","兰","竹","菊"}};
				GmudWorld.npc[GmudWorld.npc.length-1].name = s1[(int) (Math.random() * s1.length)] +
						s2[GmudWorld.npc[GmudWorld.npc.length-1].sex][(int) (Math.random() * s2[GmudWorld.npc[GmudWorld.npc.length-1].sex].length)];
				Log.e("恶人生成", "3");
				GmudWorld.map[map].setEvent(ex, ey, GmudWorld.npc.length-1);
				GmudWorld.map[map].setWalkable(ex, ey, GmudMap.MP_NPC);
				Log.e("恶人生成", "4");
				GmudWorld.npc[GmudWorld.npc.length-1].faction = (int) (Math.random() * 6 +1);
				GmudWorld.npc[GmudWorld.npc.length-1].age = GmudWorld.mc.age + (int) (Math.random() * 6);
				int wp[][] = {{},{11,42},{35,42},{22,42},{11,42},{21,42},{35,42}};
				GmudWorld.npc[GmudWorld.npc.length-1].items = wp[GmudWorld.npc[GmudWorld.npc.length-1].faction];
				GmudWorld.npc[GmudWorld.npc.length-1].itemsckd = wp[GmudWorld.npc[GmudWorld.npc.length-1].faction];
				window = new TalkingWindow((GmudGame) game,"近有恶人["+GmudWorld.npc[GmudWorld.npc.length-1].name+
						"]在"+GmudMap.MAP_NAME[map]+"为非作歹，请速去为民除害！", false);
				GmudWorld.game.hunting = true;
			}
			break;
		case 5://村长
			if(mdone)
			{
				window = new TalkingWindow((GmudGame) game,"看你红光满面，还是先去找顾炎武领赏吧！", false);
			}
			else if(m1)
			{
				window = new TalkingWindow((GmudGame) game,"你完成了任务，去找顾炎武领赏吧！", false);
				m1 = false;
				m1npc = -1;
				mdone = true;
			}
			else if(m1npc == -1)
			{
				alldead = true;
				for(int i = 0; i<123; i++)
					if(!GmudWorld.npc[i].dead && i !=5 && i !=9 && i !=24 && i !=25 && i !=30)
						alldead = false;
				if(alldead)
				{
					window = new TalkingWindow((GmudGame) game,"什么？你把人都杀光了？I服了U！", false);
					return;
				}
				else do{
					m1npc = (int) (Math.random() * 123);
				}while(m1npc == 2 || m1npc == 5 || m1npc == 9 || m1npc ==25 || m1npc ==30 || m1npc == 24);
				window = new TalkingWindow((GmudGame) game,"请速去拜见"+GmudWorld.npc[m1npc].name+"!", false);
			}
			else
			{
				window = new TalkingWindow((GmudGame) game,"老夫不是说过请去拜见"+GmudWorld.npc[m1npc].name+"的吗!", false);
			}
			break;
		case 9://中年妇人
			if(mdone)
			{
				window = new TalkingWindow((GmudGame) game,"看你红光满面，还是先去找顾炎武领赏吧！", false);
			}
			else if(!m2)
			{
				m2itm = (int) (Math.random() * 76) + 1;
				m2 = true;
				window = new TalkingWindow((GmudGame) game,"今天妾身正准备请人去找"+GmudWorld.wp[m2itm].name+",能否帮个忙？", false);
			}
			else if(GmudWorld.mc.have(m2itm) && !(GmudWorld.mc.only(m2itm)))
			{
				GmudWorld.mc.drop(m2itm, 1);
				window = new TalkingWindow((GmudGame) game,"你完成了任务，去找顾炎武领赏吧！", false);
				m2 = false;
				mdone = true;
			}
			else
			{
				window = new TalkingWindow((GmudGame) game,"妾身还盼着您的"+GmudWorld.wp[m2itm].name+"呢!", false);
			}
			break;
		case 24://老婆婆
			if(GmudWorld.mc.exp>=5000)
			{
				window = new TalkingWindow((GmudGame) game,"唉！你也算小有名气了，老身使唤不动你了！", false);
			}
			else if(mpp == 0)
			{
				mpp = (int) (2 + Math.random() * 3);
				window = new TalkingWindow((GmudGame) game,"老身年事已高，有好心人帮老身"+spp[mpp]+"吗？", false);
			}
			else
			{
				window = new TalkingWindow((GmudGame) game,"老身吩咐你的事做完了吗？", false);
			}
			break;
		case 25://平一指
			if(GmudWorld.mc.fame >= 128)
				window = new TalkingWindow((GmudGame) game,"最讨厌伪君子了，马上从我眼前消失！", false);
			else if(mdone)
			{
				window = new TalkingWindow((GmudGame) game,"看你红光满面，还是先去找顾炎武领赏吧！", false);
			}
			else if(m3 && m3npc>-1)
			{
				if(GmudWorld.npc[m3npc].dead){
					window = new TalkingWindow((GmudGame) game,"你完成了任务，去找顾炎武领赏吧！", false);
					m3 = false;
					mdone = true; 
				}
				else
					window = new TalkingWindow((GmudGame) game,"老夫不是让你解决"+GmudWorld.npc[m3npc].name+"吗？", false);
			}
			else
			{
				alldead = true;
				for(int i = 0; i<123; i++){
					Log.w("PingYiZhi","testing:"+i);
					if((!GmudWorld.npc[i].dead) && i != 2 && i != 5 && i != 9 && i != 24 && i != 25 && i != 30)
						alldead = false;

				}
				if(alldead)
				{
					window = new TalkingWindow((GmudGame) game,"什么？你把人都杀光了？I服了U！", false);
				}
				else 
				{
					Log.w("PingYiZhi","not all dead");
					do{
						Log.w("PingYiZhi","Finding victim");
						m3npc = (int) (Math.random() * 123);
					}while(m3npc == 5 || m3npc == 9 || m3npc ==25 || m3npc ==30 || m3npc == 24 || GmudWorld.npc[m3npc].dead);
					Log.w("PingYiZhi","Victim found:" + m3npc);
					window = new TalkingWindow((GmudGame) game,"老夫夜观天象，"+GmudWorld.npc[m3npc].name+"阳寿已尽，你快去解决ta！", false);
					m3 = true;
				}
			}
			break;
		case 30://顾炎武
			if(mdone)
			{
				boolean jl = GmudWorld.rand.nextBoolean();
				if(jl)
				{
					int exp = (int) (Math.random()*GmudWorld.mc.getWxg()*4 + 50);
					window = new TalkingWindow((GmudGame) game,"你被奖励了："+exp+"经验", false);
					GmudWorld.mc.setExp(GmudWorld.mc.exp+exp);
				}
				else
				{
					int pot = (int) (Math.random()*GmudWorld.mc.getWxg()*2 + 20);
					window = new TalkingWindow((GmudGame) game,"你被奖励了："+pot+"潜能", false);
					GmudWorld.mc.setPotential(GmudWorld.mc.potential +pot);
				}
				mdone = false;
			}
			else
			{
				window = new TalkingWindow((GmudGame) game,"努力吧，干活儿去吧！", false);
			}

			break;

		default:


			if(m1npc == npcid)
			{
				window = new TalkingWindow((GmudGame) game,"我知道了，多谢来访！", false);
				m1npc = -1;
				m1 = true;
			}else{
				s = text[npcid];
				String t =s[(int) (Math.random()*s.length)];
				t=t.replace("$n", GmudWorld.npc[npcid].name);
				window = new TalkingWindow((GmudGame) game,t, false);
			}

		}
		
		BasicScreen.recheck();
		Log.e("talking", "npc:"+npcid);
	}

	public TalkingScreen(IGame game,String s,boolean splited)
	{
		super(game);
		this.border = new DummyWindow((GmudGame) game);
		this.npcid = -1;
		window = new TalkingWindow((GmudGame) game,s, splited);
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchDown(int, int)
	 */
	@Override
	protected void onTouchDown(int tx, int ty) {
		// 什么也不做

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchMove(int, int)
	 */
	@Override
	protected void onTouchMove(int tx, int ty) {
		// 什么也不做

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onTouchUp(int, int)
	 */
	@Override
	protected void onTouchUp(int tx, int ty) {
		//什么也不做

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onClick(int, int)
	 */
	@Override
	protected void onClick(int tx, int ty) {
		if(window.page<window.getPages()-1)
			window.page++;
		else
		{
			switch(npcid)
			{
			case 1: //小顽童
				game.setScreen(new ExchangeScreen(game,"太棒了,你用糖葫芦跟我换了吧! ",
						"你给我的糖葫芦真好吃. ",6,2,40));
				break;
			case 4://厨师
				game.setScreen(new ExchangeScreen(game,"我用这把祖传的宝刀换你身上的猪肉,你换吗? ",
						"真是谢谢你啊,我的祖传宝刀还好用吗? ",5,1,12));
				break;
			case 12://西门广
				game.setScreen(new ExchangeScreen(game,"太好了,我可以吃你的豆腐吗? ",
						"嘻嘻,穿上很漂亮吧. ",7,18,54));
				break;
			case 19://荷西
				game.setScreen(new ExchangeScreen(game,"这位客官身上的鱼可以送给小人吗? ",
						"我前天钓鱼钓上来一把破剑,留著也是无用,就送给客官了! ",74,12,33));
				break;
			case 29://老裁缝
				game.setScreen(new ExchangeScreen(game,"你身上的老花镜反正也用不到,送给他吗? ",
						"太感谢了,看你也是穷小子,这件精制布衣就送给你了! ",40,1,43));
				break;
			case 36://盐商
				game.setScreen(new ExchangeScreen(game,"老夫一生致力于炼丹,你身上的仙丹可否让我研究研究! ",
						"大恩不言谢,这件{金环锁子甲}乃赵云遗物,老夫就送给你了! ",10,1,62));
				break;
			case 46://平阿四
				game.setScreen(new ExchangeScreen(game,"你身上这两页纸好眼熟,能给我看看吗? ",
						"你看,加上这两页,就是一本书了! ",69,1,68));
				break;
			case 63://侍书
				game.setScreen(new ExchangeScreen(game,"你有笔,太好了,借我用一下吧! ",
						"我现在还记得清清楚楚,现在就默写下来给你 ",72,1,71));
				break;
			case 67://小红
				game.setScreen(new ExchangeScreen(game,"你身上的白玉箫是我家公子以前身上之物,可否送给我以作纪念 ",
						"门主送给我一把{红拂尘},我无意学武,就送给你了! ",29,1,28));
				break;
			case 89://食野太郎
				game.setScreen(new ExchangeScreen(game,"包子!我最爱吃的包子!不管那么多了,你给我包子,我就用这本书跟你换了! ",
						"你的包子真好吃,呵呵! ",2,10,70));
				break;

			default:
				if(ts==null)
					game.setScreen(GmudWorld.ms);
				else
				{
					game.setScreen(ts);
					ts = null;
				}
			}
		}

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.DialogScreen#onCancel()
	 */
	@Override
	public void onCancel() {
		// 什么也不做

	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		GmudWorld.mapTile.drawMap(GmudWorld.ms.map, GmudWorld.ms.X, GmudWorld.ms.Y);
		GmudWorld.cnm.draw(MainCharTile.currentDirection, GmudWorld.cnm.currentStep, MainCharTile.X, MainCharTile.Y);
		window.draw();
	}

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.blgframework.CScreen#pause()
	 */
	@Override
	public void pause() {

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

	/* （非 Javadoc）
	 * @see lostland.gumd.platinum12548.ui.core.DialogScreen#isStable()
	 */
	@Override
	public boolean isStable() {
		return false;

	}

}
