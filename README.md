# Khit

Khit is an annotation processor that helps you read table files(_Microsoft Excels, OpenOffice Calculator_). It is a wrapper over the native SDKs that each platforms offers.

# How to

To get started, simply annotate a class with `@Page`. This will tell Khit to generate and adapter for this class.

Next, annotate each member with `@Key` and give the name of the column where the value is.

``` kotlin
@Page
data class Item(
    @Key(name = "id") val id: Double,
    @Key(name = "name") val name: String?,
    @Key(name = "available") val available: Boolean,
)
```

But to parse them, you will need an adapter.

In order to get the adapter, use ` Khit.adapter(T::class.java)`. This will return a `KhitAdapter` of type `T` that can parse sheets.

Next, get a file reader using `KhitFileReader`, 
```kotlin
val reader = KhitFileReader {
    path = AbsolutFilePath("path_to_your_file")
    type = FileType.MICROSOFT
}
```
Load your file, select the `KhitSheet` you want and pass it to your adapter!

The adapter will open & read the sheet and will return you a list of `RowReadResults`, that tells you which rows were succesfully read and which were not. 

:smile: That's all!

# Limitations

Currently Khit only supports Doubles, Strings and Booleans, nullable or not.

Default values are not currently supported.

Platforms supported:

| Microsoft | OpenOffice |
|:---------:|:----------:|
| :white_check_mark: | :x: |


# Add to project

_Not currently in any repository. When a stable version is reached, it will be uploaded to Maven._

_Meanwhile, feel free to copy & explore the project._

# License

```
Copyright 2022 Hadăr Alexandru, hdralexandru.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
