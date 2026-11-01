package com.example.rmap;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rmap.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        binding.btnRegister.setOnClickListener(v -> {
            String user = binding.etRegUser.getText().toString().trim();
            String pass = binding.etRegPass.getText().toString();
            String confirm = binding.etRegConfirm.getText().toString();

            if (user.isEmpty()) {
                binding.etRegUser.setError(getString(R.string.err_required));
                return;
            }
            if (pass.length() < 6) {
                binding.etRegPass.setError(getString(R.string.err_short));
                return;
            }
            if (!pass.equals(confirm)) {
                binding.etRegConfirm.setError(getString(R.string.err_mismatch));
                return;
            }

            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("username", user);
            intent.putExtra("password", pass);
            startActivity(intent);
            finish();
        });
    }
}
