package com.cnicola.sample.ormlite.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.cnicola.sample.ormlite.R;
import com.cnicola.sample.ormlite.db.provider.DatabaseManager;
import com.cnicola.sample.ormlite.db.provider.SampleOrmLiteHelper;
import com.cnicola.sample.ormlite.models.User;
import java.util.List;

public class HomeScreen extends Activity {

    protected SampleOrmLiteHelper dbHelper;

    protected EditText alias;
    protected EditText username;
    protected EditText password;
    protected TextView outputShowAll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        initViews();
        initDB();
    }

    protected void initViews() {

        alias = (EditText) findViewById(R.id.alias);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        outputShowAll = (TextView) findViewById(R.id.output);

        final View addUser = findViewById(R.id.add_user);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onAddUserClick();
                clearFields();
            }
        });

        final View showAllUsers = findViewById(R.id.show_all_users);
        showAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onShowAllUsersClick();
            }
        });
    }

    protected void initDB() {
        final DatabaseManager<SampleOrmLiteHelper> databaseManager = new DatabaseManager<>();
        dbHelper = databaseManager.getHelper(HomeScreen.this);
    }

    protected void onAddUserClick() {
        final String userAlias = alias.getText().toString();
        if (TextUtils.isEmpty(userAlias)) {
            Toast.makeText(HomeScreen.this, getString(R.string.error_empty_fields), Toast.LENGTH_SHORT).show();
            return;
        }
        final User user = new User(userAlias, username.getText().toString(), password.getText().toString());
        dbHelper.createUser(user);
        // retrieves a User object from the database by 'alias"
        final User fetchedUser = dbHelper.getUserByAlias(userAlias);
        outputShowAll.setText("Added: " + ((fetchedUser != null) ? fetchedUser.toString() : " NULL USER "));
    }

    protected void onShowAllUsersClick() {
        final List<User> allUsers = dbHelper.getAllUsers();
        if (allUsers.isEmpty()) {
            Toast.makeText(HomeScreen.this, getString(R.string.no_users_message), Toast.LENGTH_SHORT).show();
            return;
        }

        final StringBuilder builder = new StringBuilder();
        for (User user : allUsers) {
            builder.append(user.toString());
            builder.append("\n");
        }
        outputShowAll.setText(builder.toString());
    }

    protected void clearFields() {
        alias.setText("");
        username.setText("");
        password.setText("");
    }
}
