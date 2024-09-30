package com.katsuki.administracintributariav2.ui.consultarusuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

  private final MutableLiveData<String> mText;

  public HomeViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is consultarusuario fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}