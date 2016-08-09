package com.example.mylibrary.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.mylibrary.R;
import com.example.mylibrary.adapter.MyPopWindowAdapter;
import com.example.mylibrary.utils.DebugLog;
import com.example.mylibrary.utils.RuleUtils;

import java.util.List;

/**
 * 当前类注析：带模糊查询的Spinner
 * Created by huson on 2016/6/30.
 * 940762301@qq.com
 */
public class MySpinner extends LinearLayout{
    private Context mContext;
    private EditText editText;
    private PopupWindow mPopupWindow;
    private View contentView;
    private ListView lv_my_spinner;
    private MyPopWindowAdapter myPopWindowAdapter;
    private List<String> stringList;
    private OnSpinnerInterface mInterface;
    public MySpinner(Context context) {
        super(context);
    }

    public MySpinner(Context context, AttributeSet attrs){
        super(context, attrs);
        mContext = context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_my_spinner_main, this);
        editText = (EditText) findViewById(R.id.et_my_spinner);
        editText.addTextChangedListener(watcher);
        editText.setFocusable(true);


    }

    TextWatcher watcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            DebugLog.i(s.toString().trim());
            if (mInterface != null)
            mInterface.onEditTextValueChange(s.toString().trim());

        }};

    public void spOnKeyListener(OnKeyListener onKeyListener){
        editText.setOnKeyListener(onKeyListener);
    }
    public String getText(){
        return editText.getText().toString();
    }

    public void setText(String string){
        editText.setText(string);
    }

    public void setSelection(String string){
        editText.setText(string);
        editText.setSelection(string.length());
    }

    public void notifyList(){
        if (myPopWindowAdapter != null){
            myPopWindowAdapter.notifyDataSetChanged();
        }
    }

    public void setItemClick(){
        if (myPopWindowAdapter != null){
            myPopWindowAdapter.setOnClickListener(new MyPopWindowAdapter.OnClickItem() {
                @Override
                public void ItemClick(String string) {
                    editText.setText(string);
                    editText.setSelection(string.length());
                    dismiss();
                }
            });
        }
    }

    public void removePw(){
        if (mPopupWindow != null){
        }
        mPopupWindow = null;
    }

    public void dismiss(){
        if (mPopupWindow != null){
            mPopupWindow.dismiss();
        }
    }


    /**
     * type == 1线路
     * type == 2司机
     * type == 3车牌
     * @param type
     */

    public void showPopwindow(int type) {
        try {
            if (mPopupWindow != null){
                mPopupWindow.dismiss();
            }
                LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
                contentView = mLayoutInflater.inflate(R.layout.view_my_spinner, null);
                lv_my_spinner = (ListView) contentView.findViewById(R.id.lv_spinner);
                lv_my_spinner.setVerticalScrollBarEnabled(false);//隐藏滚动条
                int width = editText.getWidth();
            int height = 0;
//            if (type == 1){
//                height = lineInfos.size() * (int)(RuleUtils.convertDp2Px(mContext, 30) / 1);
//            }else if (type == 2){
//                height = driverInfos.size() * (int)(RuleUtils.convertDp2Px(mContext, 30) / 1);
//            }else {
//                height = carNumInfos.size() * (int)(RuleUtils.convertDp2Px(mContext, 30) / 1);
//            }
            if (height > (int)(RuleUtils.convertDp2Px(mContext, 150) / 1)){
                height = (int)(RuleUtils.convertDp2Px(mContext, 150) / 1);
            }
                //设置动画效果
            mPopupWindow = new PopupWindow(contentView,width, height);
//设置数据adapter
//            if (type == 1){
//                myPwLineAdapter = new MyPwLineAdapter(mContext,R.layout.item_pop_window, lineInfos);
//                lv_my_spinner.setAdapter(myPwLineAdapter);
//            }else if (type == 2){
//                myPwDriverAdapter = new MyPwDriverAdapter(mContext,R.layout.item_pop_window, driverInfos);
//                lv_my_spinner.setAdapter(myPwDriverAdapter);
//            }else {
//                myPwCarNumAdapter = new MyPwCarNumAdapter(mContext,R.layout.item_pop_window, carNumInfos);
//                lv_my_spinner.setAdapter(myPwCarNumAdapter);
//            }
            lv_my_spinner.setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    String keyCodeToString = KeyEvent.keyCodeToString(keyCode);
                    String substring = keyCodeToString.substring(keyCodeToString.length()-1);
                    String newString = editText.getText().toString().trim()+substring;
                    editText.setText(newString);
                    editText.setSelection(newString.length());
                    return true;
                }
            });


            // 设置外侧可点击
            mPopupWindow.setOutsideTouchable(true);

            // 设置背景色
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//            mPopupWindow.setFocusable(true);

            // 设置popupwindow弹出的动画
            mPopupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
            mPopupWindow.showAsDropDown(editText);

        } catch (Exception e) {
//            ExceptionUploadUtils.sendTryCatchException(mContext, String.valueOf(10001), e);
        }


    }

    public void setOnSpinnerInterface(OnSpinnerInterface mInterface){
        this.mInterface = mInterface;
    }

    public interface OnSpinnerInterface{
         void onEditTextValueChange(String str);
    }

    //设置pw
//    public void setLineItemClick(MyPwLineAdapter.OnClickItem onClickItem){
//        if (myPwLineAdapter != null){
//            myPwLineAdapter.setOnClickListener(onClickItem);
//
//        }
//    }
//
//    public void setDriverItemClick(MyPwDriverAdapter.OnClickItem onClickItem){
//        if (myPwDriverAdapter != null){
//            myPwDriverAdapter.setOnClickListener(onClickItem);
//
//        }
//    }
//
//    public void setCarNumItemClick(MyPwCarNumAdapter.OnClickItem onClickItem){
//        if (myPwCarNumAdapter != null){
//            myPwCarNumAdapter.setOnClickListener(onClickItem);
//
//        }
//    }
//
//    public void showLineSpinner(List<LineInfo> lineInfos){
//        this.lineInfos = lineInfos;
//        showPopwindow(1);
//
//    }
//
//    public void showDriverSpinner(List<DriverInfo> driverInfos){
//        this.driverInfos = driverInfos;
//        showPopwindow(2);
//    }
//
//    public void showCarNumSpinner(List<CarNumInfo> carNumInfos){
//        this.carNumInfos = carNumInfos;
//        showPopwindow(3);
//    }

}
