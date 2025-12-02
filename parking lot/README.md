# Parking Lot

Interactive Java CLI that simulates a multi-vehicle parking lot with pluggable spot allocation and pricing strategies (hourly/minutely plus VIP surcharges for near-exit/elevator spots).

## Project layout
- `src/main/java/org/ankurGangwar/systemDesign/Main.java` – terminal menu loop and system bootstrap.
- `parkingSpot/*` – spot types, managers, and allocation strategies (default/near-exit/near-elevator).
- `gate/*` – entry/exit flow, ticketing, and payment receipts.
- `pricingStrategy/*` – hourly/minutely billing plus decorators for VIP location fees.

## Requirements
- JDK 17+
- Maven 3.9+

## Build & run
```bash
mvn -q -DskipTests package
java -cp target/parkingLot-1.0-SNAPSHOT.jar org.ankurGangwar.systemDesign.Main
```

## Usage
1. Launch the app to see the menu.
2. Choose **Vehicle Entry** to enter a vehicle number, vehicle type (2/4 wheeler), and a spot preference (standard, near exit, near elevator). A ticket ID and spot ID are issued.
3. Choose **Vehicle Exit** to enter the ticket ID and pick billing (hourly rounded up or exact minutes). A receipt is printed and the spot is freed.
4. **View System Status** lists active tickets for quick debugging.

## Notes & customization
- Default inventory: 10 bike spots (IDs 1–10) and 10 car spots (IDs 101–110) with mixed near-exit/elevator tags.
- Pricing defaults are set inside `Main.initializeSystem` via `setRate` and can be tweaked per time unit.
- VIP surcharges are applied through pricing decorators based on spot location features.
