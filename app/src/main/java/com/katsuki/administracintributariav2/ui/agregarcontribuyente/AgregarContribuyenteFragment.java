package com.katsuki.administracintributariav2.ui.agregarcontribuyente;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.katsuki.administracintributariav2.R;

import java.util.HashMap;
import java.util.Map;

public class AgregarContribuyenteFragment extends Fragment {

  private AgregarContribuyenteViewModel mViewModel;

  // DECLARAMOS VARIABLES
  Button agregarContribuyente;
  EditText nombre, documento, direccion,telefono,correo, actEconomica, estCivil, propiedad, ubicacion,
    area, valor, uso, historial, predios, adeudados, vencimiento, pagos, multa, fiscalizacion;

  private FirebaseFirestore mFirestore;

  public static AgregarContribuyenteFragment newInstance() {
    return new AgregarContribuyenteFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_agregar_contribuyente, container, false);

    // DECLARAR LA BASE DE DATOS
    mFirestore = FirebaseFirestore.getInstance();
    nombre = v.findViewById(R.id.txtNombreCompleto);
    documento = v.findViewById(R.id.txtDniRuc);
    direccion = v.findViewById(R.id.txtDireccionDomicilio);
    telefono = v.findViewById(R.id.txtTelefonoContacto);
    correo = v.findViewById(R.id.txtCorreoElectronico);
    actEconomica = v.findViewById(R.id.txtActividadEconomica);
    estCivil = v.findViewById(R.id.txtEstadoCivil);
    propiedad = v.findViewById(R.id.txtTipoPropiedad);
    ubicacion = v.findViewById(R.id.txtUbicacionPropiedad);
    area = v.findViewById(R.id.txtAreaTerreno);
    valor = v.findViewById(R.id.txtValorCatastral);
    uso = v.findViewById(R.id.txtUsoPropiedad);
    historial = v.findViewById(R.id.txtHistorialPagos);
    predios = v.findViewById(R.id.txtNumPredios);
    adeudados = v.findViewById(R.id.txtImpuestosAdeudados);
    vencimiento = v.findViewById(R.id.txtFechaVencimiento);
    pagos = v.findViewById(R.id.txtPagosRealizados);
    multa = v.findViewById(R.id.txtMultasRecargos);
    fiscalizacion = v.findViewById(R.id.txtFiscalizacion);

    // DECLARACION DEL BOTON
    agregarContribuyente = v.findViewById(R.id.btnAddContribuyente);

    //ACCION DEL BOTON
    agregarContribuyente.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String nombreContribuyente = nombre.getText().toString();
        String documentoContribuyente = documento.getText().toString();
        String direccionContribuyente = direccion.getText().toString();
        String telefonoContribuyente = telefono.getText().toString();
        String correoContribuyente = correo.getText().toString();
        String actEconomicaContribuyente = actEconomica.getText().toString();
        String estCivilContribuyente = estCivil.getText().toString();
        String propiedadContribuyente = propiedad.getText().toString();
        String ubicacionContribuyente = ubicacion.getText().toString();
        String areaContribuyente = area.getText().toString();
        String valorContribuyente = valor.getText().toString();
        String usoContribuyente = uso.getText().toString();
        String historialContribuyente = historial.getText().toString();
        String prediosContribuyente = predios.getText().toString();
        String adeudadosContribuyente = adeudados.getText().toString();
        String vencimientoContribuyente = vencimiento.getText().toString();
        String pagosContribuyente = pagos.getText().toString();
        String multaContribuyente = multa.getText().toString();
        String fiscalizacionContribuyente = fiscalizacion.getText().toString();

        // METODO PARA SUBIR LA INFORMACION A LA BASE DE DATOS
        postContribuyente(nombreContribuyente, documentoContribuyente,direccionContribuyente,telefonoContribuyente,
                correoContribuyente,actEconomicaContribuyente,estCivilContribuyente,propiedadContribuyente,
                ubicacionContribuyente,areaContribuyente,valorContribuyente,usoContribuyente,historialContribuyente,
                prediosContribuyente,adeudadosContribuyente,vencimientoContribuyente,pagosContribuyente,multaContribuyente,fiscalizacionContribuyente);
      }
    });

    return v;
  }

  // ESTE METODO SE CREA AUTOMATICAMENTE PARA PODER MANDAR A LA BASE DE DATOS
  // RECORDAR QUE EN LA BASE DE DATOS EN LAS REGLAS SE DEBE CAMBIAR A true
  private void postContribuyente(String nombreContribuyente, String documentoContribuyente, String direccionContribuyente, String telefonoContribuyente, String correoContribuyente, String actEconomicaContribuyente, String estCivilContribuyente, String propiedadContribuyente, String ubicacionContribuyente, String areaContribuyente, String valorContribuyente, String usoContribuyente, String historialContribuyente, String prediosContribuyente, String adeudadosContribuyente, String vencimientoContribuyente, String pagosContribuyente, String multaContribuyente, String fiscalizacionContribuyente) {
    Map<String, Object> map = new HashMap<>();
    map.put("nombre", nombreContribuyente);
    map.put("documento", documentoContribuyente);
    map.put("direccion", direccionContribuyente);
    map.put("telefono", telefonoContribuyente);
    map.put("correo", correoContribuyente);
    map.put("actEconomica", actEconomicaContribuyente);
    map.put("estCivil", estCivilContribuyente);
    map.put("propiedad", propiedadContribuyente);
    map.put("ubicacion", ubicacionContribuyente);
    map.put("area", areaContribuyente);
    map.put("valor", valorContribuyente);
    map.put("uso", usoContribuyente);
    map.put("historial", historialContribuyente);
    map.put("predios", prediosContribuyente);
    map.put("adeudados", adeudadosContribuyente);
    map.put("vencimiento", vencimientoContribuyente);
    map.put("pagos", pagosContribuyente);
    map.put("multa", multaContribuyente);
    map.put("fiscalizacion", fiscalizacionContribuyente);

    mFirestore.collection("contribuyente").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
      @Override
      public void onSuccess(DocumentReference documentReference) {
        Toast.makeText(getContext(), "Contribuyente agregado con exito", Toast.LENGTH_SHORT).show();
        nombre.setText("");
        documento.setText("");
        direccion.setText("");
        telefono.setText("");
        correo.setText("");
        actEconomica.setText("");
        estCivil.setText("");
        propiedad.setText("");
        ubicacion.setText("");
        area.setText("");
        valor.setText("");
        uso.setText("");
        historial.setText("");
        predios.setText("");
        adeudados.setText("");
        vencimiento.setText("");
        pagos.setText("");
        multa.setText("");
        fiscalizacion.setText("");

      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(getContext(), "Falla al registrar contribuyente", Toast.LENGTH_SHORT).show();
      }
    });

  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(AgregarContribuyenteViewModel.class);
    // TODO: Use the ViewModel
  }

}