package ca.sanrus.demos.databindingwithloader;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.sanrus.demos.databindingwithloader.databinding.TestFragmentLayoutBinding;


/**
 * Created by santhoshgutta on 2017-05-24.
 */

public class TestFragment extends Fragment implements ViewModel.ViewModelWatcher {

    private ViewModel viewModel;
    private TestFragmentLayoutBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModel(getActivity());
        viewModel.setViewModelWatcher(this);
        getLoaderManager().initLoader(2, new Bundle(), viewModel);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test_fragment_layout, null, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onViewModelLoadFinished(ViewModel viewModel) {
        binding.setViewModel(viewModel);
        Log.d("", "");
    }
}
