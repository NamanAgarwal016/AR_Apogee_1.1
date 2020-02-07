package com.bitspilani.apogeear.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitspilani.apogeear.Adapters.MoreAdapter;
import com.bitspilani.apogeear.Models.MoreModel;
import com.bitspilani.apogeear.R;

import java.util.ArrayList;
import java.util.List;

public class More extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    List<MoreModel> list;
    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public More() {
        // Required empty public constructor
    }

    public static More newInstance(String param1, String param2) {
        More fragment = new More();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more,container,false);

        recyclerView = view.findViewById(R.id.more_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        list.add(new MoreModel("Contact Us",R.drawable.next));
        list.add(new MoreModel("Developers",R.drawable.next));
        list.add(new MoreModel("Kind Store",R.drawable.next));
        list.add(new MoreModel("Map",R.drawable.next));
        list.add(new MoreModel("EPC Blog",R.drawable.next));
        list.add(new MoreModel("HPC Blog",R.drawable.next));
        list.add(new MoreModel("Sponsers",R.drawable.next));
        list.add(new MoreModel("About Us",R.drawable.next));
        list.add(new MoreModel("Privacy Policy",R.drawable.next));
        list.add(new MoreModel("Terms And Conditions",R.drawable.next));
        list.add(new MoreModel("Credits",R.drawable.next));

        MoreAdapter moreAdapter = new MoreAdapter(getActivity(),list);
        recyclerView.setAdapter(moreAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
