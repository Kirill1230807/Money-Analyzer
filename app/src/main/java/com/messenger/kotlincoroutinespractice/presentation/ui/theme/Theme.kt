package com.messenger.kotlincoroutinespractice.presentation.ui.theme
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.compose.backgroundDarkHighContrast
import com.example.compose.backgroundDarkMediumContrast
import com.example.compose.errorContainerDarkHighContrast
import com.example.compose.errorDarkHighContrast
import com.example.compose.inverseOnSurfaceDarkHighContrast
import com.example.compose.inverseOnSurfaceDarkMediumContrast
import com.example.compose.inversePrimaryDarkHighContrast
import com.example.compose.inversePrimaryDarkMediumContrast
import com.example.compose.inverseSurfaceDarkHighContrast
import com.example.compose.inverseSurfaceDarkMediumContrast
import com.example.compose.onBackgroundDarkHighContrast
import com.example.compose.onBackgroundDarkMediumContrast
import com.example.compose.onErrorContainerDarkHighContrast
import com.example.compose.onErrorDarkHighContrast
import com.example.compose.onPrimaryContainerDarkHighContrast
import com.example.compose.onPrimaryDarkHighContrast
import com.example.compose.onSecondaryContainerDarkHighContrast
import com.example.compose.onSecondaryDarkHighContrast
import com.example.compose.onSurfaceDarkHighContrast
import com.example.compose.onSurfaceDarkMediumContrast
import com.example.compose.onSurfaceVariantDarkHighContrast
import com.example.compose.onSurfaceVariantDarkMediumContrast
import com.example.compose.onTertiaryContainerDarkHighContrast
import com.example.compose.onTertiaryDarkHighContrast
import com.example.compose.outlineDarkHighContrast
import com.example.compose.outlineDarkMediumContrast
import com.example.compose.outlineVariantDarkHighContrast
import com.example.compose.outlineVariantDarkMediumContrast
import com.example.compose.primaryContainerDarkHighContrast
import com.example.compose.primaryDarkHighContrast
import com.example.compose.scrimDarkHighContrast
import com.example.compose.scrimDarkMediumContrast
import com.example.compose.secondaryContainerDarkHighContrast
import com.example.compose.secondaryDarkHighContrast
import com.example.compose.surfaceBrightDarkHighContrast
import com.example.compose.surfaceBrightDarkMediumContrast
import com.example.compose.surfaceContainerDarkHighContrast
import com.example.compose.surfaceContainerDarkMediumContrast
import com.example.compose.surfaceContainerHighDarkHighContrast
import com.example.compose.surfaceContainerHighDarkMediumContrast
import com.example.compose.surfaceContainerHighestDarkHighContrast
import com.example.compose.surfaceContainerHighestDarkMediumContrast
import com.example.compose.surfaceContainerLowDarkHighContrast
import com.example.compose.surfaceContainerLowDarkMediumContrast
import com.example.compose.surfaceContainerLowestDarkHighContrast
import com.example.compose.surfaceContainerLowestDarkMediumContrast
import com.example.compose.surfaceDarkHighContrast
import com.example.compose.surfaceDarkMediumContrast
import com.example.compose.surfaceDimDarkHighContrast
import com.example.compose.surfaceDimDarkMediumContrast
import com.example.compose.surfaceVariantDarkHighContrast
import com.example.compose.surfaceVariantDarkMediumContrast
import com.example.compose.tertiaryContainerDarkHighContrast
import com.example.compose.tertiaryDarkHighContrast
import com.messenger.kotlincoroutinespractice.presentation.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = _root_ide_package_.com.example.compose.primaryLight,
    onPrimary = _root_ide_package_.com.example.compose.onPrimaryLight,
    primaryContainer = _root_ide_package_.com.example.compose.primaryContainerLight,
    onPrimaryContainer = _root_ide_package_.com.example.compose.onPrimaryContainerLight,
    secondary = _root_ide_package_.com.example.compose.secondaryLight,
    onSecondary = _root_ide_package_.com.example.compose.onSecondaryLight,
    secondaryContainer = _root_ide_package_.com.example.compose.secondaryContainerLight,
    onSecondaryContainer = _root_ide_package_.com.example.compose.onSecondaryContainerLight,
    tertiary = _root_ide_package_.com.example.compose.tertiaryLight,
    onTertiary = _root_ide_package_.com.example.compose.onTertiaryLight,
    tertiaryContainer = _root_ide_package_.com.example.compose.tertiaryContainerLight,
    onTertiaryContainer = _root_ide_package_.com.example.compose.onTertiaryContainerLight,
    error = _root_ide_package_.com.example.compose.errorLight,
    onError = _root_ide_package_.com.example.compose.onErrorLight,
    errorContainer = _root_ide_package_.com.example.compose.errorContainerLight,
    onErrorContainer = _root_ide_package_.com.example.compose.onErrorContainerLight,
    background = _root_ide_package_.com.example.compose.backgroundLight,
    onBackground = _root_ide_package_.com.example.compose.onBackgroundLight,
    surface = _root_ide_package_.com.example.compose.surfaceLight,
    onSurface = _root_ide_package_.com.example.compose.onSurfaceLight,
    surfaceVariant = _root_ide_package_.com.example.compose.surfaceVariantLight,
    onSurfaceVariant = _root_ide_package_.com.example.compose.onSurfaceVariantLight,
    outline = _root_ide_package_.com.example.compose.outlineLight,
    outlineVariant = _root_ide_package_.com.example.compose.outlineVariantLight,
    scrim = _root_ide_package_.com.example.compose.scrimLight,
    inverseSurface = _root_ide_package_.com.example.compose.inverseSurfaceLight,
    inverseOnSurface = _root_ide_package_.com.example.compose.inverseOnSurfaceLight,
    inversePrimary = _root_ide_package_.com.example.compose.inversePrimaryLight,
    surfaceDim = _root_ide_package_.com.example.compose.surfaceDimLight,
    surfaceBright = _root_ide_package_.com.example.compose.surfaceBrightLight,
    surfaceContainerLowest = _root_ide_package_.com.example.compose.surfaceContainerLowestLight,
    surfaceContainerLow = _root_ide_package_.com.example.compose.surfaceContainerLowLight,
    surfaceContainer = _root_ide_package_.com.example.compose.surfaceContainerLight,
    surfaceContainerHigh = _root_ide_package_.com.example.compose.surfaceContainerHighLight,
    surfaceContainerHighest = _root_ide_package_.com.example.compose.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = _root_ide_package_.com.example.compose.primaryDark,
    onPrimary = _root_ide_package_.com.example.compose.onPrimaryDark,
    primaryContainer = _root_ide_package_.com.example.compose.primaryContainerDark,
    onPrimaryContainer = _root_ide_package_.com.example.compose.onPrimaryContainerDark,
    secondary = _root_ide_package_.com.example.compose.secondaryDark,
    onSecondary = _root_ide_package_.com.example.compose.onSecondaryDark,
    secondaryContainer = _root_ide_package_.com.example.compose.secondaryContainerDark,
    onSecondaryContainer = _root_ide_package_.com.example.compose.onSecondaryContainerDark,
    tertiary = _root_ide_package_.com.example.compose.tertiaryDark,
    onTertiary = _root_ide_package_.com.example.compose.onTertiaryDark,
    tertiaryContainer = _root_ide_package_.com.example.compose.tertiaryContainerDark,
    onTertiaryContainer = _root_ide_package_.com.example.compose.onTertiaryContainerDark,
    error = _root_ide_package_.com.example.compose.errorDark,
    onError = _root_ide_package_.com.example.compose.onErrorDark,
    errorContainer = _root_ide_package_.com.example.compose.errorContainerDark,
    onErrorContainer = _root_ide_package_.com.example.compose.onErrorContainerDark,
    background = _root_ide_package_.com.example.compose.backgroundDark,
    onBackground = _root_ide_package_.com.example.compose.onBackgroundDark,
    surface = _root_ide_package_.com.example.compose.surfaceDark,
    onSurface = _root_ide_package_.com.example.compose.onSurfaceDark,
    surfaceVariant = _root_ide_package_.com.example.compose.surfaceVariantDark,
    onSurfaceVariant = _root_ide_package_.com.example.compose.onSurfaceVariantDark,
    outline = _root_ide_package_.com.example.compose.outlineDark,
    outlineVariant = _root_ide_package_.com.example.compose.outlineVariantDark,
    scrim = _root_ide_package_.com.example.compose.scrimDark,
    inverseSurface = _root_ide_package_.com.example.compose.inverseSurfaceDark,
    inverseOnSurface = _root_ide_package_.com.example.compose.inverseOnSurfaceDark,
    inversePrimary = _root_ide_package_.com.example.compose.inversePrimaryDark,
    surfaceDim = _root_ide_package_.com.example.compose.surfaceDimDark,
    surfaceBright = _root_ide_package_.com.example.compose.surfaceBrightDark,
    surfaceContainerLowest = _root_ide_package_.com.example.compose.surfaceContainerLowestDark,
    surfaceContainerLow = _root_ide_package_.com.example.compose.surfaceContainerLowDark,
    surfaceContainer = _root_ide_package_.com.example.compose.surfaceContainerDark,
    surfaceContainerHigh = _root_ide_package_.com.example.compose.surfaceContainerHighDark,
    surfaceContainerHighest = _root_ide_package_.com.example.compose.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = _root_ide_package_.com.example.compose.primaryLightMediumContrast,
    onPrimary = _root_ide_package_.com.example.compose.onPrimaryLightMediumContrast,
    primaryContainer = _root_ide_package_.com.example.compose.primaryContainerLightMediumContrast,
    onPrimaryContainer = _root_ide_package_.com.example.compose.onPrimaryContainerLightMediumContrast,
    secondary = _root_ide_package_.com.example.compose.secondaryLightMediumContrast,
    onSecondary = _root_ide_package_.com.example.compose.onSecondaryLightMediumContrast,
    secondaryContainer = _root_ide_package_.com.example.compose.secondaryContainerLightMediumContrast,
    onSecondaryContainer = _root_ide_package_.com.example.compose.onSecondaryContainerLightMediumContrast,
    tertiary = _root_ide_package_.com.example.compose.tertiaryLightMediumContrast,
    onTertiary = _root_ide_package_.com.example.compose.onTertiaryLightMediumContrast,
    tertiaryContainer = _root_ide_package_.com.example.compose.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = _root_ide_package_.com.example.compose.onTertiaryContainerLightMediumContrast,
    error = _root_ide_package_.com.example.compose.errorLightMediumContrast,
    onError = _root_ide_package_.com.example.compose.onErrorLightMediumContrast,
    errorContainer = _root_ide_package_.com.example.compose.errorContainerLightMediumContrast,
    onErrorContainer = _root_ide_package_.com.example.compose.onErrorContainerLightMediumContrast,
    background = _root_ide_package_.com.example.compose.backgroundLightMediumContrast,
    onBackground = _root_ide_package_.com.example.compose.onBackgroundLightMediumContrast,
    surface = _root_ide_package_.com.example.compose.surfaceLightMediumContrast,
    onSurface = _root_ide_package_.com.example.compose.onSurfaceLightMediumContrast,
    surfaceVariant = _root_ide_package_.com.example.compose.surfaceVariantLightMediumContrast,
    onSurfaceVariant = _root_ide_package_.com.example.compose.onSurfaceVariantLightMediumContrast,
    outline = _root_ide_package_.com.example.compose.outlineLightMediumContrast,
    outlineVariant = _root_ide_package_.com.example.compose.outlineVariantLightMediumContrast,
    scrim = _root_ide_package_.com.example.compose.scrimLightMediumContrast,
    inverseSurface = _root_ide_package_.com.example.compose.inverseSurfaceLightMediumContrast,
    inverseOnSurface = _root_ide_package_.com.example.compose.inverseOnSurfaceLightMediumContrast,
    inversePrimary = _root_ide_package_.com.example.compose.inversePrimaryLightMediumContrast,
    surfaceDim = _root_ide_package_.com.example.compose.surfaceDimLightMediumContrast,
    surfaceBright = _root_ide_package_.com.example.compose.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = _root_ide_package_.com.example.compose.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = _root_ide_package_.com.example.compose.surfaceContainerLowLightMediumContrast,
    surfaceContainer = _root_ide_package_.com.example.compose.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = _root_ide_package_.com.example.compose.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = _root_ide_package_.com.example.compose.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = _root_ide_package_.com.example.compose.primaryLightHighContrast,
    onPrimary = _root_ide_package_.com.example.compose.onPrimaryLightHighContrast,
    primaryContainer = _root_ide_package_.com.example.compose.primaryContainerLightHighContrast,
    onPrimaryContainer = _root_ide_package_.com.example.compose.onPrimaryContainerLightHighContrast,
    secondary = _root_ide_package_.com.example.compose.secondaryLightHighContrast,
    onSecondary = _root_ide_package_.com.example.compose.onSecondaryLightHighContrast,
    secondaryContainer = _root_ide_package_.com.example.compose.secondaryContainerLightHighContrast,
    onSecondaryContainer = _root_ide_package_.com.example.compose.onSecondaryContainerLightHighContrast,
    tertiary = _root_ide_package_.com.example.compose.tertiaryLightHighContrast,
    onTertiary = _root_ide_package_.com.example.compose.onTertiaryLightHighContrast,
    tertiaryContainer = _root_ide_package_.com.example.compose.tertiaryContainerLightHighContrast,
    onTertiaryContainer = _root_ide_package_.com.example.compose.onTertiaryContainerLightHighContrast,
    error = _root_ide_package_.com.example.compose.errorLightHighContrast,
    onError = _root_ide_package_.com.example.compose.onErrorLightHighContrast,
    errorContainer = _root_ide_package_.com.example.compose.errorContainerLightHighContrast,
    onErrorContainer = _root_ide_package_.com.example.compose.onErrorContainerLightHighContrast,
    background = _root_ide_package_.com.example.compose.backgroundLightHighContrast,
    onBackground = _root_ide_package_.com.example.compose.onBackgroundLightHighContrast,
    surface = _root_ide_package_.com.example.compose.surfaceLightHighContrast,
    onSurface = _root_ide_package_.com.example.compose.onSurfaceLightHighContrast,
    surfaceVariant = _root_ide_package_.com.example.compose.surfaceVariantLightHighContrast,
    onSurfaceVariant = _root_ide_package_.com.example.compose.onSurfaceVariantLightHighContrast,
    outline = _root_ide_package_.com.example.compose.outlineLightHighContrast,
    outlineVariant = _root_ide_package_.com.example.compose.outlineVariantLightHighContrast,
    scrim = _root_ide_package_.com.example.compose.scrimLightHighContrast,
    inverseSurface = _root_ide_package_.com.example.compose.inverseSurfaceLightHighContrast,
    inverseOnSurface = _root_ide_package_.com.example.compose.inverseOnSurfaceLightHighContrast,
    inversePrimary = _root_ide_package_.com.example.compose.inversePrimaryLightHighContrast,
    surfaceDim = _root_ide_package_.com.example.compose.surfaceDimLightHighContrast,
    surfaceBright = _root_ide_package_.com.example.compose.surfaceBrightLightHighContrast,
    surfaceContainerLowest = _root_ide_package_.com.example.compose.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = _root_ide_package_.com.example.compose.surfaceContainerLowLightHighContrast,
    surfaceContainer = _root_ide_package_.com.example.compose.surfaceContainerLightHighContrast,
    surfaceContainerHigh = _root_ide_package_.com.example.compose.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = _root_ide_package_.com.example.compose.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = _root_ide_package_.com.example.compose.primaryDarkMediumContrast,
    onPrimary = _root_ide_package_.com.example.compose.onPrimaryDarkMediumContrast,
    primaryContainer = _root_ide_package_.com.example.compose.primaryContainerDarkMediumContrast,
    onPrimaryContainer = _root_ide_package_.com.example.compose.onPrimaryContainerDarkMediumContrast,
    secondary = _root_ide_package_.com.example.compose.secondaryDarkMediumContrast,
    onSecondary = _root_ide_package_.com.example.compose.onSecondaryDarkMediumContrast,
    secondaryContainer = _root_ide_package_.com.example.compose.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = _root_ide_package_.com.example.compose.onSecondaryContainerDarkMediumContrast,
    tertiary = _root_ide_package_.com.example.compose.tertiaryDarkMediumContrast,
    onTertiary = _root_ide_package_.com.example.compose.onTertiaryDarkMediumContrast,
    tertiaryContainer = _root_ide_package_.com.example.compose.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = _root_ide_package_.com.example.compose.onTertiaryContainerDarkMediumContrast,
    error = _root_ide_package_.com.example.compose.errorDarkMediumContrast,
    onError = _root_ide_package_.com.example.compose.onErrorDarkMediumContrast,
    errorContainer = _root_ide_package_.com.example.compose.errorContainerDarkMediumContrast,
    onErrorContainer = _root_ide_package_.com.example.compose.onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

