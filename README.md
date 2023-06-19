# Java-NLG

You can see all the information on the [WIKI](https://github.com/heartsker/Java-NLG/wiki)

## For the user

### I have docker image with tag:

You can use this docker image!

```
./run.sh docker-run <tag> "<prompt>"
```

OR

```
docker run --rm medved:<tag> "<prompt>"
```

### I don't have docker image with tag:

You can create the image of the medved with your own settings for yourself.

For example, you have a dataset of jokes, you can create your docker image 

```
./run.sh docker-build --dataset <path_to_jokes_dataset> --length <your_number> --depth <your_number> <your_tag>
```

And then use this image

```
./run.sh docker-run <your_tag> "<your_prompt>"
```

### For the developer

```
./run.sh build

./run.sh test

./run.sh local-build

./run.sh local-run --dataset <path> --length <number> --depth <number> "<prompt>"

./run.sh docker-build --dataset <path> --length <number> --depth <number> <tag>

./run.sh docker-run <tag> "<prompt>"
```