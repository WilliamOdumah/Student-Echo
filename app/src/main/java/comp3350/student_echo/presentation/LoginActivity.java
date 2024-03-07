package comp3350.student_echo.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.student_echo.R;
import comp3350.student_echo.application.Main;
import comp3350.student_echo.business.LoginManager;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {copyDatabaseToDevice();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void buttonSubmitOnClick(View v){

        // get login info
        EditText Username=(EditText)findViewById(R.id.TextUsername);
        EditText Password=(EditText)findViewById(R.id.TextPassword);;
        String username=Username.getText().toString().trim();
        String password=Password.getText().toString().trim();

        // try login
        boolean success = LoginManager.performLogin(username, password);

        // go to home page if successful
        if(success) {
            Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
            LoginActivity.this.startActivity(loginIntent);
        } else {
            Toast.makeText(this, "Check your username and password!",Toast.LENGTH_LONG).show();
        }
    }

    public void buttonSignupOnClick(View v){
        Intent signUpIntent= new Intent(LoginActivity.this, SignUpActivity.class);
        LoginActivity.this.startActivity(signUpIntent);
    }

    // DB STUFF FROM SAMPLE PROJECT BELOW
    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            //Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
            //TODO: UPDATE ERROR MESSAGE
            System.out.println("Unable to access application data: " + ioe.getMessage());
        }
    }
    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}