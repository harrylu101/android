
package com.harry.tools;



import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * An activity that demonstrated how to get height of status bar, height of
 * title bar, and height of content view .
 * 
 * @author harry
 */
public class DisplayInfo extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button showInfoButton = (Button)super.findViewById(R.id.showInfo);

        
        showInfoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Resources res = getResources();

                // get DisplayMetrics of this device
                DisplayMetrics dm = res.getDisplayMetrics();
                

                // get visible area Rectangle. Status bar height is excluded.
                Rect frame = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                final int statusBarHeight = frame.top;

                // get ContentView. status bar height and title bar is excluded.
                View contentView = getWindow().findViewById(Window.ID_ANDROID_CONTENT);
                final int contentViewTop = contentView.getTop();
                final int contentViewHeight = contentView.getHeight();

                // String that will be printed out to users.
                String toastText = "density : " + dm.density + "\n" 
                        + "densityDpi : " + dm.densityDpi + "\n" 
                        + "heightPixel : " + dm.heightPixels + "\n"
                        + "widthPixel : " + dm.widthPixels + "\n" 
                        + "xdpi : " + dm.xdpi + "\n"
                        + "ydpi : " + dm.ydpi + "\n" 
                        +"density_default : " + dm.DENSITY_DEFAULT+ "\n"
                        + "density_density_high : " + dm.DENSITY_HIGH + "\n"
                        + "density_density_low : " + dm.DENSITY_LOW + "\n" 
                        + "density_medium : "+ dm.DENSITY_MEDIUM + "\n" 
                        + "status bar : " + statusBarHeight + "\n"
                        + "title bar :" + (contentViewTop - statusBarHeight) + "\n"
                        + "content view height :" + contentViewHeight;

                TextView info = (TextView)findViewById(R.id.info);
                info.setText(toastText);

                v.setVisibility(View.INVISIBLE);
            }
        });

    }

}
