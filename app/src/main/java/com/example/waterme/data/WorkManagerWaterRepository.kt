/*
 * Copyright (C) 2022 The Android Open Source Project
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

package com.example.waterme.data

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.waterme.model.Plant
import com.example.waterme.worker.WaterReminderWorker
import com.example.waterme.worker.WaterReminderWorker.Companion.durationKey
import com.example.waterme.worker.WaterReminderWorker.Companion.nameKey
import java.util.concurrent.TimeUnit

class WorkManagerWaterRepository(context: Context) : WaterRepository {
    private val workManager = WorkManager.getInstance(context)

    override val plants: List<Plant>
        get() = DataSource.plants

    override fun scheduleReminder(duration: Long, unit: TimeUnit, plantName: String) {
        val data = Data.Builder()
            .putString(nameKey, plantName)

        var delay = when (unit) {
            TimeUnit.DAYS -> duration * 60 * 24
            TimeUnit.MINUTES -> duration * 60
            else -> { 0 }
        }
        delay *= 1000

        data.putLong(durationKey, delay)

        val worker = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInputData(data.build())

        workManager.enqueueUniqueWork(
            plantName + duration,
            ExistingWorkPolicy.REPLACE,
            worker.build())
    }
}
