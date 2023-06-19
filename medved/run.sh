if [ "$1" = "local-build" ]; then
    mvn clean package -Dmaven.test.skip
fi

if [ "$1" = "docker-run" ]; then
    docker run --rm medved:$2 $3
fi

if [ "$1" = "test" ]; then
    mvn clean test
fi

if [ "$1" = "local-run" ]; then
    shift
    while [[ $# -gt 1 ]]; do
        case $1 in
            --dataset)
            DATASET="$2"
            shift
            shift
            ;;
            --maxlength)
            MAXLENGTH="$2"
            shift
            shift
            ;;
            --depth)
            DEPTH="$2"
            shift
            shift
            ;;
        esac
    done

    echo "DATASET     = $DATASET"
    echo "MAXLENGTH   = $MAXLENGTH"
    echo "DEPTH       = $DEPTH"
    echo "PROMPT      = $1"
    java -Ddataset=$DATASET -Dmaxlength=$MAXLENGTH -Ddepth=$DEPTH -jar target/*.jar $1
fi

if [ "$1" = "docker-build" ]; then
    shift
    while [[ $# -gt 1 ]]; do
        case $1 in
            --dataset)
            DATASET="$2"
            shift
            shift
            ;;
            --maxlength)
            MAXLENGTH="$2"
            shift
            shift
            ;;
            --depth)
            DEPTH="$2"
            shift
            shift
            ;;
        esac
    done

    TAG=$1
    shift

    echo "DATASET     = $DATASET"
    echo "MAXLENGTH   = $MAXLENGTH"
    echo "DEPTH       = $DEPTH"
    mvn clean package -Dmaven.test.skip
    docker build --progress=plain --build-arg "DATASET=$DATASET" --build-arg "MAXLENGTH=$MAXLENGTH" --build-arg "DEPTH=$DEPTH" -t medved:$TAG .
fi