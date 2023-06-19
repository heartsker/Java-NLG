# Java-NLG

You can see all the information on the [WIKI](https://github.com/heartsker/Java-NLG/wiki)

## For the user

If you have the docker image:

```
docker run --rm medved:<tag> <prompt>
```

## Ror the developer

```
./run.sh build

./run.sh test

./run.sh local --dataset <path> --length <number> --depth <number> "<prompt>"

./run.sh docker --dataset <path> --length <number> --depth <number> "<prompt>"
```