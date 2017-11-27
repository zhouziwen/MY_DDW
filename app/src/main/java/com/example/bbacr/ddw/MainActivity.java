package com.example.bbacr.ddw;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.bbacr.ddw.base.BaseActivity;
import com.example.bbacr.ddw.car.ShoppingCarTwoFragment;
import com.example.bbacr.ddw.centre.CenterFragment;
import com.example.bbacr.ddw.classify.view.ClassifyFragment;
import com.example.bbacr.ddw.home.HomeFragment;
import com.example.bbacr.ddw.mine.MineFragment;
import butterknife.Bind;
public class MainActivity extends BaseActivity {
    @Bind(R.id.bottom_home)
    TextView mBottomHome;
    @Bind(R.id.bottom_classifcation)
    TextView mBottomClassifcation;
    @Bind(R.id.bottom_super)
    TextView mBottomSuper;
    @Bind(R.id.bottom_shopping_cart)
    TextView mBottomShoppingCart;
    @Bind(R.id.bottom_my)
    TextView mBottomMy;
    @Bind(R.id.main_bottom)
    LinearLayout mMainBottom;
    @Bind(R.id.fl_content)
    FrameLayout mFlContent;
    private Fragment fg_home, fg_classifcation, fg_shopping_cart, fg_my, fg_super;
    private FragmentManager fManager;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        fManager = getSupportFragmentManager();
    }

    @Override
    public void initListener() {
        mBottomHome.setOnClickListener(this);
        mBottomClassifcation.setOnClickListener(this);
        mBottomShoppingCart.setOnClickListener(this);
        mBottomSuper.setOnClickListener(this);
        mBottomMy.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mBottomHome.performClick();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()) {
            case R.id.bottom_home:
                setSelected();
                mBottomHome.setSelected(true);
                if (null == fg_home) {
                    fg_home = new HomeFragment();
                    fTransaction.add(R.id.fl_content, fg_home);
                } else {
                    fTransaction.show(fg_home);
                }
                break;
            case R.id.bottom_classifcation:
                setSelected();
                mBottomClassifcation.setSelected(true);
                if (null == fg_classifcation) {
                    fg_classifcation = new ClassifyFragment();
                    fTransaction.add(R.id.fl_content, fg_classifcation);
                } else {
                    fTransaction.show(fg_classifcation);
                }
                break;
            case R.id.bottom_shopping_cart:
                setSelected();
                mBottomShoppingCart.setSelected(true);
                if (null == fg_shopping_cart) {
                    fg_shopping_cart = new ShoppingCarTwoFragment();
                    fTransaction.add(R.id.fl_content, fg_shopping_cart);
                } else {
                    fTransaction.show(fg_shopping_cart);
                }
                break;
            case R.id.bottom_my:
                setSelected();
                mBottomMy.setSelected(true);
                if (null == fg_my) {
                    fg_my = new MineFragment();
                    fTransaction.add(R.id.fl_content, fg_my);
                } else {
                    fTransaction.show(fg_my);
                }
                break;
            case R.id.bottom_super:
                setSelected();
                mBottomSuper.setSelected(true);
                if (null == fg_super) {
                    fg_super = new CenterFragment();
                    fTransaction.add(R.id.fl_content, fg_super);
                } else {
                    fTransaction.show(fg_super);
                }
                break;
        }
        fTransaction.commit();
    }
    //重置所有文本的选中状态
    private void setSelected() {
        mBottomHome.setSelected(false);
        mBottomClassifcation.setSelected(false);
        mBottomShoppingCart.setSelected(false);
        mBottomSuper.setSelected(false);
        mBottomMy.setSelected(false);
    }
    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg_home != null) fragmentTransaction.hide(fg_home);
        if (fg_classifcation != null) fragmentTransaction.hide(fg_classifcation);
        if (fg_shopping_cart != null) fragmentTransaction.hide(fg_shopping_cart);
        if (fg_my != null) fragmentTransaction.hide(fg_my);
        if (fg_super != null) fragmentTransaction.hide(fg_super);
    }
}
