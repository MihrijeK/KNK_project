package sample.Controllers.Partials;

import Helpers.GuestPayment;
import Helpers.Rooms;
import Helpers.Service_Type;
import Helpers.Staff;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Controllers.Admin.RoomControllers.EditRoomsView;
import sample.Controllers.Admin.Services.EditServiceType;
import sample.Controllers.Admin.StaffControllers.EditStaffMemberView;
import sample.Controllers.PaymentView;
import sample.Controllers.ReservationsController;
import sample.Repositories.RoomRespository;
import sample.Repositories.ServicesTypeRepository;
import sample.Repositories.StaffRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AddButton {

    public static void addEditRoomBtn(TableView<Rooms> roomsTableView, String btnName, ObservableList<Rooms> room) {
        TableColumn<Rooms, Void> colBtn = new TableColumn(btnName);

        Callback<TableColumn<Rooms, Void>, TableCell<Rooms, Void>> cellFactory = new Callback<TableColumn<Rooms, Void>, TableCell<Rooms, Void>>() {
            @Override
            public TableCell<Rooms, Void> call(final TableColumn<Rooms, Void> param) {
                final TableCell<Rooms, Void> cell = new TableCell<Rooms, Void>() {
                    private final javafx.scene.control.Button btn = new javafx.scene.control.Button(btnName);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Rooms data = getTableView().getItems().get(getIndex());
                            EditRoomsView ew = null;
                            try {
                                ew = new EditRoomsView(data);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                ew.display(room);
                                roomsTableView.getItems().remove(getIndex());
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        roomsTableView.getColumns().add(colBtn);
    }

    public static void addEditStaffBtn(TableView<Staff> showStaffTable, String btnName, ObservableList<Staff> staffi) {
        TableColumn<Staff, Void> colBtn = new TableColumn(btnName);

        Callback<TableColumn<Staff, Void>, TableCell<Staff, Void>> cellFactory = new Callback<TableColumn<Staff, Void>, TableCell<Staff, Void>>() {
            @Override
            public TableCell<Staff, Void> call(final TableColumn<Staff, Void> param) {
                final TableCell<Staff, Void> cell = new TableCell<Staff, Void>() {
                    private final javafx.scene.control.Button btn = new javafx.scene.control.Button(btnName);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Staff data = getTableView().getItems().get(getIndex());
                            showStaffTable.getItems().remove(getIndex());
                            EditStaffMemberView ew = null;
                            try {
                                ew = new EditStaffMemberView(data);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ew.display(staffi);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        showStaffTable.getColumns().add(colBtn);
    }

    public static void addEditServiceTypeBtn(TableView<Service_Type> showServiceTable, String btnName, ObservableList<Service_Type> observableList) {
        TableColumn<Service_Type, Void> colBtn = new TableColumn(btnName);

        Callback<TableColumn<Service_Type, Void>, TableCell<Service_Type, Void>> cellFactory = new Callback<TableColumn<Service_Type, Void>, TableCell<Service_Type, Void>>() {
            @Override
            public TableCell<Service_Type, Void> call(final TableColumn<Service_Type, Void> param) {
                final TableCell<Service_Type, Void> cell = new TableCell<Service_Type, Void>() {
                    private final javafx.scene.control.Button btn = new javafx.scene.control.Button(btnName);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Service_Type data = getTableView().getItems().get(getIndex());
                            showServiceTable.getItems().remove(getIndex());
                            EditServiceType ew = null;
                            try {
                                ew = new EditServiceType(data);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ew.display(observableList);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        showServiceTable.getColumns().add(colBtn);
    }

    public static void addDeleteStaffBtn(TableView<Staff> showStaffTable, String btnName) {
        TableColumn<Staff, Void> colBtn = new TableColumn(btnName);

        Callback<TableColumn<Staff, Void>, TableCell<Staff, Void>> cellFactory = new Callback<TableColumn<Staff, Void>, TableCell<Staff, Void>>() {
            @Override
            public TableCell<Staff, Void> call(final TableColumn<Staff, Void> param) {
                final TableCell<Staff, Void> cell = new TableCell<Staff, Void>() {
                    private final javafx.scene.control.Button btn = new javafx.scene.control.Button(btnName);

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            Staff data = getTableView().getItems().get(getIndex());
                            UserCardController us = new UserCardController();
                            try {
                                if(us.display(data,null)){
                                    try {
                                        StaffRepository.remove(data.getId());
                                        showStaffTable.getItems().remove(getIndex());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        showStaffTable.getColumns().add(colBtn);
    }

    public static void addDeleteRoomBtn(TableView<Rooms> showRoomsTable, String btnName) {
        TableColumn<Rooms, Void> colBtn = new TableColumn(btnName);

        Callback<TableColumn<Rooms, Void>, TableCell<Rooms, Void>> cellFactory = new Callback<TableColumn<Rooms, Void>, TableCell<Rooms, Void>>() {
            @Override
            public TableCell<Rooms, Void> call(final TableColumn<Rooms, Void> param) {
                final TableCell<Rooms, Void> cell = new TableCell<Rooms, Void>() {
                    private final javafx.scene.control.Button btn = new javafx.scene.control.Button(btnName);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Rooms data = getTableView().getItems().get(getIndex());
                            try {
                                RoomRespository.remove(data.getRoom_number());
                                showRoomsTable.getItems().remove(getIndex());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        showRoomsTable.getColumns().add(colBtn);
    }

    public static void addDeleteServiceTypeBtn(TableView<Service_Type> serviceTable, String btnName) {
        TableColumn<Service_Type, Void> colBtn = new TableColumn(btnName);

        Callback<TableColumn<Service_Type, Void>, TableCell<Service_Type, Void>> cellFactory = new Callback<TableColumn<Service_Type, Void>, TableCell<Service_Type, Void>>() {
            @Override
            public TableCell<Service_Type, Void> call(final TableColumn<Service_Type, Void> param) {
                final TableCell<Service_Type, Void> cell = new TableCell<Service_Type, Void>() {
                    private final javafx.scene.control.Button btn = new javafx.scene.control.Button(btnName);

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            Service_Type data = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Delte Room");
                            alert.showAndWait();
                            try {
                                ServicesTypeRepository.remove(data.getId());
                                serviceTable.getItems().remove(getIndex());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        serviceTable.getColumns().add(colBtn);
    }

    public static void addPayButton(TableView<GuestPayment> paymentTable, String btnName, AnchorPane anchor) {
        TableColumn<GuestPayment, Void> colBtn = new TableColumn(btnName);

        Callback<TableColumn<GuestPayment, Void>, TableCell<GuestPayment, Void>> cellFactory = new Callback<TableColumn<GuestPayment, Void>, TableCell<GuestPayment, Void>>() {
            @Override
            public TableCell<GuestPayment, Void> call(final TableColumn<GuestPayment, Void> param) {
                final TableCell<GuestPayment, Void> cell = new TableCell<GuestPayment, Void>() {
                    private final javafx.scene.control.Button btn = new javafx.scene.control.Button(btnName);

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            GuestPayment data = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Pay");
                            alert.showAndWait();
                            try {
                                GuestPayment guestPayment=paymentTable.getItems().get(getIndex());
                                URL url = new File("src/sample/Views/PaymentView.fxml").toURI().toURL();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(url);
                                Pane newScreen = loader.load();
                                PaymentView controller=loader.getController();
                                controller.getPaymentId(guestPayment.getId());

                                Scene scene=new Scene(newScreen);
                                Stage stage=(Stage)anchor.getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        paymentTable.getColumns().add(colBtn);
    }

}
