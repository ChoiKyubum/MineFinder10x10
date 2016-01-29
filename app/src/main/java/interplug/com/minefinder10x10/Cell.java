package interplug.com.minefinder10x10;

import android.widget.Button;

/**
 * Created by bbulbum on 16. 1. 29..
 */
public class Cell {
    public int x,y;
    public boolean isMine = false;
    public boolean isChecked = false;
    public Button button;

    public int checkEight(Cell[][] cells){
        isChecked = true;
        int count = 0;
        for(int i = x-1<0?0:x-1; i<= (x+1>9?9:x+1); i++){
            for(int j = y-1<0?0:y-1; j<=(y+1>9?9:y+1); j++){
                if(cells[i][j].isMine){
                    count ++;
                }
            }
        }
        if(count == 0){
            checkNeighbor(cells);
        }
        button.setText(""+count);
        return count;
    }

    public void checkNeighbor(Cell[][] cells){
        for(int i = x-1<0?0:x-1; i<= (x+1>9?9:x+1); i++){
            for(int j = y-1<0?0:y-1; j<=(y+1>9?9:y+1); j++){
                if(!cells[i][j].isChecked){
                    cells[i][j].checkEight(cells);
                }
            }
        }
    }
}
