package com.example.danepracownika3;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextImie, editTextNazwisko, editTextZnaki;
    private Spinner spinnerStanowisko;
    private CheckBox checkBoxMaleWielkieLitery, checkBoxCyfry, checkBoxZnakiSpecjalne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextImie = findViewById(R.id.editTextText);
        editTextNazwisko = findViewById(R.id.editTextText2);
        spinnerStanowisko = findViewById(R.id.spinner);
        editTextZnaki = findViewById(R.id.editTextText3);
        checkBoxMaleWielkieLitery = findViewById(R.id.checkBox);
        checkBoxCyfry = findViewById(R.id.checkBox2);
        checkBoxZnakiSpecjalne = findViewById(R.id.checkBox3);

        Button generujHasloButton = findViewById(R.id.button);
        Button zatwierdzButton = findViewById(R.id.button2);

        List<String> stanowiskaList = new ArrayList<>();
        stanowiskaList.add("Kierownik");
        stanowiskaList.add("Starszy programista");
        stanowiskaList.add("Młodszy programista");
        stanowiskaList.add("Tester");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stanowiskaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStanowisko.setAdapter(adapter);

        generujHasloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generujHaslo();
            }
        });

        zatwierdzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zatwierdzDane();
            }
        });
    }

    private void generujHaslo() {
        int dlugoscHasla = Integer.parseInt(editTextZnaki.getText().toString());
        boolean maleWielkieLitery = checkBoxMaleWielkieLitery.isChecked();
        boolean cyfry = checkBoxCyfry.isChecked();
        boolean znakiSpecjalne = checkBoxZnakiSpecjalne.isChecked();

        String zestawLiter = "abcdefghijklmnopqrstuvwxyz";
        String zestawCyfr = "0123456789";
        String zestawZnakowSpecjalnych = "!@#$%^&*()_+-=";

        StringBuilder haslo = new StringBuilder();

        String aktualnyZestaw = zestawLiter;

        if (maleWielkieLitery) {
            aktualnyZestaw += zestawLiter.toUpperCase();
        }

        if (cyfry) {
            aktualnyZestaw += zestawCyfr;
        }

        if (znakiSpecjalne) {
            aktualnyZestaw += zestawZnakowSpecjalnych;
        }

        Random random = new Random();

        for (int i = 0; i < dlugoscHasla; i++) {
            int indeks = random.nextInt(aktualnyZestaw.length());
            haslo.append(aktualnyZestaw.charAt(indeks));
        }

        Toast.makeText(this, "Generowane hasło: " + haslo.toString(), Toast.LENGTH_SHORT).show();
    }

    private void zatwierdzDane() {
        String imie = editTextImie.getText().toString();
        String nazwisko = editTextNazwisko.getText().toString();
        String stanowisko = spinnerStanowisko.getSelectedItem().toString();
        String haslo = ""; // Tutaj uzyskaj wygenerowane hasło (możesz przechowywać je jako pole klasy)

        // Resetowanie pól edycyjnych
        editTextImie.setText("");
        editTextNazwisko.setText("");
        spinnerStanowisko.setSelection(0);
        editTextZnaki.setText("");
        checkBoxMaleWielkieLitery.setChecked(true);
        checkBoxCyfry.setChecked(false);
        checkBoxZnakiSpecjalne.setChecked(false);

        Toast.makeText(this, "Imię: " + imie + "\nNazwisko: " + nazwisko + "\nStanowisko: " + stanowisko + "\nHasło: " + haslo, Toast.LENGTH_SHORT).show();
    }
}