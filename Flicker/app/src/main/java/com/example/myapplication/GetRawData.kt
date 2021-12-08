package com.example.myapplication

import android.os.AsyncTask
import android.telephony.mbms.DownloadStatusListener
import android.telephony.mbms.GroupCallCallback
import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus{
    YET_TO_BEGIN,FAILED_URL,FAILED_IO,FAILED_SECURITY,FAILED,COMPLETE
}
class GetRawData(val listener:OnDataDownload):AsyncTask<String,Void,String>() {
    private var TAG:String="GetRawData"
    var downloadStatus:DownloadStatus = DownloadStatus.YET_TO_BEGIN
     override fun doInBackground(vararg p0: String?): String {
         //3 kind of exception
         //MalformedURLException ,2. IOException, 3.SecurityException, set status variable called downloadStatus
         //downloadStatus{COMPLETE,ONHOLD,YET_TO_BEGIN,FAILED}
         var errorMessage:String=""
         try{
         var jsonResultString:String = URL(p0[0]).readText()
             downloadStatus=DownloadStatus.COMPLETE
         return jsonResultString
     }
         catch (e:MalformedURLException){
             Log.d(TAG,e.message.toString())
             downloadStatus=DownloadStatus.FAILED_URL
             errorMessage=e.message.toString()
         }catch (e:IOException){
             Log.d(TAG,e.message.toString())
             downloadStatus=DownloadStatus.FAILED_IO
             errorMessage=e.message.toString()

         }catch (e:SecurityException){
             Log.d(TAG,e.message.toString())
             downloadStatus=DownloadStatus.FAILED_SECURITY
             errorMessage=e.message.toString()

         }
         catch (e:Exception){
         Log.d(TAG,e.message.toString())
             errorMessage = e.message.toString()
             downloadStatus=DownloadStatus.FAILED
         }
         return errorMessage
     }

     override fun onPostExecute(result: String?) {
        Log.d(TAG,"Receive result"+result!!)
       listener.onDataDownloaded(result,downloadStatus)
     }
 }