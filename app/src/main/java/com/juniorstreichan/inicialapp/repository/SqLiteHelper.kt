package com.juniorstreichan.inicialapp.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqLiteHelper(
        val DATABASE_NAME: String = "appdata.db",
        private  val DATABASE_VERSION: Int = 1,
        val TABLE_NAME: String = "usuario",
        val COLUMN_ID: String = "idUsuario",
        val COLUMN_NOME: String = "nome",
        val COLUMN_EMAIL: String = "email",
        val COLUMN_SENHA: String = "senha",
        val COLUMN_FONE: String = "fone",
        private val CREATE_SCRIPT: String = """ CREATE  TABLE IF NOT EXISTS $TABLE_NAME(
         $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
         $COLUMN_NOME TEXT ,
         $COLUMN_EMAIL TEXT ,
         $COLUMN_SENHA TEXT ,
         $COLUMN_FONE TEXT
         )
    """,
        context: Context
) : SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
) {




    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        sqLiteDatabase?.execSQL(CREATE_SCRIPT)
        Log.i("DB","START DATABASE")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, p1: Int, p2: Int) {
//        sqLiteDatabase?.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME ;")
//        onCreate(sqLiteDatabase)
    }


}