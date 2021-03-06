package ca.ulaval.glo4002.atm_uat;


import ca.ulaval.glo4002.atm_api.AtmServer;
import cucumber.api.java.Before;

public class JettyStarterHook {
    public static final int JETTY_TEST_PORT = 15146;
    
    private static boolean isFirstFeature = true;
    private AtmServer server;
 
    @Before
    public void beforeAll() throws Exception {
        if (isFirstFeature) {
            Runtime.getRuntime().addShutdownHook(new JettyServerShutdown());
            startJettyServer();
            isFirstFeature = false;
        }
    }
 
    private void startJettyServer() throws Exception {
        server = new AtmServer();
        server.start(JETTY_TEST_PORT, new UatContext());
    }
 
    private class JettyServerShutdown extends Thread {
        public void run() {
            try {
                server.stop();
            } catch (Exception e) {
                // Nothing do to anyways
            }
        }
    }
}
