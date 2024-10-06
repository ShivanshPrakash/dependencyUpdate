This a tool which provides the latest version for input Gradle/Maven dependencies.

## How to use
Since there is no pre-built binaries or releases being shipped with this project of now, you will have to build the binary yourself.

### Input
By default, input dependencies can be defined in `input.txt`. Each line of the file should denote a single library in proper dependency notation. `<group>:<name>:<version>`

```
com.airbnb.android:lottie-compose:6.0.0
com.airbnb.android:lottie:5.1.1
com.airbnb.android:lottie:6.1.0
```

### Output
Depending on what is defined in `FileIOConstants.getOutputFile()`, the output would be present in either `output.txt` or `experiments.txt`.

```
com.airbnb.android:lottie-compose:6.0.0 -> 6.5.2
com.airbnb.android:lottie:5.1.1 -> 6.5.2
com.airbnb.android:lottie:6.1.0 -> 6.5.2
```

**Note** - Currently, the latest version resolution for each dependency happens sequentially. We can fasten this up by doing this is parallel.