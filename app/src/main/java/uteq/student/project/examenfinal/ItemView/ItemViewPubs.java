package uteq.student.project.examenfinal.ItemView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import uteq.student.project.examenfinal.R;
import uteq.student.project.examenfinal.models.Pubs;

@Layout(R.layout.load_more_item_view_pubs)
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
        abstractsTxt.setText(Html.fromHtml(mInfo.getPabstract()));
        doiTxt.setText(mInfo.getDoi());
        date_publishedTxt.setText(mInfo.getDate_published());
        sectionTxt.setText(mInfo.getSection());
    }

    @Click(R.id.rootView)
    public void onCardClick() {
        getPDF();
    }

    private void getPDF() {
        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mInfo.getUrlViewGalley() + ".pdf")));
    }
}
