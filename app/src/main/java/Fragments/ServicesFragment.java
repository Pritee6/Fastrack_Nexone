package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fastrack_nexon.R;
import com.example.fastrack_nexon.RecyclerViewAdapterServices;

import java.util.ArrayList;

public class ServicesFragment extends Fragment {

    private ArrayList<Integer> serviceImageUrls = new ArrayList<>();
    private ArrayList<String> serviceName = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_services, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Services");

        serviceImageUrls.add(R.drawable.consulting);
        serviceName.add("Consulting");

        serviceImageUrls.add(R.drawable.digitalmarketing);
        serviceName.add("Digital Marketing");

        serviceImageUrls.add(R.drawable.design);
        serviceName.add("Design");

        serviceImageUrls.add(R.drawable.development);
        serviceName.add("Development");

        serviceImageUrls.add(R.drawable.engineering);
        serviceName.add("Engineering");

        serviceImageUrls.add(R.drawable.scale);
        serviceName.add("Scale");

        RecyclerView recyclerViewServices = (RecyclerView) view.findViewById(R.id.recycler_view_services);
        RecyclerViewAdapterServices adapter = new RecyclerViewAdapterServices(getContext(), serviceImageUrls, serviceName);
        recyclerViewServices.setAdapter(adapter);
        recyclerViewServices.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}