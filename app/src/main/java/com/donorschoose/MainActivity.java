package com.donorschoose;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CheckableImageButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mTextMessage;
    private ImageButton search_button;
    private  TextView title;
    private  TextView description;
    private TextView teacherName;
    private TextView schoolName;
    private TextView donorN;
    private  TextView needN;
    private ImageView imageDonor;
    private DonorAsyncTask task;
    private ListView lvProposal;
    private int itemMenu;
    private List<Donor.Proposal> listProposal;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    itemMenu = -1;
                    task = new DonorAsyncTask (MainActivity.this,mTextMessage.getText().toString(),"-1");
                    task.execute();
                    return true;
                case R.id.navigation_dashboard:
                    itemMenu = -2;
                    task = new DonorAsyncTask (MainActivity.this,mTextMessage.getText().toString(),"-2");
                    task.execute();

                    return true;
                case R.id.navigation_notifications:
                    itemMenu = -6;
                    task = new DonorAsyncTask (MainActivity.this,mTextMessage.getText().toString(),"-6");
                    task.execute();

                    return true;
                case R.id.ultimo:
                    itemMenu = -4;
                    task = new DonorAsyncTask (MainActivity.this,mTextMessage.getText().toString(),"-4");
                    task.execute();

                    return true;
                case R.id.penultimo:
                    itemMenu = -3;
                    task = new DonorAsyncTask (MainActivity.this,mTextMessage.getText().toString(),"-3");
                    task.execute();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (EditText) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        task = new DonorAsyncTask (MainActivity.this,mTextMessage.getText().toString(),"-1");
        task.execute();

        imageDonor = (ImageView) findViewById(R.id.imageDonor);
        imageDonor.setVisibility(View.VISIBLE);
        search_button = (ImageButton) findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                task = new DonorAsyncTask (MainActivity.this,mTextMessage.getText().toString(),""+itemMenu);
                task.execute();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:



                if(itemMenu==0){
                task = new DonorAsyncTask (MainActivity.this,mTextMessage.getText().toString(),"-1");
                }else{
                    task = new DonorAsyncTask (MainActivity.this,mTextMessage.getText().toString(),""+itemMenu);
                }
                task.execute();
                return true;
            default:
                return true;

        }
    }


    private class DonorAsyncTask extends AsyncTask<Void, Void, Void> {


        private Activity activity;
        private String keyword;
        private String subject;
        ProgressDialog progress;
        public DonorAsyncTask(Activity activity,String keyword,String subject) {
            this.keyword = keyword;
            this.activity = activity;
            this.subject = subject;
            progress = new ProgressDialog(activity);

        }

        protected void setActivity(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("App", "calling on pre");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progress.setTitle("Wait...");
                    progress.setMessage("loading");
                    progress.show();
                }
            });

        }

        @Override
        protected Void doInBackground(Void... params) {

            RestClient.get().getInfo("CA","0 TO 2000", "5",subject, keyword, "DONORSCHOOSE", new Callback<Donor>() {
                @Override
                public void success(final Donor donor, Response response) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (progress.isShowing()) {
                                progress.dismiss();
                            }



                            listProposal = donor.getProposals();
                            lvProposal = (ListView) findViewById(R.id.lvProposal);
                            lvProposal.setAdapter(new ProposalAdapter(MainActivity.this, listProposal));

                            lvProposal.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                                 @Override
                                                                 public void onItemClick(AdapterView<?> parent,
                                                                                         View view, int position, long id)
                                                                 {

                                                                     Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(listProposal.get(position).getProposalURL()));
                                                                     startActivity(browserIntent);
                                                                 }
                                                             }
                            );
                            if(listProposal.isEmpty()){
                                imageDonor.setVisibility(View.VISIBLE);
                            }else{
                                imageDonor.setVisibility(View.INVISIBLE);
                            }




                        }
                    });



                }

                @Override
                public void failure(RetrofitError error) {

                    if (progress.isShowing()) {
                        progress.dismiss();
                        imageDonor.setVisibility(View.VISIBLE);
                        Toast.makeText(activity, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }

                }
            });


            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }











}
