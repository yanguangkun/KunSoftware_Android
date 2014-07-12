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
   
	private List<TextView> listViews = new ArrayList<TextView>(); // Tabҳ���б�
    private ImageView cursor;// ����ͼƬ
    private ImageView cursor2;// ����ͼƬ
    private TextView t1, t2, t3;// ҳ��ͷ��
    private int offset = 0;// ����ͼƬƫ����
    private int currIndex = 0;// ��ǰҳ�����
    private int bmpW;// ����ͼƬ���
    private int bmpW2;// ����ͼƬ���
	
    private void InitTextView() {
    	t1 = (TextView) findViewById(R.id.text1);
    	t2 = (TextView) findViewById(R.id.text2);
 	  
	 	listViews.add(t1);
	 	listViews.add(t2); 
 
		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1)); 
    }
    
    /** ��ʼ������ */
    private void InitImageView() { 

    	cursor = (ImageView) findViewById(R.id.cursor); 
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;// ��ȡ�ֱ��ʿ��
        
        bmpW = screenWidth / 2;
        bmpW2 = screenWidth / 4;
        
        LayoutParams cursorPara = cursor.getLayoutParams();
        cursorPara.width = bmpW; 
        cursor.setLayoutParams(cursorPara);  
         
    }
    
    /**
     * ͷ��������
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
        //���ñ���  
        setTitle("Attach");  
  
        //��ʼ�������˵�  
        initSlidingMenu();  
        //Toast.makeText(getApplicationContext(), "Deleted Successfully!" + px2dip(getApplicationContext(),90), Toast.LENGTH_LONG).show();          
    }  
	 
	public void listView(View view) {
		
		ListView list = (ListView) view.findViewById(R.id.MyListView);  
	      
	    //���ɶ�̬���飬����ת������  
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  
	    for(int i=0;i<30;i++)  {  
	        HashMap<String, String> map = new HashMap<String, String>();  
	        map.put("ItemTitle", "�Ž�");  
	        map.put("ItemText", "���ݴ���-����Ա");  
	        mylist.add(map);  
	    }  
	    //����������������===��ListItem  
	    SimpleAdapter mSchedule = new SimpleAdapter(this, //ûʲô����  
	                                                mylist,//������Դ   
	                                                R.layout.my_listitem,//ListItem��XMLʵ��  
	                                                  
	                                                //��̬������ListItem��Ӧ������          
	                                                new String[] {"ItemTitle", "ItemText"},   
	                                                  
	                                                //ListItem��XML�ļ����������TextView ID  
	                                                new int[] {R.id.ItemTitle,R.id.ItemText});  
	    //��Ӳ�����ʾ  
	    list.setAdapter(mSchedule); 
	}
  
    /** 
     * ��ʼ�������˵� 
     */  
    private void initSlidingMenu() {  
    	  
        // ������������ͼ  
        setContentView(R.layout.content_frame);  
        
        InitTextView();
        InitImageView();       
        
        mViewPager = (ViewPager)findViewById(R.id.viewpager); 
        imageMenu = (ImageView)findViewById(R.id.imageMenu);
        
        //��Ҫ��ҳ��ʾ��Viewװ��������
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
      
        /** ���ViewPager������������ */
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
	             *  setSelectedNavigationItem ������������ActionBar�ľ۽�tab .   
	             *  �ڽ����������ж���SLidingMenu��������ģʽ�� 
	             *  ���ViewPager�Ѿ�����������ߣ������ǰ��������ó�ȫ���ģ� 
	             *  ���������󻬶���ʱ�򣬾ͻ��Menu . 
	             */ 	        	
	            switch (position) {  
	                //��������߅퓿��O��SlidingMenu�����քݿ�ȫ�����һ����ˆ�  
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
	            animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
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
	             *  setSelectedNavigationItem ������������ActionBar�ľ۽�tab .   
	             *  �ڽ����������ж���SLidingMenu��������ģʽ�� 
	             *  ���ViewPager�Ѿ�����������ߣ������ǰ��������ó�ȫ���ģ� 
	             *  ���������󻬶���ʱ�򣬾ͻ��Menu . 
	             */  
	        	pagerPosition = position;
	            switch (position) {  
	                //��������߅퓿��O��SlidingMenu�����քݿ�ȫ�����һ����ˆ�  
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
	           animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
	           animation.setDuration(300);
		            
		            
	           cursor2.startAnimation(animation);
		            
	           currIndex2 = position;
	        }  
	  
	    }); 
        
        // ���û����˵�������ֵ  
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
        button.setText("����"); 
        
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
//            	new AlertDialog.Builder(MainActivity.this).setTitle("��ѡ��").setMultiChoiceItems(
//            			  new String[] { "Item1", "Item2" }, null, null)
//            			 .setPositiveButton("ȷ��", null)
//            			 .setNegativeButton("ȡ��", null).show();
            	
            	CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);
        		builder.setMessage("��������Զ������ʾ��");
        		builder.setTitle("��ʾ");
        		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int which) {
        				dialog.dismiss();
        				//������Ĳ�������
        			}
        		});

        		builder.setNegativeButton("ȡ��",
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
        //������ؼ��رջ����˵�  
        if (menu.isMenuShowing()) {  
            menu.showContent();  
        } else {  
            super.onBackPressed();  
        }  
    }  
  
}  
