package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.SearchView;

import com.example.mad_project.adapter.SearchAdapter;
import com.example.mad_project.database.Database;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchPark extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter searchAdapter;
    MaterialSearchBar materialSearchBar;
    Database database;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_park);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar=(MaterialSearchBar)findViewById(R.id.search_bar);

        database = new Database(this);


        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
       loadsuggestList();
       materialSearchBar.addTextChangeListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               List<String> suggest = new ArrayList<>();
               for(String search:suggestList)
               {
                   if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()));
                   suggest.add(search);
               }
               materialSearchBar.setLastSuggestions(suggest);

           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });
       materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
           @Override
           public void onSearchStateChanged(boolean enabled) {
               if(!enabled)
                   recyclerView.setAdapter(searchAdapter);


           }

           @Override
           public void onSearchConfirmed(CharSequence text) {
               startSearch(text.toString());

           }
           @Override
           public void onButtonClicked(int buttonCode) {

           }
       });


        searchAdapter=new SearchAdapter(this,database.getParks());
        recyclerView.setAdapter(searchAdapter);




    }

    private void startSearch(String text) {
        searchAdapter = new SearchAdapter(this,database.getParkbyTown(text));
        recyclerView.setAdapter(searchAdapter);
    }

    private void loadsuggestList() {
        suggestList = database.getTowns();
        materialSearchBar.setLastSuggestions(suggestList);
    }


}