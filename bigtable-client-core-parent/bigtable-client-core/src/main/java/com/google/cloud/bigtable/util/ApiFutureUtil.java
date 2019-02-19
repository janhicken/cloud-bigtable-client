/*
 * Copyright 2019 Google LLC. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.bigtable.util;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureToListenableFuture;
import com.google.api.core.ListenableFutureToApiFuture;
import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

public final class ApiFutureUtil {

  public static <T> ListenableFuture<T> adapt(final ApiFuture<T> apiFuture) {
    return new ApiFutureToListenableFuture(apiFuture);
  }

  public static <T> ApiFuture<T> adapt(final ListenableFuture<T> listenableFuture) {
    return new ListenableFutureToApiFuture(listenableFuture);
  }

  public static final <R1, R2> ApiFuture<R2> transform(ListenableFuture<R1> response,
      Function<R1, R2> function) {
    return ApiFutureUtil
        .adapt(Futures.transform(response, function, MoreExecutors.directExecutor()));
  }
}
