package com.example.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.service.constants.DataBaseConstants
import com.example.convidados.service.model.GuestModel

/* Singleton gera somente uma instância da sua classe, nesse caso repositório. Com uma instância somente,
*  você consegue ter uma conexão ou um banco de dados ao mesmo tempo, isso evita concorrência de conexão
*  com o banco e escrever e ler informação desatualizada. */

class GuestRepository private constructor(context: Context) { //Com private constructor, ninguém consegue instanciar essa classe em outro lugar

    private val mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context = context)

    //Criar o método estático responsável por retornar a instância dessa classe
    companion object{
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context):GuestRepository {
            if (!::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository

            /*if (repository != null){
                return repository
            }else{
                return GuestRepository(context)
            }*/
        }
    }

    fun get(id: Int): GuestModel? { //Carregar os dados de um convidado

        var guest: GuestModel? = null

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(DataBaseConstants.GUEST.COLUMNS.NAME, DataBaseConstants.GUEST.COLUMNS.PRESENCE)
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString()) //Vai pesquisar a tabela toda de ID, o registro que tiver o ID que coincida com o ID do args, será atualizado. O ponto de interrogação é esse argumento

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null, null, null )

            if (cursor != null && cursor.count > 0){
                cursor.moveToFirst()

                val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) ==1)

                guest = GuestModel(id, name, presence)

            }
            cursor?.close()
            guest
        }catch (e:Exception){
            guest
        }

    }


    fun save(guests: GuestModel): Boolean { //Criar dados
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guests.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guests.presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues )
            true
        }catch (e:Exception){
            false
        }

    }


    fun getAll(): List<GuestModel> {

        val list: MutableList<GuestModel> = ArrayList()


        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(DataBaseConstants.GUEST.COLUMNS.ID,
                                    DataBaseConstants.GUEST.COLUMNS.NAME,
                                    DataBaseConstants.GUEST.COLUMNS.PRESENCE) //Colunas que nós queremos, porém aqui precisamos do ID

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME, //Só tabela e projeção
                projection,
                null, //vamos trazer todos os convidados!
                null,
                null, null, null )

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) ==1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        }catch (e:Exception){
            list
        }

        list
    }

    fun getPresent(): List<GuestModel> {

        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val cursor = db.rawQuery( "SELECT id, name, presence FROM Guest" +
                    " WHERE presence = 1 ", null)


            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) ==1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        }catch (e:Exception){
            list
        }

        list
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val cursor = db.rawQuery( "SELECT id, name, presence FROM Guest" +
                    " WHERE presence = 0 ", null)


            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) ==1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        }catch (e:Exception){
            list
        }

        list
    }


    fun update(guest: GuestModel):Boolean {

        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString()) //Vai pesquisar a tabela toda de ID, o registro que tiver o ID que coincida com o ID do args, será atualizado. O ponto de interrogação é esse argumento

            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
            true
        }catch (e:Exception){
            false
        }
    }

    fun delete(id: Int):Boolean {

        return try {

            val db = mGuestDataBaseHelper.writableDatabase
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        }catch (e:Exception){
            false
        }
    }
}