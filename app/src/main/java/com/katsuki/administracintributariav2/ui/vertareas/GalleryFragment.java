package com.katsuki.administracintributariav2.ui.vertareas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.katsuki.administracintributariav2.AgregarTarea;
import com.katsuki.administracintributariav2.CrearTarea;
import com.katsuki.administracintributariav2.adapter.TaskAdapter;
import com.katsuki.administracintributariav2.databinding.FragmentGalleryBinding;
import com.katsuki.administracintributariav2.model.Task;

public class GalleryFragment extends Fragment {

  private FragmentGalleryBinding binding;


  // VARIABLES
  Button btnAgregar, btnFlotante;
  RecyclerView mRecycler;
  TaskAdapter mAdapter;
  FirebaseFirestore mFirestore;

  FirebaseAuth mAuth;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    GalleryViewModel galleryViewModel =
            new ViewModelProvider(this).get(GalleryViewModel.class);

    binding = FragmentGalleryBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    mFirestore = FirebaseFirestore.getInstance();
    mAuth = FirebaseAuth.getInstance();
    mRecycler = binding.rcvTareas;
    mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

    // OBTENER EL ID DE USUSARIO LOGEADO PARA CARGAR LAS TAREAS SOLO PARA ESE USUSARIO
    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    Query query = mFirestore.collection("task").whereEqualTo("userId", userId);
    FirestoreRecyclerOptions<Task> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Task>().setQuery(query, Task.class).build();

    mAdapter = new TaskAdapter(firestoreRecyclerOptions);
    mAdapter.notifyDataSetChanged();
    mRecycler.setAdapter(mAdapter);

    // DARLE CLICK AL AGREGAR TAREA
//    btnAgregar = binding.btnAddTask;
    btnFlotante = binding.btnAgregarFlotante;

//    btnAgregar.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        startActivity(new Intent(getContext(), CrearTarea.class));
//      }
//    });

    btnFlotante.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AgregarTarea fmTarea = new AgregarTarea();
        fmTarea.show(getActivity().getSupportFragmentManager(), "Navegar a fragment");
      }
    });

    return root;
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

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}