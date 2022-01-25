module org.libheiffx {
    requires javafx.controls;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;
    requires jdk.incubator.foreign; 
    requires java.logging;
    exports org.libheiffx;    
    exports org.libheif.linuxosx;    
}
