package interplug.com.minefinder10x10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Cell[][] cells = new Cell[10][10];
    private LinearLayout mineMap;
    private int mineCount = 10;
    private Random random = new Random();
    private int[] minePosition = new int[mineCount];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mineMap = (LinearLayout) findViewById(R.id.mine_map);

        for(int i = 0; i<mineCount; i++){
            minePosition[i] = random.nextInt(100);
            for(int j=0; j<i; j++){
                if(minePosition[i] == minePosition[j])
                    i--;
            }
        }

        for(int i = 0; i<mineMap.getChildCount(); i++){
            LinearLayout row = (LinearLayout)mineMap.getChildAt(i);
            for(int j = 0; j<row.getChildCount() ; j++){
                Button cellBtn = (Button)row.getChildAt(j);
                Cell cell = new Cell();
                cell.x = i;
                cell.y = j;
                for(int position : minePosition){
                    if(10*i+j+1 == position){
                        cell.isMine = true;
                        mineCount--;
                    }
                }
                cells[i][j] = cell;
                cellBtn.setTag(cell);
                cellBtn.setOnClickListener(this);
                cell.button = cellBtn;
            }
        }
    }

    @Override
    public void onClick(View v) {
        Cell cell = (Cell)v.getTag();
        if(!cell.isChecked){
            if(cell.isMine){
                ((Button)v).setText("M");
            }else{
                cell.checkEight(cells);
            }
        }
    }
}
