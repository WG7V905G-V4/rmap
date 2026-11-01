package com.example.rmap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rmap.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        // данные, присланные из RegisterActivity (null, если пришли не оттуда)
        String savedUser = getIntent().getStringExtra("username");
        String savedPass = getIntent().getStringExtra("password");
        if (savedUser != null) binding.etUser.setText(savedUser);

        binding.btnLogin.setOnClickListener(v -> {
            String user = binding.etUser.getText().toString().trim();
            String pass = binding.etPass.getText().toString();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, R.string.msg_fill_all, Toast.LENGTH_SHORT).show();
            } else if (savedUser == null) {
                Toast.makeText(this, R.string.msg_register_first, Toast.LENGTH_SHORT).show();
            } else if (user.equals(savedUser) && pass.equals(savedPass)) {
                Toast.makeText(this, R.string.msg_login_ok, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.msg_login_bad, Toast.LENGTH_SHORT).show();
            }
        });

        binding.tvToRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });
    }
}
