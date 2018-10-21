package com.theceo_dev.networknotifier.network_notifier_module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NetworkLayout extends View implements NetworkListener {
    private Context context;
    private ViewGroup ID;

    private RelativeLayout relativeLayout;
    private TextView textView;

    public static NetworkListener networkListener;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String Status = NetworkTracker.checkNetwork(context);
            if (Status.length() > 0) {
                NetworkListener networkListener = (NetworkListener) NetworkLayout.networkListener;
                if (networkListener != null) {
                    networkListener.OnNetworkChanged(Status);
                }
            }
        }
    };

    public NetworkLayout(Context context, ViewGroup ID) {
        super(context);
        this.context = context;
        this.ID = ID;
        this.networkListener = this;
        if (context != null) {
            init();
            context.registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    private void init() {
        if (ID.getChildAt(0) instanceof RelativeLayout) {
            RelativeLayout constraintLayout = (RelativeLayout) ID.getChildAt(0);

            relativeLayout = new RelativeLayout(context);
            relativeLayout.setBackgroundColor(Color.parseColor("#008000"));
            relativeLayout.setGravity(Gravity.CENTER);
            relativeLayout.setVisibility(GONE);

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            relativeLayout.setLayoutParams(layoutParams);

            textView = new TextView(context);
            textView.setText("Online");
            textView.setTextColor(Color.WHITE);
            textView.setPadding(5, 5, 5, 5);
            relativeLayout.addView(textView);

            constraintLayout.addView(relativeLayout);

            //visible();
        } else if (ID.getChildAt(0) instanceof ConstraintLayout) {
            ConstraintLayout constraintLayout = (ConstraintLayout) ID.getChildAt(0);

            relativeLayout = new RelativeLayout(context);
            relativeLayout.setBackgroundColor(Color.parseColor("#008000"));
            relativeLayout.setGravity(Gravity.CENTER);
            relativeLayout.setVisibility(GONE);

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
            relativeLayout.setLayoutParams(layoutParams);

            textView = new TextView(context);
            textView.setText("Online");
            textView.setTextColor(Color.WHITE);
            textView.setPadding(5, 5, 5, 5);
            relativeLayout.addView(textView);

            constraintLayout.addView(relativeLayout);

            //visible();
        } else if (ID.getChildAt(0) instanceof LinearLayout) {
            Toast.makeText(context, "Parent layout does't support", Toast.LENGTH_SHORT).show();
        } else if (ID.getChildAt(0) instanceof AbsoluteLayout) {
            Toast.makeText(context, "Parent layout does't support", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Parent layout does't support", Toast.LENGTH_SHORT).show();
        }

    }

    private void visible() {
        relativeLayout.setVisibility(VISIBLE);
        showAnimation();

    }

    private void invisible() {
        hideAnimation();
        relativeLayout.setVisibility(GONE);
    }

    private boolean isVisible() {
        return relativeLayout.getVisibility() == VISIBLE ? true : false;
    }

    private void updateStatus(String status) {
        if (status.equals(NetworkTracker.MOBILE_WIFI)) {
            relativeLayout.setBackgroundColor(Color.parseColor("#008000"));
            autoHide();
        } else if (status.equals(NetworkTracker.NO_INTERNET_CONNECTION)) {
            relativeLayout.setBackgroundColor(Color.DKGRAY);

        }
        textView.setText(status);
        textView.invalidate();
        visible();
    }

    private void showAnimation() {
        Animation animationUtils = AnimationUtils.loadAnimation(context, R.anim.up);
        relativeLayout.setAnimation(animationUtils);
    }

    private void hideAnimation() {
        Animation animationUtils = AnimationUtils.loadAnimation(context, R.anim.down);
        relativeLayout.setAnimation(animationUtils);
    }

    private void autoHide() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                invisible();
            }
        }, 2000);
    }

    @Override
    public void OnNetworkChanged(String status) {
        if (!textView.getText().toString().equalsIgnoreCase(status)) {
            this.updateStatus(status);
        }
    }
}
