package com.MyGymRoutine.myapp.view.components.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.MyGymRoutine.myapp.R;

public class Header extends ConstraintLayout
{
    private ImageView backButton;

    private TextView titleText;

    public Header(Context context) {
        super(context);
    }

    public Header(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Header(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Header(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

        backButton = findViewById(R.id.common_header_back_button);
        titleText = findViewById(R.id.common_header_title_text);
    }

    public void setOnBackClickListener(@Nullable OnClickListener l)
    {
        backButton.setOnClickListener(l);
    }

    public void hideBackButton()
    {
        backButton.setVisibility(GONE);
    }

    public void setTitle(@StringRes int resId)
    {
        titleText.setText(resId);
    }

    public void setTitle(@NonNull String text)
    {
        titleText.setText(text);
    }
}
