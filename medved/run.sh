if [ "$1" = "all" ]; then
    mvn clean package
    docker build -t medved:local .
    docker run --rm medved:local
fi

if [ "$1" = "build" ]; then
    mvn clean package
fi