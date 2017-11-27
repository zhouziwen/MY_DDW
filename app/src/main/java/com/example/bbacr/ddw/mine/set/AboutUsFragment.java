package com.example.bbacr.ddw.mine.set;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.utils.LogUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 * 关于我们
 */
public class AboutUsFragment extends BaseFragment {

    private TextView mBack;
    @Bind(R.id.Version)
    TextView mTextVersion;
    private String mVersion;
    public AboutUsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        getLocalVersion(getContext());
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
    }
    @Override
    protected void setListener() {
        super.setListener();
    }
    @Override
    protected void setData() {
        super.setData();
        mTextVersion.setText("版本号V"+mVersion);
    }
    @Override
    public void onClick(View v) {
    }
    private void getLocalVersion(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            mVersion = "0.0";
        }
        LogUtils.d("版本号"+info.versionName);
        mVersion = info.versionName;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
