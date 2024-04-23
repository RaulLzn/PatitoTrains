package Main;

import patitotrains.model.domain.Station;
import patitotrains.model.repository.StationRepository;



public class StationPrueba {
    public static void main(String[] args) {
        StationRepository stationRepository = new StationRepository();

        Station a = new Station("001", "A");
        Station b = new Station("002", "B");
        Station c = new Station("003", "C");
        Station d = new Station("004", "D");
        Station e = new Station("005", "E");
        Station f = new Station("006", "F");
        Station g = new Station("007", "G");
        Station h = new Station("008", "H");
        Station i = new Station("009", "I");
        Station j = new Station("010", "J");
        Station k = new Station("011", "K");

        stationRepository.saveStation(a);
        stationRepository.saveStation(b);
        stationRepository.saveStation(c);
        stationRepository.saveStation(d);
        stationRepository.saveStation(e);
        stationRepository.saveStation(f);
        stationRepository.saveStation(g);
        stationRepository.saveStation(h);
        stationRepository.saveStation(i);
        stationRepository.saveStation(j);
        stationRepository.saveStation(k);

    }
}
