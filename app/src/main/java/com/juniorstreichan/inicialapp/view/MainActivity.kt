package com.juniorstreichan.inicialapp.view

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.juniorstreichan.inicialapp.R
import com.juniorstreichan.inicialapp.repository.SqLiteHelper


class MainActivity : AppCompatActivity() {
    private lateinit var txEmail: EditText
    private lateinit var txSenha: EditText
    private lateinit var sqLite: SqLiteHelper
    private lateinit var db: SQLiteDatabase
    private lateinit var btnLogin: Button
    private lateinit var btnReg: Button
    private var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        txEmail = findViewById(R.id.tx_login)
        txSenha = findViewById(R.id.tx_senha)
        btnLogin = findViewById(R.id.btn_login)
        btnReg = findViewById(R.id.btn_register)

        btnLogin.setOnClickListener({
            dologin()
        })

        btnReg.setOnClickListener({
            doRegister()
        })

        sqLite = SqLiteHelper(context = this)

        db = sqLite.readableDatabase
        sqLite.onCreate(db)

    }

    fun doRegister() {
        val intent = Intent(this@MainActivity, RegisterActivity::class.java);
        startActivity(intent)
    }


    fun dologin() {

        val login = txEmail.text.toString()
        val senha = txSenha.text.toString()
        cursor = db.rawQuery(
                """SELECT * FROM ${sqLite.TABLE_NAME}
                     WHERE ${sqLite.COLUMN_EMAIL} = '$login'
                     and ${sqLite.COLUMN_SENHA} = '$senha' """.trimMargin()
                , null)

        cursor?.let {
            if (!cursor?.count!!.equals(0)) {

                cursor?.moveToFirst()

                val _name = cursor?.getString(cursor!!.getColumnIndex(sqLite.COLUMN_NOME))
                val _email = cursor?.getString(cursor!!.getColumnIndex(sqLite.COLUMN_EMAIL))

                Toast.makeText(this, " $_name logado com sucesso !!", Toast.LENGTH_LONG).show()

            } else {

                val alertBuilder = AlertDialog.Builder(this)
                alertBuilder.setTitle("Alerta")
                alertBuilder.setMessage("Usuário ou senha inválidos")
                alertBuilder.setPositiveButton("OK") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                val dialog = alertBuilder.create()
                dialog.show()

            }
        }
    }

}
