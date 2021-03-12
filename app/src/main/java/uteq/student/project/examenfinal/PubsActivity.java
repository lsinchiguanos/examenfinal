package uteq.student.project.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uteq.student.project.examenfinal.ItemView.ItemViewIssues;
import uteq.student.project.examenfinal.ItemView.ItemViewPubs;
import uteq.student.project.examenfinal.LoadMoreView.LoadMoreView;
import uteq.student.project.examenfinal.LoadMoreView.LoadMoreViewIssues;
import uteq.student.project.examenfinal.LoadMoreView.LoadMoreViewPubs;
import uteq.student.project.examenfinal.models.Issues;
import uteq.student.project.examenfinal.models.Journals;
import uteq.student.project.examenfinal.models.Pubs;

public class PubsActivity extends AppCompatActivity {

    private Pubs pubs;
    private ArrayList<Pubs> pubsArrayList;
    private RequestQueue requestQueue;
    private InfinitePlaceHolderView mLoadMoreView;
    private String issues_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubs);
        issues_id = getIntent().getExtras().getString("issues_id");
        requestQueue = Volley.newRequestQueue(this);
        mLoadMoreView = findViewById(R.id.loadMoreView);
        getDataWebService();
    }

    private void setupView(ArrayList<Pubs> feedList){
        Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + LoadMoreViewPubs.LOAD_VIEW_SET_COUNT);
        for(int i = 0; i < LoadMoreViewPubs.LOAD_VIEW_SET_COUNT; i++){
            mLoadMoreView.addView(new ItemViewPubs(this.getApplicationContext(), feedList.get(i)));
        }
        /* EVITANDO MOSTRAR EL OTRO DE MAS */
        mLoadMoreView.setLoadMoreResolver(new LoadMoreViewPubs(mLoadMoreView, feedList));
    }

    private void getDataWebService() {
        pubsArrayList = new ArrayList<>();
        String url = "https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" + issues_id;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                response -> {
                    int size = response.length();
                    for (int i = 0; i < size; i++){
                        try {
                            JSONObject jsonObject = new JSONObject(response.get(i).toString());
                            pubs = new Pubs();
                            pubs.setSection(jsonObject.getString("section"));
                            pubs.setPublication_id(jsonObject.getString("publication_id"));
                            pubs.setTitle(jsonObject.getString("title"));
                            pubs.setDoi(jsonObject.getString("doi"));
                            pubs.setPabstract(jsonObject.getString("abstract"));
                            pubs.setDate_published(jsonObject.getString("date_published"));
                            pubs.setSubmission_id(jsonObject.getString("submission_id"));
                            JSONArray jsonElements = jsonObject.getJSONArray("galeys");
                            for (int ix = 0; ix < jsonElements.length(); ix++) {
                                JSONObject object = jsonElements.getJSONObject(ix);
                                if (object.getString("label").equals("PDF") || object.getString("label").equals("pdf")) {
                                    pubs.setUrlViewGalley(object.getString("UrlViewGalley"));
                                }
                            }
                            pubsArrayList.add(pubs);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    setupView(pubsArrayList);
                },
                error -> Log.e("HttpClient", "error: " + error.toString())
        );
        requestQueue.add(jsonObjectRequest);
    }

}