package com.example.bbacr.ddw.home.search;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.detail.ShoppingDetailBean;
import com.example.bbacr.ddw.bean.home.PopSearch;
import com.example.bbacr.ddw.classify.view.init.ClassDetailFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.data.RecordSQLiteOpenHelper;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.MyListView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment {
    @Bind(R.id.et_search)
    EditText mEtSearch;
    @Bind(R.id.tv_tip)
    TextView mTvTip;
    @Bind(R.id.list_view2)
    ListView mListView2;
    /*数据库变量*/
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    @Bind(R.id.iv_search)
    TextView mIvSearch;
    @Bind(R.id.tv_clear)
    ImageView mTvClear;
    @Bind(R.id.list_view)
    MyListView mListView;
    private BaseAdapter mAdapter;
    @Bind(R.id.container)
    TagFlowLayout mContainer;
    private TagAdapter<String> mTagAdapter;
    private List<String> mStrings = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        //实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(getContext());
        // 第一次进入时查询所有的历史记录
        queryData("");
//        ListBean bean= (ListBean) mArguments.getSerializable("mListBean");
        mTagAdapter = new TagAdapter<String>(mStrings) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                final LayoutInflater mInflater = LayoutInflater.from(getActivity());
                TextView tv = (TextView) mInflater.inflate(R.layout.flow_layout_item,
                        mContainer, false);
                tv.setText(s);
                return tv;
            }
        };
       mContainer.setAdapter(mTagAdapter);
    mContainer.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        boolean hasData = hasData(mStrings.get(position));
        if (!hasData&&!TextUtils.isEmpty(mStrings.get(position))) {
            insertData(mStrings.get(position));
            //搜索后显示数据库里所有搜索历史是为了测试
            queryData("");
        }
        mArguments.putString("keyWords",mStrings.get(position));
        ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class, FragmentTag.CLASSDETAILFRAGMENT, mArguments, true);
        return true;
    }
});
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void setListener() {
        super.setListener();
        mTvClear.setOnClickListener(this);
        mEtSearch.setOnClickListener(this);
        mIvSearch.setOnClickListener(this);
        //搜索框的文本变化实时监听
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            //输入后调用该方法
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 0) {
                    //若搜索框为空,则模糊搜索空字符,即显示所有的搜索历史
                    mTvTip.setText("历史搜索");
                } else if (s.toString().length() >= 1) {
//                    popSuggest(et_search.getText().toString().trim());
                    mTvTip.setText("搜索结果");
                }
                //每次输入后都查询数据库并显示
                //根据输入的值去模糊查询数据库中有没有数据
                String tempName = mEtSearch.getText().toString();
                queryData(tempName);
            }
        });
        mEtSearch.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键

            // 修改回车键功能
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 隐藏键盘，这里getCurrentFocus()需要传入Activity对象，如果实际不需要的话就不用隐藏键盘了，免得传入Activity对象，这里就先不实现了
//                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
//                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    boolean hasData = hasData(mEtSearch.getText().toString().trim());
                    if (!hasData&&!TextUtils.isEmpty(mEtSearch.getText().toString().trim())) {
                        insertData(mEtSearch.getText().toString().trim());
                        queryData("");
                    }
                    //根据输入的内容模糊查询商品，并跳转到另一个界面，这个需要根据需求实现
                    mArguments.putString("keyWords",mEtSearch.getText().toString().trim());
                    ShowFragmentUtils.showFragment(getActivity(), ClassDetailFragment.class, FragmentTag.CLASSDETAILFRAGMENT, mArguments, true);

                }
                return false;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取到用户点击列表里的文字,并自动填充到搜索框内
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                mEtSearch.setText(name);
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        popSearch();
    }

    /**
     * 热门搜索
     */
private void popSearch(){
    HashMap<String, String> hashMap = new HashMap<>();
    HttpHelper.getInstance().post(Url.popSearch, hashMap, new RawResponseHandler() {
        @Override
        public void onSuccess(int statusCode, String response) {
            LogUtils.d("popSearch"+response);
            PopSearch bean = JsonUtils.parse(response, PopSearch.class);
            if (bean.getCode()==1) {
                mStrings.addAll(bean.getDatas());
                mTagAdapter.notifyDataChanged();
            } else if (bean.getCode()==0) {
                ToastUtil.showShort(bean.getMsg());
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
        switch (v.getId()) {
            case R.id.tv_clear:
                //清空数据库
                deleteData();
                queryData("");
                break;
            case R.id.et_search:
                boolean hasData = hasData(mEtSearch.getText().toString().trim());
                if (!hasData&&!TextUtils.isEmpty(mEtSearch.getText().toString().trim())) {
                    insertData(mEtSearch.getText().toString().trim());
                    //搜索后显示数据库里所有搜索历史是为了测试
                    queryData("");
                }
                break;
            case R.id.iv_search:
                popSelf();
                break;
        }
    }
    /*插入数据*/
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }
    /*检查数据库中是否已经有该条记录*/
    private boolean hasData(String tempName) {
        //从Record这个表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }
    /*清空数据*/
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }
    /*模糊查询数据 并显示在ListView列表上*/
    private void queryData(String tempName) {
        //模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象,装入模糊搜索的结果

        mAdapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_1, cursor, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
