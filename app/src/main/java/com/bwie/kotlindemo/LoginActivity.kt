package com.bwie.kotlindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bwie.kotlindemo.api.Api
import com.bwie.kotlindemo.api.UserApiservice
import com.bwie.kotlindemo.entity.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

//    var loinBtn: Button? = null
//    lateinit var phoneEt: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    /**
     * 初始化view
     */
    private fun initView() {

//        loinBtn = findViewById(R.id.login)
//        loinBtn?.setOnClickListener {
//
//
//        }

    }

    /**
     * 登录接口
     */
    private fun requestLogin() {

        var params:HashMap<String,String> = HashMap()

        params.put("phone",phone.text.toString())
        params["pwd"] = "111111"



        var okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        var retrofit:Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(Api.BASE_URL)
            .build()


        //创建apiservice，.class ::class.java
        var userApiservice:UserApiservice = retrofit.create(UserApiservice::class.java)

        userApiservice.login(params).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                Toast.makeText(LoginActivity@this,it.message,Toast.LENGTH_SHORT).show()
                var intent:Intent = Intent(LoginActivity@this,MainActivity::class.java)
                startActivity(intent)
            }, Consumer {

            })




    }

    /**
     * 登录
     */
    fun login(view: View) {
        Toast.makeText(this, "dianji", Toast.LENGTH_SHORT).show()
        requestLogin()
    }
}
