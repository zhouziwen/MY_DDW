package com.example.bbacr.ddw.mine.myorder;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.CommentImageActivity;
import com.example.bbacr.ddw.EvaluateActivity;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.my.OrderComment;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.home.evaluate.KeyBoardManager;
import com.example.bbacr.ddw.home.evaluate.PermissionCheckUtil;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.BitmapUtils;
import com.example.bbacr.ddw.utils.FileUtils;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.StringUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sunfusheng.glideimageview.GlideImageView;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Call;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToEvaluateFragment extends BaseFragment implements TextWatcher {

    @Bind(R.id.sdv_goods_img)
    GlideImageView mSdvGoodsImg;
    @Bind(R.id.iv_comment_star_1)
    ImageView mIvCommentStar1;
    @Bind(R.id.iv_comment_star_2)
    ImageView mIvCommentStar2;
    @Bind(R.id.iv_comment_star_3)
    ImageView mIvCommentStar3;
    @Bind(R.id.iv_comment_star_4)
    ImageView mIvCommentStar4;
    @Bind(R.id.iv_comment_star_5)
    ImageView mIvCommentStar5;
    @Bind(R.id.et_comment_content)
    EditText mEtCommentContent;
    @Bind(R.id.hsv_comment_imgs)
    HorizontalScrollView mHsvCommentImgs;
    @Bind(R.id.iv_choose_goods_pic)
    ImageView mIvChooseGoodsPic;
    private List<ImageView> starList = new ArrayList<>();
    private List<String> imageUrls = new ArrayList<>();//所有晒图图片路径
    private int currentStarCount;
    private List<String> mPath = new ArrayList<>();
    private TextView mBack;
    private TextView mPublish;
    private InputMethodManager manager;
    public static final String KEY_IMAGE_LIST = "imageList";
    public static final String KEY_CURRENT_INDEX = "currentIndex";
    private final int REQUEST_CODE_PICTURE = 1;
    private final int RESULT_CODE_LARGE_IMAGE = 1;
    //晒单图片最多选择四张
    private final int MAX_PIC = 5;
    private String mShopOrderId;
    private String goodsId;
    private String id;
    private String content;
    private List<OrderComment.DatasBean.GoodsListBean> mList = new ArrayList<>();
    public ToEvaluateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_evaluate, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mPublish = mFindViewUtils.findViewById(R.id.app_title_action);
        currentStarCount = 5;//默认为五星好评
        manager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        starList.add(mIvCommentStar1);
        starList.add(mIvCommentStar2);
        starList.add(mIvCommentStar3);
        starList.add(mIvCommentStar4);
        starList.add(mIvCommentStar5);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBack.setOnClickListener(this);
        mPublish.setOnClickListener(this);
        mIvChooseGoodsPic.setOnClickListener(this);
        mIvCommentStar1.setOnClickListener(this);
        mIvCommentStar2.setOnClickListener(this);
        mIvCommentStar3.setOnClickListener(this);
        mIvCommentStar4.setOnClickListener(this);
        mIvCommentStar4.setOnClickListener(this);
        mEtCommentContent.addTextChangedListener(this);
    }

    @Override
    protected void setData() {
        super.setData();
        orderComment(PreferenceManager.instance().getShopOrderId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_title_action:
                if (validateComment()) {
                    //评价提交
                    addComments(mShopOrderId,"[{\"goodId\":\""+goodsId+"\","+"\"orderId\":\""+id+"\","+"\"content\":\""+content+"\","+"\"gradedValue\":"+"\""+currentStarCount+"\""+","+"\"imgUrl\":"+"\""+ StringUtils.arrayToStr(mPath,",")+"\""+"}]");
                }
                break;
            case R.id.app_title_back:
              popSelf();
                break;
            case R.id.iv_choose_goods_pic:
                //检查是否有打开照相机和文件读写的权限
                if (PermissionCheckUtil.checkCameraAndExternalStoragePermission(mBaseActivity))
                    //权限已经开启, 调出图片选择界面
                    MultiImageSelector.create().count(MAX_PIC - imageUrls.size()).start(this, REQUEST_CODE_PICTURE);
                break;
            case R.id.iv_comment_star_1:
                currentStarCount = 1;
                break;
            case R.id.iv_comment_star_2:
                currentStarCount = 2;
                break;
            case R.id.iv_comment_star_3:
                currentStarCount = 3;
                break;
            case R.id.iv_comment_star_4:
                currentStarCount = 4;
                break;
            case R.id.iv_comment_star_5:
                currentStarCount = 5;
                break;
            default:
                break;
        }
        for (int i = 0, len = starList.size(); i < len; i++) {
            starList.get(i).setImageResource(i < currentStarCount ? R.mipmap.icon_comment_star_red : R.mipmap.icon_comment_star_gray);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_PICTURE) {
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                imageUrls.addAll(path);
                handleCommentPicList(imageUrls, false);
            }
        } else if (resultCode == RESULT_CODE_LARGE_IMAGE) {
            //晒单大图页返回, 重新设置晒单图片
            handleCommentPicList(imageUrls = data.getStringArrayListExtra(KEY_IMAGE_LIST), true);
        }
    }
    /**
     * 处理选择的评价图片
     *
     * @param paths      图片的路径集合
     * @param isFromBack 是否来自LargeImageActivity返回
     */
    private void handleCommentPicList(final List<String> paths, boolean isFromBack) {
        LinearLayout rootview = new LinearLayout(getContext());
        View commentView;
        SimpleDraweeView sdv_pic;
        if (mPath!=null) {
            mPath.clear();
        }
        for (int i = 0, len = paths.size(); i < len; i++) {
            commentView =getActivity().getLayoutInflater().inflate(R.layout.order_comment_pic, null);
            sdv_pic = (SimpleDraweeView) commentView.findViewById(R.id.sdv_pic);
            File file = new File(paths.get(i));
            if (isFromBack) {
                OkHttpUtils.post().tag(this)
                        .url(Url.uploadImage).addFile("file",file.getName(),file).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("图片集合"+response);
                        mPath.add(response.substring(8, response.length()));
                    }
                });
                sdv_pic.setImageURI(Uri.parse("file://" + paths.get(i)));
            } else {
                //来自图片选择器
                String path = FileUtils.getCachePath(getContext());//获取app缓存路径来存放临时图片
                BitmapUtils.compressImage(paths.get(i), path, 95);
                sdv_pic.setImageURI(Uri.parse("file://" + path));
                OkHttpUtils.post().tag(this)
                        .url(Url.uploadImage).addFile("file",file.getName(),file).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("图片集合"+response);
                        mPath.add(response.substring(8, response.length()));
                    }
                });
                imageUrls.set(i, path);
            }
            final int finalI = i;
            sdv_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击HorizontalScrollView里的晒单图进入图片详情页
                    Intent intent = new Intent(getActivity(),CommentImageActivity.class);
                    intent.putExtra(KEY_CURRENT_INDEX, finalI);
                    intent.putStringArrayListExtra(KEY_IMAGE_LIST, (ArrayList<String>) paths);
                    startActivityForResult(intent, REQUEST_CODE_PICTURE);
                }
            });
            AutoUtils.auto(commentView);
            rootview.addView(commentView);
        }
        mHsvCommentImgs.removeAllViews();
        mHsvCommentImgs.addView(rootview);
    }
    /**
     * 评价内容验证
     */
    private boolean validateComment() {
        content = mEtCommentContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            content = "产品非常好";
            return true;
        }
        return true;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        content = mEtCommentContent.getText().toString();
        if (content.length() >= 255) {
            Toast.makeText(getContext(), "评论内容不能多于255个字符", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void afterTextChanged(Editable s) {
    }
    /**
     * 添加评论
     * http://localhost/commentsC/addComments.json?token=jjjjjjjjjj&shopOrderId=12&datas=
     * [{"goodId":2,"content":1ooo,"gradedValue":"3","imgUrl":"img1"},{"goodId":3,"content":2ooo,"gradedValue":"4","imgUrl":"img2"}]
     *
     */
    private void addComments(String shopOrderId,String datas){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("shopOrderId", shopOrderId);
        hashMap.put("datas", datas);
        HttpHelper.getInstance().post(Url.addComments, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("addComments"+response);
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode()==1) {
                    ToastUtil.showShort(bean.getMsg());
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                    mBaseActivity.showActivity(LoginActivity.class, null);
                } else {
                    ToastUtil.showShort(bean.getMsg());
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
    /**
     * @param shopOrderId
     * 要评价的商品
     */
    private void orderComment(final String  shopOrderId){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("shopOrderId", shopOrderId);
        HttpHelper.getInstance().post(Url.orderComment, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                OrderComment bean = JsonUtils.parse(response, OrderComment.class);
                if (bean.getCode()==1) {
                    mShopOrderId = bean.getDatas().getShopOrderId();
                    mList.addAll(bean.getDatas().getGoodsList());
                    goodsId = String.valueOf(mList.get(0).getGoodsId());
                    id = String.valueOf(mList.get(0).getId());
                    mSdvGoodsImg.loadImage(mList.get(0).getGoodsImg(), R.mipmap.ic_launcher);
                } else if (bean.getCode() == -1) {
                    ToastUtil.showShort(bean.getMsg());
                    PreferenceManager.instance().removeToken();
                  mBaseActivity.showActivity(LoginActivity.class,null);
                } else {
                    ToastUtil.showShort(bean.getMsg());
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtil.showShort(error_msg);
            }
        });
    }
}
