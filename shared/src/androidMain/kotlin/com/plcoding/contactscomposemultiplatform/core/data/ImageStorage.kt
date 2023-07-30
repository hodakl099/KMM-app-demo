package com.plcoding.contactscomposemultiplatform.core.data


import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

actual class ImageStorage(
   private val context : Context
) {


   actual suspend fun saveImage(bytes: ByteArray): String {
      return withContext(Dispatchers.IO) {
         val fileName = UUID.randomUUID().toString() + ".jpg"
         context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outPutSteam ->
            outPutSteam.write(bytes)
         }
         fileName
      }
   }

   actual suspend fun getImage(fileName: String): ByteArray? {
      return withContext(Dispatchers.IO) {
         context.openFileInput(fileName).use { inputSream ->
            inputSream.readBytes()
         }
      }
   }

   actual suspend fun deleteImage(fileName: String) {
      withContext(Dispatchers.Default) {
         context.deleteFile(fileName)
      }
   }
}

