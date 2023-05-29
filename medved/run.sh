if [ "$1" = "all" ]; then
    mvn clean package
    docker build --no-cache -t medved .
    docker run --rm medved 1 2 3
fi

if [ "$1" = "build" ]; then
    mvn clean package
fi