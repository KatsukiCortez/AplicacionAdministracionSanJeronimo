package com.katsuki.administracintributariav2;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class LoginTest {

  // Define la actividad que quieres probar
  @Rule
  public ActivityScenarioRule<Login> mActivityTestRule = new ActivityScenarioRule<>(Login.class);

  @Test
  public void testLoginSuccess() {
    // Escribe texto en los campos de correo y contraseña
    onView(withId(R.id.txtCorreo)).perform(typeText("test@correo.com"));
    onView(withId(R.id.txtPassword)).perform(typeText("password123"), ViewActions.closeSoftKeyboard());

    // Haz clic en el botón de ingresar
    onView(withId(R.id.btnLogin)).perform(click());

    // Verifica que el mensaje de bienvenida se muestra correctamente
    onView(withText("Bienvenido")).check(matches(isDisplayed()));
  }

  @Test
  public void testLoginEmptyFields() {
    // Deja los campos vacíos y haz clic en ingresar
    onView(withId(R.id.btnLogin)).perform(click());

    // Verifica que se muestra el mensaje de advertencia
    onView(withText("Llene los datos por favor")).check(matches(isDisplayed()));
  }

  @Test
  public void testRememberMeFunctionality() {
    // Marca el checkbox "recuerdame"
    onView(withId(R.id.chbRemember)).perform(click());

    // Escribe texto en los campos de correo y contraseña
    onView(withId(R.id.txtCorreo)).perform(typeText("test@correo.com"));
    onView(withId(R.id.txtPassword)).perform(typeText("password123"), ViewActions.closeSoftKeyboard());

    // Haz clic en el botón de ingresar
    onView(withId(R.id.btnLogin)).perform(click());

    // Verifica que el usuario es redirigido a la pantalla principal
    onView(withId(R.id.textView7)).check(matches(isDisplayed()));
  }
}