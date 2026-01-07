package controller;

import view.MainFrame;

public class AppController {

    private final MainFrame mainFrame;

    public AppController() {
        this.mainFrame = new MainFrame(this);
    }

    public void start() {
        mainFrame.setVisible(true);
    }

    // Phase C/D: later we’ll add methods like:
    // loadAllData()
    // addPatient(...)
    // addPrescription(...)
    // createReferral(...)
}
