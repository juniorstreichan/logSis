package com.juniorstreichan.inicialapp.view

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
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
        db = sqLite.writableDatabase

        val txNome = findViewById<EditText>(R.id.et_nome)
        val txEmail = findViewById<EditText>(R.id.et_email)
        val txSenha = findViewById<EditText>(R.id.et_senha)
        val txTelefone = findViewById<EditText>(R.id.et_telefone)
        val btnGravar = findViewById<Button>(R.id.btn_gravar_usuario)
        val btnCancelar = findViewById<Button>(R.id.btn_cancel)

        btnCancelar.setOnClickListener {
            toLogin()
        }


        btnGravar.setOnClickListener {
            gravarUsuario(
                    txNome.text.toString(),
                    txEmail.text.toString(),
                    txSenha.text.toString(),
                    txTelefone.text.toString()
            )
        }

    }


    private fun gravarUsuario(
            nome: String = "",
            email: String = "",
            senha: String = "",
            telefone: String = ""
    ) {
        if (nome == "" || email == "" || senha == "" || telefone == "") {
            mensagem("Alerta", "Dados inválidos")
            return
        }


        val values = ContentValues()
        values.put(sqLite.COLUMN_NOME, nome)
        values.put(sqLite.COLUMN_EMAIL, email)
        values.put(sqLite.COLUMN_SENHA, senha)
        values.put(sqLite.COLUMN_FONE, telefone)

        db.insert(sqLite.TABLE_NAME, null, values)
        mensagem(titulo = "", msg = "Dados gravados com sucesso")

    }

    private fun mensagem(titulo: String = "Alerta", msg: String = "Mensagem") {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle(titulo)
        alertBuilder.setMessage(msg)
        alertBuilder.setPositiveButton("OK") { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        val dialog = alertBuilder.create()
        dialog.show()
    }

    private fun toLogin() {
        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
