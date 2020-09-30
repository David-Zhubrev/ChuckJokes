package com.appdav.chuckjokes.ui.jokes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.appdav.chuckjokes.jokegetter.Joke;
import com.appdav.chuckjokes.JokesRecyclerAdapter;
import com.appdav.chuckjokes.ui.activities.MainActivity;
import com.appdav.chuckjokes.PresenterProvider;
import com.appdav.chuckjokes.R;
import com.appdav.chuckjokes.SnackbarInterface;

import java.util.List;

public class JokesFragment extends Fragment implements JokesFragmentInterface.View {

    private boolean isEmpty = true;

    private EditText editText;
    private Button button;
    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private ProgressBar progressBar;

    private JokesRecyclerAdapter adapter;

    private JokesFragmentInterface.Presenter presenter;
    private SnackbarInterface snackbarInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_jokes, container, false);
        button = v.findViewById(R.id.button);
        recyclerView = v.findViewById(R.id.recyclerView);
        editText = v.findViewById(R.id.editText);
        editText.setOnEditorActionListener((v1, actionId, event) -> {
            presenter.onButtonClicked(((EditText) v1).getText().toString());
            return false;
        });
        tvEmpty = v.findViewById(R.id.tvEmpty);
        progressBar = v.findViewById(R.id.progressBar);
        presenter = PresenterProvider.getJokesPresenterInstance();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        snackbarInterface = (MainActivity) getActivity();
        assert snackbarInterface != null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(v ->
                presenter.onButtonClicked(editText.getText().toString()));
        presenter.attachView(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.viewIsReady();
    }


    @Override
    public void setActive(List<Joke> jokes) {
        if (jokes == null || jokes.isEmpty()) {
            setEmpty();
        } else {
            setFull(jokes);
        }
    }

    @Override
    public void setRefreshing() {
        button.setEnabled(false);
        tvEmpty.setVisibility(View.GONE);
        if (!isEmpty) {
            recyclerView.setAlpha(0.6f);
        }
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWrongFormatErrorMessage() {
        snackbarInterface.showWrongFormatMessage();
    }

    @Override
    public void showRequestErrorMessage() {
        snackbarInterface.showFailureMessage(() ->
                presenter.onButtonClicked(editText.getText().toString()));
    }

    private void setEmpty() {
        isEmpty = true;
        tvEmpty.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.INVISIBLE);
        editText.setEnabled(true);
        button.setEnabled(true);
    }

    private void setFull(List<Joke> jokes) {
        isEmpty = false;
        tvEmpty.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAlpha(1f);
        button.setEnabled(true);
        editText.setEnabled(true);
        if (adapter == null) {
            adapter = new JokesRecyclerAdapter(jokes);
            recyclerView.setAdapter(adapter);
        } else adapter.updateAdapter(jokes);
        if (editText.getText().toString().isEmpty()) {
            editText.setText(String.valueOf(jokes.size()));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

}