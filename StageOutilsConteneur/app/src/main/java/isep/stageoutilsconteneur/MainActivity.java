package isep.stageoutilsconteneur;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import io.paperdb.Paper;
import isep.stageoutilsconteneur.helper.LocaleHelper;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.text_view);
        //init paper first
        Paper.init(this);
        //default lang is fr
        String language=Paper.book().read("language");
        if(language==null){
            Paper.book().write("language","fr");
            updateView((String)Paper.book().read("language"));
        }

    }

    private void updateView(String lang) {
        Context context =LocaleHelper.setLocale(this,lang);
        Resources res = context.getResources();
        textView.setText(res.getString(R.string.welcome_message));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.language_fr){
            Paper.book().write("language","fr");
            updateView((String)Paper.book().read("language"));
        }
        else if(item.getItemId()==R.id.language_en){
            Paper.book().write("language","en");
            updateView((String)Paper.book().read("language"));

        }
        return true;
    }
}