package com.example.test;

import androidx.credentials.CredentialManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.EdgeToEdge;

import com.google.android.libraries.identity.googleid.GetGoogleIdOption;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    // --- EDIT THESE ---
    private static final String SUPABASE_URL = "https://<YOUR_PROJECT_REF>.supabase.co";
    private static final String SUPABASE_ANON_KEY = "<YOUR_ANON_KEY>"; // safe: anon key, not service_role
    private static final String REDIRECT_URI = "com.example.test://callback";
    // --------------------

    private static final String PREFS = "auth_prefs";
    private static final String KEY_CODE_VERIFIER = "code_verifier";
    private static final String KEY_STATE = "oauth_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button loginButton = findViewById(R.id.buttonLoginGoogle);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(LoginActivity.this, "ANISHA ISCH DUMB", Toast.LENGTH_SHORT).show();
                startSignIn();
            }
        });
    }

    private void startSignIn() {
        CredentialManager credentialManager = CredentialManager.create(this);
        GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId("")
                .setNonce("")
                .build();
    }

//    // 1) Start OAuth: generate PKCE, open Supabase authorize URL in Custom Tab
//    private void startSignIn() {
//        try {
//            String codeVerifier = generateCodeVerifier();
//            String codeChallenge = generateCodeChallenge(codeVerifier);
//            String state = generateRandomState();
//
//            // save verifier and state to prefs temporarily
//            getSharedPreferences(PREFS, MODE_PRIVATE)
//                    .edit()
//                    .putString(KEY_CODE_VERIFIER, codeVerifier)
//                    .putString(KEY_STATE, state)
//                    .apply();
//
//            String authorizeUrl =
//                    SUPABASE_URL + "/auth/v1/authorize"
//                            + "?provider=google"
//                            + "&redirect_to=" + Uri.encode(REDIRECT_URI)
//                            + "&response_type=code"
//                            + "&code_challenge=" + Uri.encode(codeChallenge)
//                            + "&code_challenge_method=S256"
//                            + "&state=" + Uri.encode(state)
//                            + "&scope=" + Uri.encode("openid email profile");
//
//            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
//            customTabsIntent.launchUrl(this, Uri.parse(authorizeUrl));
//        } catch (Exception e) {
//            Log.e(TAG, "startSignIn error", e);
//        }
//    }
}