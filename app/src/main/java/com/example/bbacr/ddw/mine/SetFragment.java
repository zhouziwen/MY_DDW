package com.example.bbacr.ddw.mine;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.car.address.ManageAddressFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.mine.set.AboutUsFragment;
import com.example.bbacr.ddw.mine.set.BasicsFragment;
import com.example.bbacr.ddw.mine.set.DealPwdFragment;
import com.example.bbacr.ddw.mine.set.LoginPwdFragment;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 设置
 */
public class SetFragment extends BaseFragment {
    @Bind(R.id.basic)
    RelativeLayout mBasic;
    @Bind(R.id.my_address)
    RelativeLayout mMyAddress;
    @Bind(R.id.my_pwd)
    RelativeLayout mMyPwd;
    @Bind(R.id.service)
    RelativeLayout mService;
    @Bind(R.id.about_us)
    RelativeLayout mAboutUs;
    @Bind(R.id.exit_login)
    Button mExitLogin;
    @Bind(R.id.my_login_pwd)
    RelativeLayout mMyLoginPwd;
    @Bind(R.id.update_txt)
    TextView mUpdateTxt;
    @Bind(R.id.update)
    RelativeLayout mUpdate;
    private TextView mBack;

    public SetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.basic, R.id.my_address, R.id.my_pwd, R.id.service, R.id.about_us, R.id.exit_login,R.id.my_login_pwd, R.id.update_txt, R.id.update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.basic:
                ShowFragmentUtils.showFragment(getActivity(), BasicsFragment.class, FragmentTag.BASICSFRAGMENT, null, true);
                break;
            case R.id.my_address:
                ShowFragmentUtils.showFragment(getActivity(), ManageAddressFragment.class, FragmentTag.MANAGEADDRESSFRAGMENT, null, true);
                break;
            case R.id.my_pwd:
                ShowFragmentUtils.showFragment(getActivity(), DealPwdFragment.class, FragmentTag.DEALPWDFRAGMENT, null, true);
                break;
            case R.id.service:
                break;
            case R.id.about_us:
                ShowFragmentUtils.showFragment(getActivity(), AboutUsFragment.class, FragmentTag.ABOUTUSFRAGMENT, null, true);
                break;
            case R.id.exit_login:
                dialog();
                break;
            case R.id.my_login_pwd:
                ShowFragmentUtils.showFragment(getActivity(), LoginPwdFragment.class, FragmentTag.LOGINPWDFRAGMENT, null, true);
                break;
            case R.id.update_txt:
                break;
            case R.id.update:

                break;
        }
    }
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确定要退出吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PreferenceManager.instance().removeToken();
                        mBaseActivity.finish();

                    }
                });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}
