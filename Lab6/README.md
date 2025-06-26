# SmartTasks - Android Push Notification App

Ứng dụng Android với tính năng thông báo đẩy sử dụng Firebase Cloud Messaging (FCM).

## 🚀 Tính năng

- ✅ **Google Sign-in** - Đăng nhập bằng tài khoản Google
- ✅ **Push Notifications** - Thông báo đẩy từ Firebase
- ✅ **Navigation** - Điều hướng giữa các màn hình
- ✅ **Modern UI** - Giao diện đẹp với Jetpack Compose
- ✅ **Permission Handling** - Xử lý quyền thông báo

## 📱 Cấu trúc dự án

```
app/src/main/java/com/uth/myapplication/
├── MainActivity.kt                    # Activity chính với navigation
├── MyFirebaseMessagingService.kt     # Service xử lý FCM
├── NotificationManager.kt            # Quản lý thông báo
├── NotificationTestActivity.kt       # Test thông báo
└── ui/
    ├── WelcomeScreen.kt              # Màn hình chào mừng
    ├── ProfileScreen.kt              # Màn hình hồ sơ
    └── theme/                        # Theme và styling
```

## 🔧 Cài đặt

### 1. Firebase Setup

1. Tạo project trên [Firebase Console](https://console.firebase.google.com/)
2. Thêm ứng dụng Android với package name: `com.uth.myapplication`
3. Tải file `google-services.json` và đặt vào thư mục `app/`
4. Bật Firebase Cloud Messaging trong project

### 2. Google Sign-in Setup

1. Trong Firebase Console, vào Authentication > Sign-in method
2. Bật Google Sign-in
3. Thêm SHA-1 fingerprint của project

### 3. Build và Run

```bash
# Clone project
git clone <repository-url>
cd Lab6

# Build project
./gradlew build

# Run trên device/emulator
./gradlew installDebug
```

## 📨 Push Notification Implementation

### 1. Firebase Cloud Messaging Service

```kotlin
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        // Lưu token mới
        sendRegistrationToServer(token)
    }
    
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Xử lý thông báo nhận được
        sendNotification(title, message, taskId)
    }
}
```

### 2. Notification Manager

```kotlin
class NotificationManager(private val context: Context) {
    suspend fun getFCMToken(): String? {
        return FirebaseMessaging.getInstance().token.await()
    }
    
    suspend fun subscribeToTopic(topic: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic).await()
    }
}
```

### 3. Permission Handling

```kotlin
// Request notification permission for Android 13+
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
}
```

## 🧪 Test Notifications

### 1. Local Testing

Chạy `NotificationTestActivity` để test thông báo local:

```kotlin
// Mở NotificationTestActivity
val intent = Intent(this, NotificationTestActivity::class.java)
startActivity(intent)
```

### 2. Firebase Console Testing

1. Vào Firebase Console > Cloud Messaging
2. Tạo campaign mới
3. Chọn target: "Single device" hoặc "Topic"
4. Nhập FCM token từ logcat
5. Gửi test message

### 3. Server-side Testing

Sử dụng Firebase Admin SDK hoặc REST API:

```bash
curl -X POST -H "Authorization: key=YOUR_SERVER_KEY" \
     -H "Content-Type: application/json" \
     -d '{
       "to": "DEVICE_TOKEN",
       "notification": {
         "title": "SmartTasks",
         "body": "You have a new task!"
       },
       "data": {
         "taskId": "task_123"
       }
     }' \
     https://fcm.googleapis.com/fcm/send
```

## 📋 Quyền cần thiết

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

## 🔄 Workflow

1. **App khởi động** → Request notification permission
2. **User đăng nhập** → Lấy FCM token và subscribe topics
3. **Nhận thông báo** → Hiển thị notification với sound/vibration
4. **Tap notification** → Mở app và navigate đến màn hình phù hợp

## 🎯 Customization

### Thay đổi notification style:

```kotlin
val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
    .setSmallIcon(R.drawable.ic_launcher_foreground)
    .setContentTitle("Custom Title")
    .setContentText("Custom Message")
    .setAutoCancel(true)
    .setSound(defaultSoundUri)
    .setPriority(NotificationCompat.PRIORITY_HIGH)
```

### Thêm custom actions:

```kotlin
val actionIntent = Intent(this, MainActivity::class.java)
val actionPendingIntent = PendingIntent.getActivity(
    this, 0, actionIntent, PendingIntent.FLAG_IMMUTABLE
)

notificationBuilder.addAction(
    R.drawable.ic_action,
    "Open App",
    actionPendingIntent
)
```

## 🐛 Troubleshooting

### Common Issues:

1. **Notification không hiển thị**
   - Kiểm tra permission POST_NOTIFICATIONS
   - Verify notification channel được tạo
   - Check FCM token có hợp lệ

2. **Google Sign-in failed**
   - Verify SHA-1 fingerprint
   - Check google-services.json
   - Ensure OAuth client ID đúng

3. **FCM token null**
   - Check internet connection
   - Verify Firebase project setup
   - Check google-services.json

## 📚 Resources

- [Firebase Cloud Messaging Documentation](https://firebase.google.com/docs/cloud-messaging)
- [Android Notification Guide](https://developer.android.com/guide/topics/ui/notifiers/notifications)
- [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation)

## 🤝 Contributing

1. Fork project
2. Create feature branch
3. Commit changes
4. Push to branch
5. Create Pull Request

## 📄 License

This project is licensed under the MIT License. 