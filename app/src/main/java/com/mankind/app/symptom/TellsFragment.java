package com.mankind.app.symptom;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mankind.app.R;
import com.mankind.app.base.BaseFragment;
import com.mankind.app.base.helper.ImageHelper;
import com.mankind.app.base.helper.Utils;
import com.mankind.app.base.view.BaseEditText;
import com.mankind.app.base.view.BaseToast;
import com.mankind.app.home.HomeActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class TellsFragment extends BaseFragment implements TellsContract.View {

    TellsPresenter presenter = new TellsPresenter();

    @BindView(R.id.et_tell_symptom)
    BaseEditText etTell;

    @BindView(R.id.iv_profile)
    CircleImageView ivProfile;

    @OnClick(R.id.btn_submit)
    public void insert() {
        Utils.hideKeyboard(getActivity(), etTell);

        if (etTell.getText().toString().trim().length() == 0) {
            BaseToast.showMessage(getActivity(), "Please fill symptom");
            return;
        }

        presenter.actionInsertSymptom(etTell.getText().toString().trim());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tell_fragment;
    }

    @Override
    protected void initial(View view) {
        presenter.setView(this);
        loadImageProfile();
    }

    @Override
    public void onError(String message) {
        BaseToast.showMessage(getActivity(), message);
    }

    @Override
    public void onSuccess(String message) {
        BaseToast.showMessage(getActivity(), message);
    }

    @Override
    public void navigateAccount() {
        EventBus.getDefault().post(HomeActivity.NAVIGATE_ACCOUNT);
    }

    @Override
    public void loadImageProfile() {
        Glide.with(getActivity())
                .load(ImageHelper.getProfileImagePath())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(R.drawable.user_profile)
                .into(ivProfile);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String message) {
        if (message.equalsIgnoreCase(HomeActivity.REFRESH_PIC_PROFILE)) {
            loadImageProfile();
        }
    }
}
