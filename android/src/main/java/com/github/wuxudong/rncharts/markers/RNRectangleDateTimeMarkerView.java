package com.github.wuxudong.rncharts.markers;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.github.wuxudong.rncharts.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RNRectangleDateTimeMarkerView extends RNRectangleMarkerView {

    private TextView tvContent;
    private String mFormat;
    private Date mStartTime;

    public RNRectangleDateTimeMarkerView(Context context, long startTimeStamp, String format) {
        super(context);

        tvContent = (TextView) findViewById(R.id.rectangle_tvContent);

        mStartTime = new Date(startTimeStamp);
        mFormat = format;
    }

    private String getFormattedEntry(Entry e) {
        String text = Utils.formatNumber(e.getY(), 0, true) + "\n";
        Calendar c = Calendar.getInstance();
        c.setTime(mStartTime);
        c.add(Calendar.SECOND, (int) e.getX());
        Date axisTime = c.getTime();

        DateFormat df = new SimpleDateFormat(mFormat);
        String xEntry = df.format(axisTime);
        return text + xEntry;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);

        String text = getFormattedEntry(e);
        tvContent.setText(text);
    }
}

