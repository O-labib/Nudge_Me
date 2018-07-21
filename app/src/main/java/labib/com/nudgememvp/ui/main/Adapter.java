package labib.com.nudgememvp.ui.main;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.nudgememvp.R;
import labib.com.nudgememvp.data.db.DatabaseOpenHelper;
import labib.com.nudgememvp.ui.base.BaseViewHolder;
import labib.com.nudgememvp.utils.TimeUtils;


public class Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_NORMAL = 1;
    private static final int VIEW_TYPE_EMPTY = 0;


    private Cursor dataCursor;
    private Callback mCallBack;

    private int COLUMN_ID;
    private int COLUMN_MESSAGE;
    private int COLUMN_PLACE;
    private int COLUMN_TIME;

    public void setCallBack(Callback callBack) {
        mCallBack = callBack;
    }

    public void setDataCursor(Cursor dataCursor) {
        this.dataCursor = dataCursor;

        COLUMN_ID = dataCursor.getColumnIndex(DatabaseOpenHelper.Col_1);
        COLUMN_MESSAGE = dataCursor.getColumnIndex(DatabaseOpenHelper.Col_2);
        COLUMN_PLACE = dataCursor.getColumnIndex(DatabaseOpenHelper.Col_3);
        COLUMN_TIME = dataCursor.getColumnIndex(DatabaseOpenHelper.Col_6);
    }

    @Override
    public int getItemViewType(int position) {

        if (dataCursor != null && dataCursor.getCount() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler_view, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler_view_empty, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (!dataCursor.moveToPosition(position)) {
            return;
        }

        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (dataCursor != null && dataCursor.getCount() > 0) {
            return dataCursor.getCount();
        } else {
            return 1;
        }
    }

    void swapCursor(Cursor cursor) {
        if (cursor != null) {
            dataCursor = null;
            dataCursor = cursor;
            notifyDataSetChanged();
        }
    }

    public interface Callback {
        void onItemClicked(long id);

        void onEmptyClicked();
    }


    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.nudgeTV)
        TextView nudgeTV;

        @BindView(R.id.placeTV)
        TextView placeTV;

        @BindView(R.id.timeTV)
        TextView timeTV;

        @BindView(R.id.dateTV)
        TextView dateTV;

        View itemView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            nudgeTV.setText(dataCursor.getString(COLUMN_MESSAGE));
            placeTV.setText((dataCursor.getString(COLUMN_PLACE)));
            timeTV.setText(TimeUtils.dateToReadable(dataCursor.getLong(COLUMN_TIME)));
            dateTV.setText((TimeUtils.timeToReadable(dataCursor.getLong(COLUMN_TIME))));

            itemView.setTag(dataCursor.getLong(COLUMN_ID));
        }

        @Override
        protected void clear() {
            nudgeTV.setText("");
            placeTV.setText("");
            timeTV.setText("");
            dateTV.setText("");
        }

        @OnClick(R.id.recyclerViewCard)
        public void buttonClicked() {
            mCallBack.onItemClicked((Long) itemView.getTag());
        }

    }

    public class EmptyViewHolder extends BaseViewHolder {


        public EmptyViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        protected void clear() {

        }


        @OnClick(R.id.recyclerViewEmptyCard)
        public void emptyClicked() {
            mCallBack.onEmptyClicked();
        }

    }


}
