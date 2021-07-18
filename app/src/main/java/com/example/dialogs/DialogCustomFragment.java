package com.example.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class DialogCustomFragment extends DialogFragment {

    private EditText editTextName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Подключаем макет
        View view = inflater.inflate(R.layout.fragment_dialog_custom, null);

        // Устанавливаем слушателя
        view.findViewById(R.id.btn_custom).setOnClickListener(listener);
        editTextName = view.findViewById(R.id.editTextName);

        // Запретить выход из диалога, ничего не выбрав
        setCancelable(false);
        return view;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Закрываем диалог
            dismiss();
            // Передаём в activity информацию о нажатой кнопке
            String answer = editTextName.getText().toString();
            ((MainActivity)requireActivity()).onDialogResult(answer);
        }
    };
}