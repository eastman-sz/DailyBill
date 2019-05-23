package com.bill.update

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import com.bill.util.ILog
import com.sz.kk.daily.bill.BuildConfig
import com.utils.lib.ss.common.PkgHelper
import java.io.File

class AppInstaller {

    companion object {

        fun install(context: Context, apkFile : File?){
            if (null == apkFile) {
                return
            }
            //		int result = PackageUtils.install(context, apkFile.getAbsolutePath());
//		IwyLog.e("--------result---------:: " + result);

            val pkgName = PkgHelper.getUninstallAPKPkgName(context, apkFile.path)
            val versionName = PkgHelper.getUninstallAPKVesionName(context, apkFile.path)

            ILog.e("检查更新", "pkgName::: $pkgName    versionName:: $versionName")

            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                ILog.e("版本大于 N ，开始使用 fileProvider 进行安装")

                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                val contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", apkFile)
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
            } else {
                ILog.e("正常进行安装")

                intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
            }
            context.startActivity(intent)
        }



    }

}