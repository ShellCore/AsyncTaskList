package com.shellcore.android.asynctasklist.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.shellcore.android.asynctasklist.adapters.PokemonListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 16/06/2017.
 */

public class DownloadPokemonTask extends AsyncTask<Void, Integer, ArrayList<String>> {

    private ProgressDialog dialog;
    private PokemonListListener listener;

    public DownloadPokemonTask(Context context, PokemonListListener listListener) {
        this.dialog = new ProgressDialog(context);
        this.listener = listListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Downloading data...");
        dialog.show();
    }

    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        ArrayList<String> newPokemonList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(1000);
                newPokemonList.add(simulateCallToApi(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return newPokemonList;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
        listener.handleResult(strings);
        dialog.dismiss();
    }



    /**
     * This method simulate a call to the API
     * @param i
     * @return
     */
    private String simulateCallToApi(int i) {
        String[] pokemons = {
                "Bulbasaur",
                "Charmander",
                "Squirtle",
                "Pikachu"
        };

        return pokemons[i];
    }

    public interface PokemonListListener {
        void handleResult(ArrayList<String> strings);
    }
}
