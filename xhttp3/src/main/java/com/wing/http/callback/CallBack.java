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

package com.wing.http.callback;

import com.wing.http.callback.impl.IType;
import com.wing.http.exception.ApiException;
import com.wing.http.utils.TypeUtils;

import java.lang.reflect.Type;

/**
 * 网络请求回调
 *
 * @author xuexiang
 * @since 2024/11/20 下午4:39
 */
public abstract class CallBack<T> implements IType<T> {

    public abstract void onStart();

    public abstract void onSuccess(T response) throws Throwable;

    public abstract void onError(ApiException e);

    public abstract void onCompleted();

    @Override
    public Type getType() {
        return TypeUtils.findNeedClass(getClass());
    }

    @Override
    public Type getRawType() {
        return TypeUtils.findRawType(getClass());
    }
}
