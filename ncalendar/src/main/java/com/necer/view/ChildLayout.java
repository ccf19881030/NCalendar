package com.necer.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.necer.listener.OnCalendarStateChangedListener;

/**
 * Created by necer on 2018/11/9.
 */
public class ChildLayout extends FrameLayout implements ValueAnimator.AnimatorUpdateListener {


    protected View targetView;//嵌套滑动的目标view，即RecyclerView等

    protected ValueAnimator childLayoutValueAnimator;
    private int monthHeight;
    private int weekHeight;
    private OnCalendarStateChangedListener onCalendarStateChangedListenerr;


    public ChildLayout(@NonNull Context context, AttributeSet attrs, int monthHeight, int duration, OnCalendarStateChangedListener onCalendarStateChangedListener) {
        super(context, attrs);
        this.monthHeight = monthHeight;
        this.weekHeight = monthHeight / 5;

        this.onCalendarStateChangedListenerr = onCalendarStateChangedListener;
        childLayoutValueAnimator = new ValueAnimator();
        childLayoutValueAnimator.setDuration(duration);
        childLayoutValueAnimator.addUpdateListener(this);
        childLayoutValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                onCalendarStateChangedListenerr.onCalendarStateChanged(isMonthState());
            }
        });

    }


    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        ViewGroup parent = (ViewGroup) child.getParent();
        if (parent != null) {
            parent.removeView(child);
        }
        super.addView(child, params);

        targetView = getNestedScrollingChild(child);
        if (targetView == null) {
            throw new RuntimeException("NCalendar需要实现了NestedScrollingChild2的子类");
        }
    }

    private View getNestedScrollingChild(View view) {

        if (view instanceof NestedScrollingChild2) {
            return view;
        } else if (view instanceof ViewGroup) {
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = ((ViewGroup) view).getChildAt(i);
                if (childAt instanceof NestedScrollingChild2) {
                    return childAt;
                } else {
                    getNestedScrollingChild(((ViewGroup) view).getChildAt(i));
                }
            }
        }
        return null;
    }


    @Override
    public void offsetTopAndBottom(int offset) {
        super.offsetTopAndBottom(offset);
        invalidate();
    }

    public boolean canScrollVertically(int direction) {
        return ViewCompat.canScrollVertically(targetView, direction);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float animatedValue = (float) animation.getAnimatedValue();
        float top = getY();
        float i = animatedValue - top;
        float y = getY();
       // offsetTopAndBottom(i);
        setY(i + y);
    }


    public void autoToMonth() {
        float start = getY();
        int end = monthHeight;
        childLayoutValueAnimator.setFloatValues(start, end);
        childLayoutValueAnimator.start();

    }


    public void autoToWeek() {
        float start = getY();
        int end = weekHeight;
        childLayoutValueAnimator.setFloatValues(start, end);
        childLayoutValueAnimator.start();
    }


    public boolean isMonthState() {
        return getY() >= monthHeight;
    }

    public boolean isWeekState() {
       // return getTop() <= weekHeight;
        return getY() <= weekHeight;

    }


    public int getChildLayoutOffset() {
        return monthHeight - weekHeight;
    }


}
