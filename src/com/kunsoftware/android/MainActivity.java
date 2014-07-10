package com.kunsoftware.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
  
public class MainActivity extends Activity  {  
    private SlidingMenu menu;  
    private ViewPager mViewPager;
	private PagerTitleStrip mPagerTitleStrip;
	private ViewPager mViewPager2;
	private PagerTitleStrip mPagerTitleStrip2;
	private	int pagerPosition = 0;
   
	private List<View> listViews; // Tabҳ���б�
    private ImageView cursor;// ����ͼƬ
    private TextView t1, t2, t3;// ҳ��ͷ��
    private int offset = 0;// ����ͼƬƫ����
    private int currIndex = 0;// ��ǰҳ�����
    private int bmpW;// ����ͼƬ���
	
    private void InitTextView() {
	t1 = (TextView) findViewById(R.id.text1);
	t2 = (TextView) findViewById(R.id.text2);
 	t3 = (TextView) findViewById(R.id.text3);
 
	t1.setOnClickListener(new MyOnClickListener(0));
	t2.setOnClickListener(new MyOnClickListener(1));
	t3.setOnClickListener(new MyOnClickListener(2));
	
    }
    
    /**
     * ��ʼ������
*/
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        bmpW = 60;// BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();// ��ȡͼƬ���
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��
        offset = (screenW / 3 - bmpW) / 2;// ����ƫ����
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// ���ö�����ʼλ��
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
    
	@Override
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
                     
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);  
        //���ñ���  
        setTitle("Attach");  
  
        //��ʼ�������˵�  
        initSlidingMenu();  
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
        mPagerTitleStrip = (PagerTitleStrip)findViewById(R.id.pagertitle);
        LinearLayout tabLine = (LinearLayout)findViewById(R.id.tab1);
       
//        TextView textView = new TextView(this);
//        textView.setText("tab1"); 
//        textView.setPadding(20, 0, 20, 0);
//        
//        tabLine.addView(textView);
//        
//        textView = new TextView(this);
//        textView.setText("tab2");
//        tabLine.addView(textView);
        
        //��Ҫ��ҳ��ʾ��Viewװ��������
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.view1, null);
        View view2 = mLi.inflate(R.layout.view2, null);
        View view3 = mLi.inflate(R.layout.view3, null);
        View view4 = mLi.inflate(R.layout.view4, null);
        
        mViewPager2 = (ViewPager)view1.findViewById(R.id.viewpager);
        mPagerTitleStrip2 = (PagerTitleStrip)view1.findViewById(R.id.pagertitle);
        
        //ÿ��ҳ���Title����
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2); 
        
        final ArrayList<View> views2 = new ArrayList<View>();
        views2.add(view3);
        views2.add(view4); 
        
        final ArrayList<String> titles = new ArrayList<String>();
        titles.add("tab1");
        titles.add("tab2"); 
        
        final ArrayList<String> titles2 = new ArrayList<String>();
        titles2.add("tab3");
        titles2.add("tab4"); 
        
      //���ViewPager������������
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
			
			 int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����
			 int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����
			 Animation animation = null;
			 
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
	            animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
	            animation.setDuration(300);
	            cursor.startAnimation(animation);
	        }  
	  
	    });
		
		mViewPager2.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {  
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
	        }  
	  
	    });  
		
        
        //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new SampleListFragment()).commit();  
        Button button2 = (Button)findViewById(R.id.button02);
        button2.setText("��");
        
        
        // ���û����˵�������ֵ  
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
        button.setText("����");
         
        
        button2.setOnClickListener(new Button.OnClickListener(){ 
            public void onClick(View v)
            {
                menu.showMenu();
            }
        });
        //getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new SampleListFragment()).commit();  
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
