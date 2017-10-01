package com.mankind.app.symptom;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mankind.app.R;
import com.mankind.app.account.EditSymptomDialogFragment;
import com.mankind.app.base.view.BaseImageButton;
import com.mankind.app.base.view.BaseImageView;
import com.mankind.app.base.view.BaseTextView;
import com.mankind.app.db.Query;
import com.mankind.app.db.symptom.SymptomModel;
import com.mankind.app.home.HomeActivity;

import org.greenrobot.eventbus.EventBus;

import io.realm.RealmResults;

/**
 * Created by galihadityo on 2017-10-01.
 */

public class ListSymptomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int MODE_SEARCH = 0;
    public static final int MODE_LIST = 1;

    private int mode;
    private Context context;
    private RealmResults<SymptomModel> list;

    public ListSymptomAdapter(Context context, RealmResults<SymptomModel> list, int mode) {
        this.list = list;
        this.context = context;
        this.mode = mode;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.symptom_item, parent, false);
        return new SymptomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SymptomModel data = list.get(position);

        if (holder instanceof SymptomViewHolder) {
            ((SymptomViewHolder) holder).tvSymptom.setText(data.getSymptom());

            if (mode == MODE_SEARCH) {
                ((SymptomViewHolder) holder).btnEdit.setVisibility(View.GONE);
                ((SymptomViewHolder) holder).btnDelete.setVisibility(View.GONE);

            } else if (mode == MODE_LIST) {
                ((SymptomViewHolder) holder).btnNext.setVisibility(View.GONE);
                ((SymptomViewHolder) holder).btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                        dialog.setTitle("Delete symptom : " + data.getSymptom());
                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Query.deleteSymptom(data.getSymptom());
                                EventBus.getDefault().post(ListSymptomFragment.REFRESH);
                            }
                        });

                        dialog.show();
                    }
                });

                ((SymptomViewHolder) holder).btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditSymptomDialogFragment dialogFragment = new EditSymptomDialogFragment();
                        dialogFragment.setContent(data.getSymptom());
                        dialogFragment.show(((HomeActivity) context).getSupportFragmentManager(), "");
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SymptomViewHolder extends RecyclerView.ViewHolder {

        public BaseTextView tvSymptom;
        public BaseImageView btnEdit;
        public BaseImageView btnDelete;
        public BaseImageButton btnNext;

        public SymptomViewHolder(View view) {
            super(view);

            tvSymptom = view.findViewById(R.id.tv_symptom);
            btnEdit = view.findViewById(R.id.button_edit);
            btnDelete = view.findViewById(R.id.button_delete);
            btnNext = view.findViewById(R.id.button_next);
        }
    }

}
