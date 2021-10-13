package by.epamtc.enrollmentsystem.services;


public class ServicesFactory {
    private static volatile ServicesFactory instance;

    public static ServicesFactory getInstance() {
        ServicesFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (ServicesFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ServicesFactory();
                }
            }
        }
        return localInstance;
    }
}
