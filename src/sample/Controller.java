package sample;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import services.Values;
import services.ProducerConsumer;

/**
 * @author student
 */
public class Controller implements Initializable, CallbackInterface {

    //    @FXML
//    private TextArea textArea;
//    @FXML
//    private TextArea textArea2;
    @FXML
    private Slider sliderA;
    @FXML
    private Slider sliderB;
    @FXML
    private Slider sliderC;
    @FXML
    private Slider sliderA_1;
    @FXML
    private Slider sliderB_1;
    @FXML
    private Slider sliderC_1;
    @FXML
    private Slider sliderC_2;
    @FXML
    private Button buttonFile1;
    @FXML
    private Button buttonFile2;
    @FXML
    private Button buttonFile3;
    @FXML
    private Label labelA;
    @FXML
    private Label labelB;
    @FXML
    private Label labelC;
    @FXML
    private Label labelA1;
    @FXML
    private Label labelB1;
    @FXML
    private Label labelC1;
    @FXML
    private Label labelC2;
    @FXML
    private Label labelBuffer;
    @FXML
    private Label labelASize;
    @FXML
    private Label labelBSize;
    @FXML
    private Label labelCSize;
    @FXML
    private CheckBox checkBoxA;
    @FXML
    private CheckBox checkBoxB;
    @FXML
    private CheckBox checkBoxC;
    @FXML
    private CheckBox checkBoxA1;
    @FXML
    private CheckBox checkBoxB1;
    @FXML
    private CheckBox checkBoxC1;
    @FXML
    private CheckBox checkBoxC2;
    @FXML
    private ListView<String> listViewProducer;
    @FXML
    private ListView<String> listViewConsumer;


    public String producer = "";
    public String producer_load = "";
    public String consumer = "";
    public int a = 1;

    public ProducerConsumer service;
    public HashSet<Object> hset = new HashSet<Object>();

    public ObservableList<String> itemsProducer = FXCollections.observableArrayList();
    public ObservableList<String> itemsConsumer = FXCollections.observableArrayList();


    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Start
        service.startThreads(hset);
    }

    @FXML
    private void handleButtonFile1(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        File file = fileChooser.showOpenDialog(buttonFile1.getScene().getWindow());
        if (file != null) {
            String path = file.toPath().toString();
            System.out.println(path);
            buttonFile1.setText(path);
            service.p_A.setLista_new(service.func_file(path));
        }
    }

    @FXML
    private void handleButtonFile2(ActionEvent event) {

        FileChooser fileChooser2 = new FileChooser();
        fileChooser2.setTitle("Open Resource File");

        File file2 = fileChooser2.showOpenDialog(buttonFile2.getScene().getWindow());
        if (file2 != null) {
            String path = file2.toPath().toString();
            System.out.println(path);
            buttonFile2.setText(path);
            service.p_B.setLista_new(service.func_file(path));
        }
    }

    @FXML
    private void handleButtonFile3(ActionEvent event) {

        FileChooser fileChooser3 = new FileChooser();
        fileChooser3.setTitle("Open Resource File");

        File file3 = fileChooser3.showOpenDialog(buttonFile3.getScene().getWindow());
        if (file3 != null) {
            String path = file3.toPath().toString();
            System.out.println(path);
            buttonFile3.setText(path);
            service.p_C.setLista_new(service.func_file(path));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        service = new ProducerConsumer(this);

        // Add Objects to HashSet
        // A
        hset.add(service.p_A);
        hset.add(service.c_A_1);
//        // B
        hset.add(service.p_B);
        hset.add(service.c_B_1);
        // C
        hset.add(service.p_C);
        hset.add(service.c_C_1);
        hset.add(service.c_C_2);

        // Set CheckBoxes True
        checkBoxA.setSelected(true);
        checkBoxA1.setSelected(true);

        checkBoxB.setSelected(true);
        checkBoxB1.setSelected(true);

        checkBoxC.setSelected(true);
        checkBoxC1.setSelected(true);
        checkBoxC2.setSelected(true);

        // CheckBoxes
        checkBoxA.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(newValue);
                if (!newValue) {
                    hset.remove(service.p_A);
                } else {
                    hset.add(service.p_A);
                }
                System.out.println(hset.toString());
            }
        });

        checkBoxB.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(newValue);
                if (!newValue) {
                    hset.remove(service.p_B);
                } else {
                    hset.add(service.p_B);
                }
                System.out.println(hset.toString());
            }
        });

        checkBoxC.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(newValue);
                if (!newValue) {
                    hset.remove(service.p_C);
                } else {
                    hset.add(service.p_C);
                }
                System.out.println(hset.toString());
            }
        });

        checkBoxA1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(newValue);
                if (!newValue) {
                    hset.remove(service.c_A_1);
                } else {
                    hset.add(service.c_A_1);
                }
                System.out.println(hset.toString());
            }
        });

        checkBoxB1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(newValue);
                if (!newValue) {
                    hset.remove(service.c_B_1);
                } else {
                    hset.add(service.c_B_1);
                }
                System.out.println(hset.toString());
            }
        });

        checkBoxC1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(newValue);
                if (!newValue) {
                    hset.remove(service.c_C_1);
                } else {
                    hset.add(service.c_C_1);
                }
                System.out.println(hset.toString());
            }
        });

        checkBoxC2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(newValue);
                if (!newValue) {
                    hset.remove(service.c_C_2);
                } else {
                    hset.add(service.c_C_2);
                }
                System.out.println(hset.toString());
            }
        });

        // Sliders
        sliderA.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int val = newValue.intValue() * 100;
                service.setTime(service.p_A, val);
                labelA.setText("Producer A, time: " + Integer.toString(val) + "ms");


            }
        });

        sliderB.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int val = newValue.intValue() * 100;
                service.setTime(service.p_B, val);
                labelB.setText("Producer B, time: " + Integer.toString(val) + "ms");
            }
        });

        sliderC.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int val = newValue.intValue() * 100;
                service.setTime(service.p_C, val);
                labelC.setText("Producer C, time: " + Integer.toString(val) + "ms");
            }
        });

        sliderA_1.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int val = newValue.intValue() * 100;
                service.setTime(service.c_A_1, val);
                labelA1.setText("Consumer A_1, time: " + Integer.toString(val) + "ms");
            }
        });

        sliderB_1.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int val = newValue.intValue() * 100;
                service.setTime(service.c_B_1, val);
                labelB1.setText("Consumer B_1, time: " + Integer.toString(val) + "ms");
            }
        });

        sliderC_1.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int val = newValue.intValue() * 100;
                service.setTime(service.c_C_1, val);
                labelC1.setText("Consumer C_1, time: " + Integer.toString(val) + "ms");

            }
        });

        sliderC_2.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int val = newValue.intValue() * 100;
                service.setTime(service.c_C_2, val);
                labelC2.setText("Consumer C_2, time: " + Integer.toString(val) + "ms");
            }
        });
    }

    @Override
    public void updateViewProducer(String val) {
//        producer_load += val + "";
//        textArea.setText(producer_load);
        itemsProducer.add(val);
        listViewProducer.getItems().add(0, val);

    }

    @Override
    public void updateViewConsumer(String val) {

    }

    @Override
    public void updateViewProducer(String val, boolean pom, int buffer, int list, String threadNo) {
        Platform.runLater(() -> {
//            producer += val + "\n"; // up down
//            producer = val + "\n" + producer;
//            textArea.setText(producer);
//            textArea.scrollTopProperty();
//            sliderA.setStyle("-fx-text-fill: red;");

            itemsProducer.add(val);
            listViewProducer.getItems().add(0, val);

            labelBuffer.setText("Buffer: " + Integer.toString(buffer) + "/" + Values.bufferSize);

            if (buffer == Values.bufferSize) {
                labelBuffer.setStyle("-fx-background-color: red;");
            } else {
                labelBuffer.setStyle("-fx-background-color: limegreen;");
            }


            if (threadNo.equals(Values.ProducerA)) {

                labelASize.setText("Obj. left: " + list);

                if (pom) {
                    labelA.setStyle("-fx-background-color: limegreen;");
                } else {
                    labelA.setStyle("-fx-background-color: red;");
                }
            } else if (threadNo.equals(Values.ProducerB)) {

                labelBSize.setText("Obj. left: " + list);

                if (pom) {
                    labelB.setStyle("-fx-background-color: limegreen;");
                } else {
                    labelB.setStyle("-fx-background-color: red;");
                }
            } else if (threadNo.equals(Values.ProducerC)) {

                labelCSize.setText("Obj. left: " + list);

                if (pom) {
                    labelC.setStyle("-fx-background-color: limegreen;");
                } else {
                    labelC.setStyle("-fx-background-color: red;");
                }
            }
//            textArea.setWrapText(true);
        });
    }

    @Override
    public void updateViewConsumer(String val, boolean pom, int buffer, String threadNo) {
        Platform.runLater(() -> {
//            consumer += val + "\n"; // up down
//            consumer = val + "\n" + consumer;
//            textArea2.setText(consumer);

            labelBuffer.setText("Buffer: " + Integer.toString(buffer) + "/" + Values.bufferSize);

            if (buffer == Values.bufferSize) {
                labelBuffer.setStyle("-fx-background-color: red;");
            } else {
                labelBuffer.setStyle("-fx-background-color: limegreen;");
            }

            itemsConsumer.add(val);
            listViewConsumer.getItems().add(0, val);

            if (threadNo.equals(Values.ConsumerA1)) {
                if (pom) {
                    labelA1.setStyle("-fx-background-color: limegreen;");
                } else {
                    labelA1.setStyle("-fx-background-color: red;");
                }
            } else if (threadNo.equals(Values.ConsumerB1)) {
                if (pom) {
                    labelB1.setStyle("-fx-background-color: limegreen;");
                } else {
                    labelB1.setStyle("-fx-background-color: red;");
                }
            } else if (threadNo.equals(Values.ConsumerC1)) {
                if (pom) {
                    labelC1.setStyle("-fx-background-color: limegreen;");
                } else {
                    labelC1.setStyle("-fx-background-color: red;");
                    labelC2.setStyle("-fx-background-color: red;");
                }
            } else if (threadNo.equals(Values.ConsumerC2)) {
                if (pom) {
                    labelC2.setStyle("-fx-background-color: limegreen;");
                } else {
                    labelC2.setStyle("-fx-background-color: red;");
                    labelC1.setStyle("-fx-background-color: red;");
                }
            }
        });
    }

}