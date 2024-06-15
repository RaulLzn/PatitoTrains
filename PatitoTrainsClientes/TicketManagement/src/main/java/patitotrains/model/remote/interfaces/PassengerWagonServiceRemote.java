    package patitotrains.model.remote.interfaces;


    import raul.Model.array.Array;
    import raul.Model.util.list.List;
    import patitotrains.model.domain.PassengerWagon;

    import java.rmi.Remote;
    import java.rmi.RemoteException;

    public interface PassengerWagonServiceRemote extends Remote {
        List<PassengerWagon> getAllPassengerWagons() throws RemoteException;
        boolean savePassengerWagon(PassengerWagon passengerWagon) throws RemoteException;
        boolean updatePassengerWagon(PassengerWagon passengerWagon) throws RemoteException;
        boolean deletePassengerWagon(String id) throws RemoteException;
        PassengerWagon getPassengerWagonById(String id) throws RemoteException;
        List<PassengerWagon> getPassengerWagonsByIds(Array<String> ids) throws RemoteException;
    }
