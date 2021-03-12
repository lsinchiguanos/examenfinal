package uteq.student.project.examenfinal;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

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

import uteq.student.project.examenfinal.ItemView.ItemView;
import uteq.student.project.examenfinal.LoadMoreView.LoadMoreView;
import uteq.student.project.examenfinal.models.Journals;

public class MainActivity extends AppCompatActivity {

    private Journals journals;
    private ArrayList<Journals> journalsArrayList;
    private RequestQueue requestQueue;
    private InfinitePlaceHolderView mLoadMoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        mLoadMoreView = findViewById(R.id.loadMoreView);
        getDataWebService();
    }

    private void setupView(ArrayList<Journals> feedList){
        Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + LoadMoreView.LOAD_VIEW_SET_COUNT);
        for(int i = 0; i < LoadMoreView.LOAD_VIEW_SET_COUNT; i++){
            mLoadMoreView.addView(new ItemView(this.getApplicationContext(), feedList.get(i)));
        }
        /* EVITANDO MOSTRAR EL OTRO DE MAS */
        //mLoadMoreView.setLoadMoreResolver(new LoadMoreView(mLoadMoreView, feedList));
    }


    private void getDataWebService() {
        journalsArrayList = new ArrayList<>();
        String url = "https://revistas.uteq.edu.ec/ws/journals.php";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    int size = response.length();
                    for (int i = 0; i < size; i++){
                        try {
                            JSONObject jsonObject = new JSONObject(response.get(i).toString());
                            journals = new Journals();
                            journals.setJournal_id(jsonObject.getString("journal_id"));
                            journals.setPortada(jsonObject.getString("portada"));
                            journals.setAbbreviation(jsonObject.getString("abbreviation"));
                            journals.setDescripcion(jsonObject.getString("description"));
                            journals.setName(jsonObject.getString("name"));
                            journalsArrayList.add(journals);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    setupView(journalsArrayList);
                },
                error -> Log.e("HttpClient", "error: " + error.toString())
        );
        requestQueue.add(jsonObjectRequest);
    }

}