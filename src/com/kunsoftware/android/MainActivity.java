package com.kunsoftware.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
  
public class MainActivity extends Activity  {  
	
    private SlidingMenu menu;  
    
    private ViewPager mViewPager; 
	private ViewPager mViewPager2; 
	
	private	int pagerPosition = 0;
	
	private ImageView imageMenu;
	private LinearLayout linToolBar;
   
	private List<TextView> listViews = new ArrayList<TextView>(); // Tab页面列表
    private ImageView cursor;// 动画图片
    private ImageView cursor2;// 动画图片
    private TextView t1, t2, t3;// 页卡头标
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private int bmpW2;// 动画图片宽度
	
    private void InitTextView() {
    	t1 = (TextView) findViewById(R.id.text1);
    	t2 = (TextView) findViewById(R.id.text2);
 	  
	 	listViews.add(t1);
	 	listViews.add(t2); 
 
		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1)); 
    }
    
    /** 初始化动画 */
    private void InitImageView() { 

    	cursor = (ImageView) findViewById(R.id.cursor); 
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;// 获取分辨率宽度
        
        bmpW = screenWidth / 2;
        bmpW2 = screenWidth / 4;
        
        LayoutParams cursorPara = cursor.getLayoutParams();
        cursorPara.width = bmpW; 
        cursor.setLayoutParams(cursorPara);  
         
    }
    
    /**
     * 头标点击监听
*/
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
        	mViewPager.setCurrentItem(index);
        }
    };
    
    public class MyOnClickListener2 implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener2(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
        	mViewPager2.setCurrentItem(index);
        }
    };
    
	@Override
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
                     
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);  
        //设置标题  
        setTitle("Attach");  
  
        //初始化滑动菜单  
        initSlidingMenu();  
        //Toast.makeText(getApplicationContext(), "Deleted Successfully!" + px2dip(getApplicationContext(),90), Toast.LENGTH_LONG).show();          
    }  
	 
	public void listView(View view) {
		
		ListView list = (ListView) view.findViewById(R.id.MyListView);  
	      
	    //生成动态数组，并且转载数据  
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  
	    for(int i=0;i<30;i++)  {  
	        HashMap<String, String> map = new HashMap<String, String>();  
	        map.put("ItemTitle", "张杰");  
	        map.put("ItemText", "广州大区-销售员");  
	        mylist.add(map);  
	    }  
	    //生成适配器，数组===》ListItem  
	    SimpleAdapter mSchedule = new SimpleAdapter(this, //没什么解释  
	                                                mylist,//数据来源   
	                                                R.layout.my_listitem,//ListItem的XML实现  
	                                                  
	                                                //动态数组与ListItem对应的子项          
	                                                new String[] {"ItemTitle", "ItemText"},   
	                                                  
	                                                //ListItem的XML文件里面的两个TextView ID  
	                                                new int[] {R.id.ItemTitle,R.id.ItemText});  
	    //添加并且显示  
	    list.setAdapter(mSchedule); 
	}
  
    /** 
     * 初始化滑动菜单 
     */  
    private void initSlidingMenu() {  
    	  
        // 设置主界面视图  
        setContentView(R.layout.content_frame);  
        
        InitTextView();
        InitImageView();       
        
        mViewPager = (ViewPager)findViewById(R.id.viewpager); 
        imageMenu = (ImageView)findViewById(R.id.imageMenu);
        
        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.view1, null);
        View view2 = mLi.inflate(R.layout.view2, null);
        View view3 = mLi.inflate(R.layout.view3, null);
        View view4 = mLi.inflate(R.layout.view4, null);
        View view5 = mLi.inflate(R.layout.view3, null);
        View view6 = mLi.inflate(R.layout.view3, null);
        listView(view3);
        listView(view5);
        listView(view6);
        
        cursor2 = (ImageView) view1.findViewById(R.id.cursor1); 
        mViewPager2 = (ViewPager)view1.findViewById(R.id.viewpager);
        LayoutParams cursorPara = cursor2.getLayoutParams();
        cursorPara.width = bmpW2; 
        cursor2.setLayoutParams(cursorPara);  
        
        TextView rizhi = (TextView)view1.findViewById(R.id.text1);
        TextView t11 = (TextView) view1.findViewById(R.id.text11);
        TextView t21 = (TextView) view1.findViewById(R.id.text21);
        TextView t31 = (TextView) view1.findViewById(R.id.text31);
        TextView t41 = (TextView) view1.findViewById(R.id.text41);
        
        t11.setOnClickListener(new MyOnClickListener2(0));
        t21.setOnClickListener(new MyOnClickListener2(1));
        t31.setOnClickListener(new MyOnClickListener2(2));
        t41.setOnClickListener(new MyOnClickListener2(3));
       
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2); 
        
        final ArrayList<View> views2 = new ArrayList<View>();
        views2.add(view3);
        views2.add(view4); 
        views2.add(view5); 
        views2.add(view6); 
      
        /** 填充ViewPager的数据适配器 */
        PagerAdapter mPagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views.get(position));
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return "";
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		
		PagerAdapter mPagerAdapter2 = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return views2.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views2.get(position));
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return "";
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views2.get(position));
				return views2.get(position);
			}
		}; 
		
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager2.setAdapter(mPagerAdapter2);
		 
		
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {  
		  
	        @Override  
	        public void onPageSelected(int position) {  
	            /** 
	             *  setSelectedNavigationItem 方法用于设置ActionBar的聚焦tab .   
	             *  在接下来我们判断了SLidingMenu的手势力模式， 
	             *  如果ViewPager已经滑到了最左边，则我们把手势设置成全屏的， 
	             *  这样更往左滑动的时候，就会打开Menu . 
	             */ 	        	
	            switch (position) {  
	                //滑到最左邊頁卡設置SlidingMenu滑動手勢可全屏即右滑出菜單  
	                case 0:  
	                	if(pagerPosition == 0 ) {
	                		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); 
	                	} else {
	                		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);  
	                	} 
	                	 
	                    break;      
	                default:  
	                	menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);  
	                    break;  
	            } 
	            
	            int from = bmpW  * currIndex;
	            int to = bmpW * position;
		            
	            Animation animation = null;
	            animation = new TranslateAnimation(from, to, 0, 0);
	            animation.setFillAfter(true);// True:图片停在动画结束位置
	            animation.setDuration(300);
		            
	            cursor.startAnimation(animation);
	            currIndex = position;
	            
	            
	        }  
	  
	    });
		
		mViewPager2.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {  
			
			private int currIndex2;
	        @Override  
	        public void onPageSelected(int position) {  
	        	
	        	
	            /** 
	             *  setSelectedNavigationItem 方法用于设置ActionBar的聚焦tab .   
	             *  在接下来我们判断了SLidingMenu的手势力模式， 
	             *  如果ViewPager已经滑到了最左边，则我们把手势设置成全屏的， 
	             *  这样更往左滑动的时候，就会打开Menu . 
	             */  
	        	pagerPosition = position;
	            switch (position) {  
	                //滑到最左邊頁卡設置SlidingMenu滑動手勢可全屏即右滑出菜單  
	                case 0:  
	                	menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);  
	                    break;  
	                default:  
	                	menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);  
	                    break;  
	            }  
	            
	           int from = bmpW2  * currIndex2;
	           int to = bmpW2 * position;
	            
	           Animation animation = null;
	           animation = new TranslateAnimation(from, to, 0, 0);
	           animation.setFillAfter(true);// True:图片停在动画结束位置
	           animation.setDuration(300);
		            
		            
	           cursor2.startAnimation(animation);
		            
	           currIndex2 = position;
	        }  
	  
	    }); 
        
        // 设置滑动菜单的属性值  
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_frame);  
        Button button = (Button)menu.getMenu().findViewById(R.id.button01);
        button.setText("测试"); 
        
        imageMenu.setOnClickListener(new Button.OnClickListener(){ 
            public void onClick(View v)
            {
                menu.showMenu(); 
            }
        }); 
        
        
        
        rizhi.setOnClickListener(new Button.OnClickListener(){ 
            public void onClick(View v)
            {
//            	Dialog dialog = new AlertDialog.Builder(getApplicationContext()).create();
//            	dialog.setTitle("ok");
//            	dialog.setMessage("ok");
//            	dialog.show();
//            	new AlertDialog.Builder(MainActivity.this).setTitle("复选框").setMultiChoiceItems(
//            			  new String[] { "Item1", "Item2" }, null, null)
//            			 .setPositiveButton("确定", null)
//            			 .setNegativeButton("取消", null).show();
            	
            	CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);
        		builder.setMessage("这个就是自定义的提示框");
        		builder.setTitle("提示");
        		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int which) {
        				dialog.dismiss();
        				//设置你的操作事项
        			}
        		});

        		builder.setNegativeButton("取消",
        				new android.content.DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog, int which) {
        						dialog.dismiss();
        					}
        				});

        		builder.create().show();

            }
        }); 
    }  
      
    @Override  
    public void onBackPressed() {  
        //点击返回键关闭滑动菜单  
        if (menu.isMenuShowing()) {  
            menu.showContent();  
        } else {  
            super.onBackPressed();  
        }  
    }  
  
}  
