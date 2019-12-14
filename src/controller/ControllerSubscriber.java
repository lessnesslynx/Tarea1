package controller;



public interface ControllerSubscriber {

    /** Registers an observer
     *
     * @param newObserver Observer to be registered
     */
    void register(Observer newObserver);

    /** Unregisters an observer
     *
     * @param observer Observer to be unregistered
     */
    void unregister(Observer observer);

}
