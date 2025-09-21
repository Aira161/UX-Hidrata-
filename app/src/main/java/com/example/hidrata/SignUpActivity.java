package com.example.hidrata;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SignUpActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPhone, etPassword;
    private ImageView btnClose, btnTogglePassword;
    private Button btnSignUp;
    private TextView tvLoginLink;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        etFullName = findViewById(R.id.et_full_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        btnClose = findViewById(R.id.btn_close);
        btnTogglePassword = findViewById(R.id.btn_toggle_password);
        btnSignUp = findViewById(R.id.btn_sign_up);
        tvLoginLink = findViewById(R.id.tv_login_link);
    }

    private void setupClickListeners() {
        // Botón cerrar
        btnClose.setOnClickListener(v -> finish());

        // Alternar visibilidad de contraseña
        btnTogglePassword.setOnClickListener(v -> togglePasswordVisibility());

        // Botón registrarse
        btnSignUp.setOnClickListener(v -> handleSignUp());

        // Enlace de inicio de sesión
        tvLoginLink.setOnClickListener(v -> navigateToLogin());

        // Configurar campos para mostrar teclado automáticamente
        setupAutoFocus();
    }

    private void setupAutoFocus() {
        // El campo de nombre completo obtendrá el foco automáticamente cuando se toque
        etFullName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                etFullName.requestFocus();
            }
        });

        // Configurar todos los EditText para mostrar el teclado
        View.OnClickListener showKeyboard = v -> {
            EditText editText = (EditText) v;
            editText.requestFocus();
        };

        etFullName.setOnClickListener(showKeyboard);
        etEmail.setOnClickListener(showKeyboard);
        etPhone.setOnClickListener(showKeyboard);
        etPassword.setOnClickListener(showKeyboard);
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Ocultar contraseña
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            btnTogglePassword.setImageResource(R.drawable.ic_eye);
            btnTogglePassword.setColorFilter(ContextCompat.getColor(this, R.color.grey_500));
            isPasswordVisible = false;
        } else {
            // Mostrar contraseña
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            btnTogglePassword.setImageResource(R.drawable.ic_eye_off);
            btnTogglePassword.setColorFilter(ContextCompat.getColor(this, R.color.primary_blue));
            isPasswordVisible = true;
        }

        // Mantener el cursor al final del texto
        etPassword.setSelection(etPassword.getText().length());
    }

    private void handleSignUp() {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validaciones básicas
        if (fullName.isEmpty()) {
            etFullName.setError("Ingresa tu nombre completo");
            etFullName.requestFocus();
            return;
        }

        if (email.isEmpty() || !isValidEmail(email)) {
            etEmail.setError("Ingresa un correo electrónico válido");
            etEmail.requestFocus();
            return;
        }

        if (phone.isEmpty() || phone.length() < 10) {
            etPhone.setError("Ingresa un número de teléfono válido");
            etPhone.requestFocus();
            return;
        }

        if (password.isEmpty() || password.length() < 6) {
            etPassword.setError("La contraseña debe tener al menos 6 caracteres");
            etPassword.requestFocus();
            return;
        }

        // Aquí iría la lógica de registro (llamada a API, etc.)
        processSignUp(fullName, email, phone, password);
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void processSignUp(String fullName, String email, String phone, String password) {
        // Mostrar loading o deshabilitar botón
        btnSignUp.setEnabled(false);
        btnSignUp.setText("Creando cuenta...");

        // Simular proceso de registro
        // En una aplicación real, aquí harías la llamada a tu API
        new android.os.Handler().postDelayed(() -> {
            // Simular respuesta exitosa
            Toast.makeText(SignUpActivity.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();

            // Restaurar botón
            btnSignUp.setEnabled(true);
            btnSignUp.setText("Crear una cuenta");

            // Redirigir a la pantalla principal o login
            finish();
        }, 2000);
    }

    private void navigateToLogin() {
        // Navegar a la pantalla de login
        // Intent intent = new Intent(this, LoginActivity.class);
        // startActivity(intent);

        // Por ahora solo mostrar un mensaje
        Toast.makeText(this, "Redirigiendo a inicio de sesión...", Toast.LENGTH_SHORT).show();
        finish();
    }
}