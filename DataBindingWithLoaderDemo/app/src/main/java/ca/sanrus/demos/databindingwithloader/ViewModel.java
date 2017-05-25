package ca.sanrus.demos.databindingwithloader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by santhoshgutta on 2017-05-24.
 */

public class ViewModel implements LoaderManager.LoaderCallbacks<Cursor> {

    private ViewModelWatcher viewModelWatcher;
    private Context context;
    private boolean enableButton;
    private String firstName;

    public interface ViewModelWatcher {
        void onViewModelLoadFinished(ViewModel viewModel);
    }

    public void setViewModelWatcher(ViewModelWatcher viewModelWatcher) {
        this.viewModelWatcher = viewModelWatcher;
    }

    public ViewModel() {}

    public ViewModel(Context context) {
        this.context = context;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Log.d("ViewModel", "onCreateLoader()");
        return new CursorLoader(context, Uri.parse("content://ca.sanrus.demos.databindingwithloader/PERSON_TABLE"), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor data) {
        Log.d("ViewModel", "onLoadFinished() = " + data.getCount());

        if(data.getCount() > 0) {
            data.moveToFirst();
            firstName = data.getString(1);
        }

        if(viewModelWatcher != null) {
            viewModelWatcher.onViewModelLoadFinished(this);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.d("ViewModel", "onLoaderReset()");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
