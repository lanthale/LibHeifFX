package org.libheiffx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX TestApp
 */
public class TestApp extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException, IOException {
        HEIFImageLoaderFactory.install();

        Parameters parameters = getParameters();
        String file = parameters.getRaw().get(0);
        String file2 = parameters.getRaw().get(1);
        String file3 = parameters.getRaw().get(2);

        VBox stack = new VBox();
        stack.setPadding(new Insets(10));
        stack.setSpacing(5);
        stack.setAlignment(Pos.TOP_CENTER);
        loadImagesByStream(stack, file, file2, file3);
        //loadImagesByFile(stack, System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "ressources" + File.separator + "image0.heic");

        var scene = new Scene(stack, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private void loadImagesByStream(VBox stack, String file, String file2, String file3) throws MalformedURLException {
        File initialFile = new File(file);
        File initialFile2 = new File(file2);
        File initialFile3 = new File(file3);
        //ind.progressProperty().bind(img.progressProperty());
        Button btn = new Button("Refresh");
        stack.getChildren().add(btn);
        stack.getChildren().add(new ProgressBar());
        loadImages(stack, initialFile, initialFile2, initialFile3);

        btn.setOnAction((t) -> {
            stack.getChildren().clear();
            stack.getChildren().add(btn);
            stack.getChildren().add(new ProgressBar());
            Platform.runLater(() -> {
                try {
                    loadImages(stack, initialFile, initialFile2, initialFile3);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(TestApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });

    }

    private void loadImages(VBox stack, File initialFile, File initialFile2, File initialFile3) throws MalformedURLException {
        boolean backgroundLoading = true;
        int size = 200;
        ImageView view = new ImageView();
        ImageView view2 = new ImageView();
        ImageView view3 = new ImageView();
        ProgressIndicator ind = new ProgressIndicator();
        ProgressIndicator ind2 = new ProgressIndicator();
        ProgressIndicator ind3 = new ProgressIndicator();
        stack.getChildren().add(ind);
        stack.getChildren().add(ind2);
        stack.getChildren().add(ind3);
        Image img = new Image(initialFile.toURI().toURL().toString(), size, size, true, true, backgroundLoading);
        img.progressProperty().addListener((ov, t, t1) -> {
            if (t1.doubleValue() == 1.0) {
                stack.getChildren().remove(ind);
                HBox hb = new HBox();
                stack.getChildren().add(hb);
                hb.getChildren().add(view);
                Platform.runLater(() -> {
                    try {
                        HashMap<String, String> metaData = new LibheifImage(initialFile.getAbsolutePath()).getMetaData();
                        ScrollPane sc = new ScrollPane();
                        TextArea vb = new TextArea();
                        metaData.entrySet().forEach((entry) -> {
                            //Label l = new Label(entry.getKey() + " " + entry.getValue());
                            vb.appendText(entry.getKey() + " " + entry.getValue() + "\n");
                            //vb.getChildren().add(l);
                        });
                        sc.setContent(vb);
                        hb.getChildren().add(sc);
                    } catch (IOException ex) {
                        Logger.getLogger(TestApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                if (img.getException() != null) {
                    System.out.println("Exception for: " + img.getUrl());
                    img.getException().printStackTrace();
                }
            }
        });
        ind.progressProperty().bind(img.progressProperty());
        Image img2 = new Image(initialFile2.toURI().toURL().toString(), size, size, true, true, backgroundLoading);
        img2.progressProperty().addListener((ov, t, t1) -> {
            if (t1.doubleValue() == 1.0) {
                stack.getChildren().remove(ind2);
                HBox hb = new HBox();
                stack.getChildren().add(hb);
                hb.getChildren().add(view2);
                Platform.runLater(() -> {
                    try {
                        HashMap<String, String> metaData = new LibheifImage(initialFile2.getAbsolutePath()).getMetaData();
                        ScrollPane sc = new ScrollPane();
                        TextArea vb = new TextArea();
                        metaData.entrySet().forEach((entry) -> {
                            //Label l = new Label(entry.getKey() + " " + entry.getValue());
                            vb.appendText(entry.getKey() + " " + entry.getValue() + "\n");
                            //vb.getChildren().add(l);
                        });
                        sc.setContent(vb);
                        hb.getChildren().add(sc);
                    } catch (IOException ex) {
                        Logger.getLogger(TestApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                if (img2.getException() != null) {
                    System.out.println("Exception for: " + img2.getUrl());
                    img2.getException().printStackTrace();
                }
            }
        });
        ind2.progressProperty().bind(img2.progressProperty());
        Image img3 = new Image(initialFile3.toURI().toURL().toString(), size, size, true, true, backgroundLoading);
        img3.progressProperty().addListener((ov, t, t1) -> {
            if (t1.doubleValue() == 1.0) {
                stack.getChildren().remove(ind3);
                HBox hb = new HBox();
                stack.getChildren().add(hb);
                hb.getChildren().add(view3);
                Platform.runLater(() -> {
                    try {
                        HashMap<String, String> metaData = new LibheifImage(initialFile3.getAbsolutePath()).getMetaData();
                        ScrollPane sc = new ScrollPane();
                        TextArea vb = new TextArea();
                        metaData.entrySet().forEach((entry) -> {
                            //Label l = new Label(entry.getKey() + " " + entry.getValue());
                            vb.appendText(entry.getKey() + " " + entry.getValue() + "\n");
                            //vb.getChildren().add(l);
                        });
                        sc.setContent(vb);
                        hb.getChildren().add(sc);
                    } catch (IOException ex) {
                        Logger.getLogger(TestApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                if (img3.getException() != null) {
                    System.out.println("Exception for: " + img3.getUrl());
                    img3.getException().printStackTrace();
                }
            }
        });
        ind3.progressProperty().bind(img3.progressProperty());
        view.setImage(img);
        view.setFitHeight(size);
        view.setFitWidth(size);
        view.setPreserveRatio(true);
        view2.setImage(img2);
        view2.setFitHeight(size);
        view2.setFitWidth(size);
        view2.setPreserveRatio(true);
        view3.setImage(img3);
        view3.setFitHeight(size);
        view3.setFitWidth(size);
        view3.setPreserveRatio(true);
    }

    private void loadImagesByFile(VBox stack, String file) throws IOException {
        //stack.getChildren().add(ind);
        //ind.progressProperty().bind(img.progressProperty());
        File initialFile = new File(file);
        LibheifImage libheif = new LibheifImage(initialFile.getAbsolutePath());
        for (int i = 0; i < 5; i++) {
            System.out.println("i " + i);
            int[] raw = libheif.readPixelData();
            WritableImage img = new WritableImage(libheif.getImageWidth(), libheif.getImageHeight());
            PixelWriter pw = img.getPixelWriter();
            pw.setPixels(0, 0, libheif.getImageWidth(), libheif.getImageHeight(), PixelFormat.getIntArgbInstance(), raw, 0, libheif.getImageWidth());
            ImageView view = new ImageView();
            view.setFitHeight(400);
            view.setFitWidth(400);
            view.setPreserveRatio(true);
            stack.getChildren().add(view);
            view.setImage(img);
            raw=null;
        }
        System.out.println("Finished!");
        //stack.getChildren().remove(ind);
    }

    public static void main(String[] args) {
        args = new String[3];
        args[2] = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "ressources" + File.separator + "image0.heic";
        args[1] = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "ressources" + File.separator + "image1.heic";
        args[0] = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "ressources" + File.separator + "image2.heic";
        Logger logger = Logger.getLogger("");
        Handler handler = new ConsoleHandler();
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter());
        launch(args);
    }

}
