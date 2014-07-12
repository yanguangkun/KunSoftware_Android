package com.kunsoftware.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
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
import android.widget.Toast;

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
 	//t3 = (TextView) findViewById(R.id.text3);
 	listViews.add(t1);
 	listViews.add(t2);
 	//listViews.add(t3);
 
	t1.setOnClickListener(new MyOnClickListener(0));
	t2.setOnClickListener(new MyOnClickListener(1));
	//t3.setOnClickListener(new MyOnClickListener(2));
	
//	t1.measure(0,0);  
//    //获取组件宽度  
//    int width = t1.getMeasuredWidth();  
//    //获取组件高度  
//    int height = t1.getMeasuredHeight(); 
//    System.out.println(height);
    
//    int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
//    int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
//    t1.measure(w, h); 
//    int height1 =t1.getMeasuredHeight(); 
//    int width1 =t1.getMeasuredWidth(); 
    
//    LayoutParams t1Para = t1.getLayoutParams();
//    t1Para.height = height1; 
//    t1Para.width = width1;
//    t1.setLayoutParams(t1Para); 
//   
//    Toast.makeText(getApplicationContext(), "\n"+height+","+width, Toast.LENGTH_LONG).show();  
//    
//    ViewTreeObserver vto = t1.getViewTreeObserver();  
//    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {  
//        public boolean onPreDraw() {  
//            int height = t1.getMeasuredHeight();  
//            int width = t1.getMeasuredWidth();  
//          
//            Toast.makeText(getApplicationContext(), "\n"+height+","+width, Toast.LENGTH_LONG).show();  
//            return true;  
//        }  
//    });  
    
    }
    
    /**
     * 初始化动画
*/
    private void InitImageView() {
    	
    	   
        
       
        

        cursor = (ImageView) findViewById(R.id.cursor); 
        
       // BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        
        bmpW = screenW / 2;
        bmpW2 = screenW / 4;
        offset = (screenW / 2 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// 设置动画初始位置
        
        int pix = getResources().getDimensionPixelSize(R.dimen.bar_height);
        
        
        LayoutParams cursorPara = cursor.getLayoutParams();
        cursorPara.width = screenW / 2; 
        cursor.setLayoutParams(cursorPara);  
        
        System.out.println(pix);
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
        Toast.makeText(getApplicationContext(), "Deleted Successfully!" + px2dip(getApplicationContext(),90), Toast.LENGTH_LONG).show();  
        
    }  
	
	public static int px2dip(Context context, float pxValue){ 
        final float scale = context.getResources().getDisplayMetrics().density; 
        return (int)(pxValue / scale + 0.5f); 
	} 
	
	public void listView(View view3) {
		ListView list = (ListView) view3.findViewById(R.id.MyListView);  
	      
	    //生成动态数组，并且转载数据  
	    ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  
	    for(int i=0;i<30;i++)  
	    {  
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
        linToolBar = (LinearLayout)findViewById(R.id.linToolBar);
        LayoutParams para = linToolBar.getLayoutParams();
        Toast.makeText(getApplicationContext(), "height=" + para.height, Toast.LENGTH_LONG).show();  
        
        linToolBar.measure(0,0);  
      //获取组件宽度  
      int width = linToolBar.getMeasuredWidth();  
      //获取组件高度  
      int height = linToolBar.getMeasuredHeight(); 
       
//        TextView textView = new TextView(this);
//        textView.setText("tab1"); 
//        textView.setPadding(20, 0, 20, 0);
//        
//        tabLine.addView(textView);
//        
//        textView = new TextView(this);
//        textView.setText("tab2");
//        tabLine.addView(textView);
        
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
        
        TextView t11 = (TextView) view1.findViewById(R.id.text11);
        TextView t21 = (TextView) view1.findViewById(R.id.text21);
        TextView t31 = (TextView) view1.findViewById(R.id.text31);
        TextView t41 = (TextView) view1.findViewById(R.id.text41);
        
        t11.setOnClickListener(new MyOnClickListener2(0));
        t21.setOnClickListener(new MyOnClickListener2(1));
        t31.setOnClickListener(new MyOnClickListener2(2));
        t41.setOnClickListener(new MyOnClickListener2(3));
       
        //每个页面的Title数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2); 
        
        final ArrayList<View> views2 = new ArrayList<View>();
        views2.add(view3);
        views2.add(view4); 
        views2.add(view5); 
        views2.add(view6); 
        
        final ArrayList<String> titles = new ArrayList<String>();
        titles.add("tab1");
        titles.add("tab2"); 
        
        final ArrayList<String> titles2 = new ArrayList<String>();
        titles2.add("tab3");
        titles2.add("tab4"); 
        
      //填充ViewPager的数据适配器
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
				return titles.get(position);
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
				return titles2.get(position);
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views2.get(position));
				return views2.get(position);
			}
		};
//		
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager2.setAdapter(mPagerAdapter2);
		
//		mViewPager.setOnTouchListener(new View.OnTouchListener() {
//		     @Override
//		     public boolean onTouch(View v, MotionEvent event) {
//		    	 switch (event.getAction()) {
//		              case MotionEvent.ACTION_DOWN:
//		            	  menu.setSlidingEnabled(false);
//		                   break;
//		              case MotionEvent.ACTION_UP:
//		              case MotionEvent.ACTION_CANCEL:
//		            	  menu.setSlidingEnabled(true);
//		                   break;
//		         }
//		         return false;
//		    }
//		});
		
		
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {  
			
			 int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
			 int two = one * 2;// 页卡1 -> 页卡3 偏移量
			 Animation animation = null;
			 
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
	                	
	                	if (currIndex == 1) {
	                        animation = new TranslateAnimation(one, 0, 0, 0);
	                    } else if (currIndex == 2) {
	                        animation = new TranslateAnimation(two, 0, 0, 0);
	                    }
	                	
	                    break;  
	                case 1:
	                    if (currIndex == 0) {
	                        animation = new TranslateAnimation(offset, one, 0, 0);
	                    } else if (currIndex == 2) {
	                        animation = new TranslateAnimation(two, one, 0, 0);
	                    }
	                    menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);  
	                    break;
	                case 2:
	                    if (currIndex == 0) {
	                        animation = new TranslateAnimation(offset, two, 0, 0);
	                    } else if (currIndex == 1) {
	                        animation = new TranslateAnimation(one, two, 0, 0);
	                    }
	                    menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);  
	                    break;	                
	                default:  
	                	menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);  
	                    break;  
	            }  
	            
	            currIndex = position;
	            animation.setFillAfter(true);// True:图片停在动画结束位置
	            animation.setDuration(300);
	            
	            
	            cursor.startAnimation(animation);
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
		
        
        //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new SampleListFragment()).commit();  
        Button button2 = (Button)findViewById(R.id.button02);
        button2.setText("打开");
        
        
        // 设置滑动菜单的属性值  
        menu = new SlidingMenu(this);  
        //SlidingMenu menu = new SlidingMenu(this);
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
                View view =  listViews.get(0);
                LayoutParams para = view.getLayoutParams();
                
                Toast.makeText(getApplicationContext(), "Deleted Successfully!" + para.width, Toast.LENGTH_LONG).show();  
            }
        });
        
        button2.setOnClickListener(new Button.OnClickListener(){ 
            public void onClick(View v)
            {
            	linToolBar.measure(0,0);  
                //获取组件宽度  
                int width = linToolBar.getMeasuredWidth();  
                //获取组件高度  
                int height = linToolBar.getMeasuredHeight(); 
                System.out.println(height);
            }
        });
        //getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new SampleListFragment()).commit();  
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
