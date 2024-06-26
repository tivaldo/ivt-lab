package hu.bme.mit.spaceship;

/**
* A simple spaceship with two proton torpedo stores and four lasers
*/
public class GT4500 implements SpaceShip {

  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  private boolean wasPrimaryFiredLast = false;
  private boolean failedFire = false;

  public GT4500(TorpedoStore first, TorpedoStore second) {
    //this.primaryTorpedoStore = new TorpedoStore(10);
    //this.secondaryTorpedoStore = new TorpedoStore(10);
    this.primaryTorpedoStore = first;
    this.secondaryTorpedoStore = second;
  }

  public boolean fireLaser(FiringMode firingMode) {
    // TODO not implemented yet
    return false;
  }

  /**
  * Tries to fire the torpedo stores of the ship.
  *
  * @param firingMode how many torpedo bays to fire
  * 	SINGLE: fires only one of the bays.
  * 			- For the first time the primary store is fired.
  * 			- To give some cooling time to the torpedo stores, torpedo stores are fired alternating.
  * 			- But if the store next in line is empty, the ship tries to fire the other store.
  * 			- If the fired store reports a failure, the ship does not try to fire the other one.
  * 	ALL:	tries to fire both of the torpedo stores.
  *
  * @return whether at least one torpedo was fired successfully
  */
  @Override
  public boolean fireTorpedo(FiringMode firingMode) {

    boolean firingSuccess = false;

    switch (firingMode) {
      case SINGLE:
        if (wasPrimaryFiredLast) {
          if(!failedFire){
            // try to fire the secondary first
            if (! secondaryTorpedoStore.isEmpty()) {
              firingSuccess = secondaryTorpedoStore.fire(1);
              wasPrimaryFiredLast = false;
              failedFire = !firingSuccess;
            }
            else {
              // although primary was fired last time, but the secondary is empty
              // thus try to fire primary again
              if (! primaryTorpedoStore.isEmpty()) {
                firingSuccess = primaryTorpedoStore.fire(1);
                wasPrimaryFiredLast = true;
                //failedFire = !firingSuccess;
              }

              // if both of the stores are empty, nothing can be done, return failure
            }
          }
        }
        else {
          if(!failedFire){
            // try to fire the primary first
            if (! primaryTorpedoStore.isEmpty()) {
              firingSuccess = primaryTorpedoStore.fire(1);
              wasPrimaryFiredLast = true;
              failedFire = !firingSuccess;
            }
            else {
              // although secondary was fired last time, but primary is empty
              // thus try to fire secondary again
              if (! secondaryTorpedoStore.isEmpty()) {
                firingSuccess = secondaryTorpedoStore.fire(1);
                wasPrimaryFiredLast = false;
                //failedFire = !firingSuccess;
              }

              // if both of the stores are empty, nothing can be done, return failure
            }
          }
        }
        break;

      case ALL:
        // try to fire both of the torpedo stores
        if (! primaryTorpedoStore.isEmpty()) {
              firingSuccess = primaryTorpedoStore.fire(1);
            }
        if (! secondaryTorpedoStore.isEmpty() && firingSuccess) {
              firingSuccess = secondaryTorpedoStore.fire(1);
            }
        break;
    }

    return firingSuccess;
  }

}
