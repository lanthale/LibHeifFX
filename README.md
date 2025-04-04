# LibHeifFX
Integration of Libheif (https://github.com/strukturag/libheif) library for JavaFX for all major operating systems (Linux, Windows, OSX). 
All flavoris of heif formats can be loaded with the Image class and manipulated by Pixelwriter/Pixelreader. Limitation is that the image class only supports 8-bit color deph but converts all 16bit image format to 8bit automatically.

[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/donate/?hosted_button_id=CXWX6CAQ5MMV4)

**JDK 24 and JavaFX 24 is required for v1.2.7** because of the foreign linker API usage and important changes (Windows) for threading happenend in Panama in JDK21

**JDK 18 is required for v1.1.9** because of the foreign linker API usage and the big changes for threading happenend in Panama in JDK18

## Status
Now the lib is in release status. That means it is tested on OSX, Windows, Ubuntu 20.4 under different threading scenarious.

Supported OS:
- Linux (min. glibc 2.27 which means Ubuntu 18 or later)
- OSX up to 12.5 including Apple M1
- Windows 10/11

I have integrated Libheif 1.12.0 (https://github.com/strukturag/libheif/releases/tag/v1.12.0)

## Open topics
- AVIF is not recognized as a valid image file format

## Usage
Point to the maven coordinates:

**JDK18**
```
<dependency>  
    <groupId>org.libheiffx</groupId>    
    <artifactId>LibHeifFX</artifactId>  
    <version>1.1.9</version>  
</dependency>  
```
**JDK24**
```
<dependency>  
    <groupId>org.libheiffx</groupId>    
    <artifactId>LibHeifFX</artifactId>  
    <version>1.2.7</version>  
</dependency>  
```

- In the Class where the start method is add as one of the first lines the following code to install the file handler:

     `HEIFImageLoaderFactory.install();`  

- and add the following lines to your java config for JDK18:
```
--add-modules jdk.incubator.foreign --enable-native-access=org.libheiffx  
--add-exports=javafx.graphics/com.sun.javafx.iio=org.libheiffx 
--add-exports=javafx.graphics/com.sun.javafx.iio.common=org.libheiffx
```

- add the following lines to your java config for JDK24:
```
--enable-preview
--enable-native-access=org.libheiffx
--add-exports=javafx.graphics/com.sun.javafx.iio=org.libheiffx 
--add-exports=javafx.graphics/com.sun.javafx.iio.common=org.libheiffx
```

- **Module name: org.libheiffx**

You can have a look into the class TestAPP.java to see how to use it, but generally just create an Image with the URL/stream and add it to the image view:

```
  Image img=new Image(initialFile.toURI().toURL().toString(), false);  
  ImageView view = new ImageView();  
  view.setFitHeight(200);  
  view.setFitWidth(200);  
  view.setPreserveRatio(true);  
  stack.getChildren().add(view);  
  view.setImage(img);
```  

You can also use the lib without adding the file handler. What I mean is that you can also forget the "...install" line and just load a file URL with the lib (see the `TestApp.java` to see how it works).

## Steps to create your own build:
- OpenJDK/Adoptium 24
- JavaFX 24
- SET JAVA_HOME variable
- Execute `mvn clean compile package -f LibHeifFX/pom.xml`
- To run the example execute `mvn javafx:run@cli-default`
- IDE Integration: execute as a maven goal `javafx:run@ide-debug` or `javafx:run@ide-profile`

# Notes:
- The orginal C interface was changed to get it working with project panama
- The lib is using heavily the foreign memory API from Java 21 including functions from the project Panama and therefore the tool `JExtract` (Must be build separately)
- All classes inside of package `org.libheif.win` / `org.libheif.nativ` are generated by jextract but tuned for operating system specifica
- Inside of these packages above I exposed also the raw interface to libheif
- The main class to interact with the native lib is the class `LibHeifImage.java` in package `org.libheiffx`
     

