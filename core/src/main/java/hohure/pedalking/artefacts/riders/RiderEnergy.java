package hohure.pedalking.artefacts.riders;

import hohure.pedalking.enums.RiderEffort;

public class RiderEnergy {

    float maxEndurance = 100;
    float endurance = 100;
    float maxSprintEndurance = 100;
    float sprintEndurance = 100;
    int coefSprintUp = 1;
    int coefSprintDown = 10;
    int coefEnduranceUp = 1;
    int coefEnduranceDown = 2;

    public void update(RiderEffort riderEffort, float delta){
        switch (riderEffort){
            case BRAKE -> {}
            case PEDAL -> enduranceDown(delta);
            case FREE_WHEEL -> enduranceUp(delta);
            case SPRINT -> sprintDown(delta);
        }
    }

    private void enduranceUp(float delta){
        if(endurance < 100){
            endurance += ( coefEnduranceUp * delta);
        }
    }

    private void enduranceDown(float delta){
        if(endurance > 10){
            endurance -= (coefEnduranceDown * delta);
        }
    }

    private void sprintDown(float delta){
        if(endurance > 10){
            endurance -= (coefEnduranceDown * delta);
            sprintEndurance -= (coefSprintDown * delta);
        }
    }

}
