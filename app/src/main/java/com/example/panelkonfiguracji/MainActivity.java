package com.example.panelkonfiguracji;



import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity; // lub android.app.Activity dla starszych projektów
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays; // Potrzebne, jeśli inicjujemy ArrayList z tablicy

public class MainActivity extends AppCompatActivity {

    // Deklaracja zmiennych dla ListView i danych
    ListView mySimpleListView;
    ArrayList<String> planetsList; // Nasze źródło danych

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ładujemy layout z ListView

        // Łączymy zmienną z elementem ListView z XML
        mySimpleListView = findViewById(R.id.lista);

        // Tworzymy i wypełniamy nasze źródło danych
        planetsList = new ArrayList<>();
        planetsList.add("Jasność ekranu");
        planetsList.add("Spaghetti Bolognese");
        planetsList.add("Krupnik");
        planetsList.add("Pomidorówka");
        planetsList.add("Schabowy");



        // Nowy sposób tworzenia ArrayAdapter z własnym layoutem
        ArrayAdapter<String> planetAdapter = new ArrayAdapter<>(
                this,                           // Kontekst
                R.layout.list_item_setting,   // Nasz własny layout dla elementu
                R.id.listaView,            // ID TextView wewnątrz naszego layoutu, gdzie ma być wstawiony tekst
                planetsList                     // Nasze dane
        );

        mySimpleListView.setAdapter(planetAdapter);

        // Ustawiamy adapter dla naszego ListView
        mySimpleListView.setAdapter(planetAdapter);
        mySimpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Metoda wywoływana po kliknięciu na element listy

                // parent: Referencja do adaptera, który dostarczył widok (tutaj planetAdapter)
                // view: Widok klikniętego elementu (tutaj TextView z simple_list_item_1)
                // position: Pozycja klikniętego elementu w liście (indeks od 0)
                // id: Identyfikator wiersza (dla ArrayAdapter często jest taki sam jak position)

                // Pobieramy kliknięty element z naszej listy danych
                String clickedPlanet = planetsList.get(position);
                // Alternatywnie: String clickedPlanet = (String) parent.getItemAtPosition(position);

                // Wyświetlamy Toast z nazwą klikniętej planety
                Toast.makeText(MainActivity.this, "Wybrano: " + clickedPlanet + " , idealny wybór!", Toast.LENGTH_SHORT).show();

                // Tutaj mogłaby być logika otwierania nowego ekranu, wyświetlania szczegółów itp.
            }
        });
    }
}