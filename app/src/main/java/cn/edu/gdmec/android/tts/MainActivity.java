package cn.edu.gdmec.android.tts;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

   private TextToSpeech tts = null;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //初始化成功的话，设置语音，这里将它设置为中文
                if (status == TextToSpeech.SUCCESS){
                   int supported = tts.setLanguage(Locale.US);
                    if ((supported != TextToSpeech.LANG_AVAILABLE)&&(supported != TextToSpeech.LANG_COUNTRY_AVAILABLE)){
                        Toast.makeText(MainActivity.this,"不支持当前语言",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        button = (Button)findViewById(R.id.search_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcast();
            }
        });
    }
    /**
     * 播报语音
     */
    public void broadcast(){
        tts.setLanguage(Locale.CHINA);
        tts.speak("请好好开车，勿当老司机",TextToSpeech.QUEUE_FLUSH,null);
    }

    /**
     * 初始化成功的时候，将语言设置为中文，这里是多余的
     * Called to signal the completion of the TextToSpeech engine initialization.
     *
     * @param status {@link TextToSpeech#SUCCESS} or {@link TextToSpeech#ERROR}.
     */
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            tts.setLanguage(Locale.CHINA);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (tts != null){
            tts.shutdown();
        }
    }
}
