package com.example.bbacr.ddw.content;
import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by hongchuanwei .
 * on 2017/2/23
 */
public class PreferenceManager {

    private static PreferenceManager mInstance;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private PreferenceManager() {
    }

    public static PreferenceManager instance() {
        if (mInstance == null) {
            mInstance = new PreferenceManager();
        }
        return mInstance;
    }
    public void init(Context context) {
        mSharedPreferences = context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        if (mSharedPreferences != null) {
            mEditor = mSharedPreferences.edit();
            mEditor.apply();
        }
    }
    /**
     * 保存token
     *
     * @param token :
     */
    public void saveToken(String token) {
        if (mEditor != null) {
            mEditor.putString(Key.TOKEN, token);
            mEditor.commit();
        }
    }
    public String getToken() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.TOKEN, "");
        }
        return "";
    }

    /**保存手机号
     * @param phone
     */
    public void savePhoneNum(String phone) {
        if (mEditor != null) {
            mEditor.putString(Key.PHONE, phone);
            mEditor.commit();
        }
    }
    public String getPhoneNum() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.PHONE, "");
        }
        return "";
    }
    /**保存的key
     * @param key
     */
    public void saveKey(String key) {
        if (mEditor != null) {
            mEditor.putString(Key.KEY, key);
            mEditor.commit();
        }
    }
    public String getKey() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.KEY, "");
        }
        return "";
    }



    /**地址详情
     * @param detail :
     */
    public void saveDetail(String detail) {
        if (mEditor != null) {
            mEditor.putString(Key.DETAIL, detail);
            mEditor.commit();
        }
    }

    public String getDetail() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.DETAIL, "");
        }
        return "";
    }
    /**
     * @param shopOrderId :
     */
    public void saveShopOrderId(String shopOrderId) {
        if (mEditor != null) {
            mEditor.putString(Key.SHOPORDERID, shopOrderId);
            mEditor.commit();
        }
    }

    public String getShopOrderId() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.SHOPORDERID, "");
        }
        return "";
    }
    /**
     * @param shopOrderId :
     */
    public void saveUseId(String shopOrderId) {
        if (mEditor != null) {
            mEditor.putString(Key.USEID, shopOrderId);
            mEditor.commit();
        }
    }

    public String getUseId() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.USEID, "");
        }
        return "";
    }
    /**
     * @param pwd :
     */
    public void savePwd(String pwd) {
        if (mEditor != null) {
            mEditor.putString(Key.PWD, pwd);
            mEditor.commit();
        }
    }

    public String getPwd() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.PWD, "");
        }
        return "";
    }
    /**
     * @param response :
     */
    public void saveResponse(String response) {
        if (mEditor != null) {
            mEditor.putString(Key.RESPONSE, response);
            mEditor.commit();
        }
    }
    public String getResponse() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.RESPONSE, "");
        }
        return "";
    }

    /**
     * @param accesstoken
     */
    public void saveAccessToken(String accesstoken) {
        if (mEditor != null) {
            mEditor.putString(Key.ACCESSTOKEN, accesstoken);
            mEditor.commit();
        }
    }
    public String getAccessToken() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.ACCESSTOKEN, "");
        }
        return "";
    }

    /**
     * @param openid
     */
    public void saveOpenId(String openid) {
        if (mEditor != null) {
            mEditor.putString(Key.OPENID, openid);
            mEditor.commit();
        }
    }
    public String getOpenId() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.OPENID, "");
        }
        return "";
    }
    /**
     * @param isfirstin
     */
    public void saveIsFirstIn(String isfirstin) {
        if (mEditor != null) {
            mEditor.putString(Key.ISFIRSTIN, isfirstin);
            mEditor.commit();
        }
    }
    public String getIsFirstIn() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.ISFIRSTIN, "");
        }
        return "";
    }
    /**
     * @param goodsid
     */
    public void saveGoodsId(String goodsid) {
        if (mEditor != null) {
            mEditor.putString(Key.GOODSID, goodsid);
            mEditor.commit();
        }
    }
    public String getGoodsId() {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(Key.GOODSID, "");
        }
        return "";
    }
    /**
     * 移除token
     */
    public void removeToken() {
        if (mEditor != null) {
            mEditor.remove(Key.TOKEN);
            mEditor.commit();
        }
    }
    public interface Key {
        String USERNAME = "username";
        String PHONE = "phone";
        String RESPONSE = "response";
        String TOKEN = "token";//保存的本地token
        String SHOPORDERID = "shopOrderId";
        String KEY = "key";
        String USEID = "useid";
        String DETAIL = "detail";//地址详情
        String PWD = "pwd";//密码
        String ACCESSTOKEN = "accesstoken";//授权token
        String OPENID = "openid";//授权openId
        String GOODSID = "goodsid";//商品的id
        String ISFIRSTIN = "isFirstIn";//第一次登录

    }
}
