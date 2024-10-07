package com.katsuki.administracintributariav2;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;

import com.katsuki.administracintributariav2.utilidades.ToastMatcher;

@RunWith(AndroidJUnit4.class)
public class RegisterTest {

  // Define la actividad que quieres probar
  @Rule
  public ActivityScenarioRule<Register> mActivityRule = new ActivityScenarioRule<>(Register.class);

  @Test
  public void testRegisterSuccess() {
    // Escribe texto en los campos de nombre, correo y contraseña
    onView(withId(R.id.txtNuevoNombre)).perform(typeText("Usuario de prueba"));
    onView(withId(R.id.txtNewEmail)).perform(typeText("testuser@correo.com"));
    onView(withId(R.id.txtNewPass)).perform(typeText("password123"), ViewActions.closeSoftKeyboard());

    // Haz clic en el botón de registrar
    onView(withId(R.id.btnRegistrarme)).perform(click());

    // Verifica que el Toast de "Usuario registrado con exito" es mostrado
    onView(withText("Usuario registrado con exito")).inRoot(new ToastMatcher())
            .check(matches(isDisplayed()));
  }

  @Test
  public void testRegisterEmptyFields() {
    // Deja los campos vacíos y haz clic en registrar
    onView(withId(R.id.btnRegistrarme)).perform(click());

    // Verifica que el mensaje de advertencia "Los campos no deben estar vacios" es mostrado
    onView(withText("Los campos no deben estar vacios")).check(matches(isDisplayed()));
  }

  @Test
  public void testBackButtonFunctionality() {
    // Haz clic en el botón de volver
    onView(withId(R.id.btnVolver)).perform(click());

    // Verifica que la actividad de Login se ha abierto correctamente
    onView(withId(R.id.txtCorreo)).check(matches(isDisplayed()));  // Asumiendo que 'txtCorreo' es un campo de Login
  }

  // Se puede agregar más pruebas aquí como prueba de registros con correos inválidos, contraseñas cortas, etc.

}
