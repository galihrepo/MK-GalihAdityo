package com.mankind.app.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.mankind.app.R;
import com.mankind.app.base.helper.Prefs;

/**
 * Created by galihadityo on 2017-10-01.
 */

public abstract class BaseDialogFragment extends DialogFragment {

    private View view;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        getDialog().getWindow().getAttributes().windowAnimations = R.style.animation_popup;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setCancelable(false);

        // tranparent background
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        view = inflater.inflate(getLayoutId(), null);
        Prefs.init(getActivity());
        initial(view);
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void initial(View view);
}
