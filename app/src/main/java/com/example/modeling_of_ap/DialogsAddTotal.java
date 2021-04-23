package com.example.modeling_of_ap;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.modeling_of_ap.DB.DataThreads.DataThread;
import com.example.modeling_of_ap.DB.DataThreads.DataThreadGet;
import com.example.modeling_of_ap.DB.DataThreads.DataThreadUpdate;
import com.example.modeling_of_ap.DB.Foods;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.modeling_of_ap.DBTotal.DataThreads.TotalDataThreadGet;
import com.example.modeling_of_ap.DBTotal.Eated;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.USB_SERVICE;

public class DialogsAddTotal extends DialogFragment {
    Eated eated;
    static DialogsAddTotal newInstance(){
        return new DialogsAddTotal();
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogsAddDataTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogs_add_total, container, false);
        Bundle bundle = this.getArguments();
        TotalDataThreadGet threadGet = new TotalDataThreadGet();
        String[] items = bundle.getString("foodKey").split(",");
        eated = threadGet.getEated(items[0], items[1], Double.parseDouble(items[2]));
        TextView textView1 = view.findViewById(R.id.weight);
        TextView textView2 = view.findViewById(R.id.calor);
        TextView textView3 = view.findViewById(R.id.prt);
        TextView textView4 = view.findViewById(R.id.fts);
        TextView textView5 = view.findViewById(R.id.crbhdrts);
        TextView textView6 = view.findViewById(R.id.sgs);
        textView1.setText(String.valueOf(eated.getWeight()));
        textView2.setText(String.valueOf(eated.getCalories()));
        textView3.setText(String.valueOf(eated.getProteins()));
        textView4.setText(String.valueOf(eated.getFats()));
        textView5.setText(String.valueOf(eated.getCarbohydrates()));
        textView6.setText(String.valueOf(eated.getSugars()));
        return view;
    }
}

