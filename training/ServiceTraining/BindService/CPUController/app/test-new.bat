set SIGN_PATH=C:\Users\Administrator\Desktop\SIGNAPK
set SIGN_APP=CpuController
java -jar %SIGN_PATH%\signapk.jar %SIGN_PATH%\platform.x509.pem %SIGN_PATH%\platform.pk8 %~dp0build\outputs\apk\debug\%SIGN_APP%.apk %~dp0build\outputs\apk\debug\%SIGN_APP%-signed.apk
adb root
adb remount
adb push %~dp0build\outputs\apk\debug\%SIGN_APP%-signed.apk system/app/CpuController

pause