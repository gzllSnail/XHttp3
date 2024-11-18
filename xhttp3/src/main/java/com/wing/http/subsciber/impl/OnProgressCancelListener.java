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

package com.wing.http.subsciber.impl;

/**
 * 进度框取消监听
 *
 * @author xuexiang
 * @since 2024/11/20 上午9:19
 */
public interface OnProgressCancelListener {
    /**
     * 进度框被取消
     */
    void onCancelProgress();
}
