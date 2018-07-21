package labib.com.nudgememvp.ui.main;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.database.Cursor;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import labib.com.nudgememvp.R;
import labib.com.nudgememvp.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, Adapter.Callback {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.newNudgeFAB)
    FloatingActionButton newNudgeFAB;

    @Inject
    Adapter adapter;


    // wire the menu file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    // handle on menu item click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.clear:
                getPresenter().clearData();
                break;
            case R.id.settings:

                break;
            case R.id.activateService:
                break;
            case R.id.dectivateService:
                break;
            case R.id.showStatus:
                break;
        }
        return true;
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        getPresenter().queryInitialData();


        // handle recyclerView item swipe
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {



            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                ((Adapter.ViewHolder)viewHolder).
            }
        }
        ).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }

    public void test(View view) {
        getPresenter().insertData();
    }

    @Override
    public void initRecyclerView(Cursor cursor) {
        adapter.setCallBack(this);
        adapter.setDataCursor(cursor);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void updateRecyclerView(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onItemClicked(long id) {
        getPresenter().deleteOne(id);
    }

    @Override
    public void onEmptyClicked() {

        // button popout
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(newNudgeFAB, pvhX, pvhY);
        objectAnimator.setRepeatCount(3);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }



}
