package patitotrains.model.repository.entity;

import patitotrains.model.domain.Train;
import patitotrains.model.domain.types.TrainType;

public class TrainEntity {
    private String id;
    private String name;
    private double mileage;
    private TrainType type;
    private String[] passengerWagonIds; // Mantenemos el tipo como String[]
    private String[] luggageWagonIds; // Mantenemos el tipo como String[]
    private boolean onJourney;
    private boolean disabled;

    public TrainEntity(String id, String name, double mileage, TrainType type,
                       String[] passengerWagonIds, String[] luggageWagonIds,
                       boolean onJourney, boolean disabled) {
        this.id = id;
        this.name = name;
        this.mileage = mileage;
        this.type = type;
        this.passengerWagonIds = passengerWagonIds;
        this.luggageWagonIds = luggageWagonIds;
        this.onJourney = onJourney;
        this.disabled = disabled;
    }

    public TrainEntity(Train train) {
        this.id = train.getId();
        this.name = train.getName();
        this.mileage = train.getMileage();
        this.type = train.getType();
        this.passengerWagonIds = new String[train.getPassengerWagons().size()];
        for (int i = 0; i < train.getPassengerWagons().size(); i++) {
            this.passengerWagonIds[i] = train.getPassengerWagons().get(i).getId(); // Suponiendo que el método getId() devuelve el ID como String
        }
        this.luggageWagonIds = new String[train.getLuggageWagons().size()];
        for (int i = 0; i < train.getLuggageWagons().size(); i++) {
            this.luggageWagonIds[i] = train.getLuggageWagons().get(i).getId(); // Suponiendo que el método getId() devuelve el ID como String
        }
        this.onJourney = train.isOnJourney();
        this.disabled = train.isDisabled();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public TrainType getType() {
        return type;
    }

    public void setType(TrainType type) {
        this.type = type;
    }

    public String[] getPassengerWagonIds() {
        return passengerWagonIds;
    }

    public void setPassengerWagonIds(String[] passengerWagonIds) {
        this.passengerWagonIds = passengerWagonIds;
    }

    public String[] getLuggageWagonIds() {
        return luggageWagonIds;
    }

    public void setLuggageWagonIds(String[] luggageWagonIds) {
        this.luggageWagonIds = luggageWagonIds;
    }

    public boolean isOnJourney() {
        return onJourney;
    }

    public void setOnJourney(boolean onJourney) {
        this.onJourney = onJourney;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrainEntity{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", mileage=").append(mileage);
        sb.append(", type=").append(type);
        sb.append(", passengerWagonIds=[");
        if (passengerWagonIds.length > 0) {
            sb.append(passengerWagonIds[0]);
            for (int i = 1; i < passengerWagonIds.length; i++) {
                sb.append(", ").append(passengerWagonIds[i]);
            }
        }
        sb.append("]");
        sb.append(", luggageWagonIds=[");
        if (luggageWagonIds.length > 0) {
            sb.append(luggageWagonIds[0]);
            for (int i = 1; i < luggageWagonIds.length; i++) {
                sb.append(", ").append(luggageWagonIds[i]);
            }
        }
        sb.append("]");
        sb.append(", onJourney=").append(onJourney);
        sb.append(", disabled=").append(disabled);
        sb.append('}');
        return sb.toString();
    }

}
