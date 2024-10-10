package com.noxis.customtabsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.noxis.customtabsapp.ui.theme.CustomTabsAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val customIntent = CustomTabsIntent.Builder()
        // on below line we are setting show title
        // to true to display the title for
        // our chrome tabs.
        customIntent.setShowTitle(true)
        // on below line we are enabling instant
        // app to open if it is available.
        customIntent.setInstantAppsEnabled(true)
        setContent {
            CustomTabsAppTheme {
                val context = LocalContext.current
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding).fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = {
                            openCustomTab(context, customIntent.build(), Uri.parse(URL))
                        }) {
                            Text(text = "Opening custom tabs")
                        }
                    }
                }
            }
        }
    }

    private fun openCustomTab(activity: Context, customIntent: CustomTabsIntent, uri: Uri) {
        // package name is the default package
        // for our custom chrome tab

        val packageName = "com.android.chrome"
//        val packageName: String? = null
        if (packageName != null) {
            // we are checking if the package name is not null
            // if package name is not null then we are calling
            // that custom chrome tab with intent by passing its
            // package name.
            customIntent.intent.setPackage(packageName)
            // in that custom tab intent we are passing
            // our url which we have to browse.
            customIntent.launchUrl(activity, uri)
        } else {
            // if the custom tabs fails to load then we are simply
            // redirecting our user to users device default browser.
            activity.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    companion object {
        const val URL = "https://www.geeksforgeeks.org/"
    }

}

