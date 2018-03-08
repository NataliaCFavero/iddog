package com.nataliafavero.iddog.ui.feed;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by nataliafavero on 08/03/18.
 */

public class CustomLinearLayoutManager extends LinearLayoutManager {

    private Context mContext;
    private int spanCount;

    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.mContext = context;
    }

    @Override
    public void measureChild(View child, int widthUsed, int heightUsed) {
        RecyclerView.LayoutParams lpTmp = (RecyclerView.LayoutParams) child.getLayoutParams();
        final RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(measureScreenWidth(mContext) / spanCount, lpTmp.height);
        int widthSpec = View.MeasureSpec.makeMeasureSpec(measureScreenWidth(mContext) / spanCount, View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(lp.height, View.MeasureSpec.EXACTLY);
        child.measure(widthSpec, heightSpec);
    }

    @Override
    public void measureChildWithMargins(View child, int widthUsed, int heightUsed) {
        RecyclerView.LayoutParams lpTmp = (RecyclerView.LayoutParams) child.getLayoutParams();
        final RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(measureScreenWidth(mContext) / spanCount, lpTmp.height);
        int widthSpec = View.MeasureSpec.makeMeasureSpec(measureScreenWidth(mContext) / spanCount, View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(lp.height, View.MeasureSpec.EXACTLY);
        child.measure(widthSpec, heightSpec);
    }

    private int measureScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        return point.x;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }
}