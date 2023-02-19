package ru.faaen.hackapp.messaging

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import okhttp3.internal.notify
import ru.faaen.hackapp.R
import timber.log.Timber

class PushNotificationService: FirebaseMessagingService() {

    private var notificationId: Int = 0

    private fun showNotification(title: String, text: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }

        val style = NotificationCompat.BigTextStyle()
            .bigText(text) as NotificationCompat.Style

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_logo)
            .setStyle(style)
            .setAutoCancel(false)

        NotificationManagerCompat.from(this).notify(notificationId, notification.build())
        notificationId += 1
    }

    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let { notification ->
            val title = notification.title.orEmpty()
            val text = notification.body.orEmpty()

            showNotification(title, text)
        }

        super.onMessageReceived(message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.tag(TOKEN_TAG).d(token)
    }

    companion object {
        const val TOKEN_TAG = "TOKEN_TAG"

        private const val CHANNEL_ID = "EVENTS_CHANNEL"
        private const val CHANNEL_NAME = "События"
    }
}