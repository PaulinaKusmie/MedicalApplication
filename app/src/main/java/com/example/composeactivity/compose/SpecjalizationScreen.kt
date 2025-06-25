package com.example.composeactivity.compose

import android.widget.AdapterView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.SpecjalizationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecjalizationScreen(  navController: NavController,viewModel : SpecjalizationViewModel = viewModel()) {
    val specjalizations by viewModel.specjalizations.observeAsState(initial = emptyList())

//    val specjalizacje = listOf(
//        Specjalization(id = 1, name = "Internista", isActive = true, sex = 0, isPay = false),
//        Specjalization(id = 2, name = "Lekarz rodzinny", isActive = true, sex = 0, isPay = false),
//        Specjalization(id = 3, name = "Kardiolog", isActive = true, sex = 0, isPay = true),
//        Specjalization(id = 4, name = "Dermatolog", isActive = true, sex = 0, isPay = true),
//        Specjalization(id = 5, name = "Psychiatra", isActive = true, sex = 0, isPay = false),
//        Specjalization(id = 6, name = "Ortopeda", isActive = true, sex = 0, isPay = true),
//        Specjalization(id = 7, name = "Endokrynolog", isActive = true, sex = 0, isPay = false),
//        Specjalization(id = 8, name = "Gastroenterolog", isActive = true, sex = 0, isPay = true),
//        Specjalization(id = 9, name = "Neurolog", isActive = true, sex = 0, isPay = false),
//        Specjalization(id = 10, name = "Reumatolog", isActive = true, sex = 0, isPay = false)
//    )
//
//
//    specjalizacje.forEach { i ->viewModel.addSpecjalization(i) }


    Scaffold(
        topBar = {
            TopAppBar( modifier = Modifier.background(MainColor),
                title = { Text("Lista wizyt") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor
                )
            )
        },
        containerColor = MainColor

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            specjalizations.forEach {
                    spec -> SpecjalizationItem(
                spec = spec,
                onActiveChange = { isActive ->
                    viewModel.updateIsActive(spec.id, isActive)
                },
                onClick = { specid ->
                            navController.navigate("dataEntryAddWizyta/$specid") },


            )

            }
        }
    }
}



    @Composable
    fun SpecjalizationItem(
        spec : Specjalization,
        onActiveChange: (Boolean) -> Unit,
        onClick: (Int) -> Unit

    ) {
        // Gradienty
        val cardGradient = Brush.linearGradient(
            colors = listOf(
                Color(0xFFF5E6C8), // Jasny beż
                Color(0xFFD2B48C)  // Klasyczny beż (tan)
            )
        )
        val switchGradient = Brush.linearGradient(
            colors = listOf(Color(0xFF6E48AA), Color(0xFF9D50BB))
        )
      // var showDialog by remember { mutableStateOf(false)}

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(cardGradient)
                .clickable() { onClick(spec.id)},
            color = Color.Transparent,
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 18.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = spec.name,
                    modifier = Modifier.weight(1f)
                        .background(Color.Transparent),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    letterSpacing = 0.2.sp,

                    )
                GradientSwitch(
                    checked = spec.isActive,
                    onCheckedChange = onActiveChange,
                    gradient = switchGradient
                )
            }
        }

        //if(showDialog){
            //MyPopupDialog(spec.name)
       // }
    }



