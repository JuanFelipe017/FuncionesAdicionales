package programacion.funcionesadicionales;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    ArrayList<String> listaItems = new ArrayList<>();
    ProgressBar barradeCarga;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.listaSync);
        barradeCarga = findViewById(R.id.progressBar);
        llenarLista2();
    }

    public void llenarLista2() {
        new ClaseAsincrona().execute();
    }

    private class ClaseAsincrona extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            int tiempo = 600;
            ArrayList<String> listaDeCarga = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                listaDeCarga.add("Objeto" + i);
                publishProgress(); // Notificar el progreso
                try {
                    Thread.sleep(tiempo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return listaDeCarga;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            barradeCarga.incrementProgressBy(10);
        }
        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            listaItems = result;
            ArrayAdapter<String> adaptadorLista = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_checked,
                    listaItems);
            lista.setAdapter(adaptadorLista);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}