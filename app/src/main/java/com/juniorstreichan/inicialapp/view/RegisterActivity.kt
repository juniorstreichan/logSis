package com.juniorstreichan.inicialapp.view

import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.juniorstreichan.inicialapp.R
import com.juniorstreichan.inicialapp.repository.SqLiteHelper


class RegisterActivity : AppCompatActivity() {

    private lateinit var sqLite: SqLiteHelper
    private lateinit var db: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sqLite = SqLiteHelper(context = this)

        val txNome = findViewById<EditText>(R.id.et_nome)
        val txEmail = findViewById<EditText>(R.id.et_email)
        val txSenha = findViewById<EditText>(R.id.et_senha)
        val txTelefone = findViewById<EditText>(R.id.et_telefone)
        val btnGravar = findViewById<Button>(R.id.btn_gravar_usuario)

        btnGravar.setOnClickListener({
            gravarUsuario()
        })

    }


    fun gravarUsuario(
            nome: String,
            email: String,
            senha: String,
            telefone: String
    ) {


    }

}
