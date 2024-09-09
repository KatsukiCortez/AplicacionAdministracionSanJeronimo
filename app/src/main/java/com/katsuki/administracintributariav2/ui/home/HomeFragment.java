package com.katsuki.administracintributariav2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.katsuki.administracintributariav2.adapter.ContribuyenteAdapter;
import com.katsuki.administracintributariav2.adapter.TaskAdapter;
import com.katsuki.administracintributariav2.databinding.FragmentHomeBinding;
import com.katsuki.administracintributariav2.model.Contribuyente;

public class HomeFragment extends Fragment {

  private FragmentHomeBinding binding;

  // VARIABLES
  Button buscar;
  RecyclerView mRecycler;
  ContribuyenteAdapter mAdapter;
  FirebaseFirestore mFirestore;

  EditText BuscarDoc;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    HomeViewModel homeViewModel =
            new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    mFirestore = FirebaseFirestore.getInstance();
    mRecycler = binding.rcvContribuyente;
    mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

    BuscarDoc = binding.txtDocumento;
    buscar = binding.btnBuscarContribuyente;

    buscar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String documento = BuscarDoc.getText().toString().trim();

        if (documento.isEmpty()) {
          Toast.makeText(getContext(), "El documento está vacío", Toast.LENGTH_SHORT).show();
        } else {
          Query query = mFirestore.collection("contribuyente")
                  .whereEqualTo("documento", documento);

          FirestoreRecyclerOptions<Contribuyente> firestoreRecyclerOptions =
                  new FirestoreRecyclerOptions.Builder<Contribuyente>()
                          .setQuery(query, Contribuyente.class)
                          .setLifecycleOwner(HomeFragment.this)
                          .build();

          // Crear un nuevo adaptador con las opciones actualizadas
          mAdapter = new ContribuyenteAdapter(firestoreRecyclerOptions);
          mRecycler.setAdapter(mAdapter);
          mAdapter.startListening();  // Asegurarse de que el adaptador esté escuchando los cambios

          BuscarDoc.setText("");
        }
      }
    });

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
