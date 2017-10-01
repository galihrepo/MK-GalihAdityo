package com.mankind.app.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mankind.app.R;
import com.mankind.app.account.AccountFragment;
import com.mankind.app.base.BaseActivity;
import com.mankind.app.base.BaseApplication;
import com.mankind.app.base.helper.ImageHelper;
import com.mankind.app.base.view.BaseTextView;
import com.mankind.app.help.HelpFragment;
import com.mankind.app.logout.LogoutDialogFragment;
import com.mankind.app.setting.SettingFragment;
import com.mankind.app.symptom.SymptomFragment;
import com.mvc.imagepicker.ImagePicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends BaseActivity implements HomeContract.View, NavigationView.OnNavigationItemSelectedListener {

    public static final String NAVIGATE_ACCOUNT = "navigate_account";
    public static final String REFRESH_PIC_PROFILE = "REFRESH_PIC_PROFILE";

    // content home
    View layoutContent;
    Toolbar toolbar;
    BaseTextView tvTitle;
    LinearLayout placeholder;

    // content header
    CircleImageView ivHeaderProfile;

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void initInjector() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initial() {
        setupView();
        setupToolbar();
        setupDrawer();
        setupNavigation();
        setupFirstFragment();
        loadImageProfile();
    }

    @Override
    public void setupView() {
        layoutContent = findViewById(R.id.layout_content);
        toolbar = layoutContent.findViewById(R.id.toolbar);
        tvTitle = layoutContent.findViewById(R.id.tv_title);
        placeholder = layoutContent.findViewById(R.id.placeholder);

        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer);

        View header = navigationView.getHeaderView(0);
        ivHeaderProfile = header.findViewById(R.id.iv_header_profile);
    }

    @Override
    public void setupFirstFragment() {
        navigationView.getMenu().getItem(0).setChecked(true);
        onClickSymptom();
    }

    @Override
    public void loadImageProfile() {
        Glide.with(this)
                .load(ImageHelper.getProfileImagePath())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(R.drawable.user_profile)
                .into(ivHeaderProfile);
    }

    @Override
    public void setupDrawer() {
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void setupNavigation() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.nav_icon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.isChecked()) {
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }

        drawerLayout.closeDrawers();

        switch (menuItem.getItemId()){
            case R.id.symptom:
                onClickSymptom();
                return true;
            case R.id.account:
                onClickAccount();
                return true;
            case R.id.help:
                onClickHelp();
                return true;
            case R.id.setting:
                onClickSetting();
                return true;
            case R.id.logout:
                onClickLogout();
                return true;
            default:
                return true;
        }
    }

    private void onClickLogout() {
        LogoutDialogFragment dialogFragment = new LogoutDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "");
    }

    private void onClickSetting() {
        tvTitle.setText("Setting");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(placeholder.getId(), new SettingFragment())
                .commit();
    }

    private void onClickHelp() {
        tvTitle.setText("Help");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(placeholder.getId(), new HelpFragment())
                .commit();
    }

    private void onClickAccount() {
        tvTitle.setText("Account");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(placeholder.getId(), new AccountFragment())
                .commit();
    }

    private void onClickSymptom() {
        tvTitle.setText("Symptoms");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(placeholder.getId(), new SymptomFragment())
                .commit();
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onSuccess(String message) {

    }

    public void navigateToAccount() {
        navigationView.getMenu().getItem(1).setChecked(true);
        onClickAccount();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String message) {
        if (message.equalsIgnoreCase(NAVIGATE_ACCOUNT)) {
            navigateToAccount();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == AccountFragment.PICK_IMAGE) {
                Bitmap bitmap = ImagePicker.getImageFromResult(getApplicationContext(), requestCode, resultCode, data);

                String filePath = ImageHelper.getProfileImagePath();
                if (BaseApplication.getStorage().isFileExist(ImageHelper.getProfileImagePath())) {
                    BaseApplication.getStorage().deleteFile(ImageHelper.getProfileImagePath());
                }
                BaseApplication.getStorage().createFile(filePath, bitmap);

                loadImageProfile();
                EventBus.getDefault().post(REFRESH_PIC_PROFILE);
            }
        }
    }
}