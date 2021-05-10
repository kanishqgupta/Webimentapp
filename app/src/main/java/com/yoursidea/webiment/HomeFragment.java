package com.yoursidea.webiment;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.yoursidea.webiment.Prevalent.Prevalent;

import io.paperdb.Paper;


public class HomeFragment extends Fragment {
    Button logout;
    View myFragment;
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;
    RecyclerView all_ser_rv;

    public HomeFragment() {
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
        myFragment= inflater.inflate(R.layout.fragment_home, container, false);
        logout=myFragment.findViewById(R.id.admin_logout);
        recyclerView=myFragment.findViewById(R.id.rv_all_serv);
        firebaseFirestore=FirebaseFirestore.getInstance();
        String AdminPhone= Paper.book().read(Prevalent.AdminPhoneKey);
        collectionReference=firebaseFirestore.collection(AdminPhone);
        Paper.init(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PhoneLogin.class));
                Paper.book().destroy();
            }
        });
        return myFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Query query=collectionReference.orderBy("service",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Requests> options=new FirestoreRecyclerOptions.Builder<Requests>()
                .setQuery(query,Requests.class).build();
        FirestoreRecyclerAdapter<Requests,RequestViewHolder> adapter=new FirestoreRecyclerAdapter<Requests, RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RequestViewHolder holder, int position, @NonNull Requests model) {
                holder.service.setText(model.getService());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent detintent=new Intent(getActivity(),RequestDetails.class);
                        detintent.putExtra("service",model.getService());
                        startActivity(detintent);
                    }
                });
            }

            @NonNull
            @Override
            public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diabled_layout,parent,false);
                RequestViewHolder holder=new RequestViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        adapter.notifyDataSetChanged();
    }
}