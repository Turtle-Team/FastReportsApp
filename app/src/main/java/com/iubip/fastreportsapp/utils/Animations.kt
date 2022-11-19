package com.iubip.fastreportsapp.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

class Animations {

    fun showButtons(value: Boolean, view: View) {
        if (value) {
            view.visibility = View.VISIBLE
            view.alpha = 0f
            view.translationY = view.height.toFloat()
            view.animate()
                .setDuration(200)
                .translationY(0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                    }
                }).alpha(1f).start()
        } else {
            view.visibility = View.VISIBLE
            view.alpha = 1f
            view.translationY = 0f
            view.animate()
                .setDuration(200)
                .translationY(view.height.toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        view.visibility = View.GONE
                        super.onAnimationEnd(animation)
                    }
                }).alpha(0f).start()
        }
    }
}