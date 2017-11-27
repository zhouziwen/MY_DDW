package com.example.bbacr.ddw.mine.myorder;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.callback.OnPopWinDisMisBack;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.CustomPopupWindow;
import com.sunfusheng.glideimageview.GlideImageView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import static android.app.Activity.RESULT_OK;
/**
 * A simple {@link Fragment} subclass.
 * 申请退款界面
 */
public class ApplyRefundFragment extends BaseFragment {
    /**
     * 使用相册中的图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    /**
     * 使用照相机拍照获取图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    @Bind(R.id.glide_icon)
    GlideImageView mGlideIcon;
    @Bind(R.id.shopping_name)
    TextView mShoppingName;
    @Bind(R.id.goods_price)
    TextView mGoodsPrice;
    @Bind(R.id.goods_num)
    TextView mGoodsNum;
    @Bind(R.id.ic_normal_dot_left)
    TextView mIcNormalDotLeft;
    @Bind(R.id.ic_normal_dot_left2)
    TextView mIcNormalDotLeft2;
    @Bind(R.id.money)
    TextView mMoney;
    @Bind(R.id.explain)
    EditText mExplain;
    @Bind(R.id.pic)
    GlideImageView mPic;
    @Bind(R.id.scrollView)
    ScrollView mScrollView;
    @Bind(R.id.commit)
    Button mCommit;
    private CustomPopupWindow mPopupWindow;
    private int refundTypeValue = 1;
    private int refandReasonValue = 1;
    private String mPicUrl;
    public ApplyRefundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apply_refund, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }
    @Override
    protected void setListener() {
        super.setListener();
        mCommit.setOnClickListener(this);
        mPic.setOnClickListener(this);
        mIcNormalDotLeft.setOnClickListener(this);
        mIcNormalDotLeft2.setOnClickListener(this);
    }
    @Override
    protected void setData() {
        super.setData();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                refundApply(mArguments.getString("goodsOrderId"),String.valueOf(refundTypeValue),String.valueOf(refandReasonValue),mMoney.getText().toString(),mExplain.getText().toString(),"");
                break;
            case R.id.pic:
                choseHeadImageFromCameraCapture();
                break;
            case R.id.ic_normal_dot_left:
                showTopPop();
                break;
            case R.id.ic_normal_dot_left2:
                showBomPop();
                break;
        }
    }
    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
        } else {
            Toast.makeText(getContext(), "内存卡不存在",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_PIC_BY_TACK_PHOTO:
                break;
            case SELECT_PIC_BY_PICK_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Uri uri = data.getData();
                        LogUtils.d("地址====" + uri);
                        if (uri != null) {
                            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
                            if (cursor.moveToFirst()) {
                                String videoPath = cursor.getString(cursor.getColumnIndex("_data"));// 获取绝对路径
                                LogUtils.d("地址====" + videoPath);
                                File file = new File(videoPath);
                                if (!file.exists()) {
                                    startLoadingDialog();
                                    return;
                                }
                                startLoadingDialog("正在上传");
                                OkHttpUtils.post().tag(this)
                                        .url(Url.uploadImage)
                                        .addParams("token", PreferenceManager.instance().getToken())
                                        .addFile("file", file.getName(), file)
                                        .build().execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        closeLoadingDialog();
                                    }
                                    @Override
                                    public void onResponse(String response, int id) {
                                        LogUtils.d("qqqqqqq====" + response.substring(8, response.length()));
                                        mPicUrl = response.substring(8, response.length());
                                        mGlideIcon.loadImage(response.substring(8, response.length()), R.mipmap.load_pic);
//                                        OkHttpUtils.post().tag(this).url(Url.updateAvtor).addParams("avtor", response.substring(8, response.length())).
//                                                addParams("token", PreferenceManager.instance().getToken()).build()
//                                                .execute(new StringCallback() {
//                                                    @Override
//                                                    public void onError(Call call, Exception e, int id) {
//                                                        closeLoadingDialog();
//                                                    }
//                                                    @Override
//                                                    public void onResponse(String response, int id) {
//                                                        closeLoadingDialog();
//                                                        LogUtils.d("dhjahhffahhhfhf" + response);
//
//                                                    }
//                                                });
                                    }
                                });
                            }
                        } else {
                            Bitmap bm = (Bitmap) data.getExtras().get("data");
                            File file = getFile(bm);
                            String videoPath = file.getPath();
                            startLoadingDialog("正在上传");
                            OkHttpUtils.post().tag(this)
                                    .url(Url.uploadImage)
                                    .addParams("token", PreferenceManager.instance().getToken())
                                    .addFile("file", file.getName(), file)
                                    .build().execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    closeLoadingDialog();
                                }
                                @Override
                                public void onResponse(String response, int id) {
                                    LogUtils.d("qqqqqqq====" + response.substring(8, response.length()));

                                    OkHttpUtils.post().tag(this).url(Url.updateAvtor).addParams("avtor", response.substring(8, response.length())).
                                            addParams("token", PreferenceManager.instance().getToken()).build()
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onError(Call call, Exception e, int id) {
                                                    closeLoadingDialog();
                                                }

                                                @Override
                                                public void onResponse(String response, int id) {
                                                    LogUtils.d("dhjahhffahhhfhf" + response);
                                                    closeLoadingDialog();
                                                    mPicUrl = response.substring(8, response.length());
                                                    mGlideIcon.loadImage(response.substring(8, response.length()), R.mipmap.load_pic);

                                                }
                                            });
                                }
                            });
                        }
                    }
                    try {
                        Bundle extras = data.getExtras();
                        Bitmap myBitmap = (Bitmap) extras.get("data");
//                        mCircleImageView.setImageBitmap(myBitmap);
//                        map.clear();
//                        map.put("user_id", "32");
//                        UploadUtil uploadUtil = UploadUtil.getInstance(PersonCenterActivity.this);
//                        uploadUtil.setOnUploadProcessListener(PersonCenterActivity.this);
//                        String result = uploadUtil.uploadFile(, "pic", NetUtils.UPLOAD_PIC, map);
//                        StringUtils.showToast(PersonCenterActivity.this, "上传成功" + result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private File getFile(Bitmap bitmap) {
        String pictureDir = "";
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ByteArrayOutputStream baos = null;
        File file = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            String saveDir = Environment.getExternalStorageDirectory()
                    + "/dreamtownImage";
            File dir = new File(saveDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            file = new File(saveDir, ".jpg");
            file.delete();
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(byteArray);
            pictureDir = file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return file;
    }
    /**
     * @param goodsOrderId
     * @param refundTypeValue
     * @param refandReasonValue
     * @param refundMoney
     * @param refundExplain
     * @param picture           售货申请
     */
    private void refundApply(String goodsOrderId, String refundTypeValue, String refandReasonValue, String refundMoney, String refundExplain, String picture) {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("goodsOrderId", goodsOrderId);
        hashMap.put("refundTypeValue", refundTypeValue);
        hashMap.put("refandReasonValue", refandReasonValue);
        hashMap.put("refundMoney", refundMoney);
        hashMap.put("refundExplain", refundExplain);
        hashMap.put("picture", picture);
        HttpHelper.getInstance().post(Url.refundApply, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                closeLoadingDialog();
                LogUtils.d("refundApply" + response);
                BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {
                    ToastUtil.showShort("已提交申请");
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
                closeLoadingDialog();
                ToastUtil.showShort(error_msg);
            }
        });
    }

    /**
     *
     * 服务类型
     */
    private void showTopPop() {
        mPopupWindow = new CustomPopupWindow(getContext(),
                R.layout.apply_refund_item,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                new OnPopWinDisMisBack() {
                    @Override
                    public void onPopWindowDismiss() {
                    }
                }) {
            @Override
            public void setData(View view) {
                Button button = (Button) view.findViewById(R.id.submit);
                ImageView imageView = (ImageView) view.findViewById(R.id.action_list_bgView);
                ImageView back = (ImageView) view.findViewById(R.id.back_img);
                final RadioButton radioButton1 = (RadioButton) view.findViewById(R.id.btn1);
                final RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.btn2);
                RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (radioButton1.getId() == checkedId) {
                            refundTypeValue = 1;
                        } else if (radioButton2.getId() == checkedId) {
                            refundTypeValue = 2;
                        }
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (refundTypeValue == 1) {
                            mIcNormalDotLeft.setText("仅退款");
                        } else if (refundTypeValue == 2) {
                            mIcNormalDotLeft.setText("退货退款");
                        }
                        mPopupWindow.dismissWindow();
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });

            }
        };
        mPopupWindow.showAsDownWindow(mScrollView);
    }
    /**
     *
     * 退款原因
     */
    private void showBomPop() {
        mPopupWindow = new CustomPopupWindow(getContext(),
                R.layout.apply_refund_pop_item,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                new OnPopWinDisMisBack() {
                    @Override
                    public void onPopWindowDismiss() {

                    }
                }) {
            @Override
            public void setData(View view) {
                Button button = (Button) view.findViewById(R.id.submit);
                ImageView imageView = (ImageView) view.findViewById(R.id.action_list_bgView);
                ImageView back = (ImageView) view.findViewById(R.id.back_img);
                final RadioButton radioButton1 = (RadioButton) view.findViewById(R.id.btn1);
                final RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.btn2);
                final RadioButton radioButton3 = (RadioButton) view.findViewById(R.id.btn3);
                final RadioButton radioButton4 = (RadioButton) view.findViewById(R.id.btn4);
                final RadioButton radioButton5 = (RadioButton) view.findViewById(R.id.btn5);
                final RadioButton radioButton6 = (RadioButton) view.findViewById(R.id.btn6);
                final RadioButton radioButton7 = (RadioButton) view.findViewById(R.id.btn7);
                final RadioButton radioButton8 = (RadioButton) view.findViewById(R.id.btn8);
                final RadioButton radioButton9 = (RadioButton) view.findViewById(R.id.btn9);
                final RadioButton radioButton10 = (RadioButton) view.findViewById(R.id.btn10);
                final RadioButton radioButton11 = (RadioButton) view.findViewById(R.id.btn11);
                RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (radioButton1.getId() == checkedId) {
                            refandReasonValue = 1;
                        } else if (radioButton2.getId() == checkedId) {
                            refandReasonValue = 2;
                        } else if (radioButton3.getId() == checkedId) {
                            refandReasonValue = 3;
                        } else if (radioButton4.getId() == checkedId) {
                            refandReasonValue = 4;
                        } else if (radioButton5.getId() == checkedId) {
                            refandReasonValue = 5;
                        } else if (radioButton6.getId() == checkedId) {
                            refandReasonValue = 6;
                        } else if (radioButton7.getId() == checkedId) {
                            refandReasonValue = 7;
                        } else if (radioButton8.getId() == checkedId) {
                            refandReasonValue = 8;
                        } else if (radioButton9.getId() == checkedId) {
                            refandReasonValue = 9;
                        } else if (radioButton10.getId() == checkedId) {
                            refandReasonValue = 10;
                        } else if (radioButton11.getId() == checkedId) {
                            refandReasonValue = 11;
                        }
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (refandReasonValue == 1) {
                            mIcNormalDotLeft2.setText("我不想要了");
                        } else if (refandReasonValue == 2) {
                            mIcNormalDotLeft2.setText("收到商品破损");
                        } else if (refandReasonValue == 3) {
                            mIcNormalDotLeft2.setText("商品错发/漏发");
                        } else if (refandReasonValue == 4) {
                            mIcNormalDotLeft2.setText("商品需要维修");
                        } else if (refandReasonValue == 5) {
                            mIcNormalDotLeft2.setText("发票问题");
                        } else if (refandReasonValue == 6) {
                            mIcNormalDotLeft2.setText("收到商品与描述不符");
                        } else if (refandReasonValue == 7) {
                            mIcNormalDotLeft2.setText("做工瑕疵");
                        } else if (refandReasonValue == 8) {
                            mIcNormalDotLeft2.setText("商品质量问题");
                        } else if (refandReasonValue == 9) {
                            mIcNormalDotLeft2.setText("未按约定时间发货");
                        } else if (refandReasonValue == 10) {
                            mIcNormalDotLeft2.setText("假冒品牌");
                        } else if (refandReasonValue == 11) {
                            mIcNormalDotLeft2.setText("商品补偿");
                        }
                        mPopupWindow.dismissWindow();
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismissWindow();
                    }
                });

            }
        };
        mPopupWindow.showAsDownWindow(mScrollView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
