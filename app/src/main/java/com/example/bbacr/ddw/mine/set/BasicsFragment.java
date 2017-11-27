package com.example.bbacr.ddw.mine.set;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbacr.ddw.R;
import com.example.bbacr.ddw.activity.LoginActivity;
import com.example.bbacr.ddw.adapter.listview.PopBaseAdapter;
import com.example.bbacr.ddw.base.BaseFragment;
import com.example.bbacr.ddw.bean.BaseBean;
import com.example.bbacr.ddw.bean.my.Info;
import com.example.bbacr.ddw.content.FragmentTag;
import com.example.bbacr.ddw.content.PreferenceManager;
import com.example.bbacr.ddw.content.Url;
import com.example.bbacr.ddw.eventbus.EventBase;
import com.example.bbacr.ddw.eventbus.EventBean;
import com.example.bbacr.ddw.okthhp.HttpHelper;
import com.example.bbacr.ddw.okthhp.response.RawResponseHandler;
import com.example.bbacr.ddw.utils.DisPlayUtils;
import com.example.bbacr.ddw.utils.JsonUtils;
import com.example.bbacr.ddw.utils.LogUtils;
import com.example.bbacr.ddw.utils.ShowFragmentUtils;
import com.example.bbacr.ddw.utils.ToastUtil;
import com.example.bbacr.ddw.widget.SingleChoiceDialog;
import com.sunfusheng.glideimageview.GlideImageView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * 基本资料
 */
public class BasicsFragment extends BaseFragment {
    /**
     * 使用相册中的图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    /**
     * 使用照相机拍照获取图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    @Bind(R.id.iv_photo_person_ziliao)
    GlideImageView mIvPhotoPersonZiliao;
    @Bind(R.id.text_name)
    TextView mTextName;
    @Bind(R.id.user_name)
    LinearLayout mUserName;
    @Bind(R.id.text_sex)
    TextView mTextSex;
    @Bind(R.id.sex_person)
    LinearLayout mSexPerson;
    @Bind(R.id.text_birthday)
    TextView mTextBirthday;
    @Bind(R.id.birthday)
    LinearLayout mBirthday;
    @Bind(R.id.text_phone)
    TextView mTextPhone;
    @Bind(R.id.phone)
    LinearLayout mPhone;
    @Bind(R.id.text_we_chat)
    TextView mTextWeChat;
    @Bind(R.id.WeChat)
    LinearLayout mWeChat;
    @Bind(R.id.text_qq)
    TextView mTextQq;
    @Bind(R.id.qq)
    LinearLayout mQq;
    @Bind(R.id.text_mail)
    TextView mTextMail;
    @Bind(R.id.mail_box)
    LinearLayout mMailBox;
    private ListView mListView;
    private PopupWindow mPopupWindow;
    private int mInt;
    private List<String> sex = new ArrayList<>();
    public BasicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basics, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        sex.add("男");
        sex.add("女");
        sex.add("隐私");
    }
    @Override
    protected void setListener() {
        super.setListener();
        mIvPhotoPersonZiliao.setOnClickListener(this);
        mSexPerson.setOnClickListener(this);
    }
    @Override
    protected void setData() {
        super.setData();

        info();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_photo_person_ziliao:
                avatar();
                break;
            case R.id.sex_person:
                initPopWindow(sex);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextSex.setText(sex.get(position));
                        if (sex.get(position).equals("男")) {
                            mInt = 4;
                        } else if (sex.get(position).equals("女")) {
                            mInt = 7;
                        } else {
                            mInt = 1;
                        }
//                        PreferenceManager.instance().saveSex(sex.get(position));
                        sex();
                        mPopupWindow.dismiss();
                    }
                });
                break;
        }
    }
    //修改性别
    private void sex(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        hashMap.put("sexValue", String.valueOf(mInt));
        HttpHelper.getInstance().post(Url.Alter_Sex, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                final BaseBean bean = JsonUtils.parse(response, BaseBean.class);
                if (bean.getCode() == 1) {

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
    /**
     * 初始化poupwindow
     */
    private void initPopWindow(List<String> datas) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
//		// 引入窗口配置文件
        View view = inflater.inflate(R.layout.poup_window_small, null,false);
        mListView = (ListView) view.findViewById(R.id.listview_pop);
        mPopupWindow = new PopupWindow(view,  DisPlayUtils.dip2px(52f)*2, WindowManager.LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.popupAnimation);
        mListView.setAdapter(new PopBaseAdapter(getContext(),datas));
        // 设置此参数获得焦点，否则无法点击
        mPopupWindow.setFocusable(true);
//        Toast.makeText(getContext(),"gg",Toast.LENGTH_SHORT).show();
        mPopupWindow.showAsDropDown(mTextSex, 0, 25);
    }
    /**
     * 设置头像
     */
    private void avatar() {
        List<String> list = new ArrayList<>();
        list.add("拍照");
        list.add("相册");
        new SingleChoiceDialog.Builder(getContext()).addList(list)
                .setTitle("请选择")
                .setOnItemClickListener(new SingleChoiceDialog.OnItemClickListener() {
                    @Override
                    public void OnItemClick(String title, int position) {
                        if (position == 0) {//拍照
                            choseHeadImageFromCameraCapture();
                        } else if (position == 1) {//相册
                            choseHeadImageFromGallery();
                        }
                    }
                }).show();
    }
    //调用系统相册
    private void choseHeadImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
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
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        try {
                            //获得图片的uri
//                            Uri orginalUri = data.getData();
                            Uri orginalUri = geturi(data);
                            String[] pojo = { MediaStore.Images.Media.DATA };
                            Cursor cursor = getActivity().managedQuery(orginalUri, pojo, null, null,
                                    null);
                            if (cursor != null) {
                                ContentResolver cr = getContext().getContentResolver();
                                int colunm_index = cursor
                                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                                cursor.moveToFirst();
//                                mPath = cursor.getString(colunm_index);
//                                LogUtils.d("图片"+mPath);
                                File file = new File(cursor.getString(colunm_index));
//                                if(!file.exists()){
////                                    showLoading();
//                                    return;
//                                }
                                startLoadingDialog("正在上传");
                                OkHttpUtils.post().tag(this)
                                        .url(Url.uploadImage)
//                                        .addParams(IAppKey.TOKEN,PreferenceManager.instance().getToken())
                                        .addFile("file",file.getName(),file)
                                        .build().execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        closeLoadingDialog();
                                    }
                                    @Override
                                    public void onResponse(String response, int id) {
                                        LogUtils.d("fshjfjjhfjjkajkjkajka===="+response.substring(8, response.length()));
                                        OkHttpUtils.post().tag(this).url(Url.updateAvtor).addParams("avtor", response.substring(8, response.length())).
                                                addParams("token",PreferenceManager.instance().getToken()).build()
                                                .execute(new StringCallback() {
                                                    @Override
                                                    public void onError(Call call, Exception e, int id) {
                                                      closeLoadingDialog();
                                                    }
                                                    @Override
                                                    public void onResponse(String response, int id) {
                                                        LogUtils.d("dhjahhffahhhfhf"+response);
                                                       closeLoadingDialog();
                                                        EventBus.getDefault().post(new EventBase());
                                                    }
                                                });
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
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
                                        OkHttpUtils.post().tag(this).url(Url.updateAvtor).addParams("avtor", response.substring(8, response.length())).
                                                addParams("token", PreferenceManager.instance().getToken()).build()
                                                .execute(new StringCallback() {
                                                    @Override
                                                    public void onError(Call call, Exception e, int id) {
                                                        closeLoadingDialog();
                                                    }

                                                    @Override
                                                    public void onResponse(String response, int id) {
                                                        closeLoadingDialog();
                                                        LogUtils.d("dhjahhffahhhfhf" + response);
                                                       EventBus.getDefault().post(new EventBase());
                                                    }
                                                });
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
                                                    EventBus.getDefault().post(new EventBase());
                                                    closeLoadingDialog();
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
     * 解决小米手机上获取图片路径为null的情况
     * @param intent
     * @return
     */
    public Uri geturi(android.content.Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = getActivity().getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.ImageColumns._ID },
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }

    /**
     * 基本资料
     */
    private void info() {
        startLoadingDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", PreferenceManager.instance().getToken());
        HttpHelper.getInstance().post(Url.info, hashMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.d("info" + response);
                closeLoadingDialog();
                Info bean = JsonUtils.parse(response, Info.class);
                if (bean.getCode() == 1) {
                    mIvPhotoPersonZiliao.loadCircleImage(bean.getDatas().getAvotorr(), R.mipmap.default_img);
                    mTextName.setText(bean.getDatas().getRealName());
                    mTextBirthday.setText(bean.getDatas().getBirthday());
                    mTextSex.setText(bean.getDatas().getSex());
                    mTextPhone.setText(bean.getDatas().getName());
                    mTextQq.setText(bean.getDatas().getQq());
                    mTextMail.setText(bean.getDatas().getEmail());
                    mTextWeChat.setText(bean.getDatas().getWeixin());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.user_name, R.id.sex_person, R.id.birthday, R.id.phone, R.id.WeChat, R.id.qq, R.id.mail_box})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_name:
                mArguments.putString("title","修改昵称");
                mArguments.putString("name","昵称");
                mArguments.putString("name_hint","请输入昵称");
                ShowFragmentUtils.showFragment(getActivity(),ModificationFragment.class, FragmentTag.MODIFICATIONFRAGMENT,mArguments,true);
                break;
            case R.id.sex_person:
                break;
            case R.id.birthday:
                mArguments.putString("title","修改生日");
                mArguments.putString("name","生日");
                mArguments.putString("name_hint","请输入生日");
                ShowFragmentUtils.showFragment(getActivity(),ModificationFragment.class, FragmentTag.MODIFICATIONFRAGMENT,mArguments,true);
                break;
            case R.id.phone:
                mArguments.putString("title","修改手机号");
                mArguments.putString("name","手机号");
                mArguments.putString("name_hint","请输入手机号");
                ShowFragmentUtils.showFragment(getActivity(),ModificationFragment.class, FragmentTag.MODIFICATIONFRAGMENT,mArguments,true);
                break;
            case R.id.WeChat:
                mArguments.putString("title","修改微信号");
                mArguments.putString("name","微信");
                mArguments.putString("name_hint","请输入微信");
                ShowFragmentUtils.showFragment(getActivity(),ModificationFragment.class, FragmentTag.MODIFICATIONFRAGMENT,mArguments,true);
                break;
            case R.id.qq:
                mArguments.putString("title","修改QQ");
                mArguments.putString("name","QQ");
                mArguments.putString("name_hint","请输入QQ");
                ShowFragmentUtils.showFragment(getActivity(),ModificationFragment.class, FragmentTag.MODIFICATIONFRAGMENT,mArguments,true);
                break;
            case R.id.mail_box:
                mArguments.putString("title","修改邮箱");
                mArguments.putString("name","邮箱");
                mArguments.putString("name_hint","请输入邮箱");
                ShowFragmentUtils.showFragment(getActivity(),ModificationFragment.class, FragmentTag.MODIFICATIONFRAGMENT,mArguments,true);
                break;
        }
    }
}
