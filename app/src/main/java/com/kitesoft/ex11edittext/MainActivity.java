package com.kitesoft.ex11edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edit01, edit02, edit03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit01= findViewById(R.id.edit01);
        edit02= findViewById(R.id.edit02);
        edit03= findViewById(R.id.edit03);

        //EditText의 글씨가 변경될때 마다 반응하기 - 텍스트변경리스터 TextChangedListener로서 TextWatcher객체를 전달
        edit01.addTextChangedListener(new TextWatcher() {

            //텍스트가 변경되기 이전에 자동 실행되는 메소드 - 변경 이전의 텍스트를 얻어올 때 활용가능
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            //텍스트가 변경되었을 때 자동 실행되는 메소드 - 변경된 텍스트를 얻어올 때 활용
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                //첫번재 파라미터 charSequence : 현재 EditText에 써있는 글씨

                // 전화번호의 첫번째 자리는 010 처럼 3자리 이므로..
                //EditText에 작성된 글씨(String)의 길이(글자수)가 3가 이상인가? - 그럼 edit02객체가 포커스를 가지도록 요청
                if(charSequence.length()>=3){
                    edit02.requestFocus();//포커스 요청
                }
            }

            //텍스트변경 작업이 완료된 후 자동 실행되는 메소드
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // 위와 마찬가기로 전화번호의 2번째 자리도 4자리로 구성되어 있으므로 4글자 이상이면 마지막 edit03으로 포커스 이동시키는 코드
        edit02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>=4){ //입력된 글씨가 4글자 이상인가?
                    edit03.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }//onCreat method....



    //소프트 키패드 보이기
    public void clickBtn(View view) {

        //소프트키패드를 관리하는 InputMethodManager객체를 시스템객체(Context)로 부터 얻어오기
        // 참고 : Context - 안드로이드 운영체제의 주요기능관리객체(시스템서비스객체)들을 가진 객체[일종의 운영체체 대리인 - 운영체제의 능력을 사용하고 싶을 때 호출]
        //                - InputMethodManager가 바로 주요기능 관리객체(시스템서비스객체)임.
        //                - Activity는 Context를 상속받아 만들어진 클래스 여서 Context의 능력을 그대로 사용할 수 있음.
        InputMethodManager imm= (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        //InputMethodManager객체에게 소프트 키패드를 보이도록 요청 - 파라미터로 전달된 값은 추후 소개
        imm.showSoftInput(getCurrentFocus(), 0);
    }

    //소프트 키패드 숨기기
    public void clickBtn2(View view){

        InputMethodManager imm= (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        //InputMethodManager객체에게 소프트 키패드를 숨기도록 요청 - 파라미터로 전달된 값은 추후 소개
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    //소프트 키패드 토글
    public void clickBtn3(View view) {

        InputMethodManager imm= (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        //InputMethodManager객체에게 소프트 키패드가 보이면 숨기고 안보이면 보이도록 요청 - 파라미터로 전달된 값은 추후 소개
        imm.toggleSoftInput(0, 0);
    }

}
