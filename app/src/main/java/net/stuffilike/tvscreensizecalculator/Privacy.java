package net.stuffilike.tvscreensizecalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Privacy extends Activity{

    Boolean accepted;
    Boolean checked;
    Boolean returned;
    Boolean tempCheck;
    CheckBox checkBox2;
    Button closeButton;
    Intent i;
    WebView wv;
    SharedPreferences preferences;
    Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.privacy);
        setTitle(R.string.privacy);
        closeButton = (Button) findViewById(R.id.closeButton);
        checkBox2 =(CheckBox) findViewById(R.id.checkBox2);
        addListenerOnButton();
        addListenerOnCheckBox1();
        wv = (WebView) findViewById(R.id.webview1);
        wv.loadUrl("file:///android_asset/privacy.html");

        checked = preferences.getBoolean("checked", false);
        accepted = preferences.getBoolean("accepted", true);


    }//ends onCreate


    private void addListenerOnCheckBox1() {
        checkBox2 =(CheckBox) findViewById(R.id.checkBox2);
        checkBox2.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(checkBox2.isChecked()){
                    checked = true;
                    Log.d("ATTN: ", "checked equals true");
                    editor = preferences.edit();
                    editor.putBoolean("accepted", accepted);
                    editor.putBoolean("checked", checked);
                    editor.commit ();
                }
                else{
                    checked=false;
                    Log.d("ATTN: ", "checked equals false");
                    editor = preferences.edit();
                    editor.putBoolean("accepted", accepted);
                    editor.putBoolean("checked", checked);
                    editor.commit ();
                }
                return;

            }
        });
    }

    public void addListenerOnButton() {
        closeButton = (Button) findViewById(R.id.closeButton);

        closeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                if(checked==true){
                    editor = preferences.edit();
                    editor.putBoolean("checked", checked);
                    editor.commit ();
                    Log.d("ATTN: ", "tried to go to MainActivity from HowToUse (checked)");
                    Intent mainIntent = new Intent(Privacy.this,ScreenSize.class);
                    Privacy.this.startActivity(mainIntent);
                    finish();

                }

                if(checked==false){
                    tempCheck=true;
                    editor = preferences.edit();
                    editor.putBoolean("tempCheck", tempCheck);
                    Log.d("ATTN: ", "tempCheck value is "+tempCheck);
                    editor.commit ();
                    Log.d("ATTN: ", "tried to go to MainActivity from HowToUse (tempChecked)");
                    Intent mainIntent = new Intent(Privacy.this,ScreenSize.class);
                    Privacy.this.startActivity(mainIntent);
                    finish();
                }

            };
        });
    }

    public void itemClicked(View v) {
        //code to check if this check box is checked!
        checkBox2 = (CheckBox)v;
        if(checkBox2.isChecked()){
            checked = true;
            Log.d("ATTN: ", "checked is true in itemclickedview");
            editor = preferences.edit();
            editor.putBoolean("accepted", accepted);
            editor.putBoolean("checked", checked);
            editor.commit ();
        }
        else{
            checked=false;
            Log.d("ATTN: ", "checked is false in itemclickedview");
            editor = preferences.edit();
            editor.putBoolean("accepted", accepted);
            editor.putBoolean("checked", checked);
            editor.commit ();
        }
        return;
    }


}
