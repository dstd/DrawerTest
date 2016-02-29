package dstd.test.drawertest;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.crossfader.Crossfader;
import com.mikepenz.crossfader.view.CrossFadeSlidingPaneLayout;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(android.R.drawable.ic_dialog_dialer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        onCreateDrawer(toolbar, savedInstanceState);
        onCreateMiniDrawer(toolbar, savedInstanceState);
    }

    private void onCreateMiniDrawer(Toolbar toolbar, Bundle savedInstanceState) {
        final Resources resources = getResources();
        boolean useMiniDrawer = true;

        final AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(android.R.color.holo_red_light)
                .withTranslucentStatusBar(false)
                .withSavedInstance(savedInstanceState)
                .build();

        final Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(false)
                .withAccountHeader(accountHeader)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(new PrimaryDrawerItem().withName("Item1")).withGenerateMiniDrawer(true).buildView();
        final MiniDrawer miniDrawer = drawer.getMiniDrawer();

        final Crossfader crossfader = new Crossfader<CrossFadeSlidingPaneLayout>()
                .withContent(findViewById(R.id.main_root))
                .withFirst(drawer.getSlider(), (int) resources.getDimension(R.dimen.mini_drawer_expanded_width))
                .withSecond(miniDrawer.build(this), (int) resources.getDimension(R.dimen.mini_drawer_collapsed_width))
                .withSavedInstance(savedInstanceState)
                .build();

        miniDrawer.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                crossfader.crossFade();
            }

            @Override
            public boolean isCrossfaded() {
                return crossfader.isCrossFaded();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crossfader.crossFade();
            }
        });
    }

    private void onCreateDrawer(Toolbar toolbar, Bundle savedInstanceState) {
        final Resources resources = getResources();
        boolean useMiniDrawer = true;

        final AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(android.R.color.holo_red_light)
                .withTranslucentStatusBar(true)
                .withSavedInstance(savedInstanceState)
                .build();

        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withAccountHeader(accountHeader)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(new PrimaryDrawerItem().withName("Item1"))
                .build();
    }
}
