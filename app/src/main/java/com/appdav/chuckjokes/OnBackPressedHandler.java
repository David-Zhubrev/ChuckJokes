package com.appdav.chuckjokes;

import android.app.Activity;

public interface OnBackPressedHandler {
    void attachBackPressedListener(OnBackPressedListener listener);
    void detachBackPressedListener(OnBackPressedListener source);

    /** Interface, which method is called from {@link Activity#onBackPressed()} method to make it possible to handle this action from fragment
     *
     */
    interface OnBackPressedListener{
        /**
         * Method is called when back button is pressed
         * Default implementation just pushes action back to activity
         * @return true, if action is consumed (fully completed) in fragment, false if action is not consumed and should be managed by activity
         */
        default boolean onBackPressed(){
            return false;
        }
    }
}
