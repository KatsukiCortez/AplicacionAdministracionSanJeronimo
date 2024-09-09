package com.katsuki.administracintributariav2.ui.solicitudes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.katsuki.administracintributariav2.adapter.SolicitudAdapter;
import com.katsuki.administracintributariav2.databinding.FragmentSolicitudesBinding;
import com.katsuki.administracintributariav2.model.Solicitud;

public class SolicitudesFragment extends Fragment {

  private FragmentSolicitudesBinding binding;

  // VARIABLES
  SolicitudAdapter mAdapter;
  FirebaseFirestore mFirestore;
  RecyclerView mRecycler;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    SolicitudesViewModel solicitudesViewModel =
            new ViewModelProvider(this).get(SolicitudesViewModel.class);

    binding = FragmentSolicitudesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    mFirestore = FirebaseFirestore.getInstance();
    mRecycler = binding.rcvSolicitudes;
    mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

    Query query = mFirestore.collection("solicitudes");

    FirestoreRecyclerOptions<Solicitud> firestoreRecyclerOptions =
            new FirestoreRecyclerOptions.Builder<Solicitud>()
                    .setQuery(query, Solicitud.class)
                    .setLifecycleOwner(this)
                    .build();

    mAdapter = new SolicitudAdapter(firestoreRecyclerOptions);
    mRecycler.setAdapter(mAdapter);
    mAdapter.startListening();

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
