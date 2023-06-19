if [ "$1" = "build" ]; then
    mvn clean package -Dmaven.test.skip
fi

if [ "$1" = "test" ]; then
    mvn clean test
fi

if [ "$1" = "local" ]; then
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
    mvn clean package -Dmaven.test.skip
    java -Ddataset=$DATASET -Dmaxlength=$MAXLENGTH -Ddepth=$DEPTH -jar target/*.jar $1
fi

if [ "$1" = "docker" ]; then
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
    mvn clean package -Dmaven.test.skip
    docker build --progress=plain --build-arg "DATASET=$DATASET" --build-arg "MAXLENGTH=$MAXLENGTH" --build-arg "DEPTH=$DEPTH" -t medved:local .
    docker run --rm medved:local $1
fi