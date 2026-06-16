# ProGuard rules for ComposeApp
# Keep line number info to help symbolication
-keepattributes SourceFile,LineNumberTable

# Keep Firebase Crashlytics related classes (usually not required, but safe to keep)
-keep class com.google.firebase.crashlytics.** { *; }
-keep class com.google.firebase.messaging.** { *; }

# Keep any classes used by reflection/serialization if necessary
# Add explicit keep rules here if you see missing symbols in R8 output

