package com.mankind.app.logout;

import android.content.Intent;
import android.view.View;

import com.mankind.app.R;
import com.mankind.app.base.BaseApplication;
import com.mankind.app.base.BaseDialogFragment;
import com.mankind.app.base.helper.ImageHelper;
import com.mankind.app.base.helper.Prefs;
import com.mankind.app.base.view.BaseTextView;
import com.mankind.app.home.HomeActivity;
import com.mankind.app.login.LoginActivity;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class LogoutDialogFragment extends BaseDialogFragment {

    BaseTextView tvYes;
    BaseTextView tvNo;

    @Override
    protected int getLayoutId() {
        return R.layout.custom_dialog_exit;
    }

    @Override
    protected void initial(View view) {
        tvYes = view.findViewById(R.id.btn_yes);
        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseApplication.getStorage().deleteFile(ImageHelper.getProfileImagePath());
                Prefs.write(Prefs.LOGIN_AS_USER, "");
                getActivity().finishAffinity();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        tvNo = view.findViewById(R.id.btn_no);
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
