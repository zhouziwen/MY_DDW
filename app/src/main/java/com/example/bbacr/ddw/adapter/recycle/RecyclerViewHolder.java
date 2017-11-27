package com.example.bbacr.ddw.adapter.recycle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.SuperViewHolder;
import com.sunfusheng.glideimageview.GlideImageView;

/**
 * Created by Zhouziwen .
 * 邮箱：1916973618@qq.com
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;//集合类，layout里包含的View,以view的id作为key，value是view对象
    private Context mContext;//上下文对象
    public RecyclerViewHolder(Context ctx, View itemView) {
        super(itemView);
        mContext = ctx;
        mViews = new SparseArray<View>();
    }

    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getView(int viewId) {
        return findViewById(viewId);
    }

    public TextView getTextView(int viewId) {
        return (TextView) getView(viewId);
    }

    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return (ImageView) getView(viewId);
    }

    public ImageButton getImageButton(int viewId) {
        return (ImageButton) getView(viewId);
    }

    public EditText getEditText(int viewId) {
        return (EditText) getView(viewId);
    }

    public RecyclerViewHolder setText(int viewId, String value) {
        TextView view = findViewById(viewId);
        view.setText(value);
        return this;
    }
    public RecyclerViewHolder setCheck(int viewId, String value) {
        CheckBox checkBox = findViewById(viewId);
        checkBox.setText(value);
        return this;
    }
    public RecyclerViewHolder setVisible(boolean visible, int viewId) {
        View view = getView(viewId);
        if (view != null) {
            if (visible) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
        return this;
    }
    public RecyclerViewHolder setCheckBox(int viewId, boolean isCheck) {
        View view = getView(viewId);
        if (view != null && view instanceof CheckBox) {
            if (isCheck) {
                view.setBackgroundResource(R.mipmap.ic_checked);
            } else {
                view.setBackgroundResource(R.mipmap.ic_uncheck);
            }
        }
        return this;
    }
    public RecyclerViewHolder setImageCheck(int viewId, boolean isCheck) {
        View view = getView(viewId);
        if (view != null && view instanceof ImageView) {
            if (isCheck) {
                view.setBackgroundResource(R.mipmap.ic_checked);
            } else {
                view.setBackgroundResource(R.mipmap.ic_uncheck);
            }
        }
        return this;
    }
    public RecyclerViewHolder setChecked(int viewId, boolean isCheck) {
        View view = getView(viewId);
        if (view != null && view instanceof TextView) {
        if (isCheck) {
            ((TextView) view).setTextColor(0xFFE51A18);
            view.setBackgroundResource(R.drawable.def_red_tint);
        } else {
            ((TextView) view).setTextColor(0xFF313131);
            view.setBackgroundResource(R.drawable.def_white_class);
        }
        }
        return this;
    }
    public RecyclerViewHolder setBackground(int viewId, int resId) {
        View view = findViewById(viewId);
        if (view != null && view instanceof ImageView) {
            view.setBackgroundResource(resId);
        }

        return this;
    }
    public RecyclerViewHolder setBackgroupDrawable(Drawable drawable, int viewId) {
        View view = getView(viewId);
        if (view != null) {
            view.setBackgroundDrawable(drawable);
        }
        return this;
    }
    public RecyclerViewHolder setGlideImageView(String imgUrl, int viewId) {
        View view = getView(viewId);
        if (view != null && view instanceof GlideImageView) {
            ((GlideImageView) view).loadImage(imgUrl, R.mipmap.ic_launcher);
        }
        return this;
    }
    public RecyclerViewHolder setGlideView(String imgUrl, int viewId) {
        View view = getView(viewId);
        if (view != null && view instanceof GlideImageView) {
            ((GlideImageView) view).loadLocalImage(imgUrl, R.mipmap.ic_launcher);
        }
        return this;
    }
    public RecyclerViewHolder setCircleImageView(String imgUrl, int viewId) {
        View view = getView(viewId);
        if (view != null && view instanceof GlideImageView) {
            ((GlideImageView) view).loadCircleImage(imgUrl, R.mipmap.ic_launcher);
        }
        return this;
    }
    public RecyclerViewHolder setTextColor(int color, int viewId) {
        View view = getView(viewId);
        if (view != null && view instanceof TextView) {
            ((TextView) view).setTextColor(mContext.getResources().getColor(color));
        }
        return this;
    }
    public RecyclerViewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }

}
