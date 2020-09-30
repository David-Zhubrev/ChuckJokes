package com.appdav.chuckjokes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.appdav.chuckjokes.R;
import com.appdav.chuckjokes.SnackbarInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements SnackbarInterface {

    private Snackbar snackbarWrongFormat, snackbarBackPressed;
    private View snackbarAnchorView;
    private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = findViewById(R.id.rootViewMain);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        snackbarAnchorView = navView;

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        // Creating custom ActionBar titles for every tab
        ActionBar actionBar = getSupportActionBar();
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            assert actionBar != null;
            switch (destination.getId()) {
                case R.id.navigation_jokes:
                    actionBar.setTitle(R.string.title_jokes);
                    break;
                case R.id.navigation_web:
                    actionBar.setTitle(R.string.title_api_info);
                    break;
            }
        });
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void showFailureMessage(Callback callback) {
        Snackbar snackbarFailure = Snackbar.make(root, R.string.message_failure, BaseTransientBottomBar.LENGTH_LONG);
        snackbarFailure.setAnchorView(snackbarAnchorView);
        snackbarFailure.setAction(R.string.button_retry, v ->
                callback.onSnackbarButtonClicked());
        snackbarFailure.show();
    }

    @Override
    public void showWrongFormatMessage() {
        if (snackbarWrongFormat == null) {
            snackbarWrongFormat = Snackbar.make(root, R.string.message_wrong_format, BaseTransientBottomBar.LENGTH_SHORT);
            snackbarWrongFormat.setAnchorView(snackbarAnchorView);
        }
        if (!snackbarWrongFormat.isShown()) snackbarWrongFormat.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (snackbarBackPressed == null) {
            snackbarBackPressed = Snackbar.make(root, R.string.message_press_back, BaseTransientBottomBar.LENGTH_SHORT);
            snackbarBackPressed.setAnchorView(snackbarAnchorView);
        }
        if (!snackbarBackPressed.isShown()) snackbarBackPressed.show();
        else finish();
    }
}
