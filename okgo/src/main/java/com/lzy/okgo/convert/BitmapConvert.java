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
package com.lzy.okgo.convert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/11
 * 描    述：字符串的转换器
 * 修订历史：
 * ================================================
 */
public class BitmapConvert implements Converter<Bitmap> {

    public static BitmapConvert create() {
        return BitmapConvert.ConvertHolder.convert;
    }

    private static class ConvertHolder {
        private static BitmapConvert convert = new BitmapConvert();
    }

    @Override
    public Bitmap convertSuccess(Response value) throws Exception {
        return BitmapFactory.decodeStream(value.body().byteStream());
    }
}
