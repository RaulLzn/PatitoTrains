package patitotrains.model.domain;

import raul.Model.array.Array;

public class Train {
    private String idTrain;
    private String name;
    private int milieage;
    private TrainType type;
    private Array<PassengerWagon> passengerWagons;
    private Array<ContainerWagon> containerWagons;
    private boolean onJourney;
    private boolean disabled;

    //constructor con parametros
    public Train(String idTrain, String name, int milieage, TrainType type, Array<PassengerWagon> passengerWagons, Array<ContainerWagon> containerWagons, boolean onJourney, boolean disabled) {
        this.idTrain = idTrain;
        this.name = name;
        this.milieage = milieage;
        this.type = type;
        this.passengerWagons = passengerWagons;
        this.containerWagons = containerWagons;
        this.onJourney = onJourney;
        this.disabled = disabled;
    }

    //Constructor vacio
    public Train() {
        this.idTrain = "";
        this.name = "";
        this.milieage = 0;
        this.type = TrainType.NULL;
        this.passengerWagons = new Array<>(1);
        this.containerWagons = new Array<>(1);
        this.onJourney = false;
        this.disabled = false;
    }


}
