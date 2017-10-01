package com.mankind.app.account;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.mankind.app.R;
import com.mankind.app.base.BaseApplication;
import com.mankind.app.base.BaseFragment;
import com.mankind.app.base.helper.ImageHelper;
import com.mankind.app.home.HomeActivity;
import com.mankind.app.symptom.SymptomAdapter;
import com.mvc.imagepicker.ImagePicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class AccountFragment extends BaseFragment implements AccountContract.View {

    public static final int PICK_IMAGE = 99;

    @BindView(R.id.tab)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.iv_profile)
    CircleImageView ivProfile;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void initial(View view) {
        setupPager(view);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void loadImagePicture() {
        Glide.with(getActivity())
                .load(ImageHelper.getProfileImagePath())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(R.drawable.user_profile)
                .into(ivProfile);
    }

    private void setupPager(final View view) {
        tabLayout.addTab(tabLayout.newTab().setText("Tells"));
        tabLayout.addTab(tabLayout.newTab().setText("Followers"));
        tabLayout.addTab(tabLayout.newTab().setText("Following"));

        AccountAdapter adapter = new AccountAdapter(getActivity().getSupportFragmentManager(),
                tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.fab)
    public void addSymptom() {
        EditSymptomDialogFragment dialogFragment = new EditSymptomDialogFragment();
        dialogFragment.show(getActivity().getSupportFragmentManager(), "");
    }

    @OnClick(R.id.iv_profile)
    public void changeAvatar() {
        TedPermission.with(getActivity())
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        imagePicker();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                    }
                })
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();

    }

    private void imagePicker() {
        ImagePicker.pickImage(getActivity(), PICK_IMAGE);
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
            loadImagePicture();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadImagePicture();
    }
}
