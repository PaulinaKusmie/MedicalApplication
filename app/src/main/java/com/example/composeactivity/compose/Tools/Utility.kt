package com.example.composeactivity.compose.Tools
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDateTime

sealed class EntryMode {
    data class AddSpecjalizationVisit(val specjalizationId: Int, val name : String) : EntryMode()
    data class AddExaminationVisit(val examinationId: Int, val name : String) : EntryMode()
}

enum class DateType {
    DONE,
    PREDICTED,
    APPOITMENT
}

enum class VisitType {
    SPECIALIZATION,
    EXAMINATION
}

enum class TimeType {
    HOUR,
    DAY,
    WEEK,
    MONTH
}



