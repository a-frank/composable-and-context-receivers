package de.gnarly.context.receiver

import android.app.Activity
import android.widget.Toast

context(Activity)
fun String.toast(duration: Int = Toast.LENGTH_LONG) {
	Toast.makeText(this@Activity, this, duration).show()
}

context(Activity, String)
fun contextToast(duration: Int = Toast.LENGTH_LONG) {
	Toast.makeText(this@Activity, this@String, duration).show()
}
