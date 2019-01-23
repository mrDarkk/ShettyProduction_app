package com.bhupendra.production123;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bhupendrabanothe on 29/03/18.
 */

class MyHolder {



    TextView name, email, phone, msg;

    public MyHolder(View itemView) {


        name= (TextView) itemView.findViewById(R.id.name);
        email= (TextView) itemView.findViewById(R.id.email);
        phone= (TextView) itemView.findViewById(R.id.phone);
        msg= (TextView) itemView.findViewById(R.id.msg);



    }
}
