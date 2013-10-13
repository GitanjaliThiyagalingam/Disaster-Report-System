package com.example.dars.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.test.InstrumentationTestCase;

import com.example.dars.MainActivity;
import com.example.dars.RegisterActivity;

@SuppressWarnings("deprecation")
public class MainActivityTest extends android.test.ActivityUnitTestCase<MainActivity>  {
    private Instrumentation mInstrumentation;
    private MainActivity mActivity;
    private Activity mChildActivity;
    
    public MainActivityTest() {
		super(com.example.dars.MainActivity.class);
		// TODO Auto-generated constructor stub
	}
	

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mInstrumentation = super.getInstrumentation();
    }

    @Override
    protected void tearDown() throws Exception {
        if (mActivity != null) {
            if (!mActivity.isFinishing()) {
                mActivity.finish();
            } else if (mChildActivity != null) {
                if (!mChildActivity.isFinishing()) {
                    mChildActivity.finish();
                }
            }
        }
        super.tearDown();
    }

    
    public void testChildTitleCallback() throws Exception {
        final Context context = mInstrumentation.getTargetContext();
        final Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        final MainActivity father = new MainActivity();
        final ComponentName componentName = new ComponentName(context, MainActivity.class);
        final ActivityInfo info = context.getPackageManager().getActivityInfo(componentName, 0);
        mChildActivity = mInstrumentation.newActivity(MainActivity.class, mInstrumentation
                .getTargetContext(), null, null, intent, info, MainActivity.class.getName(),
                father, null, null);

        assertNotNull(mChildActivity);
    }
}