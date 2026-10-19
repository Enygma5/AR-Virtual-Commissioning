import java.net.InetSocketAddress;

public class main {
    public static void main(String[] args){
        //String host = "192.168.1.13"; // Home IP
        String host = "192.168.250.204"; // Lab IP

        int port = 8080; // Port on which the WebSocket server will listen

        int delay = 50;

        SimpleServer server = SimpleServer.getInstance(new InetSocketAddress(host, port));

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            System.out.println("Start");

            /*try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/

            /*while (server.getLeftSensor(Constants.REAL1) != 1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("R1LeftSensor");
            server.stopConveyor(Constants.REAL1);
            server.moveRight(Constants.REAL1);
            System.out.println("R1MoveRight");
            while (server.getRightSensor(Constants.REAL1) != 1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("R1RightSensor");*/
            server.stopConveyor(Constants.VIRTUAL);
            System.out.println("VStopConveyor");
            server.moveRight(Constants.VIRTUAL);
            System.out.println("VMoveRight");
            server.createBoxLeft(Constants.VIRTUAL);
            System.out.println("VCreateBoxLeft");


            while (server.getLeftSensor(Constants.VIRTUAL) != 1) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("VLeftSensor");

            //server.stopConveyor(Constants.REAL1);
            //System.out.println("R1Stop");

            while (server.getRightSensor(Constants.VIRTUAL) != 1) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("VRightSensor");
            while (server.getRightSensor(Constants.VIRTUAL) != 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            server.deleteBox(Constants.VIRTUAL);
            System.out.println("VDeleteBox");

            /*server.stopConveyor(Constants.REAL2);
            server.moveRight(Constants.REAL2);
            System.out.println("R2MoveRight");
            while (server.getLeftSensor(Constants.REAL2) != 1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("R2LeftSensor");*/

            server.stopConveyor(Constants.VIRTUAL);
            System.out.println("VStop");

            /*while (server.getRightSensor(Constants.REAL2) != 1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("R2RightSensor");
            server.stopConveyor(Constants.REAL2);
            server.moveLeft(Constants.REAL2);
            System.out.println("R2MoveLeft");
            while (server.getLeftSensor(Constants.REAL2) != 1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("R2LeftSensor");*/

            server.stopConveyor(Constants.VIRTUAL);
            server.moveLeft(Constants.VIRTUAL);
            System.out.println("VMoveLeft");
            server.createBoxRight(Constants.VIRTUAL);
            System.out.println("VCreateBoxRight");

            while (server.getRightSensor(Constants.VIRTUAL) != 1) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("VRightSensor");
            //server.stopConveyor(Constants.REAL2);
            //System.out.println("R2Stop");

            while (server.getLeftSensor(Constants.VIRTUAL) != 1) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("VLeftSensor");
            while (server.getLeftSensor(Constants.VIRTUAL) != 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            server.deleteBox(Constants.VIRTUAL);
            System.out.println("VDeleteBox");

            /*server.stopConveyor(Constants.REAL1);
            server.moveLeft(Constants.REAL1);
            System.out.println("R1MoveLeft");
            while (server.getRightSensor(Constants.REAL1) != 1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("R1RightSensor");*/

            server.stopConveyor(Constants.VIRTUAL);
            System.out.println("VStop");
        }
    }
}
