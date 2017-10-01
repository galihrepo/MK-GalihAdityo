package com.mankind.app.account;

import android.view.View;

import com.mankind.app.R;
import com.mankind.app.base.BaseDialogFragment;
import com.mankind.app.base.helper.Prefs;
import com.mankind.app.base.view.BaseEditText;
import com.mankind.app.base.view.BaseImageButton;
import com.mankind.app.base.view.BaseImageView;
import com.mankind.app.base.view.BaseTextView;
import com.mankind.app.base.view.BaseToast;
import com.mankind.app.db.Query;
import com.mankind.app.symptom.ListSymptomAdapter;
import com.mankind.app.symptom.ListSymptomFragment;
import com.mankind.app.symptom.SymptomFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class EditSymptomDialogFragment extends BaseDialogFragment {

    BaseImageView btnClose;
    BaseImageButton btnSave;
    BaseEditText et;
    String content = "";

    @Override
    protected int getLayoutId() {
        return R.layout.edit_symptom_dialog_fragment;
    }

    @Override
    protected void initial(View view) {
        et = view.findViewById(R.id.et_tell_symptom);
        et.setText(content);

        btnSave = view.findViewById(R.id.button_next);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String keyword = et.getText().toString().trim();

                if (keyword.length() == 0) {
                    BaseToast.showMessage(getActivity(), "Please fill your symptom");
                    return;
                }

                if (content.length() > 0) {
                    // update
                    Query.updateSymptom(content, keyword);
                    BaseToast.showMessage(getActivity(), "Success update symptom : " + keyword);
                    EventBus.getDefault().post(ListSymptomFragment.REFRESH);
                    dismiss();
                } else {
                    // insert
                    if (Query.findSymptomEqual(keyword).size() > 0) {
                        BaseToast.showMessage(getActivity(), keyword + " already available");
                    } else {
                        Query.insertSymptom(keyword);
                        BaseToast.showMessage(getActivity(), "Success add symptom : " + et.getText().toString().trim());
                        EventBus.getDefault().post(ListSymptomFragment.REFRESH);
                        dismiss();
                    }
                }
            }
        });

        btnClose = view.findViewById(R.id.button_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setContent(String content) {
        this.content = content;
    }
}
