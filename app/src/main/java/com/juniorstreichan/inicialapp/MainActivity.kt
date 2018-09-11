package com.juniorstreichan.inicialapp

import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.juniorstreichan.inicialapp.repository.SqLiteHelper


class MainActivity : AppCompatActivity() {
    private lateinit var txEmail: EditText
    private lateinit var txSenha: EditText
    private lateinit var sqLite: SqLiteHelper
    private lateinit var db: SQLiteDatabase
    private lateinit var btnLogin: Button
    private var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        txEmail = findViewById(R.id.tx_login)
        txSenha = findViewById(R.id.tx_senha)
        btnLogin = findViewById(R.id.btn_login)

        val clickLogin = View.OnClickListener {
            dologin()
        }

        btnLogin.setOnClickListener(clickLogin)

        sqLite = SqLiteHelper(context = this)

        db = sqLite.readableDatabase
        sqLite.onCreate(db)

//        val values = ContentValues()
    }

    fun dologin() {

        val login = txEmail.text.toString()
        val senha = txSenha.text.toString()
        cursor = db.rawQuery(
                """SELECT * FROM ${sqLite.TABLE_NAME}
                     WHERE ${sqLite.COLUMN_EMAIL} = '$login'
                     and ${sqLite.COLUMN_SENHA} = '$senha' """.trimMargin()
                , null)

        if (cursor != null) {
            if (!cursor?.count!!.equals(0)) {

                cursor?.moveToFirst();

                val _name = cursor?.getString(cursor!!.getColumnIndex(sqLite.COLUMN_NOME))
                val _email = cursor?.getString(cursor!!.getColumnIndex(sqLite.COLUMN_EMAIL))

                Toast.makeText(this, " $_name logado com sucesso !!", Toast.LENGTH_LONG).show()

            } else {

                val alertBuilder = AlertDialog.Builder(this)
                alertBuilder.setTitle("Alerta")
                alertBuilder.setMessage("Usuário ou senha inválido")
                alertBuilder.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
                val dialog = alertBuilder.create()
                dialog.show()

            }
        }
    }

}
