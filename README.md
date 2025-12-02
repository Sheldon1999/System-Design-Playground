# System Design Playground

Curated implementations of popular low-level system design problems. Each project lives in its own folder with source, notes, and a runnable demo.

## Available implementations
- `parking lot/` – Java CLI that simulates a parking lot with spot allocation strategies and pluggable pricing (hourly/minutely plus VIP surcharges).

## How to navigate
1. Enter a project folder (e.g., `cd "parking lot"`).
2. Read its `README.md` for design notes and usage.
3. Run the demo or tests as documented in that project.

## Quickstart (parking lot example)
```bash
cd "parking lot"
mvn -q -DskipTests package
java -cp target/parkingLot-1.0-SNAPSHOT.jar org.ankurGangwar.systemDesign.Main
```

More design exercises will be added over time; feel free to extend existing ones with new features or alternative approaches.
