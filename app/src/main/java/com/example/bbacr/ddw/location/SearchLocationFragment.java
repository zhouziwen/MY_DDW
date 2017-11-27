package com.example.bbacr.ddw.location;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.adapter.listview.RecomandAdapter;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchLocationFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    @Bind(R.id.back_img)
    ImageView mBackImg;
    @Bind(R.id.search)
    EditText mSearch;
    @Bind(R.id.list_view)
    ListView mListView;
    private RecomandAdapter mRecomandAdapter;
    private RouteTask mRouteTask;
    public SearchLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_location, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        mRecomandAdapter=new RecomandAdapter(getContext());
        mListView.setAdapter(mRecomandAdapter);
        mListView.setOnItemClickListener(this);
        mRouteTask=RouteTask.getInstance(getContext());
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(RouteTask.getInstance(getContext()).getStartPoint()==null){
                    Toast.makeText(getContext(), "检查网络，Key等问题", Toast.LENGTH_SHORT).show();
                    return;
                }
                InputTipTask.getInstance( mRecomandAdapter).searchTips(getContext(),s.toString(),
                        RouteTask.getInstance(getContext()).getStartPoint().city);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PositionEntity entity = (PositionEntity) mRecomandAdapter.getItem(position);
        if (entity.latitue == 0 && entity.longitude == 0) {
            PoiSearchTask poiSearchTask=new PoiSearchTask(getContext(), mRecomandAdapter);
            poiSearchTask.search(entity.address,RouteTask.getInstance(getContext()).getStartPoint().city);
        } else {
            mRouteTask.setEndPoint(entity);
            mRouteTask.search();
           popSelf();
        }
    }
}
