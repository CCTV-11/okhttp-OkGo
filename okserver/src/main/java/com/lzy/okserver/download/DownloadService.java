/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lzy.okserver.download;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.lzy.okgo.OkGo;

import java.util.List;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：2016/1/25
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class DownloadService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static DownloadManager DOWNLOAD_MANAGER;

    /** start 方式开启服务，保存全局的下载管理对象 */
    public static DownloadManager getDownloadManager() {
        Context context = OkGo.getInstance().getContext();
        if (!DownloadService.isServiceRunning(context)) context.startService(new Intent(context, DownloadService.class));
        if (DownloadService.DOWNLOAD_MANAGER == null) DownloadService.DOWNLOAD_MANAGER = DownloadManager.getInstance();
        return DOWNLOAD_MANAGER;
    }

    public static boolean isServiceRunning(Context context) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (serviceList == null || serviceList.size() == 0) return false;
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(DownloadService.class.getName())) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
