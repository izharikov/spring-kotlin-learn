set -x
./gradlew run &
sleep 1
echo $! > .pidfile
set +x