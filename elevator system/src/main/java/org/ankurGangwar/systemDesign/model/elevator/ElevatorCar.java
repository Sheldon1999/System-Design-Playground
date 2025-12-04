package org.ankurGangwar.systemDesign.model.elevator;

public class ElevatorCar {
    int id;
    int currentFloor;
    int nextFloorStoppage;
    ElevatorDirection movingDirection;
    Door door;

    public ElevatorCar(int id) {
        this.id = id;
        currentFloor = 0;
        movingDirection = ElevatorDirection.IDLE;
        door = new Door();
    }

    public void showDisplay(){
        System.out.println("elevator: "+ id + ", Current floor: " + currentFloor + ", going: " + movingDirection);
    }

    public void moveElevator(int destinationFloor){
        this.nextFloorStoppage = destinationFloor;
        if (this.currentFloor == nextFloorStoppage){
            door.
        }
    }
}
