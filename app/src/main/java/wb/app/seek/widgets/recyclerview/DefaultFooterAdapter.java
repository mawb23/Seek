package wb.app.seek.widgets.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import wb.app.seek.R;

/**
 * Created by W.b on 12/05/2017.
 */
public abstract class DefaultFooterAdapter<T> extends BaseRecyclerAdapter<T> {

    public static final int STATE_IDLE = 1;
    public static final int STATE_LOADING = 2;
    public static final int STATE_END = 3;

    private int mCurrentState;

    public DefaultFooterAdapter(Context context) {
        super(context);

        isAddFooter = true;
    }

    @Override
    protected int getFooterLayoutResId() {
        return R.layout.item_footer;
    }

    @Override
    protected BaseRecyclerViewHolder getFooterViewHolder(View footerView) {
        return new DefaultFooterViewHolder(footerView);
    }

    @Override
    protected void bindFooterViewHolder(BaseRecyclerViewHolder viewHolder) {
        DefaultFooterViewHolder holder = (DefaultFooterViewHolder) viewHolder;

        holder.itemView.setVisibility(View.VISIBLE);

        switch (mCurrentState) {
            case STATE_LOADING:
                holder.mLoadingStatusTv.setText("加载中...");
                holder.mLoadingStatusTv.setVisibility(View.VISIBLE);
                holder.mLoadingStatusTv.animate().withLayer().alpha(1).setDuration(400);
                holder.mLoadingPb.setVisibility(View.VISIBLE);
                break;
            case STATE_END:
                holder.mLoadingStatusTv.setText("没有更多了");
                holder.mLoadingStatusTv.setVisibility(View.VISIBLE);
                holder.mLoadingStatusTv.animate().withLayer().alpha(1).setDuration(400);
                holder.mLoadingPb.setVisibility(View.GONE);
                break;
            default:
                holder.itemView.setVisibility(View.GONE);
                break;
        }
    }

    public void setFooterState(int state) {
        mCurrentState = state;
        notifyItemChanged(getItemCount() - 1);
    }

    public static class DefaultFooterViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.item_loading_pb) ProgressBar mLoadingPb;
        @BindView(R.id.item_loading_status_tv) TextView mLoadingStatusTv;

        public DefaultFooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
