package labib.com.nudgememvp.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.nudgememvp.R;
import labib.com.nudgememvp.data.db.Nudge;
import labib.com.nudgememvp.ui.base.BaseViewHolder;
import labib.com.nudgememvp.utils.TimeUtils;


public class Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_NORMAL = 1;
    private static final int VIEW_TYPE_EMPTY = 0;


    private ArrayList<Nudge> data;
    private Callback mCallBack;



    public void setCallBack(Callback callBack) {
        mCallBack = callBack;
    }

    public void setData(ArrayList<Nudge> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {

        if (data != null && data.size() > 0) {
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
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 1;
        }
    }

    void swapData(ArrayList<Nudge> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Nudge nudge, int position) {
        data.add(position, nudge);
        notifyItemInserted(position);
    }

    public interface Callback {
        void onEmptyLayoutClicked();
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


        @BindView(R.id.recyclerViewCard)
        CardView cardView;

        View itemView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            nudgeTV.setText(data.get(position).getMessage());
            placeTV.setText(data.get(position).getPlaceName());
            timeTV.setText(TimeUtils.dateToReadable(data.get(position).getTime()));
            dateTV.setText((TimeUtils.timeToReadable(data.get(position).getTime())));

            itemView.setTag(data.get(position));
        }

        @Override
        protected void clear() {
            nudgeTV.setText("");
            placeTV.setText("");
            timeTV.setText("");
            dateTV.setText("");
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
            mCallBack.onEmptyLayoutClicked();
        }

    }


}
