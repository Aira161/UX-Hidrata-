package com.example.hidrata;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class CrearAlarma extends AppCompatActivity {

    private EditText etAlarmName;
    private TextView btnOn, btnOff;
    private TextView tvFromTime, tvUntilTime, tvIntervalTime;
    private TextView btnFromAM, btnFromPM, btnUntilAM, btnUntilPM;
    private TextView tvMessageName;
    private ImageView btnBack, btnDropdown, btnSettings;
    private Button btnSave;

    // Estado de la alarma
    private boolean isAlarmOn = true;
    private boolean isFromAM = true;
    private boolean isUntilAM = true;
    private int fromHour = 9, fromMinute = 41;
    private int untilHour = 10, untilMinute = 41;
    private int intervalMinute = 0, intervalSecond = 41;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alarma);

        initViews();
        setupClickListeners();
        updateUI();
    }

    private void initViews() {
        etAlarmName = findViewById(R.id.et_alarm_name);
        btnOn = findViewById(R.id.btn_on);
        btnOff = findViewById(R.id.btn_off);
        tvFromTime = findViewById(R.id.tv_from_time);
        tvUntilTime = findViewById(R.id.tv_until_time);
        tvIntervalTime = findViewById(R.id.tv_interval_time);
        btnFromAM = findViewById(R.id.btn_from_am);
        btnFromPM = findViewById(R.id.btn_from_pm);
        btnUntilAM = findViewById(R.id.btn_until_am);
        btnUntilPM = findViewById(R.id.btn_until_pm);
        tvMessageName = findViewById(R.id.tv_message_name);
        btnBack = findViewById(R.id.btn_back);
        btnDropdown = findViewById(R.id.btn_dropdown);
        btnSettings = findViewById(R.id.btn_settings);
        btnSave = findViewById(R.id.btn_save);
    }

    private void setupClickListeners() {
        // Botón atrás
        btnBack.setOnClickListener(v -> finish());

        // Toggle On/Off
        btnOn.setOnClickListener(v -> toggleAlarmState(true));
        btnOff.setOnClickListener(v -> toggleAlarmState(false));

        // Selectores AM/PM para "Desde"
        btnFromAM.setOnClickListener(v -> toggleFromAMPM(true));
        btnFromPM.setOnClickListener(v -> toggleFromAMPM(false));

        // Selectores AM/PM para "Hasta"
        btnUntilAM.setOnClickListener(v -> toggleUntilAMPM(true));
        btnUntilPM.setOnClickListener(v -> toggleUntilAMPM(false));

        // Time pickers
        tvFromTime.setOnClickListener(v -> showFromTimePicker());
        tvUntilTime.setOnClickListener(v -> showUntilTimePicker());
        tvIntervalTime.setOnClickListener(v -> showIntervalTimePicker());

        // Dropdown de mensajes
        btnDropdown.setOnClickListener(v -> showMessageOptions());

        // Configuraciones
        btnSettings.setOnClickListener(v -> openSettings());

        // Guardar alarma
        btnSave.setOnClickListener(v -> saveAlarm());

        // Focus en nombre de alarma
        etAlarmName.setOnClickListener(v -> etAlarmName.requestFocus());
    }

    private void toggleAlarmState(boolean isOn) {
        isAlarmOn = isOn;
        updateToggleButtons();
    }

    private void updateToggleButtons() {
        if (isAlarmOn) {
            btnOn.setBackgroundResource(R.drawable.toggle_selected);
            btnOn.setTextColor(ContextCompat.getColor(this, R.color.black));
            btnOff.setBackgroundResource(R.drawable.toggle_unselected);
            btnOff.setTextColor(ContextCompat.getColor(this, R.color.grey_300));
        } else {
            btnOff.setBackgroundResource(R.drawable.toggle_selected);
            btnOff.setTextColor(ContextCompat.getColor(this, R.color.black));
            btnOn.setBackgroundResource(R.drawable.toggle_unselected);
            btnOn.setTextColor(ContextCompat.getColor(this, R.color.grey_300));
        }
    }

    private void toggleFromAMPM(boolean isAM) {
        isFromAM = isAM;
        updateFromAMPMButtons();
    }

    private void updateFromAMPMButtons() {
        if (isFromAM) {
            btnFromAM.setBackgroundResource(R.drawable.toggle_selected);
            btnFromAM.setTextColor(ContextCompat.getColor(this, R.color.black));
            btnFromPM.setBackgroundResource(R.drawable.toggle_unselected);
            btnFromPM.setTextColor(ContextCompat.getColor(this, R.color.grey_300));
        } else {
            btnFromPM.setBackgroundResource(R.drawable.toggle_selected);
            btnFromPM.setTextColor(ContextCompat.getColor(this, R.color.black));
            btnFromAM.setBackgroundResource(R.drawable.toggle_unselected);
            btnFromAM.setTextColor(ContextCompat.getColor(this, R.color.grey_300));
        }
    }

    private void toggleUntilAMPM(boolean isAM) {
        isUntilAM = isAM;
        updateUntilAMPMButtons();
    }

    private void updateUntilAMPMButtons() {
        if (isUntilAM) {
            btnUntilAM.setBackgroundResource(R.drawable.toggle_selected);
            btnUntilAM.setTextColor(ContextCompat.getColor(this, R.color.black));
            btnUntilPM.setBackgroundResource(R.drawable.toggle_unselected);
            btnUntilPM.setTextColor(ContextCompat.getColor(this, R.color.grey_300));
        } else {
            btnUntilPM.setBackgroundResource(R.drawable.toggle_selected);
            btnUntilPM.setTextColor(ContextCompat.getColor(this, R.color.black));
            btnUntilAM.setBackgroundResource(R.drawable.toggle_unselected);
            btnUntilAM.setTextColor(ContextCompat.getColor(this, R.color.grey_300));
        }
    }

    private void showFromTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    fromHour = hourOfDay;
                    fromMinute = minute;

                    // Convertir a formato 12 horas
                    if (hourOfDay == 0) {
                        fromHour = 12;
                        isFromAM = true;
                    } else if (hourOfDay > 12) {
                        fromHour = hourOfDay - 12;
                        isFromAM = false;
                    } else if (hourOfDay == 12) {
                        fromHour = 12;
                        isFromAM = false;
                    } else {
                        fromHour = hourOfDay;
                        isFromAM = true;
                    }

                    updateFromTime();
                    updateFromAMPMButtons();
                },
                fromHour, fromMinute, false
        );
        timePickerDialog.show();
    }

    private void showUntilTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    untilHour = hourOfDay;
                    untilMinute = minute;

                    // Convertir a formato 12 horas
                    if (hourOfDay == 0) {
                        untilHour = 12;
                        isUntilAM = true;
                    } else if (hourOfDay > 12) {
                        untilHour = hourOfDay - 12;
                        isUntilAM = false;
                    } else if (hourOfDay == 12) {
                        untilHour = 12;
                        isUntilAM = false;
                    } else {
                        untilHour = hourOfDay;
                        isUntilAM = true;
                    }

                    updateUntilTime();
                    updateUntilAMPMButtons();
                },
                untilHour, untilMinute, false
        );
        timePickerDialog.show();
    }

    private void showIntervalTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    intervalMinute = minute;
                    intervalSecond = 0; // Reset seconds
                    updateIntervalTime();
                },
                0, intervalMinute, false
        );
        timePickerDialog.setTitle("Seleccionar intervalo");
        timePickerDialog.show();
    }

    private void showMessageOptions() {
        // Array de opciones de mensajes pregrabados
        String[] messages = {
                "Mensaje de Ana",
                "Mensaje de María",
                "Mensaje de Carlos",
                "Mensaje personalizado"
        };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Seleccionar mensaje");
        builder.setItems(messages, (dialog, which) -> {
            tvMessageName.setText(messages[which]);
            dialog.dismiss();
        });
        builder.show();
    }

    private void openSettings() {
        Toast.makeText(this, "Grabar mensaje personalizado", Toast.LENGTH_SHORT).show();
    }

    private void saveAlarm() {
        String alarmName = etAlarmName.getText().toString().trim();

        if (alarmName.isEmpty()) {
            etAlarmName.setError("Ingresa un nombre para la alarma");
            etAlarmName.requestFocus();
            return;
        }

        // Validar que la hora "hasta" sea posterior a "desde"
        if (!isValidTimeRange()) {
            Toast.makeText(this, "La hora 'hasta' debe ser posterior a 'desde'", Toast.LENGTH_LONG).show();
            return;
        }

        // Mostrar loading
        btnSave.setEnabled(false);
        btnSave.setText("Guardando...");

        // Simular guardado
        new android.os.Handler().postDelayed(() -> {
            // Crear objeto de alarma con todos los datos
            AlarmData alarm = new AlarmData(
                    alarmName,
                    isAlarmOn,
                    fromHour, fromMinute, isFromAM,
                    untilHour, untilMinute, isUntilAM,
                    intervalMinute, intervalSecond,
                    tvMessageName.getText().toString()
            );



            Toast.makeText(this, "Alarma guardada exitosamente", Toast.LENGTH_SHORT).show();

            // Restaurar botón
            btnSave.setEnabled(true);
            btnSave.setText("Guardar");

            // Volver a la pantalla anterior
            Intent intent = new Intent(CrearAlarma.this, MainActivity.class);
            startActivity(intent);
        }, 1500);
    }

    private boolean isValidTimeRange() {
        // Convertir ambos tiempos a formato 24 horas para comparar
        int fromHour24 = convertTo24Hour(fromHour, isFromAM);
        int untilHour24 = convertTo24Hour(untilHour, isUntilAM);

        if (fromHour24 < untilHour24) {
            return true;
        } else if (fromHour24 == untilHour24) {
            return fromMinute < untilMinute;
        }
        return false;
    }

    private int convertTo24Hour(int hour, boolean isAM) {
        if (isAM && hour == 12) return 0;
        if (!isAM && hour != 12) return hour + 12;
        return hour;
    }

    private void updateUI() {
        updateFromTime();
        updateUntilTime();
        updateIntervalTime();
        updateToggleButtons();
        updateFromAMPMButtons();
        updateUntilAMPMButtons();
    }

    private void updateFromTime() {
        tvFromTime.setText(String.format("%02d:%02d", fromHour, fromMinute));
    }

    private void updateUntilTime() {
        tvUntilTime.setText(String.format("%02d:%02d", untilHour, untilMinute));
    }

    private void updateIntervalTime() {
        tvIntervalTime.setText(String.format("%02d:%02d", intervalMinute, intervalSecond));
    }

    // Clase para almacenar datos de la alarma
    public static class AlarmData {
        public String name;
        public boolean isEnabled;
        public int fromHour, fromMinute;
        public boolean isFromAM;
        public int untilHour, untilMinute;
        public boolean isUntilAM;
        public int intervalMinute, intervalSecond;
        public String messageName;

        public AlarmData(String name, boolean isEnabled,
                         int fromHour, int fromMinute, boolean isFromAM,
                         int untilHour, int untilMinute, boolean isUntilAM,
                         int intervalMinute, int intervalSecond, String messageName) {
            this.name = name;
            this.isEnabled = isEnabled;
            this.fromHour = fromHour;
            this.fromMinute = fromMinute;
            this.isFromAM = isFromAM;
            this.untilHour = untilHour;
            this.untilMinute = untilMinute;
            this.isUntilAM = isUntilAM;
            this.intervalMinute = intervalMinute;
            this.intervalSecond = intervalSecond;
            this.messageName = messageName;
        }
    }
}