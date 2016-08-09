package com.example.mylibrary.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;


import com.example.mylibrary.R;
import com.example.mylibrary.utils.OptAnimationLoader;
import com.example.mylibrary.utils.ToastHelper;

import java.util.Calendar;


/**
 * Created by huson on 2015/10/14.
 */
public class MyDialog extends AlertDialog {
    private String imgurl;
    private Context backContext;
    private Activity backactivity;
    private String mtitle;
    private String mtype;
    private long mtotal;
    private long mcurrent;
    private String mmesg;
    private TextView title;
    private TextView line;
    private TextView mesg;
    private TextView tv_percent;
    private LinearLayout ll_tn;
    private ProgressBar dialog_progressbar;
    private ImageView imageView;
    private Button cancel;
    private Button query;
    private EditText editText;
    private DatePicker datePicker;
    private TimePicker timePicker;

    private View mDialogView;
    private AnimationSet mModalInAnim;
    private AnimationSet mModalOutAnim;
    private boolean mCloseFromCancel;

    private Calendar calendar = Calendar.getInstance();
    private int myear;
    private int mmonth;
    private int mday;
    private int mhour;
    private int mminute;

    public static final String HAVEBUTTON = "havebutton";
    public static final String NOBUTTON = "nobutton";
    public static final String ONEBUTTON = "onebutton";
    public static final String PROGRESS = "progress";
    private static final String IMAGEVIEW = "imageview";
    public static final String EDITTEXT = "edittext";
    public static final String DATEPICKER = "datepicker";
    public static final String TIMEPICKER = "timepicker";

    /**
     * mtype=0版本更新 ，mtype=1进度条，mtype=2图片， mtype=3注销
     *
     * @param context
     * @param url
     */

    public MyDialog(Activity context, String url) {
        super(context);
        backactivity = context;
        imgurl = url;
        mtype = IMAGEVIEW;
    }

    public MyDialog(Context context, int theme, Activity activity) {
        super(context, theme);
        backContext = context;
        backactivity = activity;

    }

    public MyDialog(Activity activity, String title, String mesg, String type) {
        super(activity);
//        backContext = activity;
        backactivity = activity;
        mtitle = title;
        mmesg = mesg;
        mtype = type;

        mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
        mModalOutAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_out);
        mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCloseFromCancel) {
                            MyDialog.super.cancel();
                        } else {
                            MyDialog.super.dismiss();
                        }
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mydialog);
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        imageView = (ImageView) findViewById(R.id.img_dialog_station);
        dialog_progressbar = (ProgressBar) findViewById(R.id.dialog_progressbar);
        title = (TextView) findViewById(R.id.title_img);
        line = (TextView) findViewById(R.id.line_img);
        mesg = (TextView) findViewById(R.id.mesage_img);
        tv_percent = (TextView) findViewById(R.id.tv_percent);
        ll_tn = (LinearLayout) findViewById(R.id.ll_dialog);
        cancel = (Button) findViewById(R.id.cancel_dialog);
        query = (Button) findViewById(R.id.query_dialog);
        editText = (EditText) findViewById(R.id.et_mydialog);
        datePicker = (DatePicker) findViewById(R.id.datepicker);
        timePicker = (TimePicker) findViewById(R.id.timepicker);
        title.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
        mesg.setVisibility(View.GONE);
        ll_tn.setVisibility(View.GONE);
        tv_percent.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);
        datePicker.setVisibility(View.GONE);
        timePicker.setVisibility(View.GONE);

        initView();


    }

    private void initView() {

        switch (mtype) {
            case HAVEBUTTON:
                title.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                mesg.setVisibility(View.VISIBLE);
                ll_tn.setVisibility(View.VISIBLE);
                title.setText(mtitle);
                mesg.setText(mmesg);
                break;
            case ONEBUTTON:
                title.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                mesg.setVisibility(View.VISIBLE);
                ll_tn.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.GONE);
                title.setText(mtitle);
                mesg.setText(mmesg);
                break;
            case PROGRESS:
                tv_percent.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                title.setText(mtitle);
                dialog_progressbar.setVisibility(View.VISIBLE);
                setCanceledOnTouchOutside(false);

                break;
            case IMAGEVIEW://未可用
                imageView.setVisibility(View.VISIBLE);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
                break;
            case NOBUTTON:
                title.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                mesg.setVisibility(View.VISIBLE);
                ll_tn.setVisibility(View.INVISIBLE);
                title.setText(mtitle);
                mesg.setText(mmesg);
                break;
            case EDITTEXT:
                title.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                editText.setFocusable(true);
                ll_tn.setVisibility(View.VISIBLE);
                title.setText(mtitle);
                mesg.setText(mmesg);

                break;
            case DATEPICKER:
                title.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                ll_tn.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.VISIBLE);
                title.setText(mtitle);
                myear = calendar.get(Calendar.YEAR);
                mmonth = calendar.get(Calendar.MONTH);
                mday = calendar.get(Calendar.DAY_OF_MONTH);
                datePicker.init(myear, mmonth, mday, Datelistener);

                break;
            case TIMEPICKER:
                timePicker.setIs24HourView(true);
                title.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                ll_tn.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.VISIBLE);
                title.setText(mtitle);
                mhour = calendar.get(Calendar.HOUR_OF_DAY);
                mminute = calendar.get(Calendar.MINUTE);
                timePicker.setOnTimeChangedListener(Timelistener);
                break;

        }
    }

    public void setContentColor(int color){
        mesg.setTextColor(color);
    }

    public void ProgressPlan(long total, float current) {

        dialog_progressbar.setMax((int) total);
        dialog_progressbar.setProgress((int) current);
        if (dialog_progressbar.getMax() != 0) {
            int i = dialog_progressbar.getProgress() * 100 / dialog_progressbar.getMax();
            tv_percent.setText(String.valueOf(i) + "%");
        } else {
            ToastHelper.showToast("当前网络状态不良,请稍后重试", backactivity);
        }
    }
    public void setProgress(int progress){
        dialog_progressbar.setProgress(progress);
        tv_percent.setText(String.valueOf(progress) + "%");
    }

    public void ButtonCancel(View.OnClickListener listener) {
        if (listener == null)
            listener = btnListener;
        cancel.setOnClickListener(listener);
    }

    public void ButtonQuery(View.OnClickListener listener) {
        if (listener == null)
            listener = btnListener;
        query.setOnClickListener(listener);
    }

    public void SetButtonText(String calaeltext, String querytext) {
        cancel.setText(calaeltext);
        query.setText(querytext);
    }

    public String getEtText() {
        return editText.getText().toString();
    }

    public String getYear() {
        return String.valueOf(myear);
    }

    public String getMonth() {
        return String.valueOf(mmonth);
    }

    public String getDay() {
        return String.valueOf(mday);
    }

    public String getWholeTime() {
        String time = String.valueOf(setText(mhour) + ":" + setText(mminute));
        return time;
    }

    public String getWholeDate() {
        String mdate = String.valueOf(myear + "-" + mmonth + "-" + mday);
        return mdate;
    }

    private DatePicker.OnDateChangedListener Datelistener = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myear = year;
            mmonth = monthOfYear + 1;
            mday = dayOfMonth;
        }
    };

    private TimePicker.OnTimeChangedListener Timelistener = new TimePicker.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            mhour = hourOfDay;
            mminute = minute;
        }
    };


    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private String setText(int i) {
        String string = String.valueOf(i);
        if (i < 10) {
            if (String.valueOf(i).length() != 2) {
                string = "0" + String.valueOf(i);
            }
        }
        return string;
    }


    @Override
    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);
    }
}
