package hohure.pedalking.artefacts.race.matrice;

import java.util.ArrayList;
import java.util.List;

import static hohure.pedalking.utils.constant.ScreenConstant.RIDER_HEIGHT;
import static hohure.pedalking.utils.constant.ScreenConstant.RIDER_WIDTH;

public class ZoneMatrice {

    float nbColumn;
    float nbLine;

    List<Cell> cells = new ArrayList<>();

    public ZoneMatrice(float width, float height, float leftx, float bottomy){
        nbColumn = width / RIDER_WIDTH;
        nbLine = height / RIDER_HEIGHT;
        var numColumn = 1;
        var numLine = 1;
        while(nbLine > numLine){
            while(nbColumn > numColumn){
                cells.add(new Cell(numLine, true, numColumn, leftx + (numColumn * RIDER_WIDTH), bottomy + numLine * RIDER_HEIGHT));
                numColumn ++;
            }
            numColumn = 1;
            numLine++;
        }
    }

    public Cell getFirstEmptyCell(){
        return cells.getFirst();
    }

    public Cell getNextCell(Cell precedent){
        var numColumn = precedent.getNumColonne() + 1;
        var numLine = precedent.getNumLigne();
        if(numColumn >= nbColumn){
            numColumn = 1;
            numLine ++;
        }
        return getCellByMatriceCoord(numColumn, numLine);
    }

    private Cell getCellByMatriceCoord(int numColumn, int numLigne){
       return cells.stream()
           .filter(c -> c.getNumColonne() == numColumn && c.getNumLigne() == numLigne)
           .findAny().orElseThrow();
    }




}
