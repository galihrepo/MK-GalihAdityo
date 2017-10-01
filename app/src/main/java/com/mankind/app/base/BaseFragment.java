package com.mankind.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mankind.app.base.helper.Prefs;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by galihadityo on 2017-09-28.
 */

public abstract class BaseFragment extends Fragment {

    private View view;
    private Unbinder butterKnife;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        butterKnife = ButterKnife.bind(this, view);
        Prefs.init(getActivity());
        initial(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        butterKnife.unbind();
    }


    protected abstract int getLayoutId();

    protected abstract void initial(View view);

}
