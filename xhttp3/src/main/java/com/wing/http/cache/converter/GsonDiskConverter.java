/*
 * Copyright (C) 2024 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wing.http.cache.converter;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.wing.http.logs.HttpLog;
import com.wing.http.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;

/**
 * Gson磁盘序列化器
 *
 * @author xuexiang
 * @since 2024/11/22 下午4:23
 */
public class GsonDiskConverter implements IDiskConverter {
    private Gson mGson;

    public GsonDiskConverter() {
        mGson = new Gson();
    }

    public GsonDiskConverter(Gson gson) {
        mGson = gson;
    }

    @Override
    public <T> T load(InputStream source, Type type) {
        T value = null;
        try {
            if (mGson == null) {
                mGson = new Gson();
            }
            value = mGson.fromJson(new InputStreamReader(source), type);
        } catch (JsonIOException e) {
            HttpLog.e(e);
        } catch (JsonSyntaxException e) {
            HttpLog.e(e);
        } finally {
            Utils.closeIO(source);
        }
        return value;
    }

    @Override
    public boolean writer(OutputStream sink, Object data) {
        try {
            String json = mGson.toJson(data);
            byte[] bytes = json.getBytes();
            sink.write(bytes, 0, bytes.length);
            sink.flush();
            return true;
        } catch (JsonIOException e) {
            HttpLog.e(e);
        } catch (JsonSyntaxException e) {
            HttpLog.e(e);
        } catch (IOException e) {
            HttpLog.e(e);
        } finally {
            Utils.closeIO(sink);
        }
        return false;
    }

}

