package uteq.student.project.examenfinal.ItemView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import uteq.student.project.examenfinal.IssuesActivity;
import uteq.student.project.examenfinal.R;
import uteq.student.project.examenfinal.models.Issues;
import uteq.student.project.examenfinal.models.Pubs;

public class ItemViewPubs {
    @View(R.id.section)
    private TextView sectionTxt;

    @View(R.id.title)
    private TextView titleTxt;

    @View(R.id.abstracts)
    private TextView abstractsTxt;

    @View(R.id.date_published)
    private TextView date_publishedTxt;

    @View(R.id.doi)
    private TextView doiTxt;

    @View(R.id.publication_id)
    private TextView publication_idText;

    private Pubs mInfo;
    private Context mContext;

    public ItemViewPubs(Context context, Pubs info) {
        mContext = context;
        mInfo = info;
    }

    @SuppressLint("SetTextI18n")
    @Resolve
    private void onResolved() {
        publication_idText.setText(mInfo.getPublication_id());
        titleTxt.setText(mInfo.getTitle());
        abstractsTxt.setText(mInfo.getPabstract());
        doiTxt.setText(mInfo.getDoi());
        date_publishedTxt.setText(mInfo.getDate_published());
        sectionTxt.setText(mInfo.getSection());
    }

    @Click(R.id.rootView)
    public void onCardClick() {
        //Toast.makeText(mContext, mInfo.getIssue_id(), Toast.LENGTH_SHORT).show();
        getPDF();
    }

    private void getPDF() {
    }
}
