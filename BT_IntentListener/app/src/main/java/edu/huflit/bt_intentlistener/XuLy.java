package edu.huflit.bt_intentlistener;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class XuLy implements View.OnClickListener {

    Context context;

    public XuLy(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Outner class", Toast.LENGTH_SHORT).show();
    }
}
