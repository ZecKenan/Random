// ZEC KENAN - CMEDT_2

package elevator;



public class Elevator {
    private int currentFloor;
    private boolean directionUp;
    private boolean[] floorSelected; //boolean array

    public Elevator(int numberOfFloors) {
        floorSelected = new boolean[numberOfFloors];
        floorSelected[0] = true; //start at floor
        directionUp = true;
    }

    public int getNoOfFloors() {
        return floorSelected.length; //antal el. i array
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void callToFloor(int floor) {
        if (floor >= 0 && floor < getNoOfFloors()) {
            floorSelected[floor] = true;
        }
    }

    public void moveToNextFloor() {
        int nextFloor;
        if (directionUp) { //
            nextFloor = searchDirectionUp();
            if (nextFloor < 0) { //ret. -1 om ingen våning i riktn.
                directionUp = true; //"ingen ovanför vald - direction up är false"
                nextFloor = searchDirectionDown();
            }
        }
        else {
            nextFloor = searchDirectionDown();
            if (nextFloor < 0) {
                directionUp = true; //ingen nedanför vald - nedåt är false
                nextFloor = searchDirectionDown();
            }
        }

        if (nextFloor >= 0) {
            floorSelected[currentFloor] = false; //tar bort
            currentFloor = nextFloor;
        }
    }
    @Override
    public String toString() {
        boolean empty = true; //alla true blir element
        String string = "På våning: " + currentFloor + ", påväg mot vn.: ";
        for (int i = 0; i < getNoOfFloors(); i++) {
            if (floorSelected[i] && i != currentFloor) {
                string +="-> "+ i;
                empty = false;
            }
        }
        if (empty) {
            string += "Slutstation";
        }

        return string;
    }

    private int searchDirectionUp() {
        for (int i = currentFloor+1; i < getNoOfFloors(); i++) {
            if (floorSelected[i] == true) {
                return i;
            }
        }
        return -1;
    }

    private int searchDirectionDown() {
        for (int i = currentFloor-1; i >= 0; i--) {
            if (floorSelected[i] == true) {
                return i;
            }
        }
        return -1;
    }
}
