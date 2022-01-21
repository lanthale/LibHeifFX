# 1. copy heif.cc and heif.h
Copy from lib directory the files heif.cc and heif.h to the extracted source code from git in directory ./libheif
# 2. generate conf file
/Library/Java/jdk-17.jdk/Contents/Home/bin/jextract -d . --source -t org.libheif.linuxosx -I /Applications/Xcode.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX.sdk/usr/include --dump-includes libheif-osx.conf ./heif.h
# 23 Edit libheif-osx.conf and remove all with '_' functions (OSX/Linux functions)
# 3.generate source code
/Library/Java/jdk-17.jdk/Contents/Home/bin/jextract -d . --source -t org.libheif.linuxosx -I /Applications/Xcode.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX.sdk/usr/include @libheif-osx.conf ./heif.h