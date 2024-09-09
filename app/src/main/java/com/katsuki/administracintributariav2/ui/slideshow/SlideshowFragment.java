package com.katsuki.administracintributariav2.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.katsuki.administracintributariav2.adapter.EvidenciaAdapter;
import com.katsuki.administracintributariav2.databinding.FragmentSlideshowBinding;
import com.katsuki.administracintributariav2.model.Evidencia;

public class SlideshowFragment extends Fragment {

  private FragmentSlideshowBinding binding;

  // VARIABLES
  RecyclerView mRecycler;
  EvidenciaAdapter mAdapter;
  FirebaseFirestore mFirestore;
  FirebaseAuth mAuth;


  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    SlideshowViewModel slideshowViewModel =
            new ViewModelProvider(this).get(SlideshowViewModel.class);

    binding = FragmentSlideshowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    // INSTANCIAR LA BASE DE DATOS Y AUNTENTICACION
    mFirestore = FirebaseFirestore.getInstance();
    mAuth = FirebaseAuth.getInstance();

    mRecycler = binding.rcvTareas;
    mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

    // OBTENER EL ID DE USUSARIO LOGEADO PARA CARGAR LAS TAREAS SOLO PARA ESE USUSARIO
    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    Query query = mFirestore.collection("task").whereEqualTo("userId", userId);
    FirestoreRecyclerOptions<Evidencia> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Evidencia>().setQuery(query, Evidencia.class).build();

    mAdapter = new EvidenciaAdapter(firestoreRecyclerOptions);
    mAdapter.notifyDataSetChanged();
    mRecycler.setAdapter(mAdapter);

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  @Override
  public void onStart() {
    super.onStart();
    mAdapter.startListening();
  }

  @Override
  public void onStop() {
    super.onStop();
    mAdapter.stopListening();
  }
}