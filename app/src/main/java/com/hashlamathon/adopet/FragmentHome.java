package com.hashlamathon.adopet;

//import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hashlamathon.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class FragmentHome extends Fragment {

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;

//    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;
    SwipeFlingAdapterView flingContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = null;
        try {
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
        }catch (Exception e) {
            Log.d("ERROR", "onCreateView: ");
        }

//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText("test 123");


        try {
            flingContainer = (SwipeFlingAdapterView) rootView.findViewById(R.id.frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ButterKnife.inject(getActivity());


        try {
            al = new ArrayList<>();
            al.add("php");
            al.add("c");
            al.add("python");
            al.add("java");
            al.add("html");
            al.add("c++");
            al.add("css");
            al.add("javascript");

            arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.item, R.id.helloText, al );


            flingContainer.setAdapter(arrayAdapter);
            flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
                @Override
                public void removeFirstObjectInAdapter() {
                    // this is the simplest way to delete an object from the Adapter (/AdapterView)
                    Log.d("LIST", "removed object!");
                    al.remove(0);
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onLeftCardExit(Object dataObject) {
                    //Do something on the left!
                    //You also have access to the original object.
                    //If you want to use it just cast it (String) dataObject
                    makeToast(getActivity(), "Left!");
                }

                @Override
                public void onRightCardExit(Object dataObject) {
                    makeToast(getActivity(), "Right!");
                }

                @Override
                public void onAdapterAboutToEmpty(int itemsInAdapter) {
                    // Ask for more data here
                    al.add("XML ".concat(String.valueOf(i)));
                    arrayAdapter.notifyDataSetChanged();
                    Log.d("LIST", "notified");
                    i++;
                }

                @Override
                public void onScroll(float scrollProgressPercent) {
                    View view = flingContainer.getSelectedView();
                    view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                    view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                }
            });


            // Optionally add an OnItemClickListener
            flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
                @Override
                public void onItemClicked(int itemPosition, Object dataObject) {
                    makeToast(getActivity(), "Clicked!");
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.right)
    public void right() {
        /**
         * Trigger the right event manually.
         */
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.left)
    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }
}
