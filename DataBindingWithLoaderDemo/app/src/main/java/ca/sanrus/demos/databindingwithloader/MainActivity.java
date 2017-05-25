package ca.sanrus.demos.databindingwithloader;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ViewModel.ViewModelWatcher, View.OnClickListener {

    private ViewModel mViewModel;
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModel(this);

        setContentView(R.layout.activity_main);
        btnClick = (Button) findViewById(R.id.btn_click);
        btnClick.setOnClickListener(this);

        MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext());

        getLoaderManager().initLoader(1, new Bundle(), mViewModel);
        mViewModel.setViewModelWatcher(this);

        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME", "FIRST NAME -- TEST");
        contentValues.put("LAST_NAME", "LAST");
        getContentResolver().insert(Uri.parse("content://ca.sanrus.demos.databindingwithloader/PERSON_TABLE"), contentValues);
    }

    @Override
    public void onViewModelLoadFinished(ViewModel viewModel) {
        Log.d("MainActivity", "");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new TestFragment()).commit();
                break;
        }
    }
}
