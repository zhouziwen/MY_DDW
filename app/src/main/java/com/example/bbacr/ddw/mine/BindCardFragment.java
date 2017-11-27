package com.example.bbacr.ddw.mine;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.listview.PopBaseAdapter;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.DisPlayUtils;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BindCardFragment extends BaseFragment {
    @Bind(R.id.text_sex)
    TextView mTextSex;
    @Bind(R.id.pic)
    ImageView mPic;
    @Bind(R.id.Relative)
    RelativeLayout mRelative;
    @Bind(R.id.person_card)
    TextView mPersonCard;
    @Bind(R.id.text_card)
    EditText mTextCard;
    @Bind(R.id.content)
    TextView mContent;
    @Bind(R.id.text_card_hang)
    EditText mTextCardHang;
    @Bind(R.id.card_num)
    TextView mCardNum;
    @Bind(R.id.text_card_num)
    EditText mTextCardNum;
    @Bind(R.id.card_num_sure)
    TextView mCardNumSure;
    @Bind(R.id.text_card_num_sure)
    EditText mTextCardNumSure;
    @Bind(R.id.registerInBtn)
    Button mRegisterInBtn;
    private TextView mBack;
    private ListView mListView;
    private PopupWindow mPopupWindow;
    private List<String> card = new ArrayList<>();//银行的集合
    private String mSex,mPerson,mCardH,mCard,mCardSure,mPosition;
    public BindCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bind_card, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mRegisterInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
        mRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow(card);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextSex.setText(card.get(position));
                        mPosition = String.valueOf(position+1);
                        LogUtils.d("dddddddddddddddddsfhhfhfhjfhf"+mPosition);
                        mPopupWindow.dismiss();
                    }
                });
            }
        });
        initList();
    }

    @Override
    protected void setData() {
        super.setData();
    }
    private void initList(){
        card.add("工商银行");
        card.add("交通银行");
        card.add("建设银行");
        card.add("中国银行");
        card.add("农业银行");
        card.add("招商银行");
        card.add("中信银行");
        card.add("浦发银行");
        card.add("广发银行");
        card.add("民生银行");
        card.add("兴业银行");
        card.add("邮政储蓄银行");
        card.add("光大银行");

    }
    //添加
    private void addCard() {
        final HashMap<String, String> parse = new HashMap<>();
        parse.put("token", PreferenceManager.instance().getToken());
        parse.put("accountName", mTextCard.getText().toString());
        parse.put("cardNum",mTextCardNum.getText().toString() );
        parse.put("kaihuhang", mTextCardHang.getText().toString());
        parse.put("bankId", mPosition);
        LogUtils.d("上传的" + mPosition);
        HttpHelper.getInstance().post(Url.addCard, parse, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                final BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {
                    EventBus.getDefault().post(new EventBase());
                    popSelf();
                } else if (bean.getCode() == 0) {
                    LogUtils.d("dafjfajkjkk=" + bean.getMsg());
                    ToastUtil.showShort(bean.getMsg());
                } else {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
    /**
     * 初始化poupwindow
     */
    private void initPopWindow(List<String> datas) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
//		// 引入窗口配置文件
        View view = inflater.inflate(R.layout.poup_window_small, null,false);
        mListView = (ListView) view.findViewById(R.id.listview_pop);
        mPopupWindow = new PopupWindow(view, DisPlayUtils.dip2px(52f)*2, WindowManager.LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.popupAnimation);
        mListView.setAdapter(new PopBaseAdapter(getContext(),datas));
        // 设置此参数获得焦点，否则无法点击
        mPopupWindow.setFocusable(true);
//        Toast.makeText(getContext(),"gg",Toast.LENGTH_SHORT).show();
        mPopupWindow.showAsDropDown(mPic, 0, 25);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
