package com.yoursidea.webiment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ServicesFragment extends Fragment {
    List<Home_all_services> all_serv_Slide;
    View myFragment;
    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment= inflater.inflate(R.layout.fragment_services, container, false);
        all_serv_Slide= new ArrayList<>();
        all_serv_Slide.add(new Home_all_services("Website Development",R.drawable.web_d));
        all_serv_Slide.add(new Home_all_services("App Development",R.drawable.app_d));
        all_serv_Slide.add(new Home_all_services("Graphic Development",R.drawable.graphic));
        all_serv_Slide.add(new Home_all_services("Digital Marketing",R.drawable.digital));
        all_serv_Slide.add(new Home_all_services("Biling Software",R.drawable.digi_b));
        all_serv_Slide.add(new Home_all_services("Digital Token Software",R.drawable.digi_t));
        all_serv_Slide.add(new Home_all_services("Restaurant Software",R.drawable.res_icon));
        all_serv_Slide.add(new Home_all_services("Customized Request",R.drawable.ic_service_foreground));
        RecyclerView all_ser_rv=myFragment.findViewById(R.id.rv_all_serv);
        Home_all_serv_adapter serv_adapter=new Home_all_serv_adapter(getActivity(),all_serv_Slide);
        all_ser_rv.setLayoutManager(new GridLayoutManager(getActivity(),3));
        all_ser_rv.setAdapter(serv_adapter);
        return myFragment;
    }
}