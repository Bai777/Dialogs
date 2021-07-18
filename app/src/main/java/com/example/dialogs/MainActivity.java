package com.example.dialogs;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAlertDialog;
    TextView textViewPersonName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBtns();
    }

    private void initBtns() {
        btnAlertDialog = findViewById(R.id.btn_warning_from_delete);
        btnAlertDialog.setOnClickListener(this);
        textViewPersonName = findViewById(R.id.textViewPersonName);
        textViewPersonName.setOnClickListener(fragmentCustomAction);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_warning_from_delete:
                // Создаём билдер и передаём контекст приложения
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // В билдере указываем заголовок окна. Можно указывать как ресурс,
                // так и строку
                builder.setTitle(R.string.exclamation)
                        .setMessage(R.string.press_button)
                        .setIcon(R.mipmap.ic_launcher_round)
                        // Из этого окна нельзя выйти кнопкой Back
                        .setCancelable(false)
                        .setPositiveButton(R.string.btn_delete, new DialogInterface.OnClickListener() {
                            // Ставим слушатель, нажатие будем обрабатывать
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Файл удален!!!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(R.string.btn_cancellation, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNeutralButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                ;

                AlertDialog alert = builder.create();
                alert.show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v);
        }
    }

    private View.OnClickListener fragmentCustomAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment dialogCustom = new DialogCustomFragment();
            dialogCustom.show(getSupportFragmentManager(), "key");
        }
    };




    // Метод для общения с диалоговыми окнами
    protected void onDialogResult(String resultDialog){
//        Toast.makeText(this, "Выбрано " + resultDialog, Toast.LENGTH_SHORT).show();
        textViewPersonName.setText(resultDialog);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text", textViewPersonName.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textViewPersonName.setText(savedInstanceState.getString("text"));
    }
}