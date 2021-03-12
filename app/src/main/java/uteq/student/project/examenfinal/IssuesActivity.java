package uteq.student.project.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uteq.student.project.examenfinal.ItemView.ItemView;
import uteq.student.project.examenfinal.ItemView.ItemViewIssues;
import uteq.student.project.examenfinal.LoadMoreView.LoadMoreView;
import uteq.student.project.examenfinal.LoadMoreView.LoadMoreViewIssues;
import uteq.student.project.examenfinal.models.Issues;
import uteq.student.project.examenfinal.models.Journals;

public class IssuesActivity extends AppCompatActivity {

    private Issues issues;
    private ArrayList<Issues> issuesList;
    private RequestQueue requestQueue;
    private InfinitePlaceHolderView mLoadMoreView;
    private String journal_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        journal_id = getIntent().getExtras().getString("j_id");
        requestQueue = Volley.newRequestQueue(this);
        mLoadMoreView = findViewById(R.id.loadMoreView);
        getDataWebService();
    }

    private void setupView(ArrayList<Issues> feedList){
        Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + LoadMoreViewIssues.LOAD_VIEW_SET_COUNT);
        for(int i = 0; i < LoadMoreViewIssues.LOAD_VIEW_SET_COUNT; i++){
            mLoadMoreView.addView(new ItemViewIssues(this.getApplicationContext(), feedList.get(i)));
        }
        /* EVITANDO MOSTRAR EL OTRO DE MAS */
        mLoadMoreView.setLoadMoreResolver(new LoadMoreViewIssues(mLoadMoreView, feedList));
    }

    private void getDataWebService() {
        issuesList = new ArrayList<>();
        String url = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=" + journal_id;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                response -> {
                    int size = response.length();
                    for (int i = 0; i < size; i++){
                        try {
                            JSONObject jsonObject = new JSONObject(response.get(i).toString());
                            issues = new Issues();
                            issues.setIssue_id(jsonObject.getString("issue_id"));
                            issues.setVolume(jsonObject.getString("volume"));
                            issues.setNumber(jsonObject.getString("number"));
                            issues.setYear(jsonObject.getString("year"));
                            issues.setDate_published(jsonObject.getString("date_published"));
                            issues.setTitle(jsonObject.getString("title"));
                            issues.setDoi(jsonObject.getString("doi"));
                            issues.setCover(jsonObject.getString("cover"));
                            issuesList.add(issues);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    setupView(issuesList);
                },
                error -> Log.e("HttpClient", "error: " + error.toString())
        );
        requestQueue.add(jsonObjectRequest);
    }
}