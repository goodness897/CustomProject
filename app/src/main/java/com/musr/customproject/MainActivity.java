package com.musr.customproject;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    WheelNumberPicker wnpSample;

    CustomSwitch isSwitchOn;

    NumberPicker npUnit;

    private String[] unit = {"오전", "오후"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        npUnit = (NumberPicker) findViewById(R.id.np_unit);

        initNumberPicker();

        wnpSample = (WheelNumberPicker) findViewById(R.id.wnp_sample);

        wnpSample.setOnNumberSelectedListener(new WheelNumberPicker.OnNumberSelectedListener() {
            @Override
            public void onNumberSelected(WheelNumberPicker picker, int position, int number) {

            }

            @Override
            public void onNumberCurrentScrolled(WheelNumberPicker picker, int position, int number) {

            }

            @Override
            public void onNumberScrolledNewNumber(WheelNumberPicker picker) {

            }
        });

        isSwitchOn = (CustomSwitch) findViewById(R.id.sc_alarm);
        isSwitchOn.addSwitchObserver(new CustomSwitch.CustomSwitchObserver() {

            @Override
            public void onCheckStateChange(CustomSwitch switchView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "켜짐", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "꺼짐", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }

    private void initNumberPicker() {

        npUnit.setMinValue(0);
        npUnit.setMaxValue(unit.length - 1);
        npUnit.setDisplayedValues(unit);
        setDividerColor(npUnit);
        npUnit.setWrapSelectorWheel(false);

        npUnit.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                switch (newVal) {
                    case 0: // 0 선택
                        Toast.makeText(MainActivity.this, "0", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case 1: // 1 선택
                        Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT)
                                .show();
                        break;

                }
            }
        });
    }

    private void setDividerColor(NumberPicker picker) {
        Field[] numberPickerFields = NumberPicker.class.getDeclaredFields();
        for (Field field : numberPickerFields) {
            if (field.getName().equals("mSelectionDivider")) {
                field.setAccessible(true);
                try {
                    field.set(picker, getResources().getDrawable(R.drawable.number_picker_divider));
                } catch (IllegalArgumentException e) {
                    Log.v(TAG, "Illegal Argument Exception");
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    Log.v(TAG, "Resources NotFound");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    Log.v(TAG, "Illegal Access Exception");
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
