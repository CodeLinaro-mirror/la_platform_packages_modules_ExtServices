/**
 * Copyright (C) 2025 The Android Open Source Project
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
package android.ext.services.smsretriever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Trace;

import androidx.annotation.RequiresApi;

import com.android.textclassifier.utils.AppHashHelper;

@RequiresApi(Build.VERSION_CODES.BAKLAVA)
public class PackageChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getData() == null) {
            return;
        }

        String action = intent.getAction();
        if (action == null) {
            return;
        }

        if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            try {
                Trace.beginSection("addAppHashOnPackageAdd");
                AppHashHelper.addAppHashOnPackageAdd(context, packageName);
            } finally {
                Trace.endSection();
            }
        }
    }
}
