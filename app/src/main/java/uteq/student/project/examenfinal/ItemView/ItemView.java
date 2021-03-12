package uteq.student.project.examenfinal.ItemView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import uteq.student.project.examenfinal.IssuesActivity;
import uteq.student.project.examenfinal.R;
import uteq.student.project.examenfinal.models.Journals;

@Layout(R.layout.load_more_item_view)
public class ItemView {

    @View(R.id.name)
    private TextView nameTxt;

    @View(R.id.abreviatura)
    private TextView abreviaturaTxt;

    @View(R.id.journalid)
    private TextView journalidTxt;

    @View(R.id.portada)
    private ImageView portada;

    private Journals mInfo;
    private Context mContext;

    public ItemView(Context context, Journals info) {
        mContext = context;
        mInfo = info;
    }

    @Resolve
    private void onResolved() {
        nameTxt.setText(mInfo.getName());
        abreviaturaTxt.setText(mInfo.getAbbreviation());
        journalidTxt.setText(mInfo.getJournal_id());
        Glide.with(mContext).load(mInfo.getPortada()).into(portada);
    }

    @Click(R.id.rootView)
    public void onCardClick() {
        //Toast.makeText(mContext, mInfo.getJournal_id(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext, IssuesActivity.class);
        intent.putExtra("j_id", mInfo.getJournal_id());
        Log.d("j_id", "onClick");
        mContext.startActivity(intent);
    }

}
