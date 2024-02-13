package com.example.weatherforecastapp.widgets

import android.content.res.Resources.Theme
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.weatherforecastapp.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String? = null,
    icon: ImageVector? = null,
    isHomeScreen: Boolean = true,
    onIconClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { title?.let { Text(text = title, style = AppTheme.typography.bodyMedium) } },
        actions = {
            if (isHomeScreen) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon" )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More Icon" )
                }
            }
        },
        navigationIcon = {
            icon?.let {
                IconButton(onClick = onIconClick) {
                    Icon(imageVector = icon, contentDescription = null )
                }
            }
        }
    )
}