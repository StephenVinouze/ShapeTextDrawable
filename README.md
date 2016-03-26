# ShapeTextDrawable
When it comes to draw basic shapes such as squares or circles, we encounter many approaches to achieve this goal. Using `Pain` is one of the best approach and yet it is still easier to use an **XML** shape or even an image. Now if we need to have some text inside our shape, then usually we surrender to the easiest and less time consuming solution. This library intends to avoid this "easy" choice by providing a flexible solution to efficiently draw your shapes and even display some text inside them.

Square shape | Round Square shape | Round shape
---- | ---- | ----
![Square shape](art/square.png) | ![Round square shape](art/round_square.png) | ![Round shape](art/round.png)

## Download

Gradle:

```groovy
compile 'com.github.stephenvinouze:shapetextdrawable:1.0.0'
```

Maven:

```xml
<dependency>
  <groupId>com.github.stephenvinouze</groupId>
  <artifactId>shapetextdrawable</artifactId>
  <version>1.0.0</version>
  <type>aar</type>
</dependency>
```

Eclipse: [shapetextdrawable-1.0.0.aar](https://github.com/StephenVinouze/ShapeTextDrawable/releases/download/1.0.0/shapetextdrawable-1.0.0.aar)

## Usage

`ShapeTextDrawable` can be applied to any `View` as a `Drawable`. For instance, you can use it on a `ImageView`.

```xml
<ImageView
        android:id="@+id/image_view"
        android:layout_width="50dp"
        android:layout_height="50dp"/>
```

Next define your `ShapeTextDrawable` and apply it to your `ImageView`.

```Kotlin
val drawable = ShapeTextDrawable(shapeForm)

yourImageView.setImageDrawable(drawable)
```

While configuring your `ShapeTextDrawable`, you must provide a `ShapeForm` that can be either `SQUARE` or `ROUND`. You may also specify a text and customize its color as well as many other properties, but none of these are mandatory.

To draw a rounded square, you can also set a `radius` when using a `SQUARE` shape.

```Kotlin
val drawable = ShapeTextDrawable(ShapeForm.SQUARE, radius = 10f)
```

## Credits

This library is mostly inspired from the well-written [TextDrawable](https://github.com/amulyakhare/TextDrawable). It was revisited to lighten this library and port it to **Kotlin**

## License

```
Copyright 2016 Stephen Vinouze.

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
