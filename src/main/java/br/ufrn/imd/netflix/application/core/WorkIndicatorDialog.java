package br.ufrn.imd.netflix.application.core;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
 
/**
 * Public domain. Use as you like. No warranties.
 * P = Input parameter type. Given to the closure as parameter. Return type is always Integer.
 * (cc) @imifos
 */
public class WorkIndicatorDialog<T> {
 
    private Task<Object> animationWorker;
    private Task<T> taskWorker;
 
    private final ProgressIndicator progressIndicator = new ProgressIndicator(ProgressIndicator.INDETERMINATE_PROGRESS);
    private final Stage dialog = new Stage(StageStyle.UNDECORATED);
    private final Label label = new Label();
    private final Group root = new Group();
    private final Scene scene = new Scene(root, 330, 120, Color.WHITE);
    private final BorderPane mainPane = new BorderPane();
    private final VBox vbox = new VBox();
 
    /** Placing a listener on this list allows to get notified BY the result when the task has finished. */
    private ObservableList<T> resultNotificationList = FXCollections.observableArrayList();
 
    private T resultValue;
 
    /**
     *
     */
    public WorkIndicatorDialog(Window owner, String label) {
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(owner);
        dialog.setResizable(false);
        this.label.setText(label);
    }
 
    /**
     * 
     */
    public void onFinish(Consumer<T> c) {
        resultNotificationList.addListener((ListChangeListener<T>) n -> {
            resultNotificationList.clear();
            c.accept(resultValue);
        });
    }
 
    /**
     *
     */
    public void execute(Callable<T> callable) {
        setupDialog();
        setupAnimationThread();
        setupWorkerThread(callable);
    }
     
    /**
     *
     */
    private void setupDialog() {
        root.getChildren().add(mainPane);
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMinSize(330, 120);
        vbox.getChildren().addAll(label,progressIndicator);
        mainPane.setTop(vbox);
        dialog.setScene(scene);
 
        dialog.setOnHiding(event -> { /* Gets notified when task ended, but BEFORE 
                     result value is attributed. Using the observable list above is 
                     recommended. */ });
         
        dialog.show();
    }
 
    /**
     *
     */
    private void setupAnimationThread() {
 
        animationWorker = new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                /*
                This is activated when we have a "progressing" indicator.
                This thread is used to advance progress every XXX milliseconds.
                In case of an INDETERMINATE_PROGRESS indicator, it's not of use.
                for (int i=1;i<=100;i++) {
                    Thread.sleep(500);
                    updateMessage();
                    updateProgress(i,100);
                }
                */
                return true;
            }
        };
 
        progressIndicator.setProgress(0);
        progressIndicator.progressProperty().unbind();
        progressIndicator.progressProperty().bind(animationWorker.progressProperty());
 
        animationWorker.messageProperty().addListener((observable, oldValue, newValue) -> {
            // Do something when the animation value ticker has changed
        });
 
        new Thread(animationWorker).start();
    }
     
    /**
    *
    */
   private void setupWorkerThread(Callable<T> func) {
   	 
       taskWorker = new Task<T>() {
           @Override
           public T call() throws Exception {
               return func.call();
           }
       };

       EventHandler<WorkerStateEvent> eh = event -> {
           animationWorker.cancel(true);
           progressIndicator.progressProperty().unbind();
           dialog.close();
           try {
               resultValue = taskWorker.get();
               resultNotificationList.add(resultValue);   
           } 
           catch (Exception e) {
               throw new RuntimeException(e);
           }
       };

       taskWorker.setOnSucceeded(eh);
       taskWorker.setOnFailed(eh);
        
       new Thread(taskWorker).start();
   }
 
 
 
}
