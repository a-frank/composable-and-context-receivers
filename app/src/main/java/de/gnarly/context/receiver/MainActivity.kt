package de.gnarly.context.receiver

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.gnarly.context.receiver.ui.theme.ContextReceiverTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ContextReceiverTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colors.background
				) {

					val config = TextConfig(
						size = 32.sp,
						initialRotation = 45f,
						verticalTranslation = (-64).dp,
						horizontalTranslation = 32.dp,
						color = Color.Black
					)
					MainScreen(
						textConfig = config
					) {
						it.toast()

						// with(it) {
						// 	contextToast()
						// }
					}
				}
			}
		}
	}
}

@Composable
fun MainScreen(
	textConfig: TextConfig,
	onButtonClicked: (String) -> Unit
) {

	var enteredText by remember { mutableStateOf("") }
	var textRotation by remember { mutableStateOf(textConfig.initialRotation) }

	Column(
		modifier = Modifier.padding(16.dp)
	) {
		TextField(
			value = enteredText,
			onValueChange = { enteredText = it }
		)
		Spacer(modifier = Modifier.size(8.dp))
		Button(onClick = { onButtonClicked(enteredText) }) {
			Text(text = "Click me")
		}
		Spacer(modifier = Modifier.size(8.dp))
		Slider(
			value = textRotation,
			valueRange = 0f..360f,
			onValueChange = {
				textRotation = it
			},
			modifier = Modifier.fillMaxWidth()
		)
		Canvas(
			modifier = Modifier
				.fillMaxSize()
				.background(color = Color.DarkGray)
		) {
			val paint = Paint().apply {
				color = textConfig.aRgbColor
				textSize = textConfig.sizePx
			}
			val x = (size.width / 2f) - (enteredText.length / 4f * textConfig.sizePx) + textConfig.horizontalTranslationPx
			val y = size.height / 2f + textConfig.sizePx / 2f + textConfig.verticalTranslationPx
			val rotationPivot = Offset(
				x = size.width / 2f + textConfig.horizontalTranslationPx,
				y = size.height / 2f + textConfig.verticalTranslationPx
			)

			drawIntoCanvas {
				rotate(
					degrees = textRotation,
					pivot = rotationPivot
				) {
					it.nativeCanvas.drawText(
						enteredText,
						x,
						y,
						paint
					)
				}
			}
		}
	}
}