package daniel.plewinski.apidealer.chucknorisjokes.logic.services.interfaces;

public interface ApiCommunicationInterface<Dto> {
    Dto getDataFromAnotherApi();
}
