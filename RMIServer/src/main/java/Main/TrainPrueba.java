package Main;

import patitotrains.model.domain.ContainerWagon;
import patitotrains.model.domain.PassengerWagon;
import patitotrains.model.domain.Train;
import patitotrains.model.domain.types.TrainType;
import patitotrains.model.repository.ContainerWagonRepository;
import patitotrains.model.repository.PassengerWagonRepository;
import patitotrains.model.repository.TrainRepository;

import raul.Model.array.Array;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;



public class TrainPrueba {
    public static void main(String[] args) {
        // Crear instancias de los repositorios
        TrainRepository trainRepository = new TrainRepository();
        PassengerWagonRepository passengerWagonRepository = new PassengerWagonRepository();
        ContainerWagonRepository containerWagonRepository = new ContainerWagonRepository();

        // Crear un nuevo tren
        Train newTrain = new Train("789012", "Expresso", 1000, new TrainType("210", "big", 20),
                new Array<>(0), new Array<>(0), false, false);

        // Guardar el tren
        boolean saved = trainRepository.saveTrain(newTrain);
        if (saved) {
            System.out.println("New train added successfully!");
        } else {
            System.out.println("Failed to add new train.");
        }

        // Añadir vagones al tren
        PassengerWagon passengerWagon1 = new PassengerWagon("P001", true);
        PassengerWagon passengerWagon2 = new PassengerWagon("P002", false);
        ContainerWagon containerWagon1 = new ContainerWagon("C001");
        ContainerWagon containerWagon2 = new ContainerWagon("C002");

        // Guardar los vagones
        boolean passengerWagon1Added = passengerWagonRepository.savePassengerWagon(passengerWagon1);
        boolean passengerWagon2Added = passengerWagonRepository.savePassengerWagon(passengerWagon2);
        boolean containerWagon1Added = containerWagonRepository.saveContainerWagon(containerWagon1);
        boolean containerWagon2Added = containerWagonRepository.saveContainerWagon(containerWagon2);

        // Añadir vagones al tren
        trainRepository.addPassengerWagonToTrain("789012", passengerWagon1);
        trainRepository.addPassengerWagonToTrain("789012", passengerWagon2);
        trainRepository.addContainerWagonToTrain("789012", containerWagon1);
        trainRepository.addContainerWagonToTrain("789012", containerWagon2);

        // Obtener todos los trenes y mostrar su información
        List<Train> allTrains = trainRepository.getAllTrains();
        System.out.println("All Trains:");
        Iterator<Train> iterator = allTrains.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Obtener un tren por su ID
        String trainId = "789012";
        Train foundTrain = trainRepository.getTrainById(trainId);
        if (foundTrain != null) {
            System.out.println("Train found by ID:");
            System.out.println(foundTrain);
        } else {
            System.out.println("Train not found by ID.");
        }
    }

    //devolver un objeto tipo Train
    public Train getTrain(){
        TrainRepository trainRepository = new TrainRepository();
        return trainRepository.getTrainById("789012");
    }
}
