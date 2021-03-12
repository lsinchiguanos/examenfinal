package uteq.student.project.examenfinal.ItemView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import uteq.student.project.examenfinal.IssuesActivity;
import uteq.student.project.examenfinal.R;
import uteq.student.project.examenfinal.models.Issues;
import uteq.student.project.examenfinal.models.Journals;

@Layout(R.layout.load_more_item_view_issues)
public class ItemViewIssues {

    @View(R.id.issuesid)
    private TextView issuesidTxt;

    @View(R.id.title)
    private TextView titleTxt;

    @View(R.id.datos)
    private TextView datosTxt;

    @View(R.id.date_published)
    private TextView date_publishedTxt;

    @View(R.id.doi)
    private TextView doiTxt;

    @View(R.id.cover)
    private ImageView cover;

    private Issues mInfo;
    private Context mContext;

    public ItemViewIssues(Context context, Issues info) {
        mContext = context;
        mInfo = info;
    }

    @SuppressLint("SetTextI18n")
    @Resolve
    private void onResolved() {
        issuesidTxt.setText(mInfo.getIssue_id());
        titleTxt.setText(mInfo.getTitle());
        datosTxt.setText("Volumen: " + mInfo.getVolume() + " - Número: " + mInfo.getNumber() +  " - Año: " + mInfo.getYear());
        doiTxt.setText(mInfo.getDoi());
        date_publishedTxt.setText(mInfo.getDate_published());
        Glide.with(mContext).load(mInfo.getCover()).into(cover);
    }

    @Click(R.id.rootView)
    public void onCardClick() {
        //Toast.makeText(mContext, mInfo.getIssue_id(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext, IssuesActivity.class);
        intent.putExtra("issues_id", mInfo.getIssue_id());
        Log.d("issues_id", "onClick");
        mContext.startActivity(intent);
    }

}
