package camelcase.searchemall

import android.content.Context
import java.io.File
import java.net.URL

class FileDownloadTask(context:Context) : Thread() {

    private val mycontext = context.applicationContext

    private val TAG = "FileDownload"
    private val FILE_NAME = mycontext.getString(R.string.search_scopes_file_name)

    override fun run() {
        val cn = URL(mycontext.getString(R.string.search_scopes_url)).openConnection()
        cn.connect()
        val file = File(mycontext.externalCacheDir,FILE_NAME)

        if(!file.exists()) {
            file.writeBytes(cn.getInputStream().readBytes())
        }
    }
}