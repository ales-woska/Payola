set SCRIPT_DIR=%~dp0
C:\software\jdk1.7.0_79\bin\java -Dsbt.global.base=c:\.sbt -Dsbt.ivy.home=c:\.ivy2 -Xmx2048M -XX:MaxPermSize=512M -Xss2M -jar "%SCRIPT_DIR%sbt-launch.jar" %*