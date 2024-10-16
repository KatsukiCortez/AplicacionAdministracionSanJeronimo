package com.katsuki.administracintributariav2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.katsuki.administracintributariav2.AgregarTarea;
import com.katsuki.administracintributariav2.R;
import com.katsuki.administracintributariav2.model.Task;

public class TaskAdapter extends FirestoreRecyclerAdapter<Task, TaskAdapter.ViewHolder> {

  public TaskAdapter(@NonNull FirestoreRecyclerOptions<Task> options) {
    super(options);
  }

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Task Task) {
    holder.tarea.setText(Task.getTarea());
    holder.ubicacion.setText(Task.getUbicacion());
    holder.descripcion.setText(Task.getDescripcion());

    // LISTENER PARA EDITAR LA TAREA SELECCIONADA
    holder.editarTarea.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Abrir el fragmento o diálogo pasando los datos de la tarea
        dialogoEditarTarea(v, Task, position);
      }
    });

  }

  private void dialogoEditarTarea(View v, Task task, int position) {
    // Obtener el taskId desde Firestore usando la posición
    String taskId = getSnapshots().getSnapshot(position).getId();

    // Obtener los datos de la tarea
    String taskName = task.getTarea();
    String taskLocation = task.getUbicacion();
    String taskDescription = task.getDescripcion();

    // Crear un diálogo o fragmento y pasarle los datos de la tarea
    AgregarTarea editarTareaDialog = AgregarTarea.newInstance(taskId, taskName, taskLocation, taskDescription);
    editarTareaDialog.show(((FragmentActivity) v.getContext()).getSupportFragmentManager(), "EditarTarea");
  }


  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_task_single, parent, false);
    return new ViewHolder(v);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tarea, ubicacion, descripcion;
    ImageView editarTarea;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      tarea = itemView.findViewById(R.id.tviewTarea);
      ubicacion = itemView.findViewById(R.id.txtNombre);
      descripcion = itemView.findViewById(R.id.txtApellidos);
      editarTarea = itemView.findViewById(R.id.btnEditarTarea);
    }
  }
}
