package patitotrains.model.remote.services.servicesRemote;

import patitotrains.model.remote.interfaces.ContainerWagonServiceRemote;
import patitotrains.model.remote.interfaces.PassengerWagonServiceRemote;
import patitotrains.model.remote.interfaces.RouteServiceRemote;
import patitotrains.model.remote.interfaces.UserServiceRemote;
import patitotrains.model.remote.interfaces.TrainServiceRemote;

public interface AdminServiceRemote extends ContainerWagonServiceRemote, PassengerWagonServiceRemote, RouteServiceRemote, UserServiceRemote, TrainServiceRemote {

}
