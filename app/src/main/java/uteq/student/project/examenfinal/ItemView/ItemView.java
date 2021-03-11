package uteq.student.project.examenfinal.ItemView;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

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

}
