package uteq.student.project.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uteq.student.project.examenfinal.ItemView.ItemView;
import uteq.student.project.examenfinal.LoadMoreView.LoadMoreView;
import uteq.student.project.examenfinal.models.Journals;
import uteq.student.project.examenfinal.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private Journals journals;
    private List<Journals> journalsArrayList;
    private RequestQueue requestQueue;
    private InfinitePlaceHolderView mLoadMoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        mLoadMoreView = (InfinitePlaceHolderView)findViewById(R.id.loadMoreView);
        getDataWebService();
    }

    private void setupView(List<Journals> feedList){
        Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + LoadMoreView.LOAD_VIEW_SET_COUNT);
        for(int i = 0; i < 3; i++){
            mLoadMoreView.addView(new ItemView(this.getApplicationContext(), feedList.get(i)));
        }
        mLoadMoreView.setLoadMoreResolver(new LoadMoreView(mLoadMoreView, feedList));
    }


    private void getDataWebService() {
        journalsArrayList = new ArrayList<>();
        String url = "https://revistas.uteq.edu.ec/ws/journals.php";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        for (int i = 0; i < size; i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                journals = new Journals();
                                journals.setJournal_id(jsonObject.getString("journal_id"));
                                journals.setPortada(jsonObject.getString("portada"));
                                journals.setAbbreviation(jsonObject.getString("abbreviation"));
                                journals.setName(jsonObject.getString("name"));
                                journalsArrayList.add(journals);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        setupView(journalsArrayList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HttpClient", "error: " + error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

}