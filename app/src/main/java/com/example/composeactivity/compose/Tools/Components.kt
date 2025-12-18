package com.example.composeactivity.compose.Tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import com.example.composeactivity.data.entity.Reminder


@Composable
fun GradientSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    gradient: Brush
) {
    val thumbColor = remember(checked) {
        if (checked) Color.White else Color(0xFFE0E0E0)
    }
    val track = Modifier
        .width(48.dp)
        .height(20.dp)
        .clip(RoundedCornerShape(14.dp))
        .background(brush = gradient)
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = Modifier
            .then(track)
            .height(28.dp),
        colors = SwitchDefaults.colors(
            checkedThumbColor = thumbColor,
            uncheckedThumbColor = Color(0xFFE0E0E0),
            checkedTrackColor = Color.Transparent,
            uncheckedTrackColor = Color(0xFFD1C4E9)
        )
    )
}

@Composable
fun WheelPickerDemo(OnDismissRequest : () -> Unit,
                    dateTime :  MutableState<LocalDateTime?>) {

    val currentDate = dateTime.value ?: LocalDateTime.now()

    var resultDate: LocalDateTime? = null
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = OnDismissRequest,
        text = {
            WheelDateTimePicker(
                startDateTime = LocalDateTime.of(
                    currentDate.year,
                    currentDate.month,
                    currentDate.dayOfMonth,
                    currentDate.hour,
                    currentDate.minute
                ),
                minDateTime = LocalDateTime.now(), ////tu zmiennnnnn
                maxDateTime = LocalDateTime.of(
                    currentDate.year + 50,
                    currentDate.month,
                    currentDate.dayOfMonth,
                    currentDate.hour,
                    currentDate.minute
                ),
                timeFormat = TimeFormat.HOUR_24,
                size = DpSize(250.dp, 120.dp),
                rowCount = 5,
                textStyle = MaterialTheme.typography.titleMedium,
                textColor = Color.Black,
                selectorProperties = WheelPickerDefaults.selectorProperties(
                    enabled = true,
                    color = Color.Gray.copy(alpha = 0.2f)
                )
            ) { snappedDateTime -> resultDate = snappedDateTime  }
        },

        confirmButton = {
            Button(
                onClick = {
                    dateTime.value = resultDate
                    OnDismissRequest() } )
            {
                Text("Akceptuj")

            }
        },
        dismissButton = {
            Button(onClick = OnDismissRequest)
            {
                Text("Zamknij")
                resultDate = (if (currentDate == null) null else currentDate) as LocalDateTime?
            }

        }
    )
}


