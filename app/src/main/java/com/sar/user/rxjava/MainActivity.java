package com.sar.user.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    io.reactivex.Observable<String> observable;
    io.reactivex.Observer<String> observer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText editText=findViewById(R.id.editText);
        final TextView Text=findViewById(R.id.text);
        Button Button=findViewById(R.id.button);
        observable= io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(editText.getText().toString());
            }
        });
        observer=new io.reactivex.Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
               Text.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               observable.subscribe(observer);
            }
        });

    }
}
