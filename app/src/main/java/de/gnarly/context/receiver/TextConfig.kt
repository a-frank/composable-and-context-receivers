package de.gnarly.context.receiver

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TextConfig(
	val color: Color = Color.Black,
	val initialRotation: Float = 0f,
	private val verticalTranslation: Dp = 0.dp,
	private val horizontalTranslation: Dp = 0.dp,
	private val size: TextUnit = 8.sp,
	// add more config properties
) {
	context(DrawScope)
	val sizePx: Float
		get() = size.toPx()

	context(DrawScope)
	val verticalTranslationPx: Float
		get() = verticalTranslation.toPx()

	context(DrawScope)
	val horizontalTranslationPx: Float
		get() = horizontalTranslation.toPx()

	val aRgbColor: Int
		get() = color.toArgb()
}
