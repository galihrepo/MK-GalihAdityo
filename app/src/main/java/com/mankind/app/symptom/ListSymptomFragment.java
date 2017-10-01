package com.mankind.app.symptom;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.mankind.app.R;
import com.mankind.app.base.BaseFragment;
import com.mankind.app.base.helper.Utils;
import com.mankind.app.base.view.BaseEditText;
import com.mankind.app.base.view.BaseTextView;
import com.mankind.app.base.view.BaseToast;
import com.mankind.app.db.symptom.SymptomModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import io.realm.RealmResults;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class ListSymptomFragment extends BaseFragment implements ListSymptomContract.View {

    public static final String REFRESH = "refresh";
    private int mode;
    ListSymptomPresenter presenter = new ListSymptomPresenter();

    @BindView(R.id.cv_search)
    CardView cardViewSearch;

    @BindView(R.id.tv_no_data)
    BaseTextView tvNoData;

    @BindView(R.id.et_list_search)
    BaseEditText etSearch;

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.list_symptom_fragment;
    }

    @Override
    protected void initial(View view) {
        presenter.setView(this);
        setupSearch(view);
        setupRecycleView();
        presenter.actionGetAll();

        if (mode == ListSymptomAdapter.MODE_LIST) {
            cardViewSearch.setVisibility(View.GONE);
        }
    }

    private void setupSearch(View view) {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etSearch.length() == 0) {
                    presenter.actionGetAll();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Utils.hideKeyboard(getActivity(), etSearch);
                    presenter.actionSearch(etSearch.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
    }

    private void setupRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void renderSymptoms(RealmResults<SymptomModel> list) {
        if (list.size() == 0) {
            tvNoData.setVisibility(View.VISIBLE);
            return;
        } else {
            tvNoData.setVisibility(View.GONE);
        }

        ListSymptomAdapter adapter = new ListSymptomAdapter(getActivity(), list, mode);
        recyclerView.setAdapter(adapter);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String message) {
        if (message.equalsIgnoreCase(REFRESH)) {
            presenter.actionGetAll();
        }
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

}
