package com.example.darkmode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.darkmode.components.BotonIcono
import com.example.darkmode.components.BotonNormal
import com.example.darkmode.components.BotonNormal2
import com.example.darkmode.components.BotonOutline
import com.example.darkmode.components.BotonSwitch
import com.example.darkmode.components.BotonTexto
import com.example.darkmode.components.DarkMode
import com.example.darkmode.components.FloatingAction
import com.example.darkmode.components.Space
import com.example.darkmode.ui.theme.DarkModeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val dataStore = StoreMode(context)
            val darkMode = rememberSaveable { mutableStateOf(false) }

            LaunchedEffect ( Unit ) {
                dataStore.getMode.collect { mode -> darkMode.value = mode }
            }

            DarkModeTheme(darkTheme = darkMode.value) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content ( darkMode = darkMode, dataStore = dataStore )
                }
            }
        }
    }
}

@Composable
fun Content ( darkMode: MutableState<Boolean>, dataStore: StoreMode ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BotonNormal()
        Space()
        BotonNormal2()
        Space()
        BotonTexto()
        Space()
        BotonOutline()
        Space()
        BotonIcono()
        Space()
        BotonSwitch()
        Space()
        DarkMode ( darkMode = darkMode, dataStore = dataStore )
        Space()
        FloatingAction()
    }
}